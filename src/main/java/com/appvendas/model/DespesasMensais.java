package com.appvendas.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import com.appvendas.model.enums.CategoriaDaDespesa;
/*
 * Desenvolvedor: Matheus Mendes
 * 
 * suportetecnologia@outlook.com.br
*/


@SuppressWarnings("serial")
@Entity
public class DespesasMensais extends AbstractEntity<Long> {

	@Column
	@NotBlank(message = "Campo descrição é obrigatório. Favor preencher!")
	@Size(min = 5, max = 50, message = "Campo descrição deve ter entre {min} e {max}")
	private String descricao;
	
	@Column(columnDefinition = "DECIMAL(7,2)")
	@NumberFormat(style = Style.CURRENCY, pattern = "#,#00.00")
	@NotNull(message = "Campo valor deve ser preenchido")
	private double valor;

	@Column
	private boolean inativa;
	
	@Column(name = "categoria_despesa")
	@Enumerated(EnumType.STRING)
	@NotNull(message = "Campo categoria é obrigatório. Favor selecionar!")
	private CategoriaDaDespesa categoriaDaDespesa;
	
	@ManyToOne
	@JoinColumn(name = "id_empreendimento", referencedColumnName = "id")
	private Empreendimento idEmpreendimento;
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public boolean isInativa() {
		return inativa;
	}

	public void setInativa(boolean inativa) {
		this.inativa = inativa;
	}

	public CategoriaDaDespesa getCategoriaDaDespesa() {
		return categoriaDaDespesa;
	}

	public void setCategoriaDaDespesa(CategoriaDaDespesa categoriaDaDespesa) {
		this.categoriaDaDespesa = categoriaDaDespesa;
	}

	public Empreendimento getIdEmpreendimento() {
		return idEmpreendimento;
	}

	public void setIdEmpreendimento(Empreendimento idEmpreendimento) {
		this.idEmpreendimento = idEmpreendimento;
	}
	
	
}
