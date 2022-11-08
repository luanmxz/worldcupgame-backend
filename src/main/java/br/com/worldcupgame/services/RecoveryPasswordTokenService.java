package br.com.worldcupgame.services;

import java.util.Optional;
import java.util.UUID;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import br.com.worldcupgame.model.RecoveryPasswordToken;
import br.com.worldcupgame.model.Usuario;
import br.com.worldcupgame.repository.RecoveryPasswordTokenRepository;

@Service
public class RecoveryPasswordTokenService {
	
	@Autowired
	private RecoveryPasswordTokenRepository recoveryRepository;
	
	@Autowired
	private EmailService emailService;
	
	@Transactional
	public SimpleMailMessage createRecoveryTokenEmail(String token, Usuario usuario) {
		
		emailService.createRecoveryTokenEmail(token, usuario);
		
		return null;	
	}
	
	public Optional<RecoveryPasswordToken> verifyToken(String token, Long id) {
		Optional<RecoveryPasswordToken> tokenInDataBase = recoveryRepository.findByUserId(id);
		if(!token.equals(tokenInDataBase.get().getRecoveryToken())) {
			return tokenInDataBase;
		}
		return tokenInDataBase;
	}
	
	
	public void deleteToken(RecoveryPasswordToken recoveryPasswordToken) {
		try {
			recoveryRepository.delete(recoveryPasswordToken);
			System.out.println("token with id: " + recoveryPasswordToken.getId() + " deleted!");
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException("Token dont exists");
		}
	}

	public void deleteTokenIfAlreadyExists(Usuario usuario) {
		Optional<RecoveryPasswordToken> recoveryPasswordToken = 
				recoveryRepository.findByUserId(usuario.getId());
		if (recoveryPasswordToken.isPresent()) {
			this.deleteToken(recoveryPasswordToken.get());
		}
	}

	public RecoveryPasswordToken generateRandomTokenAndSendEmail(Usuario usuario) {
		String generateToken = UUID.randomUUID().toString();
		RecoveryPasswordToken token = new RecoveryPasswordToken(generateToken, usuario);
		this.createRecoveryTokenEmail(generateToken, usuario);
		return token;
	}
	
}
