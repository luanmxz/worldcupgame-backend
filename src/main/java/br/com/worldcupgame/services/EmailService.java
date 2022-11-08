package br.com.worldcupgame.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import br.com.worldcupgame.dto.EmailDTO;
import br.com.worldcupgame.enums.StatusEmail;
import br.com.worldcupgame.model.Email;
import br.com.worldcupgame.model.Usuario;
import br.com.worldcupgame.repository.EmailRepository;

@Service
public class EmailService {

	@Autowired
	private EmailRepository emailRepository;
	
	@Autowired
	private JavaMailSender emailSender;
	
	public void convertDtoToEntity(Email email, EmailDTO emailDTO) {
		email.setOwnerRef(emailDTO.getOwnerRef());
		email.setEmailFrom(emailDTO.getEmailFrom());
		email.setEmailTo(emailDTO.getEmailTo());
		email.setSubject(emailDTO.getSubject());
		email.setText(emailDTO.getText());
	}
	
	@SuppressWarnings("finally")
	public Email sendEmail(Email email) {
		email.setSendDateEmail(LocalDateTime.now());
		try {
			SimpleMailMessage msg = new SimpleMailMessage();
			msg.setFrom(email.getEmailFrom());
			msg.setTo(email.getEmailTo());
			msg.setSubject(email.getSubject());
			msg.setText(email.getText());
			emailSender.send(msg);
			
			email.setStatusEmail(StatusEmail.SENT);
		} catch (MailException e) {
			email.setStatusEmail(StatusEmail.ERROR);
			System.out.println("error: " + e);
		} finally {
			return emailRepository.save(email);
		}
	}

	@SuppressWarnings("finally")
	public Email createRecoveryTokenEmail(String token, Usuario usuario) {
		Email email = new Email();
		try {
			SimpleMailMessage msg = new SimpleMailMessage();
			
			email.setSendDateEmail(LocalDateTime.now());
			msg.setFrom("luanmarcenemxz22@gmail.com");
			msg.setTo(usuario.getEmail());
			msg.setSubject("Redefina sua senha");
			msg.setText("Seu token para redefinir a senha é: " + token);
			emailSender.send(msg);
			email.setStatusEmail(StatusEmail.SENT);
		} catch (MailException e) {
			email.setStatusEmail(StatusEmail.ERROR);
			System.out.println("error: " + e);
		} finally {
			return emailRepository.save(email);
		}
	}
	
}
