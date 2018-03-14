package br.com.mynerp.apresentacao.facade.cadastro;

import java.util.List;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.com.mynerp.negocio.IContatoServiceLocal;
import br.com.mynerp.negocio.exception.ContatoInexistenteException;
import br.com.mynerp.persistencia.Contato;
import br.com.mynerp.persistencia.Pessoa;

public class ContatoFacade {
	
	private Properties p;
	private Context c;
	
	public IContatoServiceLocal service;
	
	public ContatoFacade() throws NamingException {
		
		p = new Properties();
		c = new InitialContext(p);
		service = (IContatoServiceLocal)c.lookup("java:global/mynerpEAR/mynerpEJB/ContatoService");
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Contato> pesquisar (Integer idPessoa) throws ContatoInexistenteException {
		return service.pesquisar(idPessoa);
	}
	
	public Contato pesquisarPrincipal (Integer idPessoa) throws ContatoInexistenteException {
		return service.pesquisarPrincipal(idPessoa);
	}
	
	public Integer pesquisarMaxSequencial (Integer idPessoa) throws ContatoInexistenteException {
		return service.pesquisarMaxSequencial(idPessoa);
	}

	public void atualizar (Contato contato) throws ContatoInexistenteException {
		service.atualizar(contato);
	}
	
	public void cadastrar (Contato contato) throws ContatoInexistenteException {
		service.cadastrar(contato);
	}
	
	public void remover (Contato contato) throws ContatoInexistenteException {
		service.remover(contato);
	}	
	
	public void remover (Pessoa pessoa) throws ContatoInexistenteException {
		service.remover(pessoa);
	}
	
}
