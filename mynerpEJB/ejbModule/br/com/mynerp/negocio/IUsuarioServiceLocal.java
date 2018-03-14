package br.com.mynerp.negocio;

import java.util.List;

import javax.ejb.Local;

import com.google.gson.JsonObject;

import br.com.mynerp.negocio.exception.FalhaAoCriarJSONException;
import br.com.mynerp.negocio.exception.UsuarioInexistenteException;
import br.com.mynerp.persistencia.Empresa;
import br.com.mynerp.persistencia.Usuario;
import br.com.mynerp.persistencia.dao.exception.ObjetoNaoEncontradoException;

@Local
public interface IUsuarioServiceLocal {

	public Usuario pesquisar(String userName, String password) throws UsuarioInexistenteException;

	public Usuario pesquisar(int id) throws UsuarioInexistenteException;
	
	public List<Usuario> pesquisar(Integer position, Integer max) throws UsuarioInexistenteException;
	
	public void cadastrar (Usuario usuario) throws UsuarioInexistenteException;
	
	public void atualizar (Usuario usuario) throws UsuarioInexistenteException;
	
	public void remover (Usuario usuario) throws UsuarioInexistenteException;
	
	public JsonObject usuarioJson(Usuario usuario) throws FalhaAoCriarJSONException;
	
	public Integer totalRegistros (Empresa empresa) throws ObjetoNaoEncontradoException;
	
	public boolean possuiPermissaoMenu(Empresa empresa,Integer idMenu, Usuario usuario);

}