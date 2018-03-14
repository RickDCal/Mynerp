package br.com.mynerp.persistencia.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.com.mynerp.persistencia.Contato;
import br.com.mynerp.persistencia.Pessoa;
import br.com.mynerp.persistencia.dao.exception.ContatoNaoEncontradoException;


@Stateless
public class ContatoDAO extends GenericDAO implements IContatoDAO {
	
	public ContatoDAO() {
		
	}
	
	public List<Contato> obter (Integer idPessoa) throws ContatoNaoEncontradoException { //coloquei o tipo de lista pra ver no que dá
		
		Query query = entityManager.createQuery("select c from Contato c where c.pessoa.id = :idPessoa");
		query.setParameter("idPessoa", idPessoa);
		query.setHint("org.hibernate.timeout", 2000);
		
		List<Contato> list = (List<Contato>)query.getResultList(); //fiquei com dúvida ao colocar o Generics de lista aqui
		
		//if (list == null || list.size() == 0)
			//throw new ContatoNaoEncontradoException();
			
		return list;
		
	}	
	
public Contato obterPrincipal (Integer idPessoa) throws ContatoNaoEncontradoException {
		
		Query query = entityManager.createQuery("SELECT c FROM Contato c WHERE c.pessoa.id = :idPessoa and c.contatoPrincipal = 1");
		query.setParameter("idPessoa", idPessoa); //este usuario entre aspas é o :usuario e o depois da vírgula é a propriedade da classe
		query.setHint("org.hibernate.timeout", 2000);
		query.setMaxResults(1);
				
		List<Contato> lista = query.getResultList();
		
		Contato contatoPrincipal = null;
		
		if (lista.size()> 0) {
			contatoPrincipal = (Contato)query.getSingleResult();
		}
		
		return contatoPrincipal;				
	}
	
	//fazendo um teste aqui para obter ultimo sequencial
	public int obterMaxSequencial(Integer idPessoa) throws ContatoNaoEncontradoException {
		
		Query query = entityManager.createQuery("SELECT isnull(max(c.sequencial),0) sequencial FROM Contato c WHERE c.pessoa.id = :idPessoa");
		query.setParameter("idPessoa", idPessoa); //este usuario entre aspas é o :usuario e o depois da vírgula é a propriedade da classe
		
		int sequencial = (Integer) query.getSingleResult();
		
		return sequencial;
	}
	
	
	
	public Contato inserir (Contato contato) {
		entityManager.persist(contato);
		entityManager.flush(); // para inserir na mesma hora que gera na memória
		return contato;
	}
	
	public Contato alterar (Contato contato) {
		entityManager.merge(contato);
		entityManager.flush(); 
		return contato;
	}
	
	public void remover (Contato contato) {
		//entityManager.remove(Contato);
		entityManager.remove(entityManager.contains(contato)? contato : entityManager.merge(contato)); // não sei bem o que faz aqui mas funcionou
		entityManager.flush();
	}
	
	public void remover (Pessoa pessoa) {
		
		Query query = entityManager.createQuery("SELECT c FROM Contato c WHERE c.pessoa.id = :idPessoa");
		query.setParameter("idPessoa", pessoa.getId()); //este usuario entre aspas é o :usuario e o depois da vírgula é a propriedade da classe
		query.setHint("org.hibernate.timeout", 2000);
		query.setMaxResults(1);
				
			
		if (query.getResultList().size() > 0) {
			Contato contato = (Contato) query.getSingleResult();
			while (contato != null) {
				entityManager.remove(contato);
				//entityManager.remove(entityManager.contains(contato)? contato : entityManager.merge(contato)); // não sei bem o que faz aqui mas funcionou
				entityManager.flush();	
				if (query.getResultList().size()!= 0) {
					contato = (Contato) query.getSingleResult();
				} else contato = null;
			}
		}
	}

}
