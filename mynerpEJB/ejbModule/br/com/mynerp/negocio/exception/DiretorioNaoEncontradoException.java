package br.com.mynerp.negocio.exception;

public class DiretorioNaoEncontradoException extends Exception{
	
	private String textoAdicional;
	
	public DiretorioNaoEncontradoException() {
		
	}	
	
	public DiretorioNaoEncontradoException(String textoAdicional) {
		super();
		this.textoAdicional = textoAdicional;
	}

	public String getMotivo() {
		
		String text = "";
		if (this.textoAdicional != null){
			text = " <br>" + textoAdicional;
		}	
		
		return "Diretório não encontrado ou inexistente" + text;
	}

}
