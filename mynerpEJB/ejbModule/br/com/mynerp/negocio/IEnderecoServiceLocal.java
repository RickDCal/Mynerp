package br.com.mynerp.negocio;

import java.util.List;
import javax.ejb.Local;

import br.com.mynerp.negocio.exception.EnderecoInexistenteException;
import br.com.mynerp.persistencia.Endereco;
import br.com.mynerp.persistencia.Pessoa;

@Local
public interface IEnderecoServiceLocal {

	public List pesquisar(Integer idPessoa)	throws EnderecoInexistenteException;

	public List pesquisar(Integer idPessoa, Integer idTipo)	throws EnderecoInexistenteException;

	public Endereco pesquisarPrincipal(int idPessoa)throws EnderecoInexistenteException;

	public void cadastrar(Endereco endereco) throws EnderecoInexistenteException;

	public void atualizar(Endereco endereco) throws  EnderecoInexistenteException;

	public void remover(Endereco endereco) throws EnderecoInexistenteException;
	
	public void remover (Pessoa pessoa) throws EnderecoInexistenteException;

}