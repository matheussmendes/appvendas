package com.appvendas.dao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.appvendas.model.Acesso;
/*
 * Desenvolvedor: Matheus Mendes
 * 
 * suportetecnologia@outlook.com.br
*/
@Repository
public interface UsuarioDaoInterface  extends CrudRepository<Acesso,Long>{

	Acesso findByEmailAndAtivoTrue(String email);
	
	@Query("select a.id from Acesso a where a.email = ?1")
	long capturarIdDaEmpresaLogada(String email);
	
	@Query("select e.nomeFantasia from Empreendimento e where e.id = ?1") 
	String capturarNomeDaEmpresaLogada(Long id);
	 	
	@Query("select a.email from Acesso a where a.id = ?1")
	String capturarEmailDaEmpresaLogada(Long id);
	
	
}
