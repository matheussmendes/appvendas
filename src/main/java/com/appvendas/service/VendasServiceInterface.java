package com.appvendas.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.appvendas.model.Vendas;

@Service
public interface VendasServiceInterface {

	void salvar(Vendas vendas);
	
	void editar(Long id);
	
	void excluir(Vendas vendas);
	
	List<Vendas> listarTodasAsVendas();
	
	Optional<Vendas> buscarPorVenda(Long id);
	
	
	//implementações específicas
	
	List<Vendas> pesquisarPorDescricao(String descricao);
	
	double valorTotalDasVendas();
	
	double retornarVendaMensal();
	
	double retornarVendaAnual();
	
	double retornarVendaDiaria();
	
	List<Vendas> pesquisarVendasPorDatas(Date dataInicio, Date dataFim);
}
