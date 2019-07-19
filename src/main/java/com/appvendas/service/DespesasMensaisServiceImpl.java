package com.appvendas.service;
import java.util.List;
import java.util.Optional;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.appvendas.dao.DespesasMensaisDaoInterface;
import com.appvendas.model.DespesasMensais;
import com.appvendas.model.enums.CategoriaDaDespesa;



@Service
public class DespesasMensaisServiceImpl implements DespesasMensaisServiceInterface {

	@Autowired
	private DespesasMensaisDaoInterface dao;
	
	@Override
	public void salvar(DespesasMensais contasAPagar) {
		dao.save(contasAPagar);
	}

	@Override
	public void excluir(DespesasMensais contasAPagar) {
		dao.delete(contasAPagar);
	}

	@Override
	public List<DespesasMensais> listarTodasAsDespesasMensais() {
		return (List<DespesasMensais>) dao.findAll();
	}

	@Override
	public Optional<DespesasMensais> buscarContaAPagarPorCodigo(Long id) {
		return dao.findById(id);
	}

	@Override
	public double somaDasDespesasMensais() {	
		double soma = 0.0;
		for(DespesasMensais d : dao.findAll()) {
			if(d.isInativa() == false) {
				soma += d.getValor();
			}
		}		
		System.out.println("SOMA DAS DESPESAS" +soma);
		return soma;
	}

	@Override
	public Long quantidadeDeDespesasMensais() {
		return dao.count();
	}

	@Override
	public List<DespesasMensais> procurarDespesaMensalPorCategoria(CategoriaDaDespesa categoriaDaDespesa) {	
		if(categoriaDaDespesa == null) {
			return listarTodasAsDespesasMensais();
		}	
		return dao.findBycategoriaDaDespesaContaining( categoriaDaDespesa);
	}

	@Override
	public List<DespesasMensais> procurarDespesasMensaisPorDescricao(String descricao) {
		
		if(descricao.isEmpty()) {
			return listarTodasAsDespesasMensais();
		}
		
		return dao.findByDescricaoContaining(descricao);
	}
}
