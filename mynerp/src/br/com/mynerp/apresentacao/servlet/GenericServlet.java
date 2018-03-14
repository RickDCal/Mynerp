package br.com.mynerp.apresentacao.servlet;

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
import com.google.gson.JsonParser;

import br.com.mynerp.apresentacao.facade.GenericFacade;
import br.com.mynerp.persistencia.Empresa;
import br.com.mynerp.persistencia.dao.exception.ObjetoNaoEncontradoException;

@WebServlet("/genericServlet")

public class GenericServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/*SOMENTE OBJETOS MUITO SIMPLES PODEM USAR ESTA CLASSE*/

//	@EJB
//	private GenericFacade genericFacade;

	public GenericServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	@SuppressWarnings("unchecked")
	protected <T> void  doPost(HttpServletRequest request, HttpServletResponse response, Class<T> classe, String nomeObjeto) throws ServletException, IOException, NamingException {

		GenericFacade genericFacade = new GenericFacade();
		
		AuxiliarServlet auxiliar = new AuxiliarServlet();
		auxiliar.load(request);
		Empresa empresa = auxiliar.empresa;

		JsonObject retorno = new JsonObject();
		JsonArray dados = new JsonArray();

		//String parametroString = null;

		Integer position = auxiliar.position;		
		Integer max = auxiliar.max;

		JsonArray jsonRecebido = auxiliar.jsRecebido;
		Integer action = auxiliar.action;

		try {

			JsonParser jsonParser = new JsonParser();
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

			switch (action) {
			case 1:
				try {					
					for (int i = 0; i < jsonRecebido.size(); i++) {					

						JsonObject objetoJson = (JsonObject) jsonRecebido.get(i);

						//Gson gson = new Gson();
						
						objetoJson.remove("id");
						objetoJson.addProperty("id", 0);						
						
						Object objeto = gson.fromJson(objetoJson, classe);
						
						genericFacade.cadastrar(objeto); 				

						objetoJson = new JsonObject();						
						objetoJson = (JsonObject) jsonParser.parse(gson.toJson(objeto));
						
						String novoId = objetoJson.get("id").getAsString();		
						
						auxiliar.setSuccess(true, "Registro adicionado com sucesso! " + nomeObjeto + " <b>Código: " + novoId + ".</b>");
						retorno.add("data", objetoJson);							
					}										

				} catch (Exception e) {
					e.printStackTrace();
					auxiliar.setCausaFalha(e.getCause().getMessage());
				}
				break;

			case 2:
				
				if (auxiliar.id != null) {
					Integer id = auxiliar.id;

					Object objeto = genericFacade.pesquisar(classe, id);				
					if (objeto != null) {
						JsonObject objetoJson = new JsonObject();
						try {
							objetoJson = genericFacade.objetoJson(objeto, false);// (JsonObject) jsonParser.parse(gson.toJson(objeto));
						} catch (Exception e) {
							try {
								objetoJson = genericFacade.objetoJson(objeto, true);
							} catch (Exception e1) {
								System.out.println("Objeto Json " + nomeObjeto + " gerado utilizando @Expose.");
							}
						}
						dados.add(objetoJson);
					}					
					auxiliar.setSuccess(true, null);
				} else {

					@SuppressWarnings("rawtypes")
					List objetos = new ArrayList();	
					Integer totalRegistros = 0;			

					if (auxiliar.jsFiltros != null) {						

						String tipoFiltro = null;
						String valorFiltro = null;
						try {
							JsonObject objetoJson = (JsonObject) auxiliar.jsFiltros.get(0);
							tipoFiltro = objetoJson.get("property").getAsString();
							valorFiltro = objetoJson.get("value").getAsString();

						} catch (Exception e) {
							e.printStackTrace();
							auxiliar.setCausaFalha(e.getCause().getMessage());							
						}

						//TODO tratar aplica��o dos filtros

						if (tipoFiltro != null && tipoFiltro.equalsIgnoreCase("id") && valorFiltro != null) {							
							Object objeto = genericFacade.pesquisar(classe,(Integer.parseInt(valorFiltro)));
							objetos.add(objeto);
							totalRegistros = objetos.size();							
						}						

					}else{
						objetos = genericFacade.pesquisar(classe, position, max, null);
						totalRegistros = genericFacade.totalRegistros(classe, empresa);
					}

					for (Object objeto : objetos) {	
						
						JsonObject objectJson = null;
						try {
							objectJson = genericFacade.objetoJson(objeto, false);
						} catch (Exception e) {
							try {
								objectJson = genericFacade.objetoJson(objeto, true);
							} catch (Exception ex) {
								ex.printStackTrace();
								auxiliar.setSuccess(false, "Falha ao criar objeto Json");
							}
						}
						if (objectJson != null) {
//							if (classe == CondicaoPagamento.class) {
//
//								for (int j = 0; j < objectJson.get("parcelas").getAsJsonArray().size(); j++) {
//									objectJson.get("parcelas").getAsJsonArray().get(j).getAsJsonObject().remove("id");
//									objectJson.get("parcelas").getAsJsonArray().get(j).getAsJsonObject().addProperty("id","cond" + objectJson.get("id").getAsString()+ "n" +j);
//								}
//							}		
							
							dados.add(objectJson);	
						}																	
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

							JsonObject objetoJsonResposta = new JsonObject();

								Object objeto = gson.fromJson(objetoJson, classe);

								genericFacade.atualizar(objeto);
								objeto = genericFacade.pesquisar(classe, objetoJson.get("id").getAsInt()); //recarrega o objeto

								auxiliar.setMensagemRetorno("Registro de " + nomeObjeto + " atualizado com sucesso!");

								objetoJsonResposta = (JsonObject) jsonParser.parse(gson.toJson(objeto));													

							dados.add(objetoJsonResposta);
							retorno.add("data", dados);
						}
					}
	
					auxiliar.setSuccess(true, "Registro de " + nomeObjeto + " atualizado com sucesso!" );
				} catch (Exception e) {
					e.printStackTrace();
					auxiliar.setCausaFalha(e.getCause().getMessage());
				}				
				break;

			case 4:				
				try {

					for (int i = 0; i < jsonRecebido.size(); i++) {
						JsonObject objetoJson = (JsonObject) jsonRecebido.get(i);					

						if(objetoJson.get("id").getAsString() != "0") {
							Object objetoExistente =  genericFacade.pesquisar(classe, objetoJson.get("id").getAsInt());
							genericFacade.remover(objetoExistente);
							auxiliar.setSuccess(true, "Registro de " + nomeObjeto + " Nº " + objetoJson.get("id").getAsString() + " excluído.");
						}
					}
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (ObjetoNaoEncontradoException e) {
					e.printStackTrace();
				}			
				break;
			default:
				break;
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ObjetoNaoEncontradoException e) {
			e.printStackTrace();
			auxiliar.despachaResposta(response, retorno);
		}

		//devolvendo a requisição para o navegador
		auxiliar.despachaResposta(response, retorno);	

	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
