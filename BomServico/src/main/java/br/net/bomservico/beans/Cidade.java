package br.net.bomservico.beans;

import java.io.Serializable;

public class Cidade implements Serializable{
	private static final long serialVersionUID = 7004492849063708627L;

	
	private int id;
    private String cidade;
    private String uf;

    public Cidade() {
    }

    public Cidade(String cidade, String uf) {
        this.cidade = cidade;
        this.uf = uf;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

}
