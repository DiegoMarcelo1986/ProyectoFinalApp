package ec.edu.ups.appdis.dao;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.edu.ups.appdis.model.Cliente;
import ec.edu.ups.appdis.model.Movimiento;

@Stateless
public class ResumenCuentaDAO {
	

	@Inject
	private EntityManager em;
	
	/**
	 * Metodo que recupera un cliente
	 * @param correo pasamos el parametro correo 
	 * @return devuelve un cliente
	 */
	public Cliente getClienteCuenta(String correo) {
		String jpql = "SELECT p FROM Cliente p where p.correo = ?1";
		Query query = em.createQuery(jpql, Cliente.class);
		query.setParameter(1, correo);
		Cliente cuenta = (Cliente) query.getSingleResult();
		return cuenta;
	}
	
	/**
	 * Meotodo que recuperar el ultimo moviemintos que realizo un cliente
	 * @param idcuenta pasamos como parametro el ID de la cuenta
	 * @return Devuelve un objeto movimiento
	 */
	public Movimiento getMovimientoUltimo(int idcuenta) {
//SELECT * FROM movimiento v where v.mov_id = (SELECT MAX(m.mov_id) FROM movimiento m where m.id_cuenta = 100);
		String jpql = "SELECT v FROM Movimiento v where v.id = (SELECT MAX(m.id) FROM Movimiento m where m.cuenta.idcuenta = ?1)";
		Query query = em.createQuery(jpql,  Movimiento.class);
		query.setParameter(1, idcuenta);
		 Movimiento movi = ( Movimiento) query.getSingleResult();
		return movi;
	}

}
