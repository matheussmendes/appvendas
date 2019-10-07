package com.appvendas.model.enums;
/*
 * Desenvolvedor: Matheus Mendes
 * 
 * suportetecnologia@outlook.com.br
*/
public enum TipoDoEmpreendimento {

	MERCADO("Mercado"), FRUTARIA("Frutaria"), LANCHONETE("Lanchonete"), PADARIA("Padaria"), SORVETERIA("Sorveteria"),
	ACOUGUE("Acougue"), OUTROS("Outros");
	
	private String descricao;

	
	TipoDoEmpreendimento(String descricao) {
		this.descricao = descricao;
	}

	
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
