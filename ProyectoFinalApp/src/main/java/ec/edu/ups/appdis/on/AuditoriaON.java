package ec.edu.ups.appdis.on;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.edu.ups.appdis.dao.AuditoriaDAO;
import ec.edu.ups.appdis.dao.UsuarioDAO;
import ec.edu.ups.appdis.model.Auditoria;
import ec.edu.ups.appdis.model.Cliente;
import ec.edu.ups.appdis.model.Rol;
import ec.edu.ups.appdis.model.Usuario;
import testjpa.utils.EnviarCorreoElectronico;

@Stateless
public class AuditoriaON {

	@Inject
	private AuditoriaDAO aDAO;

	List<Cliente> getclienteCorreo;
	List<Cliente> getclientePass;

	private static final String accesoCorrecto = "Acceso satisfactorio";
	private static final String accesoIncorrecto = "Acceso fallido";

	 /**
	  * Metodo para insertar un Objeto Auditoria
	  * @param audi El parametro audi es para guardar el objecto Auditoria
	  */
	public void guardarAuditoria(Auditoria audi) {
		aDAO.insert(audi);
	}

	/**
	 * 
	 * @param correo
	 * @param pass
	 * @return
	 */
	public List<Usuario> geUsers(String correo, String pass) {
		return aDAO.getUsers(correo, pass);
	}

	/**
	 * 
	 * @param correo
	 * @param pass
	 * @return
	 */
	public List<Cliente> getClienteLogin(String correo, String pass) {
		return aDAO.getClienteLogin(correo, pass);
	}

	/**
	 * 
	 * @param correo
	 * @return
	 * @throws Exception
	 */
	public List<Cliente> getClienteLoginCorreo(String correo) throws Exception {
		List<Cliente> getclienteCorreo = aDAO.getClienteLoginCorreo(correo);
		if (getclienteCorreo.isEmpty())
			throw new Exception("Correo cliente ¡No existe!");
		return getclienteCorreo;

	}

	/**
	 * 
	 * @param pass
	 * @return
	 * @throws Exception
	 */
	public List<Cliente> getClienteLoginPass(String pass) throws Exception {
		List<Cliente> getclientePass = aDAO.getClienteLoginPass(pass);
		if (getclientePass.isEmpty())
			throw new Exception("Password cliente ¡Fallido!");
		return getclientePass;

	}

	/**
	 * 
	 * @param idcliente
	 * @return
	 */
	public List<Auditoria> getAuditoria(String idcliente) {
		return aDAO.getAuditoria(idcliente);

	}

	/**
	 * 
	 * @param correo
	 * @param pass
	 * @throws Exception
	 */
	public void getClienteLoginCorreoPass(String correo, String pass) throws Exception {
		getclienteCorreo = aDAO.getClienteLoginCorreo(correo);
		getclientePass = aDAO.getClienteLoginPass(pass);
		guardarAuditoriaSuccessful();
		enviarCorreoAccesoCuenta();
		if (getclienteCorreo.isEmpty())
			throw new Exception("Correo del cliente ¡No existe!");
		if (getclientePass.isEmpty())
			throw new Exception("Password del cliente ¡Fallido!");

	}

	/**
	 * 
	 */
	public void guardarAuditoriaSuccessful() {

		if (!getclienteCorreo.isEmpty()) {
			Auditoria newAudi = new Auditoria();
			String fechaAct = getFechaActual();
			newAudi.setFecha(fechaAct);
			if (!getclientePass.isEmpty())
				newAudi.setDescripcion(accesoCorrecto);
			else
				newAudi.setDescripcion(accesoIncorrecto);
			Cliente c = new Cliente();
			for (Cliente cli : getclienteCorreo) {
				c.setIdcedula(cli.getIdcedula());
			}
			newAudi.setCliente(c);

			aDAO.insert(newAudi);
			System.out.println("registro guardado");
		} else {
			System.out.println("Correo cliente ¡No existe!");
		}
	}

	/**
	 * 
	 */
	public void enviarCorreoAccesoCuenta() {
		String cedula = "";
		String nombre = "";
		String apellido = "";
		String correo = "";

		if (!getclienteCorreo.isEmpty()) {

			//Cliente c = new Cliente();
			for (Cliente cli : getclienteCorreo) {
				cedula = cli.getIdcedula();
				nombre = cli.getNombre();
				apellido = cli.getApellido();
				correo = cli.getCorreo();
			}

			if (!getclientePass.isEmpty()) {

				String destinatario = correo; // A quien le quieres escribir.
				String asunto = "Acceso a mi cuenta";
				String cuerpo = "Acceso: " + accesoCorrecto + " \n  CON DATOS DEL CLIENTE: " + " \n Cedula: " + cedula
						+ " \n Nombre: " + nombre + " \n Apellido: " + apellido + " \n Correo: " + correo;
				EnviarCorreoElectronico.enviarConGMail(destinatario, asunto, cuerpo);

			} else {

				String destinatario = correo; // A quien le quieres escribir.
				String asunto = "Acceso a mi cuenta";
				String cuerpo = "Acceso: " + accesoIncorrecto + " \n  CON DATOS DEL CLIENTE: " + " \n Cedula: " + cedula
						+ " \n Nombre: " + nombre + " \n Apellido: " + apellido + " \n Correo: " + correo;
				EnviarCorreoElectronico.enviarConGMail(destinatario, asunto, cuerpo);

			}

		} else {
			System.out.println("Correo cliente ¡No existe!");
		}

	}

	/**
	 * 
	 * @return
	 */
	public String getFechaActual() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		System.out.println(dtf.format(now));
		return dtf.format(now);

	}

}
