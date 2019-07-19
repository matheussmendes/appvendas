package com.appvendas.service;

import java.util.HashSet;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import com.appvendas.dao.UsuarioDaoInterface;
import com.appvendas.model.Acesso;

@Service
@SessionScope
public class UsuarioServiceImpl implements UsuarioServiceInterface, UserDetailsService {

	@Autowired
	private UsuarioDaoInterface dao;


	@Override
	public Acesso encontrarAcessoPorEmail(String email) {
	
		return dao.findByEmailAndAtivoTrue(email);
	}

	@Override 
	 public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	  
	  Acesso empreendimento = encontrarAcessoPorEmail(username);
	  System.out.println("O ID É????????" +empreendimento.getId());
	  return new User(empreendimento.getEmail(), empreendimento.getSenha(), new HashSet<>()); 
	  
	}

	@Override
	public long capturarIdDaEmpresaLogada() {
		return dao.capturarIdDaEmpresaLogada(SecurityContextHolder.getContext().getAuthentication().getName());
	}

	


}
