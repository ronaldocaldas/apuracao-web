package com.vhsolucoes.jpa2.service;

import java.io.Serializable;

import javax.inject.Inject;

import org.hibernate.exception.ConstraintViolationException;

import com.vhsolucoes.jpa2.dao.JuradoDAO;
import com.vhsolucoes.jpa2.modelo.Jurado;
import com.vhsolucoes.jpa2.util.jpa.Transactional;

public class CadastroJuradoService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private JuradoDAO juradoDAO;

	@Transactional
	public void salvar(Jurado jurado) throws NegocioException {

		try {
			this.juradoDAO.salvar(jurado);
		} catch (Exception e) {
			if (e.getCause() instanceof ConstraintViolationException) {
				throw new NegocioException("O CPF " +jurado.getCpf()+ " já foi registrado!");

			}else {
				throw new NegocioException(e.getMessage());
			}
		}
		
		
	}
}
