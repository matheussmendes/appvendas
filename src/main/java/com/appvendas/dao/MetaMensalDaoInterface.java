package com.appvendas.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.appvendas.model.MetaDeVendaMensal;
/*
 * Desenvolvedor: Matheus Mendes
 * 
 * suportetecnologia@outlook.com.br
*/
@Repository
public interface MetaMensalDaoInterface extends CrudRepository<MetaDeVendaMensal, Long>{

	@Query("select e.meta from Empreendimento e where e.id = ?1")
	Long retornarIdDaMetaMensal(Long idDaEmpresa);
}
