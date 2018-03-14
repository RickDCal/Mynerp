package br.com.mynerp.apresentacao.facade.financeiro;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.google.gson.JsonObject;

import br.com.mynerp.negocio.IContaServiceLocal;
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

public class ContaFacade {
	
	private Properties p; // coloquei o private por minha conta mas n�o precisava dele
	private Context c;
	
	private IContaServiceLocal service; //digo qual a interface que vou usar // aqui pode ter erro
	
	public ContaFacade () throws NamingException{
		p = new Properties();
		c = new InitialContext(p);
		service = (IContaServiceLocal)c.lookup("java:global/mynerpEAR/mynerpEJB/ContaService");
	}

	@SuppressWarnings("rawtypes")
	public List pesquisar(ContaEnum contaEnum) throws ContaNaoEncontradaException, ContaInexistenteException {
		return service.pesquisar(contaEnum);
	}

	public List<ContaReceber> pesquisarCR(ContaEnum contaEnum,
			Date emissaoInic, Date emissaoFim, Date vencInic, Date vencFim,
			Date pagtoInic, Date pagtoFim, Pessoa pessoa, Integer idPagamento,
			Integer position, Integer max, String order) 
	throws ContaNaoEncontradaException {
		return service.pesquisarCR(contaEnum, emissaoInic, emissaoFim, vencInic, vencFim, 
				pagtoInic, pagtoFim, pessoa, idPagamento, position, max, order);
	}

	public List<ContaPagar> pesquisarCP(ContaEnum contaEnum, Date emissaoInic,
			Date emissaoFim, Date vencInic, Date vencFim, Date pagtoInic,
			Date pagtoFim, Pessoa pessoa, Integer idPagamento,
			Integer position, Integer max, String order) 
	throws ContaNaoEncontradaException {
		return service.pesquisarCP(contaEnum, emissaoInic, emissaoFim, vencInic, vencFim, 
				pagtoInic, pagtoFim, pessoa, idPagamento, position, max, order);
	}

	@SuppressWarnings("rawtypes")
	public List pesquisar(ContaEnum contaEnum, Integer position, Integer max) throws ContaNaoEncontradaException, ContaInexistenteException {
		return service.pesquisar(contaEnum, position, max);
	}

	public Conta pesquisar(ContaEnum contaEnum, int idconta) throws ContaNaoEncontradaException, ContaInexistenteException{
		return service.pesquisar(contaEnum, idconta);
	}
	
	public List<Parcela> pesquisarParcelas (ContaEnum contaEnum, Date emissaoInic, Date emissaoFim, 
			Date vencInic, Date vencFim, Date pagtoInic, Date pagtoFim,
			Pessoa pessoa, Integer idPagamento, Integer position, Integer max, String order) throws ParcelaNaoEncontradaException {
		return service.pesquisarParcelas(contaEnum, emissaoInic, emissaoFim, vencInic, vencFim, pagtoInic, pagtoFim, pessoa, idPagamento, position, max, order);
	}
	
	public List<Parcela> pesquisarParcelas (ContaEnum contaEnum, Integer idConta, String documento, String nome, Pessoa pessoa, Date emissaoInic, Date emissaoFim, 
			Date vencInic, Date vencFim, Integer idPagamento, Integer position, Integer max) throws ParcelaNaoEncontradaException {
		return service.pesquisarParcelas(contaEnum, idConta, documento, nome, pessoa, emissaoInic, emissaoFim, vencInic, vencFim, idPagamento, position, max);
	}
	
	public Parcela pesquisarParcela (ContaEnum contaEnum, Integer idConta, Integer sequencial, Integer id, Integer idPagoNaoPago) throws ParcelaNaoEncontradaException {
		return service.pesquisarParcela(contaEnum, idConta, sequencial, id, idPagoNaoPago);
	}
	
	public ContaReceber pesquisarCRDoc (ContaEnum contaEnum, int idColeta) throws ContaNaoEncontradaException {
		return service.pesquisarCRDoc(contaEnum, idColeta);
	}
	
	public ContaReceber geraContaReceber(Pessoa pessoa, Object objeto, CondicaoPagamento condicao, Cobranca cobranca, 
			double valorTotal, Date dataBase, String numDocto, String observacao, boolean gerarParcelas) throws FalhaAoGerarFinanceiroException {
		return service.geraContaReceber(pessoa, objeto, condicao, cobranca, valorTotal, dataBase, numDocto, observacao, gerarParcelas);
	}
	
	public <T>Integer totalRegistros (ContaEnum contaEnum,Class<T> classe, Empresa empresa) throws ObjetoNaoEncontradoException {
		return service.totalRegistros(contaEnum, classe, empresa);
	}
	
	public JsonObject contaJSON(Conta conta) throws FalhaAoCriarJSONException {
		return service.contaJSON(conta);
	}
	
	public JsonObject parcelaJSON(Parcela parcela) throws FalhaAoCriarJSONException {
		return service.parcelaJSON(parcela);
	}
		
	public void cadastrar(Conta conta) throws ContaInexistenteException {
		service.cadastrar(conta);
	}

	public void atualizar(Conta conta) throws ContaInexistenteException {
		service.atualizar(conta);
	}

	public void remover(Conta conta) throws ContaInexistenteException {
		service.remover(conta);
	}
	
	
}
