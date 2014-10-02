package br.net.bomservico.controller;

import java.io.Serializable;
import br.net.bomservico.beans.Usuario;
import br.net.bomservico.dao.UsuarioDAO;
import java.io.IOException;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "cadastroMB")
@SessionScoped
public class Cadastro implements Serializable {
	private static final long serialVersionUID = 4625706344045051843L;

	String confirmaSenha;
	String estadoSelecionado, cidadeSelecionada;

	ArrayList<Usuario> arrayUsuario = null;

	Usuario usuario = null;
	UsuarioDAO UsuarioDAO = null;

	// Construtor
	public Cadastro() {

		usuario = new Usuario();
		UsuarioDAO = new UsuarioDAO();

	}

	// Cadastro de Clientes
	public String cadastroCliente() throws IOException {

		boolean retorna = false;
		if (confirmaSenha.equals(usuario.getSenha())) {
			System.out.println("Cadatro Cliente");
			UsuarioDAO.inserir(usuario);
			retorna = true;
		} else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, null,
							"Senha Incorreta"));
			retorna = false;
		}

		if (retorna == false) {
			return "";
		} else {
			usuario.setNome("");
			usuario.setSobrenome("");
			usuario.setLogin("");
			usuario.setSenha("");

			return "cadastro_cliente_sucesso.xhtml";
		}
	}

	// GETS AND SETS

	public String getConfirmaSenha() {
		return confirmaSenha;
	}

	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
	}

	public String getEstadoSelecionado() {
		return estadoSelecionado;
	}

	public void setEstadoSelecionado(String estadoSelecionado) {
		this.estadoSelecionado = estadoSelecionado;
	}

	public String getCidadeSelecionada() {
		return cidadeSelecionada;
	}

	public void setCidadeSelecionada(String cidadeSelecionada) {
		this.cidadeSelecionada = cidadeSelecionada;
	}

	public ArrayList<Usuario> getArrayUsuario() {
		return arrayUsuario;
	}

	public void setArrayUsuario(ArrayList<Usuario> arrayUsuario) {
		this.arrayUsuario = arrayUsuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public UsuarioDAO getUsuarioDAO() {
		return UsuarioDAO;
	}

	public void setUsuarioDAO(UsuarioDAO UsuarioDAO) {
		this.UsuarioDAO = UsuarioDAO;
	}

}
