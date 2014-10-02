package br.net.bomservico.dao;

import br.net.bomservico.beans.Tipo_autenticacao;
import br.net.bomservico.conexao.Conexao;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Tipo_autenticacaoDAO implements Serializable{
	private static final long serialVersionUID = 8867672006250351718L;

	
	public boolean excluir(Tipo_autenticacao idlogin)
	  {
	    boolean res = false;
	    Conexao con = new Conexao();
	    String query = "DELETE FROM tipo_autenticacao  WHERE idlogin=?";
	    
	    con.preparar(query);
	    try
	    {
	      con.getPstmt().setInt(1, idlogin.getIdlogin());
	      res = con.executeUpdate();
	    } catch (SQLException ex)
	    {
	      Logger.getLogger(Tipo_autenticacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
	    }
	    finally
	    {
	      con.fechar();
	      return(res);
	    }
	  }
	  
	  
	public ArrayList<Tipo_autenticacao> buscar()
	  {
	    ArrayList<Tipo_autenticacao> res = new ArrayList<Tipo_autenticacao>();
	    Conexao con = new Conexao();
	    String query = "SELECT idlogin, descricao FROM tipo_autenticacao ";
	    
	    con.preparar(query);
	    try
	    {
	      ResultSet rs = con.getPstmt().executeQuery();
	      while (rs.next())
	      {
	        Tipo_autenticacao login = new Tipo_autenticacao();
	        login.setIdlogin(rs.getInt("idlogin"));
	        login.setDescricao(rs.getString("descricao"));
	        res.add(login);
	      }
	    } catch (SQLException ex)
	    {
	      Logger.getLogger(Tipo_autenticacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
	    }
	    finally
	    {
	      con.fechar();
	      return(res);
	    }
	  }
	  
	  @SuppressWarnings("finally")
	public Tipo_autenticacao buscar(int id)
	  {
		  
	    Tipo_autenticacao res = null;
	    Conexao con = new Conexao();
	    String query = "SELECT idlogin, descricao FROM tipo_autenticacao WHERE idlogin=?";
	    
	    con.preparar(query);
	    try
	    {
	      con.getPstmt().setInt(1, id);
	      ResultSet rs = con.getPstmt().executeQuery();
	      if (rs.next())
	      {
	        res = new Tipo_autenticacao();
	        res.setIdlogin(rs.getInt("idlogin"));
	        res.setDescricao(rs.getString("descricao"));
	      }
	    } catch (SQLException ex)
	    {
	      Logger.getLogger(Tipo_autenticacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
	    }
	    finally
	    {
	      con.fechar();
	      return(res);
	    }
	  }
	  @SuppressWarnings("finally")
	public Tipo_autenticacao buscarPorDescricao(String descricao)
	  {
	    Tipo_autenticacao res = null;
	    Conexao con = new Conexao();
	    String query = "SELECT idlogin, descricao FROM tipo_autenticacao WHERE idlogin=?";
	    
	    con.preparar(query);
	    try
	    {
	      con.getPstmt().setString(1, "%"+descricao+"%");
	      ResultSet rs = con.getPstmt().executeQuery();
	      if (rs.next())
	      {
	        res = new Tipo_autenticacao();
	        res.setIdlogin(rs.getInt("idlogin"));
	        res.setDescricao(rs.getString("descricao"));
	      }
	    } catch (SQLException ex)
	    {
	      Logger.getLogger(Tipo_autenticacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
	    }
	    finally
	    {
	      con.fechar();
	      return(res);
	    }
	  }

}
