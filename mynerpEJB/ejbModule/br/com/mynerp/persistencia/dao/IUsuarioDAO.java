package br.com.mynerp.persistencia.dao;

import java.util.List;

import javax.ejb.Local;

import br.com.mynerp.persistencia.Empresa;
import br.com.mynerp.persistencia.Usuario;
import br.com.mynerp.persistencia.dao.exception.ObjetoNaoEncontradoException;
import br.com.mynerp.persistencia.dao.exception.UsuarioNaoEncontradoException;

@Local
public interface IUsuarioDAO {
	
public Usuario inserir (Usuario usuario);
	
	public Usuario alterar (Usuario usuario);
	
	public void remover (Usuario usuario);

	public Usuario obter(String userName, String password) 	throws UsuarioNaoEncontradoException;

	public Usuario obter(int id) throws UsuarioNaoEncontradoException;
	
	public List<Usuario> obter(Integer position, Integer max) throws UsuarioNaoEncontradoException;
	
	public <T>Integer totalRegistros (Class<T> classe, Empresa empresa) throws ObjetoNaoEncontradoException;
	
	public boolean possuiPermissaoMenu(Empresa empresa,Integer idMenu, Usuario usuario);

}