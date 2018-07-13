package com.vhsolucoes.jpa2.dao;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.vhsolucoes.jpa2.modelo.Jurado;

public class JuradoDAO implements Serializable {

	@Inject
	private EntityManager em;

	public void salvar(Jurado jurado) {
		em.persist(jurado);
	}

}
