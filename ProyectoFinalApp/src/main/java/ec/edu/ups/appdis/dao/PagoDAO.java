package ec.edu.ups.appdis.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.edu.ups.appdis.model.Pago;

@Stateless
public class PagoDAO {

	@Inject
	private EntityManager em;

	public void insertar(Pago p) {
		em.persist(p);
	}

	public Pago actualizarSaldo(Pago p) {
		return em.merge(p);
	}

	public List<Pago> getPagoCreditos(int numero) {
		String jpql = "SELECT p FROM Pago p JOIN p.credito pc where pc.numeroCredito = :numero";
		Query query = em.createQuery(jpql, Pago.class);
		query.setParameter("numero", numero);
		List<Pago> lista = query.getResultList();
		// lista.forEach(System.out::println);
		for (Pago pago : lista) {
			System.out.println("liataaa " + pago.getFechaPago());
		}
		return lista;
	}

	public Pago getPagoValor(int numero) {
		String jpql = "SELECT p FROM Pago p INNER JOIN p.credito c where c.numeroCredito = :numero";
		Query query = em.createQuery(jpql, Pago.class);
		query.setParameter("numero", numero);
		Pago usu = (Pago) query.setMaxResults(1).getSingleResult();
		return usu;
	}

	public List<Pago> getPagos(int numero) {
		String jpql = "SELECT p FROM Pago p INNER JOIN p.credito c where c.numeroCredito = :numero";
		Query query = em.createQuery(jpql, Pago.class);
		query.setParameter("numero", numero);
		List<Pago> lista = query.getResultList();
		return lista;
	}

	public List<Pago> getPagosVencidos(int numero, String estado) {
		String jpql = "SELECT p FROM Pago p INNER JOIN p.credito c where c.numeroCredito = :numero and p.estado = :estado";
		Query query = em.createQuery(jpql, Pago.class);
		query.setParameter("numero", numero);
		query.setParameter("estado", estado);
		List<Pago> lista = query.getResultList();
		for (Pago pago : lista) {
			lista.size();
		}
		return lista;
	}
}
