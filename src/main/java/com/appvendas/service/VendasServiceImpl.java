package com.appvendas.service;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Service;
import com.appvendas.dao.VendasDaoInterface;
import com.appvendas.model.Vendas;

@Service
public class VendasServiceImpl implements VendasServiceInterface {

	@Autowired
	private VendasDaoInterface dao;

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
		System.out.println("FORMA DA DATA RECEBIDA" + dataInicio + "" + dataFim);
		if (dataInicio == null || dataFim == null) {
			return listarTodasAsVendas();
		}
		return dao.findByDataBetween(dataInicio, dataFim);
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

			if (diaDoAno == diaDoAnoDaVenda && v.isPendente() == false) {
				soma += v.getValor();
			}
		}
		return soma;
	}

	@Override
	public double retornarVendaMensal() {

		double soma = 0.0;

		for (Vendas v : dao.findAll()) {
			CALENDARIO_DA_VENDA = v.getData();
			Month mesVenda = CALENDARIO_DA_VENDA.getMonth();
			Month mesAtual = CALENDARIO.getMonth();
			if (mesAtual == mesVenda && v.isPendente() == false) {
				soma += v.getValor();
			}
		}
		return soma;
	}

	@Override
	public double retornarVendaAnual() {

		double soma = 0.0;

		for (Vendas v : dao.findAll()) {

			CALENDARIO_DA_VENDA = v.getData();

			if (CALENDARIO_DA_VENDA.getYear() == ANO_ATUAL && v.isPendente() == false) {
				soma += v.getValor();
			}
		}
		return soma;
	}

	public Long retornarQuantidadeDeVendaDiaria() {
		Long count = 0L;

		for (Vendas v : dao.findAll()) {

			CALENDARIO_DA_VENDA = v.getData();
			System.out.println("COMPARAAÇÃO" + CALENDARIO_DA_VENDA.compareTo(CALENDARIO));
			int diaDoAnoDaVenda = CALENDARIO_DA_VENDA.getDayOfYear();
			System.out.println("DIA DA VENDA " + diaDoAnoDaVenda);
			int hoje = CALENDARIO.getDayOfYear();
			System.out.println("hoje " + hoje);
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
			if (v.isPendente()) {
				vendasPendentes.add(v);
			}
		}
		return vendasPendentes;
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
			if (v.isPendente() == true) {
				quantidadeDeVendasPendentes++;
			}
		}
		return quantidadeDeVendasPendentes;
	}

	@Override
	public double somarAsVendasPendentes() {
		double somaDasVendasPendentes = 0.0;

		for (Vendas v : dao.findAll()) {
			if (v.isPendente() == true) {
				somaDasVendasPendentes += v.getValor();
			}
		}
		return somaDasVendasPendentes;
	}

	@Override
	public boolean isVendaPendente() {

		return false;
	}

}
