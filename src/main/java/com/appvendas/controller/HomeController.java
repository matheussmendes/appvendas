package com.appvendas.controller;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
/*
 * Desenvolvedor: Matheus Mendes
 * 
 * suportetecnologia@outlook.com.br
*/
@Controller
public class HomeController {

	
	@RequestMapping("/")
	public String home() {
		return "/layout/login";
	}

	@RequestMapping("/logout")
	public String logout(HttpSession sessao, RedirectAttributes ra) {
		ra.addFlashAttribute("mensagemLogout", "Até, volte depois.");
		return "redirect:/";
	}

	@RequestMapping("/login-incorreto")
	public String loginIncorreto(ModelMap model) {
		model.addAttribute("alerta",
		"E-mail e/ou senha inválido(s). Tente novamente ou contate  o administrador do "
		+ "sistema para verificar se seu empreendimento está ativo na plataforma nirvana");
		return "/layout/login";
	}

	@RequestMapping("/home")
	public String paginaInicial() {
		return "/acesso/home";
	}
	
	@RequestMapping("/sobre")
	public String sobreNos() {
		return "acesso/sobre-nos";
	}
	
	
	@RequestMapping("/contato")
	public String contato() {
		return "acesso/contato";
	}

	
}
