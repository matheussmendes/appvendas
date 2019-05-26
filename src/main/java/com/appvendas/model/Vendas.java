package com.appvendas.model;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.appvendas.model.enums.StatusDoPagamento;

@SuppressWarnings("serial")
@Entity
public class Vendas extends AbstractEntity<Long>{

	@Column
	private double valor;
	
	@Column(nullable = false)
	@NotNull(message = "Campo obrigatório! Favor preencher")
	private String descricao;
	
	@Column(columnDefinition = "Date", name = "data")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date data;

	@Enumerated(EnumType.STRING)
	private StatusDoPagamento statusPagamento;
	


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

	public StatusDoPagamento getStatusPagamento() {
		return statusPagamento;
	}

	public void setStatusPagamento(StatusDoPagamento statusPagamento) {
		this.statusPagamento = statusPagamento;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}


}
