package br.com.mynerp.persistencia;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table (name = "perfil_usuario")
public class PerfilUsuario {
	
	private int id;
	private String nome;
	private List<Menu> menuUsuario;
	
	public PerfilUsuario() {
		
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

	@OneToMany (cascade=CascadeType.ALL)
	@JoinTable (name = "perfil_menu", joinColumns= {@JoinColumn (name = "idPerfil", referencedColumnName="id")}, 
	            inverseJoinColumns = {@JoinColumn (name = "idMenu", referencedColumnName = "id")})
	public List<Menu> getMenuUsuario() {
		return menuUsuario;
	}
	public void setMenuUsuario(List<Menu> menuUsuario) {
		this.menuUsuario = menuUsuario;
	}	

}
