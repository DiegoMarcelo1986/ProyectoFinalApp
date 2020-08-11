package ec.edu.ups.appdis.on;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.edu.ups.appdis.dao.CreditoDAO;
import ec.edu.ups.appdis.dao.CuentaDAO;
import ec.edu.ups.appdis.dao.PagoDAO;
import ec.edu.ups.appdis.model.Credito;
import ec.edu.ups.appdis.model.Cuenta;
import ec.edu.ups.appdis.model.Pago;

@Stateless
public class CreditoON {

	@Inject
	private CreditoDAO cdao;

	@Inject
	private CuentaDAO cudao;

	@Inject
	private PagoDAO pdao;

	private int idCuenta;

	public void insertar(Credito c) throws Exception {

		Cuenta cuentavalida = validarCuenta(idCuenta);

		if (cuentavalida != null) {
			int numCuenta = cuentavalida.getIdcuenta();

			Cuenta cuentita = new Cuenta();
			cuentita.setIdcuenta(numCuenta);
			c.setEstado("Aprobado");
			c.setCuenta(cuentita);
			cdao.insertar(c);
			try {
				generarPagos(c.getNumeroCredito());
			} catch (Exception e) {

				e.printStackTrace();
			}

		} else {
			throw new Exception("Cuenta no existe");
		}
	}

	public Cuenta validarCuenta(int numCuenta) throws Exception {
		Cuenta aux = cudao.read(numCuenta);
		if (aux != null) {
			idCuenta = aux.getIdcuenta();
			return aux;
		} else
			throw new Exception("cuenta no existe");
	}

	public void generarPagos(int numeroCredito) {

		Credito c = cdao.read(numeroCredito);
		if (c != null) {
			System.out.println("creditooo entonctrado " + c.toString());

			String fechaActual = "";
			String fecha = c.getPriFecha();
			String[] parts = fecha.split("/");
			System.out.println("Insertooooooo");
			String dia = parts[0];
			String part1 = parts[1];
			String part2 = parts[2];
			int mes = Integer.parseInt(part1);
			int anio = Integer.parseInt(part2);

			double valorr = c.getMonto();
			int numMeses = c.getNumMeses();

			double respuesta = valorr / numMeses;
			DecimalFormat df = new DecimalFormat("#,000");

			int cont = 0;
			System.out.println("Inseqqqqqqqqq " + (df.format(respuesta)));
			for (int i = 0; i < numMeses; i++) {
				Pago p = new Pago();
				System.out.println("dentro del for  " + c.getNumeroCredito());
				System.out.println("******* " + i);

				mes += cont;
				if (mes > 12) {
					anio += 1;
					mes = 1;
				}

				Credito cred = new Credito();
				int numCredito = c.getNumeroCredito();
				cred.setNumeroCredito(numCredito);

				fechaActual = dia + "/" + mes + "/" + anio;
				System.out.println("*******FECHA ACTUCAL " + fechaActual);
				p.setValor(Double.parseDouble((df.format(respuesta))));
				p.setFechaPago(fechaActual);
				p.setNumCuota(i);
				p.setEstado("Pendiente");
				p.setCancelado(0);
				p.setCredito(cred);

				pdao.insertar(p);

				cont = 1;

			}
		} else
			System.out.println("Credito no existe");
	}

	public List<Pago> getPagoCredito(int numero) {
		return pdao.getPagoCreditos(numero);
	}

	public Credito getCreditoMonto(int idCuenta) {
		return cdao.getCreditoMonto(idCuenta);
	}
	
	public List<Pago> getPagosVencidos(int numero, String estado) {
		return pdao.getPagosVencidos(numero, estado);
	}

	public void updatePagos(int numero) throws Exception {
		String getechaActualFina = getechaActualFinal();
		double valorPagar = 0.0;
		double valorCancelado = 0.0;
		double cuentaSaldo = 0.0;
		String fechaPago = "";
		double nuevoSaldoCuenta = 0.0;

		List<Pago> getPagos = pdao.getPagos(numero);
		Pago p = pdao.getPagoValor(numero);
		Cuenta cuenta = cudao.buscarCuenta(idCuenta);

		valorPagar = p.getValor();
		// valorCancelado = p.getCancelado();
		cuentaSaldo = cuenta.getSaldo();
		System.out.println("Entro 00011: ");
		if (valorPagar <= cuentaSaldo) {
			System.out.println("Entro 00022: ");
			for (Pago pago : getPagos) {
				System.out.println("Entro 000333: ");
				valorCancelado = pago.getCancelado();
				fechaPago = pago.getFechaPago();
				System.out.println("Fechas pago: " + fechaPago +" Fechas actual: " + getechaActualFina);
				int resultado = fechaPago.compareTo(getechaActualFina);
				System.out.println("Resultado: " + resultado);
				if (resultado < 0) {
					System.out.println("Entro 00044: ");
					if (valorCancelado < valorPagar) {
						System.out.println("Entro 0055: ");
						nuevoSaldoCuenta = cuentaSaldo - valorPagar;
						cuenta.setSaldo(nuevoSaldoCuenta);
						p.setCancelado(valorPagar);
						p.setEstado("Pagado");
						cudao.actualizarSaldo(cuenta);
						pdao.actualizarSaldo(p);
					}

				}

			}

		} else
			throw new Exception("No existe saldo suficiente para el debito");
	}

	public String getFechaActual() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDateTime now = LocalDateTime.now();
		System.out.println(dtf.format(now));
		return dtf.format(now);
	}

	public String getechaActualFinal() {
		String getFechaActual = getFechaActual();
		String fecha001 = getFechaActual.replaceAll("0", "");
		String[] parts = fecha001.split("/");
		String mes = parts[0];
		String dia = parts[1];
		String[] parts002 = getFechaActual.split("/");
		String anio = parts002[2];
		String fechaActual = dia + "/" + mes + "/" + anio;
		return fechaActual;
	}
}
