package br.com.mynerp.persistencia.dao;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.com.mynerp.persistencia.Cliente;
import br.com.mynerp.persistencia.Pessoa;
import br.com.mynerp.persistencia.dao.exception.ClienteNaoEncontradoException;
import br.com.mynerp.persistencia.dao.exception.PessoaNaoEncontradaException;


@Stateless
public class ClienteDAO extends GenericDAO implements IClienteDAO {

	/**
	 * 
	 */

	public ClienteDAO() {

	}

	public List<Cliente> obter () throws ClienteNaoEncontradoException { //coloquei o tipo de lista pra ver no que dá

		Query query = entityManager.createQuery("select c from Cliente c order by c.nome");

		List<Cliente> list = (List<Cliente>)query.getResultList(); //fiquei com dúvida ao colocar o Generics de lista aqui

//		if (list == null || list.size() == 0)
//			throw new ClienteNaoEncontradoException();

		return list;

	}

	public Cliente obter (int id) throws ClienteNaoEncontradoException {
		Cliente cliente = entityManager.find(Cliente.class, id);
		//if (cliente == null) throw new ClienteNaoEncontradoException();
		return cliente;
	}

	public Pessoa obterPessoa (int id) throws PessoaNaoEncontradaException {
		Pessoa pessoa = entityManager.find(Pessoa.class, id);
		return pessoa;
	}

	public List<Cliente> obter (Integer position, Integer max, String nome) throws ClienteNaoEncontradoException {

		if (position == null) {
			position = 0;
		}		
		
		String consulta = "select c from Cliente c where c.id is not null";
		String parametros = "";
		String order = " order by c.nome"; 

		if (nome != null && nome != ""){
			parametros = parametros + " and (lower(c.nome) like lower(:nome) or lower(c.nomeFantasia) like lower(:nome))";
		} 

		consulta = consulta + parametros + order; 

		Query query = entityManager.createQuery(consulta);		

		if (nome != null && nome != ""){
			query.setParameter("nome", "%" + nome + "%");
		}

		query.setFirstResult(position);		
		if (max != null) {
			query.setMaxResults(max);
		}

		List<Cliente> list = (List<Cliente>)query.getResultList(); // consultando primeiro pra ver qtos tem no total

		return list;

	}	


	public Cliente obter(String usuario, String senha) throws ClienteNaoEncontradoException {

		Query query = entityManager.createQuery("SELECT c FROM Cliente c WHERE c.usuario = :usuario and c.senha = :senha");
		query.setParameter("usuario", usuario); //este usuario entre aspas é o :usuario e o depois da vírgula é a propriedade da classe
		query.setParameter("senha", senha);

		Cliente cliente = (Cliente)query.getSingleResult();

		if (cliente == null)
			throw new ClienteNaoEncontradoException();

		return cliente;
	}

	public List<Cliente> obter(Date dataInicial, Date dataFinal, String cidade) throws ClienteNaoEncontradoException {

		String consulta = "select c from Cliente c where c.id is not null";
		String parametros = "";		

		if (dataInicial != null) {
			parametros = parametros + " and c.dataCadastro >= :dataInicial";	
		}
		if (dataFinal != null) {
			parametros = parametros + " and c.dataCadastro <= :dataFinal";
		}

		if (cidade != null && cidade != "") {
			//consulta = consulta + " join c.enderecos e ";
			parametros = parametros + " and c.id in (select p.id from Endereco e join e.pessoa p where lower(e.nomeCidade) like :cidade)" ;
		}

		
		consulta = consulta + parametros + " order by c.nome";
		
		Query query = entityManager.createQuery(consulta);

		if (dataInicial != null ) {
			query.setParameter("dataInicial", dataInicial);
		}
		if (dataFinal != null ) {
			query.setParameter("dataFinal", dataFinal);
		}
		
		if (cidade != null && cidade !="") {
			query.setParameter("cidade",  "%" + cidade.toLowerCase() + "%" );
		}

		query.setHint("org.hibernate.timeout", 2000);


		List<Cliente> list = (List<Cliente>)query.getResultList(); //fiquei com dúvida ao colocar o Generics de lista aqui

		//if (list == null || list.size() == 0)   // não quero tratar esta exceção, qdo resultar nada vou mostrar o grid vazio
		//	throw new ColetaNaoEncontradaException();

		return list;

	}	

	public Cliente obter(String cpfCnpj) throws ClienteNaoEncontradoException {

		Query query = entityManager.createQuery("SELECT c FROM Cliente c WHERE c.cpfCnpj = :cpfCnpj");
		query.setParameter("cpfCnpj", cpfCnpj); //este usuario entre aspas é o :usuario e o depois da vírgula é a propriedade da classe

		Cliente cliente = (Cliente)query.getSingleResult();

		if (cliente == null)
			throw new ClienteNaoEncontradoException();

		return cliente;
	}
	
	public List<Cliente> obterCnpj(String cpfCnpj, Integer position, Integer max) throws ClienteNaoEncontradoException {

		Query query = entityManager.createQuery("SELECT c FROM Cliente c WHERE c.cpfCnpj = :cpfCnpj");
		query.setParameter("cpfCnpj", cpfCnpj); //este usuario entre aspas é o :usuario e o depois da vírgula é a propriedade da classe

		if (max != null) {
			query.setMaxResults(max);
		}
		
		if (position != null) {
			query.setFirstResult(position);
		}
		
		List<Cliente> list = (List<Cliente>)query.getResultList(); // consultando primeiro pra ver qtos tem no total

		return list;
	}

	public Cliente inserir (Cliente cliente) {
		entityManager.persist(cliente);
		entityManager.flush(); // para inserir na mesma hora que gera na memória
		return cliente;
	}

	public Cliente alterar (Cliente cliente) {
		entityManager.merge(cliente);
		entityManager.flush(); 
		return cliente;
	}

	public void remover (Cliente cliente) {
		//entityManager.merge(cliente); a linha de baixo antes era assim mas dava o erro -> Removing a detached instance... 
		entityManager.remove(entityManager.contains(cliente)? cliente : entityManager.merge(cliente)); // não sei bem o que faz aqui mas funcionou
		entityManager.flush(); 

	}


}
