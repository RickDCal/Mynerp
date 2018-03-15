package br.com.mynerp.persistencia.dao;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import br.com.mynerp.persistencia.Contato;
import br.com.mynerp.persistencia.Empresa;
import br.com.mynerp.persistencia.Fornecedor;
import br.com.mynerp.persistencia.dao.exception.ContatoNaoEncontradoException;
import br.com.mynerp.persistencia.dao.exception.FornecedorNaoEncontradoException;
import br.com.mynerp.persistencia.dao.exception.ObjetoNaoEncontradoException;


@Local
public interface IFornecedorDAO {

	public List<Fornecedor> obter() throws FornecedorNaoEncontradoException;
	
	public List<Fornecedor> obter(Date dataInicial, Date dataFinal, String cidade) throws FornecedorNaoEncontradoException;

	public Fornecedor obter(int idpessoa) throws FornecedorNaoEncontradoException;
	
	public List<Fornecedor> obter(Integer position, Integer max,String letra)	throws FornecedorNaoEncontradoException;
	
	public Fornecedor obter(String usuario, String senha) throws FornecedorNaoEncontradoException;
	
	public Fornecedor obter(String cpfCnpj) throws FornecedorNaoEncontradoException;
	
	public List<Fornecedor> obterCnpj(String cpfCnpj, Integer position, Integer max) throws FornecedorNaoEncontradoException;

	public Fornecedor inserir(Fornecedor fornecedor);

	public Fornecedor alterar(Fornecedor fornecedor);

	public void remover(Fornecedor fornecedor);
	
	public <T>Integer totalRegistros (Class<T> classe, Empresa empresa) throws ObjetoNaoEncontradoException;
	
	public Contato obterContatoPrincipal (Integer idPessoa) throws ContatoNaoEncontradoException;

}