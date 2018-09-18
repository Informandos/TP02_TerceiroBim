package model.dao.implementacao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.dao.interfaces.InterfaceTagDAO;
import model.dao.interfaces.InterfaceUsuarioDAO;
import util.db.exception.ExcecaoPersistencia;
import util.db.ConnectionManager;
import model.dao.interfaces.InterfaceUsuarioTagDAO;
import model.domain.Tag;
import model.domain.Usuario;
import model.domain.UsuarioTag;

public class UsuarioTagDAO implements InterfaceUsuarioTagDAO{

    @Override
    public Long inserir(UsuarioTag usuarioTag) throws ExcecaoPersistencia {
        if(usuarioTag == null){
            throw new ExcecaoPersistencia("Tag Diário não pode ser null");
        }
        Long seqTagUsuario = null;
        
        try {
            Connection conn = ConnectionManager.getInstance().getConnection();

            String sql = "INSERT INTO tag_diario (seq_usuario_tag, cod_usuario, cod_tag) VALUES(?, ?, ?)";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            
            pstmt.setLong(1, usuarioTag.getSeqUsuarioTag());
            pstmt.setLong(2,usuarioTag.getUsuario().getCodUsuario());
            pstmt.setLong(3,usuarioTag.getTag().getCodTag());
            
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                seqTagUsuario = rs.getLong("seq_tag_diario");
            }

            rs.close();
            pstmt.close();
            conn.close();

            return seqTagUsuario;
        }catch (ClassNotFoundException | SQLException e){
            throw new ExcecaoPersistencia(e.getMessage(), e);
        }
    }

    @Override
    public boolean atualizar(UsuarioTag usuarioTag) throws ExcecaoPersistencia {
        try {
            Connection conn = ConnectionManager.getInstance().getConnection();

            String sql = "UPDATE tag_diario SET seq_usuario_tag = ?, cod_usuario = ?, cod_tag = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            
            pstmt.setLong(1, usuarioTag.getSeqUsuarioTag());
            pstmt.setLong(2,usuarioTag.getUsuario().getCodUsuario());
            pstmt.setLong(3,usuarioTag.getTag().getCodTag());
            
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
    public boolean deletar(UsuarioTag usuarioTag) throws ExcecaoPersistencia {
        try {
            Connection conn = ConnectionManager.getInstance().getConnection();

            String sql = "DELETE FROM tag_diario WHERE seq_usuario_tag = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            pstmt.setLong(1, usuarioTag.getSeqUsuarioTag());
            
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
    public UsuarioTag consultarPorId(Long seqUsuarioTag) throws ExcecaoPersistencia {
        try {
            Connection conn = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM tag_usuario WHERE seq_tag_usuario = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, seqUsuarioTag);
            ResultSet rs = pstmt.executeQuery();

            UsuarioTag tagU = null;
            InterfaceUsuarioDAO usuarioDAO = new UsuarioDAO();
            InterfaceTagDAO tagDAO = new TagDAO();
            if (rs.next()) {
                tagU = new UsuarioTag();
                tagU.setSeqUsuarioTag(rs.getLong("seq_usuario_tag"));
                Usuario usuario = usuarioDAO.consultarPorId(rs.getLong("cod_usuario"));
                tagU.setUsuario(usuario);
                Tag tag = tagDAO.consultarPorId(rs.getLong("cod_tag"));
                tagU.setTag(tag);
            }

            rs.close();
            pstmt.close();
            conn.close();

            return tagU;
        } catch (ClassNotFoundException | SQLException e) {
            throw new ExcecaoPersistencia(e.getMessage(), e);
        }
    }

    @Override
    public List<UsuarioTag> listarTudo() throws ExcecaoPersistencia {
        try {
            Connection conn = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM usuario_tag ORDER BY 1";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            
            InterfaceUsuarioDAO usuarioDAO = new UsuarioDAO();
            InterfaceTagDAO tagDAO = new TagDAO();
            ArrayList<UsuarioTag> listarTudo = null;
            if (rs.next()) {
                listarTudo = new ArrayList<>();
                do {
                    UsuarioTag tagU = new UsuarioTag();
                    tagU.setSeqUsuarioTag(rs.getLong("seq_usuario_tag"));
                    Usuario usuario = usuarioDAO.consultarPorId(rs.getLong("cod_usuario"));
                    tagU.setUsuario(usuario);
                    Tag tag = tagDAO.consultarPorId(rs.getLong("cod_tag"));
                    tagU.setTag(tag);

                    listarTudo.add(tagU);
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
    public List<UsuarioTag> listarPorCodUsuario(Long codUsuario) throws ExcecaoPersistencia {
        try {
            Connection conn = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM usuario_tag WHERE cod_usuario = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, codUsuario);
            ResultSet rs = pstmt.executeQuery();
            
            InterfaceUsuarioDAO usuarioDAO = new UsuarioDAO();
            InterfaceTagDAO tagDAO = new TagDAO();
            ArrayList<UsuarioTag> listarTudo = null;
            if (rs.next()) {
                listarTudo = new ArrayList<>();
                do {
                    UsuarioTag tagU = new UsuarioTag();
                    tagU.setSeqUsuarioTag(rs.getLong("seq_usuario_tag"));
                    Usuario usuario = usuarioDAO.consultarPorId(rs.getLong("cod_usuario"));
                    tagU.setUsuario(usuario);
                    Tag tag = tagDAO.consultarPorId(rs.getLong("cod_tag"));
                    tagU.setTag(tag);

                    listarTudo.add(tagU);
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
    public List<UsuarioTag> listarPorCodTag(Long codTag) throws ExcecaoPersistencia {
       try {
            Connection conn = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM usuario_tag WHERE cod_tag = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, codTag);
            ResultSet rs = pstmt.executeQuery();
            
            InterfaceUsuarioDAO usuarioDAO = new UsuarioDAO();
            InterfaceTagDAO tagDAO = new TagDAO();
            ArrayList<UsuarioTag> listarPorCodTag = null;
            
            if (rs.next()) {
                listarPorCodTag = new ArrayList<>();
                do {
                    UsuarioTag usuarioTag = new UsuarioTag();
                    usuarioTag.setSeqUsuarioTag(rs.getLong("seq_usuario_tag"));
                    Usuario usuario = usuarioDAO.consultarPorId(rs.getLong("cod_usuario"));
                    usuarioTag.setUsuario(usuario);
                    Tag tag = tagDAO.consultarPorId(rs.getLong("cod_tag"));
                    usuarioTag.setTag(tag);

                    listarPorCodTag.add(usuarioTag);
                } while (rs.next());

            }

            rs.close();
            pstmt.close();
            conn.close();

            return listarPorCodTag;
        } catch (ClassNotFoundException | SQLException e) {
            throw new ExcecaoPersistencia(e.getMessage(), e);
        }
    }
    
}
