package br.com.mynerp.apresentacao.security;

import java.security.Principal;
import java.util.Set;


public class User implements Principal{
	
	private String name; // este tem que chamar name mesmo
	@SuppressWarnings("rawtypes")
	private Set roles;   // e este tem que chamar roles
	
	
	public User (String name) { //precisa deste contrutor aqui na classe login module
		this.name = name;
	}
	

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@SuppressWarnings("rawtypes")
	public Set getRoles() {
		return roles;
	}
	public void setRoles(@SuppressWarnings("rawtypes") Set roles) {
		this.roles = roles;
	}


}
