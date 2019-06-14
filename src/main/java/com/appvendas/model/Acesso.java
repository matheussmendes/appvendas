package com.appvendas.model;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

@SuppressWarnings("serial")
public class Acesso extends AbstractEntity<Long>{

	@Column
	@NotBlank(message = "Campo E-mail não pode ser nulo")
	private String email;
	
	@Column
	@NotBlank(message = "Campo Senha não pode ser nulo")
	private String senha;
	
	@Column
	private boolean ativo;

	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	

}
