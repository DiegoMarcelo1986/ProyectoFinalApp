package ec.edu.ups.appdis.on;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.edu.ups.appdis.dao.ClienteDAO;
import ec.edu.ups.appdis.model.Cliente;
import testjpa.utils.EnviarCorreoElectronico;
import testjpa.utils.PasswordGenerator;

@Stateless
public class ClienteON {

	@Inject
	private ClienteDAO cdao;
	
	private String newpassword = "";

	/**
	 * 
	 * @param cliente
	 */
	public void inser(Cliente cliente) {
		Cliente aux = cdao.read(cliente.getIdcedula());

		if (aux != null) {
			cdao.update(cliente);
		} else {
			cdao.insert(cliente);
		}
	}

	/**
	 * 
	 * @param cliente
	 */
	public void updateClientePassword(String correo) throws Exception {
		Cliente aux = cdao.getCliente(correo);
		newpassword = PasswordGenerator.getPassword();
		if (aux != null) {
			cdao.updateCliente(correo, newpassword);
			System.out.println("registro guardado");
			enviarCorreo(correo);
			//throw new Exception("Nueva contrasenia enviado a su correo.");
		} else {
			throw new Exception("cliente no existe");
		}
	}

	/**
	 * 
	 * @param cedula
	 */
	public void remove(String cedula) {
		cdao.remove(cedula);
	}

	/**
	 * 
	 * @return
	 */
	public List<Cliente> getClientes() {
		return cdao.getClientes();
	}
	
	//NUEVO
	public void enviarCorreo(String correo) {
		Cliente updateCliente = cdao.getCliente(correo);
		String destinatario = correo; // A quien le quieres escribir.
		String asunto = "Datos del Cliente Nuevo";
		String cuerpo = "Cedula: " + updateCliente.getIdcedula() + " \n Nombre: " + updateCliente.getNombre()
				+ " \n Apellido: " + updateCliente.getApellido() + " \n Correo: " + updateCliente.getCorreo()
				+ " \n Nuevo Password: " + newpassword;

		EnviarCorreoElectronico.enviarConGMail(destinatario, asunto, cuerpo);

	}

}
