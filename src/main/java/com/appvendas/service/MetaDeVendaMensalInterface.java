package com.appvendas.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.appvendas.model.MetaDeVendaMensal;
/*
 * Desenvolvedor: Matheus Mendes
 * 
 * suportetecnologia@outlook.com.br
*/
@Service
public interface MetaDeVendaMensalInterface {

	double retornarValorDaMetaPorEmpreendimento(long id); 
	
	void salvar(MetaDeVendaMensal meta);
	
	List<MetaDeVendaMensal> listaDeMetas();
	
	Long retornarIdDaMetaMensal(Long idDaEmpresa);
}
