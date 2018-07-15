package com.vhsolucoes.jpa2.wrapper;

public class ApuracaoWrapper {

	private String nomeEscola;
	private String nomeQuesito;
	private int nota;
	private int menorNota;
	private int maiorNota;
	private float media;
	private int totalPorEscola;

	public ApuracaoWrapper(String nomeEscola, String nomeQuesito, int nota, int menorNota, int maiorNota,
			float media, int totalPorEscola) {
		this.nomeEscola = nomeEscola;
		this.nomeQuesito = nomeQuesito;
		this.nota = nota;
		this.menorNota = menorNota;
		this.maiorNota = maiorNota;
		this.media = media;
		this.totalPorEscola = totalPorEscola;
	}

	public String getNomeEscola() {
		return nomeEscola;
	}

	public void setNomeEscola(String nomeEscola) {
		this.nomeEscola = nomeEscola;
	}

	public String getNomeQuesito() {
		return nomeQuesito;
	}

	public void setNomeQuesito(String nomeQuesito) {
		this.nomeQuesito = nomeQuesito;
	}

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}

	public int getMenorNota() {
		return menorNota;
	}

	public void setMenorNota(int menorNota) {
		this.menorNota = menorNota;
	}

	public int getMaiorNota() {
		return maiorNota;
	}

	public void setMaiorNota(int maiorNota) {
		this.maiorNota = maiorNota;
	}

	public float getMedia() {
		return media;
	}

	public void setMedia(float media) {
		this.media = media;
	}

	public int getTotalPorEscola() {
		return totalPorEscola;
	}

	public void setTotalPorEscola(int totalPorEscola) {
		this.totalPorEscola = totalPorEscola;
	}

}
