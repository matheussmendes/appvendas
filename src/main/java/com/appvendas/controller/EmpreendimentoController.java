package com.appvendas.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.appvendas.model.Empreendimento;
import com.appvendas.model.enums.TipoDoEmpreendimento;
import com.appvendas.service.EmpreendimentoServiceImpl;
import com.appvendas.service.UsuarioServiceImpl;
/*
 * Desenvolvedor: Matheus Mendes
 * 
 * suportetecnologia@outlook.com.br
*/

@Controller
public class EmpreendimentoController {

	@Autowired
	private EmpreendimentoServiceImpl service;
	
	@Autowired
	private UsuarioServiceImpl serviceDoUsuario;
	
	@RequestMapping("/cadastrar")
	public String cadastrarEmpreendimento(Empreendimento empreendimento) {
		return "/acesso/home";
	}
	
	@ModelAttribute("segmentosDeAtuacao")
	public TipoDoEmpreendimento[] preencherComboBoxTipoEmpreendimento() {
		return TipoDoEmpreendimento.values();
	}

	@RequestMapping(value = "/salvarEmpreendimento", method = RequestMethod.POST)
	public String salvar(@Valid Empreendimento empreendimento, BindingResult result , RedirectAttributes ra) {
		
		if(result.hasErrors()) {
			return "/acesso/home";
		}
		service.salvar(empreendimento);
		ra.addFlashAttribute("mensagemDeSucesso", "Cadastro realizado com sucesso! Faça seu login");
		return "redirect:/";
	}
	
	@RequestMapping("/acessar")
	public String exibirPaginaDeAutenticacao() {
		return "/acesso/logar";
	}
	

	public String retornarEmailDaEmpresaLogada() {
		return serviceDoUsuario.capturarEmailDaEmpresaLogada(serviceDoUsuario.capturarIdDaEmpresaLogada());
	}
}
