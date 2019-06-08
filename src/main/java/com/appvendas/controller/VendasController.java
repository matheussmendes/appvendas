package com.appvendas.controller;

import java.util.Date;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
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

	@RequestMapping("/acesso")
	public String exibirPaginaDeAutenticacao() {
		return "/acesso/login";
	}
	
	@RequestMapping(path = "/lista")
	public String listar(ModelMap model) {
		model.addAttribute("vendas", service.listarTodasAsVendas());
		return "/vendas/lista";
	}

	
	
	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	public String salvar(@Valid Vendas vendas, BindingResult result, RedirectAttributes ra) {
		Date data = new Date();		
		  if (vendas.getId() == null && !result.hasErrors()) { 
			  vendas.setData(data); 
			  }	 
		  else if(result.hasErrors()) {
			return "/vendas/cadastro";
		}
		service.salvar(vendas);
		ra.addFlashAttribute("mensagemDeSucesso", "Venda registrada com sucesso!!");
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

	
	
	@RequestMapping("/deletar/{id}")
	public String excluir(@PathVariable("id") Vendas vendas, RedirectAttributes ra) {
		service.excluir(vendas);
		ra.addFlashAttribute("mensagemDeSucesso", "Venda excluída com sucesso!!!");
		return "redirect:/lista";
	}
	
	

	@ModelAttribute("somaDasVendasDiaria")
	public Double retornarTotalDeVendasDiaria() {
		return service.retornarVendaDiaria();
	}

	
	
	@ModelAttribute("somaDasVendasDoMes")
	public Double retornarTotalDeVendasDoMes() {
		return service.retornarVendaMensal();
	}

	
	
	@ModelAttribute("somaDasVendasDoAno")
	public Double retornarTotalDeVendasAnual() {
		return service.retornarVendaAnual();
	}

	
	
	@RequestMapping("/buscarPorDatas")
	public String pesquisarVendasPorDatas(@RequestParam("dataInicio") @DateTimeFormat(iso = ISO.DATE) Date dataInicio,
			@RequestParam("dataFim") @DateTimeFormat(iso = ISO.DATE) Date dataFim, ModelMap model) {
		model.addAttribute("vendas", service.pesquisarVendasPorDatas(dataInicio, dataFim));
		return "/vendas/lista";
	}

	
	
	@ModelAttribute("quantidadeDeVendasDiarias")
	public Long retornarQuantidadeDeVendasDiarias() {
		return service.retornarQuantidadeDeVendaDiaria();
	}
	
	
	
	@ModelAttribute("quantidadeDeVendasAnual")
	public Long retornarQuantidadeDeVendasAnual() {
		return service.retornarQuantidadeDeVendaAnual();
	}
	
	
	
	@ModelAttribute("quantidadeDeVendasMensal")
	public Long retornarQuantidadeDeVendasMensal() {
		return service.retornarQuantidadeDeVendaAnual();
	}
	
	
	
	@ModelAttribute("statusDosPagamentos")
	public StatusDoPagamento[] getStatusDoPagamento() {
		return StatusDoPagamento.values();
	}
	
	@ModelAttribute("vendaAReceber")
	public boolean retornarVendaAReceber() {
		System.out.println("resultado venda pendente ou não" +service.isVendaPendente());
		return service.isVendaPendente();
	}
}
