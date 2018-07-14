package com.vhsolucoes.jpa2.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.vhsolucoes.jpa2.dao.EscolaSambaDAO;
import com.vhsolucoes.jpa2.modelo.EscolaSamba;
import com.vhsolucoes.jpa2.service.NegocioException;
import com.vhsolucoes.jpa2.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaEscolaSambaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	EscolaSambaDAO escolaSambaDAO;

	private List<EscolaSamba> escolaSambas = new ArrayList<>();

	private EscolaSamba escolaSambaSelecionado;

	public List<EscolaSamba> getEscolaSambas() {
		return escolaSambas;
	}

	public void excluir() {
		try {
			escolaSambaDAO.excluir(escolaSambaSelecionado);
			this.escolaSambas.remove(escolaSambaSelecionado);
			FacesUtil.addSuccessMessage(
					"Escola de Samba " + escolaSambaSelecionado.getNome() + " excluída com sucesso.");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public EscolaSamba getEscolaSambaSelecionado() {
		return escolaSambaSelecionado;
	}

	public void setEscolaSambaSelecionado(EscolaSamba escolaSambaSelecionado) {
		this.escolaSambaSelecionado = escolaSambaSelecionado;
	}

	@PostConstruct
	public void inicializar() {
		escolaSambas = escolaSambaDAO.buscarTodos();
	}
}
