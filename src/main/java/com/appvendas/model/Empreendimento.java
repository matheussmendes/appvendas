package com.appvendas.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CNPJ;
import com.appvendas.model.enums.TipoDoEmpreendimento;


@SuppressWarnings("serial")
public class Empreendimento extends AbstractEntity<Long> {

	@Column()
	@NotBlank(message = "Campo CNPJ não pode ser nulo")
	@CNPJ(message = "Verifique seu CNPJ")
	private String cnpj;
	
	@Column(name = "nome_fantasia")
	@NotBlank(message = "Campo Nome Fantasia não pode ser nulo")
	@Size(min = 2, max = 20, message = "O Nome Fantasia deve ter entre {min} e {max} caracteres.")
	private String nomeFantasia;

	@Enumerated(EnumType.STRING)
	@NotNull(message = "Campo Tipo do Empreendimento não pode ser nulo")
	private TipoDoEmpreendimento tipoDoEmpreendimento;
	
	@Column
	@NotNull(message = "Campo Acesso não pode ser nulo")
	@OneToOne(cascade = CascadeType.ALL)
	private Acesso acesso;
	
	
	
	public Acesso getAcesso() {
		return acesso;
	}

	public void setAcesso(Acesso acesso) {
		this.acesso = acesso;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public TipoDoEmpreendimento getTipoDoEmpreendimento() {
		return tipoDoEmpreendimento;
	}

	public void setTipoDoEmpreendimento(TipoDoEmpreendimento tipoDoEmpreendimento) {
		this.tipoDoEmpreendimento = tipoDoEmpreendimento;
	}	
}
