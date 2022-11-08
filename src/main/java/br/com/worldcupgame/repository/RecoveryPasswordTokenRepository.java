package br.com.worldcupgame.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.worldcupgame.model.RecoveryPasswordToken;

public interface RecoveryPasswordTokenRepository extends JpaRepository<RecoveryPasswordToken, Long>{

	@Query("SELECT t FROM RecoveryPasswordToken t JOIN t.userId u WHERE u.id = :usuarioId")
	Optional<RecoveryPasswordToken> findByUserId(Long usuarioId);
	
}
