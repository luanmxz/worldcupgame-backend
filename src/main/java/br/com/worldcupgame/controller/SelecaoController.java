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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.worldcupgame.dto.SelecaoDTO;
import br.com.worldcupgame.model.Selecao;
import br.com.worldcupgame.repository.SelecaoRepository;
import br.com.worldcupgame.services.SelecaoService;

@RestController
@RequestMapping(path = "/selecoes")
public class SelecaoController {

	@Autowired
	SelecaoRepository selecaoRepository;
	
	@Autowired
	SelecaoService selecaoService;
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<Selecao> findById (@PathVariable("id") Integer id){
		return selecaoRepository.findById(id)
				.map(selecao -> ResponseEntity.ok().body(selecao))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping(path = "")
	public Iterable<Selecao> findAll(){
		return selecaoRepository.findAll();
	}
	
	@PutMapping(path = "/{id}")
	@Transactional
	public ResponseEntity<Selecao> updateById(@PathVariable("id") Integer id, @RequestBody SelecaoDTO selecaoDTO){
		boolean selecaoIsValid = selecaoService.updateById(id, selecaoDTO);
		if (selecaoIsValid) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping(path = "")
	@Transactional
	@ResponseStatus(HttpStatus.CREATED)
	public Selecao save(@RequestBody Selecao selecao){
		return selecaoRepository.save(selecao);
	}
	
	@DeleteMapping(path= "/{id}")
	@Transactional
	public ResponseEntity<?> deleteById(@PathVariable("id") Integer id) {
		Optional<Selecao> selecao = selecaoRepository.findById(id);
		if (selecao.isPresent()) {
			selecaoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping(path= "/name")
	public ResponseEntity<Selecao> findSelecaoByName(@RequestParam("nomeSelecao") String nomeSelecao) {
		Optional<Selecao> selecao = selecaoRepository.findSelecaoByName(nomeSelecao);
		if (selecao.isPresent()) {
			return ResponseEntity.ok().body(selecao.get());
		}
		return ResponseEntity.notFound().build();
	}
}
