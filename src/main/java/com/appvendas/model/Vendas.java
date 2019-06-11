package com.appvendas.model;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import com.appvendas.model.enums.StatusDoPagamento;

@SuppressWarnings("serial")
@Entity
public class Vendas extends AbstractEntity<Long>{

	@Column()
	@NumberFormat(style = Style.CURRENCY)
	private double valor;
	
	@Column(nullable = false)
	@NotBlank(message = "Campo 'descrição' não pode estar vazio! Favor preencher")
	private String descricao;

	@Column(columnDefinition = "DATE")
	@DateTimeFormat(iso = ISO.DATE, pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date data;

	@Column
	private boolean pendente;

	@Column(name = "pagamento")
	@Enumerated(EnumType.STRING)
	private StatusDoPagamento statusDoPagamento;
	

	
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

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}


	public StatusDoPagamento getStatusDoPagamento() {
		return statusDoPagamento;
	}

	public void setStatusDoPagamento(StatusDoPagamento statusDoPagamento) {
		this.statusDoPagamento = statusDoPagamento;
	}

	public boolean isPendente() {
		return pendente;
	}

	public void setPendente(boolean pendente) {
		this.pendente = pendente;
	}	
	
	
}
