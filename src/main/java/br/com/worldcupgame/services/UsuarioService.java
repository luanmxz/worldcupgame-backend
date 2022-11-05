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

import br.com.worldcupgame.dto.RoleDTO;
import br.com.worldcupgame.dto.UsuarioDTO;
import br.com.worldcupgame.dto.NovoUsuarioDTO;
import br.com.worldcupgame.dto.AtualizaUsuarioDTO;
import br.com.worldcupgame.model.Role;
import br.com.worldcupgame.model.Usuario;
import br.com.worldcupgame.repository.RoleRepository;
import br.com.worldcupgame.repository.UsuarioRepository;

@Service
@Transactional
public class UsuarioService implements UserDetailsService {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Transactional
	public List<UsuarioDTO> findAll() {
		List<Usuario> list =  repository.findAll();
		return list.stream().map(x -> new UsuarioDTO(x)).collect(Collectors.toList());
	}

	@Transactional
	public UsuarioDTO findById(Long id) {
		Optional<Usuario> obj = repository.findById(id);
		Usuario entity = obj.orElseThrow(() -> new EntityNotFoundException("Entity not found"));
		return new UsuarioDTO(entity);
	}

	@Transactional
	public UsuarioDTO insert(NovoUsuarioDTO dto) {
		Usuario entity = new Usuario();
		convertDtoToEntity(dto, entity);
		entity.setPassword(passwordEncoder.encode(dto.getSenha()));
		entity = repository.save(entity);
		return new UsuarioDTO(entity);
	}
	
	@Transactional
	public UsuarioDTO update(Long id, AtualizaUsuarioDTO dto) {
		try {
			@SuppressWarnings("deprecation")
			Usuario entity = repository.getOne(id);
			convertDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new UsuarioDTO(entity);
		} 
		catch(EntityNotFoundException e) {
			throw new EntityNotFoundException("Id not found" + id);
		}
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
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
			@SuppressWarnings("deprecation")
			Role role = roleRepository.getOne(roleDto.getId());
			entity.getRoles().add(role);
		}
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario user = repository.findUserByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("Email not found");
		}
		return user;
	}
}