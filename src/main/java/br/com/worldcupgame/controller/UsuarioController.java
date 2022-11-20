package br.com.worldcupgame.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.worldcupgame.dto.AtualizaUsuarioDTO;
import br.com.worldcupgame.dto.NovaSenhaDTO;
import br.com.worldcupgame.dto.NovoUsuarioDTO;
import br.com.worldcupgame.dto.RoleDTO;
import br.com.worldcupgame.dto.UsuarioDTO;
import br.com.worldcupgame.dto.UsuarioRankingDTO;
import br.com.worldcupgame.model.RecoveryPasswordToken;
import br.com.worldcupgame.model.Role;
import br.com.worldcupgame.model.Usuario;
import br.com.worldcupgame.repository.RecoveryPasswordTokenRepository;
import br.com.worldcupgame.repository.RoleRepository;
import br.com.worldcupgame.repository.UsuarioRepository;
import br.com.worldcupgame.services.LogService;
import br.com.worldcupgame.services.RecoveryPasswordTokenService;
import br.com.worldcupgame.services.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private LogService logService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private RecoveryPasswordTokenService recoveryPasswordTokenService;
	
	@Autowired
	private RecoveryPasswordTokenRepository recoveryRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@GetMapping
	public ResponseEntity<List<UsuarioDTO>> findAll() {
		List<UsuarioDTO> list = usuarioService.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<UsuarioDTO> findById(@PathVariable Long id) {
		UsuarioDTO dto = usuarioService.findById(id);
		return ResponseEntity.ok().body(dto);
	}
	
	@GetMapping(value= "/ranking")
	public ResponseEntity<List<UsuarioRankingDTO>> findAllForRanking() {
		List<UsuarioRankingDTO> list = usuarioService.findAllForRanking();
		return ResponseEntity.ok().body(list);
	}
	
	@PostMapping(path= "")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<UsuarioDTO> insert(@Valid @RequestBody NovoUsuarioDTO dto) {
		UsuarioDTO NewDto = usuarioService.insert(dto);
		URI uri = ServletUriComponentsBuilder
					.fromCurrentRequest()
					.path("/{id}")
					.buildAndExpand(NewDto.getId())
					.toUri();
		return ResponseEntity.created(uri).body(NewDto);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<UsuarioDTO> update(@PathVariable Long id, @Valid @RequestBody AtualizaUsuarioDTO dto) {
		UsuarioDTO newDto = usuarioService.update(id, dto);
		return ResponseEntity.ok().body(newDto);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		usuarioService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value= "/new-password")
	public ResponseEntity<Usuario> changePassword(@RequestBody NovaSenhaDTO novaSenha){
		Optional<RecoveryPasswordToken> tokenUserId = recoveryRepository.findByToken(novaSenha.getToken());
		if(tokenUserId.isPresent()) {
			Usuario usuario = tokenUserId.get().getUserId();
			System.out.println("usuario: " + usuario);
			Optional<RecoveryPasswordToken> tokenInDataBase = recoveryPasswordTokenService.verifyToken(novaSenha.getToken(), usuario.getId());
			if (tokenInDataBase.isPresent()) {
				usuario = usuarioService.changePassword(novaSenha.getNovaSenha(), usuario.getId());
				recoveryPasswordTokenService.deleteToken(tokenInDataBase.get());
				return ResponseEntity.ok().body(usuario);
			}
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.badRequest().build();
	}
	
	@PutMapping(value= "{userId}/set-new-role")
	public ResponseEntity<Usuario> setNewRole(@PathVariable("userId") Long userId, @RequestBody RoleDTO roleDto){
		Usuario usuario = usuarioRepository.findById(userId).get();
		Role role = roleRepository.findById(roleDto.getId()).get();
		usuario.getRoles().add(role);
		usuarioRepository.save(usuario);
		return ResponseEntity.ok().body(usuario);
	}
	
	@PutMapping(value= "{userId}/remove-role")
	public ResponseEntity<Usuario> removeRoleFromUser(@PathVariable("userId") Long userId, @RequestBody RoleDTO roleDto) {
		Usuario usuario = usuarioRepository.findById(userId).get();
		Role role = roleRepository.findById(roleDto.getId()).get();
		usuario.getRoles().remove(role);
		usuarioRepository.save(usuario);
		return ResponseEntity.ok().body(usuario);
	}
	
	@GetMapping(value= "/find/{email}")
	public Usuario findByEmail(@PathVariable("email") String email){
		Usuario usuario = this.usuarioRepository.findByEmail(email);
		return usuario;
	}
}