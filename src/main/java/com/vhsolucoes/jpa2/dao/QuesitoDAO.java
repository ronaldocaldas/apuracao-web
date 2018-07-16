package com.vhsolucoes.jpa2.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.exception.ConstraintViolationException;

import com.vhsolucoes.jpa2.modelo.Quesito;
import com.vhsolucoes.jpa2.service.NegocioException;
import com.vhsolucoes.jpa2.util.jpa.Transactional;

public class QuesitoDAO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
		try{
		Quesito quesito = em.find(Quesito.class, quesitoSelecionado.getId());
		em.remove(quesito);
		em.flush();
		} catch (Exception e) {
			if (e.getCause() instanceof ConstraintViolationException) {
				throw new NegocioException("O Quesito "+quesitoSelecionado.getNome()+" não pode ser excluído porque já  votou!");
			}else{
				throw new NegocioException(e.getMessage());
			}
		}
		
	}

	public Quesito buscarPeloId(Long id) {
		return em.find(Quesito.class, id);
	}

	public void setEntityManager(EntityManager manager) {
		this.em = manager;
	}
}
