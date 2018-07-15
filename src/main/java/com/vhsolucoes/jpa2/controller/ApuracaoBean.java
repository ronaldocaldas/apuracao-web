package com.vhsolucoes.jpa2.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.vhsolucoes.jpa2.service.ApuracaoService;
import com.vhsolucoes.jpa2.wrapper.ApuracaoWrapper;

@Named
@ViewScoped
public class ApuracaoBean  implements Serializable{

	@Inject
	private ApuracaoService apuracaoService;

	private List<ApuracaoWrapper> listApuracao;

	public List<ApuracaoWrapper> getListApuracao() {
		return listApuracao;
	}


	@PostConstruct
	private void ini() {
		listApuracao = apuracaoService.buscaResultados();
	}

}
