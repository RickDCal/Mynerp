package br.com.mynerp.negocio;

import java.util.List;

import javax.ejb.Local;

import com.google.gson.JsonObject;

import br.com.mynerp.negocio.exception.FalhaAoCriarJSONException;
import br.com.mynerp.persistencia.Empresa;
import br.com.mynerp.persistencia.dao.exception.ObjetoNaoEncontradoException;

@Local
public interface IGenericServiceLocal {

	public <T> Object obter(Class<T> classe, int id) throws ObjetoNaoEncontradoException;

	public <E, T> List<E> obter(Class<T> classe, Integer position, Integer max, Empresa empresa) throws ObjetoNaoEncontradoException;
	
	public <T> Object obter (Class<T> classe, String clientIdProperty) throws ObjetoNaoEncontradoException;

	public <T> Integer totalRegistros(Class<T> classe, Empresa empresa) throws ObjetoNaoEncontradoException;

	public <E, T> List<E> obter(Class<T> classe, String nome, Integer position, Integer max) throws ObjetoNaoEncontradoException;

	public Object inserir(Object entity) throws ObjetoNaoEncontradoException;

	public Object alterar(Object entity) throws ObjetoNaoEncontradoException;
	
	public JsonObject objetoJson(Object objeto, boolean exposeAnnotation) throws FalhaAoCriarJSONException;
	
	public void remover(Object entity);
	
	public <T>void removerPorId (Class<T> classe, String id);

}