package com.vhsolucoes.jpa2.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.vhsolucoes.jpa2.dao.EscolaSambaDAO;
import com.vhsolucoes.jpa2.modelo.EscolaSamba;
import com.vhsolucoes.jpa2.util.jpa.Transactional;

public class CadastroEscolaSambaService implements Serializable {

	@Inject
	private EscolaSambaDAO escolaSambaDAO;

	@Transactional
	public void salvar(EscolaSamba escolaSamba) throws NegocioException {

		if (escolaSamba.getNome() == null || escolaSamba.getNome().trim().equals("")) {
			throw new NegocioException("O nome da escola de samba é obrigatório!");
		}
		this.escolaSambaDAO.salvar(escolaSamba);
		
	}
}
