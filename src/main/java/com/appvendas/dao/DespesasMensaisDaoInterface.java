package com.appvendas.dao;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.appvendas.model.DespesasMensais;
import com.appvendas.model.enums.CategoriaDaDespesa;

@Repository
public interface DespesasMensaisDaoInterface extends CrudRepository<DespesasMensais, Long>{

	List<DespesasMensais> findBycategoriaDaDespesaContaining(CategoriaDaDespesa categoria);
	
	List<DespesasMensais> findByDescricaoContaining (String descricao);
	

	
}
