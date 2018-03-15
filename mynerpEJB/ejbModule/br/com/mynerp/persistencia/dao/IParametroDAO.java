package br.com.mynerp.persistencia.dao;

import javax.ejb.Local;

import br.com.mynerp.persistencia.Empresa;
import br.com.mynerp.persistencia.Parametro;
import br.com.mynerp.persistencia.dao.exception.ObjetoNaoEncontradoException;
import br.com.mynerp.persistencia.dao.exception.ParametroNaoEncontradoException;

@Local
public interface IParametroDAO {

	public Parametro obter(Empresa empresa)	throws ParametroNaoEncontradoException;
	
	public <T> Object obterParametros(Class<T> classe, Parametro parametro) throws ObjetoNaoEncontradoException;
	

}