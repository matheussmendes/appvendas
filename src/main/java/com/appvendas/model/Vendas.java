package com.appvendas.model;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;




@SuppressWarnings("serial")
@Entity
public class Vendas extends AbstractEntity<Long>{

	@Column(columnDefinition = "DECIMAL(7,2) DEFAULT 0.00", nullable = false)
	private Double valor;
	
	@Column(nullable = false)
	private String descricao;
	
	@Column(columnDefinition = "Date", name = "data")
	private Date data;

	
	
	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	
}
