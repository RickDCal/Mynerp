package br.com.mynerp.persistencia;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table (name = "cobranca")
public class Cobranca {
	
	private int id;
	private String nome;
	private Boolean isPagar;
	private Boolean isReceber;
	private Date dataExclusao;
	private String clientIdProperty;
	
	public Cobranca() {
		
	}
	
	@Id
	@Column (name = "id", columnDefinition="int")
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
	
	@Column (name = "ind_pagar", columnDefinition="tinyint")
	
	public Boolean isPagar() {
		return isPagar;
	}
	public void setPagar(boolean isPagar) {
		this.isPagar = isPagar;
	}

	@Column (name = "ind_receber", columnDefinition="tinyint")
	public Boolean isReceber() {
		return isReceber;
	}
	public void setReceber(boolean isReceber) {
		this.isReceber = isReceber;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Column (name = "data_exclusao", columnDefinition="datetime")
	public Date getDataExclusao() {
		return dataExclusao;
	}
	public void setDataExclusao(Date dataExclusao) {
		this.dataExclusao = dataExclusao;
	}
	
	@Column (name = "uuid", columnDefinition="nvarchar")
	public String getClientIdProperty() {
		return clientIdProperty;
	}

	public void setClientIdProperty(String clientIdProperty) {
		this.clientIdProperty = clientIdProperty;
	}	

}
