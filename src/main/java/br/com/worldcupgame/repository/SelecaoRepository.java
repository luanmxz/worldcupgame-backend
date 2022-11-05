package br.com.worldcupgame.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.worldcupgame.model.Selecao;

public interface SelecaoRepository extends JpaRepository<Selecao, Integer>{
	
	@Query("SELECT s FROM Selecao s WHERE s.nomeSelecao = ?1")
	Optional<Selecao> findSelecaoByName(String nomeSelecao);

}
