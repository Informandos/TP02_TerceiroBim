package model.busca.implementacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.dao.implementacao.CidadeDAO;
import model.dao.interfaces.InterfaceCidadeDAO;
import model.domain.Cidade;
import model.domain.Usuario;

import util.db.ConnectionManager;
import util.db.exception.ExcecaoPersistencia;

public class BuscarUsuario  {

   
    public List<Usuario> buscaGeral(String busca) throws ExcecaoPersistencia{
        try {
            List<Usuario> listaBuscaNome = compararNomUsuario(busca);
        
            return listaBuscaNome;
        } catch (ExcecaoPersistencia e) {
            throw new ExcecaoPersistencia(e.getMessage(), e);
        }    
    }   

   
    public List<Usuario> compararNomUsuario(String busca) throws ExcecaoPersistencia{
         try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM usuario WHERE nome_usuario = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, busca);
            ResultSet rs = pstmt.executeQuery();

            ArrayList<Usuario> listarBusca = null;
            InterfaceCidadeDAO cidadeDAO = new CidadeDAO();
            if (rs.next()) {
                listarBusca = new ArrayList<>();
                do {
                    Usuario usuario = new Usuario();
                    usuario.setCodUsuario(rs.getLong("cod_usuario"));
                    usuario.setNomUsuario(rs.getString("nom_usuario"));
                    usuario.setSobrenomeUsuario(rs.getString("sobrenome_usuario"));
                    usuario.setTxtEmail(rs.getString("txt_email"));
                    usuario.setTxtSenha(rs.getString("txt_senha"));
                    usuario.setImgPerfil(rs.getByte("img_perfil"));
                    usuario.setSexo(rs.getString("sexo"));
                    usuario.setDatNascimento(rs.getDate("dat_nascimento"));
                    Cidade cidade = cidadeDAO.consultarPorId(rs.getLong("cod_cidade"));
                    usuario.setCidade(cidade);
                    listarBusca.add(usuario);
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
