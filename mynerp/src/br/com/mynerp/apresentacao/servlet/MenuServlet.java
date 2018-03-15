package br.com.mynerp.apresentacao.servlet;

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
import javax.servlet.http.HttpSession;

import br.com.mynerp.apresentacao.facade.cadastro.PerfilUsuarioFacade;
import br.com.mynerp.apresentacao.facade.cadastro.UsuarioFacade;
import br.com.mynerp.negocio.exception.PerfilUsuarioInexistenteException;
import br.com.mynerp.negocio.exception.UsuarioInexistenteException;
import br.com.mynerp.persistencia.Menu;
import br.com.mynerp.persistencia.PerfilUsuario;
import br.com.mynerp.persistencia.Usuario;
import br.com.mynerp.persistencia.dao.exception.PerfilUsuarioNaoEncontradoException;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@WebServlet("/menuServlet")
public class MenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public MenuServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		HttpSession sessao = request.getSession();
		
		Integer idUsuario = (Integer) sessao.getAttribute("idUser");
		
		PerfilUsuario perfil = null;
		JsonArray dados = new JsonArray();
		
		if (idUsuario != null) {
			
			try {
				PerfilUsuarioFacade perfilFacade = new PerfilUsuarioFacade();
				UsuarioFacade usuarioFacade = new UsuarioFacade();
				Usuario usuario = usuarioFacade.pesquisar(idUsuario);
				perfil = perfilFacade.pesquisar(usuario.getPerfil().getId(), true);				
			
				// montando a árvore JSON com apenas dois níveis
				
				List<Menu> listMenu = perfil.getMenuUsuario();		
				Iterator<Menu> itlistMenu = listMenu.iterator();
				
				Menu itemMenu = new Menu(); 
				JsonObject menuPai = new JsonObject();
				
				while (itlistMenu.hasNext()){
					
					itemMenu = itlistMenu.next();
					menuPai = new JsonObject();
					if (itemMenu.getMenuPai() == null) {
						
						menuPai.addProperty("id", itemMenu.getId());
						menuPai.addProperty("text", itemMenu.getNome());
						menuPai.addProperty("iconCls", itemMenu.getIconCls());
						menuPai.addProperty("className", itemMenu.getClassName());
						
						JsonArray nivel2 = new JsonArray();
						if (itemMenu.getMenuFilhos().size() > 0) {
												
							List<Menu> listMenu2 = itemMenu.getMenuFilhos();
							Iterator<Menu> itlistMenu2 = listMenu2.iterator();					
							JsonObject menuFilho = new JsonObject();
							
							while (itlistMenu2.hasNext()) {
								Menu itemMenu2 = itlistMenu2.next();
								
								if (listMenu.contains(itemMenu2)) { // só adiciona o filho se ele fizer parte das permissões do perfil do usuário 
									
									menuFilho = new JsonObject();
									menuFilho.addProperty("id", itemMenu2.getId());
									menuFilho.addProperty("text", itemMenu2.getNome());
									menuFilho.addProperty("iconCls", itemMenu2.getIconCls());
									menuFilho.addProperty("className", itemMenu2.getClassName());
									menuFilho.addProperty("menuId", itemMenu2.getMenuPai().getId());
									menuFilho.addProperty("leaf", true );
									nivel2.add(menuFilho);
								}
								
							}
												
						}
						menuPai.add("items", nivel2);
						dados.add(menuPai);
					}				
				}			
			
			} catch (NamingException e) {
				e.printStackTrace();
			} catch (UsuarioInexistenteException e) {
				e.printStackTrace();
			} catch (PerfilUsuarioNaoEncontradoException e) {
				e.printStackTrace();
			} catch (PerfilUsuarioInexistenteException e) {
				e.printStackTrace();
			}			
			
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
