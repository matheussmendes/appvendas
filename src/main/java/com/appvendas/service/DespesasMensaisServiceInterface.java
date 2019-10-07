package com.appvendas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.appvendas.model.DespesasMensais;
import com.appvendas.model.Empreendimento;
import com.appvendas.model.enums.CategoriaDaDespesa;
/*
 * Desenvolvedor: Matheus Mendes
 * 
 * suportetecnologia@outlook.com.br
*/
@Service
public interface DespesasMensaisServiceInterface {

	void salvar(DespesasMensais contasAPagar);
	
	void excluir(DespesasMensais contasAPagar);
	
	List<DespesasMensais> listarTodasAsDespesasMensais();
	
	Optional<DespesasMensais> buscarContaAPagarPorCodigo(Long id);
	
	double somaDasDespesasMensais();
	
	Long quantidadeDeDespesasMensais();
	
	List<DespesasMensais> procurarDespesaMensalPorCategoria(CategoriaDaDespesa categoria);
	
	List<DespesasMensais> procurarDespesasMensaisPorDescricao(String descricao);
	
	List<DespesasMensais> listarDespesaPorEmpreendimento(Empreendimento empresa);
	
	Long retornarQuantidadeDeDespesaPorEmpreendimento(Empreendimento empresa);
}
