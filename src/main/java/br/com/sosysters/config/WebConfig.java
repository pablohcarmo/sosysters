package br.com.sosysters.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
		.allowedOriginPatterns("*")
		.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
		.allowedHeaders("*")
		.allowCredentials(false);
	}

    // Usuário de testes
    @Bean
    UserDetailsService testUsers() {
        UserDetails user1 = User.builder()
                .username("admin")
                .password("teste123")
                .build();
        return new InMemoryUserDetailsManager(user1);
    }

	public SecurityFilterChain securityFilters(HttpSecurity http) throws  Exception {
		return http.authorizeHttpRequests(req -> {
					req.requestMatchers("/css/**", "/js/**", "/assets/**").permitAll();
					req.anyRequest().authenticated();
				})

				.formLogin(form ->
						form.loginPage("/login")
								.defaultSuccessUrl("/")
								.permitAll())
				.logout(logout ->
						logout.logoutSuccessUrl("/login?logout")
								.permitAll())
				.build();
	}
}