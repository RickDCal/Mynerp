package br.com.mynerp.persistencia;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;


//@MappedSuperclass   // mas a tabela existe no BD, posso deixar o enity também?? -- Giovani
@Entity  // pra importar isso precisei inserir biblioteca de runtime do jboss 6 no projeto ejb - javax persistence
@Table(name="pessoa")// o nome da tabela correspondente quando for entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Pessoa {

	private int id;
	private Date dataCadastro;
	private String cpfCnpj;
	private Integer pfPj;
	private String nome;
	private String nomeFantasia;
	private Date nascimento;
	private Integer idsexo;
	private List<Endereco> enderecos;
	private List<Contato> contatos;
	private String clientIdProperty;
	
	public Pessoa() {
		
	}
	
	@Id //indica chave primária
	@Column(name="id", columnDefinition="int")//nome da coluna no bd
	@GeneratedValue(strategy = GenerationType.IDENTITY) //auto incermento na chave
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	@Column(name="data_cadastro", columnDefinition="datetime")
	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
	@Column(name="cpf_cnpj", columnDefinition="nvarchar")
	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	@Column(name="id_fisica_juridica", columnDefinition="int")
	public Integer getPfPj() {
		return pfPj;
	}

	public void setPfPj(Integer pfPj) {
		this.pfPj = pfPj;
	}
	
	@Column(name="nome", columnDefinition="nvarchar")
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Column(name="nome_fantasia", columnDefinition="nvarchar")
	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}
	
	@Column(name="nascimento", columnDefinition="date")
	public Date getNascimento() {
		return nascimento;
	}

	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}	
	
	@Column(name="idsexo", columnDefinition="int")
	public Integer getIdsexo() {
		return idsexo;
	}

	public void setIdsexo(Integer idsexo) {
		this.idsexo = idsexo;
	}

		
	@OneToMany	(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval=true)
	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	@OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval=true) //fetch = FetchType.EAGER, 
	public List<Contato> getContatos() {
		return contatos;
	}

	public void setContatos(List<Contato> contatos) {
		this.contatos = contatos;
	}
	
	@Column(name="uuid", columnDefinition="nvarchar")
	public String getClientIdProperty() {
		return clientIdProperty;
	}

	public void setClientIdProperty(String clientIdProperty) {
		this.clientIdProperty = clientIdProperty;
	}	

	public String toString() {		
		StringBuilder stringBuilder = new StringBuilder();
	    stringBuilder.append("Id: " + getId() + "\n <br>");
	    stringBuilder.append("CPF CNPJ: " + getCpfCnpj() + "\n <br>");
	    stringBuilder.append("Nome: " + getNome() + "\n <br>");
	    stringBuilder.append("Nome fantasia: " + getNomeFantasia() + "\n <br>");
		return stringBuilder.toString();
	}	
	
	//o equals abaixo serve para o stateful session bean de usuario logado
	public boolean equals(Object obj) {
		return this.getId() == ((Pessoa)obj).getId();
	}	
}
