package br.com.mynerp.apresentacao.servlet.cadastro;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import br.com.mynerp.apresentacao.facade.GenericFacade;
import br.com.mynerp.apresentacao.facade.cadastro.ContatoFacade;
import br.com.mynerp.apresentacao.facade.cadastro.EnderecoFacade;
import br.com.mynerp.apresentacao.facade.cadastro.PessoaFacade;
import br.com.mynerp.apresentacao.servlet.AuxiliarServlet;
import br.com.mynerp.negocio.exception.ContatoInexistenteException;
import br.com.mynerp.negocio.exception.EnderecoInexistenteException;
import br.com.mynerp.negocio.exception.FalhaAoCriarJSONException;
import br.com.mynerp.negocio.exception.PessoaInexistenteException;
import br.com.mynerp.persistencia.Cliente;
import br.com.mynerp.persistencia.Contato;
import br.com.mynerp.persistencia.Empresa;
import br.com.mynerp.persistencia.Endereco;
import br.com.mynerp.persistencia.Fornecedor;
import br.com.mynerp.persistencia.Pessoa;
import br.com.mynerp.persistencia.dao.exception.ContatoNaoEncontradoException;
import br.com.mynerp.persistencia.dao.exception.ObjetoNaoEncontradoException;
import br.com.mynerp.persistencia.dao.exception.PessoaNaoEncontradaException;
import br.com.mynerp.persistencia.enumerate.PessoaEnum;

@WebServlet("/pessoaServlet")

public class PessoaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public PessoaServlet() {
		super();
	}



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		AuxiliarServlet auxiliar = new AuxiliarServlet();
		auxiliar.load(request);
		Empresa empresa = auxiliar.empresa;
		
		JsonObject retorno = new JsonObject();	
		JsonArray dados = new JsonArray();		

		//String parametroString = null;
		PessoaEnum tipoPessoa = null;
		
		Integer position = auxiliar.position;		
		Integer max = auxiliar.max;
		Class classe = null;
				
		if (request.getServletPath() != null && request.getServletPath().equalsIgnoreCase("/clienteServlet")) {
			tipoPessoa = PessoaEnum.CLIENTE;
			classe = Cliente.class;
		} else if (request.getServletPath() != null && request.getServletPath().equalsIgnoreCase("/fornecedorServlet")) {
			tipoPessoa = PessoaEnum.FORNECEDOR;
			classe = Fornecedor.class;
		}		

		JsonArray arrayDados = auxiliar.jsRecebido;
		
		Integer action = auxiliar.action;		

		PessoaFacade pessoaFacade;

		try {
			GenericFacade genericFacade = new GenericFacade();
			
			pessoaFacade = new PessoaFacade();

			switch (action) {
			case 1:
				try {	
					StringBuilder mensagem = new StringBuilder();
					for (int i = 0; i < arrayDados.size(); i++) {					
						if (i > 0) mensagem.append("\n");
						Pessoa pessoa = null; 
						
						if (tipoPessoa == PessoaEnum.CLIENTE) {
							mensagem.append("Cliente Nº ");
							pessoa = new Cliente();
						} else if (tipoPessoa == PessoaEnum.FORNECEDOR) {
							mensagem.append("Fornecedor Nº ");
							pessoa = new Fornecedor();
						}						
						
						JsonObject objetoJson = (JsonObject) arrayDados.get(i);
						
						//Gson gson = new Gson();
						Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
					
						if (tipoPessoa == PessoaEnum.CLIENTE) {
							
							Cliente cliente = gson.fromJson(objetoJson, Cliente.class);
							cliente.setId(0);
							cliente.setDataCadastro(new Date());
							pessoaFacade.cadastrar(cliente);
							pessoa = cliente;

						} else if (tipoPessoa == PessoaEnum.FORNECEDOR) {
							Fornecedor fornecedor = gson.fromJson(objetoJson, Fornecedor.class);
							fornecedor.setId(0);
							fornecedor.setDataCadastro(new Date());
							pessoaFacade.cadastrar(fornecedor);
							pessoa = fornecedor;
						}
						mensagem.append(pessoa.getId()).append(" cadastrado com sucesso!");
												
						ContatoFacade contatoFacade = new ContatoFacade();
						Contato contato = new Contato();
						String element = null;
						element = objetoJson.get("email") != null ?  objetoJson.get("email").getAsString() : null;
						contato.setEmail(element);
						element = objetoJson.get("ddd") != null ?  objetoJson.get("ddd").getAsString() : null;
						contato.setDdd(element);
						element = objetoJson.get("telefone") != null ?  objetoJson.get("telefone").getAsString() : null;
						contato.setTelefone(element);
						element = objetoJson.get("dddCelular") != null ?  objetoJson.get("dddCelular").getAsString() : null;
						contato.setDddCelular(element);
						element = objetoJson.get("celular") != null ?  objetoJson.get("celular").getAsString() : null;
						contato.setCelular(element);
						element = objetoJson.get("dddAdicional") != null ?  objetoJson.get("dddAdicional").getAsString() : null;
						contato.setDddAdicional(element);
						element = objetoJson.get("telefoneAdicional") != null ?  objetoJson.get("telefoneAdicional").getAsString() : null;
						contato.setTelefoneAdicional(element);
												
						if (contato != null) {
							contato.setContatoPrincipal(true);
							contato.setPessoa(pessoa);
							contato.setNome(pessoa.getNome());
							contatoFacade.cadastrar(contato);
						}
						
						EnderecoFacade enderecoFacade = new EnderecoFacade();
						Endereco endereco = new Endereco();
						element = objetoJson.get("tipoLogradouro") != null ?  objetoJson.get("tipoLogradouro").getAsString() : null;
						endereco.setTipoLogradouro(element);
						element = objetoJson.get("logradouro") != null ?  objetoJson.get("logradouro").getAsString() : null;
						endereco.setLogradouro(element);
						element = objetoJson.get("numero") != null ?  objetoJson.get("numero").getAsString() : null;
						endereco.setNumero(element);
						element = objetoJson.get("complemento") != null ?  objetoJson.get("complemento").getAsString() : null;
						endereco.setComplemento(element);
						element = objetoJson.get("bairro") != null ?  objetoJson.get("bairro").getAsString() : null;
						endereco.setBairro(element);
						element = objetoJson.get("nomeCidade") != null ?  objetoJson.get("nomeCidade").getAsString() : null;
						endereco.setNomeCidade(element);
						element = objetoJson.get("ufSigla") != null ?  objetoJson.get("ufSigla").getAsString() : null;
						endereco.setUfSigla(element);
						element = objetoJson.get("cep") != null ?  objetoJson.get("cep").getAsString() : null;
						endereco.setCep(element == null? null: element);
						
						if (endereco != null) {
							endereco.setIndPadrao(true);
							endereco.setPessoa(pessoa);
							endereco.setTipoEndereco(1);
							enderecoFacade.cadastrar(endereco);
						}						
						
						auxiliar.setSuccess(true, mensagem.toString());
						retorno.add("data", pessoaFacade.pessoaJson(pessoa));							
					}										

				} catch (Exception e) {
					e.printStackTrace();
				}
				break;

			case 2:
				
				if (auxiliar.id != null) {
					Pessoa pessoa = pessoaFacade.pesquisar(tipoPessoa, auxiliar.id);				
					if (pessoa != null) {						
						dados.add(pessoaFacade.pessoaJson(pessoa));
					}					
					auxiliar.setSuccess(true, null);
				} else {
					List<Pessoa> pessoas = new ArrayList<Pessoa>();	
					Integer totalRegistros = 0;
					
					
					if (auxiliar.jsFiltros != null) {						

						String tipoFiltro = null;
						String valorFiltro = null;
						try {
							JsonObject jo = (JsonObject) auxiliar.jsFiltros.get(0);
							tipoFiltro = jo.get("property").getAsString();
							valorFiltro = jo.get("value").getAsString();

						} catch (Exception e) {

						}

						//TODO tratar aplica��o dos filtros
						
						if (tipoFiltro != null && tipoFiltro.equalsIgnoreCase("id") && valorFiltro != null) {							
							Pessoa pessoa = pessoaFacade.pesquisar(tipoPessoa, Integer.parseInt(valorFiltro));
							pessoas.add(pessoa);
							totalRegistros = pessoas.size();							
						}	
						
						if (tipoFiltro != null && tipoFiltro.equalsIgnoreCase("nome") && valorFiltro != null) {							
							pessoas = pessoaFacade.pesquisar(tipoPessoa, position, max, valorFiltro);
							totalRegistros = genericFacade.totalRegistros(classe, empresa);							
						}						

						if (tipoFiltro != null && tipoFiltro.equalsIgnoreCase("nomeFantasia") && valorFiltro != null) {
							pessoas = pessoaFacade.pesquisar(tipoPessoa, position, max, valorFiltro);
							totalRegistros = genericFacade.totalRegistros(classe, empresa);
						}						
											
					}else{
						pessoas = pessoaFacade.pesquisar(tipoPessoa, position, max, null);
						totalRegistros = genericFacade.totalRegistros(classe, empresa);
					}					
					
					for (Pessoa pessoa : pessoas) {						
							JsonObject pessoaJson = pessoaFacade.pessoaJson(pessoa);
							dados.add(pessoaJson);			
					} 
					
					auxiliar.setSuccess(true, null);
					retorno.addProperty("total", totalRegistros);
					
				}
				retorno.add("data", dados);
					
				break;

			case 3:				
				try {
					StringBuilder mensagem = new StringBuilder();
					for (int i = 0; i < arrayDados.size(); i++) {
						if ( i > 0) mensagem.append("\n");
						JsonObject objetoJson = (JsonObject) arrayDados.get(i);
						if(objetoJson.get("id").getAsInt() > 0) {
													
							Gson gson1 = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
							
							Contato contato  = gson1.fromJson(objetoJson, Contato.class);
							Endereco endereco = gson1.fromJson(objetoJson, Endereco.class);
							
							List<Contato> contatos = new ArrayList<Contato>();
							List<Endereco> enderecos = new ArrayList<Endereco>();	
							
							JsonObject objetoJsonResposta = new JsonObject();
							
							switch (tipoPessoa) {
							case CLIENTE:
								Cliente cliente = gson1.fromJson(objetoJson, Cliente.class);
								if (cliente.getDataCadastro() == null) {cliente.setDataCadastro(new Date());} 
								contato.setPessoa(cliente);
								contato.setContatoPrincipal(true);
								contato.setNome(cliente.getNome());
								contatos.add(contato);
								endereco.setPessoa(cliente);
								endereco.setIndPadrao(true);
								enderecos.add(endereco);
//								
								cliente.setContatos(contatos);
								cliente.setEnderecos(enderecos);
								pessoaFacade.atualizar(cliente);
								mensagem.append("Cliente N° ").append(cliente.getId()).append(" atualizado!");
																
								objetoJsonResposta = pessoaFacade.pessoaJson(cliente);
								break;
							case FORNECEDOR:
								Fornecedor fornecedor = gson1.fromJson(objetoJson, Fornecedor.class);
								if (fornecedor.getDataCadastro() == null) {fornecedor.setDataCadastro(new Date());}
								contato.setPessoa(fornecedor);
								contato.setContatoPrincipal(true);
								contato.setNome(fornecedor.getNome());
								contatos.add(contato);
								endereco.setPessoa(fornecedor);
								endereco.setIndPadrao(true);
								enderecos.add(endereco);
//								
								fornecedor.setContatos(contatos);
								fornecedor.setEnderecos(enderecos);
								pessoaFacade.atualizar(fornecedor);
								mensagem.append("Fornecedor N° ").append(fornecedor.getId()).append(" atualizado!");
								
								objetoJsonResposta = pessoaFacade.pessoaJson(fornecedor);
								break;
							default:
								break;
							}	
							
							dados.add(objetoJsonResposta);
							retorno.add("data", dados);
						}
					}

					auxiliar.setSuccess(true, mensagem.toString());	
				} catch (Exception e) {
					e.printStackTrace();
				}				
				break;

			case 4:				
				try {

					for (int i = 0; i < arrayDados.size(); i++) {
						JsonObject objetoJson = (JsonObject) arrayDados.get(i);					
						
						if(objetoJson.get("id").getAsString() != "0") {
							Pessoa pessoaExistente = (Pessoa) pessoaFacade.pesquisar(tipoPessoa, Integer.parseInt(objetoJson.get("id").getAsString()));
							pessoaFacade.remover(pessoaExistente);
							StringBuilder mensagem = new StringBuilder();
							mensagem.append("Cadastro Nº ").append(objetoJson.get("id")).append(" excluído.");
							auxiliar.setSuccess(true, mensagem.toString());					
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					auxiliar.setCausaFalha(e.getCause().toString());					
					auxiliar.setSuccess(false, null);					
					
				} 
				break;
			default:
				break;
			}
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (FalhaAoCriarJSONException e) {
			e.printStackTrace();
		} catch (PessoaNaoEncontradaException e) {
			e.printStackTrace();
		} catch (PessoaInexistenteException e) {
			e.printStackTrace();
		} catch (ContatoInexistenteException e) {
			e.printStackTrace();
		} catch (EnderecoInexistenteException e) {
			e.printStackTrace();
		} catch (ContatoNaoEncontradoException e) {
			e.printStackTrace();
		} catch (ObjetoNaoEncontradoException e) {
			e.printStackTrace();
		} finally {
			//devolvendo a requisição para o navegador
			auxiliar.despachaResposta(response, retorno);	
		}

		
		

	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
