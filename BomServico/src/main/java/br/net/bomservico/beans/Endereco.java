package br.net.bomservico.beans;

import java.io.Serializable;

public class Endereco implements Serializable {

	private static final long serialVersionUID = -3955822765405270981L;

	
	int idendereco; 
    int fk_idusuario, fk_idcidade;
    String cep,quadra, rua, numero, complemento, setor;

    public Endereco() {
    }

    public Endereco(int fk_idusuario, int fk_idcidade, String cep, String quadra, String rua, String numero, String complemento, String setor) {
        this.fk_idusuario = fk_idusuario;
        this.fk_idcidade = fk_idcidade;
        this.cep = cep;
        this.quadra = quadra;
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
        this.setor = setor;
    }

    public int getIdendereco() {
        return idendereco;
    }

    public void setIdendereco(int idendereco) {
        this.idendereco = idendereco;
    }

    public int getFk_idusuario() {
        return fk_idusuario;
    }

    public void setFk_idusuario(int fk_idusuario) {
        this.fk_idusuario = fk_idusuario;
    }

    public int getFk_idcidade() {
        return fk_idcidade;
    }

    public void setFk_idcidade(int fk_idcidade) {
        this.fk_idcidade = fk_idcidade;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getQuadra() {
        return quadra;
    }

    public void setQuadra(String quadra) {
        this.quadra = quadra;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

}
