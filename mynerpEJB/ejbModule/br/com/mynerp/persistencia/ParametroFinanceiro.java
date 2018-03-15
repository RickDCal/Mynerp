package br.com.mynerp.persistencia;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="prm_financeiro")
public class ParametroFinanceiro {
	
	
	private int id;
	private Cobranca cobrancaPadrao;
	private CondicaoPagamento condicaoPagamentoPadrao;
	private Parametro parametro;	
	
	public ParametroFinanceiro() {
		
	}
	
	@Id
	@Column(name="idparametro", columnDefinition="int")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@OneToOne
	@JoinColumn(name = "idcobranca_padrao")
	public Cobranca getCobrancaPadrao() {
		return cobrancaPadrao;
	}

	public void setCobrancaPadrao(Cobranca cobrancaPadrao) {
		this.cobrancaPadrao = cobrancaPadrao;
	}

	@OneToOne
	@JoinColumn(name="idcondicao_padrao")
	public CondicaoPagamento getCondicaoPagamentoPadrao() {
		return condicaoPagamentoPadrao;
	}

	public void setCondicaoPagamentoPadrao(CondicaoPagamento condicaoPagamentoPadrao) {
		this.condicaoPagamentoPadrao = condicaoPagamentoPadrao;
	}

	@OneToOne
	@JoinColumn(name="idparametro")
	public Parametro getParametro() {
		return parametro;
	}

	public void setParametro(Parametro parametro) {
		this.parametro = parametro;
	}

	@Override
	public String toString() {
		return "ParametroFinanceiro [id=" + id + ", cobrancaPadrao=" + cobrancaPadrao + ", condicaoPagamentoPadrao="
				+ condicaoPagamentoPadrao + ", parametro=" + parametro + "]";
	}
	
	
	
	

}
