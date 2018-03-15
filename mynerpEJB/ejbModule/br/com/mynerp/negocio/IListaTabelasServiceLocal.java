package br.com.mynerp.negocio;

import java.util.List;

import br.com.mynerp.negocio.exception.ListaTabelasInexistenteException;
import br.com.mynerp.persistencia.Cobranca;
import br.com.mynerp.persistencia.CondicaoPagamento;
import br.com.mynerp.persistencia.CondicaoParcela;
import br.com.mynerp.persistencia.dao.exception.ListaTabelasNaoEncontradoException;

public interface IListaTabelasServiceLocal {

	//formas de cobranca
	public List<Cobranca> pesquisarCobrancas(Integer idPagarReceber)	throws ListaTabelasNaoEncontradoException,ListaTabelasInexistenteException;
	
	public Cobranca pesquisarCobranca (int id) throws ListaTabelasNaoEncontradoException;
	
	public void cadastrarCobranca (Cobranca cobranca) throws ListaTabelasNaoEncontradoException;
	
	public void atualizarCobranca (Cobranca cobranca)throws ListaTabelasNaoEncontradoException;
	
	public void removerCobranca (Cobranca cobranca) throws ListaTabelasNaoEncontradoException;
	
	//condições de pagamento	
	public List<CondicaoPagamento> pesquisarCondicoesPagamento(Integer idPagarReceber, boolean isEager) throws ListaTabelasNaoEncontradoException,ListaTabelasInexistenteException;
	
	public CondicaoPagamento pesquisarCondicaoPagamento(int id, boolean isEager) throws ListaTabelasNaoEncontradoException,ListaTabelasInexistenteException;
		
	public void cadastrarCondicaoPagamento (CondicaoPagamento condicao) throws ListaTabelasNaoEncontradoException;
	
	public void atualizarCondicaoPagamento (CondicaoPagamento condicao)throws ListaTabelasNaoEncontradoException;
	
	public void removerCondicaoPagamento (CondicaoPagamento condicao) throws ListaTabelasNaoEncontradoException;
	
	public List<CondicaoParcela> parcelasCondicaoPagamento (Integer idCondicao) throws ListaTabelasNaoEncontradoException;	
	
}