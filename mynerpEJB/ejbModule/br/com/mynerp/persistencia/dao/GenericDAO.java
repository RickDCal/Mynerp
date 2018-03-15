package br.com.mynerp.persistencia.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import br.com.mynerp.persistencia.Empresa;
import br.com.mynerp.persistencia.dao.exception.ObjetoNaoEncontradoException;

public class GenericDAO {

	@PersistenceContext(unitName = "transcal")

	protected EntityManager entityManager; // protected está acessível a classes do mesmo pacote e classes que herdam desta

	@PersistenceUnit(unitName = "transcal")

	protected EntityManagerFactory entityManagerFactory;


	public <T> Object obter(Class<T> classe, int id) throws ObjetoNaoEncontradoException {		
		Object entity  =  entityManager.find(classe, id);
		//if (entity == null) throw new ObjetoNaoEncontradoException();
		return entity;
	}

	public <E, T> List<E> obter (Class<T> classe, Integer position, Integer max, Empresa empresa) throws ObjetoNaoEncontradoException {

		String className = classe.getSimpleName();
		String consulta = "select o from " + className + " o";
		Integer idEmpresa = null;

		if (empresa != null) {
			idEmpresa = empresa.getId();
			if (idEmpresa != null) {				

				try {
					if (classe.getField("empresa") != null) {
						consulta = consulta + " where o.empresa.id = :idEmpresa";
					} 
				} catch (Exception e) {
					consulta = consulta + " where o.idEmpresa = :idEmpresa";
				} finally {

				}
			}
		}

		Query query = entityManager.createQuery(consulta);		

		if (position != null) {
			query.setFirstResult(position);	
		}
		if (max != null) {
			query.setMaxResults(max);
		}		

		List<E> lista = query.getResultList();		
		return lista;	

	}
	
	public <T> Object obter (Class<T> classe, String clientIdProperty) throws ObjetoNaoEncontradoException {

		String className = classe.getSimpleName();
		StringBuilder qryBuilder = new StringBuilder().append("select o from ");
		qryBuilder.append(className);
		qryBuilder.append(" o where o.clientIdProperty = :clientIdProperty ");
		Query query = entityManager.createQuery(qryBuilder.toString());
		query.setParameter("clientIdProperty", clientIdProperty);
		return query.getSingleResult();
	}

	public <T>Integer totalRegistros (Class<T> classe, Empresa empresa) throws ObjetoNaoEncontradoException {
		
		//		Integer total = this.obter(classe, 0, null, empresa).size();
		//		return total;
		Integer total = null;
		
		String className = classe.getSimpleName();
		String consulta = "select count(o) from " + className + " o";
		Integer idEmpresa = null;

		if (empresa != null) {
			idEmpresa = empresa.getId();
			if (idEmpresa != null) {				

				try {
					if (classe.getField("empresa") != null) {
						consulta = consulta + " where o.empresa.id = :idEmpresa";
					} 
				} catch (Exception e) {
					//consulta = consulta + " where o.idEmpresa = :idEmpresa";
				} finally {

				}
			}
		}

		Query query = entityManager.createQuery(consulta);				

		total = ((Long) query.getSingleResult()).intValue();
		return total;
	}

	public void remover (Object entity) {
		entityManager.remove(entityManager.contains(entity)? entity : entityManager.merge(entity)); 
		entityManager.flush(); 
	}
	
	public <T>void removerPorId (Class<T> classe, String id) {
		//coloquei o Id já como string para ficar flexível.
		Object entity = null;
		
		try {
			Integer idInt = Integer.parseInt(id);
			entity  =  entityManager.find(classe, idInt);			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			entity  =  entityManager.find(classe, id);
		}		
		
		entityManager.remove(entityManager.contains(entity)? entity : entityManager.merge(entity)); 
		entityManager.flush(); 
	}

	public Object inserir (Object entity) {
		entityManager.persist(entity);
		entityManager.flush();		
		return entity;
	}

	public Object alterar (Object entity) {
		entityManager.merge(entity);
		entityManager.flush();		
		return entity;
	}

	public <E, T> List<E> obter (Class<T> classe, String nome, Integer position, Integer max) {

		String className = classe.getSimpleName();
		String consulta = "select o from " + className + " o where o.nome like '%" + nome + "%' order by nome asc";
		Query query = entityManager.createQuery(consulta);	

		//query.setParameter("nome", nome);

		if (position != null) {
			query.setFirstResult(position);	
		}
		if (max != null) {
			query.setMaxResults(max);
		}		

		List<E> lista = query.getResultList();		
		return lista;	

	}

}

















//package br.com.mynerp.persistencia.dao;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.PersistenceContext;
//import javax.persistence.PersistenceUnit;
//
//public class GenericDAO {
//	
//	@PersistenceContext(unitName = "transcal")
//
//	protected EntityManager entityManager;
//	
//	@PersistenceUnit(unitName = "transcal")
//	
////	@PersistenceContext(unitName = "mynerp")
////
////	protected EntityManager entityManager;
////	
////	@PersistenceUnit(unitName = "mynerp")
////	
//	protected EntityManagerFactory entityManagerFactory;
//
//}
