package br.com.sosysters.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws  Exception {
		return http.authorizeHttpRequests(req -> {
					req.requestMatchers(
						"/login",
						"/logout",
						"/error",
						"/css/**",
						"/js/**",
						"/images/**",
						"/pages/**",
						"/assets/**",
						"/favicon.ico",
						"/",
						"/home",
						"/index").permitAll();
					req.anyRequest().authenticated();
				})

				.formLogin(form ->
						form.loginPage("/login")
								.defaultSuccessUrl("/feed")
								.permitAll())
				.logout(logout ->
						logout.logoutSuccessUrl("/login?logout")
								.permitAll())

				.rememberMe(rememberMe -> rememberMe.key("lembrarDeMim")
						.alwaysRemember(true))
				.csrf(Customizer.withDefaults())
				.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}