package br.com.worldcupgame.controller;

import java.util.ArrayList;
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

import br.com.worldcupgame.dto.UsuarioDTO;
import br.com.worldcupgame.model.Usuario;
import br.com.worldcupgame.repository.UsuarioRepository;
import br.com.worldcupgame.services.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private UsuarioService usuarioService;

	@GetMapping(path= "/master/{id}")
	public ResponseEntity<Usuario> findByIdMaster(@PathVariable("id") Integer id) {
		return repository.findById(id)
				.map(dadosUsuario -> ResponseEntity.ok().body(dadosUsuario))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping(path= "/master")
	public Iterable<Usuario> findAllMaster(){
		return repository.findAll();
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<UsuarioDTO> findById(@PathVariable("id") Integer id) {
		return repository.findById(id)
				.map(usuario -> ResponseEntity.ok().body(new UsuarioDTO(usuario)))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping(path = "")
	public Iterable<UsuarioDTO> findAll(){
		ArrayList<UsuarioDTO> usuariosDTO = new ArrayList<>();
		repository.findAll().forEach(usuario -> usuariosDTO.add(new UsuarioDTO(usuario)));
		return usuariosDTO;
	}
	
	@PostMapping(path= "")
	@Transactional
	@ResponseStatus(code = HttpStatus.CREATED)
	public Usuario save(@RequestBody Usuario usuario) {
		return repository.save(usuario);
	}
	
	@PutMapping(path= "/{id}")
	@Transactional
	public ResponseEntity<Usuario> updateById(@PathVariable("id") Integer id, @RequestBody Usuario usuarioAtualizado){
		boolean usuarioIsValid = usuarioService.updateUser(id, usuarioAtualizado);
		if (usuarioIsValid) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
		}
	
	@DeleteMapping(path= "/{id}")
	@Transactional
	public ResponseEntity<?> deleteById(@PathVariable("id") Integer id) {
		Optional<Usuario> usuario = repository.findById(id);
		if (usuario.isPresent()) {
			repository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}