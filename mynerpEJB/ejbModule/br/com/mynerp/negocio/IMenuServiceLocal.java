package br.com.mynerp.negocio;

import java.util.List;

import javax.ejb.Local;

import br.com.mynerp.persistencia.Menu;
import br.com.mynerp.persistencia.PerfilUsuario;
import br.com.mynerp.persistencia.dao.exception.MenuNaoEncontradoException;

@Local
public interface IMenuServiceLocal {

	public Menu pesquisar(int id) throws MenuNaoEncontradoException;
	
	public Menu pesquisar (int id, boolean isMenuFilhosEager) throws MenuNaoEncontradoException;

	public List<Menu> pesquisar(PerfilUsuario perfilUsuario) throws MenuNaoEncontradoException;

}