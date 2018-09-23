/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service.implementacao;

import java.util.List;
import model.dao.implementacao.UsuarioDAO;
import model.dao.interfaces.InterfaceUsuarioDAO;
import model.domain.Usuario;
import model.service.interfaces.InterfaceManterUsuario;
import util.db.exception.ExcecaoNegocio;
import util.db.exception.ExcecaoPersistencia;

/**
 *
 * @author User
 */
public class ManterUsuario implements InterfaceManterUsuario {
     private final InterfaceUsuarioDAO usuarioDAO;
    
    public ManterUsuario() {
        usuarioDAO = new UsuarioDAO();
    }
    
    @Override
    public Long cadastrar(Usuario usuario) throws  ExcecaoPersistencia,ExcecaoNegocio{
        
        
         if((usuario.getNomUsuario()== null) || (usuario.getNomUsuario().isEmpty()))
            throw new ExcecaoNegocio("Obrigatório informar o nome.");
        if((usuario.getTxtSenha()== null) || (usuario.getTxtSenha().isEmpty()))
            throw new ExcecaoNegocio("Obrigatório informar a senha.");
         if((usuario.getTxtEmail() == null) || (usuario.getTxtEmail().isEmpty()))
            throw new ExcecaoNegocio("Obrigatório informar o E-mail.");
          if((usuario.getDatNascimento()== null))
            throw new ExcecaoNegocio("Obrigatório informar a data de nascimento.");
          if((usuario.getSobrenomeUsuario()== null) || (usuario.getSobrenomeUsuario().isEmpty()))
            throw new ExcecaoNegocio("Obrigatório informar o sobrenome");
          if((usuario.getSexo()== null) || (usuario.getSexo().isEmpty()))
            throw new ExcecaoNegocio("Obrigatório informar o sexo");
         
        Long result = usuarioDAO.inserir(usuario);
        return result;
    }

    @Override
    public boolean alterar(Usuario usuario) throws ExcecaoPersistencia,ExcecaoNegocio {
        
          if((usuario.getCodUsuario()== null))
            throw new ExcecaoNegocio("Obrigatório informar o codigo usuario");
         if((usuario.getNomUsuario()== null) || (usuario.getNomUsuario().isEmpty()))
            throw new ExcecaoNegocio("Obrigatório informar o nome.");
        if((usuario.getTxtSenha()== null) || (usuario.getTxtSenha().isEmpty()))
            throw new ExcecaoNegocio("Obrigatório informar a senha.");
         if((usuario.getTxtEmail() == null) || (usuario.getTxtEmail().isEmpty()))
            throw new ExcecaoNegocio("Obrigatório informar o E-mail.");
          if((usuario.getDatNascimento()== null))
            throw new ExcecaoNegocio("Obrigatório informar a data de nascimento.");
          if((usuario.getSobrenomeUsuario()== null) || (usuario.getSobrenomeUsuario().isEmpty()))
            throw new ExcecaoNegocio("Obrigatório informar o sobrenome");
          if((usuario.getSexo()== null) || (usuario.getSexo().isEmpty()))
            throw new ExcecaoNegocio("Obrigatório informar o sexo");
        
        
                  
        boolean result = usuarioDAO.atualizar(usuario);
        return result;
    }

    @Override
    public boolean excluir(Usuario usuario) throws ExcecaoPersistencia,ExcecaoNegocio {
        
        boolean result = usuarioDAO.deletar(usuario);
        return result;       
    }

    @Override
    public List<Usuario> pesquisarTodos() throws ExcecaoPersistencia{
        List<Usuario> result = usuarioDAO.listarTudo();
        return result;
    }

    @Override
    public Usuario pesquisarPorId(Long id) throws ExcecaoPersistencia {
        Usuario result = usuarioDAO.consultarPorId(id);
        return result;       
    }    

    @Override
    public Usuario getUserLogin(String email, String senha) throws ExcecaoPersistencia {
        Usuario result = usuarioDAO.consultarPorEmailSenha(email, senha);
        return result;               
    }

    @Override
    public Usuario getUserEmail(String email) throws ExcecaoPersistencia, ExcecaoNegocio {
        Usuario result = usuarioDAO.consultarPorEmail(email);
        return result;    
    }
}
