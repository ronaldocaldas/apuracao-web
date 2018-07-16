package com.vhsolucoes.jpa2.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.exception.ConstraintViolationException;

import com.vhsolucoes.jpa2.modelo.Jurado;
import com.vhsolucoes.jpa2.service.NegocioException;
import com.vhsolucoes.jpa2.util.jpa.Transactional;

public class JuradoDAO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
		try {
			Jurado jurado = em.find(Jurado.class, juradoSelecionado.getId());
			em.remove(jurado);
			em.flush();
		} catch (Exception e) {
			if (e.getCause() instanceof ConstraintViolationException) {
				throw new NegocioException("O jurado "+juradoSelecionado.getNome()+" não pode ser excluído porque já  votou!");
			}else{
				throw new NegocioException(e.getMessage());
			}
		}
		
	}

	public Jurado buscarPeloId(Long id) {
		return em.find(Jurado.class, id);
	}
	
	public void setEntityManager(EntityManager manager) {
		this.em = manager;
	}

}
