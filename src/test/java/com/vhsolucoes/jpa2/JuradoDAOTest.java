package com.vhsolucoes.jpa2;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.jintegrity.core.JIntegrity;
import com.jintegrity.helper.JPAHelper;
import com.vhsolucoes.jpa2.dao.JuradoDAO;
import com.vhsolucoes.jpa2.modelo.Jurado;
import com.vhsolucoes.jpa2.service.NegocioException;

public class JuradoDAOTest {

	private JIntegrity helper = new JIntegrity();

	private JuradoDAO juradoDAO;

	@Before
	public void init() {
		helper.cleanAndInsert();

		this.juradoDAO = new JuradoDAO();
		this.juradoDAO.setEntityManager(JPAHelper.currentEntityManager());
	}

	@Test
	public void buscarJuradoPeloIdTest() {
		Jurado jurado = this.juradoDAO.buscarPeloId(1L);

		assertEquals(1L, jurado.getId().longValue());
		assertEquals("Ronaldo", jurado.getNome());
	}

	@Test
	public void buscarJuradosTest() {
		List<Jurado> jurados = this.juradoDAO.buscarTodos();

		assertEquals(3, jurados.size());
	}

	@Test
	public void edicaoJuradoTest() {
		Jurado jurado = this.juradoDAO.buscarPeloId(1L);

		jurado.setNome("Rodolfo");

		this.juradoDAO.salvar(jurado);

		Jurado juradoModidifado = this.juradoDAO.buscarPeloId(1L);

		assertEquals(1L, juradoModidifado.getId().longValue());
		assertEquals("Rodolfo", juradoModidifado.getNome());
	}

	@Test
	public void exclusaoJuradoTest() throws NegocioException {
		Jurado jurado = this.juradoDAO.buscarPeloId(3L);

		this.juradoDAO.excluir(jurado);

		Jurado juradoExcluido = this.juradoDAO.buscarPeloId(3L);

		assertEquals(null, juradoExcluido);
	}

}
