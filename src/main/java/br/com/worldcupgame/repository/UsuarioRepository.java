package br.com.worldcupgame.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import br.com.worldcupgame.model.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {
	
	Optional<Usuario> findByUsername(String username);
	
}
