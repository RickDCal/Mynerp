package br.com.mynerp.persistencia;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "condicao_parcela")
public class CondicaoParcela {
	
	private int id;
	
//	@Expose(serialize = false, deserialize = false) // para Gson desconsiderar. se deixasse sem o expose tbem desconsideraria
//	private CondicaoPagamento condicaoPagamento;
//	@Expose
	private int sequencial;
//	@Expose
	private int prazo;
//	@Expose
	private double percentual;
	private int idCondicao;
	private String clientIdProperty;
	
	
	public CondicaoParcela() {
		
	}

	@Id
	@Column(name = "id", columnDefinition="int")
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

//	@ManyToOne(targetEntity = CondicaoPagamento.class, cascade =
//		{CascadeType.ALL}, fetch = FetchType.EAGER, optional = false)
//	@JoinColumn(name = "idcondicao")
//	public CondicaoPagamento getCondicaoPagamento() {
//		return condicaoPagamento;
//	}
//
//	public void setCondicaoPagamento(CondicaoPagamento condicaoPagamento) {
//		this.condicaoPagamento = condicaoPagamento;
//	}


	@Column (name = "sequencial", columnDefinition="int")
	public int getSequencial() {
		return sequencial;
	}
	public void setSequencial(int sequencial) {
		this.sequencial = sequencial;
	}
	
	@Column (name = "dias_prazo", columnDefinition="int")
	public int getPrazo() {
		return prazo;
	}
	public void setPrazo(int prazo) {
		this.prazo = prazo;
	}

	@Column (name = "percentual", columnDefinition="float")
	public double getPercentual() {
		return percentual;
	}
	public void setPercentual(double percentual) {
		this.percentual = percentual;
	}

	@Column (name="idcondicao", columnDefinition="int", insertable=false)
	public int getIdCondicao() {
		return idCondicao;
	}

	public void setIdCondicao(int idCondicao) {
		this.idCondicao = idCondicao;
	}
	
	@Column (name = "uuid", columnDefinition="nvarchar")
	public String getClientIdProperty() {
		return clientIdProperty;
	}

	public void setClientIdProperty(String clientIdProperty) {
		this.clientIdProperty = clientIdProperty;
	}

	
}
