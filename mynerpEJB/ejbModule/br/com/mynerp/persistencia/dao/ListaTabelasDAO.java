package br.com.mynerp.persistencia.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.com.mynerp.persistencia.Cobranca;
import br.com.mynerp.persistencia.CondicaoPagamento;
import br.com.mynerp.persistencia.CondicaoParcela;
import br.com.mynerp.persistencia.dao.exception.ListaTabelasNaoEncontradoException;


@Stateless
public class ListaTabelasDAO extends GenericDAO implements IListaTabelasDAO{

	public ListaTabelasDAO() {

	}

	//Formas de Cobrança

	public List<Cobranca> obterCobrancas (Integer idPagarReceber) throws ListaTabelasNaoEncontradoException { //coloquei o tipo de lista pra ver no que dá

		String consulta = "select c from Cobranca c where c.id is not null";
		String parametros = "";

		//		if (idPagarReceber != null && idPagarReceber == 1) {
		//			parametros = parametros + " and c.isPagar = true ";
		//		}
		//		if (idPagarReceber != null && idPagarReceber == 2) {
		//			parametros = parametros + " and c.isReceber = true ";
		//		}
		//		
		consulta = consulta + parametros + " order by c.nome";


		Query query = entityManager.createQuery(consulta);
		query.setHint("org.hibernate.timeout", 2000);

		List<Cobranca> list = (List<Cobranca>)query.getResultList(); //fiquei com dúvida ao colocar o Generics de lista aqui

		//if (list == null || list.size() == 0)
		//throw new ContatoNaoEncontradoException();

		return list;

	}	

	public Cobranca obterCobranca (int id) throws ListaTabelasNaoEncontradoException {
		Cobranca cobranca = entityManager.find(Cobranca.class, id);
		if (cobranca == null) throw new ListaTabelasNaoEncontradoException();
		return cobranca;
	}

	public Cobranca inserirCobranca (Cobranca cobranca) {
		entityManager.persist(cobranca);
		entityManager.flush(); // para inserir na mesma hora que gera na memória
		return cobranca;
	}

	public Cobranca alterarCobranca (Cobranca cobranca) {
		entityManager.merge(cobranca);
		entityManager.flush(); 
		return cobranca;
	}

	public void removerCobranca (Cobranca cobranca) {
		//entityManager.remove(coleta); a linha de baixo antes era assim mas dava o erro -> Removing a detached instance... 
		entityManager.remove(entityManager.contains(cobranca)? cobranca : entityManager.merge(cobranca)); // não sei bem o que faz aqui mas funcionou
		entityManager.flush(); // para inserir na mesma hora que gera na memória

	}


	//Condições de Pagamento
	public List<CondicaoPagamento> obterCondicoesPagamento (Integer idPagarReceber, boolean isEager) throws ListaTabelasNaoEncontradoException { //coloquei o tipo de lista pra ver no que dá

		String consulta = "select c from CondicaoPagamento c where c.id is not null";
		String parametros = "";

		if (idPagarReceber != null && idPagarReceber == 1) {
			parametros = parametros + " and c.isPagar = true";
		}
		if (idPagarReceber != null && idPagarReceber == 2) {
			parametros = parametros + " and c.isReceber = true";
		}
		if (isEager) {
			consulta = "select c from CondicaoPagamento join fetch c.parcelas where c.id is not null"; //pra retornar as parcelas no objeto
		}

		consulta = consulta + parametros + " order by c.nome";

		Query query = entityManager.createQuery(consulta);
		query.setHint("org.hibernate.timeout", 2000);

		List<CondicaoPagamento> list = (List<CondicaoPagamento>)query.getResultList(); //fiquei com dúvida ao colocar o Generics de lista aqui

		//if (list == null || list.size() == 0)
		//throw new ContatoNaoEncontradoException();

		return list;

	}

	public CondicaoPagamento obterCondicaoPagamento (int id, boolean isEager) throws ListaTabelasNaoEncontradoException {
		
		CondicaoPagamento condicao = null;
		if (isEager) {
			Query query = entityManager.createQuery("select c from CondicaoPagamento c Join Fetch c.parcelas p where c.id = :idCondicao");
			query.setParameter("idCondicao", id);			
			condicao = (CondicaoPagamento) query.getSingleResult();
		} else {
			condicao = entityManager.find(CondicaoPagamento.class, id);
		}		

		if (condicao == null) throw new ListaTabelasNaoEncontradoException();
		return condicao;
	}

	public CondicaoPagamento inserirCondicaoPagamento (CondicaoPagamento condicao) {
		entityManager.persist(condicao);
		entityManager.flush(); // para inserir na mesma hora que gera na memória
		return condicao;
	}

	public CondicaoPagamento alterarCondicaoPagamento (CondicaoPagamento condicao) {
		entityManager.merge(condicao);
		entityManager.flush(); 
		return condicao;
	}

	public void removerCondicaoPagamento (CondicaoPagamento condicao) {
		//entityManager.remove(coleta); a linha de baixo antes era assim mas dava o erro -> Removing a detached instance... 
		entityManager.remove(entityManager.contains(condicao)? condicao : entityManager.merge(condicao)); // não sei bem o que faz aqui mas funcionou
		entityManager.flush(); // para inserir na mesma hora que gera na memória

	}
	
	public List<CondicaoParcela> parcelasCondicaoPagamento(int idCondicao) {
		Query query = entityManager.createQuery("select p from CondicaoParcela p Join p.condicaoPagamento c where c.id = :idCondicao");
		query.setParameter("idCondicao", idCondicao);
		
		List<CondicaoParcela> parcelas = query.getResultList();
		return parcelas;
		
		// se eu fosse resumir em uma linha:
		//return entityManager.createQuery("select p from CondicaoPagamento c Join Fetch c.parcelas where c.id = " + idCondicao).getResultList();
	}
}
