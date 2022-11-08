package br.com.worldcupgame.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RecoveryPasswordTokenDTO {

	private Long id;
	private String recoveryToken;
	private UsuarioDTO userDTO;
	
}