package br.com.mynerp.apresentacao.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import br.com.mynerp.apresentacao.facade.GenericFacade;
import br.com.mynerp.apresentacao.facade.comercial.ColetaFacade;
import br.com.mynerp.negocio.exception.DiretorioNaoEncontradoException;
import br.com.mynerp.persistencia.ParametroColeta;
import br.com.mynerp.persistencia.dao.exception.ObjetoNaoEncontradoException;

@WebServlet("/testeServlet")
public class TesteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public TesteServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ColetaFacade coletaFacade = null;
		try {
			coletaFacade = new ColetaFacade();
		} catch (NamingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		try {
			GenericFacade genericFacade = new GenericFacade();
			ParametroColeta parametroColeta = (ParametroColeta) genericFacade.pesquisar(ParametroColeta.class, 0, 1, null).get(0);
			
			
			
			coletaFacade.gerarColetaXML(null, null, parametroColeta);
		} catch (DiretorioNaoEncontradoException e) {
			System.out.println(e.getMotivo());
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (ObjetoNaoEncontradoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JsonElement dados = null;	

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
