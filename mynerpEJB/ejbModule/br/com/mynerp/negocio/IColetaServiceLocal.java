package br.com.mynerp.negocio;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.naming.NamingException;

import com.google.gson.JsonObject;

import br.com.mynerp.negocio.exception.ColetaInexistenteException;
import br.com.mynerp.negocio.exception.DiretorioNaoEncontradoException;
import br.com.mynerp.negocio.exception.FalhaAoCriarJSONException;
import br.com.mynerp.persistencia.Cliente;
import br.com.mynerp.persistencia.Coleta;
import br.com.mynerp.persistencia.ParametroColeta;
import br.com.mynerp.persistencia.dao.exception.ObjetoNaoEncontradoException;


@Local
public interface IColetaServiceLocal {


	public List pesquisar(Integer position, Integer max) throws ColetaInexistenteException;
	
	public List<Coleta> pesquisar (Date dataInicial, Date dataFinal, Cliente cliente, String nomeCliente, String nomeLocal, String idPagamento, Integer position, Integer max) throws ColetaInexistenteException;
	
	public Coleta pesquisar(int id) throws ColetaInexistenteException;

	public void cadastrar(Coleta coleta) throws ColetaInexistenteException;

	public void atualizar(Coleta coleta) throws ColetaInexistenteException;

	public void remover(Coleta coleta) throws ColetaInexistenteException;
	
	public JsonObject coletaJSON(Coleta coleta) throws FalhaAoCriarJSONException;
	
	public List<Coleta> gerarColetaXML(File arquivoXml, String nomeDiretorio, ParametroColeta parametroColeta) throws DiretorioNaoEncontradoException, NamingException, ObjetoNaoEncontradoException;

}