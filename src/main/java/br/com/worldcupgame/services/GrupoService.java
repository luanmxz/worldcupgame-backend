package br.com.worldcupgame.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.worldcupgame.model.Grupo;

@Service
public class GrupoService {

	public void atualizaGrupo(Optional<Grupo> grupo, Grupo grupoAtualizado) {
		grupo.get().setNomeGrupo(grupoAtualizado.getNomeGrupo());
	}
}