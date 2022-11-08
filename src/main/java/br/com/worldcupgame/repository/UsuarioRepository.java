package br.com.worldcupgame.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.worldcupgame.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Optional<Usuario> findUserByEmail(String email);
	
}
