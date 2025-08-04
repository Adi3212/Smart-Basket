package com.cdac.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.AllArgsConstructor;

@Configuration // to declare config class - to declare spring beans - @Bean)
@EnableWebSecurity // to customize spring security
@EnableMethodSecurity // to enable method level annotations
//(@PreAuthorize , @PostAuthorize..) to specify  authorization rules
@AllArgsConstructor
public class SecurityConfiguration {
	// depcy - password encoder
	private final CustomJwtFilter customJwtFilter;
	private final JwtAuthEntryPoint jwtAuthEntryPoint;
	
	/*
	 * configure spring bean to customize spring security filter chain disable CSRF
	 * protection - session creation policy - stateless - disable form login based
	 * authentication - enable basic authentication scheme , for REST clients
	 */
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable());

		http.authorizeHttpRequests(request -> request
			.requestMatchers("/swagger-ui/**", "/v*/api-docs/**",
			                 "/users/signin", "/users/signup", "/error", "/grocery/expiry-soon").permitAll()
			.requestMatchers(HttpMethod.OPTIONS).permitAll()

			// 👤 USER CONTROLLER
			.requestMatchers(HttpMethod.GET, "/user/{id}").hasAnyAuthority("USER", "ADMIN")
			.requestMatchers(HttpMethod.PUT, "/user/{id}").hasAnyAuthority("USER", "ADMIN")
			.requestMatchers(HttpMethod.DELETE, "/user/{id}").hasAuthority("ADMIN")
			.requestMatchers(HttpMethod.GET, "/user").hasAuthority("ADMIN")
			.requestMatchers(HttpMethod.POST, "/user").hasAuthority("ADMIN")

			// 🧺 GROCERY CONTROLLER
			.requestMatchers(HttpMethod.GET, "/grocery/{id}").hasAnyAuthority("USER", "ADMIN")
			.requestMatchers(HttpMethod.PUT, "/grocery/{id}").hasAnyAuthority("USER", "ADMIN")
			.requestMatchers(HttpMethod.DELETE, "/grocery/{id}").hasAnyAuthority("USER", "ADMIN")
			.requestMatchers(HttpMethod.POST, "/grocery/grocery").hasAuthority("USER")
			.requestMatchers(HttpMethod.GET, "/grocery").hasAuthority("USER")
			.requestMatchers(HttpMethod.GET, "/grocery/user/{userId}").hasAuthority("USER")


			.anyRequest().authenticated()
		);

		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		http.exceptionHandling(ex -> ex.authenticationEntryPoint(jwtAuthEntryPoint));
		http.addFilterBefore(customJwtFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}


	// configure a spring to return Spring security authentication manager
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration mgr) throws Exception {
		return mgr.getAuthenticationManager();
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	   
}
