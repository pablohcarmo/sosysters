package br.com.sosysters.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	public void addCorsMapping(CorsRegistry registry) {
		registry.addMapping("/**")

		.allowedOriginPatterns("http://localhost:3000")
		.allowedOriginPatterns("http://localhost:8080")
		.allowedOriginPatterns("http://127.0.0.1:5500")
		.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT");
	}
}
