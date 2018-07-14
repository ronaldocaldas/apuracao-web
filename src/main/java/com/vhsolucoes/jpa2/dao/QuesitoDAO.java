package com.vhsolucoes.jpa2.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.vhsolucoes.jpa2.modelo.Quesito;
import com.vhsolucoes.jpa2.service.NegocioException;
import com.vhsolucoes.jpa2.util.jpa.Transactional;

public class QuesitoDAO implements Serializable {

	@Inject
	private EntityManager em;

	public void salvar(Quesito quesito) {
		em.merge(quesito);
	}

	@SuppressWarnings("unchecked")
	public List<Quesito> buscarTodos() {
		return em.createQuery("from Quesito").getResultList();
	}

	@Transactional
	public void excluir(Quesito quesitoSelecionado) throws NegocioException {
		Quesito quesito = em.find(Quesito.class, quesitoSelecionado.getId());
		em.remove(quesito);
		em.flush();
	}

	public Quesito buscarPeloId(Long id) {
		return em.find(Quesito.class, id);
	}

}
