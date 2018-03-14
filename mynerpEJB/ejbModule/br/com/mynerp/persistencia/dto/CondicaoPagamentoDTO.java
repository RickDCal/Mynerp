package br.com.mynerp.persistencia.dto;

import java.util.Date;
import java.util.Set;

import javax.persistence.OrderBy;

import br.com.mynerp.persistencia.CondicaoParcela;

public class CondicaoPagamentoDTO {

	private Integer id;
	private String nome;
	private Set<CondicaoParcela> parcelas; 
	private boolean isPagar;
	private boolean isReceber;
	private Date data_exclusao;
	private String clientIdProperty;
	
	public CondicaoPagamentoDTO() {	
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	//@OneToMany(/*mappedBy="condicaoPagamento",*/ fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
	//@JoinColumn(name = "idcondicao")// este relacionamento é unidirecional
	@OrderBy(value="sequencial ASC")
	public Set<CondicaoParcela> getParcelas() { 
		return  parcelas;
	}
	
	public void setParcelas(Set<CondicaoParcela> parcelas) {
		this.parcelas = parcelas;
	}

	public boolean isPagar() {
		return isPagar;
	}
	public void setPagar(boolean isPagar) {
		this.isPagar = isPagar;
	}
	
	public boolean isReceber() {
		return isReceber;
	}

	public void setReceber(boolean isReceber) {
		this.isReceber = isReceber;
	}

	public Date getData_exclusao() {
		return data_exclusao;
	}
	public void setData_exclusao(Date data_exclusao) {
		this.data_exclusao = data_exclusao;
	}
	
	public String getClientIdProperty() {
		return clientIdProperty;
	}

	public void setClientIdProperty(String clientIdProperty) {
		this.clientIdProperty = clientIdProperty;
	}
}
