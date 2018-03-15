package br.com.mynerp.apresentacao.servlet.parametro;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import br.com.mynerp.apresentacao.facade.GenericFacade;
import br.com.mynerp.apresentacao.facade.cadastro.UsuarioFacade;
import br.com.mynerp.apresentacao.facade.parametro.ParametroFacade;
import br.com.mynerp.apresentacao.servlet.AuxiliarServlet;
import br.com.mynerp.negocio.exception.FalhaAoCriarJSONException;
import br.com.mynerp.negocio.exception.UsuarioInexistenteException;
import br.com.mynerp.persistencia.Empresa;
import br.com.mynerp.persistencia.Parametro;
import br.com.mynerp.persistencia.ParametroColeta;
import br.com.mynerp.persistencia.ParametroFinanceiro;
import br.com.mynerp.persistencia.Usuario;
import br.com.mynerp.persistencia.dao.exception.ObjetoNaoEncontradoException;

@WebServlet("/parametroServlet")
public class ParametroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public ParametroServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	private JsonArray listaOpcoesParametro(Usuario usuario, Empresa empresa) throws FalhaAoCriarJSONException, NamingException {
		UsuarioFacade usuarioFacade = new UsuarioFacade();
		if (usuario != null && usuarioFacade.possuiPermissaoMenu(empresa, 1, usuario)) {//id do menu de parametro = 1
			ParametroFacade parametroFacade = new ParametroFacade();	
			return parametroFacade.menuParametro();				
		} else {
			return null;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		AuxiliarServlet auxiliar = new AuxiliarServlet();
		auxiliar.load(request);
		Empresa empresa = auxiliar.empresa;

		JsonObject retorno = new JsonObject();
		JsonArray dados = new JsonArray();

		//String parametroString = null;

		//Integer position = auxiliar.position;		
		//Integer max = auxiliar.max;

		//JsonArray jsonRecebido = auxiliar.jsRecebido;
		Integer action = auxiliar.action;
		
		String classe = request.getParameter("classe");
		if (classe == null) {
			classe = (String) request.getAttribute("classe");
		}

		try {
			HttpSession sessao = request.getSession();
			UsuarioFacade usuarioFacade = new UsuarioFacade();
			GenericFacade genericFacade = new GenericFacade();
			ParametroFacade parametroFacade = new ParametroFacade();
			Integer idUsuario = (Integer) sessao.getAttribute("idUser");		
			Parametro parametro = empresa.getParametro();
			Usuario usuario = null;

			if (idUsuario != null) {
				usuario = usuarioFacade.pesquisar(idUsuario); 
			}

			dados = new JsonArray();
			JsonObject objetoJson = new JsonObject();

			switch (action) 
			{
			case 1:	break;
			case 2:	
				switch (classe.toLowerCase()) {
				case "parametrocoleta":
					ParametroColeta parametroColeta = (ParametroColeta) genericFacade.pesquisar(ParametroColeta.class, parametro.getId()); 
					objetoJson = parametroFacade.parametroColetaJson(parametroColeta);				
					dados.add(objetoJson);
					auxiliar.setSuccess(true, null);
				break;
				case "parametrofinanceiro":
					ParametroFinanceiro parametroFinanceiro = (ParametroFinanceiro) genericFacade.pesquisar(ParametroFinanceiro.class, parametro.getId()); 
					objetoJson = parametroFacade.parametroFinanceiroJson(parametroFinanceiro);				
					dados.add(objetoJson);
					auxiliar.setSuccess(true, null);
				break;
				default: break;
				}
			break;
			case 3:	break;
			case 4:	break;
			case 5:
				try {
					dados = this.listaOpcoesParametro(usuario, empresa);
					auxiliar.setSuccess(true, null);
					if (dados == null) {
						auxiliar.setMensagemRetorno("Usuário não encontrado ou sem permissões de acesso.");
					} else {
						auxiliar.setMensagemRetorno("Sucesso");
					}					
				} catch (FalhaAoCriarJSONException e) {
					e.printStackTrace();					
				}
			break;
			default: break;
			}
		} catch (NamingException | UsuarioInexistenteException e1) {
			e1.printStackTrace();
			auxiliar.setMensagemRetorno("Falha ao carregar opções de parâmetro");
		} catch (FalhaAoCriarJSONException e1) {
			e1.printStackTrace();
		} catch (ObjetoNaoEncontradoException e1) {
			e1.printStackTrace();
		}			

		//Finalizando o Json de Resposta
		retorno.add("data", dados);

		//devolvendo a requisição para o navegador
		auxiliar.despachaResposta(response, retorno);		
	}

}
