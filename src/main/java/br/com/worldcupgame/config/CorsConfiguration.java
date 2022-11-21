package br.com.worldcupgame.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedMethods("*")
            .allowedHeaders("*")
            .allowedHeaders("Access-Control-Allow-Headers: Origin, Content-Type, Authorization")
            .allowedHeaders("Access-Control-Allow-Origin: *");
        registry.addMapping("https://app-wcg22.herokuapp.com/")
	        .allowedMethods("PUT, DELETE, POST, GET, OPTIONS")
	        .allowedHeaders("*")
	        .allowedHeaders("Access-Control-Allow-Headers: Origin, Content-Type, Authorization")
	        .allowedHeaders("Access-Control-Allow-Origin: https://wcg22.netlify.app/");
        registry.addMapping("https://app-wcg22.herokuapp.com/oauth/token")
	        .allowedMethods("*")
	    	.allowedHeaders("*")
	    	.allowedHeaders("Access-Control-Allow-Headers: Origin, Content-Type, Authorization")
	        .allowedHeaders("Access-Control-Allow-Origin: *")
        	.allowCredentials(true);
        registry.addMapping("/oauth/token")
	        .allowedMethods("*")
	        .allowedHeaders("*")
	        .allowedHeaders("Access-Control-Allow-Headers: Origin, Content-Type, Authorization")
	        .allowedHeaders("Access-Control-Allow-Origin: *");
    }
	
}
