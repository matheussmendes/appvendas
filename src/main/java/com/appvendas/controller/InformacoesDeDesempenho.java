package com.appvendas.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
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
public class InformacoesDeDesempenho {

	@Autowired
	private MetaMensalServiceImpl serviceDaMeta;
	
	@Autowired
	private VendasServiceImpl serviceDaVenda;
	
	@Autowired
	private DespesasMensaisServiceImpl serviceDaDespesa;
	
	@Autowired
	private UsuarioServiceImpl serviceDoAcesso;
	
	@RequestMapping("/desempenho")
	public String informacoesDeDesempenho() {
		return "/desempenho/desempenho";
	}
	
	
	@ModelAttribute("valorDaMeta")
	public double retornarValorDaMeta() {
		System.out.println("A META É :::: " +serviceDaMeta.retornarValorDaMetaPorEmpreendimento(serviceDoAcesso.capturarIdDaEmpresaLogada()));
		return serviceDaMeta.retornarValorDaMetaPorEmpreendimento(serviceDoAcesso.capturarIdDaEmpresaLogada());
	}
	
	
	@ModelAttribute("valorParaAlcancarDespesa")
	public double retornarValorNecessarioParaAlcancarDespesa() {
		return serviceDaVenda.retornarValorNecessarioParaAlcancarADespesa();
	}
	
	
	@ModelAttribute("valorLiquidoDoMes")
	public double retornarValorLiquidoDoMes() {
		return serviceDaVenda.retornarValorLiquidoDoMes();
	}
	
	@ModelAttribute("somaDasDespesasMensais")
	public double retornarSomaDasDespesasMensais() {
		return serviceDaDespesa.somaDasDespesasMensais();
	}
	
	@ModelAttribute("nomeEmpresaLogada") 
	 public  String exibeNomeDaEmpresaLogada(){
		 return serviceDoAcesso.capturarNomeDaEmpresaLogada(serviceDoAcesso.capturarIdDaEmpresaLogada()); 
	 }
	

}
