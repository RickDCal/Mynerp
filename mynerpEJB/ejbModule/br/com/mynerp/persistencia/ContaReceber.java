package br.com.mynerp.persistencia;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;


@Entity
@DiscriminatorValue("2")
@Inheritance(strategy=InheritanceType.JOINED)
public class ContaReceber extends Conta {
	
	
	public ContaReceber() {
		
	}

	

}
