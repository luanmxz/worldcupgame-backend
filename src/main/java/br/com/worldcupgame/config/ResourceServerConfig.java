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
			.cors().and().csrf().disable()
			.authorizeRequests()
				//api/usuarios
				.antMatchers(HttpMethod.GET, "/api/usuarios/**").hasAnyRole("USER", "ADMIN")
				.antMatchers(HttpMethod.POST, "/api/usuarios/**").permitAll()
				.antMatchers(HttpMethod.PUT, "/api/usuarios/**").hasAnyRole("USER", "ADMIN")
				.antMatchers(HttpMethod.DELETE, "/api/usuarios/**").hasAnyRole("ADMIN")
				//api/selecoes
				.antMatchers(HttpMethod.GET, "/api/selecoes/**").permitAll()
				.antMatchers(HttpMethod.POST, "/api/selecoes/**").hasRole("ADMIN")
				.antMatchers(HttpMethod.PUT, "/api/selecoes/**").hasRole("ADMIN")
				.antMatchers(HttpMethod.DELETE, "/api/selecoes/**").hasRole("ADMIN")
				//api/apostas
				.antMatchers("/api/apostas/**").hasAnyRole("USER", "ADMIN")
				//api/sending-email
				.antMatchers("/api/sending-email/**").permitAll()
				.antMatchers("/oauth/token", "/sending-email", "/api/jogos/**", "/selecoes", "/recovery-password/**").permitAll()
				.antMatchers(HttpMethod.POST, "/api/usuarios").permitAll()
				//api/jogos
				.antMatchers(HttpMethod.GET, "/api/jogos/**").hasAnyRole("USER", "ADMIN")
				.antMatchers(HttpMethod.POST, "/api/jogos/**").hasRole("ADMIN")
				.antMatchers(HttpMethod.PUT, "/api/jogos/**").hasRole("ADMIN")
				.antMatchers(HttpMethod.DELETE, "/api/jogos/**").hasRole("ADMIN")
				//oauth/token
				.anyRequest().permitAll();
		
	}

	
	
}
