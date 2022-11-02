package br.com.worldcupgame.dto;

import br.com.worldcupgame.enums.ResultadosEnum;

public class NovoJogoDTO {

	private Integer id;
	private Integer golsA;
	private Integer golsB;
	private boolean jaAconteceu;
	private ResultadosEnum resultado;
	private Integer idTimeA;
	private Integer idTimeB;
	private String dataInicio;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public Integer getIdTimeA() {
		return idTimeA;
	}
	public void setIdTimeA(Integer idTimeA) {
		this.idTimeA = idTimeA;
	}
	public Integer getIdTimeB() {
		return idTimeB;
	}
	public void setIdTimeB(Integer idTimeB) {
		this.idTimeB = idTimeB;
	}
	public String getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}
}
