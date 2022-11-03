package br.com.worldcupgame.configs.security;

import lombok.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.worldcupgame.dto.RefreshRequestDTO;
import br.com.worldcupgame.dto.ResponseDTO;
import br.com.worldcupgame.model.RefreshToken;
import br.com.worldcupgame.model.Usuario;
import br.com.worldcupgame.repository.RefreshTokenRepository;

@Service
public class RefreshTokenService {

	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	RefreshTokenRepository refreshTokenRepository;
	
	@Value("${jwt.refreshToken.expiration}")
	private int expirationTime;
	
	  public String createToken(Usuario usuario) {
	        var refreshToken = refreshTokenRepository.save(RefreshToken.builder()
	                .token(UUID.randomUUID().toString())
	                .usuario(usuario)
	                .expiration(ZonedDateTime.now(ZoneId.systemDefault()).plusMinutes(expirationTime))
	                .build());
	        return refreshToken.getToken();
	    }

	    public ResponseDTO refreshToken(RefreshRequestDTO refreshRequestDTO) {
	        var tokenOpt = refreshTokenRepository.findRefreshTokenByToken(refreshRequestDTO.getRefreshToken());
	        if (tokenOpt.isEmpty()) {
	            throw new RuntimeException("Refresh Token %s not found!".formatted(refreshRequestDTO.getRefreshToken()));
	        }
	        var token = tokenOpt.get();
	        if (isTokenExpired(token.getExpiration())) {
	            refreshTokenRepository.delete(token);
	            throw new RuntimeException("Refresh Token %s was expired!".formatted(refreshRequestDTO.getRefreshToken()));
	        }
	        String jwt = jwtUtils.createJwt(token.getUsuario().getEmail());
	        updateToken(token);
	        return ResponseDTO.of(jwt, token.getToken());
	    }

	    private void updateToken(RefreshToken token) {
	        token.setExpiration(ZonedDateTime.now(ZoneId.systemDefault()).plusMinutes(expirationTime));
	        refreshTokenRepository.save(token);
	    }

	    private boolean isTokenExpired(ZonedDateTime expirationTime) {
	        return expirationTime.isBefore(ZonedDateTime.now(ZoneId.systemDefault()));
	    }
}
