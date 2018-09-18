/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.implementacao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.dao.interfaces.InterfaceDiaDAO;
import model.dao.interfaces.InterfaceDiarioDAO;
import model.domain.Dia;
import model.domain.Diario;
import util.db.exception.ExcecaoPersistencia;
import util.db.ConnectionManager;

/**
 *
 * @author Juliana
 */
public class DiaDAO implements InterfaceDiaDAO {

    @Override
    public Long inserir(Dia dia) throws ExcecaoPersistencia {
        if (dia == null) {

            throw new ExcecaoPersistencia("Dia nao pode ser null");

        }

        Long seqDia = null;

        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "INSERT INTO dia (cod_diario, txt_Dia, ordem_dia, data_dia) "
                    + "VALUES(?,?,?,?) RETURNING seq_dia";

            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setLong(1, dia.getDiario().getCodDiario());
            pstmt.setString(2, dia.getTxtDia());
            pstmt.setInt(3, dia.getOrdemDia());
            pstmt.setDate(4, new java.sql.Date(dia.getDataDia().getTime()));

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                seqDia = rs.getLong("seq_dia");
            }

            rs.close();
            pstmt.close();
            connection.close();
            return seqDia;

        } catch (ClassNotFoundException | SQLException e) {

            throw new ExcecaoPersistencia(e.getMessage(), e);

        }
    }

    @Override
    public boolean atualizar(Dia dia) throws ExcecaoPersistencia {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "UPDATE dia "
                    + "   SET cod_diario = ?, "
                    + "       txt_dia = ?, "
                    + "       ordem_dia = ?, "
                    + " WHERE cod_diario = ?;";

            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setLong(1, dia.getDiario().getCodDiario());
            pstmt.setString(2, dia.getTxtDia());
            pstmt.setInt(3, dia.getOrdemDia());
            pstmt.setDate(4, new java.sql.Date(dia.getDataDia().getTime()));

            pstmt.executeUpdate();

            pstmt.close();
            connection.close();

            return true;

        } catch (ClassNotFoundException | SQLException e) {

            throw new ExcecaoPersistencia(e.getMessage(), e);

        }
    }

    @Override
    public boolean deletar(Dia dia) throws ExcecaoPersistencia {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "DELETE FROM dia WHERE seq_dia = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, dia.getSeqDia());
            pstmt.executeUpdate();

            pstmt.close();
            connection.close();
            return true;
        } catch (ClassNotFoundException | SQLException e) {

            throw new ExcecaoPersistencia(e.getMessage(), e);

        }
    }

    @Override
    public Dia consultarPorId(Long seqDia) throws ExcecaoPersistencia {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM dia WHERE seq_dia = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, seqDia);
            ResultSet rs = pstmt.executeQuery();

            Dia dia = null;
            InterfaceDiarioDAO diarioDAO = new DiarioDAO();
            if (rs.next()) {
                dia = new Dia();
                dia.setSeqDia(rs.getLong("seq_dia"));
                Diario diario;
                diario = diarioDAO.consultarPorId(rs.getLong("cod_diario"));
                dia.setDiario(diario);
                dia.setTxtDia(rs.getString("txt_dia"));
                dia.setOrdemDia(rs.getInt("ordem_dia"));
                dia.setDataDia(rs.getDate("data_dia"));
            }

            rs.close();
            pstmt.close();
            connection.close();

            return dia;
        } catch (ClassNotFoundException | SQLException e) {

            throw new ExcecaoPersistencia(e.getMessage(), e);

        }
    }

    @Override
    public List<Dia> listarPorCodDiario(Long codDiario) throws ExcecaoPersistencia {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM dia WHERE cod_diario = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, codDiario);
            ResultSet rs = pstmt.executeQuery();

            ArrayList<Dia> listarPorCodDiario = null;
            InterfaceDiarioDAO diarioDAO = new DiarioDAO();
            if (rs.next()) {
                listarPorCodDiario = new ArrayList<>();
                do {
                    Dia dia = new Dia();
                    dia.setSeqDia(rs.getLong("seq_dia"));
                    Diario diario = diarioDAO.consultarPorId(codDiario);
                    dia.setDiario(diario);
                    dia.setTxtDia(rs.getString("txt_dia"));

                    listarPorCodDiario.add(dia);
                } while (rs.next());

            }

            rs.close();
            pstmt.close();
            connection.close();

            return listarPorCodDiario;
        } catch (ClassNotFoundException | SQLException | ExcecaoPersistencia e) {

            throw new ExcecaoPersistencia(e.getMessage(), e);

        }
    }

    @Override
    public List<Dia> listarTudo() throws ExcecaoPersistencia {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM dia";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            ArrayList<Dia> listarTudo = null;
            InterfaceDiarioDAO diarioDAO = new DiarioDAO();
            if (rs.next()) {
                listarTudo = new ArrayList<>();
                do {
                    Dia dia = new Dia();
                    dia.setSeqDia(rs.getLong("seq_dia"));
                    Diario diario = diarioDAO.consultarPorId(rs.getLong("cod_diario"));
                    dia.setDiario(diario);
                    dia.setTxtDia(rs.getString("txt_dia"));
                    dia.setOrdemDia(rs.getInt("ordem_dia"));
                    dia.setDataDia(rs.getDate("data_dia"));

                    listarTudo.add(dia);
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

}
