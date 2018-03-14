package br.com.mynerp.apresentacao.facade.cadastro;

import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.google.gson.JsonObject;

import br.com.mynerp.negocio.IUsuarioServiceLocal;
import br.com.mynerp.negocio.exception.FalhaAoCriarJSONException;
import br.com.mynerp.negocio.exception.UsuarioInexistenteException;
import br.com.mynerp.persistencia.Empresa;
import br.com.mynerp.persistencia.Usuario;

public class UsuarioFacade {
	
	private Properties p;
	private Context c;
	
	public IUsuarioServiceLocal service;
	
	public UsuarioFacade() throws NamingException {
		
		p = new Properties();
		c = new InitialContext(p);
		service = (IUsuarioServiceLocal)c.lookup("java:global/mynerpEAR/mynerpEJB/UsuarioService");
		
	}
	
	public Usuario pesquisar(String userName, String password) throws UsuarioInexistenteException {
		return service.pesquisar(userName, password);
	};

	public Usuario pesquisar(int id) throws UsuarioInexistenteException {
		return service.pesquisar(id);
	};
	
	public List<Usuario> pesquisar(Integer position, Integer max) throws UsuarioInexistenteException {
		return service.pesquisar(position, max);
	}
	
	public void cadastrar (Usuario usuario) throws UsuarioInexistenteException {
		service.cadastrar(usuario);
	}
	
	public void atualizar (Usuario usuario) throws UsuarioInexistenteException {
		service.atualizar(usuario);
	}
	
	public void remover (Usuario usuario) throws UsuarioInexistenteException {
		service.remover(usuario);
	}
	
	public JsonObject usuarioJson(Usuario usuario) throws FalhaAoCriarJSONException {		
		return service.usuarioJson(usuario);		
	}
	
	public boolean possuiPermissaoMenu(Empresa empresa,Integer idMenu, Usuario usuario) {
		return service.possuiPermissaoMenu(empresa, idMenu, usuario);
	}

}
