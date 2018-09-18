package model.busca.implementacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.dao.implementacao.UsuarioDAO;
import model.dao.interfaces.InterfaceUsuarioDAO;
import model.domain.Diario;
import model.domain.Usuario;
import util.db.ConnectionManager;
import util.db.exception.ExcecaoPersistencia;

public class BuscarDiario {

    public List<Diario> buscaGeral(String busca) throws ExcecaoPersistencia{
        try {
            List<Diario> listaBuscaNome = compararNomDiario(busca);
            List<Diario> listaBuscaAtracao = compararNomAtracaoRelacionada(busca);
            List<Diario> listaBuscaCidade = compararNomCidadeRelacionada(busca);
            List<Diario> listaBuscaTxtDiario = compararTextoDiario(busca);
            
            List<Diario> listaBuscaConjunta = new ArrayList();
            listaBuscaConjunta.addAll(listaBuscaNome);
            listaBuscaConjunta.addAll(listaBuscaCidade);
            listaBuscaConjunta.addAll(listaBuscaAtracao);
            listaBuscaConjunta.addAll(listaBuscaTxtDiario);
        
            return listaBuscaConjunta;
        } catch (ExcecaoPersistencia e) {
            throw new ExcecaoPersistencia(e.getMessage(), e);
        }
    }


    public List<Diario> compararNomDiario(String busca) throws ExcecaoPersistencia{
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM diario WHERE nom_diario = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, busca);
            ResultSet rs = pstmt.executeQuery();

            ArrayList<Diario> listarBusca = null;
            InterfaceUsuarioDAO UsuarioDAO = new UsuarioDAO();
            if (rs.next()) {
                listarBusca = new ArrayList<>();
                do {
                    Diario diario = new Diario();
                    diario.setCodDiario(rs.getLong("cod_diario"));
                    Usuario usuario = UsuarioDAO.consultarPorId(rs.getLong("cod_usuario"));
                    diario.setUsuario(usuario);
                    diario.setNomDiario(rs.getString("nom_diario"));
                    diario.setDatPublicacao(rs.getDate("dat_publicacao"));
                    diario.setDatInicioViagem(rs.getDate("dat_inicio_viagem"));
                    diario.setDatFimViagem(rs.getDate("dat_fim_viagem"));
                    diario.setTxtDiario(rs.getString("txt_diario"));

                    listarBusca.add(diario);
                } while (rs.next());

            }

            rs.close();
            pstmt.close();
            connection.close();

            return listarBusca;
        } catch (Exception e) {
            throw new ExcecaoPersistencia(e.getMessage(), e);
        }
    }


    public List<Diario> compararNomAtracaoRelacionada(String busca) throws ExcecaoPersistencia{
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM diario d JOIN atracao a ON d.cod_diario = a.cod_diario WHERE nom_diario = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, busca);
            ResultSet rs = pstmt.executeQuery();

            ArrayList<Diario> listarBusca = null;
            InterfaceUsuarioDAO UsuarioDAO = new UsuarioDAO();
            if (rs.next()) {
                listarBusca = new ArrayList<>();
                do {
                    Diario diario = new Diario();
                    diario.setCodDiario(rs.getLong("cod_diario"));
                    Usuario usuario = UsuarioDAO.consultarPorId(rs.getLong("cod_usuario"));
                    diario.setUsuario(usuario);
                    diario.setNomDiario(rs.getString("nom_diario"));
                    diario.setDatPublicacao(rs.getDate("dat_publicacao"));
                    diario.setDatInicioViagem(rs.getDate("dat_inicio_viagem"));
                    diario.setDatFimViagem(rs.getDate("dat_fim_viagem"));
                    diario.setTxtDiario(rs.getString("txt_diario"));

                    listarBusca.add(diario);
                } while (rs.next());

            }

            rs.close();
            pstmt.close();
            connection.close();

            return listarBusca;
        } catch (Exception e) {
            throw new ExcecaoPersistencia(e.getMessage(), e);
        }
    }


    public List<Diario> compararNomCidadeRelacionada(String busca) throws ExcecaoPersistencia{
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM diario d "
                        +"JOIN usuario u ON d.cod_usuario = u.cod_usuario "
                        +"JOIN cidade c ON u.cod_cidade = c.cidade WHERE c.nom_cidade = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, busca);
            ResultSet rs = pstmt.executeQuery();

            ArrayList<Diario> listarBusca = null;
            InterfaceUsuarioDAO UsuarioDAO = new UsuarioDAO();
            if (rs.next()) {
                listarBusca = new ArrayList<>();
                do {
                    Diario diario = new Diario();
                    diario.setCodDiario(rs.getLong("cod_diario"));
                    Usuario usuario = UsuarioDAO.consultarPorId(rs.getLong("cod_usuario"));
                    diario.setUsuario(usuario);
                    diario.setNomDiario(rs.getString("nom_diario"));
                    diario.setDatPublicacao(rs.getDate("dat_publicacao"));
                    diario.setDatInicioViagem(rs.getDate("dat_inicio_viagem"));
                    diario.setDatFimViagem(rs.getDate("dat_fim_viagem"));
                    diario.setTxtDiario(rs.getString("txt_diario"));

                    listarBusca.add(diario);
                } while (rs.next());

            }

            rs.close();
            pstmt.close();
            connection.close();

            return listarBusca;
        } catch (Exception e) {
            throw new ExcecaoPersistencia(e.getMessage(), e);
        }
    }


    public List<Diario> compararTextoDiario(String busca) throws ExcecaoPersistencia{
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM diario WHERE txt_diario = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, busca);
            ResultSet rs = pstmt.executeQuery();

            ArrayList<Diario> listarBusca = null;
            InterfaceUsuarioDAO UsuarioDAO = new UsuarioDAO();
            if (rs.next()) {
                listarBusca = new ArrayList<>();
                do {
                    Diario diario = new Diario();
                    diario.setCodDiario(rs.getLong("cod_diario"));
                    Usuario usuario = UsuarioDAO.consultarPorId(rs.getLong("cod_usuario"));
                    diario.setUsuario(usuario);
                    diario.setNomDiario(rs.getString("nom_diario"));
                    diario.setDatPublicacao(rs.getDate("dat_publicacao"));
                    diario.setDatInicioViagem(rs.getDate("dat_inicio_viagem"));
                    diario.setDatFimViagem(rs.getDate("dat_fim_viagem"));
                    diario.setTxtDiario(rs.getString("txt_diario"));

                    listarBusca.add(diario);
                } while (rs.next());

            }

            rs.close();
            pstmt.close();
            connection.close();

            return listarBusca;
        } catch (Exception e) {
            throw new ExcecaoPersistencia(e.getMessage(), e);
        }
    }
    
}
