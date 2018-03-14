package br.com.mynerp.persistencia.dao;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import br.com.mynerp.persistencia.Cliente;
import br.com.mynerp.persistencia.Pessoa;
import br.com.mynerp.persistencia.dao.exception.ClienteNaoEncontradoException;
import br.com.mynerp.persistencia.dao.exception.PessoaNaoEncontradaException;


@Local
public interface IClienteDAO {

	public List<Cliente> obter() throws ClienteNaoEncontradoException;
	
	public List<Cliente> obter(Date dataInicial, Date dataFinal, String cidade) throws ClienteNaoEncontradoException;

	public Cliente obter(int idpessoa) throws ClienteNaoEncontradoException;
	
	public Pessoa obterPessoa (int idPessoa) throws PessoaNaoEncontradaException;
	
	public List<Cliente> obter(Integer position, Integer max,String letra)	throws ClienteNaoEncontradoException;
	
	public Cliente obter(String usuario, String senha) throws ClienteNaoEncontradoException;
	
	public Cliente obter(String cpfCnpj) throws ClienteNaoEncontradoException;
	
	public List<Cliente> obterCnpj(String cpfCnpj, Integer position, Integer max) throws ClienteNaoEncontradoException;

	public Cliente inserir(Cliente cliente);

	public Cliente alterar(Cliente cliente);

	public void remover(Cliente cliente);
	
}