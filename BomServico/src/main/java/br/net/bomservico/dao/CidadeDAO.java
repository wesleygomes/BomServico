package br.net.bomservico.dao;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.net.bomservico.conexao.Conexao;
import br.net.bomservico.beans.Cidade;

public class CidadeDAO implements Serializable {
	private static final long serialVersionUID = 4217343493820469972L;

	
	ArrayList<Cidade> arrayCid = null;

	public ArrayList<Cidade> getArrayCid() {
		return arrayCid;
	}

	public void setArrayCid(ArrayList<Cidade> arrayCid) {
		this.arrayCid = arrayCid;
	}

//	public static void main(String args[]) {
//
//		CidadeDao cd = new CidadeDao();
//
//		cd.setArrayCid(cd.buscarPorUf("TO"));
//	}

	@SuppressWarnings("finally")
	public boolean excluir(Cidade cidade) {
		boolean res = false;
		Conexao con = new Conexao();
		String query = "DELETE FROM cidade WHERE id=?";

		con.preparar(query);
		try {
			con.getPstmt().setInt(1, cidade.getId());
			res = con.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, null,
					ex);
		} finally {
			con.fechar();
			return (res);
		}
	}

	@SuppressWarnings("finally")
	public boolean inserir(Cidade cidade) {
		boolean res = false;
		Conexao con = new Conexao();

		String query = "INSERT INTO cidade (idcidade,cidade, fk_uf) "
				+ "VALUES (?, ?, ?)";

		con.transacao();
		con.preparar(query);
		try {
			con.getPstmt().setInt(1, cidade.getId());
			con.getPstmt().setString(2, cidade.getCidade());
			con.getPstmt().setString(3, cidade.getUf());

			res = con.executeUpdate();

		} catch (SQLException ex) {
			Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, null,
					ex);
		} finally {
			con.fechaTransacao(res);
			con.fechar();
			return (res);
		}
	}

	@SuppressWarnings("finally")
	public boolean atualizar(Cidade atual, Cidade novo) {
		boolean res = false;
		Conexao con = new Conexao();

		String query = "UPDATE cidade SET idcidade=?, cidade=?, fk_uf=?"
				+ "WHERE idcidade=?";

		con.transacao();
		con.preparar(query);
		try {
			con.getPstmt().setInt(1, novo.getId());
			con.getPstmt().setString(2, novo.getCidade());
			con.getPstmt().setString(3, novo.getUf());

			res = con.executeUpdate();

		} catch (SQLException ex) {
			Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, null,
					ex);
		} finally {
			con.fechaTransacao(res);
			con.fechar();
			return (res);
		}
	}

	public ArrayList<Cidade> buscar() {
		ArrayList<Cidade> res = new ArrayList<Cidade>();
		Conexao con = new Conexao();
		String query = "SELECT idcidade, cidade,fk_uf FROM cidade order by cidade";

		con.preparar(query);
		try {
			ResultSet rs = con.getPstmt().executeQuery();
			while (rs.next()) {
				Cidade cidade = new Cidade();
				cidade.setId(rs.getInt("idcidade"));
				cidade.setCidade(rs.getString("cidade"));
				cidade.setUf(rs.getString("fk_uf"));

				res.add(cidade);
			}
		} catch (SQLException e) {
			Logger.getLogger(CidadeDAO.class.getName(), null).log(Level.SEVERE,
					null, e);
		} finally {
			con.fechar();
			return (res);
		}
	}

	@SuppressWarnings("finally")
	public ArrayList<Cidade> buscar(String nome) {

		ArrayList<Cidade> res = new ArrayList<Cidade>();
		Conexao con = new Conexao();
		String query = "SELECT idcidade, cidade,fk_uf FROM WHERE cidade";

		con.preparar(query);
		try {
			con.getPstmt().setString(1, "%" + nome + "%");
			ResultSet rs = con.getPstmt().executeQuery();
			while (rs.next()) {
				Cidade cidade = new Cidade();
				cidade.setId(rs.getInt("idcidade"));
				cidade.setCidade(rs.getString("cidade"));
				cidade.setUf(rs.getString("fk_uf"));

				res.add(cidade);
			}
		} catch (SQLException ex) {
			Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, null,
					ex);
		} finally {
			con.fechar();
			return (res);
		}
	}

	@SuppressWarnings("finally")
	public ArrayList<Cidade> buscar(int id) {

		ArrayList<Cidade> res = new ArrayList<Cidade>();
		Conexao con = new Conexao();
		String query = "SELECT idcidade, cidade,fk_uf FROM cidade WHERE idcidade LIKE ?";

		con.preparar(query);
		try {
			con.getPstmt().setInt(1, id);
			ResultSet rs = con.getPstmt().executeQuery();
			while (rs.next()) {
				Cidade cidade = new Cidade();
				cidade.setId(rs.getInt("idcidade"));
				cidade.setCidade(rs.getString("cidade"));
				cidade.setUf(rs.getString("fk_uf"));

				res.add(cidade);
			}
		} catch (SQLException ex) {
			Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, null,
					ex);
		} finally {
			con.fechar();
			return (res);
		}
	}

	@SuppressWarnings("finally")
	public ArrayList<Cidade> buscarPorUf(String uf) {

		ArrayList<Cidade> res = new ArrayList<Cidade>();
		Conexao con = new Conexao();
		String query = "Select idcidade, cidade, fk_uf FROM cidade WHERE fk_uf=? order by cidade";

		con.preparar(query);
		try {
			con.getPstmt().setString(1, uf);
			ResultSet rs = con.getPstmt().executeQuery();
			while (rs.next()) {
				Cidade cidade = new Cidade();
				cidade.setId(rs.getInt("idcidade"));
				cidade.setCidade(rs.getString("cidade"));
				cidade.setUf(rs.getString("fk_uf"));

				res.add(cidade);
			}
		} catch (SQLException ex) {
			Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, null,
					ex);
		} finally {
			con.fechar();
			return (res);
		}
	}

}
