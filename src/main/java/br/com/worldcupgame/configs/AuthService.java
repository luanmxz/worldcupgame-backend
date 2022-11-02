package br.com.worldcupgame.configs;

import javax.transaction.Transactional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.worldcupgame.model.Usuario;
import br.com.worldcupgame.repository.UsuarioRepository;

@Service
@Transactional
public class AuthService implements UserDetailsService {

	final UsuarioRepository usuarioRepository;
	
	public AuthService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByUsername(username)
	                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
	        return new User(usuario.getUsername(), usuario.getPassword(), true, true, true,true, usuario.getAuthorities());
	}
}
