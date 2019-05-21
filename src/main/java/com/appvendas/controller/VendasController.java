package com.appvendas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.appvendas.model.Vendas;
import com.appvendas.model.enums.StatusDoPagamento;
import com.appvendas.service.VendasServiceImpl;

@Controller
public class VendasController {

	@Autowired
	private VendasServiceImpl service;

	@RequestMapping("/formulario")
	public String formulario(Vendas vendas) {
		return "/vendas/cadastro";
	}

	@RequestMapping(path = "/lista")
	public String listar(ModelMap model) {
		model.addAttribute("vendas", service.listarTodasAsVendas());
		return "/vendas/lista";
	}

	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	public String salvar(Vendas vendas, RedirectAttributes ra) {
		service.salvar(vendas);
		ra.addFlashAttribute("sucesso", "Venda registrada com sucesso!!");
		return "redirect:/formulario";
	}

	@RequestMapping("/buscar")
	public String pesquisarPorDescricao(@RequestParam("descricao") String descricao, ModelMap model) {
		model.addAttribute("vendas", service.pesquisarPorDescricao(descricao));
		return "/vendas/lista";
	}

	@RequestMapping("/painel")
	public String exibirPainel(Vendas vendas) {
		return "/vendas/painel";
	}

	@RequestMapping("/{id}")
	public String editar(@PathVariable("id") Vendas vendas, ModelMap model) {
		model.addAttribute(vendas);
		return "/vendas/cadastro";
	}
	
	@RequestMapping(path = "/excluir/{id}")
	public String excluir(@PathVariable("id") Vendas vendas) {
		service.excluir(vendas);
		return "redirect:/lista";
	}
	
	@ModelAttribute("listaDestatus")
	public StatusDoPagamento[] listaDeStatus() {
		return StatusDoPagamento.values();
	} 
	
}
