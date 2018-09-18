package model.busca.implementacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.dao.implementacao.CidadeDAO;
import model.dao.implementacao.TipoAtracaoDAO;
import model.dao.interfaces.InterfaceCidadeDAO;
import model.dao.interfaces.InterfaceTipoAtracaoDAO;
import model.domain.Atracao;
import model.domain.Cidade;
import model.domain.TipoAtracao;
import util.db.ConnectionManager;
import util.db.exception.ExcecaoPersistencia;

public class BuscarAtracao {

    public BuscarAtracao() {
    }
    
    
   
    public List<Atracao> buscaGeral(String busca) throws ExcecaoPersistencia {
        try {
            List<Atracao> listaBuscaNome = compararNomAtracao(busca);
            List<Atracao> listaBuscaCidade = compararCidadeAtracao(busca);

            List<Atracao> listaBuscaConjunta = new ArrayList();
            listaBuscaConjunta.addAll(listaBuscaNome);
            listaBuscaConjunta.addAll(listaBuscaCidade);

            return listaBuscaConjunta;
        } catch (ExcecaoPersistencia e) {
            throw new ExcecaoPersistencia(e.getMessage(), e);
        }
    }

   
    public List<Atracao> compararNomAtracao(String busca) throws ExcecaoPersistencia {
        try {
            Connection conn = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM atracao WHERE nom_atracao = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, busca);
            ResultSet rs = pstmt.executeQuery();

            List<Atracao> listarBusca = null;
            InterfaceCidadeDAO cidadeDAO = new CidadeDAO();
            InterfaceTipoAtracaoDAO tipoAtracaoDAO = new TipoAtracaoDAO();
            if (rs.next()) {
                listarBusca = new ArrayList();
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

                    listarBusca.add(atracao);
                } while (rs.next());

            }

            rs.close();
            pstmt.close();
            conn.close();

            return listarBusca;
        } catch (ClassNotFoundException | SQLException e) {
            throw new ExcecaoPersistencia(e.getMessage(), e);
        }
    }

   
    public List<Atracao> compararCidadeAtracao(String busca) throws ExcecaoPersistencia {
        try {
            Connection conn = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM atracao WHERE cod_cidade_atracao = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, busca);
            ResultSet rs = pstmt.executeQuery();

            List<Atracao> listarBusca = null;
            InterfaceCidadeDAO cidadeDAO = new CidadeDAO();
            InterfaceTipoAtracaoDAO tipoAtracaoDAO = new TipoAtracaoDAO();
            if (rs.next()) {
                listarBusca = new ArrayList();
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

                    listarBusca.add(atracao);
                } while (rs.next());

            }

            rs.close();
            pstmt.close();
            conn.close();

            return listarBusca;
        } catch (ClassNotFoundException | SQLException e) {
            throw new ExcecaoPersistencia(e.getMessage(), e);
        }
    }

}
