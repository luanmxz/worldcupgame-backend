package br.com.worldcupgame.dto;

public class UsuarioRankingDTO {

	private String nome;
	
	private Integer pontos;

	public UsuarioRankingDTO() {}
	
	public UsuarioRankingDTO(String nome, Integer pontos) {
		this.nome = nome;
		this.pontos = pontos;
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
