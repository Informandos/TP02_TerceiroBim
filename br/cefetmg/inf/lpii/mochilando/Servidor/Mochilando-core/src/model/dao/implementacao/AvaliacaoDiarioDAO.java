package model.dao.implementacao;

import model.dao.interfaces.InterfaceAvaliacaoDiarioDAO;
import model.domain.AvaliacaoDiario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.dao.interfaces.InterfaceDiarioDAO;
import model.dao.interfaces.InterfaceUsuarioDAO;
import model.domain.Diario;
import model.domain.Usuario;
import util.db.exception.ExcecaoPersistencia;
import util.db.ConnectionManager;

public class AvaliacaoDiarioDAO implements InterfaceAvaliacaoDiarioDAO{

    @Override
    public Long inserir(AvaliacaoDiario avaliacaoDiario) throws ExcecaoPersistencia {
         if(avaliacaoDiario == null){
            throw new ExcecaoPersistencia("Avaliação do Diário não pode ser null");
        }
        
        Long seq_avaliacaoD = null;
        
        try{
            Connection conn = ConnectionManager.getInstance().getConnection();
            
            String sql = "INSERT INTO atracao_diario (seq_avaliacao, cod_diario, cod_usuario, avaliacao) VALUES (?,?,?,?) RETURNING seq_avaliacao";
            
            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            pstmt.setLong(1, avaliacaoDiario.getSeqAvaliacao());
            pstmt.setLong(2, avaliacaoDiario.getDiario().getCodDiario());
            pstmt.setLong(3, avaliacaoDiario.getUsuario().getCodUsuario());
            pstmt.setString(4, avaliacaoDiario.getAvaliacao());
            
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                seq_avaliacaoD = rs.getLong("seq_avaliaco");
            }

            rs.close();
            pstmt.close();
            conn.close();
            return seq_avaliacaoD;
            
        }catch (ClassNotFoundException | SQLException e){
            
            throw new ExcecaoPersistencia(e.getMessage(), e);
            
        }
    }

    @Override
    public boolean atualizar(AvaliacaoDiario avaliacaoDiario) throws ExcecaoPersistencia {
        try{
            Connection conn = ConnectionManager.getInstance().getConnection();
            
            String sql = "UPDATE avaliacao_diario SET seq_avaliaco = ?, seq_diario = ?, cod_usuario = ?, avaliacao = ? WHERE seq_avaliacao = ?";
            
            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            pstmt.setLong(1, avaliacaoDiario.getSeqAvaliacao());
            pstmt.setLong(2, avaliacaoDiario.getDiario().getCodDiario());
            pstmt.setLong(3, avaliacaoDiario.getUsuario().getCodUsuario());
            pstmt.setString(4, avaliacaoDiario.getAvaliacao());
            
            pstmt.executeUpdate();

            pstmt.close();
            conn.close();
            return true;
            
        }catch (ClassNotFoundException | SQLException e){
            
            throw new ExcecaoPersistencia(e.getMessage(), e);
            
        }
    }

    @Override
    public boolean deletar(AvaliacaoDiario avaliacaoDiario) throws ExcecaoPersistencia {
        try {
            Connection conn = ConnectionManager.getInstance().getConnection();

            String sql = "DELETE FROM avaliacao_diario WHERE seq_avaliaco = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, avaliacaoDiario.getSeqAvaliacao());
            pstmt.executeUpdate();

            pstmt.close();
            conn.close();
            return true;
        } catch (ClassNotFoundException | SQLException e) {

            throw new ExcecaoPersistencia(e.getMessage(), e);

        }
    }

    @Override
    public AvaliacaoDiario consultarPorId(Long seqAvaliacao) throws ExcecaoPersistencia{
        try {
            Connection conn = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM avaliacao_diario WHERE seq_avaliacao = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, seqAvaliacao);
            ResultSet rs = pstmt.executeQuery();

            AvaliacaoDiario avaliacaoDiario = null;
            InterfaceUsuarioDAO usuarioDAO = new UsuarioDAO();
            InterfaceDiarioDAO diarioDAO = new DiarioDAO();
            if (rs.next()) {
                avaliacaoDiario = new AvaliacaoDiario();
                avaliacaoDiario.setSeqAvaliacao(rs.getLong("seq_avaliacao"));
                Diario diario = diarioDAO.consultarPorId(rs.getLong("cod_diario"));
                avaliacaoDiario.setDiario(diario);
                Usuario usuario = usuarioDAO.consultarPorId(rs.getLong("cod_usuario"));
                avaliacaoDiario.setUsuario(usuario);
                avaliacaoDiario.setAvaliacao(rs.getString("avaliacao"));
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
    public int consultarNumAvPositivas(Long codDiario) throws ExcecaoPersistencia {
        try {
            Connection conn = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM avaliacao_comentario WHERE cod_diario = ? AND avaliacao = 'P'";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, codDiario);
            ResultSet rs = pstmt.executeQuery();

            AvaliacaoDiario avaliacaoDiario = null;
            InterfaceUsuarioDAO usuarioDAO = new UsuarioDAO();
            InterfaceDiarioDAO diarioDAO = new DiarioDAO();
            if (rs.next()) {
                avaliacaoDiario = new AvaliacaoDiario();
                avaliacaoDiario.setSeqAvaliacao(rs.getLong("seq_avaliacao"));
                Diario diario = diarioDAO.consultarPorId(rs.getLong("cod_diario"));
                avaliacaoDiario.setDiario(diario);
                Usuario usuario = usuarioDAO.consultarPorId(rs.getLong("cod_usuario"));
                avaliacaoDiario.setUsuario(usuario);
                avaliacaoDiario.setAvaliacao(rs.getString("avaliacao"));
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
    public int consultarNumAvNegativas(Long codDiario) throws ExcecaoPersistencia {
        try {
            Connection conn = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM avaliacao_comentario WHERE cod_diario = ? AND avaliacao = 'N'";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, codDiario);
            ResultSet rs = pstmt.executeQuery();

            AvaliacaoDiario avaliacaoDiario = null;
            InterfaceUsuarioDAO usuarioDAO = new UsuarioDAO();
            InterfaceDiarioDAO diarioDAO = new DiarioDAO();
            if (rs.next()) {
                avaliacaoDiario = new AvaliacaoDiario();
                avaliacaoDiario.setSeqAvaliacao(rs.getLong("seq_avaliacao"));
                Diario diario = diarioDAO.consultarPorId(rs.getLong("cod_diario"));
                avaliacaoDiario.setDiario(diario);
                Usuario usuario = usuarioDAO.consultarPorId(rs.getLong("cod_usuario"));
                avaliacaoDiario.setUsuario(usuario);
                avaliacaoDiario.setAvaliacao(rs.getString("avaliacao"));
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
    public List<AvaliacaoDiario> listarPorDiario(Long codDiario) throws ExcecaoPersistencia {
        try {
            Connection conn = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM atracao ORDER BY nom_atracao";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            ArrayList<AvaliacaoDiario> listarPorDiario = null;
            AvaliacaoDiario avaliacaoDiario = null;
            InterfaceUsuarioDAO usuarioDAO = new UsuarioDAO();
            InterfaceDiarioDAO diarioDAO = new DiarioDAO();
            if (rs.next()) {
                listarPorDiario = new ArrayList<>();
                do {
                    AvaliacaoDiario atracao = new AvaliacaoDiario();
                    avaliacaoDiario.setSeqAvaliacao(rs.getLong("seq_avaliacao"));
                    Diario diario = diarioDAO.consultarPorId(rs.getLong("cod_diario"));
                    avaliacaoDiario.setDiario(diario);
                    Usuario usuario = usuarioDAO.consultarPorId(rs.getLong("cod_usuario"));
                    avaliacaoDiario.setUsuario(usuario);
                    avaliacaoDiario.setAvaliacao(rs.getString("avaliacao"));
                    
                    listarPorDiario.add(avaliacaoDiario);
                } while (rs.next());

            }

            rs.close();
            pstmt.close();
            conn.close();

            return listarPorDiario;
        } catch (ClassNotFoundException | SQLException e) {

            throw new ExcecaoPersistencia(e.getMessage(), e);

        }
    }
    

    @Override
    public List<AvaliacaoDiario> listarTudo() throws ExcecaoPersistencia {
        try {
            Connection conn = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM atracao ORDER BY nom_atracao";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            ArrayList<AvaliacaoDiario> listarTudo = null;
            AvaliacaoDiario avaliacaoDiario = null;
            InterfaceUsuarioDAO usuarioDAO = new UsuarioDAO();
            InterfaceDiarioDAO diarioDAO = new DiarioDAO();
            if (rs.next()) {
                listarTudo = new ArrayList<>();
                do {
                    AvaliacaoDiario atracao = new AvaliacaoDiario();
                    avaliacaoDiario.setSeqAvaliacao(rs.getLong("seq_avaliacao"));
                    Diario diario = diarioDAO.consultarPorId(rs.getLong("cod_diario"));
                    avaliacaoDiario.setDiario(diario);
                    Usuario usuario = usuarioDAO.consultarPorId(rs.getLong("cod_usuario"));
                    avaliacaoDiario.setUsuario(usuario);
                    avaliacaoDiario.setAvaliacao(rs.getString("avaliacao"));
                    
                    listarTudo.add(avaliacaoDiario);
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
