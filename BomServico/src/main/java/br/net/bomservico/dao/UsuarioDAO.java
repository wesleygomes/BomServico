package br.net.bomservico.dao;

import java.io.Serializable;
import br.net.bomservico.beans.Usuario;
import br.net.bomservico.beans.Usuario_Servico;
import br.net.bomservico.conexao.Conexao;
import java.sql.Date; 
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioDAO implements Serializable{
	private static final long serialVersionUID = 4746530905701003341L;

	public boolean excluir(Usuario usuario) {
        boolean res = false;
        Conexao con = new Conexao();
        String query = "DELETE FROM usuario WHERE idusuario=?";

        con.transacao();
        con.preparar(query);
        try {
            con.getPstmt().setInt(1, usuario.getIdusuario());
            res = con.executeUpdate();


        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.fechaTransacao(res);
            con.fechar();
            return (res);
        }
    }

    @SuppressWarnings("finally")
    public boolean inserir(Usuario usuario) {
        boolean res = false;
        Conexao con = new Conexao();

        String query = "INSERT INTO usuario (nome,sobrenome, email, sexo, login, senha, cpf, foto_perfil, celular, telefone_fixo) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        con.transacao();
        con.preparar(query);
        try {
            con.getPstmt().setString(1, usuario.getNome());
            con.getPstmt().setString(2, usuario.getSobrenome());
            con.getPstmt().setString(3, usuario.getEmail());
            con.getPstmt().setString(4, usuario.getSexo());
            con.getPstmt().setString(5, usuario.getLogin());
            con.getPstmt().setString(6, usuario.getSenha());
            con.getPstmt().setInt(7, usuario.getCpf());
            con.getPstmt().setString(8, usuario.getFoto());
            con.getPstmt().setInt(9, usuario.getCelular());
            con.getPstmt().setInt(10, usuario.getTelefone_fixo());


            res = con.executeUpdate();


        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.fechaTransacao(res);
            con.fechar();
            return (res);
        }
    }

    @SuppressWarnings("finally")
    public boolean atualizar(Usuario atual, Usuario novo) {
        boolean res = false;
        Conexao con = new Conexao();

        String query = "UPDATE usuario SET nome=?, sobrenome=?, email=?, sexo=?, login=?, senha=?, cpf=?, foto_perfil=?, celular=?, telefone_fixo=? "
                + "WHERE idusuario=?";

        con.transacao();
        con.preparar(query);
        try {
            con.getPstmt().setString(1, novo.getNome());
            con.getPstmt().setString(2, novo.getSobrenome());
            con.getPstmt().setString(3, novo.getEmail());
            con.getPstmt().setString(4, novo.getSexo());
            con.getPstmt().setString(5, novo.getLogin());
            con.getPstmt().setString(6, novo.getSenha());
            con.getPstmt().setInt(7, novo.getCpf());
            con.getPstmt().setString(8, novo.getFoto());
            con.getPstmt().setInt(9, novo.getCelular());
            con.getPstmt().setInt(10, novo.getTelefone_fixo());
            con.getPstmt().setInt(11, atual.getIdusuario());

            res = con.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.fechaTransacao(res);
            con.fechar();
            return (res);
        }
    }

    public ArrayList<Usuario> buscar() {
        ArrayList<Usuario> res = new ArrayList<Usuario>();
        Conexao con = new Conexao();
        String query = "SELECT idusuario, nome,sobrenome, email,sexo, login, senha, cpf, foto_perfil, celular, telefone_fixo, data_cadastro FROM usuario order by nome";

        con.preparar(query);
        try {
            ResultSet rs = con.getPstmt().executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdusuario(rs.getInt("idusuario"));
                usuario.setNome(rs.getString("nome"));
                usuario.setNome(rs.getString("sobrenome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSexo(rs.getString("sexo"));
                usuario.setLogin(rs.getString("login"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setCpf(rs.getInt("cpf"));
                usuario.setFoto(rs.getString("foto_perfil"));
                usuario.setCelular(rs.getInt("celular"));
                usuario.setTelefone_fixo(rs.getInt("telefone_fixo"));
                Date t = rs.getDate("data_cadastro");
                Calendar cal = Calendar.getInstance();
                cal.setTime(t);
                usuario.setData_cadastro(cal);


                res.add(usuario);
            }
        } catch (SQLException e) {
            Logger.getLogger(UsuarioDAO.class.getName(), null).log(Level.SEVERE, null, e);
        } finally {
            con.fechar();
            return (res);
        }
    }

     @SuppressWarnings("finally")
    public ArrayList<Usuario_Servico> buscarProfissao(String profissao) {

        ArrayList<Usuario_Servico> res = new ArrayList<Usuario_Servico>();
        Conexao con = new Conexao();
        String query = "Select * FROM usuario INNER JOIN servico ON usuario.idusuario = servico.fk_idusuario where servico.atividade LIKE ?";

        con.preparar(query);
        try {
            con.getPstmt().setString(1, "%"+profissao+"%");
            ResultSet rs = con.getPstmt().executeQuery();
            while (rs.next()) {
                Usuario_Servico pesquisa = new Usuario_Servico();
                pesquisa.setIdusuario(rs.getInt("usuario.idusuario"));
                pesquisa.setNome(rs.getString("usuario.nome"));
                pesquisa.setSobrenome(rs.getString("usuario.sobrenome"));
                pesquisa.setEmail(rs.getString("usuario.email"));
                pesquisa.setSexo(rs.getString("usuario.sexo"));
                pesquisa.setLogin(rs.getString("usuario.login"));
                pesquisa.setSenha(rs.getString("usuario.senha"));
                pesquisa.setCpf(rs.getInt("usuario.cpf"));
                pesquisa.setFoto(rs.getString("usuario.foto_perfil"));
                pesquisa.setCelular(rs.getInt("usuario.celular"));
                pesquisa.setTelefone_fixo(rs.getInt("usuario.telefone_fixo"));
                Date t = rs.getDate("usuario.data_cadastro");
                Calendar cal = Calendar.getInstance();
                cal.setTime(t);
                pesquisa.setUsuario_data_cadastro(cal);
                
                pesquisa.setIdservico(rs.getInt("servico.idservico"));
                pesquisa.setAtividade(rs.getString("servico.atividade"));
                pesquisa.setDescricao(rs.getString("servico.descricao"));
                pesquisa.setSite(rs.getString("servico.site"));
                t = rs.getDate("servico.data_cadastro");
	        cal =  Calendar.getInstance();
	        cal.setTime(t);
                pesquisa.setServico_data_cadastro(cal);
                pesquisa.setValor_servico(rs.getFloat("servico.valor_servico"));
                pesquisa.setFk_idusuario(rs.getInt("servico.fk_idusuario"));
                pesquisa.setFk_idsituacao(rs.getInt("servico.fk_idsituacao"));

                res.add(pesquisa);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.fechar();
            return (res);
        }
    }

    @SuppressWarnings("finally")
    public ArrayList<Usuario> buscar(String nome) {

        ArrayList<Usuario> res = new ArrayList<Usuario>();
        Conexao con = new Conexao();
        String query = "SELECT idusuario, nome, sobrenome, email,sexo, login, senha, cpf, foto_perfil, celular, telefone_fixo, data_cadastro"
                + "FROM usuario WHERE nome";

        con.preparar(query);
        try {
            con.getPstmt().setString(1, "%" + nome + "%");
            ResultSet rs = con.getPstmt().executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdusuario(rs.getInt("idusuario"));
                usuario.setNome(rs.getString("nome"));
                usuario.setNome(rs.getString("sobrenome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSexo(rs.getString("sexo"));
                usuario.setLogin(rs.getString("login"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setCpf(rs.getInt("cpf"));
                usuario.setFoto(rs.getString("foto_perfil"));
                usuario.setCelular(rs.getInt("celular"));
                usuario.setTelefone_fixo(rs.getInt("telefone_fixo"));
                Date t = rs.getDate("data_cadastro");
                Calendar cal = Calendar.getInstance();
                cal.setTime(t);
                usuario.setData_cadastro(cal);

                res.add(usuario);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.fechar();
            return (res);
        }
    }
    
    @SuppressWarnings("finally")
    public ArrayList<Usuario> buscarEmail(String email) {

        ArrayList<Usuario> res = new ArrayList<Usuario>();
        Conexao con = new Conexao();
        String query = "SELECT idusuario, nome,sobrenome, email,sexo, login, senha, cpf, foto_perfil, celular, telefone_fixo, data_cadastro FROM usuario WHERE email=?";

        con.preparar(query);
        try {
            con.getPstmt().setString(1, email);
            ResultSet rs = con.getPstmt().executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdusuario(rs.getInt("idusuario"));
                usuario.setNome(rs.getString("nome"));
                usuario.setSobrenome(rs.getString("sobrenome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSexo(rs.getString("sexo"));
                usuario.setLogin(rs.getString("login"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setCpf(rs.getInt("cpf"));
                usuario.setFoto(rs.getString("foto_perfil"));
                usuario.setCelular(rs.getInt("celular"));
                usuario.setTelefone_fixo(rs.getInt("telefone_fixo"));
                Date t = rs.getDate("data_cadastro");
                Calendar cal = Calendar.getInstance();
                cal.setTime(t);
                usuario.setData_cadastro(cal);

                res.add(usuario);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.fechar();
            return (res);
        }
    }

    public ArrayList<Usuario> buscar(int id) {

        ArrayList<Usuario> res = new ArrayList<Usuario>();
        Conexao con = new Conexao();
        String query = "SELECT idusuario, nome,sobrenome, email,sexo, login, senha, cpf, foto_perfil, celular, telefone_fixo, data_cadastro FROM usuario WHERE idusuario=?";

        con.preparar(query);
        try {
            con.getPstmt().setInt(1, id);
            ResultSet rs = con.getPstmt().executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdusuario(rs.getInt("idusuario"));
                usuario.setNome(rs.getString("nome"));
                usuario.setSobrenome(rs.getString("sobrenome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSexo(rs.getString("sexo"));
                usuario.setLogin(rs.getString("login"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setCpf(rs.getInt("cpf"));
                usuario.setFoto(rs.getString("foto_perfil"));
                usuario.setCelular(rs.getInt("celular"));
                usuario.setTelefone_fixo(rs.getInt("telefone_fixo"));
                Date t = rs.getDate("data_cadastro");
                Calendar cal = Calendar.getInstance();
                cal.setTime(t);
                usuario.setData_cadastro(cal);


                res.add(usuario);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.fechar();
            return (res);
        }
    }

    public ArrayList<Usuario> buscarLogin(String login, String senha) {
        ArrayList<Usuario> res = new ArrayList<Usuario>();
        Conexao con = new Conexao();
        String query = "SELECT idusuario, nome,sobrenome, email,sexo, login, senha, cpf, foto_perfil, celular, telefone_fixo, data_cadastro FROM usuario where login=? and senha=?";
        con.preparar(query);
        try {
            con.getPstmt().setString(1, login);
            con.getPstmt().setString(2, senha);
            ResultSet rs = con.getPstmt().executeQuery();
            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdusuario(rs.getInt("idusuario"));
                usuario.setNome(rs.getString("nome"));
                usuario.setSobrenome(rs.getString("sobrenome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSexo(rs.getString("sexo"));
                usuario.setLogin(rs.getString("login"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setCpf(rs.getInt("cpf"));
                usuario.setFoto(rs.getString("foto_perfil"));
                usuario.setCelular(rs.getInt("celular"));
                usuario.setTelefone_fixo(rs.getInt("telefone_fixo"));
                Date t = rs.getDate("data_cadastro");
                Calendar cal = Calendar.getInstance();
                cal.setTime(t);
                usuario.setData_cadastro(cal);

                res.add(usuario);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName(), null).log(Level.SEVERE, null, ex);
        } finally {
            con.fechar();
            return (res);
        }
    }
    
    public ArrayList<Usuario> buscarUltimoRegistro() {
        ArrayList<Usuario> res = new ArrayList<Usuario>();
        Conexao con = new Conexao();
        String query = "SELECT max(idusuario) FROM usuario";

        con.preparar(query);
        try {
            ResultSet rs = con.getPstmt().executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdusuario(rs.getInt("max(idusuario)"));
                res.add(usuario);
            }
        } catch (SQLException e) {
            Logger.getLogger(UsuarioDAO.class.getName(), null).log(Level.SEVERE, null, e);
        } finally {
            con.fechar();
            return (res);
        }
    }
}
