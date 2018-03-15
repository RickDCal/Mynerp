package br.com.mynerp.negocio;

import javax.ejb.Local;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import br.com.mynerp.negocio.exception.FalhaAoCriarJSONException;
import br.com.mynerp.persistencia.Empresa;
import br.com.mynerp.persistencia.Parametro;
import br.com.mynerp.persistencia.ParametroColeta;
import br.com.mynerp.persistencia.ParametroFinanceiro;
import br.com.mynerp.persistencia.dao.exception.ObjetoNaoEncontradoException;
import br.com.mynerp.persistencia.dao.exception.ParametroNaoEncontradoException;

@Local
public interface IParametroServiceLocal {

	public Parametro pesquisar(Empresa empresa) throws ParametroNaoEncontradoException;
	
	public JsonArray menuParametro() throws FalhaAoCriarJSONException;
	
	public JsonObject parametroJson(Parametro parametro) throws FalhaAoCriarJSONException;
	
	public JsonObject parametroColetaJson(ParametroColeta parametroColeta) throws FalhaAoCriarJSONException;
	
	public JsonObject parametroFinanceiroJson(ParametroFinanceiro parametroFinanceiro) throws FalhaAoCriarJSONException;
	
	public <T> Object obterParametros(Class<T> classe, Parametro parametro) throws ObjetoNaoEncontradoException;

}