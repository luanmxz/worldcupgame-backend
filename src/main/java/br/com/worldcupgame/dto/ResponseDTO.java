package br.com.worldcupgame.dto;

public class ResponseDTO {

    private String token;
    private String refreshToken;
    
    public ResponseDTO() {}

	public ResponseDTO(String token, String refreshToken) {
		super();
		this.token = token;
		this.refreshToken = refreshToken;
	}
	
	public static ResponseDTO of(String token, String refreshToken) {
        return new ResponseDTO(token, refreshToken);
    }

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
}
