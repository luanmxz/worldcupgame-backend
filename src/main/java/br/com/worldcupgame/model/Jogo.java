package br.com.worldcupgame.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.worldcupgame.dto.NovoJogoDTO;
import br.com.worldcupgame.enums.ResultadosEnum;


@Entity
@Table(name= "tb_jogo")
public class Jogo {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name= "id_jogo")
	private int id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Selecao idTimeA;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Selecao idTimeB;
	
	private Integer golsA;
	private Integer golsB;
	
	@Column(name= "data_inicio")
	@NotNull
	@DateTimeFormat(style= "dd-MM-yyyy HH:mm")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern= "dd-MM-yyyy HH:mm")
	private LocalDateTime dataInicio;
	
	private boolean jaAconteceu = false;
	
	@Enumerated(EnumType.STRING)
	private ResultadosEnum resultado = ResultadosEnum.ND;
	
	public Jogo() {}
	
	public Jogo(NovoJogoDTO novoJogoDTO, LocalDateTime dataDoJogo, Selecao selecaoA, Selecao selecaoB) {
		this.idTimeA = selecaoA;
		this.idTimeB = selecaoB;
		this.golsA = novoJogoDTO.getGolsA();
		this.golsB = novoJogoDTO.getGolsB();
		this.dataInicio = dataDoJogo;
		this.jaAconteceu = novoJogoDTO.isJaAconteceu();
		this.resultado = novoJogoDTO.getResultado();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Selecao getIdTimeA() {
		return idTimeA;
	}
	public void setIdTimeA(Selecao idTimeA) {
		this.idTimeA = idTimeA;
	}
	public Selecao getIdTimeB() {
		return idTimeB;
	}
	public void setIdTimeB(Selecao idTimeB) {
		this.idTimeB = idTimeB;
	}

	public Integer getGolsA() {
		return golsA;
	}
	public void setGolsA(Integer golsA) {
		this.golsA = golsA;
	}
	public Integer getGolsB() {
		return golsB;
	}
	public void setGolsB(Integer golsB) {
		this.golsB = golsB;
	}
	public LocalDateTime getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(LocalDateTime dataInicio) {
		this.dataInicio = dataInicio;
	}
	public boolean isJaAconteceu() {
		return jaAconteceu;
	}
	public void setJaAconteceu(boolean jaAconteceu) {
		this.jaAconteceu = jaAconteceu;
	}
	public ResultadosEnum getResultado() {
		return resultado;
	}
	public void setResultado(ResultadosEnum resultado) {
		this.resultado = resultado;
	}
	
	
	
}
