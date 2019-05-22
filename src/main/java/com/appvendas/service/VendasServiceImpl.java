package com.appvendas.service;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appvendas.dao.VendasDaoInterface;
import com.appvendas.model.Vendas;


@Service
public class VendasServiceImpl implements VendasServiceInterface{

	@Autowired
	private VendasDaoInterface dao;
	
	
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
	public List<Vendas> pesquisarPorDescricao(String descricao){
		return dao.findByDescricaoContaining(descricao);
	}
	

	@Override
	public double informarTotalVendasMensal() {
		
		return (Double) null;
		}
	


	@Override
	public double informarTotalVendasAnual(int ano) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double informarTotalVendasDiaria(int dia, int mes, int ano) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Vendas> pesquisarVendasPorData(Date dataInicial, Date dataFinal) {
		return dao.findByDataBetween(dataInicial, dataFinal);
	}

}
