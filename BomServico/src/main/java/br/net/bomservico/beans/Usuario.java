package br.net.bomservico.beans;

import java.io.Serializable;
import java.util.Calendar;

public class Usuario implements Serializable{

	private static final long serialVersionUID = 4274087544556154310L;

	
	int idusuario;
	String nome, sobrenome, email, sexo, login, senha, foto;
	int cpf, celular, telefone_fixo;
	Calendar data_cadastro;

	public Usuario() {
	}

	public Usuario(String nome, String sobrenome, String email, String sexo,
			String login, String senha, String foto, int cpf, int celular,
			int telefone_fixo, Calendar data_cadastro) {
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
		this.data_cadastro = data_cadastro;
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

	public Calendar getData_cadastro() {
		return data_cadastro;
	}

	public void setData_cadastro(Calendar data_cadastro) {
		this.data_cadastro = data_cadastro;
	}

}
