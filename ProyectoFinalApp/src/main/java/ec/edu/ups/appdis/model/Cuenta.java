package ec.edu.ups.appdis.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Cuenta {
	
	@Id
	@Column(name="id_cuenta")
	private int idcuenta;
	
	@Column(name="cue_tipo")
	private String tipo;
	
	@Column(name="cue_saldo")
	private double saldo;
	
	@OneToOne
	@JoinColumn(name="id_cliente")
	private Cliente cliente;


	public int getIdcuenta() {
		return idcuenta;
	}


	public void setIdcuenta(int idcuenta) {
		this.idcuenta = idcuenta;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public double getSaldo() {
		return saldo;
	}


	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}


	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
	

}

