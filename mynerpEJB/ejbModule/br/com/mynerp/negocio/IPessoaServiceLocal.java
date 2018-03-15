package br.com.mynerp.negocio;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import com.google.gson.JsonObject;

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


@Local
public interface IPessoaServiceLocal {

	public List<Pessoa> pesquisar(PessoaEnum pessoaEnum)throws PessoaNaoEncontradaException, PessoaInexistenteException;
	
	public List<Cliente> pesquisar (PessoaEnum pessoaEnum, Date dataInicial, Date dataFinal, String cidade) throws ClienteNaoEncontradoException;
	
	public List<Fornecedor> pesquisarFornecedor (PessoaEnum pessoaEnum, Date dataInicial, Date dataFinal, String cidade) throws FornecedorNaoEncontradoException;

	public List<Pessoa> pesquisar(PessoaEnum pessoaEnum, Integer position, Integer max, String letra)throws PessoaNaoEncontradaException, PessoaInexistenteException;

	public Pessoa pesquisar(PessoaEnum pessoaEnum, int idpessoa)throws PessoaNaoEncontradaException, PessoaInexistenteException;

	public Pessoa pesquisarPessoa (int idPessoa) throws PessoaNaoEncontradaException;
	
	public Pessoa pesquisar(PessoaEnum pessoaEnum, String usuario, String senha) throws PessoaNaoEncontradaException, PessoaInexistenteException;
	
	public Pessoa pesquisar(PessoaEnum pessoaEnum, String cpfCnpj) throws PessoaNaoEncontradaException, PessoaInexistenteException;
		
	public List<Cliente> pesquisarClientesCnpj(String cpfCnpj, Integer position, Integer max) throws PessoaNaoEncontradaException, ClienteNaoEncontradoException;
	
	public List<Fornecedor> pesquisarFornecedoresCnpj(String cpfCnpj, Integer position, Integer max) throws PessoaNaoEncontradaException, FornecedorNaoEncontradoException;
	
	public void cadastrar(Pessoa pessoa) throws PessoaInexistenteException;

	public void atualizar(Pessoa pessoa) throws PessoaInexistenteException;

	public void remover(Pessoa pessoa) throws PessoaInexistenteException;
	
	public Cliente pesquisar(String usuario, String senha) throws ClienteNaoEncontradoException, PessoaInexistenteException;
	
	public Fornecedor pesquisarFornecedor (String usuario, String senha) throws FornecedorNaoEncontradoException, PessoaInexistenteException;
	
	public JsonObject pessoaJson(Pessoa pessoa) throws FalhaAoCriarJSONException, ContatoInexistenteException, PessoaInexistenteException, EnderecoInexistenteException, ContatoNaoEncontradoException;

}