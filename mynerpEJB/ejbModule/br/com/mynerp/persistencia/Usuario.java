package br.com.mynerp.persistencia;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity  
@Table(name="usuario")// o nome da tabela correspondente quando for entity
public class Usuario {

	private int id;	
	private String userName;	
	private String password;
	private String apelido;
	private String email;
	private PerfilUsuario perfil;
	//private Pessoa pessoa;
	private String nomeArquivoFoto;
	private Date dataExclusao; 
	private List<Empresa> empresas;
			
	public Usuario() {
		
	}
	
	@Id //indica chave prim�ria
	@Column(name="id")//nome da coluna no bd
	@GeneratedValue(strategy = GenerationType.IDENTITY) //auto incermento na chave
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
//	@ManyToOne
//	@JoinColumn(name = "idpessoa")
//	public Pessoa getPessoa() {
//		return pessoa;
//	}
//	public void setPessoa(Pessoa pessoa) {
//		this.pessoa = pessoa;
//	}

	@Column(name="user_name", columnDefinition="nvarchar")
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name="password", columnDefinition="nvarchar")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name="apelido", columnDefinition="nvarchar")
	public String getApelido() {
		return apelido;
	}
	public void setApelido(String apelido) {
		this.apelido = apelido;
	}
	
	@Column(name="email", columnDefinition="nvarchar")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	@OneToOne
	@JoinColumn(name = "idperfil")
	public PerfilUsuario getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilUsuario perfil) {
		this.perfil = perfil;
	}

	@Column(name="nome_foto_perfil", columnDefinition="nvarchar")
	public String getNomeArquivoFoto() {
		return nomeArquivoFoto;
	}
	public void setNomeArquivoFoto(String nomeArquivoFoto) {
		this.nomeArquivoFoto = nomeArquivoFoto;
	}

	@Column(name="data_desativacao", columnDefinition="datetime")
	public Date getDataExclusao() {
		return dataExclusao;
	}

	public void setDataExclusao(Date dataExclusao) {
		this.dataExclusao = dataExclusao;
	}

	@OneToMany (cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable (name = "usuario_empresa", joinColumns= {@JoinColumn (name = "idUsuario", referencedColumnName="id")}, 
	            inverseJoinColumns = {@JoinColumn (name = "idEmpresa", referencedColumnName = "id")})
	public List<Empresa> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id +  ", userName="
				+ userName + ", password=" + password + ", apelido=" + apelido
				+ ", perfil=" + perfil + ", nomeArquivoFoto=" + nomeArquivoFoto + "]";
	}	
	
}
