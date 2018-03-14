package br.com.mynerp.persistencia.dao;

import java.util.List;

import javax.ejb.Local;

import br.com.mynerp.persistencia.Empresa;
import br.com.mynerp.persistencia.PerfilUsuario;
import br.com.mynerp.persistencia.dao.exception.ObjetoNaoEncontradoException;
import br.com.mynerp.persistencia.dao.exception.PerfilUsuarioNaoEncontradoException;

@Local
public interface IPerfilUsuarioDAO {

	public PerfilUsuario obter (int id) throws PerfilUsuarioNaoEncontradoException;
	
	public List<PerfilUsuario> obter(boolean isMenuEager) throws PerfilUsuarioNaoEncontradoException;

	public PerfilUsuario obter (int idPerfil, boolean isMenuEager) throws PerfilUsuarioNaoEncontradoException;
	
	public List<PerfilUsuario> obter() throws PerfilUsuarioNaoEncontradoException;
	
	public PerfilUsuario inserir(PerfilUsuario perfil);

	public PerfilUsuario alterar(PerfilUsuario perfil) throws PerfilUsuarioNaoEncontradoException;

	public void remover(PerfilUsuario perfil);
	
	public <T>Integer totalRegistros (Class<T> classe, Empresa empresa)throws ObjetoNaoEncontradoException;

}