package br.com.worldcupgame.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.worldcupgame.dto.JogoDTO;
import br.com.worldcupgame.dto.SelecaoDTO;
import br.com.worldcupgame.model.Grupo;
import br.com.worldcupgame.model.Jogo;
import br.com.worldcupgame.model.Selecao;
import br.com.worldcupgame.repository.GrupoRepository;
import br.com.worldcupgame.repository.SelecaoRepository;

@Service
public class SelecaoService {

	@Autowired
	SelecaoRepository selecaoRepository;
	
	@Autowired
	GrupoRepository grupoRepository;
	
	public boolean updateById(Integer id, SelecaoDTO selecaoDTO) {
		Optional<Selecao> selecao = selecaoRepository.findById(id);
		if (selecao.isPresent()) {
			selecao.get().setPontos(selecaoDTO.getPontos());
			selecao.get().setVitorias(selecaoDTO.getVitorias());
			selecao.get().setDerrotas(selecaoDTO.getDerrotas());
			selecao.get().setEmpates(selecaoDTO.getEmpates());
			selecao.get().setGols(selecaoDTO.getGols());
			selecao.get().setJogos(selecaoDTO.getJogos());
			return true;
		}
		return false;	
	}

	public Selecao convertDtoToEntity(SelecaoDTO selecaoDto) {
		Grupo grupo = grupoRepository.findById(selecaoDto.getGrupo()).get();
		Selecao newSelecao = new Selecao(selecaoDto);
		newSelecao.setGrupo(grupo);
		return newSelecao;
	}
	
	public void atualizaEstatisticas(Jogo jogo, JogoDTO jogoDTO) {
		Selecao selecaoA = jogo.getIdTimeA();
		Selecao selecaoB = jogo.getIdTimeB();
		
		selecaoA.setJogos(selecaoA.getJogos() + 1);
		selecaoB.setJogos(selecaoB.getJogos() + 1);
		selecaoA.setGols(selecaoA.getGols() + jogoDTO.getGolsA());
		selecaoB.setGols(selecaoB.getGols() + jogoDTO.getGolsB());
		
		if(jogoDTO.getResultado().equals("A")) {
			selecaoA.setPontos(selecaoA.getPontos() + 1);
			selecaoA.setVitorias(selecaoA.getVitorias() + 1);
			selecaoB.setDerrotas(selecaoB.getDerrotas() + 1);
		} else if (jogoDTO.getResultado().equals("B")) {
			selecaoB.setPontos(selecaoB.getPontos() + 1);
			selecaoB.setVitorias(selecaoB.getVitorias() + 1);
			selecaoA.setDerrotas(selecaoA.getDerrotas() + 1);
		} else {
			selecaoA.setEmpates(selecaoA.getEmpates() + 1);
			selecaoB.setEmpates(selecaoB.getEmpates() + 1);
		}
		
		jogo.setJaAconteceu(true);
	}
	
	
}
