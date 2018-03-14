package br.com.mynerp.negocio;


import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.mynerp.negocio.exception.EnderecoInexistenteException;
import br.com.mynerp.persistencia.Endereco;
import br.com.mynerp.persistencia.Pessoa;
import br.com.mynerp.persistencia.dao.IEnderecoDAO;



@Stateless
public class EnderecoService implements IEnderecoServiceLocal {
	
	@EJB
	private IEnderecoDAO enderecoDao;

	public EnderecoService() {
		
	}	
	
	public List pesquisar (Integer idPessoa) throws EnderecoInexistenteException {		
	
			try {
				return enderecoDao.obter(idPessoa);				
			}catch (EnderecoInexistenteException e){
				throw new EnderecoInexistenteException();				
			}
		} 
	
	public List pesquisar (Integer idPessoa, Integer idTipo) throws EnderecoInexistenteException {		
		
		try {
			return enderecoDao.obter(idPessoa, idTipo);				
		}catch (EnderecoInexistenteException e){
			throw new EnderecoInexistenteException();				
		}
	}
	
	public Endereco pesquisarPrincipal (int idPessoa) throws EnderecoInexistenteException {		
		
		try {
			return enderecoDao.obterPrincipal(idPessoa);				
		}catch (EnderecoInexistenteException e){
			throw new EnderecoInexistenteException();				
		}
	}

	public void cadastrar (Endereco endereco) throws EnderecoInexistenteException {
	
		enderecoDao.inserir((Endereco)endereco);
		
	}	

	public void atualizar (Endereco endereco) throws EnderecoInexistenteException {
	
			
			enderecoDao.alterar((Endereco) endereco);
		
		} 
	
	
	public void remover (Endereco endereco) throws EnderecoInexistenteException {

			enderecoDao.remover((Endereco) endereco);
		
		} 
	
	public void remover (Pessoa pessoa) throws EnderecoInexistenteException {

		enderecoDao.remover((Pessoa) pessoa);
	
	} 
	
}
		
	

