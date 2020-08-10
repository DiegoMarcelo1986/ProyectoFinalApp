package ec.edu.ups.appdis.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.edu.ups.appdis.model.Cliente;
import ec.edu.ups.appdis.model.Cuenta;
import ec.edu.ups.appdis.model.Rol;
import ec.edu.ups.appdis.model.Usuario;

@Stateless
public class UsuarioDAO {
	
	@Inject
	private EntityManager em;
	 
	/**
	 * Metodo para insertar un objeto usuario
	 * @param usuario pasamos como parametro un objeto usuario
	 */
	public void insert(Usuario usuario) {
		em.persist(usuario);
	}
	
	/**
	 * Metodo para actulizar un objeto usuario
	 * @param usuario asamos como parametro un objeto usuario
	 */
	public void update(Usuario usuario) {
		em.merge(usuario);
	}
	
	/**
	 * Metodo para actualizar un objeto usuario
	 * @param id Parametro es el ID del usuario
	 */
	public void remove(int id) {
		em.remove(read(id));
	}
	
	/**
	 * Metodo para leer un objeto usuario
	 * @param id Parametro es el ID del usuario
	 * @return Devulve un objeto usuario
	 */
	public Usuario read(int id) {
		Usuario aux = em.find(Usuario.class, id);
		return aux;
	}
	
	/***
	 * Metodo para busacar un objeto usuario
	 * @param correo Parametro es el correo del usuario
	 * @return Devulve un objeto usuario
	 */
	public Usuario buscarUsuario(String correo) {
		String jpql = "SELECT u FROM Usuario u where u.correo = :correo";
		Query query = em.createQuery(jpql, Usuario.class);
		query.setParameter("correo", correo);
		Usuario usu = (Usuario) query.getSingleResult();
		return usu;
	}
	
	/**
	 * Metodo para recuperar una lista de un objeto usuario
	 * @param correo Parametro es el correo del usuario
	 * @param pass Parametro es el password del usuario
	 * @return Devulve una lista de usuarios
	 */
	public List<Usuario> getUsuarioLogin(String correo, String pass){
		String jpql = "SELECT u FROM Usuario u where u.correo = ?1 and u.passsword = ?2";
		Query q = em.createQuery(jpql, Usuario.class);
		q.setParameter(1, correo);
		q.setParameter(2, pass);
		List<Usuario> usu = q.getResultList();
		return usu;
	}
	
	/**
	 * Metodo para recuperar una lista de un objeto usuario
	 * @return Devulve una lista de usuarios
	 */
	public List<Usuario> getUsers(){
		String jpql = "SELECT u FROM Usuario u";
		Query q = em.createQuery(jpql, Usuario.class);
		List<Usuario> users = q.getResultList();
		return users;
	}

}
