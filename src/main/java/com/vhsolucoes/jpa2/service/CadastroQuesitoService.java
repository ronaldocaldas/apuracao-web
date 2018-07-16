package com.vhsolucoes.jpa2.service;

import java.io.Serializable;

import javax.inject.Inject;

import org.hibernate.exception.ConstraintViolationException;

import com.vhsolucoes.jpa2.dao.QuesitoDAO;
import com.vhsolucoes.jpa2.modelo.Quesito;
import com.vhsolucoes.jpa2.util.jpa.Transactional;

public class CadastroQuesitoService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private QuesitoDAO quesitoDAO;

	@Transactional
	public void salvar(Quesito quesito) throws NegocioException {

		try {
			this.quesitoDAO.salvar(quesito);
		} catch (Exception e) {
			if (e.getCause() instanceof ConstraintViolationException) {
				throw new NegocioException("O quesito " +quesito.getNome()+" já foi cadastrodo");
			}else{
				throw new NegocioException(e.getMessage());
			}
		}
		
	}
}
