package br.com.mynerp.persistencia.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.com.mynerp.negocio.exception.EnderecoInexistenteException;
import br.com.mynerp.persistencia.Endereco;
import br.com.mynerp.persistencia.Pessoa;


@Stateless
public class EnderecoDAO extends GenericDAO implements IEnderecoDAO {
	
	public EnderecoDAO() {
		
	}

	public List<Endereco> obter (Integer idPessoa) throws EnderecoInexistenteException { //coloquei o tipo de lista pra ver no que dá
		
		Query query = entityManager.createQuery("select e from Endereco e where e.pessoa.id = :idpessoa");
		query.setParameter("idpessoa", idPessoa);
		query.setHint("org.hibernate.timeout", 2000);
		
		List<Endereco> list = (List<Endereco>)query.getResultList(); //fiquei com dúvida ao colocar o Generics de lista aqui
		
		//if (list == null || list.size() == 0)
			//throw new EnderecoNaoEncontradoException();
			
		return list;
		
	}
	
	public Endereco obterPrincipal (Integer idPessoa) throws EnderecoInexistenteException {
		
//		if (entityManager == null) {
//			entityManager = entityManagerPublic;
//		}
		
		
		
		Query query = entityManager.createQuery("select e from Endereco e where e.indPadrao = 1 and e.pessoa.id = :idpessoa");
		query.setParameter("idpessoa", idPessoa);
		query.setHint("org.hibernate.timeout", 2000);
		query.setMaxResults(1);
		
		List<Endereco> lista = query.getResultList();
		if (lista.size() != 0) {
			Endereco endereco = (Endereco) query.getSingleResult();
			return endereco;
		} else return null;
		
		
	}
	
	public List<Endereco> obter (Integer idPessoa, Integer idTipo) throws EnderecoInexistenteException { //position é o registro inicial e max a quantidade de registro, pra escolher quantos mostrar na tela 
																								   //e apartir de qual.... muito útil em paginação igual cadastro de pedidos no supraweb 
		
		// consertar isso e colocar enum pro tipo
		
		Query query = entityManager.createQuery("select e from Endereco e where e.pessoa.id = :idPessoa and e.tipoEndereco = :idTipo");
		query.setParameter("idpessoa", idPessoa);
		query.setParameter("idTipo", idTipo);
		query.setHint("org.hibernate.timeout", 2000);		

		List<Endereco> list = (List<Endereco>)query.getResultList(); //fiquei com dúvida ao colocar o Generics de lista aqui
		
		//if (list == null || list.size() == 0)
		//	throw new EnderecoNaoEncontradoException();
			
		return list;
		
	}	
	
	public Endereco inserir (Endereco endereco) {
		entityManager.persist(endereco);
		entityManager.flush(); // para inserir na mesma hora que gera na memória
		return endereco;
	}
	
	public Endereco alterar (Endereco endereco) {
		entityManager.merge(endereco);
		entityManager.flush(); 
		return endereco;
	}
	
	public void remover (Endereco endereco) {
		//entityManager.remove(endereco);
		entityManager.remove(entityManager.contains(endereco)? endereco : entityManager.merge(endereco)); // não sei bem o que faz aqui mas funcionou
		entityManager.flush();
	}
	
public void remover (Pessoa pessoa) {
		
		Query query = entityManager.createQuery("select e from Endereco e where e.pessoa.id = :idpessoa");
		query.setParameter("idpessoa", pessoa.getId()); //este usuario entre aspas é o :usuario e o depois da vírgula é a propriedade da classe
		query.setHint("org.hibernate.timeout", 2000);
		query.setMaxResults(1);
				
			
		if (query.getResultList().size() > 0) {
			Endereco endereco = (Endereco) query.getSingleResult();
			while (endereco != null) {
				entityManager.remove(endereco);
				//entityManager.remove(entityManager.contains(contato)? contato : entityManager.merge(contato)); // não sei bem o que faz aqui mas funcionou
				entityManager.flush();	
				if (query.getResultList().size()!= 0) {
					endereco = (Endereco) query.getSingleResult();
				} else endereco = null;
			}
		}
	}


	
	
	

}
