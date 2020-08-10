package ec.edu.ups.appdis.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.edu.ups.appdis.model.Rol;

@Stateless
public class RolDao {
	
	@Inject
	private EntityManager em;
	 
	 
	public void insert(Rol rol) {
		em.persist(rol);
	}
	
	public Rol read(Integer id) {
		Rol aux = em.find(Rol.class, id);
		return aux;
	}
	
	public List<Rol> getRol(){
		String jpql = "SELECT r FROM Rol r";
		Query q = em.createQuery(jpql, Rol.class);
		List<Rol> roles = q.getResultList();
		return roles;
	}

}
