package com.appvendas.dao;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.appvendas.model.DespesasMensais;
import com.appvendas.model.Empreendimento;
import com.appvendas.model.enums.CategoriaDaDespesa;
/*
 * Desenvolvedor: Matheus Mendes
 * 
 * suportetecnologia@outlook.com.br
*/
@Repository
public interface DespesasMensaisDaoInterface extends CrudRepository<DespesasMensais, Long>{

	List<DespesasMensais> findBycategoriaDaDespesaContaining(CategoriaDaDespesa categoria);
	
	List<DespesasMensais> findByDescricaoContaining (String descricao);
	
	List<DespesasMensais> findByidEmpreendimento(Empreendimento empresa);
	
	Long countByIdEmpreendimento(Empreendimento empresa);
	
}
