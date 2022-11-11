package br.com.worldcupgame.dto;

import java.time.LocalDateTime;

public class LogDTO {

	private Long id;
	
	private Long usuario;
	
	private String action;
	
	private LocalDateTime dateOfAction = LocalDateTime.now();
	
	public LogDTO() {}
	
	public LogDTO(Long usuario, String action) {
		this.usuario = usuario;
		this.action = action;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUsuario() {
		return usuario;
	}

	public void setUsuario(Long usuario) {
		this.usuario = usuario;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public LocalDateTime getDateOfAction() {
		return dateOfAction;
	}

	public void setDateOfAction(LocalDateTime dateOfAction) {
		this.dateOfAction = dateOfAction;
	}
	
	
}
