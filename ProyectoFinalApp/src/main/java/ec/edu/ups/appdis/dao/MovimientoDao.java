package ec.edu.ups.appdis.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.edu.ups.appdis.model.Cliente;
import ec.edu.ups.appdis.model.Movimiento;


@Stateless
public class MovimientoDao {
	
	@Inject
	private EntityManager em;
	
	/**
	 * Metodo para insertar un movimiento
	 * @param m Pasamos como parametro un objeto movimiento
	 */
	public void insertar(Movimiento m) {
		em.persist(m);
	}
	
	/**
	 * Metodo que lista todos los movimientos entre fechas dentro de su respectiva cuenta
	 * @param fechaInicio fecha inicio 
	 * @param fechaFin fecha fin
	 * @param idCuenta numero de cuenta asociada al cliente
	 * @return Lista de movimeintos
	 */
	public List<Movimiento> getMovimientosFechas(String fechaInicio, String fechaFin, int idCuenta){
		String jpql = "SELECT m FROM Movimiento m WHERE m.fecha BETWEEN ?1 AND ?2 AND m.cuenta.idcuenta = ?3";
		Query q = em.createQuery(jpql, Movimiento.class);
		q.setParameter(1, fechaInicio);
		q.setParameter(2, fechaFin);
		q.setParameter(3, idCuenta);
		List<Movimiento> movi = q.getResultList();
		return movi;
	}
	
}
