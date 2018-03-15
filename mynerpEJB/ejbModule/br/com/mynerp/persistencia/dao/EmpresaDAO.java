package br.com.mynerp.persistencia.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.com.mynerp.persistencia.Empresa;
import br.com.mynerp.persistencia.dao.exception.EmpresaNaoEncontradaException;


@Stateless
public class EmpresaDAO extends GenericDAO implements IEmpresaDAO    {
	
	public EmpresaDAO() {
		
	}
	
	public  Empresa obter (int id) throws EmpresaNaoEncontradaException {
		Empresa empresa = entityManager.find(Empresa.class, id);
		if (empresa == null) throw new EmpresaNaoEncontradaException();
		return empresa;
	}
	
	
	
	public List<Empresa> obter () throws EmpresaNaoEncontradaException { 
		
		
		String consulta = "select e from Empresa e";			
		
		Query query = entityManager.createQuery(consulta);
		
		query.setHint("org.hibernate.timeout", 2000);
		
		List<Empresa> list = (List<Empresa>)query.getResultList(); 
			
		return list;
		
	}	
			

}
