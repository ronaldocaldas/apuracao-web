package com.vhsolucoes.jpa2;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.jintegrity.core.JIntegrity;
import com.jintegrity.helper.JPAHelper;
import com.vhsolucoes.jpa2.dao.EscolaSambaDAO;
import com.vhsolucoes.jpa2.dao.JuradoDAO;
import com.vhsolucoes.jpa2.dao.NotaDAO;
import com.vhsolucoes.jpa2.dao.QuesitoDAO;
import com.vhsolucoes.jpa2.modelo.EscolaSamba;
import com.vhsolucoes.jpa2.modelo.Jurado;
import com.vhsolucoes.jpa2.modelo.Nota;
import com.vhsolucoes.jpa2.modelo.Quesito;
import com.vhsolucoes.jpa2.service.NegocioException;

public class VotacaoDAOTest {

	private JIntegrity helper = new JIntegrity();

	private NotaDAO notaDAO;

	private JuradoDAO juradoDAO;

	private QuesitoDAO quesitoDAO;
	
	private EscolaSambaDAO escolaSambaDAO;

	@Before
	public void init() {
		helper.cleanAndInsert();

		this.notaDAO = new NotaDAO();
		this.juradoDAO = new JuradoDAO();
		this.escolaSambaDAO =  new EscolaSambaDAO();
		this.quesitoDAO =  new QuesitoDAO();
		this.notaDAO.setEntityManager(JPAHelper.currentEntityManager());
		this.juradoDAO.setEntityManager((JPAHelper.currentEntityManager()));
		this.quesitoDAO.setEntityManager(JPAHelper.currentEntityManager());
		this.escolaSambaDAO.setEntityManager((JPAHelper.currentEntityManager()));
	}

	@Test
	public void buscarNotaPeloIdTest() {
		Nota nota = this.notaDAO.buscarPeloId(1L);

		assertEquals(1L, nota.getId().longValue());
		assertEquals(10, nota.getNota());
		assertEquals(1L, nota.getEscola().getId().longValue());
		assertEquals(1L, nota.getQuesito().getId().longValue());
		assertEquals(1L, nota.getJurado().getId().longValue());
	}

	@Test
	public void buscarNotasTest() {
		List<Nota> notas = this.notaDAO.buscarTodos();

		assertEquals(7, notas.size());
	}

	@Test
	public void votacaoTest() throws NegocioException {

		Nota novaNota = new Nota();
		

		Jurado juradoBanco = this.juradoDAO.buscarPeloId(2L);
		Quesito quesitoBanco = this.quesitoDAO.buscarPeloId(2l);
		EscolaSamba escolaSamba = this.escolaSambaDAO.buscarPeloId(2l);

		novaNota.setJurado(juradoBanco);
		novaNota.setQuesito(quesitoBanco);
		novaNota.setEscola(escolaSamba);
		novaNota.setNota(99);
		this.notaDAO.salvar(novaNota);
		
		List<Nota> notas = this.notaDAO.buscarTodos();
		assertEquals(2, notas.get(notas.size()-1).getJurado().getId().intValue());
		assertEquals(2, notas.get(notas.size()-1).getQuesito().getId().intValue());
		assertEquals(2, notas.get(notas.size()-1).getEscola().getId().intValue());

	}
}
