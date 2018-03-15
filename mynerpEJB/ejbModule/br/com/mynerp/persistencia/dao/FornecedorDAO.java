package br.com.mynerp.persistencia.dao;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.com.mynerp.persistencia.Contato;
import br.com.mynerp.persistencia.Fornecedor;
import br.com.mynerp.persistencia.dao.exception.ContatoNaoEncontradoException;
import br.com.mynerp.persistencia.dao.exception.FornecedorNaoEncontradoException;


@Stateless
public class FornecedorDAO extends GenericDAO implements IFornecedorDAO {

	/**
	 * 
	 */

	public FornecedorDAO() {

	}

	public List<Fornecedor> obter () throws FornecedorNaoEncontradoException { //coloquei o tipo de lista pra ver no que dá

		Query query = entityManager.createQuery("select f from Fornecedor f order by f.nome");

		List<Fornecedor> list = (List<Fornecedor>)query.getResultList(); //fiquei com dúvida ao colocar o Generics de lista aqui

		if (list == null || list.size() == 0)
			throw new FornecedorNaoEncontradoException();

		return list;

	}

	public Fornecedor obter (int idpessoa) throws FornecedorNaoEncontradoException {
		Fornecedor fornecedor = entityManager.find(Fornecedor.class, idpessoa);
		if (fornecedor == null) throw new FornecedorNaoEncontradoException();
		return fornecedor;
	}


	public List<Fornecedor> obter (Integer position, Integer max, String nome) throws FornecedorNaoEncontradoException {

		if (position == null) {
			position = 0;
		}		
		
		String consulta = "select f from Fornecedor f where f.id is not null";
		String parametros = "";
		String order = " order by f.nome"; 

		if (nome != null && nome != ""){
			parametros = parametros + " and (lower(f.nome) like lower(:nome) or lower(f.nomeFantasia) like lower(:nome))";
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

		List<Fornecedor> list = (List<Fornecedor>)query.getResultList(); // consultando primeiro pra ver qtos tem no total

		return list;

	}	


	public Fornecedor obter(String usuario, String senha) throws FornecedorNaoEncontradoException {

		Query query = entityManager.createQuery("SELECT f FROM Fornecedor f WHERE f.usuario = :usuario and f.senha = :senha");
		query.setParameter("usuario", usuario); //este usuario entre aspas é o :usuario e o depois da vírgula é a propriedade da classe
		query.setParameter("senha", senha);

		Fornecedor fornecedor = (Fornecedor)query.getSingleResult();

		if (fornecedor == null)
			throw new FornecedorNaoEncontradoException();

		return fornecedor;
	}

	public List<Fornecedor> obter(Date dataInicial, Date dataFinal, String cidade) throws FornecedorNaoEncontradoException {

		String consulta = "select f from Fornecedor f where f.id is not null";
		String parametros = "";		

		if (dataInicial != null) {
			parametros = parametros + " and f.dataCadastro >= :dataInicial";	
		}
		if (dataFinal != null) {
			parametros = parametros + " and f.dataCadastro <= :dataFinal";
		}

		if (cidade != null && cidade != "") {
			parametros = parametros + " and f.id in (select p.id from Endereco e join e.pessoa p where lower(e.nomeCidade) like :cidade)" ;
		}

		consulta = consulta +  parametros + " order by f.nome";

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


		List<Fornecedor> list = (List<Fornecedor>)query.getResultList(); //fiquei com dúvida ao colocar o Generics de lista aqui

		//if (list == null || list.size() == 0)   // não quero tratar esta exceção, qdo resultar nada vou mostrar o grid vazio
		//	throw new ColetaNaoEncontradaException();

		return list;

	}	

	public Fornecedor obter(String cpfCnpj) throws FornecedorNaoEncontradoException {

		Query query = entityManager.createQuery("SELECT f FROM Fornecedor f WHERE f.cpfCnpj = :cpfCnpj");
		query.setParameter("cpfCnpj", cpfCnpj); //este usuario entre aspas é o :usuario e o depois da vírgula é a propriedade da classe

		Fornecedor fornecedor = (Fornecedor)query.getSingleResult();

		if (fornecedor == null)
			throw new FornecedorNaoEncontradoException();

		return fornecedor;
	}
	
	public List<Fornecedor> obterCnpj(String cpfCnpj, Integer position, Integer max) throws FornecedorNaoEncontradoException {

		Query query = entityManager.createQuery("SELECT f FROM Fornecedor f WHERE f.cpfCnpj = :cpfCnpj order by f.id desc");
		query.setParameter("cpfCnpj", cpfCnpj); //este usuario entre aspas é o :usuario e o depois da vírgula é a propriedade da classe

		if (max != null) {
			query.setMaxResults(max);
		}
		
		if (position != null) {
			query.setFirstResult(position);
		}
		
		List<Fornecedor> list = (List<Fornecedor>)query.getResultList(); // consultando primeiro pra ver qtos tem no total

		return list;
	}

	public Contato obterContatoPrincipal (Integer idPessoa) throws ContatoNaoEncontradoException {

		Query query = entityManager.createQuery("SELECT c FROM Contato c WHERE c.id = :idPessoa and c.contatoPrincipal = 1");
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


	public Fornecedor inserir (Fornecedor fornecedor) {
		entityManager.persist(fornecedor);
		entityManager.flush(); // para inserir na mesma hora que gera na memória
		return fornecedor;
	}

	public Fornecedor alterar (Fornecedor fornecedor) {
		entityManager.merge(fornecedor);
		entityManager.flush(); 
		return fornecedor;
	}

	public void remover (Fornecedor fornecedor) {
		//entityManager.merge(fornecedor); a linha de baixo antes era assim mas dava o erro -> Removing a detached instance... 
		entityManager.remove(entityManager.contains(fornecedor)? fornecedor : entityManager.merge(fornecedor)); // não sei bem o que faz aqui mas funcionou
		entityManager.flush(); 

	}


}
