package br.com.mynerp.persistencia.dao;

import java.util.List;

import javax.ejb.Local;

import br.com.mynerp.persistencia.Cobranca;
import br.com.mynerp.persistencia.CondicaoPagamento;
import br.com.mynerp.persistencia.dao.exception.ListaTabelasNaoEncontradoException;

@Local
public interface ICobrancaDAO {

	public List<Cobranca> obter(Integer idPagarReceber) throws ListaTabelasNaoEncontradoException;
	
	public List<CondicaoPagamento> obter (Integer idPagarReceber, boolean isEager) throws ListaTabelasNaoEncontradoException;

}