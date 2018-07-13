package com.vhsolucoes.jpa2.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.vhsolucoes.jpa2.dao.JuradoDAO;
import com.vhsolucoes.jpa2.modelo.Jurado;
import com.vhsolucoes.jpa2.util.jpa.Transactional;

public class CadastroJuradoService implements Serializable {

	@Inject
	private JuradoDAO juradoDAO;

	@Transactional
	public void salvar(Jurado jurado) throws NegocioException {

		if (jurado.getNome() == null || jurado.getNome().trim().equals("")) {
			throw new NegocioException("O nome do jurado é obrigatório!");
		}
		this.juradoDAO.salvar(jurado);
		
	}
}
