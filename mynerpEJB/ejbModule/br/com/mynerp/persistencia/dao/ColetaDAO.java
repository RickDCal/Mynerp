package br.com.mynerp.persistencia.dao;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.com.mynerp.persistencia.Coleta;
import br.com.mynerp.persistencia.Pessoa;
import br.com.mynerp.persistencia.dao.exception.ColetaNaoEncontradaException;


@Stateless
public class ColetaDAO extends GenericDAO implements IColetaDAO {
	
	public ColetaDAO() {
		
	}

	public List<Coleta> obter () throws ColetaNaoEncontradaException { //coloquei o tipo de lista pra ver no que dá
		
		Query query = entityManager.createQuery("select c from Coleta c");
		query.setHint("org.hibernate.timeout", 2000);

		
		List<Coleta> list = (List<Coleta>)query.getResultList(); //fiquei com dúvida ao colocar o Generics de lista aqui
		
		if (list == null || list.size() == 0)
			throw new ColetaNaoEncontradaException();
			
		return list;
		
	}
	
	public Coleta obter (int id) throws ColetaNaoEncontradaException {
		Coleta coleta = entityManager.find(Coleta.class, id);
		if (coleta == null) throw new ColetaNaoEncontradaException();
		return coleta;
	}
	
	public List<Coleta> obter (Integer position, Integer max) throws ColetaNaoEncontradaException { //position é o registro inicial e max a quantidade de registro, pra escolher quantos mostrar na tela 
																								   //e apartir de qual.... muito útil em paginação igual cadastro de pedidos no supraweb 
		
		Query query = entityManager.createQuery("select c from Coleta c ");
		query.setHint("org.hibernate.timeout", 2000);
		
		if (position != null) query.setFirstResult(position);
		
		if (max != null) query.setMaxResults(max);
		
		List<Coleta> list = (List<Coleta>)query.getResultList(); //fiquei com dúvida ao colocar o Generics de lista aqui
		
		//if (list == null || list.size() == 0)   // não quero tratar esta exceção, qdo resultar nada vou mostrar o grid vazio
		//	throw new ColetaNaoEncontradaException();
			
		return list;
		
	}	
	
	public List<Coleta> obter (Date dataInicial, Date dataFinal, Pessoa pessoa, String nomeCliente, String nomeLocal, String idPagamento, Integer position, Integer max) throws ColetaNaoEncontradaException { //position é o registro inicial e max a quantidade de registro, pra escolher quantos mostrar na tela 
		   //e apartir de qual.... muito útil em paginação igual cadastro de pedidos no supraweb 

		String consulta = "select c from Coleta c ";// where c.id is not null ";
		
		Integer pagto = null;
		if (idPagamento != null) {
			pagto = Integer.parseInt(idPagamento);	
			consulta = consulta + "left join c.conta cr ";			
		} else pagto = 0;
		
		consulta = consulta + "where c.id is not null ";
		
		String parametros = "";		
		
		if (dataInicial != null) {
			parametros = parametros + " and c.data >= :dataInicial";	
		}
		if (dataFinal != null) {
			parametros = parametros + " and c.data <= :dataFinal";
		}
		if (pessoa != null) {
			parametros = parametros + " and c.cliente.id = :idPessoa";
		}
		if (nomeLocal != null && nomeLocal != "") {
			parametros = parametros + " and lower(c.nomeLocal) like :nomeLocal";
		}
		
		if (nomeCliente != null && nomeLocal !="") {
			parametros = parametros + " and (lower(c.cliente.nome) like :nomeCliente or lower(c.cliente.nome) like :nomeCliente)";
		}
				
		switch (pagto) {		
		case 2: parametros = parametros + " and cr.id in (select p.conta.id from Parcela p where p.dataQuitacao is not null)"; break;	
		case 1: parametros = parametros + " and (cr.id in (select p.conta.id from Parcela p where p.dataQuitacao is null) or cr is null)"; break;
		//case 2: parametros = parametros + " and c.conta.parcela.dataQuitacao is not null"; break;	
		//case 1: parametros = parametros + " and c.conta.parcela.dataQuitacao is null"; break;
		}
		
		
		if (parametros != "") {
			consulta = consulta + parametros;
		}
		
		Query query = entityManager.createQuery(consulta);
		
		if (dataInicial != null ) {
			query.setParameter("dataInicial", dataInicial);
		}
		if (dataFinal != null ) {
			query.setParameter("dataFinal", dataFinal);
		}
		if (pessoa != null) {
			query.setParameter("idPessoa", pessoa.getId());
		}
		if (nomeLocal != null && nomeLocal !="") {
			query.setParameter("nomeLocal",  "%" + nomeLocal.toLowerCase() + "%" );
		}
		if (nomeCliente != null && nomeCliente !="") {
			query.setParameter("nomeCliente",  "%" + nomeCliente.toLowerCase() + "%" );
		}
		
		query.setHint("org.hibernate.timeout", 2000);
				
		if (position != null) query.setFirstResult(position);

		if (max != null) query.setMaxResults(max);

		List<Coleta> list = (List<Coleta>)query.getResultList(); //fiquei com dúvida ao colocar o Generics de lista aqui

		//if (list == null || list.size() == 0)   // não quero tratar esta exceção, qdo resultar nada vou mostrar o grid vazio
		//	throw new ColetaNaoEncontradaException();

		return list;
	}	
		
	public Coleta inserir (Coleta coleta) {
		entityManager.persist(coleta);
		entityManager.flush(); // para inserir na mesma hora que gera na memória
		return coleta;
	}
	
	public Coleta alterar (Coleta coleta) {
		entityManager.merge(coleta);
		entityManager.flush(); 
		return coleta;
	}
	
	public void remover (Coleta coleta) {
		//entityManager.remove(coleta); a linha de baixo antes era assim mas dava o erro -> Removing a detached instance... 
		entityManager.remove(entityManager.contains(coleta)? coleta : entityManager.merge(coleta)); // não sei bem o que faz aqui mas funcionou
		entityManager.flush(); // para inserir na mesma hora que gera na memória
		
	}	

}

