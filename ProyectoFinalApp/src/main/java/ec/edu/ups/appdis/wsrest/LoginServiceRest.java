package ec.edu.ups.appdis.wsrest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ec.edu.ups.appdis.model.Credito;
import ec.edu.ups.appdis.model.Pago;
import ec.edu.ups.appdis.on.AuditoriaON;
import ec.edu.ups.appdis.on.ClienteON;
import ec.edu.ups.appdis.on.CreditoON;
import ec.edu.ups.appdis.on.CuentaON;
import ec.edu.ups.appdis.on.MovimientoON;

@Path("/cliente")
public class LoginServiceRest {
	
	@Inject
	private AuditoriaON aon;
	
	@Inject
	private ClienteON clion;
	
	@Inject
	private CuentaON cueon;
	
	@Inject
	private CreditoON con;
	
	@Inject
	private MovimientoON movon;
	
	@GET
	@Path("/login")
	@Produces("application/json")
	public  Response loginCliente(@QueryParam("correo") String correo, @QueryParam("password") String password) {

		Response.ResponseBuilder builder = null;
		Map<String, String> data = new HashMap<>();

		try {
			aon.getClienteLoginCorreoPass(correo, password);
			data.put("code", "1");
			data.put("message", "OK");
			builder = Response.status(Response.Status.OK).entity(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			data.put("code", "99");
			data.put("message", e.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(data);
		}

		return builder.build();
		
	}
	
	@GET
	@Path("/updatecliente")
	@Produces("application/json")
	public  Response updateClientePassword(@QueryParam("correo") String correo) {

		Response.ResponseBuilder builder = null;
		Map<String, String> data = new HashMap<>();

		try {
			clion.updateClientePassword(correo);
			data.put("code", "1");
			data.put("message", "Nueva contrasenia enviado a su correo.");
			builder = Response.status(Response.Status.OK).entity(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			data.put("code", "99");
			data.put("message", e.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(data);
		}

		return builder.build();
	}
	
	@GET
	@Path("/saldocuenta")
	@Produces("application/json")
	public Response valorCuenta (@QueryParam("idcuenta") int idcuenta) {
		double saldo = 0.0; 		
		Response.ResponseBuilder builder = null;
		Map<String, String> data = new HashMap<>();

		try {
			saldo = cueon.getValorCuenta(idcuenta);
			data.put("code", "1");
			data.put("saldo", saldo+"");
			builder = Response.status(Response.Status.OK).entity(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			data.put("code", "99");
			data.put("message", e.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(data);
		}

		return builder.build();
	}
	
	@GET
	@Path("/transfer")
	@Produces("application/json")
	public Response movimiento(@QueryParam("corigen") int corigen, @QueryParam("cdestino") int cdestino, @QueryParam("valor") double valor ) {
		Response.ResponseBuilder builder=null;
		Map<String, String> data=new HashMap<>();
		try {
			System.out.println("entrooo");
			movon.nuevaTransferencia(corigen, cdestino, valor);
			data.put("code", "1");
			data.put("message", "TRANSACCIÓN EXITOSA");
			builder=Response.status(Response.Status.OK).entity(data);
		}catch(Exception e) {
			data.put("code", "99");
			data.put("message", e.getMessage());
			builder=Response.status(Response.Status.BAD_REQUEST).entity(data);
			
			// TODO Auto-generated catch block
			e.printStackTrace();	
			
		}
		
		return builder.build();	
	}
	
	@GET
	@Path("/getcreditomonto")
	@Produces("application/json")
	public Response getCreditoMonto(@QueryParam("idCuenta") int idCuenta) {
		Response.ResponseBuilder builder=null;
		Map<String, String> data=new HashMap<>();
		try {
			System.out.println("entrooo");
			Credito cred = con.getCreditoMonto(idCuenta);
			double monto = cred.getMonto();
			data.put("code", "1");
			data.put("message", monto+"");
			builder=Response.status(Response.Status.OK).entity(data);
		}catch(Exception e) {
			data.put("code", "99");
			data.put("message", e.getMessage());
			builder=Response.status(Response.Status.BAD_REQUEST).entity(data);
			
			// TODO Auto-generated catch block
			e.printStackTrace();	
			
		}
		
		return builder.build();	
	}
	
	@GET
	@Path("/listpagsvencidos")
	@Produces("application/json")
	public List<Pago> getPagosVencidos(@QueryParam("numeroCredito") int numeroCredito) {
		return con.getPagosVencidos(numeroCredito, "Vencido");
	}

}
