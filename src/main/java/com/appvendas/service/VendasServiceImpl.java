package com.appvendas.service;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.appvendas.dao.EmpreendimentoDaoInterface;
import com.appvendas.dao.VendasDaoInterface;
import com.appvendas.model.Empreendimento;
import com.appvendas.model.Vendas;
/*
 * Desenvolvedor: Matheus Mendes
 * 
 * suportetecnologia@outlook.com.br
*/
@Service
public class VendasServiceImpl implements VendasServiceInterface {

	@Autowired
	private VendasDaoInterface dao;

	@Autowired
	private DespesasMensaisServiceImpl serviceDespesa;
	
	@Autowired
	private EmpreendimentoDaoInterface daoEmpreendimento;

	@Autowired
	private UsuarioServiceImpl serviceDoAcesso;

	public static LocalDate CALENDARIO = LocalDate.now();
	public static LocalDate CALENDARIO_DA_VENDA = LocalDate.now();
	public static int MES_ATUAL = 1 + CALENDARIO.getMonthValue();
	public static int ANO_ATUAL = CALENDARIO.getYear();
	public static int DIA_DE_HOJE = CALENDARIO.getDayOfMonth();

	@Override
	public void salvar(Vendas vendas) {
		dao.save(vendas);
	}

	@Override
	public void editar(Long id) {
		dao.findById(id);

	}

	@Override
	public void excluir(Vendas vendas) {
		dao.delete(vendas);
	}

	@Override
	public List<Vendas> listarTodasAsVendas() {
		return (List<Vendas>) dao.findAll();
	}

	@Override
	public Optional<Vendas> buscarPorVenda(Long id) {
		return dao.findById(id);
	}

	@Override
	public List<Vendas> pesquisarPorDescricao(String descricao) {
		return dao.findByDescricaoContaining(descricao);
	}

	@Override
	public List<Vendas> pesquisarVendasPorDatas(LocalDate dataInicio, LocalDate dataFim) {
		Empreendimento empresa = new Empreendimento();
		empresa.setId(serviceDoAcesso.capturarIdDaEmpresaLogada());
		if (dataInicio == null || dataFim == null) {
			return listarVendasPorEmpresa(empresa);
		}

			return	dao.findByDataBetween(dataInicio, dataFim);

	}

	// MÉTODOS REFERENTES ÀS VENDAS

	//////////////////////////////////////////////////////////////////////////
	@Override
	public double valorTotalDasVendas() {

		double soma = 0.0;

		for (Vendas v : dao.findAll()) {
			soma += v.getValor();
		}
		return soma;
	}

	@Override
	public double retornarVendaDiaria() {

		double soma = 0.0;

		for (Vendas v : dao.findAll()) {

			CALENDARIO_DA_VENDA = v.getData();

			int diaDoAnoDaVenda = CALENDARIO_DA_VENDA.getDayOfYear();

			int diaDoAno = CALENDARIO.getDayOfYear();

			if (retornarValidacaoDaVendaDiaria(diaDoAnoDaVenda, diaDoAno, v.isPendente(),
					v.getIdDoEmpreendimento().getId())) {
				soma += v.getValor();
			}
		}
		return soma;
	}

	// neste método especifico a forma de validar para retornar somente a venda
	// diária da empresa logada
	public boolean retornarValidacaoDaVendaDiaria(int diaDoAnoDaVenda, int diaDoAno, boolean pendente, long id) {
		return diaDoAno == diaDoAnoDaVenda && !pendente && id == serviceDoAcesso.capturarIdDaEmpresaLogada();
	}

	@Override
	public double retornarVendaMensal() {

		double soma = 0.0;

		for (Vendas v : dao.findAll()) {
			CALENDARIO_DA_VENDA = v.getData();
			Month mesVenda = CALENDARIO_DA_VENDA.getMonth();
			Month mesAtual = CALENDARIO.getMonth();
			if (retornarValidacaoDaVendaMensal(mesVenda, mesAtual, v.isPendente(), v.getIdDoEmpreendimento().getId())) {
				soma += v.getValor();
			}
		}
		return soma;
	}

	// neste método especifico a forma de validar para retornar somente a venda do
	// mês da empresa logada
	public boolean retornarValidacaoDaVendaMensal(Month mesVenda, Month mesAtual, boolean pendente, long id) {
		return mesVenda == mesAtual && !pendente && id == serviceDoAcesso.capturarIdDaEmpresaLogada();
	}

	@Override
	public double retornarVendaAnual() {

		double soma = 0.0;

		for (Vendas v : dao.findAll()) {

			CALENDARIO_DA_VENDA = v.getData();

			if (retornarValidacaoDaVendaAnual(CALENDARIO_DA_VENDA.getYear(), CALENDARIO.getYear(), v.isPendente(),
					v.getIdDoEmpreendimento().getId())) {
				soma += v.getValor();
			}
		}
		return soma;
	}

	/*neste método especifico a forma de validar para retornar somente a venda do
		ano da empresa logada */
	public boolean retornarValidacaoDaVendaAnual(int anoDaVenda, int anoAtual, boolean pendente, long id) {
		return anoAtual == anoDaVenda && !pendente && id == serviceDoAcesso.capturarIdDaEmpresaLogada();
	}

	
	
	public Long retornarQuantidadeDeVendaDiaria() {
		Long count = 0L;

		for (Vendas v : dao.findAll()) {

			CALENDARIO_DA_VENDA = v.getData();

			int diaDoAnoDaVenda = CALENDARIO_DA_VENDA.getDayOfYear();

			int hoje = CALENDARIO.getDayOfYear();

			if (diaDoAnoDaVenda == hoje) {
				count++;
			}
		}
		return count;
	}

	public Long retornarQuantidadeDeVendaMensal() {

		Long count = 0L;

		for (Vendas v : dao.findAll()) {

			CALENDARIO_DA_VENDA = v.getData();

			int mesVenda = 1 + CALENDARIO_DA_VENDA.getMonthValue();
			int anoVenda = CALENDARIO_DA_VENDA.getYear();

			if (MES_ATUAL == mesVenda && anoVenda == ANO_ATUAL) {
				count++;
			}
		}
		return count;
	}

	public Long retornarQuantidadeDeVendaAnual() {

		Long count = 0L;

		for (Vendas v : dao.findAll()) {

			CALENDARIO_DA_VENDA = v.getData();

			int anoVenda = CALENDARIO_DA_VENDA.getYear();

			if (anoVenda == ANO_ATUAL) {
				count++;
			}
		}
		return count;
	}

	// MÉTODOS RELACIONADOS A VENDAS PENDENTES
	///////////////////////////////////////////////////////////////////

	@Override
	public List<Vendas> buscarVendasPendentes() {
		List<Vendas> vendasPendentes = new ArrayList<Vendas>();
		for (Vendas v : dao.findAll()) {
			if (retornarValidacaoDaListagemDeVendasPendentes(v.isPendente(), v.getIdDoEmpreendimento().getId())) {
				vendasPendentes.add(v);
			}
		}
		return vendasPendentes;
	}

	public boolean retornarValidacaoDaListagemDeVendasPendentes(boolean pendente, long id) {
		return pendente && id == serviceDoAcesso.capturarIdDaEmpresaLogada();
	}
	
	public boolean existeVendaPendente() {
		boolean x = false;
		int count = 0;
		for (Vendas v : dao.findAll()) {

			if (v.isPendente() == true) {
				count++;
			}
			if (count > 0) {
				x = true;
			} else {
				x = false;
			}
		}
		return x;
	}

	@Override
	public Long contarAQuantidadeDeVendasPendentes() {
		Long quantidadeDeVendasPendentes = 0L;
		for (Vendas v : dao.findAll()) {
			if (retornarValidacaoDaQuantidadeDeVendasPendentes(v.isPendente(), v.getIdDoEmpreendimento().getId())) {
				quantidadeDeVendasPendentes++;
			}
		}
		return quantidadeDeVendasPendentes;
	}

	
	public boolean retornarValidacaoDaQuantidadeDeVendasPendentes(boolean pendente, long id) {
		return pendente && id == serviceDoAcesso.capturarIdDaEmpresaLogada();
	}
	
	@Override
	public double somarAsVendasPendentes() {
		double somaDasVendasPendentes = 0.0;

		for (Vendas v : dao.findAll()) {
			if (retornarValidacaoDaSomaDasVendasPendentes(v.isPendente(), v.getIdDoEmpreendimento().getId())) {
				somaDasVendasPendentes += v.getValor();
			}
		}
		return somaDasVendasPendentes;
	}

	
	public boolean retornarValidacaoDaSomaDasVendasPendentes(boolean pendente, long id) {
		return pendente && id == serviceDoAcesso.capturarIdDaEmpresaLogada();
	}
	
	@Override
	public boolean isVendaPendente() {

		return false;
	}

	@Override
	public double retornarValorLiquidoDoMes() {
		double valorLiquido = 0.0;
		valorLiquido = retornarVendaMensal() - serviceDespesa.somaDasDespesasMensais();
		if (valorLiquido <= 0) {
			return 0.0;
		}
		return valorLiquido;
	}

	@Override
	public List<Vendas> procurarVendasPorCodigo(Long codigo) {
	
		Empreendimento emp = new Empreendimento();

		if (codigo == null) {		
			emp.setId(serviceDoAcesso.capturarIdDaEmpresaLogada());
			return listarVendasPorEmpresa(emp);
		}
		
		for(Vendas v : dao.findAll()) {
			if(v.getId() == codigo && v.getIdDoEmpreendimento().getId() == serviceDoAcesso.capturarIdDaEmpresaLogada()) {
				return dao.procurarVendasPorCodigo(codigo);
			}
		}
	
		return null;			
	}

	@Override
	public Page<Vendas> findAll(org.springframework.data.domain.Pageable pageable) {
		return dao.findAll(pageable);
	}

	@Override
	public List<Vendas> listarVendasPorEmpresa(Empreendimento empresa) {
		return dao.findByidDoEmpreendimento(empresa);
	}

	@Override
	public double retornarValorNecessarioParaAlcancarADespesa() {	
		double valor = serviceDespesa.somaDasDespesasMensais() - retornarVendaMensal();
		double resultado = (valor > 0) ? valor : 0.0;
		return resultado;
	}

}
