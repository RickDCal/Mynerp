package br.com.mynerp.persistencia;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity  
@Table(name="cliente")// o nome da tabela correspondente quando for entity
@PrimaryKeyJoinColumn(name="idpessoa")  
public class Cliente extends Pessoa{

	//private int idpessoa;
	private Double credito;	
	private Integer vencimento;
	//private List<Coleta> coletas;
	
	
		
	public Cliente() {
		
	}
	
	@Column(name="limite_credito", columnDefinition="float")
	public Double getCredito() {
		return credito;
	}

	public void setCredito(Double credito) {
		this.credito = credito;
	}
	
	@Column(name="dia_vencimento", columnDefinition="int")
	public Integer getVencimento() {
		return vencimento;
	}

	public void setVencimento(Integer vencimento) {
		this.vencimento = vencimento;
	}	
		
	public String toString() {
		
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(super.toString());
	    stringBuilder.append("Limite de Crédito: " + getCredito() + "\n <br>");
	    stringBuilder.append("Dia Vencimento: " + getVencimento() + "\n <br>");
	   
	   	return stringBuilder.toString();
	}





	


}
