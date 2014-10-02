package br.net.bomservico.dao;

import br.net.bomservico.beans.Endereco;
import br.net.bomservico.conexao.Conexao;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EnderecoDAO implements Serializable{
	private static final long serialVersionUID = -3881468282880056313L;

	public boolean excluir(Endereco endereco) {
	        boolean res = false;
	        Conexao con = new Conexao();
	        String query = "DELETE FROM endereco WHERE idendereco=?";

	        con.transacao();
	        con.preparar(query);
	        try {
	            con.getPstmt().setInt(1, endereco.getIdendereco());
	            res = con.executeUpdate();


	        } catch (SQLException ex) {
	            Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
	        } finally {
	            con.fechaTransacao(res);
	            con.fechar();
	            return (res);
	        }
	    }

	    @SuppressWarnings("finally")
	    public boolean inserir(Endereco endereco) {
	        boolean res = false;
	        Conexao con = new Conexao();
	        String query = "INSERT INTO endereco (cep, quadra, rua, numero, complemento, setor, fk_idusuario, fk_idcidade) "
	                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	        con.transacao();
	        con.preparar(query);
	        try {
	            con.getPstmt().setString(1, endereco.getCep());
	            con.getPstmt().setString(2, endereco.getQuadra());
	            con.getPstmt().setString(3, endereco.getRua());
	            con.getPstmt().setString(4, endereco.getNumero());
	            con.getPstmt().setString(5, endereco.getComplemento());
	            con.getPstmt().setString(6, endereco.getSetor());
	            con.getPstmt().setInt(7, endereco.getFk_idusuario());
	            con.getPstmt().setInt(8, endereco.getFk_idcidade());

	            res = con.executeUpdate();


	        } catch (SQLException ex) {
	            Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
	        } finally {
	            con.fechaTransacao(res);
	            con.fechar();
	            return (res);
	        }
	    }

	    @SuppressWarnings("finally")
	    public boolean atualizar(Endereco atual, Endereco novo) {
	        boolean res = false;
	        Conexao con = new Conexao();

	        String query = "UPDATE endereco SET cep=?, quadra=?, rua=?, numero=?, complemento=?, setor=?, fk_idusuario=?, fk_idcidade=? "
	                + "WHERE idendereco=?";

	        con.transacao();
	        con.preparar(query);
	        try {
	            con.getPstmt().setString(1, novo.getCep());
	            con.getPstmt().setString(2, novo.getQuadra());
	            con.getPstmt().setString(3, novo.getRua());
	            con.getPstmt().setString(4, novo.getNumero());
	            con.getPstmt().setString(5, novo.getComplemento());
	            con.getPstmt().setString(6, novo.getSetor());
	            con.getPstmt().setInt(7, novo.getFk_idusuario());
	            con.getPstmt().setInt(8, novo.getFk_idcidade());
	            con.getPstmt().setInt(9, novo.getIdendereco());

	            res = con.executeUpdate();

	        } catch (SQLException ex) {
	            Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
	        } finally {
	            con.fechaTransacao(res);
	            con.fechar();
	            return (res);
	        }
	    }

	    public ArrayList<Endereco> buscar() {
	        ArrayList<Endereco> res = new ArrayList<Endereco>();
	        Conexao con = new Conexao();
	        String query = "SELECT idendereco, cep, quadra, rua, numero, complemento, setor, fk_idusuario, fk_idcidade"
	                + "FROM endereco WHERE quadra LIKE";

	        con.preparar(query);
	        try {
	            ResultSet rs = con.getPstmt().executeQuery();
	            while (rs.next()) {
	                Endereco endereco = new Endereco();
	                endereco.setIdendereco(rs.getInt("idendereco"));
	                endereco.setCep(rs.getString("cep"));
	                endereco.setQuadra(rs.getString("quadra"));
	                endereco.setRua(rs.getString("rua"));
	                endereco.setNumero(rs.getString("numero"));
	                endereco.setComplemento(rs.getString("complemento"));
	                endereco.setSetor(rs.getString("setor"));
	                endereco.setFk_idusuario(rs.getInt("fk_idusuario"));
	                endereco.setFk_idcidade(rs.getInt("fk_idcidade"));
	                res.add(endereco);
	            }
	        } catch (SQLException e) {
	            Logger.getLogger(EnderecoDAO.class.getName(), null).log(Level.SEVERE, null, e);
	        } finally {
	            con.fechar();
	            return (res);
	        }
	    }

	    @SuppressWarnings("finally")
	    public ArrayList<Endereco> buscar(String cep) {

	        ArrayList<Endereco> res = new ArrayList<Endereco>();
	        Conexao con = new Conexao();
	        String query = "SELECT idendereco, cep, quadra, rua, numero, complemento, setor, fk_idusuario, fk_idcidade"
	                + "FROM endereco WHERE cep LIKE ?";

	        con.preparar(query);
	        try {
	            con.getPstmt().setString(1, "%" + cep + "%");
	            ResultSet rs = con.getPstmt().executeQuery();
	            while (rs.next()) {
	                Endereco endereco = new Endereco();
	                endereco.setIdendereco(rs.getInt("idendereco"));
	                endereco.setCep(rs.getString("cep"));
	                endereco.setQuadra(rs.getString("quadra"));
	                endereco.setRua(rs.getString("rua"));
	                endereco.setNumero(rs.getString("numero"));
	                endereco.setComplemento(rs.getString("complemento"));
	                endereco.setSetor(rs.getString("setor"));
	                endereco.setFk_idusuario(rs.getInt("fk_idusuario"));
	                endereco.setFk_idcidade(rs.getInt("fk_idcidade"));

	                res.add(endereco);
	            }
	        } catch (SQLException ex) {
	            Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
	        } finally {
	            con.fechar();
	            return (res);
	        }
	    }

	    @SuppressWarnings("finally")
	    public ArrayList<Endereco> buscarQuadra(String quadra) {

	        ArrayList<Endereco> res = new ArrayList<Endereco>();
	        Conexao con = new Conexao();
	        String query = "SELECT idendereco, cep, quadra, rua, numero, complemento, setor, fk_idusuario, fk_idcidade"
	                + "FROM endereco WHERE quadra LIKE ?";

	        con.preparar(query);
	        try {
	            con.getPstmt().setString(1, quadra);
	            ResultSet rs = con.getPstmt().executeQuery();
	            while (rs.next()) {
	                Endereco endereco = new Endereco();
	                endereco.setIdendereco(rs.getInt("idendereco"));
	                endereco.setCep(rs.getString("cep"));
	                endereco.setQuadra(rs.getString("quadra"));
	                endereco.setRua(rs.getString("rua"));
	                endereco.setNumero(rs.getString("numero"));
	                endereco.setComplemento(rs.getString("complemento"));
	                endereco.setSetor(rs.getString("setor"));
	                endereco.setFk_idusuario(rs.getInt("fk_idusuario"));
	                endereco.setFk_idcidade(rs.getInt("fk_idcidade"));

	                res.add(endereco);
	            }
	        } catch (SQLException ex) {
	            Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
	        } finally {
	            con.fechar();
	            return (res);
	        }
	    }

	    @SuppressWarnings("finally")
	    public ArrayList<Endereco> buscarIdusuario(int idusuario) {

	        ArrayList<Endereco> res = new ArrayList<Endereco>();
	        Conexao con = new Conexao();
	        String query = "SELECT * FROM endereco WHERE fk_idusuario LIKE ?";

	        con.preparar(query);
	        try {
	            con.getPstmt().setInt(1, idusuario);
	            ResultSet rs = con.getPstmt().executeQuery();
	            while (rs.next()) {
	                Endereco endereco = new Endereco();
	                endereco.setIdendereco(rs.getInt("idendereco"));
	                endereco.setCep(rs.getString("cep"));
	                endereco.setQuadra(rs.getString("quadra"));
	                endereco.setRua(rs.getString("rua"));
	                endereco.setNumero(rs.getString("numero"));
	                endereco.setComplemento(rs.getString("complemento"));
	                endereco.setSetor(rs.getString("setor"));
	                endereco.setFk_idusuario(rs.getInt("fk_idusuario"));
	                endereco.setFk_idcidade(rs.getInt("fk_idcidade"));

	                res.add(endereco);
	            }
	        } catch (SQLException ex) {
	            Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
	        } finally {
	            con.fechar();
	            return (res);
	        }
	    }

	    public ArrayList<Endereco> buscar(int id) {

	        ArrayList<Endereco> res = new ArrayList<Endereco>();
	        Conexao con = new Conexao();
	        String query = "SELECT  idendereco, cep, quadra, rua, numero, complemento, setor, fk_idusuario, fk_idcidade"
	                + "FROM endereco WHERE idendereco LIKE ?";

	        con.preparar(query);
	        try {
	            con.getPstmt().setInt(1, id);
	            ResultSet rs = con.getPstmt().executeQuery();
	            while (rs.next()) {
	                Endereco endereco = new Endereco();
	                endereco.setIdendereco(rs.getInt("idendereco"));
	                endereco.setCep(rs.getString("cep"));
	                endereco.setQuadra(rs.getString("quadra"));
	                endereco.setRua(rs.getString("rua"));
	                endereco.setNumero(rs.getString("numero"));
	                endereco.setComplemento(rs.getString("complemento"));
	                endereco.setSetor(rs.getString("setor"));
	                endereco.setFk_idusuario(rs.getInt("fk_idusuario"));
	                endereco.setFk_idcidade(rs.getInt("fk_idcidade"));
	                res.add(endereco);
	            }
	        } catch (SQLException ex) {
	            Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
	        } finally {
	            con.fechar();
	            return (res);
	        }
	    }

}
