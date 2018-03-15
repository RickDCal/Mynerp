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
import br.com.mynerp.negocio.exception.FalhaAoCriarJSONException;
import br.com.mynerp.persistencia.CondicaoPagamento;
import br.com.mynerp.persistencia.CondicaoParcela;
import br.com.mynerp.persistencia.dao.exception.ObjetoNaoEncontradoException;

@WebServlet("/condicaoparcelaServlet")

public class CondicaoParcelaServlet extends GenericServlet {
	private static final long serialVersionUID = 1L;

	public CondicaoParcelaServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			AuxiliarServlet auxiliar = new AuxiliarServlet();
			auxiliar.load(request);
			GenericFacade genericFacade = new GenericFacade();
			JsonObject retorno = new JsonObject();
			JsonArray dados = new JsonArray();
			JsonArray jsonRecebido = auxiliar.jsRecebido;

			switch (auxiliar.action) {
			
			case 1:
				try {
					JsonParser jsonParser = new JsonParser();
					Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
					for (int i = 0; i < jsonRecebido.size(); i++) {
						JsonObject objetoJson = (JsonObject) jsonRecebido.get(i);
						if (objetoJson.get("parcelas") != null){
							JsonArray parcelas = objetoJson.get("parcelas").getAsJsonObject().get("parcelas").getAsJsonArray();
							CondicaoPagamento condicao = (CondicaoPagamento) genericFacade.pesquisar(CondicaoPagamento.class, parcelas.get(0).getAsJsonObject().get("idCondicao").getAsInt());
							parcelas = auxiliar.trataUuid(parcelas);
							for (int j = 0; j < parcelas.size(); j++) {
								CondicaoParcela cp = gson.fromJson(parcelas.get(j), CondicaoParcela.class);
								if(cp.getId()== 0) {
									condicao.getParcelas().add(cp);
								}
								genericFacade.atualizar(condicao);
								cp = (CondicaoParcela) genericFacade.obter(CondicaoParcela.class, cp.getClientIdProperty()); //recarrega o objeto
								JsonObject objetoJsonResposta = new JsonObject();									
								objetoJsonResposta = (JsonObject) jsonParser.parse(gson.toJson(cp));
								dados.add(objetoJsonResposta);
							}								
						}
					}
					retorno.add("data", dados);
					auxiliar.setSuccess(true, "Condição de pagamento atualizada com sucesso!");
				} catch (Exception e) {
					e.printStackTrace();
					auxiliar.setCausaFalha(e.getCause().getMessage());
				}
				
			break;
			case 2:
				Integer idCondicao = null;
				JsonArray filtros = auxiliar.jsFiltros;

				for (int i = 0; i < filtros.size(); i++) {
					JsonObject filtro = (JsonObject) filtros.get(i);
					if(filtro.get("property")!= null && filtro.get("property").getAsString().equalsIgnoreCase("idCondicao")){
						idCondicao = filtro.get("value").getAsInt();
					}

				}


				//				for (JsonElement jsFiltro : filtros) {
				//					if (((JsonArray) jsFiltro).get("propety") != null){
				//						idCondicao = jsFiltro.getAsJsonObject().get("idConta").getAsInt();
				//					}
				//				}
				if (idCondicao != null) {
					CondicaoPagamento condicao = (CondicaoPagamento) genericFacade.pesquisar(CondicaoPagamento.class, idCondicao);
					try {
						dados = genericFacade.objetoJson(condicao, false).get("parcelas").getAsJsonArray();
						retorno.add("data", dados);
						auxiliar.setSuccess(true, null);
					} catch (FalhaAoCriarJSONException e) {
						auxiliar.setCausaFalha(e.getCause().getMessage());
					}
				}

				break;
			case 3:
				try {
					JsonParser jsonParser = new JsonParser();
					Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
					for (int i = 0; i < jsonRecebido.size(); i++) {
						JsonObject objetoJson = (JsonObject) jsonRecebido.get(i);
						if (objetoJson.get("parcelas") != null){
							JsonArray parcelas = objetoJson.get("parcelas").getAsJsonObject().get("parcelas").getAsJsonArray();
							parcelas = auxiliar.trataUuid(parcelas);
							for (int j = 0; j < parcelas.size(); j++) {
								CondicaoParcela cp = gson.fromJson(parcelas.get(j), CondicaoParcela.class);
								genericFacade.atualizar(cp);
								cp = (CondicaoParcela) genericFacade.pesquisar(CondicaoParcela.class, cp.getId()); //recarrega o objeto
								JsonObject objetoJsonResposta = new JsonObject();									
								objetoJsonResposta = (JsonObject) jsonParser.parse(gson.toJson(cp));
								dados.add(objetoJsonResposta);
							}								
						}
					}
					retorno.add("data", dados);
					auxiliar.setSuccess(true, "Condição de pagamento atualizada com sucesso!");
				} catch (Exception e) {
					e.printStackTrace();
					auxiliar.setCausaFalha(e.getCause().getMessage());
				}
			break;	
			
			case 4: 
				super.doPost(request, response, CondicaoParcela.class, "Parcela de Condição de Pagamento");
			break;
			default:break;
			}
			auxiliar.despachaResposta(response, retorno);

			
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (ObjetoNaoEncontradoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
