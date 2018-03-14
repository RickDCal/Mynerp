package br.com.mynerp.apresentacao.facade.estaticos;

import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.com.mynerp.negocio.IMenuServiceLocal;
import br.com.mynerp.persistencia.Menu;
import br.com.mynerp.persistencia.PerfilUsuario;
import br.com.mynerp.persistencia.dao.exception.MenuNaoEncontradoException;

public class MenuFacade {
	
	private Properties p;
	private Context c;
	
	public IMenuServiceLocal service;
	
	public MenuFacade() throws NamingException {
		
		p = new Properties();
		c = new InitialContext(p);
		service = (IMenuServiceLocal)c.lookup("java:global/mynerpjsEAR/mynerpjsEJB/MenuService");
		
	}
	
	public Menu pesquisar(int id) throws MenuNaoEncontradoException {
		return service.pesquisar(id);
	}
	
	public Menu pesquisar (int id, boolean isMenuFilhosEager) throws MenuNaoEncontradoException {
		return service.pesquisar(id, isMenuFilhosEager);
	}

	public List<Menu> pesquisar(PerfilUsuario perfilUsuario) throws MenuNaoEncontradoException {
		return service.pesquisar(perfilUsuario);
	}	
		
	
}
