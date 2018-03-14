package br.com.mynerp.negocio;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.mynerp.negocio.exception.ContatoInexistenteException;
import br.com.mynerp.persistencia.Contato;
import br.com.mynerp.persistencia.Pessoa;
import br.com.mynerp.persistencia.dao.IContatoDAO;
import br.com.mynerp.persistencia.dao.exception.ContatoNaoEncontradoException;

@Stateless
public class ContatoService implements IContatoServiceLocal {
	
	@EJB
	private IContatoDAO contatoDao; 

	public ContatoService() {
		
	}	

	public List pesquisar (Integer idPessoa) throws ContatoInexistenteException {		
		
		try {
			return contatoDao.obter(idPessoa);				
		}catch (ContatoNaoEncontradoException e){
			throw new ContatoInexistenteException();				
		}
	}	
	
	public Contato pesquisarPrincipal (Integer idPessoa) throws ContatoInexistenteException {		
	
			try {
				return contatoDao.obterPrincipal(idPessoa);				
			}catch (ContatoNaoEncontradoException e){
				throw new ContatoInexistenteException();				
			}
		} 


	public Integer pesquisarMaxSequencial (Integer idPessoa) throws ContatoInexistenteException {

			try {
				return contatoDao.obterMaxSequencial(idPessoa);				
			}catch (ContatoNaoEncontradoException e){
				throw new ContatoInexistenteException();				
			}
		} 

	public void cadastrar (Contato contato) throws ContatoInexistenteException {
	
		contatoDao.inserir((Contato)contato);
		
	}	

	public void atualizar (Contato contato) throws ContatoInexistenteException {
	
			
			contatoDao.alterar((Contato)contato);
		
		} 
	
	
	public void remover (Contato contato) throws ContatoInexistenteException {

			contatoDao.remover((Contato)contato);
		
		} 
	
	public void remover (Pessoa pessoa) throws ContatoInexistenteException {

		contatoDao.remover((Pessoa)pessoa);
	}
	
	
	
}
		
	

