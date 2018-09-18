package model.dao.implementacao;


import java.util.List;
import model.dao.interfaces.InterfaceFotoDAO;
import model.domain.Foto;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.domain.Dia;
import util.db.exception.ExcecaoPersistencia;
import util.db.ConnectionFactory;
import util.db.ConnectionManager;
import model.dao.implementacao.DiaDAO;
import model.dao.interfaces.InterfaceDiaDAO;



public class FotoDAO implements InterfaceFotoDAO {

    
    @Override
    public Long inserir(Foto foto) throws ExcecaoPersistencia {

        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "INSERT INTO foto (seq_dia,foto) VALUES(?,?)";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            
            pstmt.setLong(1,foto.getDia().getSeqDia());
            pstmt.setByte(2,foto.getByteFoto());
            
            ResultSet rs = pstmt.executeQuery();

            Long id = null;
            if (rs.next()) {
                id = new Long(rs.getLong("seq_foto"));
                foto.setSeqFoto(id);
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
    public boolean atualizar(Foto foto) throws ExcecaoPersistencia {
        try {

            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "UPDATE foto "
                    + "   SET seq_dia = ?, "                  
                    + "       foto = ? "    
                    + " WHERE seq_foto = ?;";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            Long seqFoto = foto.getSeqFoto();
            pstmt.setLong(3,foto.getSeqFoto());
            pstmt.setLong(1,foto.getDia().getSeqDia());
            pstmt.setByte(2,foto.getByteFoto());

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
    public boolean deletar(Foto foto) throws ExcecaoPersistencia {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "DELETE FROM foto WHERE seq_foto = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1,foto.getSeqFoto());
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
    public ArrayList<Foto> listarTudo() throws ExcecaoPersistencia {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM foto ORDER BY seq_foto";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            
            ArrayList<Foto> listAll = null;
            InterfaceDiaDAO diaDAO = new DiaDAO();
            if (rs.next()) {
                listAll = new ArrayList<>();
                do {
                    Foto foto = new Foto();
                    foto.setSeqFoto(rs.getLong("seq_foto"));
                    Dia dia = diaDAO.consultarPorId(rs.getLong("seq_dia"));
                    foto.setDia(dia);
                    foto.setByteFoto(rs.getByte("foto"));
                    
                    listAll.add(foto);
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

    @Override
    public Foto consultarPorId(Long seqFoto) throws ExcecaoPersistencia {

        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM foto WHERE seq_foto = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, seqFoto);
            ResultSet rs = pstmt.executeQuery();

            Foto foto = null;
             InterfaceDiaDAO diaDAO = new DiaDAO();
            if (rs.next()) {
                
                    foto = new Foto();
                    foto.setSeqFoto(rs.getLong("seq_foto"));
                    Dia dia = diaDAO.consultarPorId(rs.getLong("seq_dia"));
                    foto.setDia(dia);
                    foto.setByteFoto(rs.getByte("foto"));
            }

            rs.close();
            pstmt.close();
            connection.close();

            return foto;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExcecaoPersistencia(e.getMessage(), e);
        }
    }

    

   
 

    @Override
    public List<Foto> listarPorDia(Long seqDia) throws ExcecaoPersistencia {
         try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM foto WHERE seq_dia = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, seqDia);
            ResultSet rs = pstmt.executeQuery();

            Foto foto = null;
            InterfaceDiaDAO diaDAO = new DiaDAO();
            
            if (rs.next()) {
                
                   foto = new Foto();
                    foto.setSeqFoto(rs.getLong("seq_foto"));
                    Dia dia = diaDAO.consultarPorId(rs.getLong("seq_dia"));
                    foto.setDia(dia);
                    foto.setByteFoto(rs.getByte("foto"));
            }

            rs.close();
            pstmt.close();
            connection.close();

            return (List<Foto>) foto;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExcecaoPersistencia(e.getMessage(), e);
        }
    }


}
