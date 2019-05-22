package com.appvendas.controller;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

	@RequestMapping(path = "/lista")
	public String listar(ModelMap model) {
		model.addAttribute("listaDevendas", service.listarTodasAsVendas());
		return "/vendas/lista";
	}

	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	public String salvar(@Valid Vendas vendas, RedirectAttributes ra, BindingResult result) {
		if(result.hasErrors()) {
			return "/vendas/cadastro";
		}else {
		service.salvar(vendas);
		ra.addFlashAttribute("mensagemDeSucesso", "Venda registrada com sucesso!!");
		return "redirect:/formulario";}
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
	public String excluir(@PathVariable("id")Vendas vendas, RedirectAttributes ra) {
		service.excluir(vendas);
		ra.addFlashAttribute("mensagemDeSucesso", "");
		return "redirect:/lista";
	}
	
	/*
	 * @ModelAttribute("somaDasVendasDoMes") public Double
	 * informarTotalDeVendasDoMes() { System.out.println("resultad" +
	 * service.informarTotalVendasMensal()); return
	 * service.informarTotalVendasMensal();
	 * 
	 * }
	 */
	
	
	
	
	
	/*
	 * @ModelAttribute("listaDestatus") public StatusDoPagamento[] listaDeStatus() {
	 * return StatusDoPagamento.values(); }
	 * 
	 * @RequestMapping("/pesquisarVendasPorDatas") public String
	 * pesquisarVendasPorData(@RequestParam("datas")ModelMap model, Date
	 * dataInicial, Date dataFinal) {
	 * model.addAttribute("vendasEncontradasPelasDatas",
	 * service.pesquisarVendasPorData(dataInicial, dataFinal)); return
	 * "/vendas/lista"; }
	 */
	
}
