package com.vhsolucoes.jpa2.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.vhsolucoes.jpa2.modelo.Jurado;
import com.vhsolucoes.jpa2.service.CadastroJuradoService;
import com.vhsolucoes.jpa2.service.NegocioException;
import com.vhsolucoes.jpa2.util.jsf.FacesUtil;


@Named
@ViewScoped
public class CadastroJuradoBean implements Serializable {

	@Inject
	private CadastroJuradoService cadastroJuradoService;

	private Jurado jurado;

	@PostConstruct
	public void init() {
		this.limpar();

	}

	public void limpar() {
		this.jurado = new Jurado();
	}
	
	public void salvar(){
		try {
			this.cadastroJuradoService.salvar(jurado);
			FacesUtil.addSuccessMessage("Jurado salvo com sucesso!");

		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	

	public Jurado getJurado() {
		return jurado;
	}

	public void setJurado(Jurado jurado) {
		this.jurado = jurado;
	}

	public CadastroJuradoService getCadastroJuradoService() {
		return cadastroJuradoService;
	}

	public void setCadastroJuradoService(CadastroJuradoService cadastroJuradoService) {
		this.cadastroJuradoService = cadastroJuradoService;
	}

}
