package br.com.worldcupgame.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.worldcupgame.model.Aposta;


public interface ApostaRepository extends JpaRepository<Aposta, Integer> {

	@Query("SELECT apo from Aposta apo WHERE apo.idUser.id = ?1")
	List<Aposta> findApostaByUserId(Long idUser);
	
}
