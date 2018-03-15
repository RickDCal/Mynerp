package br.com.mynerp.util;

import java.text.ParseException;
import java.util.Date;

import javax.servlet.http.Part;

import br.com.mynerp.negocio.exception.FalhaAoConverterDataException;
import br.com.mynerp.negocio.exception.FalhaAoEncriptarChaveException;
import br.com.mynerp.negocio.exception.FalhaAoRemoverArquivoException;
import br.com.mynerp.negocio.exception.FalhaAoSalvarArquivoException;

public interface IUtilitiesLocal {
	
	public void salvarArquivoDisco(Part parte, String caminhoArquivo, String nomeArquivo) throws FalhaAoSalvarArquivoException;
	
	public void salvarArquivoDisco(String caminhoArquivo, String nomeArquivo, String conteudoString) throws FalhaAoSalvarArquivoException;

	public void removerArquivoDisco(String caminhoArquivo, String nomeArquivo) throws FalhaAoRemoverArquivoException;
	
	public String obterNomeArquivo (Part parte);
	
	public String encriptarCesar(int chave, String texto) throws FalhaAoEncriptarChaveException;
	
	public String criptografiaSenha(String senha);
	
	public String removeAcentos(final String texto);
	
	public String bytesToHex(byte[] bytes);
	
	public String dataYYYY_MM_DD(Date data);
	
	public String dataYYYYMMDD(Date data);
	
	public String dataDD_MM_YYYY(Date data);
	
	public String dataMM_DD_YYYY(Date data);
	
	public String dataDDMMYYYY(Date data);
	
	public String dataDDIMMIYYYY(Date data);
	
	public Date IntegerToDate(int numero) throws FalhaAoConverterDataException;
	
	public Date dataYYYY_MM_DDeHHppmmppss(String data) throws ParseException, FalhaAoConverterDataException;	//e = espaço pp= :
	
	public Date dataYYYY_MM_DDTHHppmmppss(String data) throws ParseException, FalhaAoConverterDataException;
	
	public Date dataDD_MM_YYYY (String data) throws ParseException, FalhaAoConverterDataException;
	
	public Date dataDDIMMIYYYY (String data) throws ParseException, FalhaAoConverterDataException; // I = barra
	
	public Date dataYYYY_MM_DD (String data) throws ParseException, FalhaAoConverterDataException; // _ = -

}