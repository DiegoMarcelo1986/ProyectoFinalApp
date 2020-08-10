package ec.edu.ups.appdis.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Auditoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_auditoria")
	private int iauditoria;
	
	@Column(name="aud_descripcion")
	private String descripcion;
	
	@Column(name="aud_fecha")
	private String fecha;
	
	@OneToOne
	@JoinColumn(name="id_cliente")
	private Cliente cliente;

	public int getIauditoria() {
		return iauditoria;
	}

	public void setIauditoria(int iauditoria) {
		this.iauditoria = iauditoria;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	
}

