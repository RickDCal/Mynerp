package br.com.mynerp.negocio;



import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import br.com.mynerp.negocio.exception.FalhaAoCriarJSONException;
import br.com.mynerp.persistencia.Empresa;
import br.com.mynerp.persistencia.dao.IGenericDAO;
import br.com.mynerp.persistencia.dao.exception.ObjetoNaoEncontradoException;



@Stateless
public class GenericService implements IGenericServiceLocal  {

	@EJB
	private IGenericDAO genericDao;

	public GenericService() {

	}
	
	public <T> Object obter(Class<T> classe, int id) throws ObjetoNaoEncontradoException {
		try {
			return genericDao.obter(classe, id);
		} catch (ObjetoNaoEncontradoException e) {
			throw new ObjetoNaoEncontradoException();
		}		
	}

	public <E, T> List<E> obter(Class<T> classe, Integer position, Integer max, Empresa empresa) throws ObjetoNaoEncontradoException {
		try {
			return genericDao.obter(classe, position, max, empresa);
		} catch (ObjetoNaoEncontradoException e) {
			throw new ObjetoNaoEncontradoException();
		}
	}
	
	public <T> Object obter (Class<T> classe, String clientIdProperty) throws ObjetoNaoEncontradoException {
		try {
			return genericDao.obter(classe, clientIdProperty);
		} catch (ObjetoNaoEncontradoException e) {
			throw new ObjetoNaoEncontradoException();
		}
	}

	public <T> Integer totalRegistros(Class<T> classe, Empresa empresa) throws ObjetoNaoEncontradoException {
		try {
			return genericDao.totalRegistros(classe, empresa);
		} catch (ObjetoNaoEncontradoException e) {
			throw new ObjetoNaoEncontradoException();
		}
	}

	public void remover(Object entity) {
		try {
			genericDao.remover(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public <T>void removerPorId (Class<T> classe, String id) {
		try {
			genericDao.removerPorId(classe , id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Object inserir(Object entity) throws ObjetoNaoEncontradoException {
		return genericDao.inserir(entity);
	}

	public Object alterar(Object entity) throws ObjetoNaoEncontradoException {
		return genericDao.alterar(entity);
	}

	public <E, T> List<E> obter(Class<T> classe, String nome, Integer position, Integer max) throws ObjetoNaoEncontradoException {
		return genericDao.obter(classe, nome, position, max);
	}
	
	public JsonObject objetoJson(Object objeto, boolean exposeAnnotation) throws FalhaAoCriarJSONException {
		// um parametro qualquer pode ser recebido aqui e gerar um objeto json desde que não precise ser modificado
		Gson gson = new Gson();
		
		if (exposeAnnotation) {
			final GsonBuilder gBuilder = new GsonBuilder();
			gBuilder.excludeFieldsWithoutExposeAnnotation();
			gson = gBuilder.create();
		}		
		JsonParser jp = new JsonParser();
		JsonObject jo = (JsonObject) jp.parse(gson.toJson(objeto));
		return jo;		
	}

}



