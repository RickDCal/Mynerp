package br.com.mynerp.persistencia;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;


@Entity
@Table (name = "condicao_pagamento")
public class CondicaoPagamento {
	//@Expose
	private int id;
	//@Expose
	private String nome;
	//@Expose
	private Set<CondicaoParcela> parcelas; /*Aqui eu usava um list mas o list + eager me dava uma excessão MultipleBagFetchException. O set não guarda ordenação o List sim. devo fazer testes para ver as implicações*/
	//@Expose
	private boolean isPagar;
	//@Expose
	private boolean isReceber;
	//@Expose
	private Date data_exclusao;
	//@Expose
	private String clientIdProperty;
	
	//ver pdf com anotações do gson nos materiais de estudo
	
	public CondicaoPagamento() {
		
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
	
	@Column (name = "nome", columnDefinition="nvarchar")
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@OneToMany(/*mappedBy="condicaoPagamento",*/ fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
	@JoinColumn(name = "idcondicao")// este relacionamento é unidirecional

	@OrderBy(value="sequencial ASC")
	public Set<CondicaoParcela> getParcelas() { 
		return  parcelas;
	}
	/*Fetch eager estava gerando excessão ao iniciar o jboss depois que eu mapeei a coleta para ter condição de pagamento. Então coloquei o relacionamento unidirecional e troquei de List para Set*/

	public void setParcelas(Set<CondicaoParcela> parcelas) {
		this.parcelas = parcelas;
	}

	@Column (name = "ind_conta_pagar", columnDefinition="tinyint")
	public boolean isPagar() {
		return isPagar;
	}
	public void setPagar(boolean isPagar) {
		this.isPagar = isPagar;
	}
	
	@Column (name = "ind_conta_receber", columnDefinition="tinyint")
	public boolean isReceber() {
		return isReceber;
	}

	public void setReceber(boolean isReceber) {
		this.isReceber = isReceber;
	}

	@Column (name = "data_exclusao", columnDefinition="datetime")
	public Date getData_exclusao() {
		return data_exclusao;
	}
	public void setData_exclusao(Date data_exclusao) {
		this.data_exclusao = data_exclusao;
	}
	
	@Column (name = "uuid", columnDefinition="nvarchar")
	public String getClientIdProperty() {
		return clientIdProperty;
	}

	public void setClientIdProperty(String clientIdProperty) {
		this.clientIdProperty = clientIdProperty;
	}
}
