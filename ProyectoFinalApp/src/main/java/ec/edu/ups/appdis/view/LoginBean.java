package ec.edu.ups.appdis.view;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import ec.edu.ups.appdis.model.Auditoria;
import ec.edu.ups.appdis.model.Cliente;
import ec.edu.ups.appdis.model.Movimiento;
import ec.edu.ups.appdis.model.ResumenCuenta;
import ec.edu.ups.appdis.model.Usuario;
import ec.edu.ups.appdis.on.AuditoriaON;
import ec.edu.ups.appdis.on.MovimientoON;
import ec.edu.ups.appdis.on.ResumenCuentaON;
import ec.edu.ups.appdis.on.UsuarioON;

@ManagedBean
@SessionScoped
public class LoginBean {

	@Inject
	private AuditoriaON aon;

	@Inject
	private ResumenCuentaON ron;
	
	@Inject
	private MovimientoON mon;

	@Inject
	private FacesContext facesContext;

	private Cliente cliente;
	
	private Cliente c;
	
	private String nombreCLiente;

	private ResumenCuenta rc;

	private List<ResumenCuenta> resumenCuenta;
	
	private List<Auditoria> auditorias;
	
	private List<Movimiento> getMovimientosFechas;
	
	private Date fechaDesde;
	private Date fechaHasta;

	@PostConstruct
	public void init() {

		cliente = new Cliente();
		rc = new ResumenCuenta();
		resumenCuenta = new ArrayList<ResumenCuenta>();
		auditorias = new ArrayList<Auditoria>();
		getMovimientosFechas = new ArrayList<Movimiento>();

	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<ResumenCuenta> getResumenCuenta() {
		return resumenCuenta;
	}

	public void setResumenCuenta(List<ResumenCuenta> resumenCuenta) {
		this.resumenCuenta = resumenCuenta;
	}
	
	public String getNombreCLiente() {
		return nombreCLiente;
	}
	
	public List<Auditoria> getAuditorias() {
		return auditorias;
	}

	public void setAuditorias(List<Auditoria> auditorias) {
		this.auditorias = auditorias;
	}

	public List<Movimiento> getGetMovimientosFechas() {
		return getMovimientosFechas;
	}

	public void setGetMovimientosFechas(List<Movimiento> getMovimientosFechas) {
		this.getMovimientosFechas = getMovimientosFechas;
	}

	public Date getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public void leerUsuario() {

		// List<Usuario> getUsers = aon.geUsers(usuario.getCorreo(),
		// usuario.getPasssword());

		// System.out.println("Usuarios: " + getUsers.size());
	}

	/**
	 * 
	 * @return
	 */
	public String guardarAuditoriaSuccessful() {
		try {
			aon.getClienteLoginCorreoPass(cliente.getCorreo(), cliente.getPassword());
			resumenCuentaDatos();
			nombreCLiente = c.getNombre()+ " " + c.getApellido();
			return "web-transaccional?faces-redirect=true";
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
			facesContext.addMessage(null, m);
		}
		return null;

	}

	/**
	 * 
	 */
	public void resumenCuentaDatos() {
		c = ron.getClienteCuenta(cliente.getCorreo());
		System.out.println("iddd: " + c.getCuenta().getIdcuenta());
		Movimiento c1 = ron.getMovimientoUltimo(c.getCuenta().getIdcuenta());
		System.out.println(
				"N-# Ceunta: " + c.getCuenta().getIdcuenta() + " Propietario: " + c.getNombre() + " " + c.getApellido()
						+ " Fecha ultima transaccion: " + c1.getFecha() + " Saldo: " + c.getCuenta().getSaldo());
		rc.setNumeroCuenta(c.getCuenta().getIdcuenta());
		rc.setPropetario(c.getNombre());
		rc.setFechaUltimaTrans(c1.getFecha());
		rc.setSaldo(c.getCuenta().getSaldo());
		resumenCuenta.add(rc);
	}
	
	/**
	 * 
	 */
	public void loadGetMovimientosFechas() {
		// getMovimientosFechas = mon.getMovimientosFechas("06/10/2020", "06/30/2020");
		System.out.println("fecha desde: " + fechaDesde);
		System.out.println("fecha hasta: " + fechaHasta);
		
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");  
		String fechaDesdeFin = dateFormat.format(fechaDesde);
		String fechaHastaFin = dateFormat.format(fechaHasta);  
		
		getMovimientosFechas = mon.getMovimientosFechas(fechaDesdeFin, fechaHastaFin, c.getCuenta().getIdcuenta());

		for (Movimiento movimiento : getMovimientosFechas) {
			System.out.println(movimiento.toString());

		}
	}
	
	/**
	 * 
	 */
	public void getAuditoriaa() {
		auditorias = aon.getAuditoria(c.getIdcedula());
	}

}
