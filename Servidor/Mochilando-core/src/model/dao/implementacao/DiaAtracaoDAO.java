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
import model.dao.interfaces.InterfaceAtracaoDAO;
import model.dao.interfaces.InterfaceDiaAtracaoDAO;
import model.dao.interfaces.InterfaceDiaDAO;
import model.domain.Atracao;
import model.domain.Dia;
import model.domain.DiaAtracao;
import util.db.ConnectionManager;
import util.db.exception.ExcecaoPersistencia;

/**
 *
 * @author Juliana
 */
public class DiaAtracaoDAO implements InterfaceDiaAtracaoDAO {

    @Override
    public Long inserir(DiaAtracao diaAtracao) throws ExcecaoPersistencia {
        if (diaAtracao == null) {

            throw new ExcecaoPersistencia("DiaAtracao nao pode ser null");

        }

        Long codDiaAtracao = null;

        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "INSERT INTO dia_atracao (seq_atracao, seq_dia) "
                    + "VALUES(?,?) RETURNING seq_dia_atracao ";

            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setLong(1, diaAtracao.getAtracao().getSeqAtracao());
            pstmt.setLong(2, diaAtracao.getDia().getSeqDia());

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                codDiaAtracao = rs.getLong("seq_dia_atracao");
            }

            rs.close();
            pstmt.close();
            connection.close();
            return codDiaAtracao;

        } catch (ClassNotFoundException | SQLException e) {

            throw new ExcecaoPersistencia(e.getMessage(), e);

        }
    }

    @Override
    public boolean atualizar(DiaAtracao diaAtracao) throws ExcecaoPersistencia {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "UPDATE diaAtracao "
                    + "   SET seq_atracao = ?, "
                    + "       seq_dia = ? "
                    + " WHERE cod_diario = ?;";

            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setLong(1, diaAtracao.getAtracao().getSeqAtracao());
            pstmt.setLong(2, diaAtracao.getDia().getSeqDia());

            pstmt.executeUpdate();

            pstmt.close();
            connection.close();

            return true;

        } catch (ClassNotFoundException | SQLException e) {

            throw new ExcecaoPersistencia(e.getMessage(), e);

        }
    }

    @Override
    public boolean deletar(DiaAtracao diaAtracao) throws ExcecaoPersistencia {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "DELETE FROM dia_atracao WHERE seq_dia_atracao = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, diaAtracao.getSeqDiaAtracao());
            pstmt.executeUpdate();

            pstmt.close();
            connection.close();
            return true;
        } catch (ClassNotFoundException | SQLException e) {

            throw new ExcecaoPersistencia(e.getMessage(), e);

        }
    }

    @Override
    public DiaAtracao consultarPorId(Long seqDiaAtracao) throws ExcecaoPersistencia {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM dia_atarcao WHERE seq_dia_atracao = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, seqDiaAtracao);
            ResultSet rs = pstmt.executeQuery();

            DiaAtracao diaAtracao = null;
            InterfaceDiaDAO diaDAO = new DiaDAO();
            InterfaceAtracaoDAO atracaoDAO = new AtracaoDAO();

            if (rs.next()) {
                diaAtracao = new DiaAtracao();
                diaAtracao.setSeqDiaAtracao(seqDiaAtracao);
                Dia dia;
                dia = diaDAO.consultarPorId(rs.getLong("seq_dia"));
                diaAtracao.setDia(dia);
                Atracao atracao;
                atracao = atracaoDAO.consultarPorId(rs.getLong("seq_atracao"));
                diaAtracao.setAtracao(atracao);
            }

            rs.close();
            pstmt.close();
            connection.close();

            return diaAtracao;
        } catch (ClassNotFoundException | SQLException e) {

            throw new ExcecaoPersistencia(e.getMessage(), e);

        }
    }

    @Override
    public List<DiaAtracao> listarPorSeqDia(Long seqDia) throws ExcecaoPersistencia {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM dia_atracao WHERE seq_dia = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, seqDia);
            ResultSet rs = pstmt.executeQuery();

            ArrayList<DiaAtracao> listarPorSeqDia = null;

            InterfaceDiaDAO diaDAO = new DiaDAO();
            InterfaceAtracaoDAO atracaoDAO = new AtracaoDAO();

            if (rs.next()) {
                listarPorSeqDia = new ArrayList<>();
                do {
                    DiaAtracao diaAtracao = new DiaAtracao();
                    
                    Dia dia;
                    dia = diaDAO.consultarPorId(rs.getLong("seq_dia"));
                    diaAtracao.setDia(dia);
                    
                    Atracao atracao;
                    atracao = atracaoDAO.consultarPorId(rs.getLong("seq_atracao"));
                    diaAtracao.setAtracao(atracao);

                    listarPorSeqDia.add(diaAtracao);
                } while (rs.next());

            }

            rs.close();
            pstmt.close();
            connection.close();

            return listarPorSeqDia;
        } catch (Exception e) {

            throw new ExcecaoPersistencia(e.getMessage(), e);

        }
    }

    @Override
    public List<DiaAtracao> listarTudo() throws ExcecaoPersistencia {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM dia_atracao ORDER BY seq_dia";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            ArrayList<DiaAtracao> listarTudo = null;
            
            InterfaceDiaDAO diaDAO = new DiaDAO();
            InterfaceAtracaoDAO atracaoDAO = new AtracaoDAO();

            if (rs.next()) {
                listarTudo = new ArrayList<>();
                do {
                    DiaAtracao diaAtracao = new DiaAtracao();
                    
                    Dia dia;
                    dia = diaDAO.consultarPorId(rs.getLong("seq_dia"));
                    diaAtracao.setDia(dia);
                    
                    Atracao atracao;
                    atracao = atracaoDAO.consultarPorId(rs.getLong("seq_atracao"));
                    diaAtracao.setAtracao(atracao);

                    listarTudo.add(diaAtracao);
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
