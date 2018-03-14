package br.com.mynerp.apresentacao.servlet.relatorio;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import br.com.mynerp.apresentacao.facade.UtilFacade;
import br.com.mynerp.apresentacao.facade.comercial.ColetaFacade;
import br.com.mynerp.apresentacao.servlet.AuxiliarServlet;
import br.com.mynerp.persistencia.Coleta;

@WebServlet("/testeRelatorioServlet")
public class TesteRelatorioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public TesteRelatorioServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		JsonArray dados = new JsonArray();		

		try {
			ColetaFacade coletaFacade = new ColetaFacade();
			UtilFacade util = new UtilFacade();
			List<Coleta> listColetas = coletaFacade.pesquisar(util.dataYYYY_MM_DD("2016-01-01"), new Date(), null, null, null, null, null,null);
			Map<String,Double> mapColetas = new HashMap<String, Double>();
			Map<String,Integer> mapColetasQtd = new HashMap<String, Integer>();


			for (Iterator<Coleta> iterator = listColetas.iterator(); iterator.hasNext();) {
				Coleta coleta = (Coleta) iterator.next();
				String data = util.dataDDIMMIYYYY(coleta.getData()).substring(3, 10);
				if (!mapColetas.containsKey(data)) {
					mapColetas.put(data, coleta.getValorFrete());
				} else {
					mapColetas.replace(data, mapColetas.get(data).sum(mapColetas.get(data), coleta.getValorFrete()));
					//mapColetas.get(data).sum(mapColetas.get(data), coleta.getValorFrete());
				}

				if (!mapColetasQtd.containsKey(data)) {
					mapColetasQtd.put(data, 1);
				} else {
					mapColetasQtd.replace(data, mapColetasQtd.get(data).sum(mapColetasQtd.get(data), 1));
				}
			}
			JsonObject jo = new JsonObject();

			for (String data: mapColetas.keySet()) {
				jo = new JsonObject();
				jo.addProperty("mes", data);
				jo.addProperty("valor", mapColetas.get(data));
				jo.addProperty("quantidade", mapColetasQtd.get(data));

				dados.add(jo);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				


		//Finalizando o Json de Resposta
		JsonObject retorno = new JsonObject();
		retorno.add("data", dados);		

		//Montando os Header de resposta da requisição HTTP
		AuxiliarServlet auxiliar = new AuxiliarServlet();
		response = auxiliar.montaHeadResposta(response);

		//Escrevendo e enviando a resposta
		PrintWriter out = response.getWriter();
		out.println(retorno.toString());
		out.close();		
	}













}
