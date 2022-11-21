package br.com.worldcupgame.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.worldcupgame.dto.AtualizaUsuarioDTO;
import br.com.worldcupgame.dto.JogoDTO;
import br.com.worldcupgame.dto.NovoUsuarioDTO;
import br.com.worldcupgame.dto.RoleDTO;
import br.com.worldcupgame.dto.UsuarioDTO;
import br.com.worldcupgame.dto.UsuarioRankingDTO;
import br.com.worldcupgame.model.Aposta;
import br.com.worldcupgame.model.Jogo;
import br.com.worldcupgame.model.Role;
import br.com.worldcupgame.model.Usuario;
import br.com.worldcupgame.repository.ApostaRepository;
import br.com.worldcupgame.repository.RoleRepository;
import br.com.worldcupgame.repository.UsuarioRepository;

@Service
@Transactional
public class UsuarioService implements UserDetailsService {
	
	@Autowired
	private LogService logService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private ApostaRepository apostaRepository;
	
	public List<UsuarioDTO> findAll() {
		List<Usuario> list =  usuarioRepository.findAll();
		return list.stream().map(x -> new UsuarioDTO(x)).collect(Collectors.toList());
	}
	
	public List<UsuarioRankingDTO> findAllForRanking() {
		List<Usuario> list =  usuarioRepository.findAll();
		return list.stream().map(x -> new UsuarioRankingDTO(x.getUsername(), x.getPontos())).collect(Collectors.toList());
	}

	public UsuarioDTO findById(Long id) {
		Optional<Usuario> obj = usuarioRepository.findById(id);
		Usuario entity = obj.orElseThrow(() -> new EntityNotFoundException("Entity not found"));
		return new UsuarioDTO(entity);
	}

	public UsuarioDTO insert(NovoUsuarioDTO dto) {
		Usuario entity = new Usuario();
		convertDtoToEntity(dto, entity);
		Role role = roleRepository.findById((long) 1).get(); 
		entity.getRoles().add(role);
		entity.setPassword(passwordEncoder.encode(dto.getSenha()));
		entity = usuarioRepository.save(entity);
		String action = "Usuario com o ID: " + entity.getId() + " criado.";
		logService.formataLog(action, entity);
		return new UsuarioDTO(entity);
	}
	
	public UsuarioDTO update(Long id, AtualizaUsuarioDTO dto) {
		try {
			Usuario entity = usuarioRepository.findById(id).get();
			convertDtoToEntity(dto, entity);
			entity = usuarioRepository.save(entity);
			String action = "Atualizou as informações.";
			logService.formataLog(action, entity);
			return new UsuarioDTO(entity);
		} 
		catch(EntityNotFoundException e) {
			throw new EntityNotFoundException("Id not found" + id);
		}
	}

	public void delete(Long id) {
		try {
			
			Usuario usuario = usuarioRepository.findById(id).get();
			String action = "Usuario de ID: " + usuario.getId() + " deletou a conta";
			logService.formataLog(action, usuario);
			usuarioRepository.deleteById(id);
		}
		catch(EmptyResultDataAccessException e) {
			throw new EntityNotFoundException("Id not found" + id);
		}
	}
	
	public void convertDtoToEntity(UsuarioDTO dto, Usuario entity) {
		entity.setUsername(dto.getNome());
		entity.setEmail(dto.getEmail());
		
		entity.getRoles().clear();
		for(RoleDTO roleDto : dto.getRoles()) {
			Role role = roleRepository.findById(roleDto.getId()).get();
			entity.getRoles().add(role);
		}
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<Usuario> user = usuarioRepository.findUserByEmail(username);
		if (!user.isPresent()) {
			throw new UsernameNotFoundException("Email not found");
		}
		return user.get();
	}

	public Usuario changePassword(String novaSenha, Long id) {
		Usuario usuario = this.usuarioRepository.findById(id).get();
		usuario.setPassword(passwordEncoder.encode(novaSenha));
		usuarioRepository.save(usuario);
		String action = "Usuario de ID: " + usuario.getId() + " alterou a senha";
		logService.formataLog(action, usuario);
		return usuario;
	}
	
	public void atualizaPontosUsuario(JogoDTO jogoDTO) {
		List<Aposta> apostas = apostaRepository.findAll();
		
		apostas.forEach((aposta) -> {
			if(aposta.getApostouEm().equals(jogoDTO.getResultado())) {
				Usuario usuario = aposta.getIdUser();
				usuario.setPontos(usuario.getPontos() + 1);
			}
		});
	}

}