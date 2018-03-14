package br.com.mynerp.persistencia.dao;

import java.util.List;

import javax.ejb.Local;

import br.com.mynerp.persistencia.Menu;
import br.com.mynerp.persistencia.PerfilUsuario;
import br.com.mynerp.persistencia.dao.exception.MenuNaoEncontradoException;

@Local
public interface IMenuDAO {

	public Menu obter(int id) throws MenuNaoEncontradoException;
	
	public Menu obter (int id, boolean isMenuFilhosEager) throws MenuNaoEncontradoException;

	public List<Menu> obter(PerfilUsuario perfilUsuario)
			throws MenuNaoEncontradoException;

}