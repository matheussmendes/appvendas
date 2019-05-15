package com.appvendas.model.enums;

public enum CategoriaDoComercio {

	MERCADO("Mercado"),
	FRUTARIA("Frutaria"),
	LANCHONETE("Lanchonete"), 
	PADARIA("Padaria"),
	SORVETERIA("Sorveteria"),
	ACOUGUE("Acougue"),
	OUTROS("Outros");
	
	
	
	CategoriaDoComercio(String descricao) {
		this.descricao = descricao;
	}

	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
