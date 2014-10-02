package br.net.bomservico.beans;

import java.io.Serializable;
import java.util.Calendar;

public class Servico implements Serializable{
	private static final long serialVersionUID = -2821498550215595157L;
	
	
	int idservico;
	int fk_idusuario, fk_idsituacao = 1;
	String atividade,
			descricao = "Exemplo: Lava, passa, cozinha, arruma. Disponibilidade de segunda a sexta das 8h - 12h e 14h - 18h.",
			site = "Exemplo: pagina do facebook, twitter, etc.";
	Calendar data_cadastro;
	float valor_servico;

	public Servico() {
	}

	public Servico(int fk_idusuario, String atividade, Calendar data_cadastro,
			float valor_servico) {
		this.fk_idusuario = fk_idusuario;
		this.atividade = atividade;
		this.data_cadastro = data_cadastro;
		this.valor_servico = valor_servico;
	}

	public int getIdservico() {
		return idservico;
	}

	public void setIdservico(int idservico) {
		this.idservico = idservico;
	}

	public int getFk_idusuario() {
		return fk_idusuario;
	}

	public void setFk_idusuario(int fk_idusuario) {
		this.fk_idusuario = fk_idusuario;
	}

	public int getFk_idsituacao() {
		return fk_idsituacao;
	}

	public void setFk_idsituacao(int fk_idsituacao) {
		this.fk_idsituacao = fk_idsituacao;
	}

	public String getAtividade() {
		return atividade;
	}

	public void setAtividade(String atividade) {
		this.atividade = atividade;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public Calendar getData_cadastro() {
		return data_cadastro;
	}

	public void setData_cadastro(Calendar data_cadastro) {
		this.data_cadastro = data_cadastro;
	}

	public float getValor_servico() {
		return valor_servico;
	}

	public void setValor_servico(float valor_servico) {
		this.valor_servico = valor_servico;
	}

}
