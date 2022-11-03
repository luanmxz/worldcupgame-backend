package br.com.worldcupgame.model;

import java.time.ZonedDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Builder;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(of = "uuid")
@Builder
@Entity
public class RefreshToken {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Builder.Default
	private String uuid = UUID.randomUUID().toString();
	 
	@Column
	private String token;
	
	@Column
	private ZonedDateTime expiration;
	
	@OneToOne
	@JoinColumn(nullable = false, name = "id_user")
	private Usuario usuario;
	
	public RefreshToken() {}

	public RefreshToken(Integer id, String uuid, String token, ZonedDateTime expiration, Usuario usuario) {
		super();
		this.id = id;
		this.uuid = uuid;
		this.token = token;
		this.expiration = expiration;
		this.usuario = usuario;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public ZonedDateTime getExpiration() {
		return expiration;
	}

	public void setExpiration(ZonedDateTime expiration) {
		this.expiration = expiration;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	
	
}
