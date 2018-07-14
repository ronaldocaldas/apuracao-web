package com.vhsolucoes.jpa2.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.vhsolucoes.jpa2.dao.QuesitoDAO;
import com.vhsolucoes.jpa2.modelo.Quesito;
import com.vhsolucoes.jpa2.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Quesito.class)
public class QuesitoConverter implements Converter {

	private QuesitoDAO quesitoDAO;

	public QuesitoConverter() {
		this.quesitoDAO = CDIServiceLocator.getBean(QuesitoDAO.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Quesito retorno = null;

		if (value != null) {
			retorno = this.quesitoDAO.buscarPeloId(new Long(value));
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long codigo = ((Quesito) value).getId();
			String retorno = (codigo == null ? null : codigo.toString());

			return retorno;
		}

		return "";
	}

}