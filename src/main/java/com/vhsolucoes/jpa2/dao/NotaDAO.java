package com.vhsolucoes.jpa2.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.vhsolucoes.jpa2.modelo.Nota;
import com.vhsolucoes.jpa2.service.NegocioException;
import com.vhsolucoes.jpa2.util.jpa.Transactional;

public class NotaDAO implements Serializable {

	@Inject
	private EntityManager em;

	public void salvar(Nota nota) throws NegocioException {
			em.merge(nota);
	}

	@SuppressWarnings("unchecked")
	public List<Nota> buscarTodos() {
		return em.createQuery("from Nota").getResultList();
	}

	@Transactional
	public void excluir(Nota notaSelecionado) throws NegocioException {
		Nota nota = em.find(Nota.class, notaSelecionado.getId());
		em.remove(nota);
		em.flush();
	}

	public Nota buscarPeloId(Long id) {
		return em.find(Nota.class, id);
	}

}
