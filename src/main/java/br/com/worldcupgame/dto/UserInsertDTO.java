package br.com.worldcupgame.dto;

public class UserInsertDTO extends UserDTO{
	private static final long serialVersionUID = 1L;
	
	private String senha;
	
	
	UserInsertDTO() {
		super();
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	

}

