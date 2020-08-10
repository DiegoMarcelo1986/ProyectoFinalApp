package ec.edu.ups.appdis.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Credito implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "cre_numCredito")
	private int numeroCredito;
	
	@Column(name = "cre_monto")
	private Double monto;
	
	@Column(name = "cre_numMeses")
	private int numMeses;
	
	@Column(name = "cre_priFecha")
	private String priFecha;
	
	@Column(name = "estado")
	private String estado;
	
	@JoinColumn(name="fk_idcuenta", referencedColumnName="id_cuenta")
	@ManyToOne(optional=false, fetch=FetchType.EAGER)
	@JsonIgnore
	private Cuenta cuenta;

	public int getNumeroCredito() {
		return numeroCredito;
	}

	public void setNumeroCredito(int numeroCredito) {
		this.numeroCredito = numeroCredito;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	public int getNumMeses() {
		return numMeses;
	}

	public void setNumMeses(int numMeses) {
		this.numMeses = numMeses;
	}

	public String getPriFecha() {
		return priFecha;
	}

	public void setPriFecha(String priFecha) {
		this.priFecha = priFecha;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Credito [numeroCredito=" + numeroCredito + ", monto=" + monto + ", numMeses=" + numMeses + ", priFecha="
				+ priFecha + ", cuenta=" + cuenta + "]";
	}

}
