package ec.edu.ups.appdis.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class Pago implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "pag_codigo")
	private int codigo;
	
	@Column(name = "pag_numCuota")
	private int numCuota;
	
	@Column(name = "pag_fechaPago")
	private String fechaPago;
	
	@Column(name = "pag_valor")
	private Double valor;
	
	@Column(name = "pag_estado")
	private String estado;
	
	@Column(name = "pag_cancelado")
	private double cancelado;
	
	@JoinColumn(name="fk_idcredito", referencedColumnName="cre_numCredito")
	@ManyToOne(optional=false, fetch=FetchType.EAGER)
	private Credito credito;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getNumCuota() {
		return numCuota;
	}

	public void setNumCuota(int numCuota) {
		this.numCuota = numCuota;
	}

	public String getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(String fechaPago) {
		this.fechaPago = fechaPago;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Credito getCredito() {
		return credito;
	}

	public void setCredito(Credito credito) {
		this.credito = credito;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public double getCancelado() {
		return cancelado;
	}

	public void setCancelado(double cancelado) {
		this.cancelado = cancelado;
	}	
	

}
