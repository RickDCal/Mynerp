package br.com.mynerp.apresentacao.servlet.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import br.com.mynerp.apresentacao.facade.UtilFacade;
import br.com.mynerp.apresentacao.facade.cadastro.PerfilUsuarioFacade;
import br.com.mynerp.apresentacao.facade.cadastro.UsuarioFacade;
import br.com.mynerp.apresentacao.servlet.AuxiliarServlet;
import br.com.mynerp.negocio.exception.FalhaAoCriarJSONException;
import br.com.mynerp.negocio.exception.PerfilUsuarioInexistenteException;
import br.com.mynerp.negocio.exception.UsuarioInexistenteException;
import br.com.mynerp.persistencia.Parametro;
import br.com.mynerp.persistencia.PerfilUsuario;
import br.com.mynerp.persistencia.Usuario;
import br.com.mynerp.persistencia.dao.exception.PerfilUsuarioNaoEncontradoException;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2 , // 2MB se for maior que isso não fica na memória, grava em disco. mantendo só para referência
maxFileSize = 1024 * 512, // 512 kb tamanho maximo do arquivo que quero aceitar
maxRequestSize = 1024 * 1024 * 4 // 4MB tamanho máximo da requisição
		)
@WebServlet("/userServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserServlet() {
		super();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession sessao = request.getSession();	
		Parametro parametro = (Parametro) sessao.getAttribute("Parametro");	

		AuxiliarServlet auxiliar = new AuxiliarServlet();	
		JsonObject retorno = new JsonObject();			
		retorno.addProperty("success", false);

		int action = Integer.parseInt(request.getParameter("action")); /**1=create, 2=read, 3=update, 4=destroy**/
		
		UsuarioFacade usuarioFacade = null;
		UtilFacade utilfacade = null;

		switch (action) {
		case 1: { //create

			try {
				usuarioFacade = new UsuarioFacade();
				utilfacade = new UtilFacade();
			} catch (NamingException e1) {
				e1.printStackTrace();
			}

			Usuario usuarioRecebido = requestToUsuario(request.getParameterMap());	
			String SenhaInicial = utilfacade.dataDDMMYYYY(new Date())+usuarioRecebido.getUserName();
			usuarioRecebido.setPassword(utilfacade.criptografiaSenha(SenhaInicial));			
			
			Part parte = request.getPart("arquivoFoto"); 

			String nomeFoto = null;

			if (parte != null) {							
				nomeFoto = utilfacade.obterNomeArquivo(parte);				
				if (nomeFoto !=null && !nomeFoto.equalsIgnoreCase("")) {
					usuarioRecebido.setNomeArquivoFoto(nomeFoto);						
				} else {
					usuarioRecebido.setNomeArquivoFoto("undefinedUser.jpg");
				}
			}

			try {				
				usuarioFacade.cadastrar(usuarioRecebido);
				if (nomeFoto !=null && !nomeFoto.equalsIgnoreCase("")) {
					utilfacade.salvarArquivoDisco(parte, parametro.getCaminhoFotoPerfil(),nomeFoto);
				}
				retorno.remove("success");
				retorno.addProperty("success", true);

			} catch (UsuarioInexistenteException e) {
				e.printStackTrace();
			} 

		}	break;

		case 2: {//read
			JsonArray dados = new JsonArray();
			String start = request.getParameter("start");
			String limit = request.getParameter("limit");
			Integer position = null;
			Integer max = null;

			if (start != null) {
				position = Integer.parseInt(start);
			}			
			if (limit != null) {
				max = Integer.parseInt(limit);
			}

			try {
				UsuarioFacade usuariofacade = new UsuarioFacade();
				List<Usuario> usuarios = new ArrayList<Usuario>();
				String idUser = request.getParameter("id");

				if (idUser != null && idUser != "0") {					
					try {
						Integer id = Integer.parseInt(idUser);
						Usuario usuario = usuariofacade.pesquisar(id);
						usuarios.add(usuario);
					} catch (NumberFormatException e) {
						e.printStackTrace();
					}
				} else {
					usuarios = usuariofacade.pesquisar(position,max);
				}			

				Iterator<Usuario> itListUsuarios = usuarios.iterator();

				while (itListUsuarios.hasNext()) {
					Usuario usuario = itListUsuarios.next();
					JsonObject jsonUsuario = usuariofacade.usuarioJson(usuario);
					dados.add(jsonUsuario);	
				}	

				retorno.remove("success");
				retorno.addProperty("success", true);
				retorno.add("data", dados);

			} catch (NamingException e) {
				e.printStackTrace();			
			} catch (UsuarioInexistenteException e) {
				e.printStackTrace();
			} catch (FalhaAoCriarJSONException e) {
				e.printStackTrace();
			}

		} break;

		case 3: {

			Usuario usuarioRecebido = requestToUsuario(request.getParameterMap());

			Usuario usuarioExistente = null;
			String fotoAtual = null;

			if (usuarioRecebido.getId() != 0) {
				try {
					usuarioFacade = new UsuarioFacade();
					usuarioExistente = usuarioFacade.pesquisar(usuarioRecebido.getId()); 
					usuarioRecebido.setPassword(usuarioExistente.getPassword());
					if (usuarioRecebido.getPerfil() == null) {
						usuarioRecebido.setPerfil(usuarioExistente.getPerfil());
					}
					if (usuarioRecebido.getNomeArquivoFoto() == null) {
						usuarioRecebido.setNomeArquivoFoto(usuarioExistente.getNomeArquivoFoto());
					}					
					fotoAtual = usuarioRecebido.getNomeArquivoFoto();				
				} catch (NamingException e) {
					e.printStackTrace();
				} catch (UsuarioInexistenteException e) {
					e.printStackTrace();
				}			

				Part parte = request.getPart("arquivoFoto"); 
				String nomeFoto = null;

				if (parte != null) {				
					try {
						utilfacade = new UtilFacade();
						nomeFoto = utilfacade.obterNomeArquivo(parte);				
						if (nomeFoto !=null && !nomeFoto.equalsIgnoreCase("")) {
							usuarioRecebido.setNomeArquivoFoto(nomeFoto);						
						}				
					} catch (NamingException e) {
						e.printStackTrace();
					}			
				}

				try {
					usuarioFacade = new UsuarioFacade();
					usuarioFacade.atualizar(usuarioRecebido); 
					if (nomeFoto != null && !nomeFoto.equalsIgnoreCase(fotoAtual) && !nomeFoto.equalsIgnoreCase("")) {
						utilfacade.salvarArquivoDisco(parte, parametro.getCaminhoFotoPerfil(),nomeFoto);
						if (!fotoAtual.equalsIgnoreCase("undefinedUser.jpg")) {
							utilfacade.removerArquivoDisco(parametro.getCaminhoFotoPerfil(), fotoAtual);
						}					
					}
					retorno.remove("success");
					retorno.addProperty("success", true);					
				} catch (UsuarioInexistenteException e) {
					e.printStackTrace();
				} catch (NamingException e) {
					e.printStackTrace();
				}				
			}			
		} break;

		case 4: {

			String dados = request.getParameter("data").replace("[", "").replace("]", "");			
			JsonObject usuarioJson = new JsonObject();			
			JsonParser parser = new JsonParser();
			
			try {
				usuarioJson = (JsonObject) parser.parse(dados);			
				Gson gson = new Gson();
				Usuario usuarioRecebido = gson.fromJson(usuarioJson, Usuario.class);
				Usuario usuarioExistente = null;

				if (usuarioRecebido.getId() != 0) {
					try {
						usuarioFacade = new UsuarioFacade();
						utilfacade = new UtilFacade();
						usuarioExistente = usuarioFacade.pesquisar(usuarioRecebido.getId()); 
						usuarioExistente.setDataExclusao(new Date());

						String fotoAtual = usuarioExistente.getNomeArquivoFoto();
						if (!fotoAtual.equalsIgnoreCase("undefinedUser.jpg")) {
							utilfacade.removerArquivoDisco(parametro.getCaminhoFotoPerfil(), fotoAtual);
						}
						usuarioFacade.atualizar(usuarioExistente);
						retorno.remove("success");
						retorno.addProperty("success", true);
					} catch (NamingException e) {
						e.printStackTrace();
					} catch (UsuarioInexistenteException e) {
						e.printStackTrace();
					}
				}
			} catch (JsonSyntaxException e) {
				e.printStackTrace();
			}			
		}

		default: break;
		}				

		//Montando os Header de resposta da requisição HTTP
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

	private Usuario requestToUsuario(Map<String, String[]> parameterMap) {
		JsonObject usuarioJson = new JsonObject();
		PerfilUsuario perfil = null;

		for (Entry<String, String[]> entry : parameterMap.entrySet()) {			
			String param = entry.getKey();			
			for (String valor : entry.getValue()) {				

				try {
					byte byteText[] = valor.getBytes("ISO_8859_1");
					valor = new String(byteText, "UTF-8"); // estava vindo desconfigurado qdo tinha acento, etc.
//					if (param.equalsIgnoreCase("id")) {
//						valor = "0";
//					}					
					usuarioJson.addProperty(param, valor);				
					if (param.equalsIgnoreCase("idPerfil") && valor != null) {						
						PerfilUsuarioFacade perfilFacade = new PerfilUsuarioFacade();
						perfil = perfilFacade.pesquisar(Integer.parseInt(valor));							
						try {												
						} catch (NumberFormatException e) {
							e.printStackTrace();
						}					
					}					
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
				} catch (PerfilUsuarioNaoEncontradoException e1) {
					e1.printStackTrace();
				} catch (PerfilUsuarioInexistenteException e1) {
					e1.printStackTrace();
				} catch (NamingException e1) {
					e1.printStackTrace();
				}
			}			
		}

		Gson gson = new Gson();
		Usuario usuario = gson.fromJson(usuarioJson, Usuario.class);
		usuario.setPerfil(perfil);
		return usuario;
	}
}
