package br.net.bomservico.controller;

import java.io.Serializable;
import java.util.ArrayList;
import br.net.bomservico.beans.Servico;
import br.net.bomservico.beans.Usuario_Servico;
import br.net.bomservico.dao.ServicoDAO;
import br.net.bomservico.dao.UsuarioDAO;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean (name="pesquisarMB")
@SessionScoped
public class Pesquisar implements Serializable {
	private static final long serialVersionUID = -3858592367056338330L;
	
	
	String atividade;
    int idCidadeSelecionada;
    
    ArrayList<Usuario_Servico> arrayPesquisa=null;
    
    Usuario_Servico pesquisa = null;
    UsuarioDAO usuarioDao = null;
    
    ArrayList<Servico> arrayServico = null;
    Servico servico = null;
    ServicoDAO servicoDao = null;

    //Construtor
    public Pesquisar() {
        pesquisa = new Usuario_Servico();
        usuarioDao= new UsuarioDAO();
        
        servico = new Servico();
        servicoDao = new ServicoDAO();
        
    }
    
    //Pesquisar Serviço
    public String pesquisar(){
        
        setArrayPesquisa(servicoDao.buscarServico(atividade, idCidadeSelecionada));
      
        return "resultadoPesquisa.xhtml";
        
    }
    
    //Pesquisar Login
    public String pesquisarLogin(){
        
        setArrayPesquisa(usuarioDao.buscarProfissao(atividade));
      
        return "resultadoPesquisa_login.xhtml";
        
    }
    
    //GETS AND SETS
    public String getAtividade() {
        return atividade;
    }

    public void setAtividade(String atividade) {
        this.atividade = atividade;
    }

    public ArrayList<Usuario_Servico> getArrayPesquisa() {
        return arrayPesquisa;
    }

    public void setArrayPesquisa(ArrayList<Usuario_Servico> arrayPesquisa) {
        this.arrayPesquisa = arrayPesquisa;
    }

    public Usuario_Servico getPesquisa() {
        return pesquisa;
    }

    public void setPesquisa(Usuario_Servico pesquisa) {
        this.pesquisa = pesquisa;
    }

    public UsuarioDAO getUsuarioDao() {
        return usuarioDao;
    }

    public void setUsuarioDao(UsuarioDAO usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public ServicoDAO getServicoDao() {
        return servicoDao;
    }

    public void setServicoDao(ServicoDAO servicoDao) {
        this.servicoDao = servicoDao;
    }

    public int getIdCidadeSelecionada() {
        return idCidadeSelecionada;
    }

    public void setIdCidadeSelecionada(int idCidadeSelecionada) {
        this.idCidadeSelecionada = idCidadeSelecionada;
    }

    public ArrayList<Servico> getArrayServico() {
        return arrayServico;
    }

    public void setArrayServico(ArrayList<Servico> arrayServico) {
        this.arrayServico = arrayServico;
    }
}
