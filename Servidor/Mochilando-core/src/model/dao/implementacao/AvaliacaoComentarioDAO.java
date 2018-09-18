package model.dao.implementacao;

import model.dao.interfaces.InterfaceAvaliacaoComentarioDAO;
import model.domain.AvaliacaoComentario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.dao.interfaces.InterfaceComentarioDAO;
import model.dao.interfaces.InterfaceUsuarioDAO;
import model.domain.Usuario;
import model.domain.Comentario;
import util.db.exception.ExcecaoPersistencia;
import util.db.ConnectionManager;

public class AvaliacaoComentarioDAO implements InterfaceAvaliacaoComentarioDAO{

    @Override
    public Long inserir(AvaliacaoComentario avaliacaoComenario) throws ExcecaoPersistencia {
        if(avaliacaoComenario == null){
            throw new ExcecaoPersistencia("Avaliação do Comentário não pode ser null");
        }
        
        Long seq_avaliacaoC = null;
        
        try{
            Connection conn = ConnectionManager.getInstance().getConnection();
            
            String sql = "INSERT INTO atracao_comentario (seq_avaliacao, seq_cometario, cod_usuario, avaliacao) VALUES (?,?,?,?) RETURNING seq_avaliacao";
            
            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            pstmt.setLong(1, avaliacaoComenario.getSeqAvaliacao());
            pstmt.setLong(2, avaliacaoComenario.getComentario().getSeqComentario());
            pstmt.setLong(3, avaliacaoComenario.getUsuario().getCodUsuario());
            pstmt.setString(4, avaliacaoComenario.getAvaliacao());
            
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                seq_avaliacaoC = rs.getLong("seq_avaliaco");
            }

            rs.close();
            pstmt.close();
            conn.close();
            return seq_avaliacaoC;
            
        }catch (ClassNotFoundException | SQLException e){
            
            throw new ExcecaoPersistencia(e.getMessage(), e);
            
        }
    }

    @Override
    public boolean atualizar(AvaliacaoComentario avaliacaoComenario) throws ExcecaoPersistencia {
        try{
            Connection conn = ConnectionManager.getInstance().getConnection();
            
            String sql = "UPDATE avaliacao_comentario SET seq_avaliaco = ?, seq_comentario = ?, cod_usuario = ?, avaliacao = ? WHERE seq_avaliacao = ?";
            
            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            pstmt.setLong(1, avaliacaoComenario.getSeqAvaliacao());
            pstmt.setLong(2, avaliacaoComenario.getComentario().getSeqComentario());
            pstmt.setLong(3, avaliacaoComenario.getUsuario().getCodUsuario());
            pstmt.setString(4, avaliacaoComenario.getAvaliacao());
            
            pstmt.executeUpdate();

            pstmt.close();
            conn.close();
            return true;
            
        }catch (ClassNotFoundException | SQLException e){
            
            throw new ExcecaoPersistencia(e.getMessage(), e);
            
        }
    }

    @Override
    public boolean deletar(AvaliacaoComentario avaliacaoComenario) throws ExcecaoPersistencia {
        try {
            Connection conn = ConnectionManager.getInstance().getConnection();

            String sql = "DELETE FROM avaliacao_comentario WHERE seq_avaliaco = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, avaliacaoComenario.getSeqAvaliacao());
            pstmt.executeUpdate();

            pstmt.close();
            conn.close();
            return true;
        } catch (ClassNotFoundException | SQLException e) {

            throw new ExcecaoPersistencia(e.getMessage(), e);

        }
    }

    @Override
    public AvaliacaoComentario consultarPorId(Long seqAvaliacao) throws ExcecaoPersistencia {
        try {
            Connection conn = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM avaliacao_comentario WHERE seq_avaliacao = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, seqAvaliacao);
            ResultSet rs = pstmt.executeQuery();

            AvaliacaoComentario avaliacaoComentario = null;
            InterfaceComentarioDAO comentarioDAO = new ComentarioDAO();
            InterfaceUsuarioDAO usuarioDAO = new UsuarioDAO();
            if (rs.next()) { 
                avaliacaoComentario = new AvaliacaoComentario();
                    Comentario comentario = comentarioDAO.consultarPorId(rs.getLong("cod_comentario"));
                    avaliacaoComentario.setComentario(comentario);
                    avaliacaoComentario.setSeqAvaliacao(rs.getLong("seq_avaliacao"));
                    Usuario usuario = usuarioDAO.consultarPorId(rs.getLong("cod_usuario"));
                    avaliacaoComentario.setUsuario(usuario);
                    avaliacaoComentario.setAvaliacao(rs.getString("avaliacao"));
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
    public int consultarNumAvPositivas(Long seqComentario) throws ExcecaoPersistencia {
        try {
            Connection conn = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM avaliacao_comentario WHERE seq_comentario = ? AND avaliacao = 'P'";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, seqComentario);
            ResultSet rs = pstmt.executeQuery();

            AvaliacaoComentario avaliacaoComentario = null;
            InterfaceComentarioDAO comentarioDAO = new ComentarioDAO();
            InterfaceUsuarioDAO usuarioDAO = new UsuarioDAO();
            if (rs.next()) { 
                avaliacaoComentario = new AvaliacaoComentario();
                Comentario comentario = comentarioDAO.consultarPorId(rs.getLong("cod_comentario"));
                avaliacaoComentario.setComentario(comentario);
                avaliacaoComentario.setSeqAvaliacao(rs.getLong("seq_avaliacao"));
                Usuario usuario = usuarioDAO.consultarPorId(rs.getLong("cod_usuario"));
                avaliacaoComentario.setUsuario(usuario);
                avaliacaoComentario.setAvaliacao(rs.getString("avaliacao"));
            }

            rs.close();
            pstmt.close();
            conn.close();

            return 0;
        } catch (ClassNotFoundException | SQLException e) {

            throw new ExcecaoPersistencia(e.getMessage(), e);

        }
    }

    @Override
    public int consultarNumAvNegativas(Long seqComentario) throws ExcecaoPersistencia {
        try {
            Connection conn = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM avaliacao_comentario WHERE seq_comentario = ? AND avaliacao = 'N'";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, seqComentario);
            ResultSet rs = pstmt.executeQuery();

            AvaliacaoComentario avaliacaoComentario = null;
            InterfaceComentarioDAO comentarioDAO = new ComentarioDAO();
            InterfaceUsuarioDAO usuarioDAO = new UsuarioDAO();
            if (rs.next()) { 
                avaliacaoComentario = new AvaliacaoComentario();
                Comentario comentario = comentarioDAO.consultarPorId(rs.getLong("cod_comentario"));
                avaliacaoComentario.setComentario(comentario);
                avaliacaoComentario.setSeqAvaliacao(rs.getLong("seq_avaliacao"));
                Usuario usuario = usuarioDAO.consultarPorId(rs.getLong("cod_usuario"));
                avaliacaoComentario.setUsuario(usuario);
                avaliacaoComentario.setAvaliacao(rs.getString("avaliacao"));
            }

            rs.close();
            pstmt.close();
            conn.close();

            return 0;
        } catch (ClassNotFoundException | SQLException e) {

            throw new ExcecaoPersistencia(e.getMessage(), e);

        }
    }

    @Override
    public List<AvaliacaoComentario> listarPorDiario(Long codDiario) throws ExcecaoPersistencia {
        try {
            Connection conn = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM avaliacao_comentario a "+
                            "JOIN usuario u on a.cod_usuario = u.cod_usuario "+
                            "JOIN diario d on u.cod_diario = d.cod_diario "+
                            "WHERE d.cod_diario = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, codDiario);
            ResultSet rs = pstmt.executeQuery();

            List<AvaliacaoComentario> listarPorDiario = null;
            AvaliacaoComentario avaliacaoComentario = null;
            InterfaceComentarioDAO comentarioDAO = new ComentarioDAO();
            InterfaceUsuarioDAO usuarioDAO = new UsuarioDAO();
            if (rs.next()) {
                listarPorDiario = new ArrayList<>();
                do {
                    avaliacaoComentario = new AvaliacaoComentario();
                    Comentario comentario = comentarioDAO.consultarPorId(rs.getLong("cod_comentario"));
                    avaliacaoComentario.setComentario(comentario);
                    avaliacaoComentario.setSeqAvaliacao(rs.getLong("seq_avaliacao"));
                    Usuario usuario = usuarioDAO.consultarPorId(rs.getLong("cod_usuario"));
                    avaliacaoComentario.setUsuario(usuario);
                    avaliacaoComentario.setAvaliacao(rs.getString("avaliacao"));

                    listarPorDiario.add(avaliacaoComentario);
                } while (rs.next());

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
    public List<AvaliacaoComentario> listarTudo(Long seqAvaliacao) throws ExcecaoPersistencia {
        try {
            Connection conn = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM avaliacao_comentario WHERE seq_avaliacao = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            //pstmt.setLong(1, seq_atracao);
            ResultSet rs = pstmt.executeQuery();

            List<AvaliacaoComentario> listarTudo = null;
            AvaliacaoComentario avaliacaoComentario = null;
            InterfaceComentarioDAO comentarioDAO = new ComentarioDAO();
            InterfaceUsuarioDAO usuarioDAO = new UsuarioDAO();
            if (rs.next()) {
                listarTudo = new ArrayList<>();
                do {
                    avaliacaoComentario = new AvaliacaoComentario();
                    Comentario comentario = comentarioDAO.consultarPorId(rs.getLong("cod_comentario"));
                    avaliacaoComentario.setComentario(comentario);
                    avaliacaoComentario.setSeqAvaliacao(rs.getLong("seq_avaliacao"));
                    Usuario usuario = usuarioDAO.consultarPorId(rs.getLong("cod_usuario"));
                    avaliacaoComentario.setUsuario(usuario);
                    avaliacaoComentario.setAvaliacao(rs.getString("avaliacao"));

                    listarTudo.add(avaliacaoComentario);
                } while (rs.next());

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
