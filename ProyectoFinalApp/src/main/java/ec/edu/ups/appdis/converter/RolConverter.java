package ec.edu.ups.appdis.converter;

import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import ec.edu.ups.appdis.dao.RolDao;
import ec.edu.ups.appdis.model.Rol;
import ec.edu.ups.appdis.on.UsuarioON;
import testjpa.utils.JsfUtil;

@Named("rolConverter")
@FacesConverter(forClass = Rol.class)
@ManagedBean
public class RolConverter implements Converter{
	
	
	@Inject
	private RolDao uon;
	
	public RolConverter() {
		
	}
	
	  public SelectItem[] getItemsAvailableSelectMany() {
	        return JsfUtil.getSelectItems(uon.getRol(), false);
	    }

	
    public Rol getRoles(java.lang.Integer id) {
        return uon.read(id);
    }

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
		// TODO Auto-generated method stub
        if (value == null || value.length() == 0) {
            return null;
        }
        RolConverter controller = (RolConverter) facesContext.getApplication().getELResolver().
                getValue(facesContext.getELContext(), null, "rolConverter");
        return controller.getRoles(getKey(value));
    }

    java.lang.Integer getKey(String value) {
        java.lang.Integer key;
        key = Integer.valueOf(value);
        return key;
    }

    String getStringKey(java.lang.Integer value) {
        StringBuilder sb = new StringBuilder();
        sb.append(value);
        return sb.toString();
    }

	@Override
	public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
		// TODO Auto-generated method stub
	     if (object == null || (object instanceof String && ((String) object).length() == 0)){
             return null;
         }
         if (object instanceof Rol) {
             Rol o = (Rol) object;
             return getStringKey(o.getIdRol());
         } else {
             throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Rol.class.getName());
         }
     }
     

}
