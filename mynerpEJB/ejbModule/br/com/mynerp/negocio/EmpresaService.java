package br.com.mynerp.negocio;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.mynerp.persistencia.Empresa;
import br.com.mynerp.persistencia.dao.IEmpresaDAO;
import br.com.mynerp.persistencia.dao.exception.EmpresaNaoEncontradaException;

@Stateless
public class EmpresaService implements IEmpresaServiceLocal   {
	
	@EJB
	private IEmpresaDAO empresaDao;

	public EmpresaService() {
		
	}		
	
	public Empresa pesquisar (int id) throws EmpresaNaoEncontradaException {
		
		try {
			return empresaDao.obter(id);
		} catch (EmpresaNaoEncontradaException e) {
			throw new EmpresaNaoEncontradaException();
		}
	}
	
	public List<Empresa> pesquisar () throws EmpresaNaoEncontradaException {
		
		try {
			return empresaDao.obter();
		} catch (EmpresaNaoEncontradaException e) {
			throw new EmpresaNaoEncontradaException();
		}	
		
	}
	
	 
	
	 

}
