package com.vhsolucoes.jpa2.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.exception.ConstraintViolationException;

import com.vhsolucoes.jpa2.modelo.EscolaSamba;
import com.vhsolucoes.jpa2.service.NegocioException;
import com.vhsolucoes.jpa2.util.jpa.Transactional;

public class EscolaSambaDAO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
		try {
			EscolaSamba escolaSamba = em.find(EscolaSamba.class, escolaSambaSelecionada.getId());
			em.remove(escolaSamba);
			em.flush();
		} catch (Exception e) {
			if (e.getCause() instanceof ConstraintViolationException) {
				throw new NegocioException("A escola de samba " + escolaSambaSelecionada.getNome()
						+ " não pode ser excluído porque possui votação!");
			} else {
				throw new NegocioException(e.getMessage());
			}
		}

	}

	public EscolaSamba buscarPeloId(Long id) {
		return em.find(EscolaSamba.class, id);
	}
	
	public void setEntityManager(EntityManager manager) {
		this.em = manager;
	}

}
