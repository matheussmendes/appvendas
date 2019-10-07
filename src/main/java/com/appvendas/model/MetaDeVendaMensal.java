package com.appvendas.model;
import javax.persistence.Column;
import javax.persistence.Entity;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;
/*
 * Desenvolvedor: Matheus Mendes
 * 
 * suportetecnologia@outlook.com.br
*/
@SuppressWarnings("serial")
@Entity( name = "meta_mensal")
public class MetaDeVendaMensal extends AbstractEntity<Long>{

	@Column(columnDefinition = "DECIMAL(7,2)")
	@NumberFormat(style = Style.CURRENCY, pattern = "#,#00.00")
	private double valorDaMeta;


	public double getValorDaMeta() {
		return valorDaMeta;
	}

	public void setValorDaMeta(double valorDaMeta) {
		this.valorDaMeta = valorDaMeta;
	}
	
	public double retornarMetaMensal() {
		return valorDaMeta;
	}


	
}
