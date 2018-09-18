/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.implementacao;

import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.dao.interfaces.InterfaceCidadeDAO;
import model.domain.Cidade;
import util.db.exception.ExcecaoPersistencia;
import java.sql.Connection;
import model.dao.interfaces.InterfaceEstadoDAO;
import model.domain.Estado;
import util.db.ConnectionManager;

/**
 *
 * @author Juliana, Carlos
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class CidadeDAO implements InterfaceCidadeDAO {

    @Override
    public Long inserir(Cidade cidade) throws ExcecaoPersistencia {
        if (cidade == null) {

            throw new ExcecaoPersistencia("Cidade nao pode ser null");

        }

        Long codCidade = null;

        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "INSERT INTO cidade (cod_estado, nom_cidade) "
                    + "VALUES(?,?) RETURNING cod_cidade";

            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setLong(1, cidade.getEstado().getCodEstado());
            pstmt.setString(2, cidade.getNomCidade());

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                codCidade = rs.getLong("cod_cidade");
            }

            rs.close();
            pstmt.close();
            connection.close();
            return codCidade;

        } catch (ClassNotFoundException | SQLException e) {

            throw new ExcecaoPersistencia(e.getMessage(), e);
        }
    }

    @Override
    public boolean atualizar(Cidade cidade) throws ExcecaoPersistencia {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "UPDATE cidade "
                    + "   SET cod_estado = ?, "
                    + "       nom_cidade = ?, "
                    + " WHERE cod_diario = ?;";

            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setLong(1, cidade.getEstado().getCodEstado());
            pstmt.setString(2, cidade.getNomCidade());

            pstmt.executeUpdate();

            pstmt.close();
            connection.close();

            return true;

        } catch (ClassNotFoundException | SQLException e) {

            throw new ExcecaoPersistencia(e.getMessage(), e);

        }
    }

    @Override
    public boolean deletar(Cidade cidade) throws ExcecaoPersistencia {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "DELETE FROM cidade WHERE cod_cidade = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, cidade.getCodCidade());
            pstmt.executeUpdate();

            pstmt.close();
            connection.close();
            return true;
        } catch (ClassNotFoundException | SQLException e) {

            throw new ExcecaoPersistencia(e.getMessage(), e);

        }
    }

    @Override
    public Cidade consultarPorId(Long codCidade) throws ExcecaoPersistencia {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM cidade WHERE cod_cidade = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, codCidade);
            ResultSet rs = pstmt.executeQuery();
            
            InterfaceEstadoDAO estadoDAO = new EstadoDAO(); 
            
            Cidade cidade = null;
            if (rs.next()) {
                cidade = new Cidade();
                cidade.setCodCidade(rs.getLong("cod_cidade"));
                
                Estado estado = estadoDAO.consultarPorId(rs.getLong("cod_estado"));
                cidade.setEstado(estado);
                
                cidade.setNomCidade(rs.getString("nom_cidade"));
            }

            rs.close();
            pstmt.close();
            connection.close();

            return cidade;
        } catch (ClassNotFoundException | SQLException e) {

            throw new ExcecaoPersistencia(e.getMessage(), e);

        }
    }

    @Override
    public List<Cidade> listarPorCodEstado(Long codEstado) throws ExcecaoPersistencia {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM cidade WHERE cod_estado = ? ORDER BY nom_cidade";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, codEstado);
            ResultSet rs = pstmt.executeQuery();

            ArrayList<Cidade> listarTudo = null;
            InterfaceEstadoDAO estadoDAO = new EstadoDAO();
            
            
            if (rs.next()) {
                listarTudo = new ArrayList<>();
                do {
                    Cidade cidade = new Cidade();
                    
                    cidade.setCodCidade(rs.getLong("cod_cidade"));
                    
                    Estado estado = estadoDAO.consultarPorId(rs.getLong("cod_estado"));
                    cidade.setEstado(estado);
                    
                    cidade.setNomCidade(rs.getString("nom_cidade"));

                    listarTudo.add(cidade);
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

    @Override
    public List<Cidade> listarTudo() throws ExcecaoPersistencia {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM cidade ORDER BY nom_cidade";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            ArrayList<Cidade> listarTudo = null;
            InterfaceEstadoDAO estadoDAO = new EstadoDAO();
            
            if (rs.next()) {
                listarTudo = new ArrayList<>();
                do {
                    Cidade cidade = new Cidade();
                    cidade.setCodCidade(rs.getLong("cod_Cidade"));
                    
                    Estado estado = estadoDAO.consultarPorId(rs.getLong("cod_Estado"));
                    cidade.setEstado(estado);
                    
                    cidade.setNomCidade(rs.getString("nom_cidade"));

                    listarTudo.add(cidade);
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
