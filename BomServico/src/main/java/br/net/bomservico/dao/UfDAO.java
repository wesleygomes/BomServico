package br.net.bomservico.dao;

import java.io.Serializable;
import br.net.bomservico.beans.Uf;
import br.net.bomservico.conexao.Conexao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UfDAO implements Serializable {
	private static final long serialVersionUID = -9202236509498684799L;
	
	public boolean excluir(Uf id_uf)
	  {
	    boolean res = false;
	    Conexao con = new Conexao();
	    String query = "DELETE FROM uf  WHERE uf=?";
	    
	    con.preparar(query);
	    try
	    {
	      con.getPstmt().setString(1, id_uf.getUf());
	      res = con.executeUpdate();
	    } catch (SQLException ex)
	    {
	      Logger.getLogger(UfDAO.class.getName()).log(Level.SEVERE, null, ex);
	    }
	    finally
	    {
	      con.fechar();
	      return(res);
	    }
	  }
	  
	public boolean atualizar(Uf ufatual, Uf ufnova)
	  {
	    boolean res = false;
	    Conexao con = new Conexao();
	    String query = "UPDATE uf SET uf=?, estado=? WHERE uf=?";
	    
	    con.preparar(query);
	    try
	    {
	      con.getPstmt().setString(1, ufnova.getUf());
	      con.getPstmt().setString(2, ufnova.getEstado());
	      con.getPstmt().setString(3, ufatual.getUf());
	      con.getPstmt().setString(4, ufatual.getEstado());
	      res = con.executeUpdate();
	    } catch (SQLException ex)
	    {
	      Logger.getLogger(UfDAO.class.getName()).log(Level.SEVERE, null, ex);
	    }
	    finally
	    {
	      con.fechar();
	      return(res);
	    }
	  }
	  
	public ArrayList<Uf> buscar()
	  {
	    ArrayList<Uf> res = new ArrayList<Uf>();
	    Conexao con = new Conexao();
	    String query = "SELECT uf, estado FROM uf order by estado";
	    
	    con.preparar(query);
	    try
	    {
	      ResultSet rs = con.getPstmt().executeQuery();
	      while (rs.next())
	      {
	        Uf id_uf = new Uf();
	        id_uf.setUf(rs.getString("uf"));
	        id_uf.setEstado(rs.getString("estado"));
	        res.add(id_uf);
	      }
	    } catch (SQLException ex)
	    {
	      Logger.getLogger(UfDAO.class.getName()).log(Level.SEVERE, null, ex);
	    }
	    finally
	    {
	      con.fechar();
	      return(res);
	    }
	  }
	  
	 public ArrayList<Uf> buscar(String uf)
	  {
	    ArrayList<Uf> res = new ArrayList<Uf>();
	    Conexao con = new Conexao();
	    String query = "SELECT uf, estado FROM uf WHERE uf=?";
	    
	    con.preparar(query);
	    try
	    {
	      con.getPstmt().setString(1, uf);
	      ResultSet rs = con.getPstmt().executeQuery();
	      if (rs.next())
	      {
	        Uf id_uf = new Uf();
	        id_uf.setUf(rs.getString("uf"));
	        id_uf.setEstado(rs.getString("estado"));
	        res.add(id_uf);
	      }
	    } catch (SQLException ex)
	    {
	      Logger.getLogger(UfDAO.class.getName()).log(Level.SEVERE, null, ex);
	    }
	    finally
	    {
	      con.fechar();
	      return(res);
	    }
	  }
	  @SuppressWarnings("finally")
	public Uf buscarPorEstado(String estado)
	  {
	    Uf res = null;
	    Conexao con = new Conexao();
	    String query = "SELECT uf, estado FROM uf WHERE uf=?";
	    
	    con.preparar(query);
	    try
	    {
	      con.getPstmt().setString(1, "%"+estado+"%");
	      ResultSet rs = con.getPstmt().executeQuery();
	      if (rs.next())
	      {
	        res = new Uf();
	        res.setUf(rs.getString("uf"));
	        res.setEstado(rs.getString("estado"));
	      }
	    } catch (SQLException ex)
	    {
	      Logger.getLogger(UfDAO.class.getName()).log(Level.SEVERE, null, ex);
	    }
	    finally
	    {
	      con.fechar();
	      return(res);
	    }
	  }
	  /*
	  public static void main (String args[]){
		  Uf est = new Uf();
		  est.setId_uf("Ti");
		  est.setEstado("Tocantins");
		  
		  UfDAO e = new UfDAO();
		  e.inserir(est);
		  
	  }
	  */

}
