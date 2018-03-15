package br.com.mynerp.negocio;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.mynerp.negocio.exception.ListaTabelasInexistenteException;
import br.com.mynerp.persistencia.Cobranca;
import br.com.mynerp.persistencia.CondicaoPagamento;
import br.com.mynerp.persistencia.CondicaoParcela;
import br.com.mynerp.persistencia.dao.IListaTabelasDAO;
import br.com.mynerp.persistencia.dao.exception.ListaTabelasNaoEncontradoException;

@Stateless
public class ListaTabelasService implements IListaTabelasServiceLocal {
	
	@EJB
	private IListaTabelasDAO tabelasDao;

	public ListaTabelasService() {
		
	}	

	public List<Cobranca> pesquisarCobrancas(Integer idPagarReceber) throws ListaTabelasNaoEncontradoException, ListaTabelasInexistenteException {
		
		try {
			return tabelasDao.obterCobrancas(idPagarReceber);				
		}catch (ListaTabelasNaoEncontradoException e){
			throw new ListaTabelasInexistenteException();				
		}
		
	}
	
	public Cobranca pesquisarCobranca (int id) throws ListaTabelasNaoEncontradoException {
		try {
			return tabelasDao.obterCobranca(id);				
		}catch (ListaTabelasNaoEncontradoException e){
			throw new ListaTabelasNaoEncontradoException();				
		}
	}
	
	public void cadastrarCobranca (Cobranca cobranca) throws ListaTabelasNaoEncontradoException {
		tabelasDao.inserirCobranca((Cobranca)cobranca);
	}
	
	public void atualizarCobranca (Cobranca cobranca)throws ListaTabelasNaoEncontradoException {
		tabelasDao.alterarCobranca((Cobranca)cobranca);
	}
		
	
	public void removerCobranca (Cobranca cobranca) throws ListaTabelasNaoEncontradoException {
		tabelasDao.removerCobranca((Cobranca)cobranca);
	}	
	
	public List<CondicaoPagamento> pesquisarCondicoesPagamento (Integer idPagarReceber, boolean isEager) throws ListaTabelasNaoEncontradoException, ListaTabelasInexistenteException {
		try {
			return tabelasDao.obterCondicoesPagamento(idPagarReceber, isEager);				
		}catch (ListaTabelasNaoEncontradoException e){
			throw new ListaTabelasInexistenteException();				
		}
	}
	
	public CondicaoPagamento pesquisarCondicaoPagamento (int id, boolean isEager) throws ListaTabelasNaoEncontradoException {
		try {
			return tabelasDao.obterCondicaoPagamento(id, isEager);				
		}catch (ListaTabelasNaoEncontradoException e){
			throw new ListaTabelasNaoEncontradoException();				
		}
	}
	
	public void cadastrarCondicaoPagamento (CondicaoPagamento condicao) throws ListaTabelasNaoEncontradoException {
		tabelasDao.inserirCondicaoPagamento((CondicaoPagamento)condicao);
	}
	
	public void atualizarCondicaoPagamento (CondicaoPagamento condicao)throws ListaTabelasNaoEncontradoException {
		tabelasDao.alterarCondicaoPagamento((CondicaoPagamento)condicao);
	}
		
	
	public void removerCondicaoPagamento (CondicaoPagamento condicao) throws ListaTabelasNaoEncontradoException {
		tabelasDao.removerCondicaoPagamento((CondicaoPagamento)condicao);
	}
	
	public List<CondicaoParcela> parcelasCondicaoPagamento (Integer idCondicao) throws ListaTabelasNaoEncontradoException {
		return tabelasDao.parcelasCondicaoPagamento(idCondicao);//removerCondicaoPagamento((CondicaoPagamento)condicao);
	}
	
}
		
	

