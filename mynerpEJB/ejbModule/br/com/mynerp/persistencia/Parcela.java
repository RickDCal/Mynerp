package br.com.mynerp.persistencia;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;


@Entity
@Table (name = "parcela")
public class Parcela {
	
	private int id;
	private Conta conta;
	private int sequencial;
	private Date dataEmissao;
	private Cobranca cobranca;
	private Date dataVencimento;
	private Date dataQuitacao;
	private double valor;
	private String numeroDocto;
	private String observacao;
	private String clientIdProperty;
	
	public Parcela() {

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
	
	//@OneToOne //--- estava assim
	@ManyToOne
	@JoinColumn (name = "idconta")
	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	@Column (name = "sequencial", columnDefinition="int")
	@OrderBy(value="sequencial ASC")
	public int getSequencial() {
		return sequencial;
	}
	public void setSequencial(int sequencial) {
		this.sequencial = sequencial;
	}

	@Column (name = "data_emissao", columnDefinition="datetime")
	public Date getDataEmissao() {
		return dataEmissao;
	}
	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}
	
	@OneToOne
	@JoinColumn (name = "idcobranca")
	public Cobranca getCobranca() {
		return cobranca;
	}
	public void setCobranca(Cobranca cobranca) {
		this.cobranca = cobranca;
	}

	@Column (name = "data_vencimento", columnDefinition="date")
	public Date getDataVencimento() {
		return dataVencimento;
	}
	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	@Column (name = "data_quitacao", columnDefinition="datetime")
	public Date getDataQuitacao() {
		return dataQuitacao;
	}
	public void setDataQuitacao(Date dataQuitacao) {
		this.dataQuitacao = dataQuitacao;
	}

	@Column (name = "valor", columnDefinition="float")
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}

	@Column (name = "numero_documento", columnDefinition="nvarchar")
	public String getNumeroDocto() {
		return numeroDocto;
	}
	public void setNumeroDocto(String numeroDocto) {
		this.numeroDocto = numeroDocto;
	}

	@Column (name = "observacao", columnDefinition="nvarchar")
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
	@Column(name="uuid", columnDefinition="nvarchar")
	public String getClientIdProperty() {
		return clientIdProperty;
	}

	public void setClientIdProperty(String clientIdProperty) {
		this.clientIdProperty = clientIdProperty;
	}
	

}
