package br.com.mynerp.apresentacao.servlet.financeiro;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import br.com.mynerp.apresentacao.facade.cadastro.PessoaFacade;
import br.com.mynerp.apresentacao.facade.estaticos.ListaTabelasFacade;
import br.com.mynerp.apresentacao.facade.financeiro.ContaFacade;
import br.com.mynerp.apresentacao.servlet.AuxiliarServlet;
import br.com.mynerp.negocio.exception.ContaInexistenteException;
import br.com.mynerp.negocio.exception.FalhaAoCriarJSONException;
import br.com.mynerp.negocio.exception.ListaTabelasInexistenteException;
import br.com.mynerp.persistencia.Cobranca;
import br.com.mynerp.persistencia.Conta;
import br.com.mynerp.persistencia.ContaPagar;
import br.com.mynerp.persistencia.ContaReceber;
import br.com.mynerp.persistencia.Empresa;
import br.com.mynerp.persistencia.Parcela;
import br.com.mynerp.persistencia.dao.exception.ContaNaoEncontradaException;
import br.com.mynerp.persistencia.dao.exception.ListaTabelasNaoEncontradoException;
import br.com.mynerp.persistencia.dao.exception.ObjetoNaoEncontradoException;
import br.com.mynerp.persistencia.enumerate.ContaEnum;

@WebServlet("/contaServlet")

public class ContaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public ContaServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		AuxiliarServlet auxiliar = new AuxiliarServlet();
		auxiliar.load(request);
		Empresa empresa = auxiliar.empresa;
		
		JsonObject retorno = new JsonObject();	
		JsonArray dados = new JsonArray();


		String parametroString = null;
		ContaEnum tipoConta = null;
		boolean isParcela = false;

		Integer position = auxiliar.position;		
		Integer max = auxiliar.max;

		if (request.getServletPath() != null && (request.getServletPath().equalsIgnoreCase("/contareceberServlet") || request.getServletPath().equalsIgnoreCase("/parcelareceberServlet"))) {
			tipoConta = ContaEnum.RECEBER;
		} else if (request.getServletPath() != null && (request.getServletPath().equalsIgnoreCase("/contapagarServlet")|| request.getServletPath().equalsIgnoreCase("/parcelapagarServlet"))) {
			tipoConta = ContaEnum.PAGAR;
		} 
		
		if (request.getServletPath() != null && (request.getServletPath().equalsIgnoreCase("/parcelapagarServlet") || request.getServletPath().equalsIgnoreCase("/parcelareceberServlet"))) {
			isParcela = true;
		}		

		JsonArray jsonRecebido = auxiliar.jsRecebido;		//era arrayDados
		Integer action = auxiliar.action;
		
		ContaFacade contaFacade;
		PessoaFacade pessoaFacade;
		try {
			contaFacade = new ContaFacade();
			pessoaFacade = new PessoaFacade();
			switch (action) {
			case 1:
				try {					
					for (int i = 0; i < jsonRecebido.size(); i++) {					

						Conta conta = null; 						

						if (tipoConta == ContaEnum.RECEBER) {
							conta = new ContaReceber();
						} else if (tipoConta == ContaEnum.PAGAR) {
							conta = new ContaPagar();
						}						

						JsonObject objetoJson = (JsonObject) jsonRecebido.get(i);

						JsonArray arrayParcelas = null;
						if (objetoJson.get("parcelas") != null) {
							arrayParcelas = (JsonArray) objetoJson.get("parcelas");
							arrayParcelas = auxiliar.trataUuid(arrayParcelas);
							objetoJson.remove("parcelas");
						}
						
						if (isParcela) {
							arrayParcelas = jsonRecebido;
							arrayParcelas = auxiliar.trataUuid(arrayParcelas);
						}

						//Gson gson = new Gson();
						Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
						Integer idPessoa = objetoJson.get("idPessoa").getAsInt();
						
						if (isParcela) {
							if (arrayParcelas != null) {
								List<Parcela> parcelas = null;
								Integer idConta = arrayParcelas.get(0).getAsJsonObject().get("idConta").getAsInt();
								conta = contaFacade.pesquisar(tipoConta, idConta);
								parcelas = this.gerarParcelas(arrayParcelas, conta);								
								conta.getParcelas().addAll(parcelas);
								contaFacade.atualizar(conta);

								for (Parcela parcela : parcelas) {						
									JsonObject parcelaJson = contaFacade.parcelaJSON(parcela);
									dados.add(parcelaJson);						
								}								
							}
						}
						
						else {
							if (tipoConta == ContaEnum.RECEBER) {
								ContaReceber contaReceber = gson.fromJson(objetoJson, ContaReceber.class);
								contaReceber.setId(0);
								if (contaReceber.getPessoa() == null) {								
									contaReceber.setPessoa(pessoaFacade.pesquisarPessoa(idPessoa));
								}
								List<Parcela> parcelas = null;							
								if (arrayParcelas != null) {
									parcelas = this.gerarParcelas(arrayParcelas, contaReceber);								
									contaReceber.setParcelas(parcelas);								
								}							
								contaFacade.cadastrar(contaReceber);							
								conta = contaReceber;
								
							} else if (tipoConta == ContaEnum.PAGAR) {
								ContaPagar contaPagar = gson.fromJson(objetoJson, ContaPagar.class);
								contaPagar.setId(0);
								if (contaPagar.getPessoa() == null) {
									contaPagar.setPessoa(pessoaFacade.pesquisarPessoa(idPessoa));
								}
								List<Parcela> parcelas = null;							
								if (arrayParcelas != null) {
									parcelas = this.gerarParcelas(arrayParcelas, contaPagar);								
									contaPagar.setParcelas(parcelas);								
								}							
								contaFacade.cadastrar(contaPagar);							
								conta = contaPagar;												
							}

						}
						//auxiliar.setMensagemRetorno ("Conta adicionada com sucesso! <b>Código: " + conta.getId() + ".</b>");

						if (isParcela) {
							auxiliar.setSuccess(true, " ");
							retorno.add("data", dados);
						} else {
							auxiliar.setSuccess(true, "Conta adicionada com sucesso! <b>Código: " + conta.getId() + ".</b>");
							objetoJson = new JsonObject();
							objetoJson = contaFacade.contaJSON(conta);
							retorno.add("data", objetoJson);
						}													
					}	
				} catch (Exception e) {
					e.printStackTrace();
					auxiliar.setMensagemRetorno ( auxiliar.mensagemRetorno + "\n <br>Falha: " + e.getCause().getMessage() + ".");
				}
				break;

			case 2:

				parametroString = request.getParameter("id");
				if (parametroString != null) {
					Integer id = Integer.parseInt(parametroString);

					Conta conta = contaFacade.pesquisar(tipoConta, id);				
					if (conta != null) {
						JsonObject json = contaFacade.contaJSON(conta);
						dados.add(json);
					}					
					//retorno.remove("success");
					//retorno.addProperty("success", true);
					auxiliar.setSuccess(true, null);
				} else {

					List<Conta> contas = new ArrayList<Conta>();	
					Integer totalRegistros = 0;
							
					JsonArray filters = auxiliar.jsFiltros;
					
					if (filters != null) {
						String tipoFiltro = null;
						String valorFiltro = null;
						try {					
							JsonObject jo = (JsonObject) filters.get(0);
							tipoFiltro = jo.get("property").getAsString();
							valorFiltro = jo.get("value").getAsString();
						} catch (Exception e) {
							e.printStackTrace();
							auxiliar.setMensagemRetorno(auxiliar.mensagemRetorno + "\n <br>Falha: " + e.getCause().getMessage() + ".");
						}

						//TODO tratar aplica��o dos filtros

						if (tipoFiltro != null && tipoFiltro.equalsIgnoreCase("id") && valorFiltro != null) {							
							Conta conta = contaFacade.pesquisar(tipoConta, Integer.parseInt(valorFiltro));
							contas.add(conta);
							totalRegistros = contas.size();							
						}	

						if (tipoFiltro != null && tipoFiltro.equalsIgnoreCase("nome") && valorFiltro != null) {							
							//							contas = contaFacade.pesquisar(tipoConta, position, max, valorFiltro);
							//							totalRegistros = contaFacade.pesquisar(tipoConta, 0, null, valorFiltro).size();							
						}						

						if (tipoFiltro != null && tipoFiltro.equalsIgnoreCase("nomeFantasia") && valorFiltro != null) {
							//							contas = contaFacade.pesquisar(tipoConta, position, max, valorFiltro);
							//							totalRegistros = contaFacade.pesquisar(tipoConta, 0, null, valorFiltro).size();
						}						

					}else{
						contas = contaFacade.pesquisar(tipoConta, position, max);
						totalRegistros = contaFacade.totalRegistros(tipoConta, ContaReceber.class, empresa);
					}
					
					for (Conta conta : contas) {						
						JsonObject contaJson = contaFacade.contaJSON(conta);
						dados.add(contaJson);											
					} 

					auxiliar.setSuccess(true, null);
					retorno.addProperty("total", totalRegistros);

				}
				retorno.add("data", dados);

				break;

			case 3:				
				try {
					for (int i = 0; i < jsonRecebido.size(); i++) {
						JsonObject objetoJson = (JsonObject) jsonRecebido.get(i);
						if(objetoJson.get("id").getAsInt() > 0) {

							Gson gson1 = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
							JsonArray jparcelas = (JsonArray) objetoJson.get("parcelas");
							if (jparcelas != null && jparcelas.isJsonArray()) {
								objetoJson.remove("parcelas");								
							}
							if (objetoJson.get("pessoa") != null && !objetoJson.get("pessoa").isJsonNull()) {
								objetoJson.remove("pessoa");
							}
							Integer idPessoa = null;
							if (objetoJson.get("idPessoa") != null && !objetoJson.get("idPessoa").isJsonNull()) {
								idPessoa = objetoJson.get("idPessoa").getAsInt();
							}							

							JsonObject objetoJsonResposta = new JsonObject();
							
							switch (tipoConta) {
							case RECEBER:
								ContaReceber contaReceber = gson1.fromJson(objetoJson, ContaReceber.class);
								ContaReceber contaPreExistente = (ContaReceber) contaFacade.pesquisar(tipoConta, contaReceber.getId());
								contaReceber.setPessoa(pessoaFacade.pesquisarPessoa(idPessoa));

								if (jparcelas != null && jparcelas.isJsonArray()) {
									contaReceber.setParcelas(this.gerarParcelas(jparcelas, contaReceber));
									System.out.println(jparcelas);
								} else if (jparcelas==null || jparcelas.size() == 0) {									
									contaReceber.setParcelas(contaPreExistente.getParcelas());
								}
								if (contaReceber.getDataEmissaoConta() == null) {
									contaReceber.setDataEmissaoConta(contaPreExistente.getDataEmissaoConta());
								}

								contaFacade.atualizar(contaReceber);
								contaReceber = (ContaReceber)contaFacade.pesquisar(tipoConta, contaReceber.getId()); //recarrega os dados de parcela
															
								auxiliar.setMensagemRetorno("Conta atualizada com sucesso!");

								objetoJsonResposta = contaFacade.contaJSON(contaReceber);
								if (jparcelas==null || jparcelas.size() == 0) {
									objetoJsonResposta.remove("parcelas"); //pra voltar o json de retorno igual veio
									objetoJsonResposta.remove("cliente");
									objetoJsonResposta.remove("nomePessoa");
								}								
								
								break;
							case PAGAR:
								ContaPagar contaPagar = gson1.fromJson(objetoJson, ContaPagar.class);
								ContaPagar contaPagarPreExistente = (ContaPagar) contaFacade.pesquisar(tipoConta, contaPagar.getId());
								contaPagar.setPessoa(pessoaFacade.pesquisarPessoa(idPessoa));

								if (jparcelas != null && jparcelas.isJsonArray()) {
									contaPagar.setParcelas(this.gerarParcelas(jparcelas, contaPagar));
									System.out.println(jparcelas);
								} else if (jparcelas==null || jparcelas.size() == 0) {
									
									contaPagar.setParcelas(contaPagarPreExistente.getParcelas());
								}
								if (contaPagar.getDataEmissaoConta() == null) {
									contaPagar.setDataEmissaoConta(contaPagarPreExistente.getDataEmissaoConta());
								}

								contaFacade.atualizar(contaPagar);
								contaPagar = (ContaPagar)contaFacade.pesquisar(tipoConta, contaPagar.getId()); //recarrega os dados de parcela
															
								auxiliar.setMensagemRetorno("Conta atualizada com sucesso!");

								objetoJsonResposta = contaFacade.contaJSON(contaPagar);
								if (jparcelas==null || jparcelas.size() == 0) {
									objetoJsonResposta.remove("parcelas"); //pra voltar o json de retorno igual veio
									objetoJsonResposta.remove("fornecedor");
									objetoJsonResposta.remove("nomePessoa");
								}
								break;
							default:
								break;
							}						

							dados.add(objetoJsonResposta);
							retorno.add("data", dados);
						}
					}

					auxiliar.setSuccess(true, null);
				} catch (Exception e) {
					e.printStackTrace();
					auxiliar.setMensagemRetorno (auxiliar.mensagemRetorno + "\n <br>Falha: " + e.getCause().getMessage() + ".");
				}				
				break;

			case 4:				
				try {

					for (int i = 0; i < jsonRecebido.size(); i++) {
						JsonObject objetoJson = (JsonObject) jsonRecebido.get(i);					

						if(objetoJson.get("id").getAsString() != "0") {
							Conta contaExistente = (Conta) contaFacade.pesquisar(tipoConta, Integer.parseInt(objetoJson.get("id").getAsString()));
							contaFacade.remover(contaExistente);
							auxiliar.setSuccess(true, null);
							auxiliar.setMensagemRetorno ("Conta Nº " + contaExistente.getId() + " excluída.");
						}
					}
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (ContaNaoEncontradaException e) {
					e.printStackTrace();
				}			
				break;
			default:
				break;
			}
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (FalhaAoCriarJSONException e) {
			e.printStackTrace();
		} catch (ContaNaoEncontradaException e) {
			e.printStackTrace();
		} catch (ContaInexistenteException e) {
			e.printStackTrace();
		} catch (ObjetoNaoEncontradoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//devolvendo a requisição para o navegador
		auxiliar.despachaResposta(response, retorno);		

	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected List<Parcela> gerarParcelas(JsonArray arrayParcelas, Conta conta) throws ListaTabelasNaoEncontradoException, ListaTabelasInexistenteException, NamingException {

		ListaTabelasFacade listafacade = new ListaTabelasFacade();
		List<Parcela> parcelas = null;							
		if (arrayParcelas != null) {
			parcelas = new ArrayList<Parcela>();
			JsonObject parcelaJson = new JsonObject();
			for (int j = 0; j < arrayParcelas.size(); j++) {

				parcelaJson = (JsonObject) arrayParcelas.get(j);
				
				if (parcelaJson.get("conta") != null && !parcelaJson.get("conta").isJsonNull()) {
					parcelaJson.remove("conta");
				}
				
				if (parcelaJson.get("cobranca") != null && !parcelaJson.get("cobranca").isJsonNull()) {
					parcelaJson.remove("cobranca");
				}
				
				Cobranca cobranca = null;
				
				if (parcelaJson.get("idCobranca") != null && !parcelaJson.get("idCobranca").isJsonNull()) {
					cobranca = listafacade.pesquisarCobranca(parcelaJson.get("idCobranca").getAsInt());
				}	

				Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
				Parcela parcela = gson.fromJson(parcelaJson, Parcela.class);

				if (parcela.getId() < 0) {parcela.setId(0);}

				parcela.setCobranca(cobranca);	
				parcela.setConta(conta);				
				parcelas.add(parcela);
			}										
		}
		return parcelas;		
	}

}
