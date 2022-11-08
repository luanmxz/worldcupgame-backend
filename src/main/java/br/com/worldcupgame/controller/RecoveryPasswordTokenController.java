package br.com.worldcupgame.controller;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.worldcupgame.dto.EmailUsuarioDTO;
import br.com.worldcupgame.model.RecoveryPasswordToken;
import br.com.worldcupgame.model.Usuario;
import br.com.worldcupgame.repository.RecoveryPasswordTokenRepository;
import br.com.worldcupgame.repository.UsuarioRepository;
import br.com.worldcupgame.services.RecoveryPasswordTokenService;

@RestController
@RequestMapping(path= "/recovery-password")
public class RecoveryPasswordTokenController {

	@Autowired
	private RecoveryPasswordTokenRepository recoveryRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private RecoveryPasswordTokenService recoveryService;
	
	
	@PostMapping(path="")
	@Transactional
	public RecoveryPasswordToken recoveryPassword(@RequestBody EmailUsuarioDTO email){
		Optional<Usuario> usuario = usuarioRepository.findUserByEmail(email.getEmail());
		if (usuario.isEmpty()) {
			throw new EntityNotFoundException("Usuário com o email " + email.getEmail() + "não encontrado");
		}
		recoveryService.deleteTokenIfAlreadyExists(usuario.get());
		RecoveryPasswordToken token = recoveryService.generateRandomTokenAndSendEmail(usuario.get());
		return recoveryRepository.save(token);
		
	}
	
	@GetMapping(path= "/{id}")
	public ResponseEntity<RecoveryPasswordToken> findTokenByUserId(@PathVariable("id") Long id) {
		RecoveryPasswordToken token = recoveryRepository.findByUserId(id).get();
		return ResponseEntity.ok().body(token);
	}
	
}
