package br.com.sosysters.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
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
		return http
				.csrf(csrf -> csrf.ignoringRequestMatchers(new AntPathRequestMatcher("/usuarias")))
				.authorizeHttpRequests(req -> {
					req.requestMatchers(
						"/login",
							"/usuarias",
						"/logout",
						"/error",
						"/css/**",
						"/js/**",
						"/scripts/**",
						"/images/**",
						"/pages/**",
						"/assets/**",
						"/favicon.ico",
						"/",
						"/home",
						"/index",
						"/cadastro",
						"/api/etnias",
						"/api/generos",
						"/verify-email/**").permitAll();
					req.anyRequest().authenticated();
				})

				.formLogin(form ->
						form.loginPage("/login")
								.defaultSuccessUrl("/feed")
								.permitAll())
				.logout(logout ->
						logout.logoutSuccessUrl("/login?logout")
								.permitAll())

				.rememberMe(rememberMe -> rememberMe.key("lembrarDeMim"))
				.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}