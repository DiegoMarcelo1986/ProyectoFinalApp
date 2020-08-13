package ec.edu.ups.appdis.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.edu.ups.appdis.model.Cliente;
import ec.edu.ups.appdis.model.Cuenta;
import ec.edu.ups.appdis.model.Usuario;

@Stateless
public class CuentaDAO {

	@Inject
	private EntityManager em;

	/**
	 * Metodo para insertar una Objeto cuenca
	 * @param cuenta insertar un objeto cuenta
	 */
	public void insert(Cuenta cuenta) {
		em.persist(cuenta);
	}
	
	/**
	 * Metodo para actualizar una Objeto cuenca
	 * @param cuenta Actualizar todo un objeto cuenta
	 */
	public void update(Cuenta cuenta) {
		em.merge(cuenta);
	}
	
	/**
	 * Metodo para eliminar una Objeto cuenca
	 * @param codigocuenta eliminar por numero de cuenta
	 */
	public void remove(int codigocuenta) {
		em.remove(read(codigocuenta));
	}

	/**
	 * Metodo para leer una Objeto cuenca
	 * @param numCuenta Lee por numero de cuenta
	 * @return Devuelve un objeto cuenta
	 */
	public Cuenta read(int numCuenta) {
		Cuenta aux = em.find(Cuenta.class, numCuenta);
		return aux;
	}

	/**
	 * Metodo para actulizar saldo de una cuenta
	 * @param c Para por parametro un Objeto cuenta
	 * @return Devuelve un objeto cuenta
	 */
	public Cuenta actualizarSaldo(Cuenta c) {
		return em.merge(c);
	}
	
	/**
	 * Metodo para buscar una Objeto cuenca
	 * @param numero Busca por numero de cuenta
	 * @return Devuelve un objeto cuenta
	 */
	public Cuenta buscarCuenta(int numero) {
		String jpql = "SELECT c FROM Cuenta c where c.idcuenta = :numero";
		Query query = em.createQuery(jpql, Cuenta.class);
		query.setParameter("numero", numero);
		Cuenta cuenta = (Cuenta) query.getSingleResult();
		return cuenta;
	}
	
	public List<Cuenta> getCuenta(int idcuenta){
		String jpql = "SELECT c FROM Cuenta c where c.idcuenta = ?1";
		Query q = em.createQuery(jpql, Cuenta.class);
		q.setParameter(1, idcuenta);
		List<Cuenta> cli = q.getResultList();
		return cli;
	}
	
	public Cuenta buscarCuentaPorCorreo(String correo) {
		String jpql = "SELECT c FROM Cuenta c INNER JOIN c.cliente cli where cli.correo = :correo";
		Query query = em.createQuery(jpql, Cuenta.class);
		query.setParameter("correo", correo);
		Cuenta cuenta = (Cuenta) query.getSingleResult();
		return cuenta;
	}
	
}
