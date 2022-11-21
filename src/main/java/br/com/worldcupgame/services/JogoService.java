package br.com.worldcupgame.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.worldcupgame.dto.JogoDTO;
import br.com.worldcupgame.dto.NovoJogoDTO;
import br.com.worldcupgame.model.Jogo;
import br.com.worldcupgame.model.Selecao;
import br.com.worldcupgame.repository.JogoRepository;
import br.com.worldcupgame.repository.SelecaoRepository;

@Service
public class JogoService {

	@Autowired
	JogoRepository jogoRepository;

	public boolean updateById(Integer id, JogoDTO jogoDTO) {
		Optional<Jogo> jogo = jogoRepository.findById(id);
		if (jogo.isPresent()) {
			jogo.get().setGolsA(jogoDTO.getGolsA());
			jogo.get().setGolsB(jogoDTO.getGolsB());
			jogo.get().setJaAconteceu(jogoDTO.isJaAconteceu());
			jogo.get().setResultado(jogoDTO.getResultado());
			
			return true;
		}
		return false;
	}
	
	public Jogo formataJogo(NovoJogoDTO novoJogoDTO, SelecaoRepository selecaoRepository) {
		LocalDateTime dataDoJogo = this.formatDateTime(novoJogoDTO.getDataInicio());
		
		Optional<Selecao> selecaoA = selecaoRepository.findById(novoJogoDTO.getIdTimeA());
		Optional<Selecao> selecaoB = selecaoRepository.findById(novoJogoDTO.getIdTimeB());
		
		Jogo jogo = new Jogo(novoJogoDTO, dataDoJogo, selecaoA.get(), selecaoB.get());
		return jogo;
	}
	
	public LocalDateTime formatDateTime(String data) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
		LocalDateTime dateTime = LocalDateTime.parse(data, formatter);
		return dateTime;
	}
	
	
	
}
