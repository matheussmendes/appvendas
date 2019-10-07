package com.appvendas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appvendas.dao.EmpreendimentoDaoInterface;
import com.appvendas.dao.MetaMensalDaoInterface;
import com.appvendas.model.Empreendimento;
import com.appvendas.model.MetaDeVendaMensal;
/*
 * Desenvolvedor: Matheus Mendes
 * 
 * suportetecnologia@outlook.com.br
*/
@Service
public class MetaMensalServiceImpl implements MetaDeVendaMensalInterface {


	@Autowired
	private EmpreendimentoDaoInterface daoDoEmpreendimento;
	
	@Autowired
	private UsuarioServiceImpl serviceDaEmpresa;
	
	@Autowired
	private MetaMensalDaoInterface daoDaMeta;

	@Override
	public double retornarValorDaMetaPorEmpreendimento(long id) {
		
		Optional<Empreendimento> empresa = daoDoEmpreendimento.findById(id);
		
		return empresa.get().getMeta().getValorDaMeta();
	}

	@Override
	public void salvar(MetaDeVendaMensal meta) {
		
		daoDaMeta.save(meta);
	}

	@Override
	public List<MetaDeVendaMensal> listaDeMetas() {
		return (List<MetaDeVendaMensal>) daoDaMeta.findAll();
	}

	@Override
	public Long retornarIdDaMetaMensal(Long idDaEmpresa) {
		return daoDaMeta.retornarIdDaMetaMensal(idDaEmpresa);
	}

}
