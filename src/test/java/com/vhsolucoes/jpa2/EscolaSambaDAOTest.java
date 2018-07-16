package com.vhsolucoes.jpa2;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.jintegrity.core.JIntegrity;
import com.jintegrity.helper.JPAHelper;
import com.vhsolucoes.jpa2.dao.EscolaSambaDAO;
import com.vhsolucoes.jpa2.modelo.EscolaSamba;
import com.vhsolucoes.jpa2.service.NegocioException;

public class EscolaSambaDAOTest {

	private JIntegrity helper = new JIntegrity();

	private EscolaSambaDAO escolaSambaDAO;

	@Before
	public void init() {
		helper.cleanAndInsert();

		this.escolaSambaDAO = new EscolaSambaDAO();
		this.escolaSambaDAO.setEntityManager(JPAHelper.currentEntityManager());
	}

	@Test
	public void buscarEscolaSambaPeloIdTest() {
		EscolaSamba escolaSamba = this.escolaSambaDAO.buscarPeloId(1l);

		assertEquals(1L, escolaSamba.getId().longValue());
		assertEquals("Acadêmicos da Ilha de Java", escolaSamba.getNome());
		assertEquals("82237928000159", escolaSamba.getCnpj());

	}

	@Test
	public void buscarEscolaSambasTest() {
		List<EscolaSamba> escolaSambas = this.escolaSambaDAO.buscarTodos();

		assertEquals(3, escolaSambas.size());
	}

	@Test
	public void edicaoEscolaSambaTest() {
		EscolaSamba escolaSamba = this.escolaSambaDAO.buscarPeloId(1L);

		escolaSamba.setNome("Rodolfo");

		this.escolaSambaDAO.salvar(escolaSamba);

		EscolaSamba escolaSambaModidifado = this.escolaSambaDAO.buscarPeloId(1L);

		assertEquals(1L, escolaSambaModidifado.getId().longValue());
		assertEquals("Rodolfo", escolaSambaModidifado.getNome());
	}

	@Test
	public void exclusaoEscolaSambaTest() throws NegocioException {
		EscolaSamba escolaSamba = this.escolaSambaDAO.buscarPeloId(3L);

		this.escolaSambaDAO.excluir(escolaSamba);

		EscolaSamba escolaSambaExcluido = this.escolaSambaDAO.buscarPeloId(3L);

		assertEquals(null, escolaSambaExcluido);
	}

}
