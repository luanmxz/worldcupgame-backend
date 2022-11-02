package br.com.worldcupgame.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.worldcupgame.model.Usuario;
import br.com.worldcupgame.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;
	
	public boolean updateUser(Integer id, Usuario usuarioAtualizado) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		if (usuario.isPresent()) {
			usuario.get().setUsername(usuarioAtualizado.getUsername());
			usuario.get().setEmail(usuarioAtualizado.getEmail());
			usuario.get().setPassword(usuarioAtualizado.getPassword());
			usuario.get().setPontos(usuario.get().getPontos());
			usuario.get().setCriadoEm(usuario.get().getCriadoEm());
			usuario.get().setAtualizadoEm(LocalDateTime.now());
			
			return true;
		}
		return false;
	}
	
}