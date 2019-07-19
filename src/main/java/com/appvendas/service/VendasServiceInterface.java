package com.appvendas.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import com.appvendas.model.Vendas;

@Service
public interface VendasServiceInterface {

	void salvar(Vendas vendas);

	void editar(Long id);

	void excluir(Vendas vendas);

	List<Vendas> listarTodasAsVendas();

	 Page<Vendas> findAll(Pageable pageable);
	  
	Optional<Vendas> buscarPorVenda(Long id);

	// implementações específicas

	List<Vendas> pesquisarPorDescricao(String descricao);

	List<Vendas> buscarVendasPendentes();

	double valorTotalDasVendas();

	boolean isVendaPendente();
	
	double retornarVendaMensal();
	  
	double retornarVendaAnual();
	  
	double retornarVendaDiaria();
	 
	Long contarAQuantidadeDeVendasPendentes();
	
	double somarAsVendasPendentes();

	List<Vendas> pesquisarVendasPorDatas(LocalDate dataInicio, LocalDate dataFim);

	double retornarValorLiquidoDoMes();
	
	List<Vendas> procurarVendasPorCodigo(Long codigo);
}
