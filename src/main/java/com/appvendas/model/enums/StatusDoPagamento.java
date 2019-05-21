package com.appvendas.model.enums;

public enum StatusDoPagamento {

	PENDENTE("Pendente"), PAGO("Pago"); 
	
	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	StatusDoPagamento(String descricao) {
		this.descricao = descricao;
	}
	
	
	
	
}
