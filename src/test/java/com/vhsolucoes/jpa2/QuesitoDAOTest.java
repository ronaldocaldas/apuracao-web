package com.vhsolucoes.jpa2;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.jintegrity.core.JIntegrity;
import com.jintegrity.helper.JPAHelper;
import com.vhsolucoes.jpa2.dao.QuesitoDAO;
import com.vhsolucoes.jpa2.modelo.Quesito;
import com.vhsolucoes.jpa2.service.NegocioException;

public class QuesitoDAOTest {

	private JIntegrity helper = new JIntegrity();

	private QuesitoDAO quesitoDAO;

	@Before
	public void init() {
		helper.cleanAndInsert();

		this.quesitoDAO = new QuesitoDAO();
		this.quesitoDAO.setEntityManager(JPAHelper.currentEntityManager());
	}

	@Test
	public void buscarQuesitoPeloIdTest() {
		Quesito quesito = this.quesitoDAO.buscarPeloId(1L);

		assertEquals(1L, quesito.getId().longValue());
		assertEquals("Comissão de frente", quesito.getNome());
	}

	@Test
	public void buscarQuesitosTest() {
		List<Quesito> quesitos = this.quesitoDAO.buscarTodos();

		assertEquals(3, quesitos.size());
	}

	@Test
	public void edicaoQuesitoTest() {
		Quesito quesito = this.quesitoDAO.buscarPeloId(1L);

		quesito.setNome("Harmonia");

		this.quesitoDAO.salvar(quesito);

		Quesito quesitoModidifado = this.quesitoDAO.buscarPeloId(1L);

		assertEquals(1L, quesitoModidifado.getId().longValue());
		assertEquals("Harmonia", quesitoModidifado.getNome());
	}

	@Test
	public void exclusaoQuesitoTest() throws NegocioException {
		Quesito quesito = this.quesitoDAO.buscarPeloId(3L);

		this.quesitoDAO.excluir(quesito);

		Quesito quesitoExcluido = this.quesitoDAO.buscarPeloId(3L);

		assertEquals(null, quesitoExcluido);
	}

}
