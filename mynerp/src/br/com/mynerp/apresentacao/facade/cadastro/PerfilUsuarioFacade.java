package br.com.mynerp.apresentacao.facade.cadastro;

import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.google.gson.JsonObject;

import br.com.mynerp.negocio.IPerfilUsuarioServiceLocal;
import br.com.mynerp.negocio.exception.FalhaAoCriarJSONException;
import br.com.mynerp.negocio.exception.PerfilUsuarioInexistenteException;
import br.com.mynerp.persistencia.PerfilUsuario;
import br.com.mynerp.persistencia.dao.exception.PerfilUsuarioNaoEncontradoException;

public class PerfilUsuarioFacade {
	
	private Properties p;
	private Context c;
	
	public IPerfilUsuarioServiceLocal service;
	
	public PerfilUsuarioFacade() throws NamingException {
		
		p = new Properties();
		c = new InitialContext(p);
		service = (IPerfilUsuarioServiceLocal)c.lookup("java:global/mynerpEAR/mynerpEJB/PerfilUsuarioService");
				
	}
	
	public PerfilUsuario pesquisar (int id) throws PerfilUsuarioNaoEncontradoException, PerfilUsuarioInexistenteException {
		return service.pesquisar(id);
	}
	
	public List<PerfilUsuario> pesquisar(boolean isMenuEager) throws PerfilUsuarioNaoEncontradoException, PerfilUsuarioInexistenteException {
		return service.pesquisar(isMenuEager);
	}
	
	public PerfilUsuario pesquisar(Integer idPerfil, boolean isMenuEager) throws PerfilUsuarioNaoEncontradoException, PerfilUsuarioInexistenteException {
		return service.pesquisar(idPerfil, isMenuEager);
	}
	
	public List<PerfilUsuario> pesquisar() throws PerfilUsuarioNaoEncontradoException, PerfilUsuarioInexistenteException {
		return service.pesquisar();
	}
	
	public void atualizar(PerfilUsuario perfil) throws PerfilUsuarioNaoEncontradoException {
		service.atualizar(perfil);
	}

	public void cadastrar (PerfilUsuario perfil) throws PerfilUsuarioNaoEncontradoException {
		service.cadastrar(perfil);
	}

	public void remover(PerfilUsuario perfil) throws PerfilUsuarioNaoEncontradoException {
		service.remover(perfil);
	}		
	
	public JsonObject perfilUsuarioJson(PerfilUsuario perfilUsuario) throws FalhaAoCriarJSONException {
		return service.perfilUsuarioJson(perfilUsuario);
	}	
	
}
