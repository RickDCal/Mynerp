package br.com.mynerp.persistencia;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity  // pra importar isso precisei inserir biblioteca de runtime do jboss 6 no projeto ejb - javax persistence
@Table(name="contato")// o nome da tabela correspondente quando for entity
//@Inheritance(strategy=InheritanceType.JOINED)
public class Contato /*extends Pessoa*/ {
	
	private Integer id;
	private Pessoa pessoa;
	private Integer sequencial;
	private String nome;
	private String ddd;
	private String telefone;
	private String dddAdicional;
	private String telefoneAdicional;	
	private String email;
	private Date dataAniversario;
	private String dddCelular;
	private String celular;
	private boolean contatoPrincipal;
	
	public Contato() {
		
	}
	
	@Id
	@Column(name="id", columnDefinition="int")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@ManyToOne
	@JoinColumn(name = "idpessoa")	
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	@Column(name="sequencial", columnDefinition="int")
	public Integer getSequencial() {
		return sequencial;
	}
	public void setSequencial(Integer sequencial) {
		this.sequencial = sequencial;
	}
	
	@Column(name="nome", columnDefinition="nvarchar")
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Column(name="ddd_telefone", columnDefinition="nvarchar")
	public String getDdd() {
		return ddd;
	}
	public void setDdd(String ddd) {
		this.ddd = ddd;
	}
	
	@Column(name="telefone", columnDefinition="nvarchar")
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	@Column(name="ddd_adicional", columnDefinition="nvarchar")
	public String getDddAdicional() {
		return dddAdicional;
	}
	public void setDddAdicional(String dddAdicional) {
		this.dddAdicional = dddAdicional;
	}
	
	@Column(name="telefone_adicional", columnDefinition="nvarchar")
	public String getTelefoneAdicional() {
		return telefoneAdicional;
	}
	public void setTelefoneAdicional(String telefoneAdicional) {
		this.telefoneAdicional = telefoneAdicional;
	}
	
	@Column(name="email", columnDefinition="nvarchar")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name="data_aniversario", columnDefinition="date")
	public Date getDataAniversario() {
		return dataAniversario;
	}
	public void setDataAniversario(Date dataAniversario) {
		this.dataAniversario = dataAniversario;
	}
	
	@Column(name="ddd_celular", columnDefinition="nvarchar")
	public String getDddCelular() {
		return dddCelular;
	}
	public void setDddCelular(String dddCelular) {
		this.dddCelular = dddCelular;
	}
	
	@Column(name="celular", columnDefinition="nvarchar")
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	
	@Column(name="ind_contato_principal", columnDefinition="tinyint")
	public boolean isContatoPrincipal() {
		return contatoPrincipal;
	}
	public void setContatoPrincipal(boolean contatoPrincipal) {
		this.contatoPrincipal = true;
	}
	

	
	


}
