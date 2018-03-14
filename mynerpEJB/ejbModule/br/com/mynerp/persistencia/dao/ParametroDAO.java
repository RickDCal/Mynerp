package br.com.mynerp.persistencia.dao;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.com.mynerp.persistencia.Empresa;
import br.com.mynerp.persistencia.Parametro;
import br.com.mynerp.persistencia.dao.exception.ObjetoNaoEncontradoException;
import br.com.mynerp.persistencia.dao.exception.ParametroNaoEncontradoException;


@Stateless
public class ParametroDAO extends GenericDAO implements IParametroDAO    {

	public ParametroDAO() {

	}

	public  Parametro obter (Empresa empresa) throws ParametroNaoEncontradoException {

		String consulta = "Select p from Parametro p where p.empresa = :empresa";

		Query query = entityManager.createQuery(consulta);

		query.setParameter("empresa", empresa);	

		Parametro parametro = (Parametro)query.getSingleResult();

		if (parametro == null) throw new ParametroNaoEncontradoException();

		return parametro;
	}

	public <T> Object obterParametros(Class<T> classe, Parametro parametro) throws ObjetoNaoEncontradoException {
		
		String className = classe.getSimpleName();
		String consulta = "select o from " + className + " o where o.parametro.id = :idParametro";

		Query query = entityManager.createQuery(consulta);

		query.setParameter("idParametro", parametro.getId());	

		return query.getSingleResult();
	}




}
