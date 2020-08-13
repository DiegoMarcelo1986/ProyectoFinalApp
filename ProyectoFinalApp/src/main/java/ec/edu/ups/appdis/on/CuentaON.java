package ec.edu.ups.appdis.on;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.edu.ups.appdis.dao.CuentaDAO;
import ec.edu.ups.appdis.model.Cliente;
import ec.edu.ups.appdis.model.Cuenta;

@Stateless
public class CuentaON {

	@Inject
	private CuentaDAO cdao;

	/**
	 * 
	 * @param cuenta
	 */
	public void insert(Cuenta cuenta) {
		Cuenta aux = cdao.read(cuenta.getIdcuenta());
		if (aux != null) {
			cdao.update(cuenta);
		} else {
			cdao.insert(cuenta);
		}
	}

	/**
	 * 
	 * @param codigocuenta
	 */
	public void remove(int codigocuenta) {
		cdao.remove(codigocuenta);
	}

	/**
	 * 
	 * @param numCuenta
	 * @return
	 * @throws Exception
	 */
	public Cuenta read(int numCuenta) throws Exception {
		Cuenta c = cdao.read(numCuenta);
		if (c == null)
			throw new Exception("Cuenta no encontrada");
		return c;
	}

	/**
	 * 
	 * @param numCuenta
	 * @return
	 * @throws Exception
	 */
	public double getValorCuenta(int numCuenta) throws Exception {
		Cuenta c = cdao.read(numCuenta);
		double saldo = 0.0;
		if (c != null)
			saldo = c.getSaldo();
		else
			throw new Exception("Cuenta no encontrada");

		return saldo;
	}

	/**
	 * 
	 * @param c
	 * @return
	 */
	public Cuenta actualizarSaldo(Cuenta c) {
		return cdao.actualizarSaldo(c);
	}

	/**
	 * 
	 * @param numero
	 * @return
	 * @throws Exception
	 */
	public Cuenta buscarCuenta(int numero) throws Exception {
		Cuenta c = cdao.buscarCuenta(numero);
		return c;

	}
	
	public Cuenta buscarCuentaPorCorreo(String correo) {
		return cdao.buscarCuentaPorCorreo(correo);
	}
}
