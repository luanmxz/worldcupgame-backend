package br.com.worldcupgame.controller;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.worldcupgame.dto.ApostaDTO;
import br.com.worldcupgame.model.Aposta;
import br.com.worldcupgame.model.Usuario;
import br.com.worldcupgame.repository.ApostaRepository;
import br.com.worldcupgame.repository.UsuarioRepository;
import br.com.worldcupgame.services.ApostaService;
import br.com.worldcupgame.services.LogService;

@RestController
@RequestMapping(path = "/api/apostas")
public class ApostaController {

	@Autowired
	ApostaRepository apostaRepository;
	
	@Autowired
	ApostaService apostaService;
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	LogService logService;
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<Aposta> findById(@PathVariable("id") Integer id){
		return apostaRepository.findById(id)
				.map(aposta -> ResponseEntity.ok().body(aposta))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping(path= "/users/{userId}")
	public List<Aposta> findByUserId(@PathVariable("userId") Long userId){
		return apostaRepository.findApostaByUserId(userId);
	}
	
	@GetMapping(path = "")
	public Iterable<Aposta> findAll(){
		return apostaRepository.findAll();
	}
	
	@PutMapping(path = "/{id}")
	public ResponseEntity<Aposta> updateById(@PathVariable("id") Integer id, Aposta apostaAtualizada){
		Optional<Aposta> aposta = apostaRepository.findById(id);
		if (aposta.isPresent()) {
			apostaService.atualizaAposta(aposta, apostaAtualizada);
			String action = "Atualizou a aposta de ID: " + aposta.get().getId();
			Usuario usuario = aposta.get().getIdUser();
			logService.formataLog(action, usuario);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping(path = "")
	@Transactional
	@ResponseStatus(HttpStatus.CREATED)
	public Aposta save(@RequestBody @Valid ApostaDTO apostaDTO) {
		Aposta aposta = apostaService.formataAposta(apostaDTO);
		String action = "Realizou uma aposta('ID':" + aposta.getId() + ")"
				+ " no jogo de ID: " + aposta.getIdJogo().getId() + "!";
		logService.formataLog(action, aposta.getIdUser());
		return apostaRepository.save(aposta);
	}
	
	@DeleteMapping(path = "/{id}")
	@Transactional
	public ResponseEntity<?> deleteById(@PathVariable("id") Integer id){
		Optional<Aposta> aposta = apostaRepository.findById(id);
		if (aposta.isPresent()) {
			String action = "Deletou a aposta com id: " + aposta.get().getId();
			logService.formataLog(action, aposta.get().getIdUser());
			apostaRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
