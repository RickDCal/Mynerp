package br.com.mynerp.persistencia.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.com.mynerp.persistencia.PerfilUsuario;
import br.com.mynerp.persistencia.dao.exception.PerfilUsuarioNaoEncontradoException;


@Stateless
public class PerfilUsuarioDAO extends GenericDAO implements IPerfilUsuarioDAO   {

	public PerfilUsuarioDAO() {

	}
	
	public PerfilUsuario inserir (PerfilUsuario perfil) {
		entityManager.persist(perfil);
		entityManager.flush(); // para inserir na mesma hora que gera na mem�ria
		return perfil;
	}

	public PerfilUsuario alterar (PerfilUsuario perfil) throws PerfilUsuarioNaoEncontradoException {
		entityManager.merge(perfil);
		entityManager.flush(); 
		return perfil;
	}

	public void remover (PerfilUsuario perfil) {
		entityManager.remove(entityManager.contains(perfil)? perfil: entityManager.merge(perfil)); // n�o sei bem o que faz aqui mas funcionou
		entityManager.flush();
	}

	public PerfilUsuario obter (int id) throws PerfilUsuarioNaoEncontradoException {
		PerfilUsuario perfil = entityManager.find(PerfilUsuario.class, id);
		return perfil;
	}	


	public List<PerfilUsuario> obter (boolean isMenuEager) throws PerfilUsuarioNaoEncontradoException { //coloquei o tipo de lista pra ver no que d�

		String consulta = "select p from PerfilUsuario p";

		if (isMenuEager) {
			consulta = consulta + " Join fetch p.menuUsuario";
		}	


		Query query = entityManager.createQuery(consulta);


		query.setHint("org.hibernate.timeout", 2000);

		List<PerfilUsuario> list = (List<PerfilUsuario>)query.getResultList(); 

		return list;

	}	

	public PerfilUsuario obter (int idPerfil, boolean isMenuEager) throws PerfilUsuarioNaoEncontradoException { //coloquei o tipo de lista pra ver no que d�

		String consulta = "select p from PerfilUsuario p";

		if (isMenuEager) {
			consulta = consulta + " Left Join fetch p.menuUsuario";// testar se o left tem efeitos colaterais
		}	

		consulta = consulta + " where p.id = :perfil";


		Query query = entityManager.createQuery(consulta);

		query.setParameter("perfil", idPerfil);

		query.setHint("org.hibernate.timeout", 2000);

		PerfilUsuario perfil = (PerfilUsuario)query.getSingleResult();

		return perfil;

	}	

	public List<PerfilUsuario> obter() throws PerfilUsuarioNaoEncontradoException {

		String consulta = "select p from PerfilUsuario p";

		Query query = entityManager.createQuery(consulta);

		query.setHint("org.hibernate.timeout", 2000);

		List<PerfilUsuario> perfis = (List<PerfilUsuario>)query.getResultList();		

		return perfis;

	}

}
