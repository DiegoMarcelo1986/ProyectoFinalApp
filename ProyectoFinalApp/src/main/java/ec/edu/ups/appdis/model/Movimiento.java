package ec.edu.ups.appdis.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Movimiento {

	@Id
	@GeneratedValue
	@Column(name="mov_id")
	private int id;
	
	@NotNull
	@Column(name="mov_fecha")
	private String fecha;
	
	@NotNull
	@Column(name="mov_monto")
	private double monto;
	
	@NotNull
	@Column(name="mov_tipo")
	private String tipo;
	
	@ManyToOne
	@JoinColumn(name="id_cuenta")
	private Cuenta cuenta;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	@Override
	public String toString() {
		return "Movimiento [id=" + id + ", fecha=" + fecha + ", monto=" + monto + ", tipo=" + tipo + "]";
	}
	
}
