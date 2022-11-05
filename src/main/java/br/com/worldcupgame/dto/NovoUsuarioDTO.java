package br.com.worldcupgame.dto;

public class NovoUsuarioDTO extends UsuarioDTO{
	private static final long serialVersionUID = 1L;
	
	private String senha;
	
	
	NovoUsuarioDTO() {
		super();
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	

}

