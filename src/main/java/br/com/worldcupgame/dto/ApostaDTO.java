package br.com.worldcupgame.dto;

import br.com.worldcupgame.enums.ResultadosEnum;

public class ApostaDTO {

	private Integer id;
	
	private ResultadosEnum apostouEm;
	
	private boolean estaAtiva = true;
	
	private Long idUser;
	
	private int idJogo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ResultadosEnum getApostouEm() {
		return apostouEm;
	}

	public void setApostouEm(ResultadosEnum apostouEm) {
		this.apostouEm = apostouEm;
	}

	public boolean isEstaAtiva() {
		return estaAtiva;
	}

	public void setEstaAtiva(boolean estaAtiva) {
		this.estaAtiva = estaAtiva;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public int getIdJogo() {
		return idJogo;
	}

	public void setIdJogo(int idJogo) {
		this.idJogo = idJogo;
	}
	
	
}
