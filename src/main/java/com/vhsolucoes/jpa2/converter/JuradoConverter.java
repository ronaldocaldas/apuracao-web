package com.vhsolucoes.jpa2.converter;


import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.vhsolucoes.jpa2.dao.JuradoDAO;
import com.vhsolucoes.jpa2.modelo.Jurado;
import com.vhsolucoes.jpa2.util.cdi.CDIServiceLocator;


@FacesConverter(forClass=Jurado.class)
public class JuradoConverter implements Converter {

	private JuradoDAO juradoDAO;
	
	public JuradoConverter() {
		this.juradoDAO = CDIServiceLocator.getBean(JuradoDAO.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Jurado retorno = null;

		if (value != null) {
			retorno = this.juradoDAO.buscarPeloId(new Long(value));
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long codigo = ((Jurado) value).getId();
			String retorno = (codigo == null ? null : codigo.toString());
			
			return retorno;
		}
		
		return "";
	}

}