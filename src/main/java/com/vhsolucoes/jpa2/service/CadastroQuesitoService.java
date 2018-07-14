package com.vhsolucoes.jpa2.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.vhsolucoes.jpa2.dao.QuesitoDAO;
import com.vhsolucoes.jpa2.modelo.Quesito;
import com.vhsolucoes.jpa2.util.jpa.Transactional;

public class CadastroQuesitoService implements Serializable {

	@Inject
	private QuesitoDAO quesitoDAO;

	@Transactional
	public void salvar(Quesito quesito) throws NegocioException {

		if (quesito.getNome() == null || quesito.getNome().trim().equals("")) {
			throw new NegocioException("O nome do quesito é obrigatório!");
		}
		this.quesitoDAO.salvar(quesito);
		
	}
}
