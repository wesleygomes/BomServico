package br.net.bomservico.beans;

import java.io.Serializable;

public class Uf implements Serializable {

	private static final long serialVersionUID = -1685361188737250571L;

	private String uf;
	private String estado;

	public Uf() {

	}

	public Uf(String uf, String nome) {
		this.uf = uf;
		this.estado = estado;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
