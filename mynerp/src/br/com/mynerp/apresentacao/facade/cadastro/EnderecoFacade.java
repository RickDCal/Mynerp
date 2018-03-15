package br.com.mynerp.apresentacao.facade.cadastro;

import java.util.List;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.com.mynerp.negocio.IEnderecoServiceLocal;
import br.com.mynerp.negocio.exception.EnderecoInexistenteException;
import br.com.mynerp.persistencia.Endereco;
import br.com.mynerp.persistencia.Pessoa;

public class EnderecoFacade {
	
	private Properties p;
	private Context c;	
	public IEnderecoServiceLocal service;
	
	public EnderecoFacade() throws NamingException {
		
		p = new Properties();
		c = new InitialContext(p);
		service = (IEnderecoServiceLocal)c.lookup("java:global/mynerpEAR/mynerpEJB/EnderecoService");
		
	}
	

	@SuppressWarnings("rawtypes")
	public List pesquisar (Integer idPessoa) throws EnderecoInexistenteException {
		return service.pesquisar(idPessoa);
	}
	
	public Endereco pesquisarPrincipal (Integer idPessoa) throws EnderecoInexistenteException {
		return service.pesquisarPrincipal(idPessoa);
	}
	
	@SuppressWarnings("rawtypes")
	public List pesquisar (Integer idPessoa, Integer idTipo) throws EnderecoInexistenteException {
		return service.pesquisar(idPessoa, idTipo);
	}
	
	public void atualizar (Endereco endereco) throws EnderecoInexistenteException {
		service.atualizar(endereco);
	}
	
	public void cadastrar (Endereco endereco) throws EnderecoInexistenteException {
		service.cadastrar(endereco);
	}
		
	public void remover (Endereco endereco) throws EnderecoInexistenteException {
		service.remover(endereco);
	}
	
	public void remover (Pessoa pessoa) throws EnderecoInexistenteException {
		service.remover(pessoa); 
	}
	
	
	
}
