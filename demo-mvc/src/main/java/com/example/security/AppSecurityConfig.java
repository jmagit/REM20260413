package com.example.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class AppSecurityConfig {
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

//	@Bean
//	public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder)
//			throws Exception {
//		return http.getSharedObject(AuthenticationManagerBuilder.class)
//				.userDetailsService(new UserDetailsServiceImpl())
//				.passwordEncoder(bCryptPasswordEncoder)
//				.and().build();
//	}

	@Bean
	public HttpFirewall getHttpFirewall() {
		return new DefaultHttpFirewall();
	}

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http.cors(Customizer.withDefaults()).csrf((csrf) -> csrf.disable())
				.authorizeHttpRequests(requests -> requests
//						.requestMatchers("/*").permitAll()
						.requestMatchers("/ciudades/**").authenticated()
//						.requestMatchers("/actores/**").hasRole("MANAGER")
//						.requestMatchers("/ajax/**").hasRole("ADMIN")
						.anyRequest().permitAll())
				.formLogin(login -> login.loginPage("/mylogin").permitAll())
				.logout(logout -> logout.logoutSuccessUrl("/").permitAll())
				.build();
	}
}