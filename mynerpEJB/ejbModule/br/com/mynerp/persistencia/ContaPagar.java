package br.com.mynerp.persistencia;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;


@Entity
@DiscriminatorValue("1")
@Inheritance(strategy=InheritanceType.JOINED)
public class ContaPagar extends Conta {	

	
	public ContaPagar() {
		
	}


	

}
