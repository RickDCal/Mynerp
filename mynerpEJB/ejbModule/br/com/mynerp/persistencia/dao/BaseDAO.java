package br.com.mynerp.persistencia.dao;

import javax.ejb.Stateless;

@Stateless
public class BaseDAO extends GenericDAO implements IGenericDAO {
/*CRIEI ESTA CLASSE APENAS PARA QUE A CLASSE GenericDAO NÃO TENHA QUE IMPLEMETAR DIRETAMENTE A INTERFACE IGenericDAO*/

	public BaseDAO() {
		
	}
	
	

}
