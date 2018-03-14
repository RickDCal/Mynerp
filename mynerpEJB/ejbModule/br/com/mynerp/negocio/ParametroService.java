package br.com.mynerp.negocio;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import br.com.mynerp.negocio.exception.FalhaAoCriarJSONException;
import br.com.mynerp.persistencia.Cobranca;
import br.com.mynerp.persistencia.CondicaoPagamento;
import br.com.mynerp.persistencia.Empresa;
import br.com.mynerp.persistencia.Parametro;
import br.com.mynerp.persistencia.ParametroColeta;
import br.com.mynerp.persistencia.ParametroFinanceiro;
import br.com.mynerp.persistencia.dao.IParametroDAO;
import br.com.mynerp.persistencia.dao.exception.ObjetoNaoEncontradoException;
import br.com.mynerp.persistencia.dao.exception.ParametroNaoEncontradoException;

@Stateless
public class ParametroService implements IParametroServiceLocal   {

	@EJB
	private IParametroDAO parametroDao;

	public ParametroService() {

	}		

	public Parametro pesquisar (Empresa empresa) throws ParametroNaoEncontradoException {

		try {
			return parametroDao.obter(empresa);
		} catch (ParametroNaoEncontradoException e) {
			throw new ParametroNaoEncontradoException();
		}
	}
	
	public JsonArray menuParametro() throws FalhaAoCriarJSONException {
		/* vetor com as strings do menu de parametros contém os atributos na ordem:
			[nome][iconCls][classname][position]
		 */
		String [][] menus = {{"Geral","xfxfxf","cliente-form","1"},{"Coleta","xf0d1","parametro-coleta-form","2"},{"menu3","xff","config-base-form","3"}};
		JsonArray dados = new JsonArray();

		for (int i = 0; i < menus.length; i++) {
			JsonObject jo =  new JsonObject();
			for (int j = 0; j < menus[i].length; j++) {
				String propriedade = null;
				String valor = menus[i][j];				
				switch (j) {
				case 0: propriedade = "text";break;
				case 1: propriedade = "iconCls";break;
				case 2: propriedade = "className";break;
				case 3: propriedade = "position";break;
				default: break;				
				}
				jo.addProperty(propriedade, valor);	
				jo.addProperty("leaf", true);
			}
			dados.add(jo);
		}		
		return dados;		
	}
	
	public <T> Object obterParametros(Class<T> classe, Parametro parametro) throws ObjetoNaoEncontradoException {
		try {
			return parametroDao.obterParametros(classe, parametro);
		} catch (ObjetoNaoEncontradoException e) {
			throw new ObjetoNaoEncontradoException();
		}		
	}
	
	public JsonObject parametroJson(Parametro parametro) throws FalhaAoCriarJSONException {
		Gson gson = new Gson();		
		JsonParser jp = new JsonParser();
		JsonObject jo = (JsonObject) jp.parse(gson.toJson(parametro));
		return jo;
		
	}
	
	public JsonObject parametroColetaJson(ParametroColeta parametroColeta) throws FalhaAoCriarJSONException {
		Gson gson = new Gson();		
		JsonParser jp = new JsonParser();
		
		CondicaoPagamento condicao = parametroColeta.getCondicaoPadraoColeta();
		Cobranca cobranca = parametroColeta.getCobrancaPadraoColeta();
		Parametro parametro  = parametroColeta.getParametro();		
		
		parametroColeta.setCobrancaPadraoColeta(null);
		parametroColeta.setParametro(null);
		parametroColeta.setCondicaoPadraoColeta(null);
						
		JsonObject jo = (JsonObject) jp.parse(gson.toJson(parametroColeta));		
		
		if (condicao != null) {
			jo.addProperty("idCondicao", condicao.getId());
			jo.addProperty("nomeCondicao", condicao.getNome());
		}		
		
		if (cobranca != null) {
			jo.addProperty("idCobranca", cobranca.getId());
			jo.addProperty("nomeCobranca", cobranca.getNome());
		}

		jo.addProperty("idParametro", parametro.getId());	
		
		return jo;		
	}
	
	public JsonObject parametroFinanceiroJson(ParametroFinanceiro parametroFinanceiro) throws FalhaAoCriarJSONException {
		Gson gson = new Gson();		
		JsonParser jp = new JsonParser();
		
		CondicaoPagamento condicao = parametroFinanceiro.getCondicaoPagamentoPadrao();
		Cobranca cobranca = parametroFinanceiro.getCobrancaPadrao();
		Parametro parametro  = parametroFinanceiro.getParametro();		
		
		parametroFinanceiro.setCobrancaPadrao(null);
		parametroFinanceiro.setParametro(null);
		parametroFinanceiro.setCondicaoPagamentoPadrao(null);
						
		JsonObject jo = (JsonObject) jp.parse(gson.toJson(parametroFinanceiro));		
		
		if (condicao != null) {
			jo.addProperty("idCondicao", condicao.getId());
			jo.addProperty("nomeCondicao", condicao.getNome());
		}		
		
		if (cobranca != null) {
			jo.addProperty("idCobranca", cobranca.getId());
			jo.addProperty("nomeCobranca", cobranca.getNome());
		}

		jo.addProperty("idParametro", parametro.getId());	
		
		return jo;		
	}
	
	

}
