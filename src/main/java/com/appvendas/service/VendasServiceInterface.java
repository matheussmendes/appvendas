package com.appvendas.service;

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
	
	List<Vendas> pesquisarPorDescricao(String descricao);
	
	//implementações específicas
	
	double informarTotalVendasMensal(int mes, int ano);
	
	double informarTotalVendasAnual(int ano);
	
	double informarTotalVendasDiaria(int dia, int mes, int ano);
}