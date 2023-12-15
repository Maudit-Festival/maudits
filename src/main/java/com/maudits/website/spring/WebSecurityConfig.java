package com.maudits.website.spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	@Value("${password}")
	private String password;

	@Bean
	InMemoryUserDetailsManager inMemoryUserDetailsManager() {
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		return new InMemoryUserDetailsManager(
				User.builder().username("maudit").password(encoder.encode(password)).roles("ADMIN").build());
	}

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// @formatter:off
		http.authorizeHttpRequests(
			authorize -> authorize
				.requestMatchers("/bo/**").authenticated()
				.anyRequest().permitAll())
			.formLogin(formLogin -> formLogin.permitAll())
			.logout(logout -> logout.logoutSuccessUrl("/"));
		// @formatter:on
		return http.build();
	}

}
