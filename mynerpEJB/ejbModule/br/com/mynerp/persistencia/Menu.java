package br.com.mynerp.persistencia;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table (name = "menu")
public class Menu {
	
	private int id;
	private String nome;
	private Integer posicao;
	private String iconCls;
	private String className;
	private Menu menuPai;	
	private List<Menu> menuFilhos;
	
	@Id
	@Column (name = "id")
	//@GeneratedValue(strategy = GenerationType.AUTO) // para esta classe preferi gerar id manualmente sendo manipulado no banco
	public int getId() {
		return id;
	}
	public void setId(int id) { // comentando o set para que esta propriedade não possa ser passada. menus devem ser criados diretamente no banco de dados
		this.id = id;
	}

	@Column (name = "nome", columnDefinition="nvarchar")
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Column (name = "posicao")
	public Integer getPosicao() {
		return posicao;
	}
	public void setPosicao(Integer posicao) {
		this.posicao = posicao;
	}
	
	@Column (name = "iconCls", columnDefinition="nvarchar")
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	
	@Column (name = "className", columnDefinition="nvarchar")
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}	
	
	@ManyToOne
	@JoinColumn(name = "idmenu")
	public Menu getMenuPai() {
		return menuPai;
	}
	public void setMenuPai(Menu menuPai) {
		this.menuPai = menuPai;
	}
	
	@OneToMany (fetch = FetchType.EAGER)// (mappedBy = "Menu")
	@JoinColumn(name = "idmenu")
	public List<Menu> getMenuFilhos() {
		return menuFilhos;
	}
	public void setMenuFilhos(List<Menu> menuFilhos) {
		this.menuFilhos = menuFilhos;
	}
	
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Menu other = (Menu) obj;
//		if (id != other.id)
//			return false;
//		return true;
//	}	

}
