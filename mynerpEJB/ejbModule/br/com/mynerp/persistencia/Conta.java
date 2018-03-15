package br.com.mynerp.persistencia;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "id_pagar_receber", discriminatorType=DiscriminatorType.INTEGER)
public abstract class Conta {
	
private int id;
private Pessoa pessoa;
private Coleta coleta;
private String numeroDocumento;
private double valorTotal;
private Date dataEmissaoConta;
private String observacao;
private List<Parcela> parcelas;
private Date dataAlteracao;
private String clientIdProperty;

public Conta(){
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

@OneToOne
@JoinColumn (name = "idpessoa")
public Pessoa getPessoa() {
	return pessoa;
}
public void setPessoa(Pessoa pessoa) {
	this.pessoa = pessoa;
}

@OneToOne
@JoinColumn (name = "idcoleta")
public Coleta getColeta() {
	return coleta;
}
public void setColeta(Coleta coleta) {
	this.coleta = coleta;
}

@Column (name = "numero_documento", columnDefinition="nvarchar")
public String getNumeroDocumento() {
	return numeroDocumento;
}
public void setNumeroDocumento(String numeroDocumento) {
	this.numeroDocumento = numeroDocumento;
}

@Column (name="valor_total", columnDefinition="float")
public double getValorTotal() {
	return valorTotal;
}
public void setValorTotal(double valorTotal) {
	this.valorTotal = valorTotal;
}

@Column (name = "data_emissao", columnDefinition="datetime")
public Date getDataEmissaoConta() {
	return dataEmissaoConta;
}
public void setDataEmissaoConta(Date dataEmissaoConta) {
	this.dataEmissaoConta = dataEmissaoConta;
}

@Column(name = "observacao", columnDefinition="nvarchar")
public String getObservacao() {
	return observacao;
}
public void setObservacao(String observacao) {
	this.observacao = observacao;
}

@OneToMany(mappedBy = "conta", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
@OrderBy(value="sequencial ASC")
public List<Parcela> getParcelas() {
	return parcelas;

	/* // este fetch type  EAGER é para que quando eu fizer 
	 * uma consulta e quizer que um atributo lista seja carregado junto com o objeto
	 * o padrão dos atribuitos de One to Many é LAZY que só carrega se der um get no atributo,
	 * mas para fazer um iterator destas parcelas buscando a conta dá erro pois o dao não retorna a coleção 
	 * se esiver lazy.
	 * Importante: isso não deve ser usado sem necessidade pois pode provocar estouro de memòria
	 * contorna o erro failed to lazily initialize a collection of role*/		
}

public void setParcelas(List<Parcela> parcelas) {
	this.parcelas = parcelas;
}

@Column (name = "data_alteracao", columnDefinition="datetime")
public Date getDataAlteracao() {
	return dataAlteracao;
}
public void setDataAlteracao(Date dataAlteracao) {
	this.dataAlteracao = dataAlteracao;
}

@Column(name="uuid", columnDefinition="nvarchar")
public String getClientIdProperty() {
	return clientIdProperty;
}

public void setClientIdProperty(String clientIdProperty) {
	this.clientIdProperty = clientIdProperty;
}


}
