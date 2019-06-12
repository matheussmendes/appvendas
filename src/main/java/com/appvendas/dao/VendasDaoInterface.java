package com.appvendas.dao;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.appvendas.model.Vendas;

@Repository
public interface VendasDaoInterface extends CrudRepository<Vendas, Long>{

	List<Vendas> findByDescricaoContaining(String descricao);
	
	List<Vendas> findByDataBetween(LocalDate dataInicial, LocalDate dataFinal);
	
	List<Vendas> findByPendenteTrue();
	
	;
}
