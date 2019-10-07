package com.appvendas.controller;

import java.time.LocalDate;

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

import com.appvendas.model.Empreendimento;
import com.appvendas.model.Vendas;
import com.appvendas.service.DespesasMensaisServiceImpl;
import com.appvendas.service.MetaMensalServiceImpl;
import com.appvendas.service.UsuarioServiceImpl;
import com.appvendas.service.VendasServiceImpl;
/*
 * Desenvolvedor: Matheus Mendes
 * 
 * suportetecnologia@outlook.com.br
*/
@Controller
public class VendasController {

	@Autowired
	private VendasServiceImpl service;

	@Autowired
	private EmailController controllerDoEmail;
	
	@Autowired
	private DespesasMensaisServiceImpl serviceDeDespesa;

	@Autowired
	private UsuarioServiceImpl serviceDoAcesso;

	@Autowired
	private MetaMensalServiceImpl serviceDaMeta;

	@RequestMapping("/formulario")
	public String formulario(Vendas vendas) {
		return "/vendas/cadastro-produto";
	}

	@RequestMapping(path = "/lista")
	public String listar(ModelMap model/* , @PageableDefault(size = 2) Pageable pageable */) {

		Empreendimento empresa = new Empreendimento();

		empresa.setId(serviceDoAcesso.capturarIdDaEmpresaLogada());

		model.addAttribute("vendas", service.listarVendasPorEmpresa(empresa));

		return "/vendas/vendas-servicos";
	}

	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	public String salvar(@Valid Vendas vendas, BindingResult result, RedirectAttributes ra) {
		LocalDate agora = LocalDate.now();
		vincularEmpresaLogadaComRegistroDeReceita(vendas);

		if (ehCadastroNovo(vendas, result, ra)) {
			vendas.setData(agora);
			service.salvar(vendas);
			ra.addFlashAttribute("mensagemDeSucesso", "Receita registrada com sucesso!!");
		} else if (ehEdicao(vendas, result, ra)) {
			service.salvar(vendas);
			ra.addFlashAttribute("mensagemDeSucesso", "Receita atualizada com sucesso!!");
		} else {
			return "/vendas/cadastro-produto";
		}

		return "redirect:/formulario";
	}

	public boolean ehEdicao(Vendas vendas, BindingResult result, RedirectAttributes ra) {
		return vendas.getId() != null && !result.hasErrors() == true;
	}

	public boolean ehCadastroNovo(Vendas vendas, BindingResult result, RedirectAttributes ra) {
		return vendas.getId() == null && !result.hasErrors() == true;
	}

	@RequestMapping("/buscar")
	public String pesquisarPorDescricao(@RequestParam("descricao") String descricao, ModelMap model) {
		model.addAttribute("vendas", service.pesquisarPorDescricao(descricao));
		return "/vendas/lista";
	}

	@RequestMapping("/painel")
	public String exibirPainel(Vendas vendas) {

		/*
		 * Por meio da classe 'SecurityContextHolder' capturo o e-mail da empresa
		 * logada. Após isso consigo capturar, por meio de um método próprio, o id da
		 * empresa do respectivo e-mail.
		 */;

		return "/vendas/dashboard";
	}

	@RequestMapping("/{id}")
	public String editar(@PathVariable("id") Vendas vendas, ModelMap model) {
		model.addAttribute(vendas);
		return "/vendas/cadastro-produto";
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
			@RequestParam("dataFim") @DateTimeFormat(iso = ISO.DATE, pattern = "dd/MM/yyyy") LocalDate dataFim,
			ModelMap model) {
		model.addAttribute("vendas", service.pesquisarVendasPorDatas(dataInicio, dataFim));

		return "/vendas/vendas-servicos";
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
		return "vendas/pendentes";
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

	@ModelAttribute("valorLiquidoDoMes")
	public double retornarValorLiquidoDoMes() {
		/*Neste código posso implementar a verificação de caso o valor líquido mensal seja maior que 0, 
		 * significa que já comecei a ter rentabilidade, podendo inserir o método de envio de e-mail.
		 * */
		double valorLiquidoDoMes = service.retornarValorLiquidoDoMes();
	
		if(valorLiquidoDoMes > 0 && controllerDoEmail.emailEnviado == false) {
			//controllerDoEmail.enviarEmail();
		}
		return service.retornarValorLiquidoDoMes();
	}

	@RequestMapping("/procurarVendaPorCodigo")
	public String procurarVendaPorCodigo(@RequestParam("codigo") Long codigo, ModelMap model) {
		model.addAttribute("vendas", service.procurarVendasPorCodigo(codigo));
		return "/vendas/vendas-servicos";
	}

	public void vincularEmpresaLogadaComRegistroDeReceita(Vendas vendas) {
		/*
		 * instanciei um empreendimento, passo o id do empreendimento logado como o id
		 * desse novo objeto empreendimento, posteriormente passo esse empreendimento
		 * para a venda
		 */
		Empreendimento empresa = new Empreendimento();
		empresa.setId(serviceDoAcesso.capturarIdDaEmpresaLogada());
		vendas.setIdDoEmpreendimento(empresa);
	}

	@ModelAttribute("nomeEmpresaLogada")
	public String exibeNomeDaEmpresaLogada() {
		return serviceDoAcesso.capturarNomeDaEmpresaLogada(serviceDoAcesso.capturarIdDaEmpresaLogada());
	}

	@ModelAttribute("valorParaAlcancarDespesa")
	public double retornarValorNecessarioParaAlcancarDespesa() {
		return service.retornarValorNecessarioParaAlcancarADespesa();
	}

	@ModelAttribute("somaDasDespesasMensais")
	public double retornarSomaDasDespesasMensais() {
		return serviceDeDespesa.somaDasDespesasMensais();
	}

	@ModelAttribute("valorDaMeta")
	public double retornarValorDaMeta() {
		return serviceDaMeta.retornarValorDaMetaPorEmpreendimento(serviceDoAcesso.capturarIdDaEmpresaLogada());
	}

}
