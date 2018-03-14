package br.com.mynerp.persistencia;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table (name = "prm")
public class Parametro {
	
	private int id;
	private List<Empresa> empresas;
	private String caminhoFotoPerfil;

	public Parametro() {
		
	}	
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name = "caminho_foto_perfil", columnDefinition="nvarchar")
	public String getCaminhoFotoPerfil() {
		return caminhoFotoPerfil;
	}
	public void setCaminhoFotoPerfil(String caminhoFotoPerfil) {
		this.caminhoFotoPerfil = caminhoFotoPerfil;
	} 
	
	@OneToMany(mappedBy = "parametro")
	public List<Empresa> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}

	@Override
	public String toString() {
		return "Parametro [id=" + id + ", empresas=" + empresas + ", caminhoFotoPerfil=" + caminhoFotoPerfil + "]";
	}
	
	

}
