package ec.edu.ups.appdis.view;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import ec.edu.ups.appdis.dao.CuentaDAO;
import ec.edu.ups.appdis.model.Cuenta;
import ec.edu.ups.appdis.model.Movimiento;
import ec.edu.ups.appdis.on.CuentaON;
import ec.edu.ups.appdis.on.MovimientoON;

@ManagedBean
public class MovimientoBean {

	@Inject
	private MovimientoON mon;

	@Inject
	private CuentaON con;

	@Inject
	private FacesContext facesContext;

	private Cuenta newCuenta;

	private Movimiento newMovimiento;

	private String nombreCliente;
	private double saldoCuenta;

	@PostConstruct
	public void init() {
		newMovimiento = new Movimiento();
		newCuenta = new Cuenta();
		
	}

	public Movimiento getNewMovimiento() {
		return newMovimiento;
	}

	public void setNewMovimiento(Movimiento newMovimiento) {
		this.newMovimiento = newMovimiento;
	}

	public Cuenta getNewCuenta() {
		return newCuenta;
	}

	public void setNewCuenta(Cuenta newCuenta) {
		this.newCuenta = newCuenta;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public double getSaldoCuenta() {
		return saldoCuenta;
	}

	public void setSaldoCuenta(double saldoCuenta) {
		this.saldoCuenta = saldoCuenta;
	}

	/**
	 * 
	 */
	public void transaccion() {
		try {
			mon.insertar(newCuenta.getIdcuenta(), newMovimiento.getMonto(), newMovimiento.getTipo());
			// cuentacliente();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("Error: " + e.getMessage());
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, e.getMessage(), e.getMessage());
			facesContext.addMessage(null, m);
		}

	}

	/**
	 * 
	 */
	public void cuentacliente() {

		try {
			Cuenta c = con.read(newCuenta.getIdcuenta());
			this.nombreCliente = c.getCliente().getNombre() + " " + c.getCliente().getApellido();
			this.saldoCuenta = c.getSaldo();
			System.out.println("Saldoooooo: " + c.getSaldo());
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
			facesContext.addMessage(null, m);
		}
	}


}
