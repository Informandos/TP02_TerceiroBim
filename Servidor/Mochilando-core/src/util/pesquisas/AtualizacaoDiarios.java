/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.pesquisas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.dao.implementacao.UsuarioDAO;
import model.dao.interfaces.InterfaceUsuarioDAO;
import model.domain.Diario;
import model.domain.Usuario;
import util.db.ConnectionManager;
import util.db.exception.ExcecaoPersistencia;

/**
 *
 * @author Juliana
 */
public class AtualizacaoDiarios {
    //obtem os diarios mais recentes que tem tags seguidas pelo usuaro
    public ArrayList<Diario> atualizar(Long codUsuario) throws ExcecaoPersistencia{
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT A.cod_diario FROM"
                    + " diario A JOIN tag_diario B ON A.cod_diario = B.cod_diario "
                    + "JOIN usuario_tag C ON B.cod_tag = C.cod_tag "
                    + "WHERE C.cod_usuario = ? "
                    + "GROUP BY 1, A.dat_publicacao ORDER BY A.dat_publicacao";
            
            PreparedStatement pstmt = connection.prepareStatement(sql);
            
            
            pstmt.setLong(1, codUsuario);
           
            
            ResultSet rs = pstmt.executeQuery();

            
            ArrayList<Diario> listarTudo = null;
            InterfaceUsuarioDAO UsuarioDAO = new UsuarioDAO();
            if (rs.next()) {
                listarTudo = new ArrayList<>();
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

                    listarTudo.add(diario);
                } while (rs.next());

            }

            rs.close();
            pstmt.close();
            connection.close();

            return listarTudo;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExcecaoPersistencia(e.getMessage(), e);
        }
    }
}
