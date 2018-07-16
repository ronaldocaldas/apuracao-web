package com.vhsolucoes.jpa2.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.vhsolucoes.jpa2.dao.JuradoDAO;
import com.vhsolucoes.jpa2.modelo.Jurado;
import com.vhsolucoes.jpa2.service.NegocioException;
import com.vhsolucoes.jpa2.util.jsf.FacesUtil;


@Named
@ViewScoped
public class PesquisaJuradoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	JuradoDAO juradoDAO;

	private List<Jurado> jurados = new ArrayList<>();

	private Jurado juradoSelecionado;

	public List<Jurado> getJurados() {
		return jurados;
	}

	public void excluir(){
		
			try {
				juradoDAO.excluir(juradoSelecionado);
				this.jurados.remove(juradoSelecionado);
				FacesUtil.addSuccessMessage("Jurado " + juradoSelecionado.getNome() + " excluído com sucesso.");
			} catch (NegocioException e) {
				FacesUtil.addErrorMessage(e.getMessage());
			}
			
	}

	public Jurado getJuradoSelecionado() {
		return juradoSelecionado;
	}

	public void setJuradoSelecionado(Jurado juradoSelecionado) {
		this.juradoSelecionado = juradoSelecionado;
	}

	@PostConstruct
	public void inicializar() {
		jurados = juradoDAO.buscarTodos();
	}
}
