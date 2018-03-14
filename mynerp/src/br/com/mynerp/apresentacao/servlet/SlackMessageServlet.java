package br.com.mynerp.apresentacao.servlet;

import java.io.IOException;

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

@WebServlet("/SlackMessageServlet")
public class SlackMessageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public SlackMessageServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AuxiliarServlet auxiliar = new AuxiliarServlet();
		auxiliar.load(request);

		JsonObject retorno = new JsonObject();
		JsonArray dados = new JsonArray();

		String parametroString = null;

		Integer position = auxiliar.position;		
		Integer max = auxiliar.max;

		JsonArray jsonRecebido = auxiliar.jsRecebido;
		Integer action = auxiliar.action;
		
		enviaMensagemSlack("https://hooks.slack.com/services/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" , "testes 1234");
	}

	public String enviaMensagemSlack(String webRook, String mensagem) throws ParseException, IOException {
		
		JsonObject json = new JsonObject();
		
		json.addProperty("text", mensagem);

		Gson gson = new Gson();
		StringEntity entity = new StringEntity(gson.toJson(json));
		HttpPost post = new HttpPost(webRook);
		post.addHeader(HTTP.CONTENT_TYPE, "application/json");
		//post.addHeader("Authentication", "1224578");
		post.setEntity(entity);

		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpResponse response = httpClient.execute(post);

		// processando a resposta

		String resposta = null;
		Integer status = response.getStatusLine().getStatusCode();
		if (response.getEntity() != null) {
			resposta = EntityUtils.toString(response.getEntity());
		}
		return mensagem;
	}




	}
