package br.com.mynerp.apresentacao.servlet.slack;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import br.com.mynerp.apresentacao.facade.cadastro.UsuarioFacade;
import br.com.mynerp.apresentacao.servlet.AuxiliarServlet;
import br.com.mynerp.negocio.exception.UsuarioInexistenteException;
import br.com.mynerp.persistencia.Usuario;

@WebServlet("/SlackMessageServlet")
public class SlackMessageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public SlackMessageServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		System.out.println(request.getParameterMap());
		
		
		JsonObject retorno = new JsonObject();
		AuxiliarServlet auxiliar = new AuxiliarServlet();
		auxiliar.load(request);

		JsonArray jsonRecebido = auxiliar.jsRecebido;		

		String parametroString = null;
		String webHook = null;
		String mensagem =  null;			

		try {
			UsuarioFacade usuarioFacade = new UsuarioFacade();

			//quando receber os dados na url de requisição
			parametroString = request.getParameter("webHook");
			if (parametroString != null && !parametroString.isEmpty()) {
				webHook = parametroString;		
			} 
//			parametroString = request.getParameter("idUsuarioSupra");
//			if (parametroString != null && !parametroString.isEmpty()){
//				Usuario usuario = usuarioFacade.pesquisarIdUsuarioSupra(Integer.parseInt(parametroString));				
//				if (usuario != null) {
//					webHook = usuario.getEnderecoSlack();
//				}
//			}
//
//			parametroString = request.getParameter("idUsuario");
//			if (parametroString != null && !parametroString.isEmpty()){
//				Usuario usuario = usuarioFacade.pesquisar(Integer.parseInt(parametroString));				
//				if (usuario != null) {
//					webHook = usuario.getEnderecoSlack();
//				}
//			}

			parametroString = request.getParameter("mensagem");
			if (parametroString != null && !parametroString.isEmpty()) {
				mensagem = parametroString;
			}

			if (!validaMensagem(webHook, mensagem, null)) {
				try {
					usuarioFacade = new UsuarioFacade();
					//não vieram os dados na url então deve ter mandando num json
					for (int i = 0; i < jsonRecebido.size(); i++) {
					
						Usuario usuario = new Usuario();
						JsonObject objetoJson = (JsonObject) jsonRecebido.get(i);
						if (objetoJson.get("mensagem") != null) {
							mensagem = objetoJson.get("mensagem").getAsString();
						}

						if (objetoJson.get("idUsuario") != null) {
							usuario = usuarioFacade.pesquisar(objetoJson.get("idUsuario").getAsInt());
//						} else if (objetoJson.get("idUsuarioSupra") != null){
//							usuario = usuarioFacade.pesquisarIdUsuarioSupra(objetoJson.get("idUsuarioSupra").getAsInt());
//						}
//
//						if (usuario != null) {
//							webHook = usuario.getEnderecoSlack();
//						}					
//
//						auxiliar.setMensagemRetorno(enviaMensagemSlack(webHook, mensagem));
//						if (auxiliar.getMensagemRetorno().equalsIgnoreCase("ok")) {
//							auxiliar.setSuccess(true);
						}					
					}
				} catch (NamingException e) {
					e.printStackTrace();
					auxiliar.setMensagemRetorno("Erro: NamingException");
				} catch (UsuarioInexistenteException e) {
					auxiliar.setMensagemRetorno("Erro: Falha ao localizar usuario"); 
					e.printStackTrace();
				} 			
			} else {
				auxiliar.setMensagemRetorno(enviaMensagemSlack(webHook, mensagem));
				if (auxiliar.getMensagemRetorno().equalsIgnoreCase("ok")) {
					//auxiliar.setSuccess(true);
				}				
			}			

		} catch (NamingException e1) {
			e1.printStackTrace();
			auxiliar.setMensagemRetorno("NamingException");
		} catch (NumberFormatException e) {
			auxiliar.setMensagemRetorno("NumberFormatException");
			e.printStackTrace();
//		} catch (UsuarioInexistenteException e) {
//			auxiliar.setMensagemRetorno("UsuarioInexistenteException");
//			e.printStackTrace();
		}
		auxiliar.despachaResposta(response, retorno);

	}

	private boolean validaMensagem(String webHook, String mensagem, JsonObject jsonObjetc) {
		if (webHook != null && !webHook.isEmpty() && mensagem != null && !webHook.isEmpty()) {
			return true;
		}		
		return false;
	}

	public String enviaMensagemSlack(String webHook, String mensagem) throws ParseException, IOException {

		if (!validaMensagem(webHook, mensagem, null)){
			return "Dados insuficientes ou incorretos para enviar a requisição";
		}

		JsonObject jObject = new JsonObject();

		jObject.addProperty("text", mensagem);

		Gson gson = new Gson();
		StringEntity entity = new StringEntity(gson.toJson(jObject));

		HttpPost post = new HttpPost(webHook);
		post.addHeader(HTTP.CONTENT_TYPE, "application/json");
		post.setEntity(entity);

		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpResponse response = httpClient.execute(post);

		// processando a resposta

		String resposta = null;
		Integer status = response.getStatusLine().getStatusCode();
		if (status == 200) {
			return "ok";
		}
		if (response.getEntity() != null) {
			resposta = EntityUtils.toString(response.getEntity());
		}
		return resposta;
	}




}
