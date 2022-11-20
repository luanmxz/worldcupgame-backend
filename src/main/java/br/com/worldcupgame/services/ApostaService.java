package br.com.worldcupgame.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.worldcupgame.dto.ApostaDTO;
import br.com.worldcupgame.model.Aposta;
import br.com.worldcupgame.model.Jogo;
import br.com.worldcupgame.model.Usuario;
import br.com.worldcupgame.repository.JogoRepository;
import br.com.worldcupgame.repository.UsuarioRepository;

@Service
public class ApostaService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private JogoRepository jogoRepository;
	
	public void atualizaAposta(Optional<Aposta> aposta, Aposta apostaAtualizada) {
		aposta.get().setApostouEm(apostaAtualizada.getApostouEm());
	}

	public Aposta formataAposta(ApostaDTO apostaDTO) {
		Optional<Usuario> usuario = usuarioRepository.findById(apostaDTO.getIdUser());
		Optional<Jogo> jogo = jogoRepository.findById(apostaDTO.getIdJogo());
		LocalDateTime dateTime = LocalDateTime.now();
		
		Aposta aposta = new Aposta(apostaDTO, usuario.get(), jogo.get(), dateTime);
		return aposta;
		
	}
	
	public LocalDateTime formatDateTime(String data) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
		LocalDateTime dateTime = LocalDateTime.parse(data, formatter);
		return dateTime;
	}
}