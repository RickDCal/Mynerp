package br.com.mynerp.apresentacao.servlet.estaticos;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import br.com.mynerp.apresentacao.facade.GenericFacade;
import br.com.mynerp.apresentacao.servlet.AuxiliarServlet;
import br.com.mynerp.apresentacao.servlet.GenericServlet;
import br.com.mynerp.persistencia.CondicaoPagamento;
import br.com.mynerp.persistencia.CondicaoParcela;

@WebServlet("/condicaopagamentoServlet")

public class CondicaoPagamentoServlet extends GenericServlet {
	private static final long serialVersionUID = 1L;

	public CondicaoPagamentoServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			AuxiliarServlet auxiliar = new AuxiliarServlet();
			auxiliar.load(request);

			if (auxiliar.action == 1) {

				GenericFacade genericFacade= new GenericFacade();
				Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
				JsonArray jsonRecebido = auxiliar.jsRecebido;
				JsonObject retorno = new JsonObject();
				
				try {					
					for (int i = 0; i < jsonRecebido.size(); i++) {					

						JsonObject objetoJson = (JsonObject) jsonRecebido.get(i);
						JsonArray parcelas = objetoJson.get("parcelas").getAsJsonArray();
						parcelas = auxiliar.trataUuid(parcelas);
						for (int j = 0; j < parcelas.size(); j++) {							
							parcelas.get(j).getAsJsonObject().remove("idCondicao");
						}	
						
						//objetoJson.remove("parcelas");
						
						CondicaoPagamento condicao = gson.fromJson(objetoJson, CondicaoPagamento.class);
						for (CondicaoParcela parcela : condicao.getParcelas()) {
							parcela.setIdCondicao(condicao.getId());
						}
						
						condicao = (CondicaoPagamento) genericFacade.cadastrar(condicao); 				

						objetoJson = new JsonObject();	
						JsonParser jsonParser = new JsonParser();
						objetoJson = (JsonObject) jsonParser.parse(gson.toJson(condicao));
						
						auxiliar.setSuccess(true, "Condição de pagamento adicionada com sucesso <b>Código: " + condicao.getId() + ".</b>");
						retorno.add("data", objetoJson);							
					}										

				} catch (Exception e) {
					e.printStackTrace();
					auxiliar.setCausaFalha(e.getCause().getMessage());
				}
				
				
				
				
	 

//				if (parametroString != null) {
//					JsonObject objetoJson = new JsonObject();
//					JsonParser jparser = new JsonParser();
//					try {
//						objetoJson = (JsonObject) jparser.parse(parametroString);
//						arrayDados.add(objetoJson);
//					} catch (Exception e) {
//						arrayDados = (JsonArray) jparser.parse(parametroString);
//					}
//					for (int i = 0; i < arrayDados.size(); i++) {					
//						//arrayDados.get(i).getAsJsonObject().remove("id");
//						JsonArray parcelas = arrayDados.get(i).getAsJsonObject().get("parcelas").getAsJsonArray();
//						for (int j = 0; j < arrayDados.size(); j++) {							
//							parcelas.get(j).getAsJsonObject().remove("idCondicao");
//							parcelas.get(j).getAsJsonObject().addProperty("clientIdProperty", parcelas.get(j).getAsJsonObject().get("id").getAsString());
//							parcelas.get(j).getAsJsonObject().remove("id");
//						}
//					}
//				} 
//				request.setAttribute("data", arrayDados.toString());
				auxiliar.despachaResposta(response, retorno);
			}else{
				super.doPost(request, response, CondicaoPagamento.class, "Condição de Pagamento" );
			}
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
