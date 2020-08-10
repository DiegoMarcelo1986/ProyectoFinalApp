package ec.edu.ups.appdis.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import ec.edu.ups.appdis.model.Cliente;
import ec.edu.ups.appdis.model.Cuenta;
import ec.edu.ups.appdis.model.Rol;
import ec.edu.ups.appdis.model.Usuario;
import ec.edu.ups.appdis.on.UsuarioON;
import testjpa.utils.*;   

@ManagedBean
public class UsuarioBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioON uon;

	private Usuario newUsuario;
	
	private List<Usuario> geUsers;

	@Inject
	private FacesContext facesContext;

	private boolean editable = false;

	@PostConstruct
	public void init() {
		newUsuario = new Usuario();
		geUsers = uon.geUsers();
	}

	public Usuario getNewUsuario() {
		return newUsuario;
	}

	public void setNewUsuario(Usuario newUsuario) {
		this.newUsuario = newUsuario;
	}

	public List<Usuario> getGeUsers() {
		return geUsers;
	}

	public void setGeUsers(List<Usuario> geUsers) {
		this.geUsers = geUsers;
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public String guardar() {

		try {

			uon.guardarUsuario(newUsuario);
			System.out.println("registro guardado");
			return "login_usuario?faces-redirect=true";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
			facesContext.addMessage(null, m);
			e.printStackTrace();
		}

		return null;
	}
	
	/**
	 * 
	 * @return
	 */
	public String guardarUpdate() {

		try {

			uon.guardarUsuario(newUsuario);
			System.out.println("registro guardado");
			return "admi_clientes?faces-redirect=true";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
			facesContext.addMessage(null, m);
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 
	 * @return
	 */
	public String loginUsuario() {
		List<Usuario> getUsuarioLogin = null;
		try {
			getUsuarioLogin = uon.getUsuarioLogin(newUsuario.getCorreo(), newUsuario.getPasssword());

			String rol = "";

			for (Usuario usuario : getUsuarioLogin) {
				rol = usuario.getRol();
			}

			if (rol.equals("Administrador"))
				return "admi_clientes?faces-redirect=true";
			else if (rol.equals("Cajero"))
				return "movimientoscajero?faces-redirect=true";

		} catch (Exception e) {
			// TODO Auto-generated catch block
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
			facesContext.addMessage(null, m);
			System.out.println("Error: " + m.getDetail());
			// e.printStackTrace();
		}

		return null;

	}

	/**
	 * 
	 * @param usu
	 * @return
	 */
	public String editar(Usuario usu) {
		newUsuario = usu;
		System.out.println(""+newUsuario.toString());
		editable = true;
		return "insertusuario?faces-redirect=true";
	}
	
	/**
	 * 
	 * @param usu
	 * @return
	 */
	public String updateEditar(Usuario usu) {
		newUsuario = usu;
		System.out.println(""+newUsuario.toString());
		editable = true;
		return "update_usuario?faces-redirect=true";
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public String borrar(int id) {
		try {
			uon.remove(id);
			System.out.println("registro eliminado");
			return "admi_clientes?faces-redirect=true";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
			// facesContext.addMessage(null, m);

			e.printStackTrace();
		}

		return null;
	}

	public void guardar2() {
		Usuario u = new Usuario();
		u.setNombre("mario");
		u.setCorreo("as22@gmail.com");
		u.setPasssword("patio.123");
		Rol r = new Rol();
		r.setIdRol(1);
		// u.setRol(r);

		uon.guardarUsuario(u);

	}
}
