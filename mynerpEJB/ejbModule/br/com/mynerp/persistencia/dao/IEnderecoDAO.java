package br.com.mynerp.persistencia.dao;

import java.util.List;
import javax.ejb.Local;

import br.com.mynerp.negocio.exception.EnderecoInexistenteException;
import br.com.mynerp.persistencia.Endereco;
import br.com.mynerp.persistencia.Pessoa;

@Local
public interface IEnderecoDAO {
	
	public List<Endereco> obter(Integer idPessoa) throws EnderecoInexistenteException;
	
	public Endereco obterPrincipal (Integer idPessoa) throws EnderecoInexistenteException;
	
	public List<Endereco> obter(Integer idPessoa, Integer idTipo) throws EnderecoInexistenteException;

	public Endereco inserir(Endereco endereco);

	public Endereco alterar(Endereco endereco);

	public void remover(Endereco endereco);
	
	public void remover (Pessoa pessoa);

}