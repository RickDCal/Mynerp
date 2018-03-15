package br.com.mynerp.persistencia.dao;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.com.mynerp.persistencia.ContaReceber;
import br.com.mynerp.persistencia.Empresa;
import br.com.mynerp.persistencia.Parcela;
import br.com.mynerp.persistencia.Pessoa;
import br.com.mynerp.persistencia.dao.exception.ContaNaoEncontradaException;
import br.com.mynerp.persistencia.dao.exception.ObjetoNaoEncontradoException;
import br.com.mynerp.persistencia.dao.exception.ParcelaNaoEncontradaException;


@Stateless
public class ContaReceberDAO extends GenericDAO implements IContaReceberDAO {
	
	public ContaReceberDAO() {
		
	}
	
	public List<ContaReceber> obterCR () throws ContaNaoEncontradaException { //coloquei o tipo de lista pra ver no que dá
		
		Query query = entityManager.createQuery("select c from ContaReceber c");
		query.setHint("org.hibernate.timeout", 2000);
		
		List<ContaReceber> list = (List<ContaReceber>)query.getResultList(); //fiquei com dúvida ao colocar o Generics de lista aqui
		
		if (list == null || list.size() == 0)
			throw new ContaNaoEncontradaException();
			
		return list;
		
	}
	
		
	public ContaReceber obterCR (int id) throws ContaNaoEncontradaException {
				
		ContaReceber conta = entityManager.find(ContaReceber.class, id);
		if (conta == null) throw new ContaNaoEncontradaException();
		
		return conta;
	
	}	
	
	public List<ContaReceber> obterCR (Integer position, Integer max) throws ContaNaoEncontradaException { //position é o registro inicial e max a quantidade de registro, pra escolher quantos mostrar na tela 
		   //e apartir de qual.... muito útil em paginação igual cadastro de pedidos no supraweb 
		Query query = entityManager.createQuery("select c from ContaReceber c ");
		query.setHint("org.hibernate.timeout", 2000);

		if (position != null) query.setFirstResult(position);

		if (max != null) query.setMaxResults(max);

		List<ContaReceber> list = (List<ContaReceber>)query.getResultList(); //fiquei com dúvida ao colocar o Generics de lista aqui

		return list;

	}	
	
	public List<ContaReceber> obter (Date emissaoInic, Date emissaoFim, Date vencInic, Date vencFim, Date pagtoInic, Date pagtoFim,Pessoa pessoa, Integer idPagamento, Integer position, Integer max, String order) throws ContaNaoEncontradaException {  

		String consulta = "select c from ContaReceber c where c.pessoa.id is not null";
		String parametros = "";
		String ordenacao = "";	
		
		if (emissaoInic != null) {
			parametros = parametros + " and c.parcelas.dataEmissao >= :emissaoInicial";	
		}
		if (emissaoFim != null) {
			parametros = parametros + " and c.parcelas.dataEmissao <= :emissaoInicial";
		}
		if (vencInic != null) {
			parametros = parametros + " and c.parcelas.dataVencimento >= :vencimentoInicial";	
		}
		if (vencFim != null) {
			parametros = parametros + " and c.parcelas.dataVencimento <= :vencimentoFinal";
		}
		if (pagtoInic != null) {
			parametros = parametros + " and c.parcelas.dataQuitacao >= :pagtoInicial";	
		}
		if (pagtoFim != null) {
			parametros = parametros + " and c.parcelas.dataQuitacao <= :pagtoFinal";
		}		
		
		if (pessoa != null) {
			parametros = parametros + " and c.idpessoa = :idPessoa";
		}			
		
		if (idPagamento != null) {	
			
			switch (idPagamento) {
			case 1: parametros = parametros + " and p.dataQuitacao is not null"; break;	
			case 2: parametros = parametros + " and p.dataQuitacao is null"; break;
			}
		}
		
		
		if (order != null) {
			ordenacao = " order by " + order;
		}
		
		consulta = consulta + parametros + ordenacao;
		
		Query query = entityManager.createQuery(consulta);
		
		if (emissaoInic != null ) {
			query.setParameter("emissaoInicial", emissaoInic);
		}
		if (emissaoFim != null ) {
			query.setParameter("emissaoFinal", emissaoFim);
		}
		if (vencInic != null) {
			query.setParameter("vencimentoInicial", vencInic);
		}
		if (vencFim != null) {
			query.setParameter("vencimentoFinal", vencFim);
		}
		if (pagtoInic != null) {
			query.setParameter("pagtoInicial", pagtoInic);	
		}
		if (pagtoFim != null) {
			query.setParameter("pagtoFinal", pagtoFim);
		}
	
		query.setHint("org.hibernate.timeout", 2000);
				
		if (position != null) query.setFirstResult(position);

		if (max != null) query.setMaxResults(max);

		List<ContaReceber> list = (List<ContaReceber>)query.getResultList(); //fiquei com dúvida ao colocar o Generics de lista aqui

		return list;

	}	
	
	public List<Parcela> obterParcelas (Date emissaoInic, Date emissaoFim, Date vencInic, Date vencFim, Date pagtoInic, Date pagtoFim,Pessoa pessoa, Integer idPagamento, Integer position, Integer max, String order) throws ParcelaNaoEncontradaException { //coloquei o tipo de lista pra ver no que dá
						
		String consulta = "select p from ContaReceber c Inner Join c.parcelas p where c.id is not null";
		String parametros = "";	
		String ordenacao = "";
		
		if (emissaoInic != null) {
			parametros = parametros + " and p.dataEmissao >= :emissaoInicial";	
		}
		if (emissaoFim != null) {
			parametros = parametros + " and p.dataEmissao <= :emissaoFinal";
		}
		if (vencInic != null) {
			parametros = parametros + " and p.dataVencimento >= :vencimentoInicial";	
		}
		if (vencFim != null) {
			parametros = parametros + " and p.dataVencimento <= :vencimentoFinal";
		}
		if (pagtoInic != null) {
			parametros = parametros + " and p.dataQuitacao >= :pagtoInicial";	
		}
		if (pagtoFim != null) {
			parametros = parametros + " and p.dataQuitacao <= :pagtoFinal";
		}
		
		if (pessoa != null) {
			parametros = parametros + " and p.conta.pessoa.id = :idPessoa";
		}		
		
		if (idPagamento != null) {		
		
			switch (idPagamento) {
			case 1: parametros = parametros + " and p.dataQuitacao is not null"; break;	//pagos
			case 2: parametros = parametros + " and p.dataQuitacao is null"; break; //não pagos
			}

		}
		
		if (order != null) {
			ordenacao = " order by " + order;
		}
		
		consulta = consulta + parametros + ordenacao;		
		
		Query query = entityManager.createQuery(consulta);
		
		if (emissaoInic != null ) {
			query.setParameter("emissaoInicial", emissaoInic);
		}
		if (emissaoFim != null ) {
			query.setParameter("emissaoFinal", emissaoFim);
		}
		if (vencInic != null) {
			query.setParameter("vencimentoInicial", vencInic);
		}
		if (vencFim != null) {
			query.setParameter("vencimentoFinal", vencFim);
		}
		if (pagtoInic != null) {
			query.setParameter("pagtoInicial", pagtoInic);	
		}
		if (pagtoFim != null) {
			query.setParameter("pagtoFinal", pagtoFim);
		}		
		
		if (pessoa != null) {
			query.setParameter("idPessoa", pessoa.getId());
		}
	
		query.setHint("org.hibernate.timeout", 2000);
				
		if (position != null) query.setFirstResult(position);

		if (max != null) query.setMaxResults(max);

		List<Parcela> list = (List<Parcela>)query.getResultList(); 

		return list;
		
	}
	
	public List<Parcela> obterParcelas (Integer idConta, String documento, String nome, Pessoa pessoa, Date emissaoInic, Date emissaoFim, 
			Date vencInic, Date vencFim, Integer idPagamento, Integer position, Integer max) throws ParcelaNaoEncontradaException {
				
				String consulta = "select p from ContaReceber c Inner Join c.parcelas p where c.id is not null";
				String parametros = "";	
				String ordenacao = " order by p.id desc"; // estava pelo vencimento asc
				
				if (emissaoInic != null) {
					parametros = parametros + " and p.dataEmissao >= :emissaoInicial";	
				}
				if (emissaoFim != null) {
					parametros = parametros + " and p.dataEmissao <= :emissaoFinal";
				}
				if (vencInic != null) {
					parametros = parametros + " and p.dataVencimento >= :vencimentoInicial";	
				}
				if (vencFim != null) {
					parametros = parametros + " and p.dataVencimento <= :vencimentoFinal";
				}		
				if (pessoa != null) {
					parametros = parametros + " and p.conta.pessoa.id = :idPessoa";
				}		
				if (idConta != null) {
					parametros = parametros + " and c.id = :idConta";
				}
				if (documento != null && documento != "") {
					parametros = parametros + " and p.numeroDocto like :documento";
				}
				if (nome != null && nome != "") {
					parametros = parametros + " and (c.pessoa.nome like :nome or c.pessoa.nomeFantasia like :nome)";
				}
				
				
				if (idPagamento != null) {		
					switch (idPagamento) {
					case 1: parametros = parametros + " and p.dataQuitacao is not null"; break;	
					case 2: parametros = parametros + " and p.dataQuitacao is null"; break;
					}

				}
				
				consulta = consulta + parametros + ordenacao;		
				
				Query query = entityManager.createQuery(consulta);
				
				if (emissaoInic != null ) {
					query.setParameter("emissaoInicial", emissaoInic);
				}
				if (emissaoFim != null ) {
					query.setParameter("emissaoFinal", emissaoFim);
				}
				if (vencInic != null) {
					query.setParameter("vencimentoInicial", vencInic);
				}
				if (vencFim != null) {
					query.setParameter("vencimentoFinal", vencFim);
				}
				if (idConta != null) {
					query.setParameter("idConta", idConta);
				}
				if (documento != null && documento != "") {
					query.setParameter("documento", "%" + documento +"%");	
				}		
				if (pessoa != null) {
					query.setParameter("idPessoa", pessoa.getId());
				}
				if (nome != null && nome != "") {
					query.setParameter("nome", "%" + nome +"%");	
				}
			
				query.setHint("org.hibernate.timeout", 2000);
						
				if (position != null) query.setFirstResult(position);

				if (max != null) query.setMaxResults(max);

				List<Parcela> list = (List<Parcela>)query.getResultList(); //fiquei com dúvida ao colocar o Generics de lista aqui

				//if (list == null || list.size() == 0)   // não quero tratar esta exceção, qdo resultar nada vou mostrar o grid vazio
				//	throw new ContaNaoEncontradaException();

				return list;
				
			}

	
	public List<Parcela> obterPCP(Integer idconta, Integer pagoNaoPago, Integer idpessoa)throws ContaNaoEncontradaException {
		
		
		String consulta = "select p from Parcela p, ContaReceber r where p.conta.id is not null and p.conta member of r and r.id = p.conta.id";
		String parametros = "";
		String ordenacao = " order by p.conta.id, p.sequencial";
				
		if (idconta != null) {
			parametros = parametros + " and p.conta.id = :idconta ";
		}
		if (pagoNaoPago != null && pagoNaoPago == 1) {
			parametros = parametros + " and p.dataQuitacao is not null";
		} else if (pagoNaoPago != null && pagoNaoPago == 2) {
			parametros = parametros + " and p.dataQuitacao is null";
		}
		if (idpessoa != null) {
			parametros = parametros + " and p.Conta.Pessoa.id = :idpessoa";
		}
		
		consulta = consulta + parametros + ordenacao;
		
		Query query = entityManager.createQuery(consulta);
			
		query.setHint("org.hibernate.timeout", 2000);
		
		if (idconta != null ) {
			query.setParameter("idconta", idconta);
		}
		if (idpessoa != null ) {
			query.setParameter("idpessoa", idpessoa);
		}
		
		List<Parcela> list = (List<Parcela>)query.getResultList();		
		
		return list;
	}
	
	public ContaReceber inserir (ContaReceber conta) {
		entityManager.persist(conta);
		entityManager.flush(); // para inserir na mesma hora que gera na memória
		return conta;
	}
	
	public Parcela obterParcela (Integer idConta, Integer sequencial, Integer id, Integer idPagoNaoPago) throws ParcelaNaoEncontradaException {
		
		Parcela parcela = null;
		
		if (id != null) {
			parcela = entityManager.find(Parcela.class, id);
		} else {
			
			String consulta = "select p from Parcela p where p.conta.id = :idConta and p.sequencial = :sequencial" ;
			String parametros = "";
			if (idPagoNaoPago !=null) {
				switch (idPagoNaoPago) {
				case 2: parametros = parametros + " and c.parcelas.dataQuitacao is not null"; break;	
				case 1: parametros = parametros + " and c.parcelas.dataQuitacao is null"; break;
				}
			}
			
			consulta = consulta + parametros;
						
			Query query = entityManager.createQuery(consulta);			
			
			query.setParameter("idConta", idConta);
			query.setParameter("sequencial", sequencial);
			query.setMaxResults(1);
			
			if (query.getResultList().size() > 0) {
				parcela = (Parcela)query.getSingleResult();
			}			
		}
		
		//if (parcela == null) throw new ParcelaNaoEncontradaException();
		return parcela;
	}
	
	public ContaReceber obterCRDoc (int idColeta) throws ContaNaoEncontradaException  {
		
		String consulta  = "select c from ContaReceber c where c.coleta.id = :idColeta";		
		Query query = entityManager.createQuery(consulta);
		
		query.setParameter("idColeta", idColeta);
		
		List<ContaReceber> list = query.getResultList();
		
		if (list.size() <=0) {
			return null;
		} else {		
			ContaReceber conta = (ContaReceber)query.getSingleResult();		
			return conta;
		}
	}
	
	public ContaReceber alterar (ContaReceber conta) {
		
//		Iterator<Parcela> itListParcelas = conta.getParcelas().iterator();
//		ParcelaDAO esteDao = new ParcelaDAO();
//		
//		while (itListParcelas.hasNext()) {
//			
//			Parcela parcelaNova = itListParcelas.next();	
//			Parcela parcelaBd = new Parcela();
//			try {
//				parcelaBd = esteDao.obterParcela(parcelaNova.getConta().getId(), parcelaNova.getSequencial(), null, null);
//			} catch (ParcelaNaoEncontradaException e) {
//				e.printStackTrace();
//			} finally {
//				parcelaBd = parcelaNova;
//			}
//			
//			parcelaNova.setId(parcelaBd.getId());
//				
//		}			
		entityManager.merge(conta);
		entityManager.flush(); 
		return conta;
	}
	
	public void remover (ContaReceber conta) {
		//entityManager.remove(conta); a linha de baixo antes era assim mas dava o erro -> Removing a detached instance... 
		entityManager.remove(entityManager.contains(conta)? conta : entityManager.merge(conta)); // não sei bem o que faz aqui mas funcionou
		entityManager.flush(); // para inserir na mesma hora que gera na memória
		
	}
	
	public <T>Integer totalRegistros (Class<T> classe, Empresa empresa) throws ObjetoNaoEncontradoException {

		Integer total = null;
		
		String className = classe.getSimpleName();
		String consulta = null;
		
		switch (className) {
		case "Parcela": consulta = "select count(p) from ContaReceber o Inner Join o.parcelas p ";
		break;
		default: consulta = "select count(o) from " + className + " o";
		break;
		}
		
		Integer idEmpresa = null;

		if (empresa != null) {
			idEmpresa = empresa.getId();
			if (idEmpresa != null) {				

				try {
					if (classe.getField("empresa") != null) {
						consulta = consulta + " where o.empresa.id = :idEmpresa";
					} 
				} catch (Exception e) {
					//consulta = consulta + " where o.idEmpresa = :idEmpresa";
				} finally {

				}
			}
		}

		Query query = entityManager.createQuery(consulta);				

		total = ((Long) query.getSingleResult()).intValue();
		return total;
	}

}

