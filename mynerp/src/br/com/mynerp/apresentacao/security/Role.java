package br.com.mynerp.apresentacao.security;

import java.security.Principal;
import java.security.acl.Group;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

public class Role implements Group {
	
	private String name; // tem que chamar de name mesmo
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Set<Principal> principal = new HashSet();
	
	
	public Role (String name) {
		this.name = name;
	}
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<Principal> getPrincipal() {
		return principal;
	}
	public void setPrincipal(Set<Principal> principal) {
		this.principal = principal;
	}
	@Override
	public boolean addMember(Principal user) {
		
		return principal.add(user);
		
	}
	@Override
	public boolean isMember(Principal user) {
		
		return principal.contains(user);
	}
	@Override
	public Enumeration<? extends Principal> members() {
		
		return Collections.enumeration(principal);
	}
	@Override
	public boolean removeMember(Principal user) {
		
		return principal.remove(user);
	}

}
