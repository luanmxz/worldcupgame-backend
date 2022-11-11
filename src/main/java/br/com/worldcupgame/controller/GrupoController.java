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

import br.com.worldcupgame.model.Grupo;
import br.com.worldcupgame.repository.GrupoRepository;
import br.com.worldcupgame.services.GrupoService;

@RestController
@RequestMapping(path = "/api/grupos")
public class GrupoController {

	@Autowired
	GrupoRepository grupoRepository;
	
	@Autowired
	GrupoService grupoService;
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<Grupo> findById(@PathVariable("id") Integer id){
		return grupoRepository.findById(id)
				.map(grupo -> ResponseEntity.ok().body(grupo))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping(path = "")
	public Iterable<Grupo> findAll(){
		return grupoRepository.findAll();
	}
	
	@PutMapping(path = "/{id}")
	@Transactional
	public ResponseEntity<Grupo> updateById(@PathVariable("id") Integer id, Grupo grupoAtualizado){
		Optional<Grupo> grupo = grupoRepository.findById(id);
		if (grupo.isPresent()) {
			grupoService.atualizaGrupo(grupo, grupoAtualizado);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping(path = "")
	@Transactional
	@ResponseStatus(HttpStatus.CREATED)
	public Grupo save(@RequestBody Grupo grupo) {
		return grupoRepository.save(grupo);
	}
	
	@DeleteMapping(path = "/{id}")
	@Transactional
	public ResponseEntity<?> deleteById(@PathVariable("id") Integer id){
		Optional<Grupo> grupo = grupoRepository.findById(id);
		if (grupo.isPresent()) {
			grupoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
