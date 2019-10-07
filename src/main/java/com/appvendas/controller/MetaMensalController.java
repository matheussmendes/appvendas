package com.appvendas.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.appvendas.model.MetaDeVendaMensal;
import com.appvendas.service.MetaMensalServiceImpl;
import com.appvendas.service.UsuarioServiceImpl;
/*
 * Desenvolvedor: Matheus Mendes
 * 
 * suportetecnologia@outlook.com.br
*/
@RequestMapping
@Controller
public class MetaMensalController {

	@Autowired
	private UsuarioServiceImpl serviceDoAcesso;

	@Autowired
	private MetaMensalServiceImpl serviceDaMeta;
	
	@RequestMapping("/meta")
	public String metaMensal() {

		return "/metaMensal/meta-mensal";
	}

	@ModelAttribute("idDaMeta")
	public Long listaDeMetas(){
		System.out.println("ID DA META É LLLLLLLL:::::" +serviceDaMeta.retornarIdDaMetaMensal(serviceDoAcesso.capturarIdDaEmpresaLogada()));
		return serviceDaMeta.retornarIdDaMetaMensal(serviceDoAcesso.capturarIdDaEmpresaLogada());
	}
	
	
	@ModelAttribute("nomeEmpresaLogada")
	public String exibeNomeDaEmpresaLogada() {
			return serviceDoAcesso.capturarNomeDaEmpresaLogada(serviceDoAcesso.capturarIdDaEmpresaLogada());
	}
	
	@RequestMapping("/salvarMeta")
	public String salvar(@Valid MetaDeVendaMensal meta, BindingResult result, RedirectAttributes ra) {

		if (result.hasErrors()) {
			return "/metaMensal/cadastro";
		}
		
		serviceDaMeta.salvar(meta);
		ra.addFlashAttribute("mensagemDeSucesso", "Meta atualizada com sucesso!!!");
		return "redirect:/meta";
	}
	
	
	@ModelAttribute("valorDaMeta")
	public double retornarValorDaMeta() {
		return serviceDaMeta.retornarValorDaMetaPorEmpreendimento(serviceDoAcesso.capturarIdDaEmpresaLogada());
	}
	
	@RequestMapping("/editarMeta/{id}")
	public String editar(@PathVariable("id") MetaDeVendaMensal meta, ModelMap model) {
		model.addAttribute(meta);
		return "/metaMensal/cadastro";
	}
	
}
