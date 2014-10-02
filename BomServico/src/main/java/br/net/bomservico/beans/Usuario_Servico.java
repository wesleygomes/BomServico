package br.net.bomservico.beans;

import java.util.Calendar;

public class Usuario_Servico {

	int idusuario;
	String nome, sobrenome, email, sexo, login, senha, foto;
	int cpf, celular, telefone_fixo;
	Calendar usuario_data_cadastro;

	int idservico;
	int fk_idusuario, fk_idsituacao = 1;
	String atividade,
			descricao = "Exemplo: Lava, passa, cozinha, arruma. Disponibilidade de segunda a sexta das 8h - 12h e 14h - 18h.",
			site = "Exemplo: pagina do facebook, twitter, etc.", expediente;
	Calendar servico_data_cadastro;
	float valor_servico;

	public Usuario_Servico() {
	}

	public Usuario_Servico(String nome, String sobrenome, String email,
			String sexo, String login, String senha, String foto, int cpf,
			int celular, int telefone_fixo, Calendar usuario_data_cadastro,
			int fk_idusuario, String atividade, String expediente,
			Calendar servico_data_cadastro, float valor_servico) {
		
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.email = email;
		this.sexo = sexo;
		this.login = login;
		this.senha = senha;
		this.foto = foto;
		this.cpf = cpf;
		this.celular = celular;
		this.telefone_fixo = telefone_fixo;
		this.usuario_data_cadastro = usuario_data_cadastro;
		this.fk_idusuario = fk_idusuario;
		this.atividade = atividade;
		this.expediente = expediente;
		this.servico_data_cadastro = servico_data_cadastro;
		this.valor_servico = valor_servico;
	}

	public int getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(int idusuario) {
		this.idusuario = idusuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public int getCpf() {
		return cpf;
	}

	public void setCpf(int cpf) {
		this.cpf = cpf;
	}

	public int getCelular() {
		return celular;
	}

	public void setCelular(int celular) {
		this.celular = celular;
	}

	public int getTelefone_fixo() {
		return telefone_fixo;
	}

	public void setTelefone_fixo(int telefone_fixo) {
		this.telefone_fixo = telefone_fixo;
	}

	public Calendar getUsuario_data_cadastro() {
		return usuario_data_cadastro;
	}

	public void setUsuario_data_cadastro(Calendar usuario_data_cadastro) {
		this.usuario_data_cadastro = usuario_data_cadastro;
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

	public String getExpediente() {
		return expediente;
	}

	public void setExpediente(String expediente) {
		this.expediente = expediente;
	}

	public Calendar getServico_data_cadastro() {
		return servico_data_cadastro;
	}

	public void setServico_data_cadastro(Calendar servico_data_cadastro) {
		this.servico_data_cadastro = servico_data_cadastro;
	}

	public float getValor_servico() {
		return valor_servico;
	}

	public void setValor_servico(float valor_servico) {
		this.valor_servico = valor_servico;
	}

}
