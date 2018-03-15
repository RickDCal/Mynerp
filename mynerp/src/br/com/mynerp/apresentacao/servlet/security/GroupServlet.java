package br.com.mynerp.apresentacao.servlet.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.mynerp.apresentacao.facade.cadastro.PerfilUsuarioFacade;
import br.com.mynerp.apresentacao.servlet.AuxiliarServlet;
import br.com.mynerp.negocio.exception.FalhaAoCriarJSONException;
import br.com.mynerp.negocio.exception.PerfilUsuarioInexistenteException;
import br.com.mynerp.persistencia.PerfilUsuario;
import br.com.mynerp.persistencia.dao.exception.PerfilUsuarioNaoEncontradoException;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@WebServlet("/groupServlet")
public class GroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public GroupServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		JsonObject retorno = new JsonObject();	
		JsonArray dados = new JsonArray();
		
		retorno.addProperty("success", false);		
	
		try {
			
			PerfilUsuarioFacade perfilFacade = new PerfilUsuarioFacade();
			
			List<PerfilUsuario> perfisUsuario = perfilFacade.pesquisar();
			
			Iterator<PerfilUsuario> itListPerfisUsuario = perfisUsuario.iterator();
			
			while (itListPerfisUsuario.hasNext()) {
				
				PerfilUsuario perfilUsuario = itListPerfisUsuario.next();
				JsonObject jsonPerfilUsuario = perfilFacade.perfilUsuarioJson(perfilUsuario);
				dados.add(jsonPerfilUsuario);	
				
			}	
			
			retorno.remove("success");
			retorno.addProperty("success", true);
			
		} catch (NamingException e) {
			e.printStackTrace();			
		} catch (PerfilUsuarioInexistenteException e) {
			e.printStackTrace();
		} catch (PerfilUsuarioNaoEncontradoException e) {
			e.printStackTrace();
		} catch (FalhaAoCriarJSONException e) {
			e.printStackTrace();
		}		
		
		retorno.add("data", dados);	

				
		//Montando os Header de resposta da requisição HTTP
		AuxiliarServlet auxiliar = new AuxiliarServlet();
		response = auxiliar.montaHeadResposta(response);
				
		//Escrevendo e enviando a resposta
		PrintWriter out = response.getWriter();
		out.println(retorno.toString());
		out.close();	
	
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
