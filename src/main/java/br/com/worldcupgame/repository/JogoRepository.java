package br.com.worldcupgame.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.worldcupgame.model.Jogo;

public interface JogoRepository extends JpaRepository<Jogo, Integer> {

}
