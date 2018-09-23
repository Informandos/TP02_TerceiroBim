package model.dao.implementacao;

import java.util.List;
import model.dao.interfaces.InterfaceUsuarioDAO;
import model.domain.Usuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.domain.Cidade;
import util.db.exception.ExcecaoPersistencia;
import util.db.ConnectionFactory;
import util.db.ConnectionManager;
import model.dao.implementacao.CidadeDAO;
import model.dao.interfaces.InterfaceCidadeDAO;

public class UsuarioDAO implements InterfaceUsuarioDAO {

    @Override
    public Long inserir(Usuario usuario) throws ExcecaoPersistencia {

        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "INSERT INTO usuario (nom_usuario,sobrenome_usuario,txt_email,txt_senha,sexo,dat_nascimento,cod_cidade) VALUES(?,?,?,md5(?),?,?,?)";

            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, usuario.getNomUsuario());
            pstmt.setString(2, usuario.getSobrenomeUsuario());
            pstmt.setString(3, usuario.getTxtEmail());
            pstmt.setString(4, usuario.getTxtSenha());
            pstmt.setString(5, usuario.getSexo());
            pstmt.setDate(6,new java.sql.Date( usuario.getDatNascimento().getTime()));
            // pstmt.setLong(7, usuario.getCidade().getCodCidade());
            int codCidade = 1;
            pstmt.setInt(7, codCidade);
            ResultSet rs = pstmt.executeQuery();

            Long id = null;
            if (rs.next()) {
                id = rs.getLong("cod_usuario");
                usuario.setCodUsuario(id);
            }

            rs.close();
            pstmt.close();
            connection.close();

            return id;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExcecaoPersistencia(e.getMessage(), e);
        }
    }

    @Override
    public boolean atualizar(Usuario usuario) throws ExcecaoPersistencia {
        try {

            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "UPDATE usuario "
                    + "   SET nom_usuario = ?, "
                    + "       sobrenome_usuario = ?, "
                    + "       txt_senha = md5(?), "
                    + "       txt_email=?,"
                    + "       img_perfil = ?,"
                    + "       sexo = ?, "
                    + "       dat_nascimento = ?,"
                    + "       cod_cidade = ?"
                    + " WHERE cod_usuario = ?;";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            Long codUsuario = usuario.getCodUsuario();
            pstmt.setLong(9, usuario.getCodUsuario());
            pstmt.setString(1, usuario.getNomUsuario());
            pstmt.setString(2, usuario.getSobrenomeUsuario());
            pstmt.setString(4, usuario.getTxtSenha());
            pstmt.setString(3, usuario.getTxtEmail());
            pstmt.setByte(5, usuario.getImgPerfil());
            pstmt.setString(6, usuario.getSexo());
            pstmt.setDate(7, (Date) usuario.getDatNascimento());
            pstmt.setLong(8, usuario.getCidade().getCodCidade());
            pstmt.executeUpdate();

            pstmt.close();
            connection.close();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExcecaoPersistencia(e.getMessage(), e);
        }
    }

    @Override
    public boolean deletar(Usuario usuario) throws ExcecaoPersistencia {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "DELETE FROM usuario WHERE cod_usuario = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, usuario.getCodUsuario());
            pstmt.executeUpdate();

            pstmt.close();
            connection.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExcecaoPersistencia(e.getMessage(), e);
        }
    }

    @Override
    public ArrayList<Usuario> listarTudo() throws ExcecaoPersistencia {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM usuario ORDER BY nome_usuario";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            ArrayList<Usuario> listAll = null;
            InterfaceCidadeDAO cidadeDAO = new CidadeDAO();
            if (rs.next()) {
                listAll = new ArrayList<>();
                do {
                    Usuario usuario = new Usuario();
                    usuario.setCodUsuario(rs.getLong("cod_usuario"));
                    usuario.setNomUsuario(rs.getString("nom_usuario"));
                    usuario.setSobrenomeUsuario(rs.getString("sobrenome_usuario"));
                    usuario.setTxtEmail(rs.getString("txt_email"));
                    usuario.setTxtSenha(rs.getString("txt_senha"));
                    usuario.setImgPerfil(rs.getByte("img_perfil"));
                    usuario.setSexo(rs.getString("sexo"));
                    usuario.setDatNascimento(rs.getDate("dat_nascimento"));
                    Cidade cidade = cidadeDAO.consultarPorId(rs.getLong("cod_cidade"));
                    usuario.setCidade(cidade);
                    listAll.add(usuario);
                } while (rs.next());
            }

            rs.close();
            pstmt.close();
            connection.close();

            return listAll;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExcecaoPersistencia(e.getMessage(), e);
        }
    }

    @Override
    public Usuario consultarPorId(Long id) throws ExcecaoPersistencia {

        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM usuario WHERE cod_usuario = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();

            Usuario usuario = null;
            InterfaceCidadeDAO cidadeDAO = new CidadeDAO();
            if (rs.next()) {
                usuario = new Usuario();
                usuario.setCodUsuario(rs.getLong("cod_usuario"));
                usuario.setNomUsuario(rs.getString("nom_usuario"));
                usuario.setSobrenomeUsuario(rs.getString("sobrenome_usuario"));
                usuario.setTxtEmail(rs.getString("txt_email"));
                usuario.setTxtSenha(rs.getString("txt_senha"));
                usuario.setImgPerfil(rs.getByte("img_perfil"));
                usuario.setSexo(rs.getString("sexo"));
                usuario.setDatNascimento(rs.getDate("dat_nascimento"));
                Cidade cidade = cidadeDAO.consultarPorId(rs.getLong("cod_cidade"));
                usuario.setCidade(cidade);
            }

            rs.close();
            pstmt.close();
            connection.close();

            return usuario;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExcecaoPersistencia(e.getMessage(), e);
        }
    }

    @Override
    public Usuario consultarPorEmail(String txtEmail) throws ExcecaoPersistencia {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM usuario WHERE txt_email = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, txtEmail);
            ResultSet rs = pstmt.executeQuery();

            Usuario usuario = null;
            InterfaceCidadeDAO cidadeDAO = new CidadeDAO();
            if (rs.next()) {
                usuario = new Usuario();
                usuario.setCodUsuario(rs.getLong("cod_usuario"));
                usuario.setNomUsuario(rs.getString("nome_usuario"));
                usuario.setSobrenomeUsuario(rs.getString("sobrenome_usuario"));
                usuario.setTxtEmail(rs.getString("txt_email"));
                usuario.setTxtSenha(rs.getString("txt_senha"));
                usuario.setImgPerfil(rs.getByte("img_perfil"));
                usuario.setSexo(rs.getString("sexo"));
                usuario.setDatNascimento(rs.getDate("dat_nascimento"));
                Cidade cidade = cidadeDAO.consultarPorId(rs.getLong("cod_cidade"));
                usuario.setCidade(cidade);
            }

            rs.close();
            pstmt.close();
            connection.close();

            return usuario;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExcecaoPersistencia(e.getMessage(), e);
        }
    }

    @Override
    public Usuario consultarPorEmailSenha(String email, String senha) throws ExcecaoPersistencia {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM usuario WHERE txt_email = ? AND txt_senha = md5(?)";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, email);
            pstmt.setString(2, senha);
            ResultSet rs = pstmt.executeQuery();

            Usuario usuario = null;
            InterfaceCidadeDAO cidadeDAO = new CidadeDAO();
            if (rs.next()) {
                usuario = new Usuario();
                usuario.setCodUsuario(rs.getLong("cod_usuario"));
                usuario.setNomUsuario(rs.getString("nom_usuario"));
                usuario.setSobrenomeUsuario(rs.getString("sobrenome_usuario"));
                usuario.setTxtEmail(rs.getString("txt_email"));
                usuario.setTxtSenha(rs.getString("txt_senha"));
                usuario.setImgPerfil(rs.getByte("img_perfil"));
                usuario.setSexo(rs.getString("sexo"));
                usuario.setDatNascimento(rs.getDate("dat_nascimento"));
                Cidade cidade = cidadeDAO.consultarPorId(rs.getLong("cod_cidade"));
                usuario.setCidade(cidade);
            }

            rs.close();
            pstmt.close();
            connection.close();

            return usuario;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExcecaoPersistencia(e.getMessage(), e);
        }
    }

}
