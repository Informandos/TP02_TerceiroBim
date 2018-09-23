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
import model.dao.interfaces.InterfaceTipoAtracaoDAO;
import model.domain.TipoAtracao;
import util.db.ConnectionManager;
import util.db.exception.ExcecaoPersistencia;



public class TipoAtracaoDAO implements InterfaceTipoAtracaoDAO {

    @Override
    public Long inserir(TipoAtracao tipoAtracao) throws ExcecaoPersistencia {
        if(tipoAtracao == null){
            throw new ExcecaoPersistencia("Tag Atração não pode ser null");
        }
        
        Long codTipoAtracao = null;
        
        try {
            Connection conn = ConnectionManager.getInstance().getConnection();

            String sql = "INSERT INTO tipo_atracao (cod_tipo_atracao, desc_tipo_atrcao) VALUES(?, ?)";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            
            pstmt.setLong(1,tipoAtracao.getCodTipoAtracao());
            pstmt.setString(2,tipoAtracao.getDescTipoAtracao());
            
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                codTipoAtracao = rs.getLong("seq_tag_diario");
            }

            rs.close();
            pstmt.close();
            conn.close();

            return codTipoAtracao;
        }catch (ClassNotFoundException | SQLException e){
            throw new ExcecaoPersistencia(e.getMessage(), e);
        }
    }

    @Override
    public boolean atualizar(TipoAtracao tipoAtracao) throws ExcecaoPersistencia {
        try {
            Connection conn = ConnectionManager.getInstance().getConnection();

            String sql = "UPDATE tipo_atracao SET cod_tipo_atracao = ?, desc_tipo_atrcao = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            
            pstmt.setLong(1,tipoAtracao.getCodTipoAtracao());
            pstmt.setString(2,tipoAtracao.getDescTipoAtracao());
            
            ResultSet rs = pstmt.executeQuery();

            rs.close();
            pstmt.close();
            conn.close();

            return true;
        }catch (ClassNotFoundException | SQLException e){
            throw new ExcecaoPersistencia(e.getMessage(), e);
        }
    }

    @Override
    public boolean deletar(TipoAtracao tipoAtracao) throws ExcecaoPersistencia {
        try {
            Connection conn = ConnectionManager.getInstance().getConnection();

            String sql = "DELETE FROM tipo_atracao WHERE cod_tipo_atracao = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            pstmt.setLong(1,tipoAtracao.getCodTipoAtracao());
            
            ResultSet rs = pstmt.executeQuery();

            rs.close();
            pstmt.close();
            conn.close();

            return true;
        }catch (ClassNotFoundException | SQLException e){
            throw new ExcecaoPersistencia(e.getMessage(), e);
        }
    }

    @Override
    public TipoAtracao consultarPorId(Long codTipoAtracao) throws ExcecaoPersistencia {
        try {
            Connection conn = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM tipo_datracao WHERE cod_tipo_atrcao = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, codTipoAtracao);
            ResultSet rs = pstmt.executeQuery();

            TipoAtracao tipoA = null;
            if (rs.next()) {
                tipoA = new TipoAtracao();
                tipoA.setCodTipoAtracao(rs.getLong("cod_tipo_atracao"));
                tipoA.setDescTipoAtracao(rs.getString("desc_tipo_atracao"));
            }

            rs.close();
            pstmt.close();
            conn.close();

            return tipoA;
        } catch (ClassNotFoundException | SQLException e) {
            throw new ExcecaoPersistencia(e.getMessage(), e);
        }
    }

    @Override
    public TipoAtracao consultarPorNome(String descTipoAtracao) throws ExcecaoPersistencia {
        try {
            Connection conn = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM tipo_datracao WHERE desc_tipo_atrcao = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, descTipoAtracao);
            ResultSet rs = pstmt.executeQuery();

            TipoAtracao tipoA = null;
            if (rs.next()) {
                tipoA = new TipoAtracao();
                tipoA.setCodTipoAtracao(rs.getLong("cod_tipo_atracao"));
                tipoA.setDescTipoAtracao(rs.getString("desc_tipo_atracao"));
            }

            rs.close();
            pstmt.close();
            conn.close();

            return tipoA;
        } catch (ClassNotFoundException | SQLException e) {
            throw new ExcecaoPersistencia(e.getMessage(), e);
        }
    }

    @Override
    public List<TipoAtracao> listarTudo() throws ExcecaoPersistencia {
        try {
            Connection conn = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM tag_diario ORDER BY 1";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            
            ArrayList<TipoAtracao> listarTudo = null;
            if (rs.next()) {
                listarTudo = new ArrayList<>();
                do {
                    TipoAtracao tipoA = new TipoAtracao();
                    tipoA.setCodTipoAtracao(rs.getLong("cod_tipo_atracao"));
                    tipoA.setDescTipoAtracao(rs.getString("desc_tipo_atracao"));

                    listarTudo.add(tipoA);
                } while (rs.next());

            }

            rs.close();
            pstmt.close();
            conn.close();

            return listarTudo;
        } catch (ClassNotFoundException | SQLException e) {
            throw new ExcecaoPersistencia(e.getMessage(), e);
        }
    }
    
}
