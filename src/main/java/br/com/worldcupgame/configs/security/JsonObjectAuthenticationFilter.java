package br.com.worldcupgame.configs.security;

import java.io.BufferedReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonObjectAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private final ObjectMapper objectMapper = new ObjectMapper();
	
	@Override
	//estudar o que esse metodo faz!!!!!!
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
		try {
			BufferedReader reader = request.getReader();
			StringBuilder stringBuilder = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				stringBuilder.append(line);
			}
			LoginCredentials authRequest = objectMapper.readValue(stringBuilder.toString(), LoginCredentials.class);
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
					authRequest.getEmail(), authRequest.getPassword()
			);
			setDetails(request, token);
			return this.getAuthenticationManager().authenticate(token);
		} catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
}
