package com.appvendas.service;
import org.springframework.stereotype.Service;
import com.appvendas.model.Acesso;

@Service
public interface UsuarioServiceInterface {

	
	Acesso encontrarAcessoPorEmail(String email);
	
	long capturarIdDaEmpresaLogada();
}
