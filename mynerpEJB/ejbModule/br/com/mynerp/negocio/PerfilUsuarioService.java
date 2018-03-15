package br.com.mynerp.negocio;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.google.gson.JsonObject;

import br.com.mynerp.negocio.exception.FalhaAoCriarJSONException;
import br.com.mynerp.negocio.exception.PerfilUsuarioInexistenteException;
import br.com.mynerp.persistencia.PerfilUsuario;
import br.com.mynerp.persistencia.dao.IPerfilUsuarioDAO;
import br.com.mynerp.persistencia.dao.exception.PerfilUsuarioNaoEncontradoException;

@Stateless
public class PerfilUsuarioService implements IPerfilUsuarioServiceLocal  {
	
	@EJB
	private IPerfilUsuarioDAO perfilUsuarioDao;

	public PerfilUsuarioService() {
		
	}	
	
	public void cadastrar (PerfilUsuario perfil) throws PerfilUsuarioNaoEncontradoException {		
		 perfilUsuarioDao.inserir(perfil);
	}

	public void remover (PerfilUsuario perfil) throws PerfilUsuarioNaoEncontradoException {		
		perfilUsuarioDao.remover(perfil);
	}
	
	public void atualizar (PerfilUsuario perfil) throws PerfilUsuarioNaoEncontradoException {		
		perfilUsuarioDao.alterar(perfil);
	}
	
	public PerfilUsuario pesquisar (int id) throws PerfilUsuarioNaoEncontradoException, PerfilUsuarioInexistenteException {
		try {
			return perfilUsuarioDao.obter(id);
		} catch (PerfilUsuarioNaoEncontradoException e) {
			throw new PerfilUsuarioInexistenteException();
		}
	}
	
	public List<PerfilUsuario> pesquisar (boolean isMenuEager) throws PerfilUsuarioNaoEncontradoException, PerfilUsuarioInexistenteException {
		
		try {
			return perfilUsuarioDao.obter(isMenuEager);
		} catch (PerfilUsuarioNaoEncontradoException e) {
			throw new PerfilUsuarioInexistenteException();
		}
	}
	
	public PerfilUsuario pesquisar (int idPerfil, boolean isMenuEager) throws PerfilUsuarioNaoEncontradoException, PerfilUsuarioInexistenteException {
				
		try {
			return perfilUsuarioDao.obter(idPerfil, isMenuEager);
		} catch (PerfilUsuarioNaoEncontradoException e) {
			throw new PerfilUsuarioInexistenteException();
		}
	}
	
	public List<PerfilUsuario> pesquisar() throws PerfilUsuarioNaoEncontradoException, PerfilUsuarioInexistenteException {
		
		try {
			return perfilUsuarioDao.obter();
		} catch (PerfilUsuarioNaoEncontradoException e) {
			throw new PerfilUsuarioInexistenteException();
		}		
	}
	
	public JsonObject perfilUsuarioJson(PerfilUsuario perfilUsuario) throws FalhaAoCriarJSONException {

		JsonObject perfilUsuarioJson = new JsonObject();
		perfilUsuarioJson.addProperty("id", perfilUsuario.getId());
		perfilUsuarioJson.addProperty("name", perfilUsuario.getNome());
		return perfilUsuarioJson;

	}
	
}
