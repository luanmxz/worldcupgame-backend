package br.com.worldcupgame.model;

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

import br.com.worldcupgame.enums.GruposEnum;

@Entity
@Table(name= "tb_grupo")
public class Grupo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name= "nome_grupo")
	@Enumerated(EnumType.STRING)
	private GruposEnum nomeGrupo;
	
	@ManyToOne
	@JoinColumn(name= "id_selecao", unique = true)
	private Selecao idSelecao;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public GruposEnum getNomeGrupo() {
		return nomeGrupo;
	}

	public void setNomeGrupo(GruposEnum nomeGrupo) {
		this.nomeGrupo = nomeGrupo;
	}

	public Selecao getIdSelecao() {
		return idSelecao;
	}

	public void setIdSelecao(Selecao idSelecao) {
		this.idSelecao = idSelecao;
	}

	
	
	
}
