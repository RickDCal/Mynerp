package br.com.mynerp.apresentacao.facade.parametro;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import br.com.mynerp.negocio.IParametroServiceLocal;
import br.com.mynerp.negocio.exception.FalhaAoCriarJSONException;
import br.com.mynerp.persistencia.Empresa;
import br.com.mynerp.persistencia.Parametro;
import br.com.mynerp.persistencia.ParametroColeta;
import br.com.mynerp.persistencia.ParametroFinanceiro;
import br.com.mynerp.persistencia.dao.exception.ObjetoNaoEncontradoException;
import br.com.mynerp.persistencia.dao.exception.ParametroNaoEncontradoException;

public class ParametroFacade {
	
	private Properties p;
	private Context c;
	
	public IParametroServiceLocal service;
	
	public ParametroFacade() throws NamingException {
		
		p = new Properties();
		c = new InitialContext(p);
		service = (IParametroServiceLocal)c.lookup("java:global/mynerpEAR/mynerpEJB/ParametroService");
		
	}
	
	public Parametro pesquisar(Empresa empresa) throws ParametroNaoEncontradoException {
		return service.pesquisar(empresa);
	}
	
	public JsonArray menuParametro() throws FalhaAoCriarJSONException {
		return service.menuParametro();
	}
	
	public JsonObject parametroJson(Parametro parametro) throws FalhaAoCriarJSONException {
		return service.parametroJson(parametro);
	}
	
	public JsonObject parametroColetaJson(ParametroColeta parametroColeta) throws FalhaAoCriarJSONException {
		return service.parametroColetaJson(parametroColeta);
	}
	
	public JsonObject parametroFinanceiroJson(ParametroFinanceiro parametroFinanceiro) throws FalhaAoCriarJSONException {
		return service.parametroFinanceiroJson(parametroFinanceiro);
	}
	
	public <T> Object pesquisarParametros(Class<T> classe, Parametro parametro) throws ObjetoNaoEncontradoException {
		/**Este método pode ser substituído pelo Generic pois as classes de parâmetro devem ter semmpre o mesmo id do objeto parametro que elas possuem
		 * Exemmplo: ParametroColeta parametroColeta = (ParametroColeta) genericFacade.pesquisar(ParametroColeta.class, parametro.getId());
		 **/
		return service.obterParametros(classe, parametro);
	}
	
}
