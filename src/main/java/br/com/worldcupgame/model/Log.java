package br.com.worldcupgame.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.worldcupgame.dto.LogDTO;


@Entity(name= "tb_log")
public class Log {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Usuario usuario;
	
	private String action;
	
	@Column(name= "dateOfAction")
	@NotNull
	@DateTimeFormat(style= "dd-MM-yyyy HH:mm")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern= "dd-MM-yyyy HH:mm")
	private LocalDateTime dateOfAction = LocalDateTime.now();
	
	public Log() {}
	
	public Log(LogDTO logDTO, Usuario usuario) {
		this.action = logDTO.getAction();
		this.usuario = usuario;
	}
	
	public Log(String action, Usuario usuario) {
		this.action = action;
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
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
