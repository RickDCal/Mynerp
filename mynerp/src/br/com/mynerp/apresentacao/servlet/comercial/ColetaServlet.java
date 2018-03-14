package br.com.mynerp.apresentacao.servlet.comercial;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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

import br.com.mynerp.apresentacao.facade.GenericFacade;
import br.com.mynerp.apresentacao.facade.UtilFacade;
import br.com.mynerp.apresentacao.facade.cadastro.PessoaFacade;
import br.com.mynerp.apresentacao.facade.comercial.ColetaFacade;
import br.com.mynerp.apresentacao.facade.estaticos.ListaTabelasFacade;
import br.com.mynerp.apresentacao.facade.financeiro.ContaFacade;
import br.com.mynerp.apresentacao.facade.parametro.ParametroFacade;
import br.com.mynerp.apresentacao.servlet.AuxiliarServlet;
import br.com.mynerp.negocio.exception.ColetaInexistenteException;
import br.com.mynerp.negocio.exception.DiretorioNaoEncontradoException;
import br.com.mynerp.negocio.exception.FalhaAoCriarJSONException;
import br.com.mynerp.persistencia.Cliente;
import br.com.mynerp.persistencia.Cobranca;
import br.com.mynerp.persistencia.Coleta;
import br.com.mynerp.persistencia.CondicaoPagamento;
import br.com.mynerp.persistencia.ContaReceber;
import br.com.mynerp.persistencia.Empresa;
import br.com.mynerp.persistencia.Parametro;
import br.com.mynerp.persistencia.ParametroColeta;
import br.com.mynerp.persistencia.Parcela;
import br.com.mynerp.persistencia.dao.exception.ObjetoNaoEncontradoException;
import br.com.mynerp.persistencia.enumerate.PessoaEnum;

@WebServlet("/coletaServlet")

public class ColetaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public ColetaServlet() {
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

		//String parametroString = null;
		
		Integer position = auxiliar.position;		
		Integer max = auxiliar.max;
		Integer action = auxiliar.action;
		JsonArray arrayDados = auxiliar.jsRecebido;		

		try {			
			ParametroFacade parametroFacade = new ParametroFacade();
			Parametro parametro = empresa.getParametro();
			GenericFacade genericFacade = new GenericFacade();
			ParametroColeta parametroColeta = (ParametroColeta) parametroFacade.pesquisarParametros(ParametroColeta.class, parametro);

			UtilFacade utilFacade = new UtilFacade();
			PessoaFacade pessoaFacade = new PessoaFacade();
			ColetaFacade coletaFacade = new ColetaFacade();

			switch (action) {
			case 1:
				try {					
					for (int i = 0; i < arrayDados.size(); i++) {					

						JsonObject objetoJson = (JsonObject) arrayDados.get(i);
						Integer idCliente = objetoJson.get("idPessoa").getAsInt();
						Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
						Cobranca cobranca = parametroColeta.getCobrancaPadraoColeta();

						Coleta coleta = gson.fromJson(objetoJson, Coleta.class);
						coleta.setId(0);
						coleta.setCliente(pessoaFacade.pesquisar(PessoaEnum.CLIENTE, idCliente));
						if (objetoJson.get("idCobranca") !=null && !objetoJson.get("idCobranca").getAsString().isEmpty()) {
							cobranca = (Cobranca) genericFacade.pesquisar(Cobranca.class, objetoJson.get("idCobranca").getAsInt());
							coleta.setCobranca(cobranca);
						}						
						
						if (coleta.getData() == null) {coleta.setData(new Date());}
						coletaFacade.cadastrar(coleta);

						if (objetoJson.get("indGeraCR") != null && objetoJson.get("indGeraCR").getAsString().equalsIgnoreCase("1")) {

							ContaFacade contaFacade = new ContaFacade();
							ListaTabelasFacade listaFacade = new ListaTabelasFacade();
							
							CondicaoPagamento condicao = listaFacade.pesquisarCondicaoPagamento(1, true); // teste fixo							

							try {
								ContaReceber cr = contaFacade.geraContaReceber(coleta.getCliente(), coleta, condicao, cobranca, coleta.getValorFrete(), coleta.getData(), ""+coleta.getId(), null, true);
								if (null != objetoJson.get("dataVencimento") && !objetoJson.get("dataVencimento").isJsonNull()) {
									Date dataVencimento = utilFacade.dataYYYY_MM_DDeHHppmmppss(objetoJson.get("dataVencimento").getAsString());
									for (Parcela parcela : cr.getParcelas()) {
										parcela.setDataVencimento(dataVencimento);
									}
									contaFacade.atualizar(cr);
								}


							} catch (Exception e) {
								e.printStackTrace();
								System.out.println("Falha ao gerar faturamento");
								coletaFacade.remover(coleta);
							}							
						}
						
						auxiliar.setSuccess(true, "Coleta N° " + coleta.getId() + " cadastrada com sucesso!");

						objetoJson = new JsonObject();
						objetoJson = coletaFacade.coletaJSON(coleta);
						retorno.add("data", objetoJson);							
					}										

				} catch (Exception e) {
					e.printStackTrace();
					
				}
				break;

			case 2:
				if (auxiliar.id != null) {
					Integer id = auxiliar.id;

					Coleta coleta  = coletaFacade.pesquisar(id);				
					JsonObject json = coletaFacade.coletaJSON(coleta);
					dados.add(json);
					auxiliar.setSuccess(true, null);
				} else {

					List<Coleta> coletas = new ArrayList<Coleta>();	
					Integer totalRegistros = 0;

					if (auxiliar.jsFiltros != null) {	

						Date dataInicial = null;
						Date dataFinal = null;
						Cliente cliente = null;
						String nomeCliente = null;
						String nomeLocal = null;
						String idPagamento = null;	

						String tipoFiltro = null;
						String valorFiltro = null;
						try {
							JsonArray filters = auxiliar.jsFiltros;

							for (int j = 0; j < filters.size(); j++) {
								JsonObject jo = (JsonObject) filters.get(j);
								tipoFiltro = jo.get("property").getAsString();
								valorFiltro = jo.get("value").getAsString();

								switch (tipoFiltro) {
								case "dataInicial": dataInicial = utilFacade.dataDDIMMIYYYY(valorFiltro); break;
								case "dataFinal": dataFinal = utilFacade.dataDDIMMIYYYY(valorFiltro); break;
								case "nomeCliente": nomeCliente = valorFiltro; break;
								case "codCliente": if (valorFiltro != null) {
									cliente = (Cliente) pessoaFacade.pesquisar(PessoaEnum.CLIENTE, Integer.parseInt(valorFiltro));
								} break;
								case "nomeLocal": nomeLocal = valorFiltro; break;
								case "idPagamento": idPagamento = valorFiltro; break;
								default: break;
								}								
							} 	

							coletas = coletaFacade.pesquisar(dataInicial, dataFinal, cliente, nomeCliente, nomeLocal, idPagamento, position, max);
							totalRegistros = genericFacade.totalRegistros(Coleta.class, empresa);						

						} catch (Exception e) {
							e.printStackTrace();
						}

					}else{
						coletas = coletaFacade.pesquisar(position, max);
						totalRegistros = genericFacade.totalRegistros(Coleta.class, empresa);
					}					

					for (Coleta coleta : coletas) {						
						JsonObject coletaJson = coletaFacade.coletaJSON(coleta);
						dados.add(coletaJson);			
					} 

					auxiliar.setSuccess(true, null);
					retorno.addProperty("total", totalRegistros);

				}
				retorno.add("data", dados);

				break;

			case 3:				
				try {
					StringBuilder mensagem = new StringBuilder();
					mensagem.append("Coleta N° ");
					for (int i = 0; i < arrayDados.size(); i++) {						
						JsonObject objetoJson = (JsonObject) arrayDados.get(i);
						
						if(objetoJson.get("id").getAsInt() > 0) {
							mensagem.append(objetoJson.get("id").getAsString()).append(" ");
							objetoJson.remove("cliente");
							Integer idCliente = objetoJson.get("idPessoa").getAsInt();
							Integer idCobranca = null;
							Cobranca cobranca = null;
							if(objetoJson.get("idCobranca") != null || !objetoJson.get("idCobranca").getAsString().isEmpty()) {
								idCobranca =  objetoJson.get("idCobranca").getAsInt();
								cobranca = (Cobranca) genericFacade.pesquisar(Cobranca.class, idCobranca);
							}
							//Gson gson = new Gson();
							Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

							JsonObject objetoJsonResposta = new JsonObject();

							Coleta coleta = gson.fromJson(objetoJson, Coleta.class);
							if (coleta.getData() == null) {coleta.setData(new Date());} 
							coleta.setCliente(pessoaFacade.pesquisar(PessoaEnum.CLIENTE, idCliente));
							coleta.setCobranca(cobranca);
							coletaFacade.atualizar(coleta);

							objetoJsonResposta = coletaFacade.coletaJSON(coleta);
							if (!objetoJson.get("dataVencimento").isJsonNull()) {
								objetoJsonResposta.addProperty("dataVencimento",objetoJson.get("dataVencimento").getAsString());
							}						

							dados.add(objetoJsonResposta);
							retorno.add("data", dados);
						}
					}
					mensagem.append("atualizada!");
					auxiliar.setSuccess(true, mensagem.toString());	
				} catch (Exception e) {
					e.printStackTrace();
				}				
				break;

			case 4:				
				try {

					for (int i = 0; i < arrayDados.size(); i++) {
						JsonObject objetoJson = (JsonObject) arrayDados.get(i);					

						if(objetoJson.get("id").getAsString() != "0") {
							Coleta coletaExistente =  coletaFacade.pesquisar(Integer.parseInt(objetoJson.get("id").getAsString()));
							coletaFacade.remover(coletaExistente);
							auxiliar.setSuccess(true, "Coleta N° " + objetoJson.get("id").getAsString() + " excluída com sucesso!");					
						}
					}
				} catch (NumberFormatException e) {
					e.printStackTrace();
					auxiliar.setSuccess(false, null);
				}				
				break;
			case 5: 

				try {		

					List<ParametroColeta> parametrosColeta = genericFacade.pesquisar(ParametroColeta.class, 0, 1, null);

					for (int i = 0; i < 1; i++) {
						parametroColeta = parametrosColeta.get(i);
					}

					File [] arquivos = null;

					if (parametroColeta.getCaminhoXmlCte() != null) {

						File pasta = new File(parametroColeta.getCaminhoXmlCte());

						if(pasta.isDirectory()){
							arquivos = pasta.listFiles(
									new FileFilter() { //quero só se for xml
										public boolean accept(File arq) {
											return arq.getName().endsWith(".xml");
										}
									});
						} else {
							throw new DiretorioNaoEncontradoException(pasta.getAbsolutePath());
						}				
					}else{
						auxiliar.setSuccess(false, "Importação de XML desabilitada");
					}

					if (arquivos != null && arquivos.length > 0) {
						auxiliar.setSuccess(true, null);
						auxiliar.setMensagemRetorno("Existem 0" + arquivos.length + " arquivo(s) no diretório para importação.");
					}
				} catch (ObjetoNaoEncontradoException e) {
					e.printStackTrace();
				} catch (DiretorioNaoEncontradoException e) {
					System.out.println("Diretório de importação de xml não encontrado.");
				}
			break;
			case 6: 
				try {
					List<Coleta> coletas = coletaFacade.gerarColetaXML(null, null, parametroColeta);
					if (coletas != null && coletas.size() > 0) {
						auxiliar.setSuccess(true, "Foram importadas " + coletas.size() + " coletas.");
						//auxiliar.setMensagemRetorno("Foram importadas " + coletas.size() + " coletas.");
					}				
				} catch (DiretorioNaoEncontradoException e1) {
					e1.printStackTrace();
					if (e1.getMotivo()!= null) {
						auxiliar.setMensagemRetorno(e1.getMotivo());
					}
				} catch (NamingException e1) {
					e1.printStackTrace();
				}
				break;
			default: break;
			}
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (FalhaAoCriarJSONException e) {
			e.printStackTrace();
		} catch (ColetaInexistenteException e) {
			e.printStackTrace();
		} catch (ObjetoNaoEncontradoException e) {
			e.printStackTrace();
		}
		
		//devolvendo a requisição para o navegador
		auxiliar.despachaResposta(response, retorno);

	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
