package ec.edu.ups.appdis.view;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import ec.edu.ups.appdis.model.Cliente;
import ec.edu.ups.appdis.model.Credito;
import ec.edu.ups.appdis.model.Cuenta;
import ec.edu.ups.appdis.model.Pago;
import ec.edu.ups.appdis.on.CreditoON;

@ManagedBean
public class CreditoBean {

	@Inject
	private CreditoON con;

	private Credito newCredito;

	private List<Pago> getPagos;

	private int idCuenta;

	private int idCredito;

	@Inject
	private FacesContext facesContext;

	@PostConstruct
	public void init() {
		newCredito = new Credito();
	}

	public Credito getNewCredito() {
		return newCredito;
	}

	public void setNewCredito(Credito newCredito) {
		this.newCredito = newCredito;
	}

	public List<Pago> getGetPagos() {
		return getPagos;
	}

	public void setGetPagos(List<Pago> getPagos) {
		this.getPagos = getPagos;
	}

	public int getIdCuenta() {
		return idCuenta;
	}

	public void setIdCuenta(int idCuenta) {
		this.idCuenta = idCuenta;
	}

	public int getIdCredito() {
		return idCredito;
	}

	public void setIdCredito(int idCredito) {
		this.idCredito = idCredito;
	}

	public void saveCredito() {

		try {

			con.insertar(newCredito);
			System.out.println("registro guardado");

			// return "list-personas?faces-redirect=true";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
			facesContext.addMessage(null, m);

			e.printStackTrace();
		}

	}

	public void validarCuenta(int idCuenta) {
		try {
			con.validarCuenta(idCuenta);
			saveCredito();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
			facesContext.addMessage(null, m);
		}

	}

	public void getPaagos() {
		System.out.println("NUmero de credito: " + idCredito);
		getPagos = con.getPagoCredito(idCredito);

	}
	
	public void updatePagos() {
		try {
			con.updatePagos(idCredito);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
			facesContext.addMessage(null, m);
		}
		
	}

}
