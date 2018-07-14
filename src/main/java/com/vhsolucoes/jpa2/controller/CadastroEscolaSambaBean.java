package com.vhsolucoes.jpa2.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.vhsolucoes.jpa2.modelo.EscolaSamba;
import com.vhsolucoes.jpa2.service.CadastroEscolaSambaService;
import com.vhsolucoes.jpa2.service.NegocioException;
import com.vhsolucoes.jpa2.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroEscolaSambaBean implements Serializable {

	@Inject
	private CadastroEscolaSambaService cadastroEscolaSambaService;

	private EscolaSamba escolaSamba;

	@PostConstruct
	public void init() {
		this.limpar();

	}

	public void limpar() {
		this.escolaSamba = new EscolaSamba();
	}

	public void salvar() {
		try {
			this.cadastroEscolaSambaService.salvar(escolaSamba);
			FacesUtil.addSuccessMessage("Escola de saamba salva com sucesso!");

		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public EscolaSamba getEscolaSamba() {
		return escolaSamba;
	}

	public void setEscolaSamba(EscolaSamba escolaSamba) {
		this.escolaSamba = escolaSamba;
	}

	public CadastroEscolaSambaService getCadastroEscolaSambaService() {
		return cadastroEscolaSambaService;
	}

	public void setCadastroEscolaSambaService(CadastroEscolaSambaService cadastroEscolaSambaService) {
		this.cadastroEscolaSambaService = cadastroEscolaSambaService;
	}

}
