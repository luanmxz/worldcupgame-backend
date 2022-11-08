package br.com.worldcupgame.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class RecoveryPasswordToken {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	private String recoveryToken;
	
	@ManyToOne(targetEntity = Usuario.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
	private Usuario userId;

	public RecoveryPasswordToken() {}
	
	public RecoveryPasswordToken(String recoveryToken, Usuario userId) {
		super();
		this.recoveryToken = recoveryToken;
		this.userId = userId;
	}
	
	
	
}
