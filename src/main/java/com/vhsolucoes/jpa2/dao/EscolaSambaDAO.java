package com.vhsolucoes.jpa2.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.vhsolucoes.jpa2.modelo.EscolaSamba;
import com.vhsolucoes.jpa2.service.NegocioException;
import com.vhsolucoes.jpa2.util.jpa.Transactional;

public class EscolaSambaDAO implements Serializable {

	@Inject
	private EntityManager em;

	public void salvar(EscolaSamba escolaSamba) {
		em.merge(escolaSamba);
	}

	@SuppressWarnings("unchecked")
	public List<EscolaSamba> buscarTodos() {
		return em.createQuery("from EscolaSamba").getResultList();
	}

	@Transactional
	public void excluir(EscolaSamba escolaSambaSelecionada) throws NegocioException {
		EscolaSamba escolaSamba = em.find(EscolaSamba.class, escolaSambaSelecionada.getId());
		em.remove(escolaSamba);
		em.flush();
	}

	public EscolaSamba buscarPeloId(Long id) {
		return em.find(EscolaSamba.class, id);
	}

}
