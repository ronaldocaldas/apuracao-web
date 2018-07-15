package com.vhsolucoes.jpa2.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.vhsolucoes.jpa2.dao.NotaDAO;
import com.vhsolucoes.jpa2.wrapper.ApuracaoWrapper;

public class ApuracaoService implements Serializable {
	
	@Inject
	private NotaDAO notaDAO;

	public List<ApuracaoWrapper> buscaResultados() {
		return notaDAO.buscaResultados();
	}

}
