package com.appvendas.model.enums;

public enum CategoriaDaDespesa {

	ENERGIA("Energia"), ALUGUEL("Aluguel"), AGUA("Agua"), TELEFONE("Telefone"), INTERNET("Internet"),
	FORNECEDOR("Fornecedor"), OUTROS("Outros");
	
	
	private String descricao;

	
	CategoriaDaDespesa(String descricao) {
		this.descricao = descricao;
	}
	
	
	
	public String getDescricao() {
		return descricao;
	}

}
