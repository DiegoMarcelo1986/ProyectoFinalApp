package ec.edu.ups.appdis.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.edu.ups.appdis.model.Auditoria;
import ec.edu.ups.appdis.model.Cliente;
import ec.edu.ups.appdis.model.Rol;
import ec.edu.ups.appdis.model.Usuario;

@Stateless
public class AuditoriaDAO {
	
	@Inject
	private EntityManager em;
	 
	 /**
	  * Metodo para insertar un Objeto Auditoria
	  * @param audi El parametro audi es para guardar el objecto Auditoria
	  */
	public void insert(Auditoria audi) {
		em.persist(audi);
	}
	
	
	/**
	 * Metodo que lista los usuarios por sus respectivos parametros
	 * @param correo El parametro correo es para validar el correo
	 * @param pass El parametro pass es para lavidar el password
	 * @return Devulve la lista de usuarios
	 */
	public List<Usuario> getUsers(String correo, String pass){
		String jpql = "SELECT u FROM Usuario u where u.correo = ?1 and u.passsword = ?2";
		Query q = em.createQuery(jpql, Usuario.class);
		q.setParameter(1, correo);
		q.setParameter(2, pass);
		List<Usuario> users = q.getResultList();
		return users;
	}
	
	/**
	 * Metodo que lista los Clientes por sus respectivos parametros
	 * @param correo El parametro correo es para validar el correo
	 * @param pass El parametro pass es para lavidar el password
	 * @return Devulve la lista de Clientes
	 */
	public List<Cliente> getClienteLogin(String correo, String pass){
		String jpql = "SELECT c FROM Cliente c where c.correo = ?1 and c.password = ?2";
		Query q = em.createQuery(jpql, Cliente.class);
		q.setParameter(1, correo);
		q.setParameter(2, pass);
		List<Cliente> cli = q.getResultList();
		return cli;
	}
	
	/**
	 * Metodo que lista los clientes por sus respectivos parametros
	 * @param correo El parametro correo es para validar el correo
	 * @return Devulve la lista de clientes
	 */
	public List<Cliente> getClienteLoginCorreo(String correo){
		String jpql = "SELECT c FROM Cliente c where c.correo = ?1";
		Query q = em.createQuery(jpql, Cliente.class);
		q.setParameter(1, correo);
		List<Cliente> cli = q.getResultList();
		return cli;
	}
	
	/**
	 * Metodo que lista los clientes por sus respectivos parametros
	 * @param pass El parametro pass es para lavidar el password
	 * @return Devulve la lista de clientes
	 */
	public List<Cliente> getClienteLoginPass(String pass){
		String jpql = "SELECT c FROM Cliente c where c.password = ?1";
		Query q = em.createQuery(jpql, Cliente.class);
		q.setParameter(1, pass);
		List<Cliente> cli = q.getResultList();
		return cli;
	}
	
	/**
	 * Metodo que lista los Auditoria por sus respectivos parametros
	 * @param idcliente El parametro idCliente es para listar los auditorias por ID
	 * @return
	 */
	public List<Auditoria> getAuditoria(String idcliente){
		String jpql = "SELECT a FROM Auditoria a where a.cliente.idcedula = ?1";
		Query q = em.createQuery(jpql, Auditoria.class);
		q.setParameter(1, idcliente);
		List<Auditoria> users = q.getResultList();
		return users;
	}

}
