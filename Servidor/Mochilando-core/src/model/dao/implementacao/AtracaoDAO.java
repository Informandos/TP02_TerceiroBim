package model.dao.implementacao;

import model.dao.interfaces.InterfaceAtracaoDAO;
import model.domain.Atracao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.dao.interfaces.InterfaceCidadeDAO;
import model.dao.interfaces.InterfaceTipoAtracaoDAO;
import model.dao.implementacao.TipoAtracaoDAO;
import model.domain.Cidade;
import model.domain.TipoAtracao;
import util.db.exception.ExcecaoPersistencia;
import util.db.ConnectionManager;


public class AtracaoDAO implements InterfaceAtracaoDAO {

    @Override
    public Long inserir(Atracao atracao) throws ExcecaoPersistencia {
        if(atracao == null){
            throw new ExcecaoPersistencia("Atração não pode ser null");
        }
        
        Long seq_atracao = null;
        
        try{
            Connection conn = ConnectionManager.getInstance().getConnection();
            
            String sql = "INSERT INTO atracao (seq_atracao, cod_cidade_atracao, cod_tipo_atracao, nom_atracao, nro_latitude, nro_logitude) VALUES (?,?,?,?,?,?) RETURNING seq_atracao";
            
            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            pstmt.setLong(1, atracao.getSeqAtracao());
            pstmt.setLong(2, atracao.getCidade().getCodCidade());
            pstmt.setLong(3, atracao.getTipoAtracao().getCodTipoAtracao());
            pstmt.setString(4, atracao.getNomAtracao());
            pstmt.setDouble(5, atracao.getNroLatitude());
            pstmt.setDouble(6, atracao.getNroLongitude());
            
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                seq_atracao = rs.getLong("seq_atracao");
            }

            rs.close();
            pstmt.close();
            conn.close();
            return seq_atracao;
            
        }catch (ClassNotFoundException | SQLException e){
            
            throw new ExcecaoPersistencia(e.getMessage(), e);
            
        }
    }

    @Override
    public boolean atualizar(Atracao atracao) throws ExcecaoPersistencia {
        try{
            Connection conn = ConnectionManager.getInstance().getConnection();
            
            String sql = "UPDATE atracao SET seq_atracao = ?, cod_cidade_atracao = ?, cod_tipo_atracao = ?, nom_atracao = ?, nro_latitude = ?, nro_logitude = ? WHERE seq_atracao = ?";
            
            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            pstmt.setLong(1, atracao.getSeqAtracao());
            pstmt.setLong(2, atracao.getCidade().getCodCidade());
            pstmt.setLong(3, atracao.getTipoAtracao().getCodTipoAtracao());
            pstmt.setString(4, atracao.getNomAtracao());
            pstmt.setDouble(5, atracao.getNroLatitude());
            pstmt.setDouble(6, atracao.getNroLongitude());
            
            pstmt.executeUpdate();

            pstmt.close();
            conn.close();
            return true;
            
        }catch (ClassNotFoundException | SQLException e){
            
            throw new ExcecaoPersistencia(e.getMessage(), e);
            
        }
    }

    @Override
    public boolean deletar(Atracao atracao) throws ExcecaoPersistencia {
        try {
            Connection conn = ConnectionManager.getInstance().getConnection();

            String sql = "DELETE FROM atracao WHERE seq_atracao = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, atracao.getSeqAtracao());
            pstmt.executeUpdate();

            pstmt.close();
            conn.close();
            return true;
        } catch (ClassNotFoundException | SQLException e) {

            throw new ExcecaoPersistencia(e.getMessage(), e);

        }
    }

    @Override
    public Atracao consultarPorId(Long codAtracao) throws ExcecaoPersistencia {
        try {
            Connection conn = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM atracao WHERE seq_atracao = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, codAtracao);
            ResultSet rs = pstmt.executeQuery();

            Atracao atracao = null;
            InterfaceCidadeDAO cidadeDAO = new CidadeDAO();
            InterfaceTipoAtracaoDAO tipoAtracaoDAO = new TipoAtracaoDAO();
            if (rs.next()) {
                atracao = new Atracao();
                atracao.setSeqAtracao(rs.getLong("seq_atracao"));
                Cidade cidade = cidadeDAO.consultarPorId(rs.getLong("cod_cidade_atracao"));
                atracao.setCidade(cidade);
                TipoAtracao tipoA = tipoAtracaoDAO.consultarPorId(rs.getLong("cod_tipo_atracao"));
                atracao.setTipoAtracao(tipoA);
                atracao.setNomAtracao(rs.getString("nom_atracao"));
                atracao.setNroLatitude(rs.getDouble("nro_latitude"));
                atracao.setNroLongitude(rs.getDouble("nro_longitude"));

            }

            rs.close();
            pstmt.close();
            conn.close();

            return atracao;
        } catch (ClassNotFoundException | SQLException e) {

            throw new ExcecaoPersistencia(e.getMessage(), e);

        }
    }

    @Override
    public List<Atracao> listarPorCodCidade(Long codCidade) throws ExcecaoPersistencia {
        try {
            Connection conn = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM atracao WHERE cod_cidade_atracao = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, codCidade);
            ResultSet rs = pstmt.executeQuery();

            List<Atracao> listarporCidade =null;
            InterfaceCidadeDAO cidadeDAO = new CidadeDAO();
            InterfaceTipoAtracaoDAO tipoAtracaoDAO = new TipoAtracaoDAO();
            if (rs.next()) {
                listarporCidade = new ArrayList();
                do{
                    Atracao atracao = new Atracao();
                    atracao.setSeqAtracao(rs.getLong("seq_atracao"));
                    Cidade cidade = cidadeDAO.consultarPorId(rs.getLong("cod_cidade_atracao"));
                    atracao.setCidade(cidade);
                    TipoAtracao tipoA = tipoAtracaoDAO.consultarPorId(rs.getLong("cod_tipo_atracao"));
                    atracao.setTipoAtracao(tipoA);
                    atracao.setNomAtracao(rs.getString("nom_atracao"));
                    atracao.setNroLatitude(rs.getDouble("nro_latitude"));
                    atracao.setNroLongitude(rs.getDouble("nro_longitude"));

                    listarporCidade.add(atracao);
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
    public List<Atracao> listarPorCodEstado(Long codEstado) throws ExcecaoPersistencia {
        try {
            Connection conn = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM atracao a "+
                            "join cidade c on a.cod_cidade_atracao = c.cod_cidade "+
                            "join estado e on c.cod_estado = e.cod_estado "+
                            "WHERE e.cod_estado = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, codEstado);
            ResultSet rs = pstmt.executeQuery();

            
            List<Atracao> listarporCodEstado =null;
            InterfaceCidadeDAO cidadeDAO = new CidadeDAO();
            InterfaceTipoAtracaoDAO tipoAtracaoDAO = new TipoAtracaoDAO();
            if (rs.next()) {
                listarporCodEstado = new ArrayList();
                do{
                    Atracao atracao = new Atracao();
                    atracao.setSeqAtracao(rs.getLong("seq_atracao"));
                    Cidade cidade = cidadeDAO.consultarPorId(rs.getLong("cod_cidade_atracao"));
                    atracao.setCidade(cidade);
                    TipoAtracao tipoA = tipoAtracaoDAO.consultarPorId(rs.getLong("cod_tipo_atracao"));
                    atracao.setTipoAtracao(tipoA);
                    atracao.setNomAtracao(rs.getString("nom_atracao"));
                    atracao.setNroLatitude(rs.getDouble("nro_latitude"));
                    atracao.setNroLongitude(rs.getDouble("nro_longitude"));

                    listarporCodEstado.add(atracao);
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
    public List<Atracao> listarPorCodTipoAtracao(Long codTipoAtracao) throws ExcecaoPersistencia {
        try {
            Connection conn = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM atracao a "+
                            "join tipo_atracao t on a.cod_tipo_atracao = t.cod_tipo_atracao "+
                            "WHERE t.cod_tipo_atracao = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, codTipoAtracao);
            ResultSet rs = pstmt.executeQuery();

            List<Atracao> listarporCodTipoAtracao =null;
            InterfaceCidadeDAO cidadeDAO = new CidadeDAO();
            InterfaceTipoAtracaoDAO tipoAtracaoDAO = new TipoAtracaoDAO();
            if (rs.next()) {
                listarporCodTipoAtracao = new ArrayList();
                do{
                    Atracao atracao = new Atracao();
                    atracao.setSeqAtracao(rs.getLong("seq_atracao"));
                    Cidade cidade = cidadeDAO.consultarPorId(rs.getLong("cod_cidade_atracao"));
                    atracao.setCidade(cidade);
                    TipoAtracao tipoA = tipoAtracaoDAO.consultarPorId(rs.getLong("cod_tipo_atracao"));
                    atracao.setTipoAtracao(tipoA);
                    atracao.setNomAtracao(rs.getString("nom_atracao"));
                    atracao.setNroLatitude(rs.getDouble("nro_latitude"));
                    atracao.setNroLongitude(rs.getDouble("nro_longitude"));

                    listarporCodTipoAtracao.add(atracao);
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
    public List<Atracao> listarTudo() throws ExcecaoPersistencia {
         try {
            Connection conn = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM atracao ORDER BY nom_atracao";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            
            InterfaceCidadeDAO cidadeDAO = new CidadeDAO();
            InterfaceTipoAtracaoDAO tipoAtracaoDAO = new TipoAtracaoDAO();
            ArrayList<Atracao> listarTudo = null;
            if (rs.next()) {
                listarTudo = new ArrayList<>();
                do {
                    Atracao atracao = new Atracao();
                    atracao.setSeqAtracao(rs.getLong("seq_atracao"));
                    Cidade cidade = cidadeDAO.consultarPorId(rs.getLong("cod_cidade_atracao"));
                    atracao.setCidade(cidade);
                    TipoAtracao tipoA = tipoAtracaoDAO.consultarPorId(rs.getLong("cod_tipo_atracao"));
                    atracao.setTipoAtracao(tipoA);
                    atracao.setNomAtracao(rs.getString("nom_atracao"));
                    atracao.setNroLatitude(rs.getDouble("nro_latitude"));
                    atracao.setNroLongitude(rs.getDouble("nro_longitude"));

                    listarTudo.add(atracao);
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
