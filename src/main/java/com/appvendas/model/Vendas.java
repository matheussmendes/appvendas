package com.appvendas.model;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;
/*
 * Desenvolvedor: Matheus Mendes
 * 
 * suportetecnologia@outlook.com.br
*/
@SuppressWarnings("serial")
@Entity
public class Vendas extends AbstractEntity<Long>{

	
	@Column(columnDefinition = "DECIMAL(8,2)")
	@NumberFormat(style = Style.CURRENCY, pattern = "#,#00.00")
	private double valor;
	
	@Column(nullable = false, length = 50)
	@NotBlank(message = "Campo 'descrição' não pode estar vazio! Favor preencher.")
	@Size(min = 5, max = 50, message = "O campo descrição deve ter entre {min} e {max} caracteres.")
	private String descricao;

	@Column(columnDefinition = "DATE")
	@DateTimeFormat(iso = ISO.DATE, pattern = "dd/MM/yyyy")
	@PastOrPresent(message = "A data não pode ser futura. Verifique-a por favor!")
	private LocalDate data;

	@Column
	private boolean pendente;

	@ManyToOne()
	@JoinColumn(name = "id_empresa", referencedColumnName = "id")
	private Empreendimento idDoEmpreendimento;
	

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public boolean isPendente() {
		return pendente;
	}

	public void setPendente(boolean pendente) {
		this.pendente = pendente;
	}

	public Empreendimento getIdDoEmpreendimento() {
		return idDoEmpreendimento;
	}

	public void setIdDoEmpreendimento(Empreendimento idDoEmpreendimento) {
		this.idDoEmpreendimento = idDoEmpreendimento;
	}
	
	
}
