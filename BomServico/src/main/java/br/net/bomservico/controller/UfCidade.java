package br.net.bomservico.controller;

import java.io.Serializable;
import br.net.bomservico.beans.Cidade;
import br.net.bomservico.beans.Uf;
import br.net.bomservico.dao.CidadeDAO;
import br.net.bomservico.dao.UfDAO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;


@ManagedBean (name="UfCidadeMB")
@ViewScoped
public class UfCidade implements Serializable {
	private static final long serialVersionUID = 2667752332769319268L;
	
	
	private String estadoSelecionado;
    private String cidadeSelecionada;
    private Uf uf = null;  
    private Cidade cidade;  
    private Map<String,String> estados = new HashMap<String, String>();  
    private Map<String,Map<String,String>> suburbsData = new HashMap<String, Map<String,String>>();  
    private Map<String,String> suburbs = new HashMap<String, String>();
    Map<String,String> cidades; 
    
    UfDAO ufDao;
  
    //Construtor
    public UfCidade() {
         cidades = new HashMap<String, String>(); 
        ufDao = new UfDAO();
        
        ArrayList<Uf> lista = ufDao.buscar();
        // preenche a lista de estados
        for(int i=0; i<lista.size(); i++) 
        {
            estados.put(lista.get(i).getEstado(),lista.get(i).getUf());
        } 
    }    
    
    //Carregar cidades
    public void carregaCidades()
    {
        CidadeDAO cdDao = new CidadeDAO();
        List<Cidade> listCidade =  cdDao.buscarPorUf(estadoSelecionado);
        
        cidades = new HashMap<String, String>(); 
        
        System.out.println(listCidade.size()+" Quantidade de cidades ");
        
        System.out.println("Carregando dados....." + estadoSelecionado);
        
        
        for(int i=0; i<listCidade.size(); i++)
        {
            cidades.put(listCidade.get(i).getCidade(), listCidade.get(i).getId()+"");
        }
    }
    
    
    public void displayLocation() {  
        FacesMessage msg = new FacesMessage("Selected", "City:" + uf + ", Suburb: " + cidade);  
  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }
    
    
    //GETS AND SETS
    public Uf getUf() {
        return uf;
    }

    public void setUf(Uf uf) {
        this.uf = uf;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }


    public Map<String, Map<String, String>> getSuburbsData() {
        return suburbsData;
    }

    public void setSuburbsData(Map<String, Map<String, String>> suburbsData) {
        this.suburbsData = suburbsData;
    }

    public Map<String, String> getSuburbs() {
        return suburbs;
    }

    public void setSuburbs(Map<String, String> suburbs) {
        this.suburbs = suburbs;
    }


    public Map<String, String> getEstados() {
        return estados;
    }

    public void setEstados(Map<String, String> estados) {
        this.estados = estados;
    }

    public String getEstadoSelecionado() {
        return estadoSelecionado;
    }

    public void setEstadoSelecionado(String estadoSelecionado) {
        this.estadoSelecionado = estadoSelecionado;
    }

    public Map<String, String> getCidades() {
        return cidades;
    }

    public void setCidades(Map<String, String> cidades) {
        this.cidades = cidades;
    }

    public String getCidadeSelecionada() {
        return cidadeSelecionada;
    }

    public void setCidadeSelecionada(String cidadeSelecionada) {
        this.cidadeSelecionada = cidadeSelecionada;
    }

    public UfDAO getUfDao() {
        return ufDao;
    }

    public void setUfDao(UfDAO ufDao) {
        this.ufDao = ufDao;
    }

}
