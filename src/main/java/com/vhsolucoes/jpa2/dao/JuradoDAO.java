package com.vhsolucoes.jpa2.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.vhsolucoes.jpa2.modelo.Jurado;
import com.vhsolucoes.jpa2.service.NegocioException;
import com.vhsolucoes.jpa2.util.jpa.Transactional;

public class JuradoDAO implements Serializable {

	@Inject
	private EntityManager em;

	public void salvar(Jurado jurado) {
		em.merge(jurado);
	}

	@SuppressWarnings("unchecked")
	public List<Jurado> buscarTodos() {
		return em.createQuery("from Jurado").getResultList();
	}

	@Transactional
	public void excluir(Jurado juradoSelecionado) throws NegocioException {
		Jurado jurado = em.find(Jurado.class, juradoSelecionado.getId());
		em.remove(jurado);
		em.flush();
	}

	public Jurado buscarPeloId(Long id) {
		return em.find(Jurado.class, id);
	}

}
