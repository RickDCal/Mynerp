package br.com.mynerp.persistencia.dao;

import java.util.List;

import javax.ejb.Local;

import br.com.mynerp.persistencia.Contato;
import br.com.mynerp.persistencia.Pessoa;
import br.com.mynerp.persistencia.dao.exception.ContatoNaoEncontradoException;

@Local
public interface IContatoDAO {

	public List<Contato> obter(Integer idPessoa) throws ContatoNaoEncontradoException;
	
	public Contato obterPrincipal(Integer idPessoa) throws ContatoNaoEncontradoException;

	public int obterMaxSequencial(Integer idPessoa) throws ContatoNaoEncontradoException;	

	public Contato inserir(Contato contato);

	public Contato alterar(Contato contato);

	public void remover(Contato contato);
	
	public void remover (Pessoa pessoa);

}