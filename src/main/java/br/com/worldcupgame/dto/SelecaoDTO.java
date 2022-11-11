package br.com.worldcupgame.dto;

import br.com.worldcupgame.model.Selecao;

public class SelecaoDTO {

	private Integer id;
	private String nomeSelecao;
	private Integer pontos;
	private Integer vitorias;
	private Integer derrotas;
	private Integer empates;
	private Integer jogos;
	private Integer gols;
	private Integer grupoId;
	
	public SelecaoDTO() {}
	
	public SelecaoDTO(Selecao selecao) {
		this.id = selecao.getId();
		this.pontos = selecao.getPontos();
		this.vitorias = selecao.getVitorias();
		this.derrotas = selecao.getDerrotas();
		this.empates = selecao.getEmpates();
		this.jogos = selecao.getJogos();
	}

	public SelecaoDTO(String nomeSelecao, Integer id, Integer pontos, Integer vitorias, Integer derrotas, Integer empates, Integer jogos,
			Integer gols, Integer grupoId) {
		super();
		this.nomeSelecao = nomeSelecao;
		this.pontos = pontos;
		this.vitorias = vitorias;
		this.derrotas = derrotas;
		this.empates = empates;
		this.jogos = jogos;
		this.gols = gols;
		this.grupoId = grupoId;
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

	public Integer getGrupo() {
		return grupoId;
	}

	public void setGrupo(Integer grupo) {
		this.grupoId = grupo;
	}

	public String getNomeSelecao() {
		return nomeSelecao;
	}

	public void setNomeSelecao(String nomeSelecao) {
		this.nomeSelecao = nomeSelecao;
	}
	
	

}
