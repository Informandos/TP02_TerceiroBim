/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.implementacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.busca.implementacao.BuscarDiario;
import model.dao.interfaces.InterfaceDiarioDAO;
import model.dao.interfaces.InterfaceUsuarioDAO;
import model.domain.Cidade;
import model.domain.Diario;
import model.domain.Estado;
import model.domain.Usuario;
import util.db.exception.ExcecaoPersistencia;
import util.db.ConnectionManager;

/**
 *
 * @author Juliana
 */
public class DiarioDAO implements InterfaceDiarioDAO {

    @Override
    public Long inserir(Diario diario) throws ExcecaoPersistencia {
        if (diario == null) {

            throw new ExcecaoPersistencia("Diario nao pode ser null");

        }

        Long codDiario = null;

        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "INSERT INTO diario (cod_usuario, nom_diario, dat_publicacao, dat_inicio_viagem, dat_fim_viagem, txt_Diario, tipo_diario) "
                    + "VALUES(?,?,?,?,?,?,?) RETURNING cod_diario";

            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setLong(1, diario.getUsuario().getCodUsuario());
            pstmt.setString(2, diario.getNomDiario());
            pstmt.setDate(3, new java.sql.Date(diario.getDatPublicacao().getTime()));
            pstmt.setDate(4, new java.sql.Date(diario.getDatInicioViagem().getTime()));
            pstmt.setDate(5, new java.sql.Date(diario.getDatFimViagem().getTime()));
            pstmt.setString(6, diario.getTxtDiario());
            pstmt.setString(7, diario.getTipoDiario());

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                codDiario = rs.getLong("cod_diario");
            }

            rs.close();
            pstmt.close();
            connection.close();
            return codDiario;

        } catch (ClassNotFoundException | SQLException e) {

            throw new ExcecaoPersistencia(e.getMessage(), e);

        }

    }

    @Override
    public boolean atualizar(Diario diario) throws ExcecaoPersistencia {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "UPDATE diario "
                    + "   SET nom_diario = ?, "
                    + "       dat_publicacao = ?, "
                    + "       dat_inicio_viagem = ?, "
                    + "       dat_fim_viagem = ?, "
                    + "       txt_diario= ? "
                    + "       tipo_diario= ? "
                    + " WHERE cod_diario = ?;";

            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setLong(1, diario.getUsuario().getCodUsuario());
            pstmt.setString(2, diario.getNomDiario());
            pstmt.setDate(3, new java.sql.Date(diario.getDatPublicacao().getTime()));
            pstmt.setDate(4, new java.sql.Date(diario.getDatInicioViagem().getTime()));
            pstmt.setDate(5, new java.sql.Date(diario.getDatFimViagem().getTime()));
            pstmt.setString(6, diario.getTxtDiario());
            pstmt.setString(7, diario.getTipoDiario());

            pstmt.executeUpdate();

            pstmt.close();
            connection.close();

            return true;

        } catch (ClassNotFoundException | SQLException e) {

            throw new ExcecaoPersistencia(e.getMessage(), e);

        }
    }

    @Override
    public boolean deletar(Diario diario) throws ExcecaoPersistencia {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "DELETE FROM diario WHERE cod_diario = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, diario.getCodDiario());
            pstmt.executeUpdate();

            pstmt.close();
            connection.close();
            return true;
        } catch (ClassNotFoundException | SQLException e) {

            throw new ExcecaoPersistencia(e.getMessage(), e);

        }
    }

    @Override
    public Diario consultarPorId(Long codDiario) throws ExcecaoPersistencia {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM Diario WHERE cod_diario = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, codDiario);
            ResultSet rs = pstmt.executeQuery();

            Diario diario = null;
            InterfaceUsuarioDAO usuarioDAO = new UsuarioDAO();
            if (rs.next()) {
                diario = new Diario();
                diario.setCodDiario(rs.getLong("cod_diario"));
                Usuario usuario;
                usuario = usuarioDAO.consultarPorId(rs.getLong("cod_usuario"));
                diario.setUsuario(usuario);
                diario.setNomDiario(rs.getString("nom_diario"));
                diario.setDatPublicacao(rs.getDate("dat_publicacao"));
                diario.setDatInicioViagem(rs.getDate("dat_inicio_viagem"));
                diario.setDatFimViagem(rs.getDate("dat_fim_viagem"));
                diario.setTxtDiario(rs.getString("txt_diario"));
                diario.setTipoDiario(rs.getString("tipo_diario"));
            }

            rs.close();
            pstmt.close();
            connection.close();

            return diario;
        } catch (ClassNotFoundException | SQLException e) {

            throw new ExcecaoPersistencia(e.getMessage(), e);

        }
    }

    @Override
    public List<Diario> listarTudo() throws ExcecaoPersistencia {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM diario ORDER BY nom_diario";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            ArrayList<Diario> listarTudo = null;
            InterfaceUsuarioDAO UsuarioDAO = new UsuarioDAO();
            if (rs.next()) {
                listarTudo = new ArrayList<>();
                do {
                    Diario diario = new Diario();
                    diario.setCodDiario(rs.getLong("cod_diario"));
                    Usuario usuario = UsuarioDAO.consultarPorId(rs.getLong("cod_usuario"));
                    diario.setUsuario(usuario);
                    diario.setNomDiario(rs.getString("nom_diario"));
                    diario.setDatPublicacao(rs.getDate("dat_publicacao"));
                    diario.setDatInicioViagem(rs.getDate("dat_inicio_viagem"));
                    diario.setDatFimViagem(rs.getDate("dat_fim_viagem"));
                    diario.setTxtDiario(rs.getString("txt_diario"));
                    diario.setTipoDiario(rs.getString("tipo_diario"));

                    listarTudo.add(diario);
                } while (rs.next());

            }

            rs.close();
            pstmt.close();
            connection.close();

            return listarTudo;
        } catch (ClassNotFoundException | SQLException | ExcecaoPersistencia e) {

            throw new ExcecaoPersistencia(e.getMessage(), e);

        }
    }

    @Override
    public List<Diario> listarPorCodUsuario(Long codUsuario) throws ExcecaoPersistencia {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM diario WHERE cod_usuario = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, codUsuario);
            ResultSet rs = pstmt.executeQuery();

            ArrayList<Diario> listarPorCodUsuario = null;
            InterfaceUsuarioDAO UsuarioDAO = new UsuarioDAO();
            if (rs.next()) {
                listarPorCodUsuario = new ArrayList<>();
                do {
                    Diario diario = new Diario();
                    diario.setCodDiario(rs.getLong("cod_diario"));
                    Usuario usuario = UsuarioDAO.consultarPorId(codUsuario);
                    diario.setUsuario(usuario);
                    diario.setNomDiario(rs.getString("nom_diario"));
                    diario.setDatPublicacao(rs.getDate("dat_publicacao"));
                    diario.setDatInicioViagem(rs.getDate("dat_inicio_viagem"));
                    diario.setDatFimViagem(rs.getDate("dat_fim_viagem"));
                    diario.setTxtDiario(rs.getString("txt_diario"));
                    diario.setTipoDiario(rs.getString("tipo_diario"));

                    listarPorCodUsuario.add(diario);
                } while (rs.next());

            }

            rs.close();
            pstmt.close();
            connection.close();

            return listarPorCodUsuario;
        } catch (Exception e) {

            throw new ExcecaoPersistencia(e.getMessage(), e);

        }
    }

    @Override
    public List<Diario> listarPorCodCidade(Long codCidade) throws ExcecaoPersistencia {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT A.* FROM diario A "
                    + "JOIN dia B ON A.cod_diario = B.cod_diario "
                    + "JOIN dia_atracao C ON B.seq_dia = C.seq_dia "
                    + "JOIN atracao D ON C.seq_atracao = D.seq_atracao "
                    + "JOIN cidade E ON D.cod_cidade_atracao = E.cod_cidade "
                    + "WHERE E.cod_cidade = ? "
                    + "GROUP BY 1;";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, codCidade);
            ResultSet rs = pstmt.executeQuery();

            ArrayList<Diario> listarPorCodCidade = null;
            InterfaceUsuarioDAO UsuarioDAO = new UsuarioDAO();
            if (rs.next()) {
                listarPorCodCidade = new ArrayList<>();
                do {
                    Diario diario = new Diario();
                    diario.setCodDiario(rs.getLong("cod_diario"));
                    Usuario usuario = UsuarioDAO.consultarPorId(rs.getLong("cod_usuario"));
                    diario.setUsuario(usuario);
                    diario.setNomDiario(rs.getString("nom_diario"));
                    diario.setDatPublicacao(rs.getDate("dat_publicacao"));
                    diario.setDatInicioViagem(rs.getDate("dat_inicio_viagem"));
                    diario.setDatFimViagem(rs.getDate("dat_fim_viagem"));
                    diario.setTxtDiario(rs.getString("txt_diario"));

                    listarPorCodCidade.add(diario);
                } while (rs.next());

            }

            rs.close();
            pstmt.close();
            connection.close();

            return listarPorCodCidade;
        } catch (ClassNotFoundException | SQLException | ExcecaoPersistencia e) {

            throw new ExcecaoPersistencia(e.getMessage(), e);

        }
    }

    @Override
    public List<Diario> listarPorCodEstado(Long codEstado) throws ExcecaoPersistencia {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT A.* FROM diario A "
                    + "JOIN dia B ON A.cod_diario = B.cod_diario "
                    + "JOIN dia_atracao C ON B.seq_dia = C.seq_dia "
                    + "JOIN atracao D ON C.seq_atracao = D.seq_atracao "
                    + "JOIN cidade E ON D.cod_cidade_atracao = E.cod_cidade "
                    + "JOIN estado F ON E.cod_estado = F.cod_estado "
                    + "WHERE E.cod_estado = ? "
                    + "GROUP BY 1;";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, codEstado);
            ResultSet rs = pstmt.executeQuery();

            ArrayList<Diario> listarPorEstado = null;
            InterfaceUsuarioDAO UsuarioDAO = new UsuarioDAO();
            if (rs.next()) {
                listarPorEstado = new ArrayList<>();
                do {
                    Diario diario = new Diario();
                    diario.setCodDiario(rs.getLong("cod_diario"));
                    Usuario usuario = UsuarioDAO.consultarPorId(rs.getLong("cod_usuario"));
                    diario.setUsuario(usuario);
                    diario.setNomDiario(rs.getString("nom_diario"));
                    diario.setDatPublicacao(rs.getDate("dat_publicacao"));
                    diario.setDatInicioViagem(rs.getDate("dat_inicio_viagem"));
                    diario.setDatFimViagem(rs.getDate("dat_fim_viagem"));
                    diario.setTxtDiario(rs.getString("txt_diario"));

                    listarPorEstado.add(diario);
                } while (rs.next());

            }

            rs.close();
            pstmt.close();
            connection.close();

            return listarPorEstado;
        } catch (ClassNotFoundException | SQLException | ExcecaoPersistencia e) {

            throw new ExcecaoPersistencia(e.getMessage(), e);

        }
    }

    @Override
    public List<Diario> atualizarPaginaInicial(Long codUsuario) throws ExcecaoPersistencia {
        //obtem os diarios mais recentes que tem tags seguidas pelo usuaro
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT A.cod_diario FROM"
                    + " diario A JOIN tag_diario B ON A.cod_diario = B.cod_diario "
                    + "JOIN usuario_tag C ON B.cod_tag = C.cod_tag "
                    + "WHERE C.cod_usuario = ? "
                    + "GROUP BY 1, A.dat_publicacao ORDER BY A.dat_publicacao";

            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setLong(1, codUsuario);

            ResultSet rs = pstmt.executeQuery();

            ArrayList<Diario> listarTudo = null;
            InterfaceUsuarioDAO UsuarioDAO = new UsuarioDAO();
            if (rs.next()) {
                listarTudo = new ArrayList<>();
                do {
                    Diario diario = new Diario();
                    diario.setCodDiario(rs.getLong("cod_diario"));
                    Usuario usuario = UsuarioDAO.consultarPorId(rs.getLong("cod_usuario"));
                    diario.setUsuario(usuario);
                    diario.setNomDiario(rs.getString("nom_diario"));
                    diario.setDatPublicacao(rs.getDate("dat_publicacao"));
                    diario.setDatInicioViagem(rs.getDate("dat_inicio_viagem"));
                    diario.setDatFimViagem(rs.getDate("dat_fim_viagem"));
                    diario.setTxtDiario(rs.getString("txt_diario"));

                    listarTudo.add(diario);
                } while (rs.next());

            }

            rs.close();
            pstmt.close();
            connection.close();

            return listarTudo;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExcecaoPersistencia(e.getMessage(), e);
        }

    }

    @Override
    public List<Diario> buscarDiario(String textoBusca) throws ExcecaoPersistencia {
       List<Diario> diariosPesquisa = new ArrayList();
       BuscarDiario buscarDiario = new BuscarDiario();
       diariosPesquisa = buscarDiario.buscaGeral(textoBusca);
       return diariosPesquisa;
    }

}
