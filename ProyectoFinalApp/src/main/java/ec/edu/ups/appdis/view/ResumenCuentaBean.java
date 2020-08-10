package ec.edu.ups.appdis.view;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

@ManagedBean
@ViewScoped
public class ResumenCuentaBean {
	
	//@Inject
	//private ResumenCuentaON rON;

	private String correoCliente;
	

	
	
	@PostConstruct
	public void init() {
		System.out.println("init ");
	}

	
	public String getCorreoCliente() {
		return correoCliente;
	}

	public void setCorreoCliente(String correoCliente) {
		this.correoCliente = correoCliente;
	}

	public void loadDatos() {
		System.out.println("cedula cliente " + this.correoCliente);
	}
	

}
