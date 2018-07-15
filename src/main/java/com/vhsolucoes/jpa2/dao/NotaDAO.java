package com.vhsolucoes.jpa2.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import com.vhsolucoes.jpa2.modelo.Nota;
import com.vhsolucoes.jpa2.service.NegocioException;
import com.vhsolucoes.jpa2.util.jpa.Transactional;
import com.vhsolucoes.jpa2.wrapper.ApuracaoWrapper;

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

	public List<ApuracaoWrapper> buscaResultados() {
		CriteriaBuilder builder = em.getCriteriaBuilder();

		// CriteriaQuery<ApuracaoWrapper> criteriaQuery =
		// builder.createQuery(ApuracaoWrapper.class);
		//
		// Root<Nota> nota = criteriaQuery.from(Nota.class);
		//
		// criteriaQuery.select(builder.construct(ApuracaoWrapper.class,
		// nota.get("escola").get("nome"),
		// nota.get("quesito").get("nome"), nota.get("nota")));
		//
		// TypedQuery<ApuracaoWrapper> query = em.createQuery(criteriaQuery);

		String jpql = "select e.nome, q.nome, sum(n.nota) , min(n.nota), max(n.nota), "
				+ " avg(n.nota),"
				+ " ( select sum(n.nota) from Nota n JOIN  n.escola es where es.id=e.id group by e) "
				+ "	from Nota n " 
				+ " JOIN  n.escola e " 
				+ " JOIN n.quesito q " 
				+ " group by e, q"
				+ " order by e.id ";

//		(String nomeEscola, String nomeQuesito, int nota, int menorNota, int maiorNota,
//				float media)
//		
		List<ApuracaoWrapper> resultado = new ArrayList<ApuracaoWrapper>();
		List<Object[]> resultados = em.createQuery(jpql).getResultList();
                          
		for (Object[] objects : resultados) {
			ApuracaoWrapper aux = new ApuracaoWrapper((String) objects[0], (String) objects[1],
					((Long) objects[2]).intValue(),((Integer) objects[3]),
					((Integer) objects[4]), ((Double) objects[5]).floatValue(), ((Long) objects[6]).intValue());
			resultado.add(aux);
		}

		return resultado;

	}

}
