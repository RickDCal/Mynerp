package br.com.mynerp.persistencia.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.com.mynerp.persistencia.Menu;
import br.com.mynerp.persistencia.PerfilUsuario;
import br.com.mynerp.persistencia.dao.exception.MenuNaoEncontradoException;


@Stateless
public class MenuDAO extends GenericDAO implements IMenuDAO    {
	
	public MenuDAO() {
		
	}
	
	public  Menu obter (int id) throws MenuNaoEncontradoException {
		Menu menu = entityManager.find(Menu.class, id);
		if (menu == null) throw new MenuNaoEncontradoException();
		return menu;
	}
	
	
	public Menu obter (int id, boolean isMenuFilhosEager) throws MenuNaoEncontradoException { //coloquei o tipo de lista pra ver no que d�
		
		
		String consulta = "select m from Menu m Join fetch m.menuFilhos where m.id = :idMenu order by posicao";			
		
		Query query = entityManager.createQuery(consulta);
				
		query.setParameter("idMenu", id);
		
		query.setHint("org.hibernate.timeout", 2000);
		
		Menu menu = (Menu)query.getSingleResult();
			
		return menu;
		
	}
	
	
	
	public List<Menu> obter (PerfilUsuario perfilUsuario) throws MenuNaoEncontradoException { //coloquei o tipo de lista pra ver no que d�
		
		
		String consulta = "select m from PerfilUsuario p Join fetch p.menuUsuario m where p.id = :idPerfil";			
		
		Query query = entityManager.createQuery(consulta);
		
		int idPerfil = perfilUsuario.getId();
		
		query.setParameter("perfil", idPerfil);
		
		query.setHint("org.hibernate.timeout", 2000);
		
		List<Menu> list = (List<Menu>)query.getResultList(); 
			
		return list;
		
	}	
			

}
