package br.com.mynerp.persistencia.dao;

import java.util.List;

import javax.ejb.Local;

import br.com.mynerp.persistencia.Empresa;
import br.com.mynerp.persistencia.dao.exception.ObjetoNaoEncontradoException;

@Local
public interface IGenericDAO {

	public <T> Object obter(Class<T> classe, int id) throws ObjetoNaoEncontradoException;

	public <E, T> List<E> obter(Class<T> classe, Integer position, Integer max, Empresa empresa) throws ObjetoNaoEncontradoException;
	
	public <E, T> List<E> obter(Class<T> classe, String nome, Integer position, Integer max);
	
	public <T> Object obter (Class<T> classe, String clientIdProperty) throws ObjetoNaoEncontradoException;

	public <T> Integer totalRegistros(Class<T> classe, Empresa empresa) throws ObjetoNaoEncontradoException;	

	public Object inserir(Object entity);

	public Object alterar(Object entity);
	
	public void remover(Object entity);
	
	public <T>void removerPorId (Class<T> classe, String id);

}