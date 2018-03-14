package br.com.mynerp.negocio.exception;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class NomeNaoInformadoException extends Exception {

		public NomeNaoInformadoException() {
		}
}
