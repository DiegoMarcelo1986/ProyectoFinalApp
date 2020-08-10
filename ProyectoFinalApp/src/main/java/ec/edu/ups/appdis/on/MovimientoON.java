package ec.edu.ups.appdis.on;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.edu.ups.appdis.dao.CuentaDAO;
import ec.edu.ups.appdis.dao.MovimientoDao;
import ec.edu.ups.appdis.model.Cuenta;
import ec.edu.ups.appdis.model.Movimiento;

@Stateless
public class MovimientoON {

	@Inject
	private MovimientoDao mdao;

	@Inject
	private CuentaDAO cdao;

	private double saldoActual;

	private double saldoNuevo;

	/**
	 * 
	 * @param numCuenta
	 * @param valor
	 * @param tipo
	 * @throws Exception
	 */
	public void insertar(int numCuenta, double valor, String tipo) throws Exception {
		Movimiento m = new Movimiento();

		Cuenta aux = cdao.buscarCuenta(numCuenta);

		System.out.println("tipo: " + tipo);

		if (aux != null && tipo.equals("Deposito")) {
			saldoActual = aux.getSaldo();
			saldoNuevo = saldoActual + valor;
			aux.setSaldo(saldoNuevo);
			cdao.actualizarSaldo(aux);

			String fechaActual = getFechaActual();
			m.setFecha(fechaActual);
			m.setMonto(valor);
			m.setTipo(tipo);
			Cuenta cu = new Cuenta();
			cu.setIdcuenta(aux.getIdcuenta());
			m.setCuenta(cu);

			mdao.insertar(m);

			throw new Exception("Deposito realizado con ¡Exito!");

		} else if (aux != null && tipo.equals("Retiro")) {
			saldoActual = aux.getSaldo();
			if (valor <= saldoActual) {
				saldoNuevo = saldoActual - valor;
				aux.setSaldo(saldoNuevo);
				cdao.actualizarSaldo(aux);

				String fechaActual = getFechaActual();
				m.setFecha(fechaActual);
				m.setMonto(valor);
				m.setTipo(tipo);
				Cuenta cu = new Cuenta();
				cu.setIdcuenta(aux.getIdcuenta());
				m.setCuenta(cu);

				mdao.insertar(m);

				throw new Exception("Retiro realizado con ¡Exito!");

			} else {
				throw new Exception("Saldo insuficiente");
			}

		} else {
			throw new Exception("Cuenta no Existe");
		}
	}

	/**
	 * Este metodo me permite realizar una transaccion de un determinado saldo de
	 * una cuenta a otra
	 * 
	 * @param corigen  cuenta origen
	 * @param cdestino cuenta destino
	 * @param valor    en valor de la transferencia
	 * @throws Exception mstras mensajes de errores o transaccion exitosa
	 */
	public void nuevaTransferencia(int corigen, int cdestino, Double valor) throws Exception {

		System.out.println("C corigen " + corigen);

		List<Cuenta> getCuentaOrigen = cdao.getCuenta(corigen);
		List<Cuenta> getCuentaDestino = cdao.getCuenta(cdestino);

		if (getCuentaOrigen.isEmpty())
			throw new Exception("No se encuentra la cuenta Origen");
		if (getCuentaDestino.isEmpty())
			throw new Exception("No se encuentra la cuenta Destino");

		System.out.println("llego 2");
		Cuenta destino = cdao.buscarCuenta(cdestino);
		System.out.println("Destino " + destino.toString());
		Double saldo = destino.getSaldo();
		System.out.println("Saldo 0001 " + saldo);
		saldo = saldo + valor;
		System.out.println("saldo 0022 " + saldo);

		System.out.println("llego 3");
		Cuenta origen = cdao.buscarCuenta(corigen);
		Double saldoo = origen.getSaldo();

		if (saldoo >= valor) {
			saldoo = saldoo - valor;

			System.out.println("llego 4");
			origen.setSaldo(saldoo);
			destino.setSaldo(saldo);

			System.out.println("llego 5");
			cdao.actualizarSaldo(origen);
			cdao.actualizarSaldo(destino);

		} else {
			throw new Exception("No tiene saldo suficiente para transferencia \n Su salgo maximo es: " + saldoo);
		}
	}

	public void nuevaTransferenciaFinal(int corigen, int cdestino, Double valor) throws Exception {

		System.out.println("C corigen " + corigen);
		// Movimiento mor = new Movimiento();
		// Movimiento mdst = new Movimiento();

		List<Cuenta> getCuentaOrigen = cdao.getCuenta(corigen);
		List<Cuenta> getCuentaDestino = cdao.getCuenta(cdestino);

		if (getCuentaOrigen.isEmpty())
			throw new Exception("No se encuentra la cuenta Origen");
		if (getCuentaDestino.isEmpty())
			throw new Exception("No se encuentra la cuenta Destino");

	}

	/**
	 * 
	 * @param fechaInicio
	 * @param fechaFin
	 * @param idCuenta
	 * @return
	 */
	public List<Movimiento> getMovimientosFechas(String fechaInicio, String fechaFin, int idCuenta) {
		return mdao.getMovimientosFechas(fechaInicio, fechaFin, idCuenta);

	}

	/**
	 * 
	 * @return
	 */
	public String getFechaActual() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDateTime now = LocalDateTime.now();
		System.out.println(dtf.format(now));
		return dtf.format(now);
	}

}
