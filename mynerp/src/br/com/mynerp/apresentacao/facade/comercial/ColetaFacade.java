package br.com.mynerp.apresentacao.facade.comercial;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.google.gson.JsonObject;

import br.com.mynerp.negocio.IColetaServiceLocal;
import br.com.mynerp.negocio.exception.ColetaInexistenteException;
import br.com.mynerp.negocio.exception.DiretorioNaoEncontradoException;
import br.com.mynerp.negocio.exception.FalhaAoCriarJSONException;
import br.com.mynerp.persistencia.Cliente;
import br.com.mynerp.persistencia.Coleta;
import br.com.mynerp.persistencia.ParametroColeta;
import br.com.mynerp.persistencia.dao.exception.ObjetoNaoEncontradoException;

public class ColetaFacade {
	
	private Properties p;
	private Context c;
	
	public IColetaServiceLocal service;
	
	public ColetaFacade() throws NamingException {
		
		p = new Properties();
		c = new InitialContext(p);
		service = (IColetaServiceLocal)c.lookup("java:global/mynerpEAR/mynerpEJB/ColetaService");
		
	}
	
	@SuppressWarnings("rawtypes")
	public List pesquisar (Integer position, Integer max) throws ColetaInexistenteException, ColetaInexistenteException {
		return service.pesquisar(position, max); // fez aqui um delegate, ou seja passou esta tarefa para o outro m�todo l� na produto service na camada de neg�cio
	}

	public List<Coleta> pesquisar(Date dataInicial, Date dataFinal, Cliente cliente, String nomeCliente, String nomeLocal, String idPagamento, Integer position, Integer max) throws ColetaInexistenteException {
		return service.pesquisar(dataInicial, dataFinal, cliente, nomeCliente, nomeLocal, idPagamento, position, max); // fez aqui um delegate, ou seja passou esta tarefa para o outro m�todo l� na produto service na camada de neg�cio
	}
	
	public void atualizar (Coleta coleta) throws ColetaInexistenteException {
		service.atualizar(coleta);
	}
	
	public void cadastrar (Coleta coleta) throws ColetaInexistenteException {
		service.cadastrar(coleta);
	}
	
	public Coleta pesquisar (int id) throws ColetaInexistenteException {
		return service.pesquisar(id);
	}
	
	public void remover (Coleta coleta) throws ColetaInexistenteException {
		service.remover(coleta);
	}
	
	public JsonObject coletaJSON(Coleta coleta) throws FalhaAoCriarJSONException {
		return service.coletaJSON(coleta);		
	}
	
	public List<Coleta> gerarColetaXML(File arquivoXml, String nomeDiretorio, ParametroColeta parametroColeta) throws DiretorioNaoEncontradoException, NamingException, ObjetoNaoEncontradoException {
		return service.gerarColetaXML(arquivoXml, nomeDiretorio, parametroColeta);
	}
	
	
	
}
