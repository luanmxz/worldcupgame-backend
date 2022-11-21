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
        registry.addMapping("*")
	        .allowedMethods("*")
	        .allowedHeaders("*")
	        .allowedHeaders("Access-Control-Allow-Headers: Origin, Content-Type, Authorization")
	        .allowedHeaders("Access-Control-Allow-Origin: *");
        registry.addMapping("https://wcg22.netlify.app/**")
        	.allowedMethods("*")
        	.allowedHeaders("*")
        	.allowedHeaders("Access-Control-Allow-Headers: Origin, Content-Type, Authorization")
            .allowedHeaders("Access-Control-Allow-Origin: *");
        registry.addMapping("https://app-wcg22.herokuapp.com/oauth/token")
	        .allowedMethods("*")
	    	.allowedHeaders("*")
	    	.allowedHeaders("Access-Control-Allow-Headers: Origin, Content-Type, Authorization")
	        .allowedHeaders("Access-Control-Allow-Origin: *");
        registry.addMapping("https://wcg22.netlify.app/#/login")
	        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT")
	        .allowedHeaders("Access-Control-Allow-Methods: GET, POST, PATCH, PUT, DELETE, OPTIONS")
	        .allowedHeaders("Access-Control-Allow-Headers: Origin, Content-Type, Authorization")
	        .allowedHeaders("Access-Control-Allow-Origin: *");
        registry.addMapping("/oauth/token")
	        .allowedMethods("*")
	        .allowedHeaders("*")
	        .allowedHeaders("Access-Control-Allow-Headers: Origin, Content-Type, Authorization")
	        .allowedHeaders("Access-Control-Allow-Origin: *");
    }
	
}
