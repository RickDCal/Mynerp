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
import javax.persistence.Transient;


@Entity  // pra importar isso precisei inserir biblioteca de runtime do jboss 6 no projeto ejb - javax persistence
@Table(name="endereco")// o nome da tabela correspondente quando for entity
public class Endereco {

	private Integer id;
	private Pessoa pessoa;
	private Integer sequencial;
	private boolean indPadrao;
	private Integer tipoEndereco;
	private String cep;
	private String tipoLogradouro;
	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;
	private String ufSigla;
	private Integer IBGECidade;
	private String nomeCidade;
	private String pontoReferencia;
	private Date dataExclusao;
	private String pais;
	private String ddd1;
	private String telefone1;
	private String ddd2;
	private String telefone2;	
		
	public Endereco() {
		
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

	@Column(name="ind_padrao", columnDefinition="tinyint")
	public boolean isIndPadrao() {
		return indPadrao;
	}

	public void setIndPadrao(boolean indPadrao) {
		this.indPadrao = indPadrao;
	}

	@Column(name="id_tipo_endereco", columnDefinition="int")
	public Integer getTipoEndereco() {
		return tipoEndereco;
	}

	public void setTipoEndereco(Integer tipoEndereco) {
		this.tipoEndereco = 1; // coloquei fixo por enquanto
	}

	@Column(name="cep", columnDefinition="nvarchar")
	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	@Column(name="tipo_logradouro", columnDefinition="nvarchar")
	public String getTipoLogradouro() {
		return tipoLogradouro;
	}

	public void setTipoLogradouro(String tipoLogradouro) {
		this.tipoLogradouro = tipoLogradouro;
	}

	@Column(name="logradouro", columnDefinition="nvarchar")
	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	@Column(name="numero", columnDefinition="nvarchar")
	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	@Column(name="complemento", columnDefinition="nvarchar")
	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	@Column(name="bairro", columnDefinition="nvarchar")
	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	@Column(name="uf_sigla", columnDefinition="nvarchar")
	public String getUfSigla() {
		return ufSigla;
	}

	public void setUfSigla(String ufSigla) {
		this.ufSigla = ufSigla;
	}

	@Column(name="cid_codigo", columnDefinition="int")
	public Integer getIBGECidade() {
		return IBGECidade;
	}

	public void setIBGECidade(Integer iBGECidade) {
		IBGECidade = iBGECidade;
	}

	@Column(name="nome_cidade", columnDefinition="nvarchar")
	public String getNomeCidade() {
		return nomeCidade;
	}

	public void setNomeCidade(String nomeCidade) {
		this.nomeCidade = nomeCidade;
	}

	@Column(name="ponto_referencia", columnDefinition="nvarchar")
	public String getPontoReferencia() {
		return pontoReferencia;
	}

	public void setPontoReferencia(String pontoReferencia) {
		this.pontoReferencia = pontoReferencia;
	}

	@Column(name="data_exclusao", columnDefinition="datetime")
	public Date getDataExclusao() {
		return dataExclusao;
	}

	public void setDataExclusao(Date dataExclusao) {
		this.dataExclusao = dataExclusao;
	}

	@Column(name="pais", columnDefinition="nvarchar")
	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	@Column(name="ddd_telefone1", columnDefinition="nvarchar")
	public String getDdd1() {
		return ddd1;
	}

	public void setDdd1(String ddd1) {
		this.ddd1 = ddd1;
	}

	@Column(name="telefone1", columnDefinition="nvarchar")
	public String getTelefone1() {
		return telefone1;
	}

	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}

	@Column(name="ddd_telefone2", columnDefinition="nvarchar")
	public String getDdd2() {
		return ddd2;
	}

	public void setDdd2(String ddd2) {
		this.ddd2 = ddd2;
	}

	@Column(name="telefone2", columnDefinition="nvarchar")
	public String getTelefone2() {
		return telefone2;
	}

	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}
	
public String toString() {
		
		StringBuilder stringBuilder = new StringBuilder();
		//stringBuilder.append(super.toString());
	    stringBuilder.append(getTipoLogradouro()+ " " + getLogradouro()+", " 
	    + getNumero() + " - " + getBairro()+ " - " + getNomeCidade()
	    + "|" + getUfSigla());
	   	return stringBuilder.toString();
	}

@Transient
public Integer getIdtipo() {
	// TODO Auto-generated method stub
	return getIdtipo();
}


}
