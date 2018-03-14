package br.com.mynerp.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.Part;

import br.com.mynerp.negocio.exception.FalhaAoConverterDataException;
import br.com.mynerp.negocio.exception.FalhaAoRemoverArquivoException;
import br.com.mynerp.negocio.exception.FalhaAoSalvarArquivoException;
import br.com.mynerp.persistencia.dao.IUsuarioDAO;

@Stateless
public class Utilities implements IUtilitiesLocal {
	
	@EJB
	private IUsuarioDAO usuarioDao;

	public Utilities() {
		
	}	
	
	public void salvarArquivoDisco(Part parte, String caminhoArquivo, String nomeArquivo) throws FalhaAoSalvarArquivoException {

		if (parte != null && nomeArquivo == null) {
			nomeArquivo = this.obterNomeArquivo(parte);
		}
	
		File arquivo = new File(caminhoArquivo + "/" + nomeArquivo );
		try {
			parte.write(arquivo.getAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
		}	
	} 
	
	public void salvarArquivoDisco(String caminhoArquivo, String nomeArquivo, String conteudoString) {
		try {
			
			File diretorio = new File(caminhoArquivo);
			if (diretorio.exists() == false) {
				diretorio.mkdir();
			}

			File file = new File(caminhoArquivo + "/" + nomeArquivo);
			if(file.exists()){
				file.createNewFile();
			}
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			bw.write(conteudoString);
			bw.flush();
			bw.close();

		} catch (Exception ex) {
			ex.printStackTrace();     	
		}
	}
	
	public void removerArquivoDisco(String caminhoArquivo, String nomeArquivo) throws FalhaAoRemoverArquivoException {
	
		File arquivo = new File(caminhoArquivo + "/" + nomeArquivo );
		arquivo.delete();	
	}
	
	public String obterNomeArquivo (Part parte) {
		String nome = null;
		Collection<String> header = parte.getHeaders("content-disposition");
		for (String texto : header) {			
			for( String tmp : texto.split(";") ){
				System.out.println(tmp);
				if(tmp.trim().startsWith("filename")){
					int a = tmp.indexOf("=")+2;
					nome =  tmp.substring(a , tmp.length()-(1));
				}
			}			
		}
		if (nome.equalsIgnoreCase("")) {
			return null;
		} else {
			return nome;
		}
		
	}
	
	
	 /**
     * Metodo para realizar a criptografia de cesar
     * esta criptografia é simples e facilmente descriptografada, não serve para proteger comunicação, 
     * serve apenas para não deixar muito explicita a senha no banco de dados.
     * @param texto mensagem a ser codificada
     * @param chave numero de saltos
     * @return mensagem codificada
     */
	public String encriptarCesar(int chave, String texto)  {

		StringBuilder textoCifrado = new StringBuilder();
		int tamanhoTexto = texto.length();
		for(int c=0; c < tamanhoTexto; c++){
			int letraCifradaASCII = ((int) texto.charAt(c)) + chave;
			while(letraCifradaASCII > 126)
				letraCifradaASCII -= 94;
			textoCifrado.append( (char)letraCifradaASCII );
		}

		return textoCifrado.toString();
	}
	
	public String criptografiaSenha(String senha) {

		int salto = 7907;    		        

		//aqui criptografa 
		StringBuilder textoCifrado = new StringBuilder();
		int tamanhoTexto = senha.length();
		for(int c=0; c < tamanhoTexto; c++){
			int letraCifradaASCII = ((int) senha.charAt(c)) + salto;
			while(letraCifradaASCII > 126)
				letraCifradaASCII -= 94;
			textoCifrado.append( (char)letraCifradaASCII );
		}

		String senhaCriptografada = textoCifrado.toString();		
		//o que criptografar coloca em hexadecimal
		byte[] bytes = senhaCriptografada.getBytes();
		//senhaCriptografada = Hex.encodeHexString(bytes); esta classe hex depende de import da biblioteca apache.
		senhaCriptografada = bytesToHex(bytes);//este bytesToHex é um método que faz o mesmo que o método da biblioteca apache mas teve que ser colado nesta classe.
		return senhaCriptografada;
	}


	public String removeAcentos(final String texto) {
		String textoSemAcentos = Normalizer.normalize(texto, Normalizer.Form.NFD);
		textoSemAcentos = textoSemAcentos.replaceAll("[^\\p{ASCII}]", "");
		return textoSemAcentos;
		/* método copiado de: 
		 * https://ldiasrs.wordpress.com/2014/07/09/java-como-remover-acentos-e-caracteres-especiais*/ 
	}
	
	public String bytesToHex(byte[] bytes) {
		char[] hexArray = "0123456789ABCDEF".toCharArray();
	    char[] hexChars = new char[bytes.length * 2];
	    for ( int j = 0; j < bytes.length; j++ ) {
	        int v = bytes[j] & 0xFF;
	        hexChars[j * 2] = hexArray[v >>> 4];
	        hexChars[j * 2 + 1] = hexArray[v & 0x0F];
	    }
	    return new String(hexChars);
	}
	
	/*datas para String*/
	
	public String dataYYYY_MM_DD(Date data) {		
		SimpleDateFormat dataFormat = new SimpleDateFormat("YYYY-MM-dd");		
		String date = dataFormat.format(data.getTime());
		return date;		
	}
	
	public String dataYYYYMMDD(Date data) {		
		SimpleDateFormat dataFormat = new SimpleDateFormat("YYYYMMdd");
		String date = dataFormat.format(data.getTime());		
		return date;		
	}
	
	public String dataDD_MM_YYYY(Date data) {		
		SimpleDateFormat dataFormat = new SimpleDateFormat("dd-MM-YYYY");
		String date = dataFormat.format(data.getTime());		
		return date;		
	}
	
	public String dataMM_DD_YYYY(Date data) {		
		SimpleDateFormat dataFormat = new SimpleDateFormat("MM-dd-YYYY");
		String date = dataFormat.format(data.getTime());		
		return date;		
	}
	
	public String dataDDMMYYYY(Date data) {		
		SimpleDateFormat dataFormat = new SimpleDateFormat("ddMMYYYY");
		String date = dataFormat.format(data.getTime());		
		return date;		
	}
	
	public String dataDDIMMIYYYY(Date data) {		
		SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/YYYY");
		String date = dataFormat.format(data.getTime());		
		return date;		
	}	
	
	public String dataYYYY_MM_DDeHHppmmppss(Date data) {	//e = espa�o pp= :
		SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");		
		String date = dataFormat.format(data.getTime());
		return date;		
	}
	
	/*inteiro para Date*/
	
	public Date IntegerToDate(int numero) throws FalhaAoConverterDataException{		

		Date data = new Date(numero *1000L);		
		return data;		
	}
	
	/*String para Date*/	
	public Date dataYYYY_MM_DDeHHppmmppss(String data) throws ParseException, FalhaAoConverterDataException {	//e = espa�o pp= :
		Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(data);
		return date;
	}
	
	public Date dataYYYY_MM_DDTHHppmmppss(String data) throws ParseException, FalhaAoConverterDataException {	//e = espa�o pp= :
		data = data.replace("T"," ");
		Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(data);
		return date;
	}
	
	public Date dataDD_MM_YYYY (String data) throws ParseException, FalhaAoConverterDataException {
		Date date = new SimpleDateFormat("dd-MM-yyyy").parse(data);
		return date;
	}
	
	public Date dataDDIMMIYYYY (String data) throws ParseException, FalhaAoConverterDataException { // I = barra
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse(data);
		return date;
	}
	
	public Date dataYYYY_MM_DD (String data) throws ParseException, FalhaAoConverterDataException { // _ = -
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse(data);
		return date;
	}
	
	
	
	


}
