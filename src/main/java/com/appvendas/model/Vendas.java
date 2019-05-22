package com.appvendas.model;
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

	@Column(columnDefinition = "DECIMAL(7,2) DEFAULT 0.00", nullable = false)
	@NotNull(message = "Campo obrigatório! Favor preencher")
	private Double valor;
	
	@Column(nullable = false)
	@NotNull(message = "Campo obrigatório! Favor preencher")
	private String descricao;
	
	@Column(columnDefinition = "Date", name = "data")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date data;

	@Column
	@Enumerated(EnumType.STRING)
	private StatusDoPagamento statusPagamento;
	
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

	public StatusDoPagamento getStatus() {
		return statusPagamento;
	}

	public void setStatus(StatusDoPagamento status) {
		this.statusPagamento = status;
	}
	
	
	
}
