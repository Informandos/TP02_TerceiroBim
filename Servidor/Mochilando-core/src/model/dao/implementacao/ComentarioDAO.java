package model.dao.implementacao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.dao.interfaces.InterfaceComentarioDAO;
import model.dao.interfaces.InterfaceDiarioDAO;
import model.dao.interfaces.InterfaceUsuarioDAO;
import model.domain.Comentario;
import model.domain.Diario;
import model.domain.Usuario;
import util.db.ConnectionManager;
import util.db.exception.ExcecaoPersistencia;

public class ComentarioDAO implements InterfaceComentarioDAO {

    @Override
    public Long inserir(Comentario comentario) throws ExcecaoPersistencia {
        if (comentario == null) {
            throw new ExcecaoPersistencia("Cidade não pode ser null");
        }
        
        Long cod_cidade = null;
        
        try{
            Connection conn = ConnectionManager.getInstance().getConnection();
            
            String sql = "INSERT INTO comentario (seq_comentario, cod_diario, cod_autor_diario, dat_publicacao, txt_comentario) VALUES (?,?,?,?,?) RETURNING seq_comentario";
            
            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            pstmt.setLong(1, comentario.getSeqComentario());
            pstmt.setLong(2, comentario.getDiario().getCodDiario());
            pstmt.setLong(3, comentario.getUsuario().getCodUsuario());
            pstmt.setDate(4, new java.sql.Date(comentario.getDatPublicacao().getTime()));
            pstmt.setString(5, comentario.getTxtComentario());
            
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                cod_cidade = rs.getLong("cod_cidade");
            }

            rs.close();
            pstmt.close();
            conn.close();
            return cod_cidade;
            
        }catch (ClassNotFoundException | SQLException e){
            throw new ExcecaoPersistencia(e.getMessage(), e);
        }
    }

    @Override
    public boolean atualizar(Comentario comentario) throws ExcecaoPersistencia {
        try{
            Connection conn = ConnectionManager.getInstance().getConnection();
            
            String sql = "UPDATE comentario SET seq_comentario = ?, cod_diario = ?, cod_autor_diario = ?, dat_publicacao = ?, txt_comentario = ? RETURNING seq_comentario";
            
            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            pstmt.setLong(1, comentario.getSeqComentario());
            pstmt.setLong(2, comentario.getDiario().getCodDiario());
            pstmt.setLong(3, comentario.getUsuario().getCodUsuario());
            pstmt.setDate(4, new java.sql.Date(comentario.getDatPublicacao().getTime()));
            pstmt.setString(5, comentario.getTxtComentario());
            
            pstmt.executeUpdate();

            pstmt.close();
            conn.close();
            return true;
            
        }catch (ClassNotFoundException | SQLException e){
            throw new ExcecaoPersistencia(e.getMessage(), e);
        }
    }

    @Override
    public boolean deletar(Comentario comentario) throws ExcecaoPersistencia {
        try{
            Connection conn = ConnectionManager.getInstance().getConnection();
            
            String sql = "DELETE FROM comentario WHERE seq_comentario = ?";
            
            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            pstmt.setLong(1, comentario.getSeqComentario());
            
            pstmt.executeUpdate();

            pstmt.close();
            conn.close();
            return true;
            
        }catch (ClassNotFoundException | SQLException e){
            throw new ExcecaoPersistencia(e.getMessage(), e);
        }
    }

    @Override
    public Comentario consultarPorId(Long seqComentario) throws ExcecaoPersistencia {
        try {
            Connection conn = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM comentario WHERE seq_comentario = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, seqComentario);
            ResultSet rs = pstmt.executeQuery();

            Comentario comentario = null;
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            DiarioDAO diarioDAO = new DiarioDAO();
            if (rs.next()) {
                comentario = new Comentario();
                comentario.setSeqComentario(rs.getLong("seq_comentario"));
                Diario diario = diarioDAO.consultarPorId(rs.getLong("cod_diario"));
                comentario.setDiario(diario);
                Usuario usuario = usuarioDAO.consultarPorId(rs.getLong("cod_autor_diario"));
                comentario.setUsuario(usuario);
                comentario.setDatPublicacao(rs.getDate("dat_publicacao"));
                comentario.setTxtComentario(rs.getString("txt_comentario"));
            }

            rs.close();
            pstmt.close();
            conn.close();

            return null;
        } catch (ClassNotFoundException | SQLException e) {
            throw new ExcecaoPersistencia(e.getMessage(), e);
        }
    }

    @Override
    public List<Comentario> listarPorCodDiario(Long codDiario) throws ExcecaoPersistencia {
        try {
            Connection conn = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM comentario WHERE cod_diario = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, codDiario);
            ResultSet rs = pstmt.executeQuery();

            List<Comentario> listarporCodDiario =null;
            InterfaceDiarioDAO diarioDAO = new DiarioDAO();
            InterfaceUsuarioDAO usuarioDAO = new UsuarioDAO();
            if (rs.next()) {
                listarporCodDiario = new ArrayList();
                do{
                    Comentario comentario = new Comentario();
                    comentario.setSeqComentario(rs.getLong("seq_comentario"));
                    Diario diario = diarioDAO.consultarPorId(rs.getLong("cod_diario"));
                    comentario.setDiario(diario);
                    Usuario usuario = usuarioDAO.consultarPorId(rs.getLong("cod_autor_diario"));
                    comentario.setUsuario(usuario);
                    comentario.setDatPublicacao(rs.getDate("dat_publicacao"));
                    comentario.setTxtComentario(rs.getString("txt_comentario"));

                    listarporCodDiario.add(comentario);
                } while(rs.next());

            }

            rs.close();
            pstmt.close();
            conn.close();

            return null;
        } catch (ClassNotFoundException | SQLException e) {
            throw new ExcecaoPersistencia(e.getMessage(), e);
        }
    }

    @Override
    public List<Comentario> listarTudo() throws ExcecaoPersistencia {
        try {
            Connection conn = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM comentario ORDER BY 1";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            List<Comentario> listarporCodDiario =null;
            InterfaceDiarioDAO diarioDAO = new DiarioDAO();
            InterfaceUsuarioDAO usuarioDAO = new UsuarioDAO();
            if (rs.next()) {
                listarporCodDiario = new ArrayList();
                do{
                    Comentario comentario = new Comentario();
                    comentario.setSeqComentario(rs.getLong("seq_comentario"));
                    Diario diario = diarioDAO.consultarPorId(rs.getLong("cod_diario"));
                    comentario.setDiario(diario);
                    Usuario usuario = usuarioDAO.consultarPorId(rs.getLong("cod_autor_diario"));
                    comentario.setUsuario(usuario);
                    comentario.setDatPublicacao(rs.getDate("dat_publicacao"));
                    comentario.setTxtComentario(rs.getString("txt_comentario"));

                    listarporCodDiario.add(comentario);
                } while(rs.next());

            }

            rs.close();
            pstmt.close();
            conn.close();

            return null;
        } catch (ClassNotFoundException | SQLException e) {
            throw new ExcecaoPersistencia(e.getMessage(), e);
        }
    }
    
}
