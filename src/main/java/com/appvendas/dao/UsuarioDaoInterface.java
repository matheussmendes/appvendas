package com.appvendas.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.appvendas.model.Acesso;

@Repository
public interface UsuarioDaoInterface  extends CrudRepository<Acesso,Long>{

	Acesso findByEmailAndAtivoTrue(String email);
	
	@Query("select a.id from Acesso a where a.email = ?1")
	long capturarIdDaEmpresaLogada(String email);
}
