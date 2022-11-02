package br.com.worldcupgame.controller;

import java.util.Optional;

import javax.transaction.Transactional;

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

import br.com.worldcupgame.model.Aposta;
import br.com.worldcupgame.repository.ApostaRepository;
import br.com.worldcupgame.services.ApostaService;

@RestController
@RequestMapping(path = "/api/apostas")
public class ApostaController {

	@Autowired
	ApostaRepository apostaRepository;
	
	@Autowired
	ApostaService apostaService;
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<Aposta> findById(@PathVariable("id") Integer id){
		return apostaRepository.findById(id)
				.map(aposta -> ResponseEntity.ok().body(aposta))
				.orElse(ResponseEntity.notFound().build());
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
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping(path = "/nova-aposta")
	@Transactional
	@ResponseStatus(HttpStatus.CREATED)
	public Aposta save(@RequestBody Aposta aposta) {
		return apostaRepository.save(aposta);
	}
	
	@DeleteMapping(path = "/{id}")
	@Transactional
	public ResponseEntity<?> deleteById(@PathVariable("id") Integer id){
		Optional<Aposta> aposta = apostaRepository.findById(id);
		if (aposta.isPresent()) {
			apostaRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
