package com.vhsolucoes.jpa2.service;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.PersistenceException;

import org.hibernate.exception.ConstraintViolationException;

import com.vhsolucoes.jpa2.dao.NotaDAO;
import com.vhsolucoes.jpa2.modelo.Nota;
import com.vhsolucoes.jpa2.util.jpa.Transactional;

public class CadastroNotaService implements Serializable {

	@Inject
	private NotaDAO notaDAO;

	@Transactional
	public void salvar(Nota nota) throws NegocioException {
		try {
			this.notaDAO.salvar(nota);
		} catch (PersistenceException e) {
			if (e.getCause() instanceof ConstraintViolationException) {
				throw new NegocioException("O jurado já votou esse quesito para essa escola!");

			}
		}
	}
}
