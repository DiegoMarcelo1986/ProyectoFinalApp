package ec.edu.ups.appdis.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.edu.ups.appdis.model.Credito;
import ec.edu.ups.appdis.model.Cuenta;
import ec.edu.ups.appdis.model.Pago;
import ec.edu.ups.appdis.model.Usuario;

@Stateless
public class CreditoDAO {

	@Inject
	private EntityManager em;
	
	
	public void insertar(Credito c) {
		em.persist(c);
	}
	
	public Credito read(int numCredito) {
		Credito aux = em.find(Credito.class, numCredito);
		System.out.println("DAOOOO "+ aux);
		return aux;
	}
	
	public List<Credito> getCCredito(int numero){
		String jpql = "SELECT c FROM Credito c where c.numeroCredito = :numero";		
		Query query = em.createQuery(jpql, Credito.class);
		query.setParameter("numero", numero);
		List<Credito> lista = query.getResultList();
		//lista.forEach(System.out::println);
		return lista;
	}
	
	public Credito getCreditoMonto(int idCuenta) {
		String jpql = "SELECT c FROM Credito c INNER JOIN c.cuenta cta where cta.idcuenta = :idCuenta";
		Query query = em.createQuery(jpql, Credito.class);
		query.setParameter("idCuenta", idCuenta);
		Credito usu = (Credito) query.getSingleResult();
		return usu;
	}
	
}
