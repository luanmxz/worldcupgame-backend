package br.com.worldcupgame.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.worldcupgame.model.Email;

@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {

	
	
}
