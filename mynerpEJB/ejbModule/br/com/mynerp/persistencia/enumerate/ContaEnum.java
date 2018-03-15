package br.com.mynerp.persistencia.enumerate;

public enum ContaEnum {
	
 PAGAR(1), RECEBER(2);
 
 private int code;
 private ContaEnum (int c) {
	 code = c;
 }
 
 public int getCode() {
	 return code;
 }

public static ContaEnum getEnum(int code) {
	switch (code) {
	case 1: return ContaEnum.PAGAR;
	case 2: return ContaEnum.RECEBER;
	default: return null;
	}
} 

}
