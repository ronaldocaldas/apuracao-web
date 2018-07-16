package com.vhsolucoes.jpa2.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.vhsolucoes.jpa2.dao.QuesitoDAO;
import com.vhsolucoes.jpa2.modelo.Quesito;
import com.vhsolucoes.jpa2.service.NegocioException;
import com.vhsolucoes.jpa2.util.jsf.FacesUtil;


@Named
@ViewScoped
public class PesquisaQuesitoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	QuesitoDAO quesitoDAO;

	private List<Quesito> quesitos = new ArrayList<>();

	private Quesito quesitoSelecionado;
	
	@PostConstruct
	public void inicializar() {
		quesitos = quesitoDAO.buscarTodos();
	}

	public void excluir() {
		try {
			quesitoDAO.excluir(quesitoSelecionado);
			this.quesitos.remove(quesitoSelecionado);
			FacesUtil.addSuccessMessage("Quesito " + quesitoSelecionado.getNome() + " excluído com sucesso.");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public Quesito getQuesitoSelecionado() {
		return quesitoSelecionado;
	}

	public void setQuesitoSelecionado(Quesito quesitoSelecionado) {
		this.quesitoSelecionado = quesitoSelecionado;
	}
	
	public List<Quesito> getQuesitos() {
		return quesitos;
	}
	
}
