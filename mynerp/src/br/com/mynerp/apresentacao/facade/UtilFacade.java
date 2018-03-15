package br.com.mynerp.apresentacao.facade;

import java.text.ParseException;
import java.util.Date;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.Part;

import br.com.mynerp.negocio.exception.FalhaAoConverterDataException;
import br.com.mynerp.negocio.exception.FalhaAoEncriptarChaveException;
import br.com.mynerp.negocio.exception.FalhaAoRemoverArquivoException;
import br.com.mynerp.negocio.exception.FalhaAoSalvarArquivoException;
import br.com.mynerp.util.IUtilitiesLocal;

public class UtilFacade {

	private Properties p;
	private Context c;

	public IUtilitiesLocal service;

	public UtilFacade() throws NamingException {

		p = new Properties();
		c = new InitialContext(p);
		service = (IUtilitiesLocal)c.lookup("java:global/mynerpEAR/mynerpEJB/Utilities");

	}

	public void salvarArquivoDisco(Part parte, String caminhoArquivo, String nomeArquivo) {		

		try {
			service.salvarArquivoDisco(parte, caminhoArquivo, nomeArquivo);
		} catch (FalhaAoSalvarArquivoException e) {
			e.printStackTrace();
		}		

	}

	public void salvarArquivoDisco(String caminhoArquivo, String nomeArquivo, String conteudoString) {
		try {
			service.salvarArquivoDisco(caminhoArquivo, nomeArquivo, conteudoString);
		} catch (FalhaAoSalvarArquivoException e) {
			e.printStackTrace();
		}
	}


	public void removerArquivoDisco(String caminhoArquivo, String nomeArquivo) {			
		try {
			service.removerArquivoDisco(caminhoArquivo, nomeArquivo);
		} catch (FalhaAoRemoverArquivoException e) {
			e.printStackTrace();
		}		

	}

	public String obterNomeArquivo (Part parte) {
		return service.obterNomeArquivo(parte);
	}

	public String encriptarCesar(int chave, String texto) throws FalhaAoEncriptarChaveException {
		return service.encriptarCesar(chave, texto);
	}

	public String criptografiaSenha(String senha) {
		return service.criptografiaSenha(senha);
	}

	public String removeAcentos(final String texto) {
		return service.removeAcentos(texto);
	}

	public String bytesToHex(byte[] bytes) {
		return service.bytesToHex( bytes);
	}

	public String dataYYYY_MM_DD(Date data) {
		return service.dataYYYY_MM_DD(data);
	}

	public String dataYYYYMMDD(Date data) {
		return service.dataYYYYMMDD(data);
	}

	public String dataDD_MM_YYYY(Date data) {
		return service.dataDD_MM_YYYY(data);
	}
	
	public String dataMM_DD_YYYY(Date data) {
		return service.dataMM_DD_YYYY(data);
	}
	
	public String dataDDMMYYYY(Date data) {
		return service.dataDDMMYYYY(data);
	}

	public String dataDDIMMIYYYY(Date data) {
		return service.dataDDIMMIYYYY(data);
	}
	
	public Date IntegerToDate(int numero) throws FalhaAoConverterDataException {
		return service.IntegerToDate(numero);
	}
	
	public Date dataYYYY_MM_DDeHHppmmppss(String data) throws ParseException, FalhaAoConverterDataException {	//e = espaço pp= :
		return service.dataYYYY_MM_DDeHHppmmppss(data);
	}
	
	public Date dataYYYY_MM_DDTHHppmmppss(String data) throws ParseException, FalhaAoConverterDataException {
		return service.dataYYYY_MM_DDTHHppmmppss(data);
	}
	
	public Date dataDD_MM_YYYY (String data) throws ParseException, FalhaAoConverterDataException {
		return service.dataDD_MM_YYYY(data);
	}
	
	public Date dataDDIMMIYYYY (String data) throws ParseException, FalhaAoConverterDataException { // I = barra
		return service.dataDDIMMIYYYY(data);
	}
	
	public Date dataYYYY_MM_DD (String data) throws ParseException, FalhaAoConverterDataException { // _ = -
		return service.dataYYYY_MM_DD(data);
	}	
	
}
