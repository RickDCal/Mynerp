package br.com.mynerp.persistencia;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table (name = "empresa")
public class Empresa {
	
	private int id;
	private String nome;
	private Parametro parametro;
	private String logoRelatorio;
	private Pessoa pessoa;
	
	public Empresa() {

	}
	
	@Id
	@Column (name = "id")
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

	@ManyToOne
	@JoinColumn (name = "idparametro")
	public Parametro getParametro() {
		return parametro;
	}

	public void setParametro(Parametro parametro) {
		this.parametro = parametro;
	}
	
	@Column(name = "caminho_logomarca_relatorio", columnDefinition="nvarchar")
	public String getLogoRelatorio() {
		return logoRelatorio;
	}
	public void setLogoRelatorio(String logoRelatorio) {
		this.logoRelatorio = logoRelatorio;
	}
	
	@OneToOne
	@JoinColumn(name = "idpessoa")
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

}
