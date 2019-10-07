package com.appvendas.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.appvendas.model.DespesasMensais;
import com.appvendas.model.Empreendimento;
import com.appvendas.model.enums.CategoriaDaDespesa;
import com.appvendas.service.DespesasMensaisServiceImpl;
import com.appvendas.service.UsuarioServiceImpl;
/*
 * Desenvolvedor: Matheus Mendes
 * 
 * suportetecnologia@outlook.com.br
*/
@Controller
public class DespesasMensaisController {

	@Autowired
	private DespesasMensaisServiceImpl service;

	@Autowired
	private UsuarioServiceImpl serviceAcesso;
	
	
	@RequestMapping("/despesa")
	public String cadastrarDespesa(DespesasMensais despesasMensais) {
		return "/despesas/despesa-mensal";
	}

	@ModelAttribute("categoriasDeDespesas")
	public CategoriaDaDespesa[] preencherComboBoxCategoriaDeDespesa() {
		return CategoriaDaDespesa.values();
	}

	@RequestMapping("/salvarDespesa")
	public String salvar(@Valid DespesasMensais despesa, BindingResult result, RedirectAttributes ra) {

		if (result.hasErrors()) {
			return "/despesas/despesa-mensal";
		}
		
		vincularEmpresaLogadaComRegistroDeDespesa(despesa);
		
		service.salvar(despesa);
		ra.addFlashAttribute("mensagemDeSucesso", "Despesa registrada com sucesso!!!");
		return "redirect:/despesa";
	}

	@RequestMapping("/despesasMensais")
	public String listarDespesasMensais(ModelMap model) {
		Empreendimento empresa = new Empreendimento();
		
		empresa.setId(serviceAcesso.capturarIdDaEmpresaLogada());
		
		model.addAttribute("despesas", service.listarDespesaPorEmpreendimento(empresa));
		return "/despesas/despesas";
	}

	@ModelAttribute("somaDasDespesasMensais")
	public double retornarSomaDasDespesasMensais() {
		return service.somaDasDespesasMensais();
	}

	@RequestMapping("/editarDespesa{id}")
	public String editar(@PathVariable("id") DespesasMensais despesa, ModelMap model) {
		model.addAttribute(despesa);
		return "/despesas/despesa-mensal";
	}

	@RequestMapping("/excluirDespesa/{id}")
	public String excluir(@PathVariable("id") DespesasMensais despesasMensais, ModelMap model) {
		service.excluir(despesasMensais);
		return "redirect:/despesasMensais";
	}

	@ModelAttribute("quantidadeDeDespesasMensais")
	public Long exibirQuantidadeDeDespesasMensais() {
		Empreendimento empresa = new Empreendimento();	
		empresa.setId(serviceAcesso.capturarIdDaEmpresaLogada());
		return service.retornarQuantidadeDeDespesaPorEmpreendimento(empresa);
	}

	@RequestMapping("/selecionarParaModal{id}")
	public String selecionarParaModal(@PathVariable("id") DespesasMensais despesasMensais, ModelMap model) {
		model.addAttribute(despesasMensais);
		return "";
	}

	@RequestMapping("/procurarDespesaPorCategoria")
	public String buscarDespesaMensalPorCategoria(@RequestParam("categoria")CategoriaDaDespesa categoriaDaDespesa, ModelMap model){
		if(categoriaDaDespesa == null) {
			System.out.println("RECEBI NULO");
		}
		
		model.addAttribute("despesas", service.procurarDespesaMensalPorCategoria(categoriaDaDespesa));
		return "/despesas/despesas";
	}
	
	@RequestMapping("/procurarDespesaPorDescricao")
	public String buscarDespesaMensalPorDescricao(@RequestParam("descricao") String descricao, ModelMap model ) {
		model.addAttribute("despesas", service.procurarDespesasMensaisPorDescricao(descricao));
		return "/despesas/despesas";
	}
	
	public void vincularEmpresaLogadaComRegistroDeDespesa(DespesasMensais despesa) {
		/*instanciei um empreendimento, passo o id do empreendimento logado como o id desse novo objeto empreendimento, 
		posteriormente passo esse empreendimento para a despesa*/
		
		Empreendimento empresa = new Empreendimento();
		empresa.setId(serviceAcesso.capturarIdDaEmpresaLogada());
		despesa.setIdEmpreendimento(empresa);
	}
	

	@ModelAttribute("nomeEmpresaLogada") 
	 public  String exibeNomeDaEmpresaLogada(){
		 return serviceAcesso.capturarNomeDaEmpresaLogada(serviceAcesso.capturarIdDaEmpresaLogada()); 
	 }

}
