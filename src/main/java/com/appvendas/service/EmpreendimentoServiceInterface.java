package com.appvendas.service;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.appvendas.model.Empreendimento;


@Service
public interface EmpreendimentoServiceInterface {

	void salvar(Empreendimento empreendimento);
	
	void excluir(Empreendimento empreendimento);
	
	List<Empreendimento> listarTodosOsEmpreendimentos();
	
	Optional<Empreendimento> buscarPorEmpreendimento(Long id);
}
