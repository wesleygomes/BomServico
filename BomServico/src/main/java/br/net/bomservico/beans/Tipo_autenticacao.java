package br.net.bomservico.beans;

import java.io.Serializable;

public class Tipo_autenticacao implements Serializable{
	private static final long serialVersionUID = 5968617019903696014L;
	
	
	int idlogin;
	String descricao;

	public Tipo_autenticacao() {
	}

	public Tipo_autenticacao(String descricao) {
		this.descricao = descricao;
	}

	public int getIdlogin() {
		return idlogin;
	}

	public void setIdlogin(int idlogin) {
		this.idlogin = idlogin;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
