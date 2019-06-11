package com.appvendas.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Service;
import com.appvendas.dao.VendasDaoInterface;
import com.appvendas.model.Vendas;
import com.appvendas.model.enums.StatusDoPagamento;

@Service
public class VendasServiceImpl implements VendasServiceInterface {

	@Autowired
	private VendasDaoInterface dao;

	public static Calendar CALENDARIO = Calendar.getInstance();
	public static Calendar CALENDARIO_DA_VENDA = Calendar.getInstance();

	public static int MES_ATUAL = 1 + CALENDARIO.get(Calendar.MONTH);
	public static int ANO_ATUAL = CALENDARIO.get(Calendar.YEAR);
	public static int DIA_DE_HOJE = CALENDARIO.get(Calendar.DAY_OF_MONTH);

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

	
	
	  @Override public double valorTotalDasVendas() {
	  
	  double soma = 0.0;
	  
	  for (Vendas v : dao.findAll()) { soma += v.getValor(); } return soma; }
	  
	  
	  @Override public double retornarVendaMensal() {
	  
	  double soma = 0.0;
	  
	  for (Vendas v : dao.findAll()) {
	  
	  CALENDARIO_DA_VENDA.setTime(v.getData());
	  
	  int mesVenda = 1 + CALENDARIO_DA_VENDA.get(Calendar.MONTH); int anoVenda =
	  CALENDARIO_DA_VENDA.get(Calendar.YEAR);
	  
	  if (MES_ATUAL == mesVenda && anoVenda == ANO_ATUAL) { soma += v.getValor(); }
	  } return soma; }
	  
	  
	  
	  @Override public double retornarVendaAnual() {
	  
	  double soma = 0.0;
	  
	  for (Vendas v : dao.findAll()) {
	  
	  CALENDARIO_DA_VENDA.setTime(v.getData());
	  
	  int anoVenda = CALENDARIO_DA_VENDA.get(Calendar.YEAR);
	  
	  if (anoVenda == ANO_ATUAL) { soma += v.getValor(); } }
	  
	  return soma; }
	  
	  
	  
	  @Override public double retornarVendaDiaria() {
	  
	  Calendar hoje = Calendar.getInstance(); 
	  double soma = 0.0;
	  
	  for (Vendas v : dao.findAll()) { CALENDARIO_DA_VENDA.setTime(v.getData());
	  
	  if (hoje.get(Calendar.DAY_OF_MONTH) ==
	  CALENDARIO_DA_VENDA.get(Calendar.DAY_OF_MONTH) && hoje.get(Calendar.YEAR) ==
	  CALENDARIO_DA_VENDA.get(Calendar.YEAR)) { soma += v.getValor(); } }
	  System.out.println(" a venda de hoje é: :::" +
	  CALENDARIO_DA_VENDA.get(Calendar.DAY_OF_MONTH));
	  System.out.println("já a venda hoje do timestamp é: " +
	  hoje.get(Calendar.DAY_OF_MONTH));
	  
	  return soma; }
	 
	 

	@Override
	public List<Vendas> pesquisarVendasPorDatas(Date dataInicio, Date dataFim) {

		if (dataInicio == null || dataFim == null) {
			return listarTodasAsVendas();
		}
		return dao.findByDataBetween(dataInicio, dataFim);
	}

	public Long retornarQuantidadeDeVendaDiaria() {
		Long count = 0L;
		Calendar hoje = Calendar.getInstance();

		for (Vendas v : dao.findAll()) {
			CALENDARIO_DA_VENDA.setTime(v.getData());
			if (hoje.get(Calendar.DAY_OF_MONTH) == CALENDARIO_DA_VENDA.get(Calendar.DAY_OF_MONTH)
					&& hoje.get(Calendar.YEAR) == CALENDARIO_DA_VENDA.get(Calendar.YEAR)) {
				count++;
			}
		}
		return count;
	}

	public Long retornarQuantidadeDeVendaMensal() {

		Long count = 0L;

		for (Vendas v : dao.findAll()) {

			CALENDARIO_DA_VENDA.setTime(v.getData());

			int mesVenda = 1 + CALENDARIO_DA_VENDA.get(Calendar.MONTH);
			int anoVenda = CALENDARIO_DA_VENDA.get(Calendar.YEAR);

			if (MES_ATUAL == mesVenda && anoVenda == ANO_ATUAL) {
				count++;
			}
		}
		return count;
	}

	public Long retornarQuantidadeDeVendaAnual() {

		Long count = 0L;

		for (Vendas v : dao.findAll()) {

			CALENDARIO_DA_VENDA.setTime(v.getData());

			int anoVenda = CALENDARIO_DA_VENDA.get(Calendar.YEAR);

			if (anoVenda == ANO_ATUAL) {
				count++;
			}
		}
		return count;
	}



	@Override
	public List<Vendas> buscarVendasPendentes() {
		List<Vendas> vendasPendentes = new ArrayList<Vendas>();
		for(Vendas v : dao.findAll()) {
			if(v.isPendente()) {
				vendasPendentes.add(v);
			}
		}
		return vendasPendentes;
	}


	public boolean existeVendaPendente() {
		boolean x = false;
		int count = 0;
		for(Vendas v : dao.findAll()) {
			if(v.isPendente() == true) {
				count ++;
			}
			if(count > 0) {
				x = true;
			}
			else {
				x = false;
			}
		}
		return x;
	}
	
	@Override
	public Long contarAQuantidadeDeVendasPendentes() {
		Long quantidadeDeVendasPendentes = 0L;
		for(Vendas v : dao.findAll()) {
			if(v.isPendente() == true) {
				quantidadeDeVendasPendentes ++;
			}
		}
		return quantidadeDeVendasPendentes;
	}

	@Override
	public double somarAsVendasPendentes() {
		double somaDasVendasPendentes = 0.0;
	
		for(Vendas v : dao.findAll()) {
			if(v.isPendente() == true) {
				somaDasVendasPendentes += v.getValor();
			}
		}
		return somaDasVendasPendentes;
	}


}
