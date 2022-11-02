package br.com.worldcupgame.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.worldcupgame.model.Aposta;

public interface ApostaRepository extends CrudRepository<Aposta, Integer> {

}
