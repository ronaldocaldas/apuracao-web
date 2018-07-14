package com.vhsolucoes.jpa2.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.vhsolucoes.jpa2.modelo.Quesito;
import com.vhsolucoes.jpa2.service.CadastroQuesitoService;
import com.vhsolucoes.jpa2.service.NegocioException;
import com.vhsolucoes.jpa2.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroQuesitoBean implements Serializable {

	@Inject
	private CadastroQuesitoService cadastroQuesitoService;

	private Quesito quesito;

	@PostConstruct
	public void init() {
		this.limpar();

	}

	public void limpar() {
		this.quesito = new Quesito();
	}

	public void salvar() {
		try {
			this.cadastroQuesitoService.salvar(quesito);
			FacesUtil.addSuccessMessage("Quesito salvo com sucesso!");

		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public Quesito getQuesito() {
		return quesito;
	}

	public void setQuesito(Quesito quesito) {
		this.quesito = quesito;
	}

	public CadastroQuesitoService getCadastroQuesitoService() {
		return cadastroQuesitoService;
	}

	public void setCadastroQuesitoService(CadastroQuesitoService cadastroQuesitoService) {
		this.cadastroQuesitoService = cadastroQuesitoService;
	}

}
