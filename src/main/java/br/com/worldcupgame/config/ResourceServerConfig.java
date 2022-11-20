package br.com.worldcupgame.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{

	@Autowired
	private JwtTokenStore tokenStore;
	
	@Autowired
	private Environment env;

	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenStore(tokenStore);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		
		 if (Arrays.asList(env.getActiveProfiles()).contains("test")) {
		      http.headers().frameOptions().disable();
		    }
		
		http
			.authorizeRequests()
				//api/usuarios
				.antMatchers(HttpMethod.GET, "/api/usuarios/**").permitAll()
				.antMatchers(HttpMethod.POST, "/api/usuarios/**").permitAll()
				.antMatchers(HttpMethod.PUT, "/api/usuarios/new-password").permitAll()
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
				//api/logs
				.antMatchers(HttpMethod.POST, "/api/logs/**").hasRole("ADMIN")
				.antMatchers("/api/apostas/**").permitAll()
				.anyRequest().permitAll();
	}
	
	  @Bean
	  public CorsConfigurationSource corsConfigurationSource() {
	    CorsConfiguration corsConfig = new CorsConfiguration();
	    corsConfig.setAllowedOriginPatterns(Arrays.asList("*"));
	    corsConfig.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "PATCH"));
	    corsConfig.setAllowCredentials(true);
	    corsConfig.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
	
	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", corsConfig);
	    return source;
	  }
	
	  @Bean
	  public FilterRegistrationBean<CorsFilter> corsFilter() {
	    FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(corsConfigurationSource()));
	    bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
	    return bean;
	  }

}