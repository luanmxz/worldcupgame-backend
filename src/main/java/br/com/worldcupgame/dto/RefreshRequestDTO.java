package br.com.worldcupgame.dto;

public class RefreshRequestDTO {

	private String refreshToken;

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public RefreshRequestDTO() {}
	
	public RefreshRequestDTO(String refreshToken) {
		super();
		this.refreshToken = refreshToken;
	}
}
