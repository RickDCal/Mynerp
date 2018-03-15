package br.com.mynerp.apresentacao.servlet.financeiro;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import br.com.mynerp.apresentacao.facade.GenericFacade;
import br.com.mynerp.apresentacao.facade.UtilFacade;
import br.com.mynerp.apresentacao.facade.financeiro.ContaFacade;
import br.com.mynerp.apresentacao.servlet.AuxiliarServlet;
import br.com.mynerp.negocio.exception.ContaInexistenteException;
import br.com.mynerp.negocio.exception.ListaTabelasInexistenteException;
import br.com.mynerp.persistencia.Conta;
import br.com.mynerp.persistencia.Empresa;
import br.com.mynerp.persistencia.Parcela;
import br.com.mynerp.persistencia.dao.exception.ContaNaoEncontradaException;
import br.com.mynerp.persistencia.dao.exception.ListaTabelasNaoEncontradoException;
import br.com.mynerp.persistencia.dao.exception.ObjetoNaoEncontradoException;
import br.com.mynerp.persistencia.dao.exception.ParcelaNaoEncontradaException;
import br.com.mynerp.persistencia.enumerate.ContaEnum;

@WebServlet("/parcelaServlet")
public class ParcelaServlet extends ContaServlet {
		private static final long serialVersionUID = 1L;


	public ParcelaServlet() {
		super();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		AuxiliarServlet auxiliar = new AuxiliarServlet();
		auxiliar.load(request);
		Empresa empresa = auxiliar.empresa;

		JsonObject retorno = new JsonObject();	
		JsonArray dados = new JsonArray();


		String parametroString = null;
		ContaEnum tipoConta = ContaEnum.RECEBER;
		
		Integer position = auxiliar.position;		
		Integer max = auxiliar.max;
		
		if (request.getServletPath() != null && (request.getServletPath().equalsIgnoreCase("/contareceberServlet") || request.getServletPath().equalsIgnoreCase("/parcelareceberServlet"))) {
			tipoConta = ContaEnum.RECEBER;
		} else if (request.getServletPath() != null && (request.getServletPath().equalsIgnoreCase("/contapagarServlet")|| request.getServletPath().equalsIgnoreCase("/parcelapagarServlet"))) {
			tipoConta = ContaEnum.PAGAR;
		} 

		JsonArray jsonRecebido = auxiliar.jsRecebido;		//era arrayDados
		Integer action = auxiliar.action;

		try {

			ContaFacade contaFacade = new ContaFacade();
			UtilFacade utilFacade =  new UtilFacade();
			GenericFacade genericFacade = new GenericFacade();

			switch (action) {
			case 1: 
				/**se eu receber aqui uma parcela é a segunda chamada do save batch da tela que eu não consegui resolver
				 * então vou apenas retornar a parcela que já foi criada junto com a conta, simulando a inclusão da mesma
				 * até conseguir resolver  problema corretamente
				 */	
				
				
				//super.doPost(request, response);//mandando para a ContaServlet
				
				
				
				
				
				
//				try {														
//					for (int i = 0; i < jsonRecebido.size(); i++) {						
//						Integer idParcela = null;
//						JsonObject objetoJson = (JsonObject) jsonRecebido.get(i);
//						JsonArray arrayParcelas = null;
//						if (objetoJson.get("parcelas").getAsJsonObject() != null && objetoJson.get("parcelas").getAsJsonObject().get("parcelas") != null) {
//							
//							arrayParcelas =  objetoJson.get("parcelas").getAsJsonObject().get("parcelas").getAsJsonArray();
//							for (int j = 0; j < arrayParcelas.size(); j++) {
//								JsonObject parcelaJson = (JsonObject) arrayParcelas.get(j);
//								String uuid = parcelaJson.get("id").getAsString();
//								if (uuid != null) {
//									Parcela parcela = (Parcela) genericFacade.obterPorIdProperty(Parcela.class, uuid);
//									idParcela = parcela.getId();
//									dados.add(contaFacade.parcelaJSON(parcela));
//								}								
//							}	
//						}
//						
//						auxiliar.setSuccess(true, "Parcela adicionada <b>Código: " + idParcela + ".</b>");
//						//auxiliar.setMensagemRetorno ("Conta adicionada com sucesso! <b>Código: " + conta.getId() + ".</b>");							
//					}	
//				retorno.add("data", dados);
//
//				} catch (Exception e) {
//					e.printStackTrace();
//					auxiliar.setMensagemRetorno ( auxiliar.mensagemRetorno + "\n <br>Falha: " + e.getCause().getMessage() + ".");
//				}
				//auxiliar.setSuccess(true, "chamada indevida de inclusão de parcelas isoladas. Corrigir");
				//retorno.add("data", dados);
				
				
				JsonObject obj = (JsonObject) jsonRecebido.get(0);
				if (obj.get("parcelas")== null) {
					jsonRecebido = auxiliar.trataUuid(jsonRecebido);
					dados = this.inserirParcelaAvulsa(jsonRecebido);
					auxiliar.setSuccess(true, "Registro atualizado!");
				} else {
					auxiliar.setSuccess(true, "chamada indevida de inclusão de parcelas isoladas. Corrigir");
				}
				retorno.add("data", dados);
				
				
				break;
			case 2: 

				if (auxiliar.id != null) {
					Integer id = auxiliar.id;

					Parcela parcela  = contaFacade.pesquisarParcela(tipoConta, null, null, id, null);				
					JsonObject json = contaFacade.parcelaJSON(parcela);
					dados.add(json);
					auxiliar.setSuccess(true, null);

				} else {				

					List<Parcela> parcelas = new ArrayList<Parcela>();	

					Integer totalRegistros = 0;					
					Integer idConta = null;

					parametroString = request.getParameter("idConta");
					if (parametroString != null) {
						idConta = Integer.parseInt(parametroString);
					}					

					if (auxiliar.jsFiltros != null) {	

						Date dataEmissaoInicial = null;
						Date dataEmissaoFinal = null;
						Date dataVencimentoInicial = null;
						Date dataVencimentoFinal = null;
						String nomePessoa = null;
						Integer idPagamento = null;
						String numeroDocto = null;

						String tipoFiltro = null;
						String valorFiltro = null;
						try {
							JsonArray filters = auxiliar.jsFiltros;

							for (int j = 0; j < filters.size(); j++) {
								JsonObject jo = (JsonObject) filters.get(j);
								tipoFiltro = jo.get("property").getAsString();
								valorFiltro = jo.get("value").getAsString();

								switch (tipoFiltro) {
								case "dataEmissaoInicial": dataEmissaoInicial = utilFacade.dataDDIMMIYYYY(valorFiltro); break;
								case "dataEmissaoFinal": dataEmissaoFinal = utilFacade.dataDDIMMIYYYY(valorFiltro); break;
								case "dataVencimentoInicial": dataVencimentoInicial = utilFacade.dataDDIMMIYYYY(valorFiltro); break;
								case "dataVencimentoFinal": dataVencimentoFinal = utilFacade.dataDDIMMIYYYY(valorFiltro); break;						
								case "nomePessoa": nomePessoa = valorFiltro; break;
								case "idPagamento": idPagamento = Integer.parseInt(valorFiltro); break;
								case "numeroDocto": numeroDocto = valorFiltro; break;

								default: break;
								}								
							}							
							parcelas = contaFacade.pesquisarParcelas(tipoConta, idConta, numeroDocto, nomePessoa, null, dataEmissaoInicial, dataEmissaoFinal, dataVencimentoInicial, dataVencimentoFinal, idPagamento, position, max);
							//totalRegistros = contaFacade.pesquisarParcelas(tipoConta, idConta, numeroDocto, nomePessoa, null, dataEmissaoInicial, dataEmissaoFinal, dataVencimentoInicial, dataVencimentoFinal, idPagamento, 0, null).size();
							totalRegistros = contaFacade.totalRegistros(tipoConta, Parcela.class, empresa);
						} catch (Exception e) {

						}						

					}else 
						if (idConta != null) {
							parcelas = contaFacade.pesquisar(tipoConta, idConta).getParcelas();
							totalRegistros = parcelas.size();
						}				
						else{
							parcelas = contaFacade.pesquisarParcelas(tipoConta, null, null, null, null, null, null, null, null, position, max, "p.id desc");
							totalRegistros = contaFacade.totalRegistros(tipoConta, Parcela.class, empresa);
						}					

					for (Parcela parcela : parcelas) {						
						JsonObject contaJson = contaFacade.parcelaJSON(parcela);
						dados.add(contaJson);						
					} 

					auxiliar.setSuccess(true, null);
					retorno.addProperty("total", totalRegistros);
					retorno.add("data", dados);
				}
				break;
			case 3:				
				try {
					Conta contaExistente = (Conta) genericFacade.pesquisar(Conta.class, ((JsonObject) jsonRecebido.get(0)).get("idConta").getAsInt());
					List<Parcela> listParcelas = super.gerarParcelas(jsonRecebido, contaExistente);					
					for (Parcela parcela : listParcelas) {
						genericFacade.atualizar(parcela);
					}
					dados.add(jsonRecebido);//porque aparentemente estou recebendo ele sem transformar nada
					
					auxiliar.setSuccess(true, "Registro atualizado com sucesso!"); //testes 
				} catch (Exception e1) {
					e1.printStackTrace();
					auxiliar.setSuccess(false, e1.getMessage()); //testes 
				}
				retorno.add("data", dados);
				break;
			case 4: 
				JsonObject parcExcluir = (JsonObject) jsonRecebido.get(0);
				if (parcExcluir.get("parcelas")== null) {
					for (Iterator<JsonElement> iterator = jsonRecebido.iterator(); iterator.hasNext();) {
						try {
							JsonObject parcela = (JsonObject) iterator.next();
							Parcela parc = (Parcela) genericFacade.pesquisar(Parcela.class, parcela.get("id").getAsInt());
							Conta conta = parc.getConta();
							conta.getParcelas().remove(parc);
							genericFacade.atualizar(conta);
						} catch (Exception e) {
							e.getMessage();
							System.out.println("Falha ao excluir parcela");
							auxiliar.setSuccess(false, "Falha ao excluir parcela");
						}
					}
					auxiliar.setSuccess(true, "Registro excluído!");
				} else {
					auxiliar.setSuccess(true, "chamada indevida de EXCLUSÃO de parcelas isoladas. Corrigir");
				}			
				break;
				
			case 5: // 5 = quitar
				int j =0 ;
				
				for (int i = 0; i < jsonRecebido.size(); i++) {
					JsonObject objetoJson = (JsonObject) jsonRecebido.get(i);

					try {
						Integer idParcela =  objetoJson.get("id").getAsInt();
						Date dataQuitacao = utilFacade.dataYYYY_MM_DDeHHppmmppss(objetoJson.get("dataQuitacao").getAsString());  
						Parcela parcela = (Parcela) genericFacade.pesquisar(Parcela.class, idParcela); //contaFacade.pesquisarParcela(tipoConta, null, null, idParcela, null);
						parcela.setDataQuitacao(dataQuitacao);
						genericFacade.atualizar(parcela);
						j++;

					} catch (Exception e) {
						auxiliar.setMensagemRetorno("Ocorreu uma falha de processamento. Verifique o procedimento ou os dados inseridos." + "\n <br>Falha: " + e.getCause().getMessage() + ".");							
					}
				}

				auxiliar.setSuccess(true, "" + j +" Quitações efetuadas com sucesso!");
				//auxiliar.setMensagemRetorno("" + j +" Quitaçoes efetuadas com sucesso!");

				break;	
			default: break;
			}			

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//devolvendo a requisição para o navegador
		auxiliar.despachaResposta(response, retorno);

	}
	
	private JsonArray inserirParcelaAvulsa(JsonArray arrayParcelas) throws ParcelaNaoEncontradaException, ListaTabelasNaoEncontradoException, ListaTabelasInexistenteException, NamingException, ContaNaoEncontradaException, ContaInexistenteException, ObjetoNaoEncontradoException {
		GenericFacade genericFacade = new GenericFacade();
		List<Parcela> parcelas = null;
		Integer idconta = ((JsonObject) arrayParcelas.get(0)).get("idConta").getAsInt();
		
		parcelas = this.gerarParcelas(arrayParcelas, (Conta)genericFacade.pesquisar(Conta.class, idconta));
		
		for (Parcela parcela : parcelas) {
			genericFacade.cadastrar(parcela);
			for (int i = 0; i < arrayParcelas.size(); i++) {
				JsonObject jo = (JsonObject) arrayParcelas.get(i);
				if (jo.get("sequencial").getAsInt() == parcela.getSequencial()) {
					jo.addProperty("id", parcela.getId());
				}				
			}		}		
		return arrayParcelas;
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
