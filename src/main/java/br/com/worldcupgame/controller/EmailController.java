package br.com.worldcupgame.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.worldcupgame.dto.EmailDTO;
import br.com.worldcupgame.model.Email;
import br.com.worldcupgame.services.EmailService;

@RestController
public class EmailController {

	@Autowired
	private EmailService emailService;
	
	@PostMapping("/sending-email")	
	public ResponseEntity<Email> sendingEmail(@RequestBody @Valid EmailDTO emailDTO){
		Email email = new Email();
		emailService.convertDtoToEntity(email, emailDTO);
		emailService.sendEmail(email);
		return new ResponseEntity<>(email, HttpStatus.CREATED);
	}
	
}
