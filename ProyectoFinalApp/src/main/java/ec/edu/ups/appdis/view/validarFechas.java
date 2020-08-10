package ec.edu.ups.appdis.view;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@ManagedBean
public class validarFechas {
	
	@Inject
	private FacesContext facesContext;

    private boolean fechaInicialFinalMayoraHoy;
    private boolean fechaInicialyFinMayor30Dias;
    public static final int NEGATIVO_TREINTA = -30;

    private Date fechaInicio;
    private Date fechaFinal;
    
    
    public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	public void validarFechas() throws Exception {
    	
        fechaInicialFinalMayoraHoy = false;
        fechaInicialyFinMayor30Dias = false;
        
        if (this.fechaInicio != null && this.fechaFinal != null) {
            if (isMayorFechaHoy(this.fechaInicio)
                    || isMayorFechaHoy(this.fechaFinal)) {
                fechaInicialFinalMayoraHoy = true;
    			//FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR,  "Error!", "Fecha Inicio o Fecha Fin es mayor a la fecha de hoy");
    			//facesContext.addMessage(null, m);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Fecha Inicio o Fecha Fin es mayor a la fecha de hoy"));
            }
        }
            if (isMayor30Dias(this.fechaInicio, this.fechaFinal)) {
                fechaInicialyFinMayor30Dias = true;
                //FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR,  "Error!", "Fecha es mayor a 30 dias.");
    			//facesContext.addMessage(null, m);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Fecha es mayor a 30 dias."));
            }
        }
        
        /**
         * Descripci&oacute;n de la regla: Si la fecha inicial o la fecha final es
         * mayor a la fecha acutal. la validaci&oacute termina como incorrecta
         *
         * @param fecha La fecha inicial de consulta o la fecha final de consulta .
         * @throws Exception Si las fechas violan la regla de negocio.
         */
        Boolean isMayorFechaHoy(Date fecha) throws Exception {
            Date hoy = new Date();
            Boolean resultado = false;

            if (fecha != null && fecha.after(hoy)) {
                resultado = true;
            }

            return resultado;
        }
        
        /**
         * Descripci&oacute;n de la regla: Si la fecha final es mayor a 30
         * d&iacute;as de la fecha inicial la validaci&oacute termina como
         * incorrecta
         *
         * @param fechaInicial La fecha inicial de consulta.
         * @param fechaFinal final de consulta.
         * @throws Exception Si las fechas violan la regla de negocio.
         */
        public Boolean isMayor30Dias(Date fechaInicial, Date fechaFinal) throws Exception {
            Date fhFinal = fechaFinal;
            Boolean resultado = false;
            if (fechaInicial != null && fhFinal != null) {
                Calendar c = Calendar.getInstance();
                c.setTime(fhFinal);
                c.add(Calendar.DATE, NEGATIVO_TREINTA);
                fhFinal = c.getTime();
                if (fechaInicial.getTime() < fhFinal.getTime()) {
                    resultado = true;
                }
            }
            return resultado;
        }
    	public void getFechaActual() {
    		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    		LocalDateTime now = LocalDateTime.now();
    		System.out.println(dtf.format(now));
    		//return dtf.format(now);

    	}
}
