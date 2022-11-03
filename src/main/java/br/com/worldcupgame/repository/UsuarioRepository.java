package br.com.worldcupgame.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.worldcupgame.model.Usuario;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {
	
	Optional<Usuario> findUserByUsername(String username);
	Optional<Usuario> findUserByEmail(String email);
	
}
