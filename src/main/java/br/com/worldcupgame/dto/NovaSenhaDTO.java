package br.com.worldcupgame.dto;

public class NovaSenhaDTO {
	
	private String novaSenha;
	
	private String token;

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}
	
	public NovaSenhaDTO() {};
	
	public NovaSenhaDTO(String novaSenha, String token) {
		this.novaSenha = novaSenha;
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
