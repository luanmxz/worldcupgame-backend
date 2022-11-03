package br.com.worldcupgame.dto;

import br.com.worldcupgame.model.Usuario;

public class UsuarioDTO {

	private Integer id;
	private String nome;
	private Integer pontos;
	
	public UsuarioDTO() {}
	
	public UsuarioDTO(Usuario usuario) {
		this.id = usuario.getId();
		this.nome = usuario.getUsername();
		this.pontos = usuario.getPontos();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getPontos() {
		return pontos;
	}
	public void setPontos(Integer pontos) {
		this.pontos = pontos;
	}
	
	
	
}