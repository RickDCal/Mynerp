package br.com.mynerp.negocio;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.google.gson.JsonObject;

import br.com.mynerp.negocio.exception.EnderecoInexistenteException;
import br.com.mynerp.negocio.exception.FalhaAoCriarJSONException;
import br.com.mynerp.negocio.exception.PessoaInexistenteException;
import br.com.mynerp.persistencia.Cliente;
import br.com.mynerp.persistencia.Contato;
import br.com.mynerp.persistencia.Endereco;
import br.com.mynerp.persistencia.Fornecedor;
import br.com.mynerp.persistencia.Pessoa;
import br.com.mynerp.persistencia.dao.IClienteDAO;
import br.com.mynerp.persistencia.dao.IContatoDAO;
import br.com.mynerp.persistencia.dao.IEnderecoDAO;
import br.com.mynerp.persistencia.dao.IFornecedorDAO;
import br.com.mynerp.persistencia.dao.exception.ClienteNaoEncontradoException;
import br.com.mynerp.persistencia.dao.exception.ContatoNaoEncontradoException;
import br.com.mynerp.persistencia.dao.exception.FornecedorNaoEncontradoException;
import br.com.mynerp.persistencia.dao.exception.PessoaNaoEncontradaException;
import br.com.mynerp.persistencia.enumerate.PessoaEnum;
import br.com.mynerp.util.IUtilitiesLocal;



@Stateless
public class PessoaService implements IPessoaServiceLocal  {
	
	@EJB
	private IClienteDAO clienteDao;
	@EJB
	private IFornecedorDAO fornecedorDao;	
	@EJB
	private IUtilitiesLocal utilities;	
	@EJB
	private IEnderecoDAO enderecoDao;
	@EJB
	private IContatoDAO contatoDao;

	
	//não precisei do fornecedor ainda ... depois tem que fazer
	
	public PessoaService() {
		
	}
	

	public List pesquisar (PessoaEnum pessoaEnum) throws PessoaNaoEncontradaException, PessoaInexistenteException {
		
		if (pessoaEnum == PessoaEnum.CLIENTE) {
			try {
				return clienteDao.obter();				
			}catch (ClienteNaoEncontradoException e){
				throw new PessoaNaoEncontradaException();				
			}
		} else if (pessoaEnum == PessoaEnum.FORNECEDOR) {
			try {
				return fornecedorDao.obter();				
			}catch (FornecedorNaoEncontradoException e){
				throw new PessoaNaoEncontradaException();				
			}
		} else throw new PessoaInexistenteException();				
	}
	
	public List<Cliente> pesquisar (PessoaEnum pessoaEnum,Date dataInicial, Date dataFinal, String cidade) throws ClienteNaoEncontradoException {
		if (pessoaEnum == PessoaEnum.CLIENTE) {
			try {
				return clienteDao.obter(dataInicial, dataFinal, cidade);				
			}catch (ClienteNaoEncontradoException e){
				throw new ClienteNaoEncontradoException();				
			}
		} else throw new ClienteNaoEncontradoException();
	}
	
	public List<Fornecedor> pesquisarFornecedor (PessoaEnum pessoaEnum,Date dataInicial, Date dataFinal, String cidade) throws FornecedorNaoEncontradoException {
		if (pessoaEnum == PessoaEnum.FORNECEDOR) {
			try {
				return fornecedorDao.obter(dataInicial, dataFinal, cidade);				
			}catch (FornecedorNaoEncontradoException e){
				throw new FornecedorNaoEncontradoException();				
			}
		} else throw new FornecedorNaoEncontradoException();
	}
	
	
	
	public List pesquisar (PessoaEnum pessoaEnum, Integer position, Integer max, String letra) throws PessoaNaoEncontradaException, PessoaInexistenteException {
		
		if (pessoaEnum == PessoaEnum.CLIENTE) {
			try {
				return clienteDao.obter(position, max, letra);				
			}catch (ClienteNaoEncontradoException e){
				throw new PessoaNaoEncontradaException();				
			}
		} else if (pessoaEnum == PessoaEnum.FORNECEDOR) {
			try {
				return fornecedorDao.obter(position, max, letra);				
			}catch (FornecedorNaoEncontradoException e){
				throw new PessoaNaoEncontradaException();				
			}
		} else throw new PessoaInexistenteException();
		
				
	}
	

	public Pessoa pesquisar (PessoaEnum pessoaEnum, int idpessoa) throws PessoaNaoEncontradaException, PessoaInexistenteException {
		// este não é list este é um objeto veiculo referente a um id
		if (pessoaEnum == PessoaEnum.CLIENTE) {
			try {
				return clienteDao.obter(idpessoa);				
			}catch (ClienteNaoEncontradoException e){
				throw new PessoaNaoEncontradaException();				
			}
		} else if (pessoaEnum == PessoaEnum.FORNECEDOR) {
			try {
				return fornecedorDao.obter(idpessoa);				
			}catch (FornecedorNaoEncontradoException e){
				throw new PessoaNaoEncontradaException();				
			}
		} else throw new PessoaInexistenteException();
			
	}
	
	public Pessoa pesquisarPessoa (int idPessoa) throws PessoaNaoEncontradaException {
		return clienteDao.obterPessoa(idPessoa);		
	}
	
	public Pessoa pesquisar(PessoaEnum pessoaEnum, String usuario, String senha) throws PessoaNaoEncontradaException, PessoaInexistenteException {

		if (pessoaEnum == PessoaEnum.CLIENTE) {
			try {				
				return clienteDao.obter(usuario, senha);
			} catch (ClienteNaoEncontradoException e) {
				throw new PessoaNaoEncontradaException();
			}
		}  else if (pessoaEnum == PessoaEnum.FORNECEDOR) {
			try {				
				return fornecedorDao.obter(usuario, senha);
			} catch (FornecedorNaoEncontradoException e) {
				throw new PessoaNaoEncontradaException();
			}
		}  else
			throw new PessoaInexistenteException();

	}  // não coloquei este método para o funcionario ainda pois devo desenvolver uma outra forma para autenticá-lo
	
	
	public Pessoa pesquisar(PessoaEnum pessoaEnum, String cpfCnpj) throws PessoaNaoEncontradaException, PessoaInexistenteException {

		if (pessoaEnum == PessoaEnum.CLIENTE) {
			try {				
				return clienteDao.obter(cpfCnpj);
			} catch (ClienteNaoEncontradoException e) {
				throw new PessoaNaoEncontradaException();
			}
		}  else if (pessoaEnum == PessoaEnum.FORNECEDOR) {
			try {				
				return fornecedorDao.obter(cpfCnpj);
			} catch (FornecedorNaoEncontradoException e) {
				throw new PessoaNaoEncontradaException();
			}
		}  else
			throw new PessoaInexistenteException();

	}  // não coloquei este método para o funcionario ainda pois devo desenvolver uma outra forma para autenticá-lo
	
	public List<Cliente> pesquisarClientesCnpj(String cpfCnpj, Integer position, Integer max) throws PessoaNaoEncontradaException, ClienteNaoEncontradoException {
	
			try {
				return clienteDao.obterCnpj(cpfCnpj, position, max);				
			}catch (ClienteNaoEncontradoException e){
				throw new ClienteNaoEncontradoException();				
			}
		
	}
	
	public List<Fornecedor> pesquisarFornecedoresCnpj (String cpfCnpj, Integer position, Integer max) throws FornecedorNaoEncontradoException {
			try {
				return fornecedorDao.obterCnpj(cpfCnpj, position, max);				
			}catch (FornecedorNaoEncontradoException e){
				throw new FornecedorNaoEncontradoException();				
			}
	}	
	
	public Cliente pesquisar(String usuario, String senha) throws ClienteNaoEncontradoException, PessoaInexistenteException {
		
		try {				
				return clienteDao.obter(usuario, senha);
			} 	catch (ClienteNaoEncontradoException e) {
				throw new PessoaInexistenteException();
			}
	} // não coloquei este método para o funcionario ainda pois devo desenvolver uma outra forma para autenticá-lo
	
	public Fornecedor pesquisarFornecedor(String usuario, String senha) throws FornecedorNaoEncontradoException, PessoaInexistenteException {
		
		try {				
				return fornecedorDao.obter(usuario, senha);
			} 	catch (FornecedorNaoEncontradoException e) {
				throw new PessoaInexistenteException();
			}
	}
	
	public void cadastrar (Pessoa pessoa) throws PessoaInexistenteException {
	
		if (pessoa instanceof Cliente) {
		
			clienteDao.inserir((Cliente)pessoa);
		
		}  else if (pessoa instanceof Fornecedor) {
		
			fornecedorDao.inserir((Fornecedor)pessoa);
		
		}  else
		
			throw new PessoaInexistenteException();
	}

	public void atualizar (Pessoa pessoa) throws PessoaInexistenteException {
	
		if (pessoa instanceof Cliente) {
		
			clienteDao.alterar((Cliente)pessoa);
		
		} else if (pessoa instanceof Fornecedor) {
		
			fornecedorDao.alterar((Fornecedor)pessoa);
		
		} else throw new PessoaInexistenteException();
	}
	
	
	public void remover (Pessoa pessoa) throws PessoaInexistenteException {
		
		if (pessoa instanceof Cliente) {
		
			clienteDao.remover((Cliente)pessoa);
		
		} else if (pessoa instanceof Fornecedor) {
		
			fornecedorDao.remover((Fornecedor)pessoa);
		
		} else throw new PessoaInexistenteException();
	}

	
	public JsonObject pessoaJson(Pessoa pessoa) throws FalhaAoCriarJSONException, PessoaInexistenteException, EnderecoInexistenteException, ContatoNaoEncontradoException {

		JsonObject pessoaJson = new JsonObject();
		Contato contatoPrincipal = contatoDao.obterPrincipal(pessoa.getId());
		Endereco enderecoPrincipal = enderecoDao.obterPrincipal(pessoa.getId());
		
		
		if (pessoa instanceof Cliente) {			
						
			pessoaJson.addProperty("id", pessoa.getId());
			pessoaJson.addProperty("nome", pessoa.getNome());
			pessoaJson.addProperty("nomeFantasia", pessoa.getNomeFantasia());
			pessoaJson.addProperty("cpfCnpj", pessoa.getCpfCnpj());
			pessoaJson.addProperty("pfPj", pessoa.getPfPj());
			pessoaJson.addProperty("idsexo", pessoa.getIdsexo());
			pessoaJson.addProperty("nascimento", pessoa.getNascimento() != null ? utilities.dataMM_DD_YYYY(((Cliente) pessoa).getNascimento()) : "");	
			
			pessoaJson.addProperty("email", contatoPrincipal != null ? contatoPrincipal.getEmail() : "");
			pessoaJson.addProperty("ddd", contatoPrincipal != null ? contatoPrincipal.getDdd() : "");
			pessoaJson.addProperty("telefone", contatoPrincipal != null ? contatoPrincipal.getTelefone() : "");
			pessoaJson.addProperty("dddCelular", contatoPrincipal != null ? contatoPrincipal.getDddCelular() : "");
			pessoaJson.addProperty("celular", contatoPrincipal != null ? contatoPrincipal.getCelular() : "");
			pessoaJson.addProperty("dddAdicional", contatoPrincipal != null ? contatoPrincipal.getDddAdicional() : "");
			pessoaJson.addProperty("telefoneAdicional", contatoPrincipal != null ? contatoPrincipal.getTelefoneAdicional() : "");
			
			pessoaJson.addProperty("tipoLogradouro", enderecoPrincipal != null ? enderecoPrincipal.getTipoLogradouro() : "");
			pessoaJson.addProperty("logradouro", enderecoPrincipal != null ? enderecoPrincipal.getLogradouro() : "");
			pessoaJson.addProperty("numero", enderecoPrincipal != null ? enderecoPrincipal.getNumero() : "");
			pessoaJson.addProperty("complemento", enderecoPrincipal != null ? enderecoPrincipal.getComplemento() : "");
			pessoaJson.addProperty("bairro", enderecoPrincipal != null ? enderecoPrincipal.getBairro() : "");
			pessoaJson.addProperty("nomeCidade", enderecoPrincipal != null ? enderecoPrincipal.getNomeCidade() : "");
			pessoaJson.addProperty("ufSigla", enderecoPrincipal != null ? enderecoPrincipal.getUfSigla() : "");	
			pessoaJson.addProperty("cep", enderecoPrincipal != null && enderecoPrincipal.getCep() !=null ? enderecoPrincipal.getCep() : "");	
			
			pessoaJson.addProperty("vencimento", ((Cliente) pessoa).getVencimento());
			pessoaJson.addProperty("dataCadastro", pessoa.getDataCadastro() != null ? utilities.dataMM_DD_YYYY(((Cliente) pessoa).getDataCadastro()) : "");
			pessoaJson.addProperty("clientIdProperty", pessoa.getClientIdProperty());
		
		} else if (pessoa instanceof Fornecedor) {
		
			pessoaJson.addProperty("id", pessoa.getId());
			pessoaJson.addProperty("nome", pessoa.getNome());
			pessoaJson.addProperty("nomeFantasia", pessoa.getNomeFantasia());
			pessoaJson.addProperty("cpfCnpj", pessoa.getCpfCnpj());
			pessoaJson.addProperty("pfPj", pessoa.getPfPj());
			pessoaJson.addProperty("idsexo", pessoa.getIdsexo());
			pessoaJson.addProperty("nascimento", pessoa.getNascimento() != null ? utilities.dataMM_DD_YYYY(((Fornecedor) pessoa).getNascimento()) : "");	
			
			pessoaJson.addProperty("email", contatoPrincipal != null ? contatoPrincipal.getEmail() : "");
			pessoaJson.addProperty("ddd", contatoPrincipal != null ? contatoPrincipal.getDdd() : "");
			pessoaJson.addProperty("telefone", contatoPrincipal != null ? contatoPrincipal.getTelefone() : "");
			pessoaJson.addProperty("dddCelular", contatoPrincipal != null ? contatoPrincipal.getDddCelular() : "");
			pessoaJson.addProperty("celular", contatoPrincipal != null ? contatoPrincipal.getCelular() : "");
			pessoaJson.addProperty("dddAdicional", contatoPrincipal != null ? contatoPrincipal.getDddAdicional() : "");
			pessoaJson.addProperty("telefoneAdicional", contatoPrincipal != null ? contatoPrincipal.getTelefoneAdicional() : "");
			
			pessoaJson.addProperty("tipoLogradouro", enderecoPrincipal != null ? enderecoPrincipal.getTipoLogradouro() : "");
			pessoaJson.addProperty("logradouro", enderecoPrincipal != null ? enderecoPrincipal.getLogradouro() : "");
			pessoaJson.addProperty("numero", enderecoPrincipal != null ? enderecoPrincipal.getNumero() : "");
			pessoaJson.addProperty("complemento", enderecoPrincipal != null ? enderecoPrincipal.getComplemento() : "");
			pessoaJson.addProperty("bairro", enderecoPrincipal != null ? enderecoPrincipal.getBairro() : "");
			pessoaJson.addProperty("nomeCidade", enderecoPrincipal != null ? enderecoPrincipal.getNomeCidade() : "");
			pessoaJson.addProperty("ufSigla", enderecoPrincipal != null ? enderecoPrincipal.getUfSigla() : "");	
			pessoaJson.addProperty("cep", enderecoPrincipal != null && enderecoPrincipal.getCep() !=null ? enderecoPrincipal.getCep() : "");	
			
			pessoaJson.addProperty("vencimento", ((Fornecedor) pessoa).getVencimento());
			pessoaJson.addProperty("dataCadastro", pessoa.getDataCadastro() != null ? utilities.dataMM_DD_YYYY(((Fornecedor) pessoa).getDataCadastro()) : "");
			pessoaJson.addProperty("clientIdProperty", pessoa.getClientIdProperty());
			
		} else throw new PessoaInexistenteException();

		return pessoaJson;

	}
	
}
		
	

