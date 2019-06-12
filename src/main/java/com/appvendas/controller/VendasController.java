package com.appvendas.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.TimeZone;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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

		LocalDate agora = LocalDate.now();

		if (vendas.getId() == null && !result.hasErrors()) {
			vendas.setData(agora);
		} else if (result.hasErrors()) {
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
	public String pesquisarVendasPorDatas(
			@RequestParam("dataInicio") @DateTimeFormat(iso = ISO.DATE, pattern = "dd/MM/yyyy") LocalDate dataInicio,
			@RequestParam("dataFim") @DateTimeFormat(iso = ISO.DATE, pattern = "dd/MM/yyyy") LocalDate dataFim, ModelMap model) {
		model.addAttribute("vendas", service.pesquisarVendasPorDatas(dataInicio, dataFim));

		return "/vendas/lista";
	}

	@ModelAttribute("quantidadeDeVendasDiarias")
	public Long retornarQuantidadeDeVendasDiarias() {
		return service.retornarQuantidadeDeVendaDiaria();
	}

	@ModelAttribute("quantidadeDeVendasMensal")
	public Long retornarQuantidadeDeVendasMensal() {
		return service.retornarQuantidadeDeVendaAnual();
	}

	@ModelAttribute("quantidadeDeVendasAnual")
	public Long retornarQuantidadeDeVendasAnual() {
		return service.retornarQuantidadeDeVendaAnual();
	}

	@RequestMapping("/vendasPendentes")
	public String listarVendasPendente(ModelMap model) {
		model.addAttribute("vendas", service.buscarVendasPendentes());
		return "vendas/vendasPendentes";
	}

	@ModelAttribute("existeVendaPendente")
	public boolean informaSeExisteVendaPendente() {
		return service.existeVendaPendente();
	}

	@ModelAttribute("quantidadeDeVendasPendentes")
	public Long contarQuantidadeDeVendasPendentes() {
		return service.contarAQuantidadeDeVendasPendentes();
	}

	@ModelAttribute("somaDasVendasPendentes")
	public double informarASomaDasVendasPendentes() {
		return service.somarAsVendasPendentes();
	}
}
