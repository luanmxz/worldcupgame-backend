package br.com.worldcupgame.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.worldcupgame.dto.SelecaoDTO;
import br.com.worldcupgame.model.Grupo;
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
	
}
