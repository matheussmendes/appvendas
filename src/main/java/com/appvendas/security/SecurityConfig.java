package com.appvendas.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.appvendas.service.UsuarioServiceImpl;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UsuarioServiceImpl empreendimentoService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
			.antMatchers("/", "/cadastrar", "/salvarEmpreendimento", "/css/**", "/imagens/**", "/js/**", "/layout/**").permitAll()
					
		.anyRequest().authenticated()
		.and()
			.formLogin()
			.loginPage("/")
			.defaultSuccessUrl("/painel", true)
			.failureUrl("/login-incorreto")
			.permitAll()
		
		.and()
			.logout()
			.logoutSuccessUrl("/")
		;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(empreendimentoService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	

	
}
