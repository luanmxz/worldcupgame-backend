package br.com.worldcupgame.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.worldcupgame.dto.SelecaoDTO;

@Entity
@Table(name= "tb_selecao")
public class Selecao {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name= "id_selecao")
	private Integer id;
	
	@Column(name= "nome_selecao")
	private String nomeSelecao;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Grupo grupo;
	
	private String brasao;
	
	private Integer pontos;
	private Integer vitorias;
	private Integer derrotas;
	private Integer empates;
	private Integer jogos;
	private Integer gols;
	
	
	public Selecao() {}
	
	public Selecao(SelecaoDTO selecaoDto) {
		this.nomeSelecao = selecaoDto.getNomeSelecao();
		this.pontos = selecaoDto.getPontos();
		this.vitorias = selecaoDto.getVitorias();
		this.derrotas = selecaoDto.getDerrotas();
		this.empates = selecaoDto.getEmpates();
		this.jogos = selecaoDto.getJogos();
		this.gols = selecaoDto.getGols();
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeSelecao() {
		return nomeSelecao;
	}

	public void setNomeSelecao(String nomeSelecao) {
		this.nomeSelecao = nomeSelecao;
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

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}	
	
	public String getBrasao() {
		return this.brasao;
	}
	
}
