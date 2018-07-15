package com.vhsolucoes.jpa2.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.vhsolucoes.jpa2.dao.EscolaSambaDAO;
import com.vhsolucoes.jpa2.dao.JuradoDAO;
import com.vhsolucoes.jpa2.dao.QuesitoDAO;
import com.vhsolucoes.jpa2.modelo.EscolaSamba;
import com.vhsolucoes.jpa2.modelo.Jurado;
import com.vhsolucoes.jpa2.modelo.Nota;
import com.vhsolucoes.jpa2.modelo.Quesito;
import com.vhsolucoes.jpa2.service.CadastroNotaService;
import com.vhsolucoes.jpa2.service.NegocioException;
import com.vhsolucoes.jpa2.util.jsf.FacesUtil;

@Named
@ViewScoped
public class VotacaoBean implements Serializable {

	@Inject
	private CadastroNotaService cadastroNotaService; 
	
	@Inject
	private JuradoDAO juradoDAO;

	@Inject
	private EscolaSambaDAO escolaSambaDAO;

	@Inject
	private QuesitoDAO quesitoDAO;

	private List<Jurado> jurados;

	private List<EscolaSamba> escolas;

	private List<Quesito> quesitos;

	private Jurado juradoSelecionado;

	private EscolaSamba escolaSelecionada;

	private Quesito quesitoSelecionado;
	
	private Nota nota;

	@PostConstruct
	private void ini() {
		limpar();
		this.jurados = juradoDAO.buscarTodos();
		this.escolas = escolaSambaDAO.buscarTodos();
		this.quesitos = quesitoDAO.buscarTodos();
	}
	
	public void limpar() {
		this.nota = new Nota();
		this.juradoSelecionado = new Jurado();
		this.escolaSelecionada = new EscolaSamba();
		this.quesitoSelecionado = new Quesito();
	}
	
	
	public void salvar(){
		try {
			this.cadastroNotaService.salvar(nota);
			FacesUtil.addSuccessMessage("Nota "+nota.getNota()+" quesito "+nota.getQuesito().getNome()+" para a escola "+nota.getEscola().getNome()+" salva com sucesso!");

		}catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}


	public List<Jurado> getJurados() {
		return jurados;
	}

	public void setJurados(List<Jurado> jurados) {
		this.jurados = jurados;
	}

	public List<EscolaSamba> getEscolas() {
		return escolas;
	}

	public void setEscolas(List<EscolaSamba> escolas) {
		this.escolas = escolas;
	}

	public List<Quesito> getQuesitos() {
		return quesitos;
	}

	public void setQuesitos(List<Quesito> quesitos) {
		this.quesitos = quesitos;
	}

	public Jurado getJuradoSelecionado() {
		return juradoSelecionado;
	}

	public void setJuradoSelecionado(Jurado juradoSelecionado) {
		this.juradoSelecionado = juradoSelecionado;
	}

	public EscolaSamba getEscolaSelecionada() {
		return escolaSelecionada;
	}

	public void setEscolaSelecionada(EscolaSamba escolaSelecionada) {
		this.escolaSelecionada = escolaSelecionada;
	}

	public Quesito getQuesitoSelecionado() {
		return quesitoSelecionado;
	}

	public void setQuesitoSelecionado(Quesito quesitoSelecionado) {
		this.quesitoSelecionado = quesitoSelecionado;
	}

	public Nota getNota() {
		return nota;
	}

	public void setNota(Nota nota) {
		this.nota = nota;
	}

}
