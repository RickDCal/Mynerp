package br.com.mynerp.negocio;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import com.google.gson.JsonObject;

import br.com.mynerp.negocio.exception.ContaInexistenteException;
import br.com.mynerp.negocio.exception.FalhaAoCriarJSONException;
import br.com.mynerp.negocio.exception.FalhaAoGerarFinanceiroException;
import br.com.mynerp.persistencia.Cobranca;
import br.com.mynerp.persistencia.CondicaoPagamento;
import br.com.mynerp.persistencia.Conta;
import br.com.mynerp.persistencia.ContaPagar;
import br.com.mynerp.persistencia.ContaReceber;
import br.com.mynerp.persistencia.Empresa;
import br.com.mynerp.persistencia.Parcela;
import br.com.mynerp.persistencia.Pessoa;
import br.com.mynerp.persistencia.dao.exception.ContaNaoEncontradaException;
import br.com.mynerp.persistencia.dao.exception.ObjetoNaoEncontradoException;
import br.com.mynerp.persistencia.dao.exception.ParcelaNaoEncontradaException;
import br.com.mynerp.persistencia.enumerate.ContaEnum;

@Local
public interface IContaServiceLocal {

	public List pesquisar(ContaEnum contaEnum) throws ContaNaoEncontradaException, ContaInexistenteException;

	public List<ContaReceber> pesquisarCR(ContaEnum contaEnum,
			Date emissaoInic, Date emissaoFim, Date vencInic, Date vencFim,
			Date pagtoInic, Date pagtoFim, Pessoa pessoa, Integer idPagamento,
			Integer position, Integer max, String order) 
	throws ContaNaoEncontradaException;

	public List<ContaPagar> pesquisarCP(ContaEnum contaEnum, Date emissaoInic,
			Date emissaoFim, Date vencInic, Date vencFim, Date pagtoInic,
			Date pagtoFim, Pessoa pessoa, Integer idPagamento,
			Integer position, Integer max, String order) 
	throws ContaNaoEncontradaException;

	public List pesquisar(ContaEnum contaEnum, Integer position, Integer max) throws ContaNaoEncontradaException, ContaInexistenteException;

	public Conta pesquisar(ContaEnum contaEnum, int idconta) throws ContaNaoEncontradaException, ContaInexistenteException;
	
	public List<Parcela> pesquisarParcelas (ContaEnum contaEnum, Date emissaoInic, Date emissaoFim, 
				Date vencInic, Date vencFim, Date pagtoInic, Date pagtoFim,
				Pessoa pessoa, Integer idPagamento, Integer position, Integer max, String order) throws ParcelaNaoEncontradaException;
	
	public List<Parcela> pesquisarParcelas (ContaEnum contaEnum, Integer idConta, String documento, String nome, Pessoa pessoa, Date emissaoInic, Date emissaoFim, 
			Date vencInic, Date vencFim, Integer idPagamento, Integer position, Integer max) throws ParcelaNaoEncontradaException;
	
	public ContaReceber geraContaReceber(Pessoa pessoa, Object objeto, CondicaoPagamento condicao, Cobranca cobranca, 
			double valorTotal, Date dataBase, String numDocto, String observacao, boolean gerarParcelas) throws FalhaAoGerarFinanceiroException;

	public Parcela pesquisarParcela (ContaEnum contaEnum, Integer idConta, Integer sequencial, Integer id, Integer idPagoNaoPago) throws ParcelaNaoEncontradaException;
	
	public JsonObject contaJSON(Conta conta) throws FalhaAoCriarJSONException;
	
	public JsonObject parcelaJSON(Parcela parcela) throws FalhaAoCriarJSONException;
	
	public ContaReceber pesquisarCRDoc (ContaEnum contaEnum, int idColeta) throws ContaNaoEncontradaException;
	
	public <T>Integer totalRegistros (ContaEnum contaEnum,Class<T> classe, Empresa empresa) throws ObjetoNaoEncontradoException;
	
	public void cadastrar(Conta conta) throws ContaInexistenteException;

	public void atualizar(Conta conta) throws ContaInexistenteException;

	public void remover(Conta conta) throws ContaInexistenteException;

}