package br.com.mynerp.apresentacao.servlet.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonObject;

import br.com.mynerp.apresentacao.facade.UtilFacade;
import br.com.mynerp.apresentacao.facade.cadastro.ContatoFacade;
import br.com.mynerp.apresentacao.facade.cadastro.EnderecoFacade;
import br.com.mynerp.apresentacao.facade.cadastro.UsuarioFacade;
import br.com.mynerp.apresentacao.facade.estaticos.EmpresaFacade;
import br.com.mynerp.negocio.exception.ContatoInexistenteException;
import br.com.mynerp.negocio.exception.EnderecoInexistenteException;
import br.com.mynerp.negocio.exception.FalhaAoCriarJSONException;
import br.com.mynerp.negocio.exception.UsuarioInexistenteException;
import br.com.mynerp.persistencia.Contato;
import br.com.mynerp.persistencia.Empresa;
import br.com.mynerp.persistencia.Endereco;
import br.com.mynerp.persistencia.Parametro;
import br.com.mynerp.persistencia.Usuario;
import br.com.mynerp.persistencia.dao.exception.EmpresaNaoEncontradaException;

@WebServlet("/userAuthenticationServlet")
public class UserAuthenticationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserAuthenticationServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		response.setContentType("text/html");
		response.setHeader("Cache-control", "no-cache, no-store");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "-1");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST,GET");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type");
		response.setHeader("Access-Control-Max-Age", "86400");	

		PrintWriter out = response.getWriter();
		JsonObject retorno = new JsonObject();	
		retorno.addProperty("success", false);
		retorno.addProperty("msg", "Usuário ou senha informados são inválidos");

		/*recebendo os parâmetros*/
		String action = request.getParameter("action");

		if(action != null && action.equalsIgnoreCase("logout")) {

			HttpSession sessao =  request.getSession();	

			retorno.remove("msg");

			try {
				sessao.invalidate();
				retorno.remove("success");
				retorno.addProperty("success", true);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	finally {
				out.println(retorno.toString());

			}		    

		} else if (action != null && action.equalsIgnoreCase("sessionRefresh")) {
			HttpSession sessao =  request.getSession();	
			sessao.setMaxInactiveInterval(1000*60*20); //20 minutos sem fazer uma requisição ao servidor

		} else {

			Usuario usuario = new Usuario();	


			String userName = request.getParameter("user");
			String password = request.getParameter("password");		

			try {

				UtilFacade utilFacade = new UtilFacade();
				UsuarioFacade usuarioFacade = new UsuarioFacade();
				EmpresaFacade empresaFacade = new EmpresaFacade();

				String senha = utilFacade.criptografiaSenha(password);				
				usuario = usuarioFacade.pesquisar(userName, senha);

				if (usuario != null) {
					JsonObject usuarioJson = usuarioFacade.usuarioJson(usuario);					

					String idEmpresa = request.getParameter("idEmpresa");
					if (idEmpresa == null || idEmpresa.isEmpty()) {
						idEmpresa = "1";
					}

					HttpSession sessao =  request.getSession();
					sessao.setMaxInactiveInterval(1000*60*20); //20 minutos sem fazer uma requisição ao servidor
					sessao.setAttribute("idUser", usuario.getId());
					sessao.setAttribute("nome", usuario.getApelido());
					sessao.setAttribute("user", usuario.getUserName());
					sessao.setAttribute("apelido", usuario.getApelido());

					Empresa empresa = null;					

					try {						
						empresa = empresaFacade.pesquisar(Integer.parseInt(idEmpresa));
					} catch (NumberFormatException e) {							
						e.printStackTrace();
					} catch (EmpresaNaoEncontradaException e) {
						e.printStackTrace();
					} finally {
						sessao.setAttribute("empresa", empresa);
					}

					Parametro parametro =  null;
					parametro = empresa.getParametro();
					sessao.setAttribute("Parametro", parametro);

					try {
						if (parametro != null && empresa != null && empresa.getPessoa() != null) {
							ContatoFacade contatoFacade = new ContatoFacade();
							EnderecoFacade enderecoFacade = new EnderecoFacade();
							Contato contato = contatoFacade.pesquisarPrincipal(empresa.getPessoa().getId());
							Endereco endereco = enderecoFacade.pesquisarPrincipal(empresa.getPessoa().getId());

							String dadosRodape = null;
							
							if (contato != null && endereco != null) {
								dadosRodape = empresa.getPessoa().getNome() + " - " + contato.getDdd() + "-"
										+ contato.getTelefone() + " - " + endereco.getNomeCidade() + "-"
										+ endereco.getUfSigla() + " - " + contato.getEmail();
							}
							
							sessao.setAttribute("dadosRodape", dadosRodape);
							sessao.setAttribute("logoRelatorio", empresa.getLogoRelatorio());
						} 
					} catch (ContatoInexistenteException | EnderecoInexistenteException e) {
						e.printStackTrace();
					} finally {

					}
					retorno.add("usuario", usuarioJson);
					retorno.remove("success");
					retorno.remove("msg");
					retorno.addProperty("success", true);
				}	

			}  catch (UsuarioInexistenteException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			} catch (FalhaAoCriarJSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} finally {
				out.println(retorno.toString());			
			}
		}
		out.close();		
	}
}
