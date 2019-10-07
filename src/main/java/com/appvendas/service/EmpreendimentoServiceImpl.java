package com.appvendas.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.appvendas.dao.EmpreendimentoDaoInterface;
import com.appvendas.model.Empreendimento;
import com.appvendas.model.MetaDeVendaMensal;

/*
 * Desenvolvedor: Matheus Mendes
 * 
 * suportetecnologia@outlook.com.br
*/
@Service
public class EmpreendimentoServiceImpl implements EmpreendimentoServiceInterface {

	@Autowired
	private EmpreendimentoDaoInterface dao;
	
	@Autowired
	private UsuarioServiceImpl serviceDoUsuario;
	
	
	
	@Override
	public void salvar(Empreendimento empreendimento) {
		BCryptPasswordEncoder criptografarSenha = new BCryptPasswordEncoder();
		empreendimento.getAcesso().setSenha( criptografarSenha.encode(empreendimento.getAcesso().getSenha()));
		dao.save(empreendimento);
	}

	@Override
	public void excluir(Empreendimento empreendimento) {
		dao.delete(empreendimento);
	}

	@Override
	public List<Empreendimento> listarTodosOsEmpreendimentos() {
		return (List<Empreendimento>) dao.findAll();
	}

	@Override
	public Optional<Empreendimento> buscarPorEmpreendimento(Long id) {
		return dao.findById(id);
	}


	


	

}
