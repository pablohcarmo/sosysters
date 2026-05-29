package br.com.sosysters.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

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
	public AuthenticationFailureHandler authenticationFailureHandler() {
		return new AuthenticationFailureHandler() {
			@Override
			public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
				String username = request.getParameter("username");
				if (exception instanceof DisabledException) {
					String redirectUrl = "/login?disabled=true";
					if (username != null && !username.isBlank()) {
						redirectUrl += "&email=" + URLEncoder.encode(username, StandardCharsets.UTF_8.toString());
					}
					response.sendRedirect(redirectUrl);
				} else {
					response.sendRedirect("/login?error");
				}
			}
		};
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
						"/resend-confirmation",
						"/ajuda",
						"/api/etnias",
						"/api/generos",
						"/verify-email/**").permitAll();
					req.anyRequest().authenticated();
				})

				.formLogin(form ->
						form.loginPage("/login")
								.failureHandler(authenticationFailureHandler())
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