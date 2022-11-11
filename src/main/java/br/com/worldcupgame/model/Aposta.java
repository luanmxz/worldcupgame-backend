package br.com.worldcupgame.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.worldcupgame.dto.ApostaDTO;
import br.com.worldcupgame.enums.ResultadosEnum;

@Entity
@Table(name= "tb_apostas", uniqueConstraints = 
		@UniqueConstraint(columnNames = {"id_user", "id_jogo"}, name= "aposta_uk"))
public class Aposta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Enumerated(EnumType.STRING)
	private ResultadosEnum apostouEm;
	
	@Column(name= "esta_ativa")
	private boolean estaAtiva;
	
	@ManyToOne
	@JoinColumn(name = "id_user")
	private Usuario idUser;
	
	@ManyToOne
	@JoinColumn(name= "id_jogo")
	private Jogo idJogo;
	
	@DateTimeFormat(style= "dd-MM-yyyy HH:mm")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern= "dd-MM-yyyy HH:mm")
	private LocalDateTime dataDaAposta = LocalDateTime.now();
	

	public Aposta() {}
	
	public Aposta(ApostaDTO apostaDTO, Usuario idUser, Jogo idJogo) {
		this.apostouEm = apostaDTO.getApostouEm();
		this.estaAtiva = apostaDTO.isEstaAtiva();
		this.idUser = idUser;
		this.idJogo = idJogo;
	}
	
	public Aposta(ResultadosEnum apostouEm, boolean estaAtiva, Usuario idUser, Jogo idJogo) {
		super();
		this.apostouEm = apostouEm;
		this.estaAtiva = true;
		this.idUser = idUser;
		this.idJogo = idJogo;
	}

	public int getId() {
		return id;
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

	public Usuario getIdUser() {
		return idUser;
	}

	public Jogo getIdJogo() {
		return idJogo;
	}

	public void setIdJogo(Jogo idJogo) {
		this.idJogo = idJogo;
	}
	
	
}
