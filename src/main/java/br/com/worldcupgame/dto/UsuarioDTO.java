package br.com.worldcupgame.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import br.com.worldcupgame.model.Usuario;

public class UsuarioDTO implements Serializable {
	private static final long serialVersionUID = 1L; 

	private Long id;
	
	@NotBlank
	private String nome;
	
	@Email
	private String email;
	
	private Integer pontos = 0;
	
	Set<RoleDTO> roles = new HashSet<>();
	
	public UsuarioDTO() {}

	public UsuarioDTO(Long id, String nome, String sobrenome, String email) {
		this.id = id;
		this.nome = nome;
		this.email = email;
	}
	
	public UsuarioDTO(Usuario entity) {
		id = entity.getId();
		nome = entity.getUsername();
		email = entity.getEmail();
		entity.getRoles().forEach(role -> this.roles.add(new RoleDTO(role)));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<RoleDTO> getRoles() {
		return roles;
	}

	public Integer getPontos() {
		return pontos;
	}

	public void setPontos(Integer pontos) {
		this.pontos = pontos;
	}	
}
