package com.appvendas.service;
import java.util.List;

import org.springframework.stereotype.Service;
import com.appvendas.model.Acesso;
/*
 * Desenvolvedor: Matheus Mendes
 * 
 * suportetecnologia@outlook.com.br
*/
@Service
public interface UsuarioServiceInterface {

	
	Acesso encontrarAcessoPorEmail(String email);
	
	Long capturarIdDaEmpresaLogada();
	
	String capturarNomeDaEmpresaLogada(Long id);
	
	String capturarEmailDaEmpresaLogada(Long id);
	
}
