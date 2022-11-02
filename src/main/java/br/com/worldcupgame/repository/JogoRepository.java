package br.com.worldcupgame.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.worldcupgame.model.Jogo;

public interface JogoRepository extends CrudRepository<Jogo, Integer> {

}
