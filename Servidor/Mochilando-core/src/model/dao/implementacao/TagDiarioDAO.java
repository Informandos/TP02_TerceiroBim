package model.dao.implementacao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.dao.interfaces.InterfaceDiarioDAO;
import model.dao.interfaces.InterfaceTagDAO;
import util.db.exception.ExcecaoPersistencia;
import util.db.ConnectionManager;
import model.dao.interfaces.InterfaceTagDiarioDAO;
import model.domain.Diario;
import model.domain.Tag;
import model.domain.TagDiario;

public class TagDiarioDAO implements InterfaceTagDiarioDAO{

    @Override
    public Long inserir(TagDiario tagDiario) throws ExcecaoPersistencia {
        if(tagDiario == null){
            throw new ExcecaoPersistencia("Tag Diário não pode ser null");
        }
        Long seqTagDiario = null;
        
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "INSERT INTO tag_diario (seq_tag_diario, cod_diario, cod_tag) VALUES(?, ?, ?)";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            
            
            pstmt.setLong(1,tagDiario.getSeqTagDiario());
            pstmt.setLong(2,tagDiario.getDiario().getCodDiario());
            pstmt.setLong(3,tagDiario.getTag().getCodTag());
            
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                seqTagDiario = rs.getLong("seq_tag_diario");
            }

            rs.close();
            pstmt.close();
            connection.close();

            return seqTagDiario;
        }catch (ClassNotFoundException | SQLException e){
            throw new ExcecaoPersistencia(e.getMessage(), e);
        }
    }

    @Override
    public boolean atualizar(TagDiario tagDiario) throws ExcecaoPersistencia {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "UPDATE tag_diario SET seq_tag_diario = ?, cod_diario = ?, cod_tag = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            
            
            pstmt.setLong(1,tagDiario.getSeqTagDiario());
            pstmt.setLong(2,tagDiario.getDiario().getCodDiario());
            pstmt.setLong(3,tagDiario.getTag().getCodTag());
            
            ResultSet rs = pstmt.executeQuery();

            rs.close();
            pstmt.close();
            connection.close();

            return true;
        }catch (ClassNotFoundException | SQLException e){
            throw new ExcecaoPersistencia(e.getMessage(), e);
        }
    }

    @Override
    public boolean deletar(TagDiario tagDiario) throws ExcecaoPersistencia {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "DELETE FROM tag_diario WHERE seq_tag_diario = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            
            pstmt.setLong(1,tagDiario.getSeqTagDiario());
            
            ResultSet rs = pstmt.executeQuery();

            rs.close();
            pstmt.close();
            connection.close();

            return true;
        }catch (ClassNotFoundException | SQLException e){
            throw new ExcecaoPersistencia(e.getMessage(), e);
        }
    }

    @Override
    public TagDiario consultarPorId(Long seqTagDiario) throws ExcecaoPersistencia {
        try {
            Connection conn = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM tag_diario WHERE seq_tag_diario = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, seqTagDiario);
            ResultSet rs = pstmt.executeQuery();

            TagDiario tagD = null;
            InterfaceDiarioDAO diarioDAO = new DiarioDAO();
            InterfaceTagDAO tagDAO = new TagDAO();
            if (rs.next()) {
                tagD = new TagDiario();
                tagD.setSeqTagDiario(rs.getLong("seq_tag_diario"));
                Diario diario = diarioDAO.consultarPorId(rs.getLong("cod_diario"));
                tagD.setDiario(diario);
                Tag tag = tagDAO.consultarPorId(rs.getLong("cod_tag"));
                tagD.setTag(tag);
            }

            rs.close();
            pstmt.close();
            conn.close();

            return tagD;
        } catch (ClassNotFoundException | SQLException e) {
            throw new ExcecaoPersistencia(e.getMessage(), e);
        }
    }

    @Override
    public List<TagDiario> listarTudo() throws ExcecaoPersistencia {
        try {
            Connection conn = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM tag_diario ORDER BY 1";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            
            InterfaceDiarioDAO diarioDAO = new DiarioDAO();
            InterfaceTagDAO tagDAO = new TagDAO();
            ArrayList<TagDiario> listarTudo = null;
            if (rs.next()) {
                listarTudo = new ArrayList<>();
                do {
                    TagDiario tagD = new TagDiario();
                    tagD.setSeqTagDiario(rs.getLong("seq_tag_diario"));
                    Diario diario = diarioDAO.consultarPorId(rs.getLong("cod_diario"));
                    tagD.setDiario(diario);
                    Tag tag = tagDAO.consultarPorId(rs.getLong("cod_tag"));
                    tagD.setTag(tag);

                    listarTudo.add(tagD);
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

    @Override
    public List<TagDiario> listarPorCodDiario(Long codDiario) throws ExcecaoPersistencia {
        try {
            Connection conn = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM tag_diario WHERE cod_diario = ?";
            
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, codDiario);
            ResultSet rs = pstmt.executeQuery();
            
            InterfaceDiarioDAO diarioDAO = new DiarioDAO();
            InterfaceTagDAO tagDAO = new TagDAO();
            ArrayList<TagDiario> listarTudo = null;
            if (rs.next()) {
                listarTudo = new ArrayList<>();
                do {
                    TagDiario tagD = new TagDiario();
                    tagD.setSeqTagDiario(rs.getLong("seq_tag_diario"));
                    Diario diario = diarioDAO.consultarPorId(rs.getLong("cod_diario"));
                    tagD.setDiario(diario);
                    Tag tag = tagDAO.consultarPorId(rs.getLong("cod_tag"));
                    tagD.setTag(tag);

                    listarTudo.add(tagD);
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

    @Override
    public List<TagDiario> listarPorCodTag(Long codTag) throws ExcecaoPersistencia{
        try {
            Connection conn = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM tag_diario WHERE cod_diario = ?";
            
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, codTag);
            ResultSet rs = pstmt.executeQuery();
            
            InterfaceDiarioDAO diarioDAO = new DiarioDAO();
            InterfaceTagDAO tagDAO = new TagDAO();
            ArrayList<TagDiario> listarTudo = null;
            if (rs.next()) {
                listarTudo = new ArrayList<>();
                do {
                    TagDiario tagD = new TagDiario();
                    tagD.setSeqTagDiario(rs.getLong("seq_tag_diario"));
                    Diario diario = diarioDAO.consultarPorId(rs.getLong("cod_diario"));
                    tagD.setDiario(diario);
                    Tag tag = tagDAO.consultarPorId(rs.getLong("cod_tag"));
                    tagD.setTag(tag);

                    listarTudo.add(tagD);
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
