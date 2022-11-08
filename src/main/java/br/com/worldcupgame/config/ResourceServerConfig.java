package br.com.worldcupgame.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{

	@Autowired
	private JwtTokenStore tokenStore;
	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenStore(tokenStore);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		
		http
			.headers().frameOptions().disable().and()
			.cors().and().csrf().disable()
			.authorizeRequests()
				//.antMatchers("/oauth/token", "/sending-email", "/api/jogos/**", "/selecoes", "/recovery-password/**").permitAll()
				//.antMatchers(HttpMethod.POST, "/api/usuarios").permitAll()
				//.antMatchers("/api/usuarios/**").permitAll() //hasAnyRole("USER", "ADMIN")
				//.antMatchers(HttpMethod.GET, "/api/selecoes/**").hasAnyRole("USER", "ADMIN")
				//.antMatchers("/api/selecoes/**").hasRole("ADMIN")
				.anyRequest().permitAll();
		
	}

	
	
}
