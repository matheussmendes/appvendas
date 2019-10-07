package com.appvendas.dao;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.appvendas.model.Empreendimento;
import com.appvendas.model.Vendas;
/*
 * Desenvolvedor: Matheus Mendes
 * 
 * suportetecnologia@outlook.com.br
*/
@Repository
public interface VendasDaoInterface extends CrudRepository<Vendas, Long>{

	List<Vendas> findByDescricaoContaining(String descricao);

	List<Vendas> findByDataBetween(LocalDate dataInicial, LocalDate dataFinal);
	
	List<Vendas> findByPendenteTrue();
	
	@Query("select v from Vendas v where v.id = ?1")
	List<Vendas> procurarVendasPorCodigo(Long codigo);
	
	Page<Vendas> findAll(Pageable pageable);
	
	List<Vendas> findByidDoEmpreendimento(Empreendimento empresa);
	

	
	}
