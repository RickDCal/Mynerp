package br.com.mynerp.persistencia.dao;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import br.com.mynerp.persistencia.ContaReceber;
import br.com.mynerp.persistencia.Empresa;
import br.com.mynerp.persistencia.Parcela;
import br.com.mynerp.persistencia.Pessoa;
import br.com.mynerp.persistencia.dao.exception.ContaNaoEncontradaException;
import br.com.mynerp.persistencia.dao.exception.ObjetoNaoEncontradoException;
import br.com.mynerp.persistencia.dao.exception.ParcelaNaoEncontradaException;


@Local
public interface IContaReceberDAO {

	public List<ContaReceber> obterCR() throws ContaNaoEncontradaException;

	public ContaReceber obterCR(int id) throws ContaNaoEncontradaException;

	public List<ContaReceber> obterCR(Integer position, Integer max) throws ContaNaoEncontradaException;

	public List<ContaReceber> obter(Date emissaoInic, Date emissaoFim,
			Date vencInic, Date vencFim, Date pagtoInic, Date pagtoFim,
			Pessoa pessoa, Integer idPagamento, Integer position, Integer max, String order)
	throws ContaNaoEncontradaException;

	public List<Parcela> obterParcelas (Date emissaoInic, Date emissaoFim, 
			Date vencInic, Date vencFim, Date pagtoInic, Date pagtoFim,
			Pessoa pessoa, Integer idPagamento, Integer position, Integer max, String order) 
	throws ParcelaNaoEncontradaException; 	
	
	public List<Parcela> obterParcelas (Integer idConta, String documento, String nome, Pessoa pessoa, Date emissaoInic, Date emissaoFim, 
			Date vencInic, Date vencFim, Integer idPagamento, Integer position, Integer max) throws ParcelaNaoEncontradaException;
	
	public Parcela obterParcela (Integer idConta, Integer sequencial, Integer id, Integer idPagoNaoPago) throws ParcelaNaoEncontradaException;
	
	public ContaReceber obterCRDoc (int idColeta) throws ContaNaoEncontradaException;
	
	public <T>Integer totalRegistros (Class<T> classe, Empresa empresa) throws ObjetoNaoEncontradoException ;
	
	public ContaReceber inserir(ContaReceber conta);

	public ContaReceber alterar(ContaReceber conta);

	public void remover(ContaReceber conta);

}