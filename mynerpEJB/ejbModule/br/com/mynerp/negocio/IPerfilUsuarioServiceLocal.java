package br.com.mynerp.negocio;

import java.util.List;

import javax.ejb.Local;

import com.google.gson.JsonObject;

import br.com.mynerp.negocio.exception.FalhaAoCriarJSONException;
import br.com.mynerp.negocio.exception.PerfilUsuarioInexistenteException;
import br.com.mynerp.persistencia.PerfilUsuario;
import br.com.mynerp.persistencia.dao.exception.PerfilUsuarioNaoEncontradoException;


@Local
public interface IPerfilUsuarioServiceLocal {
	
	public void cadastrar(PerfilUsuario perfil) throws PerfilUsuarioNaoEncontradoException;

	public void remover(PerfilUsuario perfil) throws PerfilUsuarioNaoEncontradoException;

	public void atualizar(PerfilUsuario perfil) throws PerfilUsuarioNaoEncontradoException;
	
	public PerfilUsuario pesquisar (int id) throws PerfilUsuarioNaoEncontradoException, PerfilUsuarioInexistenteException;

	public List<PerfilUsuario> pesquisar(boolean isMenuEager) throws PerfilUsuarioNaoEncontradoException, PerfilUsuarioInexistenteException;

	public PerfilUsuario pesquisar(int idPerfil, boolean isMenuEager) throws PerfilUsuarioNaoEncontradoException, PerfilUsuarioInexistenteException;
	
	public List<PerfilUsuario> pesquisar() throws PerfilUsuarioNaoEncontradoException, PerfilUsuarioInexistenteException;
	
	public JsonObject perfilUsuarioJson(PerfilUsuario perfilUsuario) throws FalhaAoCriarJSONException;
	
}