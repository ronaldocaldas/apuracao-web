package com.vhsolucoes.jpa2.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
public class Nota {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private int nota;

	@ManyToOne
	@JoinColumn(name = "escola_samba_id", nullable=false)
	private EscolaSamba escola;

	@ManyToOne
	@JoinColumn(name = "quesito_id", nullable=false)
	private Quesito quesito;

	@ManyToOne
	@JoinColumn(name = "jurado_id", nullable=false)
	private Jurado jurado;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}

	public EscolaSamba getEscola() {
		return escola;
	}

	public void setEscola(EscolaSamba escola) {
		this.escola = escola;
	}

	public Quesito getQuesito() {
		return quesito;
	}

	public void setQuesito(Quesito quesito) {
		this.quesito = quesito;
	}

	public Jurado getJurado() {
		return jurado;
	}

	public void setJurado(Jurado jurado) {
		this.jurado = jurado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Nota other = (Nota) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
