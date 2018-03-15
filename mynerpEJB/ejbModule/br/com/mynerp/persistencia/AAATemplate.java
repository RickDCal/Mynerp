package br.com.mynerp.persistencia;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/* descomentar
@Entity  
@Table(name="pessoa")
@Inheritance(strategy=InheritanceType.JOINED) //isto varia
*/
public class AAATemplate {
	
/*A intenção é fazer com que todas as classes referentes a objetos deste pacote persitência herdem desta classe*/

	@Id 
	@Column(name="id", columnDefinition="int")
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int id;	
	@Column(name="nome", columnDefinition="nvarchar")
	private String nome;
	@Column(name="uuid", columnDefinition="nvarchar")
	private String clientIdProperty;
	@Column(name="data_cadastro", columnDefinition="datetime")
	private Date dataCadastro;
	@Column(name="data_alteracao", columnDefinition="datetime")
	private Date dataAlteracao;
	@Column(name="data_desativacao", columnDefinition="datetime")
	private Date dataDesativacao;
		
	public AAATemplate() {
		
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

	public String getClientIdProperty() {
		return clientIdProperty;
	}
	public void setClientIdProperty(String clientIdProperty) {
		this.clientIdProperty = clientIdProperty;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataAlteracao() {
		return dataAlteracao;
	}
	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	public Date getDataDesativacao() {
		return dataDesativacao;
	}
	public void setDataDesativacao(Date dataDesativacao) {
		this.dataDesativacao = dataDesativacao;
	}

	public String toString() {
		
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(super.toString());
	    stringBuilder.append("Id: " + getId() + "\n <br>");
	    stringBuilder.append("Nome: " + getNome() + "\n <br>");
	    stringBuilder.append("Data Cadastro: " + getDataCadastro() + "\n <br>");
	    stringBuilder.append("Data Alteracao: " + getDataAlteracao() + "\n <br>");
	    stringBuilder.append("Data Desativacao: " + getDataDesativacao() + "\n <br>");
	   
	   	return stringBuilder.toString();
	}





	


}
