package br.com.mynerp.apresentacao.facade.estaticos;

import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.com.mynerp.negocio.IEmpresaServiceLocal;
import br.com.mynerp.persistencia.Empresa;
import br.com.mynerp.persistencia.dao.exception.EmpresaNaoEncontradaException;

public class EmpresaFacade {
	
	private Properties p;
	private Context c;
	
	public IEmpresaServiceLocal service;
	
	public EmpresaFacade() throws NamingException {
		
		p = new Properties();
		c = new InitialContext(p);
		service = (IEmpresaServiceLocal)c.lookup("java:global/mynerpEAR/mynerpEJB/EmpresaService");
		
	}
	
	public Empresa pesquisar(int id) throws EmpresaNaoEncontradaException {
		return service.pesquisar(id);
	}
	
	public List<Empresa> pesquisar() throws EmpresaNaoEncontradaException {
		return service.pesquisar();
	}	
		
	
}
