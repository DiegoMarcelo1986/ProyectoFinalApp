package ec.edu.ups.appdis.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Cliente {
	
	@Id
	@Column(name="id_cliente")
	private String idcedula;
	
	@Column(name="cli_nombre")
	private String nombre;
	
	@Column(name="cli_apellido")
	private String apellido;
	
	@Column(name="cli_correo")
	private String correo;
	
	@Column(name="cli_password")
	private String password;
	
	@OneToOne(mappedBy="cliente")
	private Cuenta cuenta;

	public String getIdcedula() {
		return idcedula;
	}

	public void setIdcedula(String idcedula) {
		this.idcedula = idcedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	@Override
	public String toString() {
		return "Cliente [idcedula=" + idcedula + ", nombre=" + nombre + ", apellido=" + apellido + "]";
	}


}
