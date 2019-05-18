package com.appvendas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.appvendas.model.Vendas;
import com.appvendas.service.VendasServiceImpl;

@Controller
public class VendasController {

	@Autowired
	private VendasServiceImpl service;

	@RequestMapping("/formulario")
	public String formulario(Vendas vendas) {
		return "/vendas/cadastro";
	}

	@RequestMapping("/lista")
	public String listar(ModelMap model) {
		model.addAttribute("vendas", service.listarTodasAsVendas());
		return "/vendas/lista";
	}

	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	public String salvar(Vendas vendas) {
		service.salvar(vendas);
		return "redirect:/formulario";
	}

	@GetMapping("/buscar")
	public String pesquisarPorDescricao(@RequestParam("descricao") String descricao, ModelMap model) {
		model.addAttribute("vendas", service.pesquisarPorDescricao(descricao));
		return "/vendas/lista";
	}


	@RequestMapping("/painel")
	public String exibirPainel(Vendas vendas) {
		return "/vendas/painel";
	}
}
