package com.appvendas.service;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.appvendas.dao.VendasDaoInterface;
import com.appvendas.model.Vendas;


@Service
public class VendasServiceImpl implements VendasServiceInterface{

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
	public List<Vendas> pesquisarPorDescricao(String descricao){
		return dao.findByDescricaoContaining(descricao);
	}


	@Override
	public double valorTotalDasVendas() {
		
		double soma = 0.0;
		
		for(Vendas v : dao.findAll()) {
			soma += v.getValor();
		}
		return soma;
	}

	@Override
	public double retornarVendaMensal() {

		double soma = 0.0;

		for(Vendas v : dao.findAll()) {

			CALENDARIO_DA_VENDA.setTime(v.getData());

			int mesVenda =  1 + CALENDARIO_DA_VENDA.get(Calendar.MONTH);	
			int anoVenda = CALENDARIO_DA_VENDA.get(Calendar.YEAR);

			if(MES_ATUAL == mesVenda && anoVenda == ANO_ATUAL) {
				soma += v.getValor();
			}
		}
			return soma;
	}

	@Override
	public double retornarVendaAnual() {

		double soma = 0.0;

		for(Vendas v : dao.findAll()) {

			CALENDARIO_DA_VENDA.setTime(v.getData());

			int anoVenda = CALENDARIO_DA_VENDA.get(Calendar.YEAR);

			if(anoVenda == ANO_ATUAL) {
				soma += v.getValor();
			}
		}

		return soma;
	}

	@Override
	public double retornarVendaDiaria() {

		double soma = 0.0;

		for(Vendas v : dao.findAll()) {

			CALENDARIO_DA_VENDA.setTime(v.getData());

			int diaVenda = CALENDARIO_DA_VENDA.get(Calendar.DAY_OF_MONTH);
			int mesVenda = 1 + CALENDARIO_DA_VENDA.get(Calendar.MONTH);
			int anoVenda = CALENDARIO_DA_VENDA.get(Calendar.YEAR);

			if(diaVenda == DIA_DE_HOJE && mesVenda == MES_ATUAL && anoVenda == ANO_ATUAL) {
				soma += v.getValor();
			}
		}
		return soma;
	}
	@Override
	public List<Vendas> pesquisarVendasPorDatas(Date dataInicio, Date dataFim) {
		
		if(dataInicio == null || dataFim == null) {
			return listarTodasAsVendas();
		}
		return dao.findByDataBetween(dataInicio, dataFim);
	}
	
	
	public Long retornarQuantidadeDeVendaDiaria() {
		Long count = 0L;

		for(Vendas v : dao.findAll()) {

			CALENDARIO_DA_VENDA.setTime(v.getData());

			int diaVenda = CALENDARIO_DA_VENDA.get(Calendar.DAY_OF_MONTH);
			int mesVenda = 1 + CALENDARIO_DA_VENDA.get(Calendar.MONTH);
			int anoVenda = CALENDARIO_DA_VENDA.get(Calendar.YEAR);

			if(diaVenda == DIA_DE_HOJE && mesVenda == MES_ATUAL && anoVenda == ANO_ATUAL) {
				count ++;
			}
		}
		System.out.println("quantidade de vendas diarias " + count);
		return count;
	}
	
	
	public Long retornarQuantidadeDeVendaMensal() {

		Long count = 0L;

		for(Vendas v : dao.findAll()) {

			CALENDARIO_DA_VENDA.setTime(v.getData());

			int mesVenda =  1 + CALENDARIO_DA_VENDA.get(Calendar.MONTH);	
			int anoVenda = CALENDARIO_DA_VENDA.get(Calendar.YEAR);

			if(MES_ATUAL == mesVenda && anoVenda == ANO_ATUAL) {
				count ++;
			}
		}
			return count;
	}
	
	public Long retornarQuantidadeDeVendaAnual() {

		Long count = 0L;

		for(Vendas v : dao.findAll()) {

			CALENDARIO_DA_VENDA.setTime(v.getData());

			int anoVenda = CALENDARIO_DA_VENDA.get(Calendar.YEAR);

			if(anoVenda == ANO_ATUAL) {
				count ++;
			}
		}
		return count;
	}
	
}
