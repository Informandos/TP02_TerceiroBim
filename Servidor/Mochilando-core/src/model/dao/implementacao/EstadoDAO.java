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
import model.dao.interfaces.InterfaceDiarioDAO;
import model.dao.interfaces.InterfaceEstadoDAO;
import model.domain.Dia;
import model.domain.Diario;
import model.domain.Estado;
import util.db.exception.ExcecaoPersistencia;
import util.db.ConnectionManager;

/**
 *
 * @author Juliana
 */
public class EstadoDAO implements InterfaceEstadoDAO {

    @Override
    public Long inserir(Estado estado) throws ExcecaoPersistencia {
        if (estado == null) {

            throw new ExcecaoPersistencia("Estado nao pode ser null");

        }

        Long codEstado = null;

        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "INSERT INTO estado (sigla, nom_estado) "
                    + "VALUES(?,?) RETURNING cod_estado";

            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, estado.getSigla());
            pstmt.setString(2, estado.getNomEstado());

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                codEstado = rs.getLong("cod_estado");
            }

            rs.close();
            pstmt.close();
            connection.close();
            return codEstado;

        } catch (ClassNotFoundException | SQLException e) {

            throw new ExcecaoPersistencia(e.getMessage(), e);

        }
    }

    @Override
    public boolean atualizar(Estado estado) throws ExcecaoPersistencia {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "UPDATE estado "
                    + "   SET sigla = ?, "
                    + "       nom_estado = ?, "
                    + " WHERE cod_diario = ?;";

            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, estado.getSigla());
            pstmt.setString(2, estado.getNomEstado());

            pstmt.executeUpdate();

            pstmt.close();
            connection.close();

            return true;

        } catch (ClassNotFoundException | SQLException e) {

            throw new ExcecaoPersistencia(e.getMessage(), e);

        }
    }

    @Override
    public boolean deletar(Estado estado) throws ExcecaoPersistencia {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "DELETE FROM estado WHERE cod_Estado = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, estado.getCodEstado());
            pstmt.executeUpdate();

            pstmt.close();
            connection.close();
            return true;
        } catch (ClassNotFoundException | SQLException e) {

            throw new ExcecaoPersistencia(e.getMessage(), e);

        }
    }

    @Override
    public Estado consultarPorId(Long codEstado) throws ExcecaoPersistencia {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM estado WHERE cod_estado = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, codEstado);
            ResultSet rs = pstmt.executeQuery();

            Estado estado = null;
            if (rs.next()) {
                estado = new Estado();
                estado.setCodEstado(rs.getLong("cod_Estado"));
                estado.setNomEstado(rs.getString("sigla"));
                estado.setNomEstado(rs.getString("nom_estado"));
            }

            rs.close();
            pstmt.close();
            connection.close();

            return estado;
        } catch (ClassNotFoundException | SQLException e) {

            throw new ExcecaoPersistencia(e.getMessage(), e);

        }
    }

    @Override
    public Estado consultarPorSigla(String sigla) throws ExcecaoPersistencia {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM estado WHERE sigla = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, sigla);
            ResultSet rs = pstmt.executeQuery();

            Estado estado = null;
            //If e utilizado para encontrar apenas um registro de estado com a sigla (unica)
            if (rs.next()) {
                estado = new Estado();
                estado.setCodEstado(rs.getLong("cod_Estado"));
                estado.setNomEstado(rs.getString("sigla"));
                estado.setNomEstado(rs.getString("nom_estado"));
            }

            rs.close();
            pstmt.close();
            connection.close();

            return estado;
        } catch (ClassNotFoundException | SQLException e) {

            throw new ExcecaoPersistencia(e.getMessage(), e);

        }
    }

    @Override
    public List<Estado> listarTudo() throws ExcecaoPersistencia {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM estado ORDER BY nom_estado";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            ArrayList<Estado> listarTudo = null;
            if (rs.next()) {
                listarTudo = new ArrayList<>();
                do {
                    Estado estado = new Estado();
                    
                    estado.setCodEstado(rs.getLong("cod_estado"));
                    estado.setSigla(rs.getString("sigla"));
                    estado.setNomEstado(rs.getString("nom_estado"));

                    listarTudo.add(estado);
                } while (rs.next());

            }

            rs.close();
            pstmt.close();
            connection.close();

            return listarTudo;
        } catch (ClassNotFoundException | SQLException e) {

            throw new ExcecaoPersistencia(e.getMessage(), e);

        }
    }
}
