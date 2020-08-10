package ec.edu.ups.appdis.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private int idusuario;

	@Column(name = "usu_nombre")
	private String nombre;
	
	@Column(name = "usu_apellido")
	private String apellido;

	@Column(name = "usu_correo")
	private String correo;

	@Column(name = "usu_password")
	private String passsword;

	@Column(name = "usu_rol")
	private String rol;

	// @ManyToOne
	// @JoinColumn(name="id_rol")
	// private Rol rol;

	public int getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(int idusuario) {
		this.idusuario = idusuario;
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

	public String getPasssword() {
		return passsword;
	}

	public void setPasssword(String passsword) {
		this.passsword = passsword;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	/*
	 * public Rol getRol() { return rol; }
	 * 
	 * public void setRol(Rol rol) { this.rol = rol; }
	 */
	@Override
	public String toString() {
		return "Usuario [idusuario=" + idusuario + ", correo=" + correo + ", passsword=" + passsword + "]";
	}

}
