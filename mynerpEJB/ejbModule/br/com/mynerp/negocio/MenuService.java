package br.com.mynerp.negocio;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.mynerp.persistencia.Menu;
import br.com.mynerp.persistencia.PerfilUsuario;
import br.com.mynerp.persistencia.dao.IMenuDAO;
import br.com.mynerp.persistencia.dao.exception.MenuNaoEncontradoException;

@Stateless
public class MenuService implements IMenuServiceLocal   {
	
	@EJB
	private IMenuDAO menuDao;

	public MenuService() {
		
	}		
	
	public Menu pesquisar (int id) throws MenuNaoEncontradoException {
		
		try {
			return menuDao.obter(id);
		} catch (MenuNaoEncontradoException e) {
			throw new MenuNaoEncontradoException();
		}
	}
	
	public Menu pesquisar (int id, boolean isMenuFilhosEager) throws MenuNaoEncontradoException {
		
		try {
			return menuDao.obter(id, isMenuFilhosEager);
		} catch (MenuNaoEncontradoException e) {
			// TODO Auto-generated catch block
			throw new MenuNaoEncontradoException();
		}
	}

	public List<Menu> pesquisar (PerfilUsuario perfilUsuario) throws MenuNaoEncontradoException {
		
		try {
			return menuDao.obter(perfilUsuario);
		} catch (MenuNaoEncontradoException e) {
			throw new MenuNaoEncontradoException();
		}	
		
	}
	
	 
	
	 

}
