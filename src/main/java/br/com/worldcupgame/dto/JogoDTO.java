package br.com.worldcupgame.dto;

import br.com.worldcupgame.enums.ResultadosEnum;
import br.com.worldcupgame.model.Jogo;

public class JogoDTO {

	private Integer id;
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	private Integer golsA;
	private Integer golsB;
	private boolean jaAconteceu;
	private ResultadosEnum resultado;
	
	public JogoDTO() {}
	
	public JogoDTO(Jogo jogo) {
		
		this.id = jogo.getId();
		this.golsA = jogo.getGolsA();
		this.golsB = jogo.getGolsB();
		this.jaAconteceu = jogo.isJaAconteceu();
		this.resultado = jogo.getResultado();
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
	
	
	
}
