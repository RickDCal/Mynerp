package br.com.mynerp.negocio;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.mynerp.negocio.exception.FalhaAoCriarJSONException;
import br.com.mynerp.negocio.exception.UsuarioInexistenteException;
import br.com.mynerp.persistencia.Empresa;
import br.com.mynerp.persistencia.Usuario;
import br.com.mynerp.persistencia.dao.IUsuarioDAO;
import br.com.mynerp.persistencia.dao.exception.ObjetoNaoEncontradoException;
import br.com.mynerp.persistencia.dao.exception.UsuarioNaoEncontradoException;

import com.google.gson.JsonObject;

@Stateless
public class UsuarioService implements IUsuarioServiceLocal {

	@EJB
	private IUsuarioDAO usuarioDao;

	public UsuarioService() {

	}	

	public Usuario pesquisar(String userName, String password) throws UsuarioInexistenteException {

		try {				
			return usuarioDao.obter(userName, password);
		} catch (UsuarioNaoEncontradoException e) {
			throw new UsuarioInexistenteException();
		}		

	} 

	public Usuario pesquisar (int id) throws  UsuarioInexistenteException {


		try {
			return usuarioDao.obter(id);				
		}catch (UsuarioNaoEncontradoException e){
			throw new UsuarioInexistenteException();				
		}

	} 

	public List<Usuario> pesquisar(Integer position, Integer max) throws UsuarioInexistenteException {

		try {
			return usuarioDao.obter(position, max);
		} catch (UsuarioNaoEncontradoException e) {
			throw new UsuarioInexistenteException();
		}		

	}

	public void cadastrar (Usuario usuario) {	

		usuarioDao.inserir(usuario);		
	}  

	public void atualizar (Usuario usuario) throws UsuarioInexistenteException {

		usuarioDao.alterar(usuario);		
	} 

	public void remover (Usuario usuario) throws UsuarioInexistenteException {

		usuarioDao.remover(usuario);		
	} 

	public JsonObject usuarioJson(Usuario usuario) throws FalhaAoCriarJSONException {

		JsonObject usuarioJson = new JsonObject();

		usuarioJson.addProperty("id", usuario.getId());
		usuarioJson.addProperty("nomePessoa", usuario.getApelido());
		usuarioJson.addProperty("user", usuario.getUserName());
		usuarioJson.addProperty("apelido", usuario.getApelido());		
		usuarioJson.addProperty("email", usuario.getEmail());
		if (usuario.getNomeArquivoFoto() == null) {
			usuario.setNomeArquivoFoto("undefinedUser.png");
		}
		usuarioJson.addProperty("nomeArquivoFoto", usuario.getNomeArquivoFoto());

		usuarioJson.addProperty("idPerfil", usuario.getPerfil().getId());

		return usuarioJson;

	}
	
	public Integer totalRegistros (Empresa empresa) throws ObjetoNaoEncontradoException {
		return usuarioDao.totalRegistros(Usuario.class, empresa);		
	}
	
	public boolean possuiPermissaoMenu(Empresa empresa,Integer idMenu, Usuario usuario) {
		return usuarioDao.possuiPermissaoMenu(empresa, idMenu, usuario);
	}

}
