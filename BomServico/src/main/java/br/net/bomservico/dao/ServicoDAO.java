package br.net.bomservico.dao;

import java.io.Serializable;
import br.net.bomservico.beans.Servico;
import br.net.bomservico.beans.Usuario_Servico;
import br.net.bomservico.conexao.Conexao;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ServicoDAO implements Serializable {
	private static final long serialVersionUID = -3081097756543620649L;

	public boolean excluir(Servico prestrador) {
        boolean res = false;
        Conexao con = new Conexao();
        String query = "DELETE FROM servico WHERE idservico=?";

        con.transacao();
        con.preparar(query);
        try {
            con.getPstmt().setInt(1, prestrador.getIdservico());
            res = con.executeUpdate();


        } catch (SQLException ex) {
            Logger.getLogger(ServicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.fechaTransacao(res);
            con.fechar();
            return (res);
        }
    }

    @SuppressWarnings("finally")
    public boolean inserir(Servico servico) {
        boolean res = false;
        Conexao con = new Conexao();
        String query = "INSERT INTO servico (atividade, descricao, site, valor_servico, fk_idusuario, fk_idsituacao) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        con.transacao();
        con.preparar(query);
        try {
            con.getPstmt().setString(1, servico.getAtividade());
            con.getPstmt().setString(2, servico.getDescricao());
            con.getPstmt().setString(3, servico.getSite());
            con.getPstmt().setFloat(4, servico.getValor_servico());
            con.getPstmt().setInt(5, servico.getFk_idusuario());
            con.getPstmt().setInt(6, servico.getFk_idsituacao());

            res = con.executeUpdate();


        } catch (SQLException ex) {
            Logger.getLogger(ServicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.fechaTransacao(res);
            con.fechar();
            return (res);
        }
    }

    @SuppressWarnings("finally")
    public boolean atualizar(Servico atual, Servico nova) {
        boolean res = false;
        Conexao con = new Conexao();

        String query = "UPDATE servico SET atividade=?, descricao=?, site=?, valor_servico=?, fk_idusuario=?, fk_idsituacao=? WHERE idservico=?";

        con.transacao();
        con.preparar(query);
        try {
            con.getPstmt().setString(1, nova.getAtividade());
            con.getPstmt().setString(2, nova.getDescricao());
            con.getPstmt().setString(3, nova.getSite());
            con.getPstmt().setFloat(4, nova.getValor_servico());
            con.getPstmt().setInt(5, atual.getFk_idusuario());
            con.getPstmt().setInt(6, atual.getFk_idsituacao());
            con.getPstmt().setInt(7, atual.getIdservico());
            res = con.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ServicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.fechaTransacao(res);
            con.fechar();
            return (res);
        }
    }

    public ArrayList<Servico> buscar() {
        ArrayList<Servico> res = new ArrayList<Servico>();
        Conexao con = new Conexao();
        String query = "SELECT idservico, atividade, descricao, site, data_cadastro, valor_servico, fk_idusuario, fk_idsituacao "
                + "FROM servico order by atividade ";

        con.preparar(query);
        try {
            ResultSet rs = con.getPstmt().executeQuery();
            while (rs.next()) {
                Servico prestador = new Servico();
                prestador.setIdservico(rs.getInt("idservico"));
                prestador.setAtividade(rs.getString("atividade"));
                prestador.setDescricao(rs.getString("descricao"));
                prestador.setSite(rs.getString("site"));
                Date t = rs.getDate("data_cadastro");
                Calendar cal = Calendar.getInstance();
                cal.setTime(t);
                prestador.setData_cadastro(cal);
                prestador.setValor_servico(rs.getFloat("valor_servico"));
                prestador.setFk_idusuario(rs.getInt("fk_idusuario"));
                prestador.setFk_idsituacao(rs.getInt("fk_idsituacao"));


                res.add(prestador);
            }
        } catch (SQLException e) {
            Logger.getLogger(ServicoDAO.class.getName(), null).log(Level.SEVERE, null, e);
        } finally {
            con.fechar();
            return (res);
        }
    }

    @SuppressWarnings("finally")
    public ArrayList<Servico> buscar(String atividade) {

        ArrayList<Servico> res = new ArrayList<Servico>();
        Conexao con = new Conexao();
        String query = "SELECT idservico, atividade, descricao, site, data_cadastro, valor_servico, fk_idusuario, fk_idsituacao "
                + "FROM servico WHERE atividade LIKE ?";

        con.preparar(query);
        try {
            con.getPstmt().setString(1, "%" + atividade + "%");
            ResultSet rs = con.getPstmt().executeQuery();
            while (rs.next()) {
                Servico prestador = new Servico();
                prestador.setIdservico(rs.getInt("idservico"));
                prestador.setAtividade(rs.getString("atividade"));
                prestador.setDescricao(rs.getString("descricao"));
                prestador.setSite(rs.getString("site"));
                Date t = rs.getDate("data_cadastro");
                Calendar cal = Calendar.getInstance();
                cal.setTime(t);
                prestador.setData_cadastro(cal);
                prestador.setValor_servico(rs.getFloat("valor_servico"));
                prestador.setFk_idusuario(rs.getInt("fk_idusuario"));
                prestador.setFk_idsituacao(rs.getInt("fk_idsituacao"));


                res.add(prestador);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.fechar();
            return (res);
        }
    }

    public ArrayList<Servico> buscar(int id) {

        ArrayList<Servico> res = new ArrayList<Servico>();
        Conexao con = new Conexao();
        String query = "SELECT idservico, atividade, descricao, site, data_cadastro, valor_servico, fk_idusuario, fk_idsituacao "
                + "FROM servico WHERE idservico LIKE ?";

        con.preparar(query);
        try {
            con.getPstmt().setInt(1, id);
            ResultSet rs = con.getPstmt().executeQuery();
            while (rs.next()) {
                Servico prestador = new Servico();
                prestador.setIdservico(rs.getInt("idservico"));
                prestador.setAtividade(rs.getString("atividade"));
                prestador.setDescricao(rs.getString("descricao"));
                prestador.setSite(rs.getString("site"));
                Date t = rs.getDate("data_cadastro");
                Calendar cal = Calendar.getInstance();
                cal.setTime(t);
                prestador.setData_cadastro(cal);
                prestador.setValor_servico(rs.getFloat("valor_servico"));
                prestador.setFk_idusuario(rs.getInt("fk_idusuario"));
                prestador.setFk_idsituacao(rs.getInt("fk_idsituacao"));


                res.add(prestador);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.fechar();
            return (res);
        }
    }

    public ArrayList<Servico> buscarIdusuario(int id) {

        ArrayList<Servico> res = new ArrayList<Servico>();
        Conexao con = new Conexao();
        String query = "SELECT idservico, atividade, descricao, site, data_cadastro, valor_servico, fk_idusuario, fk_idsituacao "
                + "FROM servico WHERE fk_idusuario LIKE ?";

        con.preparar(query);
        try {
            con.getPstmt().setInt(1, id);
            ResultSet rs = con.getPstmt().executeQuery();
            while (rs.next()) {
                Servico prestador = new Servico();
                prestador.setIdservico(rs.getInt("idservico"));
                prestador.setAtividade(rs.getString("atividade"));
                prestador.setDescricao(rs.getString("descricao"));
                prestador.setSite(rs.getString("site"));
                Date t = rs.getDate("data_cadastro");
                Calendar cal = Calendar.getInstance();
                cal.setTime(t);
                prestador.setData_cadastro(cal);
                prestador.setValor_servico(rs.getFloat("valor_servico"));
                prestador.setFk_idusuario(rs.getInt("fk_idusuario"));
                prestador.setFk_idsituacao(rs.getInt("fk_idsituacao"));


                res.add(prestador);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.fechar();
            return (res);
        }
    }

    public ArrayList<Usuario_Servico> buscarServico(String atividade, int cidade) {

        ArrayList<Usuario_Servico> res = new ArrayList<Usuario_Servico>();
        Conexao con = new Conexao();
        String query = "Select * FROM servico INNER JOIN endereco ON servico.atividade = ? and endereco.fk_idcidade=? inner join usuario on servico.fk_idusuario=usuario.idusuario";

        con.preparar(query);
        try {
            con.getPstmt().setString(1, atividade);
            con.getPstmt().setInt(2, cidade);
            ResultSet rs = con.getPstmt().executeQuery();
            while (rs.next()) {
                Usuario_Servico prestador = new Usuario_Servico();
                prestador.setIdservico(rs.getInt("servico.idservico"));
                prestador.setAtividade(rs.getString("servico.atividade"));
                prestador.setDescricao(rs.getString("servico.descricao"));
                prestador.setSite(rs.getString("servico.site"));
                Date t = rs.getDate("servico.data_cadastro");
                Calendar cal = Calendar.getInstance();
                cal.setTime(t);
                prestador.setServico_data_cadastro(cal);
                prestador.setValor_servico(rs.getFloat("servico.valor_servico"));
                prestador.setFk_idusuario(rs.getInt("servico.fk_idusuario"));
                prestador.setFk_idsituacao(rs.getInt("servico.fk_idsituacao"));

                prestador.setIdusuario(rs.getInt("usuario.idusuario"));
                prestador.setNome(rs.getString("usuario.nome"));
                prestador.setSobrenome(rs.getString("usuario.sobrenome"));
                prestador.setEmail(rs.getString("usuario.email"));
                prestador.setSexo(rs.getString("usuario.sexo"));
                prestador.setLogin(rs.getString("usuario.login"));
                prestador.setSenha(rs.getString("usuario.senha"));
                prestador.setCpf(rs.getInt("usuario.cpf"));
                prestador.setFoto(rs.getString("usuario.foto_perfil"));
                prestador.setCelular(rs.getInt("usuario.celular"));
                prestador.setTelefone_fixo(rs.getInt("usuario.telefone_fixo"));
                t = rs.getDate("usuario.data_cadastro");
                cal = Calendar.getInstance();
                cal.setTime(t);
                prestador.setUsuario_data_cadastro(cal);

                res.add(prestador);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.fechar();
            return (res);
        }
    }

}
