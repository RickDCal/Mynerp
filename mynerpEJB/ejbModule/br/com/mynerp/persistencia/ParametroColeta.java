package br.com.mynerp.persistencia;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table (name = "prm_coleta")
public class ParametroColeta {
	
	private int id;
	private Cobranca cobrancaPadraoColeta;
	private CondicaoPagamento condicaoPadraoColeta;
	private String caminhoXmlCte;
	private Parametro parametro;

	public ParametroColeta() {
		
	}	
	
	@Id
	@Column(name = "idparametro")
	//@GeneratedValue(strategy = GenerationType.IDENTITY)-- não é identity e equivale ao mesmo id da tabela parametro
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
		
	@Column(name = "caminho_diretorio_xml_cte", columnDefinition="nvarchar")
	public String getCaminhoXmlCte() {
		return caminhoXmlCte;
	}

	public void setCaminhoXmlCte(String caminhoXmlCte) {
		this.caminhoXmlCte = caminhoXmlCte;
	}

	@OneToOne
	@JoinColumn(name = "idcobranca_coleta")
	public Cobranca getCobrancaPadraoColeta() {
		return cobrancaPadraoColeta;
	}

	public void setCobrancaPadraoColeta(Cobranca cobrancaPadraoColeta) {
		this.cobrancaPadraoColeta = cobrancaPadraoColeta;
	}
	

	@OneToOne
	@JoinColumn(name = "idcondicao_pagamento_coleta")
	public CondicaoPagamento getCondicaoPadraoColeta() {
		return condicaoPadraoColeta;
	}

	public void setCondicaoPadraoColeta(CondicaoPagamento condicaoPadraoColeta) {
		this.condicaoPadraoColeta = condicaoPadraoColeta;
	}

	@OneToOne
	@JoinColumn(name = "idparametro")
	public Parametro getParametro() {
		return parametro;
	}

	public void setParametro(Parametro parametro) {
		this.parametro = parametro;
	}

	@Override
	public String toString() {
		return "ParametroColeta [id=" + id + ", cobrancaPadraoColeta=" + cobrancaPadraoColeta
				+ ", condicaoPadraoColeta=" + condicaoPadraoColeta + ", caminhoXmlCte=" + caminhoXmlCte + ", parametro="
				+ parametro + "]";
	}
	
	

}
