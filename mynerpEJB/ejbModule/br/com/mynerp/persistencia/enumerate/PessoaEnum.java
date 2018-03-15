package br.com.mynerp.persistencia.enumerate;

public enum PessoaEnum {
	
 CLIENTE(1), FUNCIONARIO(2), FORNECEDOR(3);
 
 private int code;
 private PessoaEnum (int c) {
	 code = c;
 }
 
 public int getCode() {
	 return code;
 }

public static PessoaEnum getEnum(int code) {
	switch (code) {
	case 1: return PessoaEnum.CLIENTE;
	case 2: return PessoaEnum.FUNCIONARIO;
	case 3: return PessoaEnum.FORNECEDOR;
	default: return null;
	}
} 

}
