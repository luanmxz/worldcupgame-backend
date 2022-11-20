package br.com.worldcupgame.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import br.com.worldcupgame.model.Usuario;
import br.com.worldcupgame.repository.UsuarioRepository;

@Component
public class JwtTokenEnhancer implements TokenEnhancer{
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		Usuario usuario = usuarioRepository.findByEmail(authentication.getName());

        Map<String, Object> map = new HashMap<>();
        map.put("email", usuario.getEmail());
        map.put("username", usuario.getUsername());
        map.put("id", usuario.getId());
        map.put("pontos", usuario.getPontos());
        map.put("admin", usuario.isAdmin());

        DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken)accessToken;
        token.setAdditionalInformation(map);

        return accessToken;
	}
	
}
