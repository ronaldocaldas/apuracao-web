package com.vhsolucoes.jpa2.service;

import java.io.Serializable;

import javax.inject.Inject;

import org.hibernate.exception.ConstraintViolationException;

import com.vhsolucoes.jpa2.dao.EscolaSambaDAO;
import com.vhsolucoes.jpa2.modelo.EscolaSamba;
import com.vhsolucoes.jpa2.util.jpa.Transactional;

public class CadastroEscolaSambaService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EscolaSambaDAO escolaSambaDAO;

	@Transactional
	public void salvar(EscolaSamba escolaSamba) throws NegocioException {

		try{
		this.escolaSambaDAO.salvar(escolaSamba);
		} catch (Exception e) {
			if (e.getCause() instanceof ConstraintViolationException) {
				throw new NegocioException("Uma escola de samba já fui cadastrada com o CNPJ "+escolaSamba.getCnpj()+ "!");
			}else{
				throw new NegocioException(e.getMessage());
			}
		}
	}
}
