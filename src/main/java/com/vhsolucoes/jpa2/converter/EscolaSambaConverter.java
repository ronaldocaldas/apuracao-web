package com.vhsolucoes.jpa2.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.vhsolucoes.jpa2.dao.EscolaSambaDAO;
import com.vhsolucoes.jpa2.modelo.EscolaSamba;
import com.vhsolucoes.jpa2.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = EscolaSamba.class)
public class EscolaSambaConverter implements Converter {

	private EscolaSambaDAO escolaSambaDAO;

	public EscolaSambaConverter() {
		this.escolaSambaDAO = CDIServiceLocator.getBean(EscolaSambaDAO.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		EscolaSamba retorno = null;

		if (value != null) {
			retorno = this.escolaSambaDAO.buscarPeloId(new Long(value));
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long codigo = ((EscolaSamba) value).getId();
			String retorno = (codigo == null ? null : codigo.toString());

			return retorno;
		}

		return "";
	}

}