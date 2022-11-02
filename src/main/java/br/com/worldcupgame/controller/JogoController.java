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

import br.com.worldcupgame.dto.JogoDTO;
import br.com.worldcupgame.dto.NovoJogoDTO;
import br.com.worldcupgame.model.Jogo;
import br.com.worldcupgame.model.Usuario;
import br.com.worldcupgame.repository.JogoRepository;
import br.com.worldcupgame.repository.SelecaoRepository;
import br.com.worldcupgame.services.JogoService;

@RestController
@RequestMapping(path = "/api/jogos")
public class JogoController {

	@Autowired
	SelecaoRepository selecaoRepository;
	
	@Autowired
	JogoRepository jogoRepository;
	
	@Autowired
	JogoService jogoService;
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<Jogo> findById(@PathVariable("id") Integer id){
		return jogoRepository.findById(id)
				.map(jogo -> ResponseEntity.ok().body(jogo))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping(path = "")
	public Iterable<Jogo> findAll(){
		return jogoRepository.findAll();
	}
	
	@PutMapping(path = "/{id}")
	@Transactional
	public ResponseEntity<Jogo> updateById(@PathVariable("id") Integer id, @RequestBody JogoDTO jogoDTO){
		boolean jogoIsValid = jogoService.updateById(id, jogoDTO);
		if (jogoIsValid) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping(path = "/save")
	@Transactional
	@ResponseStatus(HttpStatus.CREATED)
	public Jogo save(Usuario usuario, @RequestBody NovoJogoDTO novoJogoDTO){
		Jogo jogo = jogoService.formataJogo(novoJogoDTO, selecaoRepository);
		return jogoRepository.save(jogo);
	}
	
	@DeleteMapping(path = "/{id}")
	@Transactional
	public ResponseEntity<?> deleteById(@PathVariable("id") Integer id){
		Optional<Jogo> jogo = jogoRepository.findById(id);
		if (jogo.isPresent()) {
			jogoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	
}
