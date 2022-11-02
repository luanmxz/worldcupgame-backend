package br.com.worldcupgame.dto;

import br.com.worldcupgame.model.Selecao;

public class SelecaoDTO {

	private Integer id;
	private Integer pontos;
	private Integer vitorias;
	private Integer derrotas;
	private Integer empates;
	private Integer jogos;
	private Integer gols;
	private boolean estaEliminada;
	
	public SelecaoDTO() {}
	
	public SelecaoDTO(Selecao selecao) {
		this.id = selecao.getId();
		this.pontos = selecao.getPontos();
		this.vitorias = selecao.getVitorias();
		this.derrotas = selecao.getDerrotas();
		this.empates = selecao.getEmpates();
		this.jogos = selecao.getJogos();
		this.estaEliminada = selecao.isEstaEliminada();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPontos() {
		return pontos;
	}

	public void setPontos(Integer pontos) {
		this.pontos = pontos;
	}

	public Integer getVitorias() {
		return vitorias;
	}

	public void setVitorias(Integer vitorias) {
		this.vitorias = vitorias;
	}

	public Integer getDerrotas() {
		return derrotas;
	}

	public void setDerrotas(Integer derrotas) {
		this.derrotas = derrotas;
	}

	public Integer getEmpates() {
		return empates;
	}

	public void setEmpates(Integer empates) {
		this.empates = empates;
	}

	public Integer getJogos() {
		return jogos;
	}

	public void setJogos(Integer jogos) {
		this.jogos = jogos;
	}

	public Integer getGols() {
		return gols;
	}

	public void setGols(Integer gols) {
		this.gols = gols;
	}

	public boolean isEstaEliminada() {
		return estaEliminada;
	}

	public void setEstaEliminada(boolean estaEliminada) {
		this.estaEliminada = estaEliminada;
	}
	
	
}
