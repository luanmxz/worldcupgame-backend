package br.com.worldcupgame.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.worldcupgame.model.Aposta;

@Service
public class ApostaService {

	public void atualizaAposta(Optional<Aposta> aposta, Aposta apostaAtualizada) {
		aposta.get().setApostouEm(apostaAtualizada.getApostouEm());
	}

}
