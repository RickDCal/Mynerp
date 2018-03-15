package br.com.mynerp.negocio;

import java.util.List;

import javax.ejb.Local;

import br.com.mynerp.negocio.exception.ContatoInexistenteException;
import br.com.mynerp.persistencia.Contato;
import br.com.mynerp.persistencia.Pessoa;


@Local
public interface IContatoServiceLocal {

	public List pesquisar(Integer idPessoa) throws ContatoInexistenteException;

	public Contato pesquisarPrincipal(Integer idPessoa) throws ContatoInexistenteException;

	public Integer pesquisarMaxSequencial(Integer idPessoa)	throws ContatoInexistenteException;

	public void cadastrar(Contato contato) throws ContatoInexistenteException;

	public void atualizar(Contato contato) throws ContatoInexistenteException;

	public void remover(Contato contato) throws ContatoInexistenteException;
	
	public void remover (Pessoa pessoa) throws ContatoInexistenteException;

}