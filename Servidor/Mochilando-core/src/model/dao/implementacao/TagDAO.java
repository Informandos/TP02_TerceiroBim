/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.implementacao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.dao.interfaces.InterfaceTagDAO;
import model.domain.Tag;
import util.db.exception.ExcecaoPersistencia;
import util.db.ConnectionManager;

/**
 *
 * @author User
 */
public class TagDAO implements InterfaceTagDAO {

    @Override
    public Long inserir(Tag tag) throws ExcecaoPersistencia {
         try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "INSERT INTO tag (desc_tag) VALUES(?)";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            
            
            pstmt.setString(1,tag.getDescTag());
           
            
            ResultSet rs = pstmt.executeQuery();

            Long id = null;
            if (rs.next()) {
                id = new Long(rs.getLong("cod_tag"));
                tag.setCodTag(id);
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
    public boolean atualizar(Tag tag) throws ExcecaoPersistencia {
        try{
         Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "UPDATE tag "
                    + "   SET  desc_tag = ?, "                      
                    + " WHERE cod_tag = ?;";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            Long codTag = tag.getCodTag();
                       
            pstmt.setString(1,tag.getDescTag());
            pstmt.setLong(2,tag.getCodTag());
            
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
    public boolean deletar(Tag tag) throws ExcecaoPersistencia {
        try{
        Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "DELETE FROM tag WHERE cod_tag = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1,tag.getCodTag());
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
    public Tag consultarPorId(Long codTag) throws ExcecaoPersistencia {
       try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM tag WHERE cod_tag = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, codTag);
            ResultSet rs = pstmt.executeQuery();

            Tag tag = null;
             
            if (rs.next()) {
                
                    tag = new Tag();
                    tag.setCodTag(rs.getLong("cod_tag"));
                   
                    tag.setDescTag(rs.getString("desc_tag"));
            }

            rs.close();
            pstmt.close();
            connection.close();

            return tag;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExcecaoPersistencia(e.getMessage(), e);
        }
    }

    @Override
    public Tag consultarPorNome(String descTag) throws ExcecaoPersistencia {
           try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM tag WHERE desc_tag = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, descTag);
            ResultSet rs = pstmt.executeQuery();

            Tag tag = null;
             
            if (rs.next()) {
                
                    tag = new Tag();
                    tag.setCodTag(rs.getLong("cod_tag"));
                   
                    tag.setDescTag(rs.getString("desc_tag"));
            }

            rs.close();
            pstmt.close();
            connection.close();

            return tag;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExcecaoPersistencia(e.getMessage(), e);
        }
    }

    @Override
    public List<Tag> listarTudo() throws ExcecaoPersistencia {
        try{
         Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM tag ORDER BY cod_tag";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            
            ArrayList<Tag> listAll = null;
            
            if (rs.next()) {
                listAll = new ArrayList<>();
                do {
                    Tag tag = new Tag();
                    tag.setCodTag(rs.getLong("cod_tag"));
                    
                    tag.setDescTag(rs.getString("desc_tag"));
                    
                    listAll.add(tag);
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
    
}
