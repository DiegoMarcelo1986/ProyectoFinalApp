package ec.edu.ups.appdis.model;

public class ResumenCuenta {
	
	private int numeroCuenta;
	
	private String propetario;
	
	private String fechaUltimaTrans;
	
	private double saldo;

	public int getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(int numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public String getPropetario() {
		return propetario;
	}

	public void setPropetario(String propetario) {
		this.propetario = propetario;
	}

	public String getFechaUltimaTrans() {
		return fechaUltimaTrans;
	}

	public void setFechaUltimaTrans(String fechaUltimaTrans) {
		this.fechaUltimaTrans = fechaUltimaTrans;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	@Override
	public String toString() {
		return "ResumenCuenta [numeroCuenta=" + numeroCuenta + ", propetario=" + propetario + ", fechaUltimaTrans="
				+ fechaUltimaTrans + ", saldo=" + saldo + "]";
	}
		

}
