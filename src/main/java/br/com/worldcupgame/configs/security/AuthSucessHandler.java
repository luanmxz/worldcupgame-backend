package br.com.worldcupgame.configs.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.worldcupgame.dto.ResponseDTO;
import br.com.worldcupgame.services.UsuarioService;

@Component
public class AuthSucessHandler extends SimpleUrlAuthenticationSuccessHandler{

	private final ObjectMapper objectMapper = new ObjectMapper();
	
	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	RefreshTokenService refreshTokenService;
	
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        var usuario = usuarioService.getUsuarioByUsername(principal.getUsername());
        String token = jwtUtils.createJwt(usuario.getEmail());
        String refreshToken = refreshTokenService.createToken(usuario);
        response.addHeader("Authorization", "Bearer " + token);
        response.addHeader("Content-Type", "application/json");
        response.getWriter().write(objectMapper.writeValueAsString(ResponseDTO.of(token, refreshToken)));
    }
}
