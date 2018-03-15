package br.com.mynerp.apresentacao.facade.cadastro;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.google.gson.JsonObject;

import br.com.mynerp.negocio.IPessoaServiceLocal;
import br.com.mynerp.negocio.exception.ContatoInexistenteException;
import br.com.mynerp.negocio.exception.EnderecoInexistenteException;
import br.com.mynerp.negocio.exception.FalhaAoCriarJSONException;
import br.com.mynerp.negocio.exception.PessoaInexistenteException;
import br.com.mynerp.persistencia.Cliente;
import br.com.mynerp.persistencia.Fornecedor;
import br.com.mynerp.persistencia.Pessoa;
import br.com.mynerp.persistencia.dao.exception.ClienteNaoEncontradoException;
import br.com.mynerp.persistencia.dao.exception.ContatoNaoEncontradoException;
import br.com.mynerp.persistencia.dao.exception.FornecedorNaoEncontradoException;
import br.com.mynerp.persistencia.dao.exception.PessoaNaoEncontradaException;
import br.com.mynerp.persistencia.enumerate.PessoaEnum;

public class PessoaFacade {
	
	private Properties p; // coloquei o private por minha conta mas n�o precisava dele
	private Context c;
	
	private IPessoaServiceLocal service; //digo qual a interface que vou usar // aqui pode ter erro
	
	public PessoaFacade () throws NamingException{
		p = new Properties();
		c = new InitialContext(p);
		service = (IPessoaServiceLocal)c.lookup("java:global/mynerpEAR/mynerpEJB/PessoaService");
	}

	public void atualizar(Pessoa pessoa) throws PessoaInexistenteException {
		service.atualizar(pessoa);
	}
	
	public void cadastrar(Pessoa pessoa) throws PessoaInexistenteException {
		service.cadastrar(pessoa);
	}
	
	@SuppressWarnings("rawtypes")
	public List pesquisar(PessoaEnum pessoaEnum, Integer position, Integer max, String letra)throws PessoaNaoEncontradaException, PessoaInexistenteException {
		return service.pesquisar(pessoaEnum, position, max, letra);
	}
	
	public List<Cliente> pesquisar (PessoaEnum pessoaEnum, Date dataInicial, Date dataFinal, String cidade) throws ClienteNaoEncontradoException {
		return service.pesquisar(pessoaEnum,dataInicial, dataFinal, cidade);
	}
	
	public List<Fornecedor> pesquisarFornecedor (PessoaEnum pessoaEnum, Date dataInicial, Date dataFinal, String cidade) throws FornecedorNaoEncontradoException {
		return service.pesquisarFornecedor(pessoaEnum,dataInicial, dataFinal, cidade);
	}
	
	@SuppressWarnings("rawtypes")
	public List pesquisar(PessoaEnum pessoaEnum) throws PessoaNaoEncontradaException, PessoaInexistenteException {
		return service.pesquisar(pessoaEnum);
	}
	
	/*public List<Pessoa> pesquisar(PessoaEnum pessoaEnum) throws PessoaNaoEncontradaException, PessoaInexistenteException {
		return service.pesquisar(pessoaEnum);
	}*/
	
	public Pessoa pesquisar(PessoaEnum pessoaEnum, int id) throws PessoaNaoEncontradaException, PessoaInexistenteException {
		return service.pesquisar(pessoaEnum, id);
	}
	
	public Pessoa pesquisarPessoa (int idPessoa) throws PessoaNaoEncontradaException {
		return service.pesquisarPessoa(idPessoa);
	}
	
	public Pessoa pesquisar(PessoaEnum pessoaEnum, String usuario, String senha) throws PessoaNaoEncontradaException, PessoaInexistenteException {
		return service.pesquisar(pessoaEnum, usuario, senha);
	}
	
	public Pessoa pesquisar(PessoaEnum pessoaEnum, String cpfCnpj) throws PessoaNaoEncontradaException, PessoaInexistenteException {
		return service.pesquisar(pessoaEnum, cpfCnpj);
	}
	
	public List<Cliente> pesquisarClientesCnpj(String cpfCnpj, Integer position, Integer max) throws PessoaNaoEncontradaException, ClienteNaoEncontradoException {
		return service.pesquisarClientesCnpj(cpfCnpj, position, max);
	}
	
	public List<Fornecedor> pesquisarFornecedoresCnpj(String cpfCnpj, Integer position, Integer max) throws PessoaNaoEncontradaException, FornecedorNaoEncontradoException {
		return service.pesquisarFornecedoresCnpj(cpfCnpj, position, max);
	}
	
	public void remover(Pessoa pessoa) throws PessoaInexistenteException {
		service.remover(pessoa);
	}
	
	public Cliente pesquisar(String usuario, String senha) throws ClienteNaoEncontradoException, PessoaInexistenteException {
		return service.pesquisar(usuario, senha);
	}
	
	public Fornecedor pesquisarFornecedor(String usuario, String senha) throws FornecedorNaoEncontradoException, PessoaInexistenteException {
		return service.pesquisarFornecedor(usuario, senha);
	}
	
	public JsonObject pessoaJson(Pessoa pessoa) throws FalhaAoCriarJSONException, ContatoInexistenteException, PessoaInexistenteException, EnderecoInexistenteException, ContatoNaoEncontradoException {
		return service.pessoaJson(pessoa);
	}	
	
}
