package br.com.mynerp.negocio;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import br.com.mynerp.negocio.exception.ContaInexistenteException;
import br.com.mynerp.negocio.exception.FalhaAoCriarJSONException;
import br.com.mynerp.negocio.exception.FalhaAoGerarFinanceiroException;
import br.com.mynerp.persistencia.Cliente;
import br.com.mynerp.persistencia.Cobranca;
import br.com.mynerp.persistencia.Coleta;
import br.com.mynerp.persistencia.CondicaoPagamento;
import br.com.mynerp.persistencia.CondicaoParcela;
import br.com.mynerp.persistencia.Conta;
import br.com.mynerp.persistencia.ContaPagar;
import br.com.mynerp.persistencia.ContaReceber;
import br.com.mynerp.persistencia.Empresa;
import br.com.mynerp.persistencia.Fornecedor;
import br.com.mynerp.persistencia.Parcela;
import br.com.mynerp.persistencia.Pessoa;
import br.com.mynerp.persistencia.dao.IContaPagarDAO;
import br.com.mynerp.persistencia.dao.IContaReceberDAO;
import br.com.mynerp.persistencia.dao.exception.ContaNaoEncontradaException;
import br.com.mynerp.persistencia.dao.exception.ObjetoNaoEncontradoException;
import br.com.mynerp.persistencia.dao.exception.ParcelaNaoEncontradaException;
import br.com.mynerp.persistencia.enumerate.ContaEnum;



@Stateless
public class ContaService implements IContaServiceLocal {

	@EJB
	private IContaReceberDAO contaReceberDao;
	@EJB
	private IContaPagarDAO contaPagarDao;

	public ContaService() {

	}


	public List pesquisar (ContaEnum contaEnum) throws ContaNaoEncontradaException, ContaInexistenteException {

		if (contaEnum == ContaEnum.RECEBER) {
			try {
				return contaReceberDao.obterCR();				
			}catch (ContaNaoEncontradaException e){
				throw new ContaNaoEncontradaException();				
			}
		} else if (contaEnum == ContaEnum.PAGAR) {
			try {
				return contaPagarDao.obterCP();				
			}catch (ContaNaoEncontradaException e){
				throw new ContaNaoEncontradaException();				
			}
		} else throw new ContaInexistenteException();				
	}


	public List<ContaReceber> pesquisarCR (ContaEnum contaEnum, Date emissaoInic, Date emissaoFim, Date vencInic, 
			Date vencFim, Date pagtoInic, Date pagtoFim,Pessoa pessoa, Integer idPagamento, 
			Integer position, Integer max, String order) throws ContaNaoEncontradaException {

		if (contaEnum == ContaEnum.RECEBER) {
			try {
				return contaReceberDao.obter(emissaoInic, emissaoFim, vencInic, vencFim, 
						pagtoInic, pagtoFim, pessoa, idPagamento, position, max, order);				
			}catch (ContaNaoEncontradaException e){
				throw new ContaNaoEncontradaException();				
			}
		} else throw new ContaNaoEncontradaException();
	}

	public List<ContaPagar> pesquisarCP (ContaEnum contaEnum, Date emissaoInic, Date emissaoFim, Date vencInic, 
			Date vencFim, Date pagtoInic, Date pagtoFim, Pessoa pessoa, Integer idPagamento, 
			Integer position, Integer max, String order) throws ContaNaoEncontradaException {

		if (contaEnum == ContaEnum.PAGAR) {
			try {
				return contaPagarDao.obter(emissaoInic, emissaoFim, vencInic, vencFim, 
						pagtoInic, pagtoFim, pessoa, idPagamento, position, max, order);				
			}catch (ContaNaoEncontradaException e){
				throw new ContaNaoEncontradaException();				
			}
		} else throw new ContaNaoEncontradaException();
	}


	public List<Parcela> pesquisarParcelas (ContaEnum contaEnum, Date emissaoInic, Date emissaoFim, 
			Date vencInic, Date vencFim, Date pagtoInic, Date pagtoFim,
			Pessoa pessoa, Integer idPagamento, Integer position, Integer max, String order) throws ParcelaNaoEncontradaException {

		if (contaEnum == ContaEnum.PAGAR) {
			try {
				return contaPagarDao.obterParcelas(emissaoInic, emissaoFim, vencInic, vencFim, pagtoInic, pagtoFim, pessoa, idPagamento, position, max, order);
			} catch (ParcelaNaoEncontradaException e) {
				throw new ParcelaNaoEncontradaException();
			}
		} else if (contaEnum == ContaEnum.RECEBER) {
			try {
				return contaReceberDao.obterParcelas(emissaoInic, emissaoFim, vencInic, vencFim, pagtoInic, pagtoFim, pessoa, idPagamento, position, max, order);
			} catch (ParcelaNaoEncontradaException e) {
				throw new ParcelaNaoEncontradaException();
			}
		} else throw new ParcelaNaoEncontradaException();
	}


	public List<Parcela> pesquisarParcelas (ContaEnum contaEnum, Integer idConta, String documento, String nome, Pessoa pessoa, Date emissaoInic, Date emissaoFim, 
			Date vencInic, Date vencFim, Integer idPagamento, Integer position, Integer max) throws ParcelaNaoEncontradaException {
		if (contaEnum == ContaEnum.PAGAR) {
			try {
				return contaPagarDao.obterParcelas(idConta, documento, nome, pessoa, emissaoInic, emissaoFim, vencInic, vencFim, idPagamento, position, max);
			} catch (ParcelaNaoEncontradaException e) {
				throw new ParcelaNaoEncontradaException();
			}
		} else if (contaEnum == ContaEnum.RECEBER) {
			try {
				return contaReceberDao.obterParcelas(idConta, documento, nome, pessoa, emissaoInic, emissaoFim, vencInic, vencFim, idPagamento, position, max);
			} catch (ParcelaNaoEncontradaException e) {
				throw new ParcelaNaoEncontradaException();
			}
		} else throw new ParcelaNaoEncontradaException();
	}	 

	public List pesquisar (ContaEnum contaEnum, Integer position, Integer max) throws ContaNaoEncontradaException, ContaInexistenteException {

		if (contaEnum == ContaEnum.RECEBER) {
			try {
				return contaReceberDao.obterCR(position, max);				
			}catch (ContaNaoEncontradaException e){
				throw new ContaNaoEncontradaException();				
			}
		} else if (contaEnum == ContaEnum.PAGAR) {
			try {
				return contaPagarDao.obterCP(position, max);				
			}catch (ContaNaoEncontradaException e){
				throw new ContaNaoEncontradaException();				
			}
		} else throw new ContaInexistenteException();


	}


	public Conta pesquisar (ContaEnum contaEnum, int idconta) throws ContaNaoEncontradaException, ContaInexistenteException {
		// este não é list este é um objeto veiculo referente a um id
		if (contaEnum == ContaEnum.RECEBER) {
			try {
				return contaReceberDao.obterCR(idconta);				
			}catch (ContaNaoEncontradaException e){
				throw new ContaNaoEncontradaException();				
			}
		} else if (contaEnum == ContaEnum.PAGAR) {
			try {
				return contaPagarDao.obterCP(idconta);				
			}catch (ContaNaoEncontradaException e){
				throw new ContaNaoEncontradaException();				
			}
		} else throw new ContaInexistenteException();

	}



	public Parcela pesquisarParcela (ContaEnum contaEnum,  Integer idConta, Integer sequencial, Integer id, Integer idPagoNaoPago) throws ParcelaNaoEncontradaException {
		try {
			return contaReceberDao.obterParcela(idConta, sequencial, id, idPagoNaoPago);				
		}catch (ParcelaNaoEncontradaException e){
			throw new ParcelaNaoEncontradaException();				
		}
	}

	public ContaReceber pesquisarCRDoc (ContaEnum contaEnum, int idColeta) throws ContaNaoEncontradaException {
		try {
			return contaReceberDao.obterCRDoc(idColeta);
		} catch (ContaNaoEncontradaException e) {
			throw new ContaNaoEncontradaException();
		}
	}	

	public void cadastrar (Conta conta) throws ContaInexistenteException {

		if (conta instanceof ContaReceber) {

			contaReceberDao.inserir((ContaReceber)conta);

		}  else if (conta instanceof ContaPagar) {

			contaPagarDao.inserir((ContaPagar)conta);

		}  else

			throw new ContaInexistenteException();
	}

	public void atualizar (Conta conta) throws ContaInexistenteException {

		if (conta instanceof ContaReceber) {

			contaReceberDao.alterar((ContaReceber)conta);

		} else if (conta instanceof ContaPagar) {

			contaPagarDao.alterar((ContaPagar)conta);

		} else throw new ContaInexistenteException();
	}


	public void remover (Conta conta) throws ContaInexistenteException {

		if (conta instanceof ContaReceber) {

			contaReceberDao.remover((ContaReceber)conta);

		} else if (conta instanceof ContaPagar) {

			contaPagarDao.remover((ContaPagar)conta);

		} else throw new ContaInexistenteException();
	}

	public ContaReceber geraContaReceber(Pessoa pessoa, Object objeto, CondicaoPagamento condicao, Cobranca cobranca, 
			double valorTotal, Date dataBase, String numDocto, String observacao, boolean gerarParcelas) throws FalhaAoGerarFinanceiroException{


		Coleta coleta;
		ContaReceber conta;

		try {
			coleta = null;
			if (objeto.getClass() == Coleta.class) coleta = (Coleta)objeto;	
			if (dataBase == null) dataBase = new Date();

			conta = new ContaReceber();

			conta.setDataEmissaoConta(dataBase);
			conta.setColeta(coleta);
			if (coleta != null && numDocto == null) {
				conta.setNumeroDocumento(""+coleta.getId()+"-1");
			} else if (numDocto != null) {
				conta.setNumeroDocumento(numDocto);
			}
			conta.setObservacao(observacao);
			conta.setPessoa(pessoa);
			conta.setValorTotal(valorTotal);

			contaReceberDao.inserir(conta);
			if (conta.getNumeroDocumento() == null) conta.setNumeroDocumento(""+conta.getId());

			if (gerarParcelas) {
				try {
					List <Parcela> listParcelas = this.gerarParcelas(conta, condicao, cobranca, numDocto, null);
					conta.setParcelas(listParcelas);
					contaReceberDao.alterar(conta);
					return conta;

				} catch (Exception e) {
					e.printStackTrace();
					contaReceberDao.remover(conta);
					System.out.println("Falha na geração de parcelas");
				}			
			}else {
				return conta;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Falha na geração de conta ou parcelas");
		}				

		return null;

	}

	public List<Parcela> gerarParcelas (Conta conta, CondicaoPagamento condicao, Cobranca cobranca, String numDocto, String observacao) throws FalhaAoGerarFinanceiroException {

		List<Parcela> listParcelas = new ArrayList<Parcela>();
		List<CondicaoParcela> listaParcelamento = (List<CondicaoParcela>) condicao.getParcelas();

		int i = 1;
		for (Iterator<CondicaoParcela> iterator = listaParcelamento.iterator(); iterator.hasNext();) {
			CondicaoParcela parc = (CondicaoParcela) iterator.next();			

			Date dataBase = conta.getDataEmissaoConta();
			int prazo = parc.getPrazo();

			Calendar c = Calendar.getInstance();
			c.setTime(dataBase);
			c.add(Calendar.DATE, +prazo);

			double percent = parc.getPercentual();

			Parcela parcela = new Parcela();
			parcela.setConta(conta);
			parcela.setDataEmissao(dataBase);
			parcela.setDataVencimento(c.getTime());
			parcela.setCobranca(cobranca);
			parcela.setNumeroDocto("" + conta.getId() + "-" + i);
			if (conta.getColeta() != null) parcela.setObservacao("Faturamento referente coleta Nº " + conta.getColeta().getId());
			parcela.setSequencial(i);
			parcela.setValor(conta.getValorTotal() * (percent / 100));			

			listParcelas.add(parcela);
			i++;
		}	

		return listParcelas;

	}

	public JsonObject contaJSON(Conta conta) throws FalhaAoCriarJSONException {

		//		Cliente cliente = new Cliente();
		//		cliente.setId(conta.getPessoa().getId());
		//		cliente.setNome(conta.getPessoa().getNome());

		//		conta.setPessoa(cliente);

		Pessoa pessoa = conta.getPessoa();		
		conta.setPessoa(null);

		Integer idColeta  = null;

		if (null != conta.getColeta()) {
			idColeta = conta.getColeta().getId();
			conta.setColeta(null);
		}

		List<Parcela> parcelas = new ArrayList<Parcela>();
		if (conta.getParcelas() != null) {
			parcelas.addAll(conta.getParcelas());
		}		

		for (Iterator<Parcela> iterator = parcelas.iterator(); iterator.hasNext();) {
			Parcela parcela = (Parcela) iterator.next();
			parcela.setConta(null);			
		}

		conta.setParcelas(null);
		conta.setParcelas(parcelas);	

		Gson gson = new Gson();

		JsonParser jp = new JsonParser();
		JsonObject jo = (JsonObject) jp.parse(gson.toJson(conta));

		JsonObject jpessoa = new JsonObject();
		jpessoa.addProperty("id", pessoa.getId());
		jpessoa.addProperty("nome", pessoa.getNome());	

		if (pessoa instanceof Cliente) {
			jo.add("cliente", jpessoa);
		} else if (pessoa instanceof Fornecedor) {
			jo.add("fornecedor", jpessoa);
		}	

		String cte = null;
		if (null != conta.getColeta() && conta.getColeta().getIdCTE() != null) {
			cte = "" + conta.getColeta().getIdCTE();
		}

		jo.addProperty("idPessoa", pessoa.getId());
		jo.addProperty("nomePessoa", pessoa.getNome());
		jo.addProperty("idColeta", idColeta);
		jo.addProperty("idCTE", cte);

		JsonArray ja = jo.getAsJsonArray("parcelas");
		for (int i = 0; i < ja.size(); i++) {
			JsonObject parc = (JsonObject) ja.get(i);
			parc.addProperty("idConta", conta.getId());
			parc.addProperty("idCobranca", parc.getAsJsonObject("cobranca").get("id").getAsString());
		}
		//jo.remove("pessoa");		
		return jo;		

	}

	public JsonObject parcelaJSON(Parcela parcela) throws FalhaAoCriarJSONException {

		Integer idCTE = null;
		
		try {
			idCTE = parcela.getConta().getColeta().getIdCTE();
		} catch (Exception e) {
		}
		
		parcela.getConta().setParcelas(null);
		parcela.getConta().getPessoa().setEnderecos(null);
		parcela.getConta().getPessoa().setContatos(null);
		parcela.getConta().setColeta(null);
		
		//Remover a pessoa de dentro da conta para o objeto ficar menor
		Pessoa pessoa = parcela.getConta().getPessoa();
		parcela.getConta().setPessoa(null);
		
		Gson gson = new Gson();

		JsonParser jp = new JsonParser();
		JsonObject jo = (JsonObject) jp.parse(gson.toJson(parcela));
		jo.addProperty("idPessoa", pessoa.getId());//parcela.getConta().getPessoa().getId());
		jo.addProperty("nomePessoa", pessoa.getNome());//parcela.getConta().getPessoa().getNome());
		jo.addProperty("idConta", parcela.getConta().getId());
		jo.addProperty("idCobranca", parcela.getCobranca() == null ? null : parcela.getCobranca().getId());
		jo.addProperty("idCTE", idCTE);
		//jo.addProperty("nomeCobranca", parcela.getCobranca().getNome());
		//colocando de volta a pessoa na conta pra não dar problema na hora de retirar da próxima parcela
		parcela.getConta().setPessoa(pessoa);

		return jo;		

	}
	
	public <T>Integer totalRegistros (ContaEnum contaEnum,Class<T> classe, Empresa empresa) throws ObjetoNaoEncontradoException {
		if (contaEnum == contaEnum.RECEBER) {
			return contaReceberDao.totalRegistros(classe, empresa);

		} else if (contaEnum == contaEnum.PAGAR) {

			return contaPagarDao.totalRegistros(classe, empresa);

		} else throw new ObjetoNaoEncontradoException();
	}
}



