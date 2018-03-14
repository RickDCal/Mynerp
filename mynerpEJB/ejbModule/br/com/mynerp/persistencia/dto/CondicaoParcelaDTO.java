package br.com.mynerp.persistencia.dto;

public class CondicaoParcelaDTO {
	
	private int id;
	private int sequencial;
	private int prazo;
	private double percentual;
	private String clientIdProperty;	
	
	public CondicaoParcelaDTO() {
		
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public int getSequencial() {
		return sequencial;
	}
	public void setSequencial(int sequencial) {
		this.sequencial = sequencial;
	}
	
	public int getPrazo() {
		return prazo;
	}
	public void setPrazo(int prazo) {
		this.prazo = prazo;
	}

	public double getPercentual() {
		return percentual;
	}
	public void setPercentual(double percentual) {
		this.percentual = percentual;
	}
	
	public String getClientIdProperty() {
		return clientIdProperty;
	}

	public void setClientIdProperty(String clientIdProperty) {
		this.clientIdProperty = clientIdProperty;
	}

	
}
