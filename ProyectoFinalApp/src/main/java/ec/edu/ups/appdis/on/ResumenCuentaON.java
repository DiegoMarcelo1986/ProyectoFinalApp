package ec.edu.ups.appdis.on;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.edu.ups.appdis.dao.CuentaDAO;
import ec.edu.ups.appdis.dao.ResumenCuentaDAO;
import ec.edu.ups.appdis.model.Cliente;
import ec.edu.ups.appdis.model.Cuenta;
import ec.edu.ups.appdis.model.Movimiento;
import ec.edu.ups.appdis.model.Usuario;

@Stateless
public class ResumenCuentaON {
	

	@Inject
	private ResumenCuentaDAO rdao;
	
	/**
	 * 
	 * @param correo
	 * @return
	 */
	public Cliente getClienteCuenta(String correo) {
		return rdao.getClienteCuenta(correo);
	}
	
	/**
	 * 
	 * @param idCuenta
	 * @return
	 */
	public Movimiento getMovimientoUltimo(int idCuenta) {
		 return rdao.getMovimientoUltimo(idCuenta);
	}
}
