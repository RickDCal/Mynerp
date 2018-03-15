package br.com.mynerp.apresentacao.servlet.estaticos;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.mynerp.apresentacao.servlet.GenericServlet;
import br.com.mynerp.persistencia.Cobranca;

@WebServlet("/cobrancaServlet")

public class CobrancaServlet extends GenericServlet {
	private static final long serialVersionUID = 1L;

	public CobrancaServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			super.doPost(request, response, Cobranca.class, "Forma de Cobrança");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
