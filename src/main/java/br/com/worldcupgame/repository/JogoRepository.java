package br.com.worldcupgame.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.worldcupgame.model.Jogo;

public interface JogoRepository extends JpaRepository<Jogo, Integer> {

	@Query("SELECT jogos FROM Jogo jogos WHERE jogos.idTimeA.grupo.id = ?1")
	List<Jogo> findJogoByGroup(Integer groupName);
	
}
