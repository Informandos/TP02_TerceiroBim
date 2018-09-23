/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proxy;

import cliente.Cliente;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.domain.Usuario;
import model.service.interfaces.InterfaceManterUsuario;
import util.db.exception.ExcecaoConexaoCliente;
import util.db.exception.ExcecaoNegocio;
import util.db.exception.ExcecaoPersistencia;

/**
 *
 * @author Juliana, Carlos
 */
public class ProxyManterUsuario implements InterfaceManterUsuario {
    
    private ArrayList manterUsuario = null;
    private Cliente cliente = null;
    /*
            ArrayList armazena:
            1) Tipo do Objeto (String)
            2) Operacao (cadastrar, alterar, excluir, pesquisar, etc)
            3) Parametro(s) da operacao (Objeto, String ou Long)
     */
    public ProxyManterUsuario() throws SocketException, UnknownHostException{
         cliente = Cliente.getInstance();
    }
    
    @Override
    public Long cadastrar(Usuario usuario) throws ExcecaoPersistencia, ExcecaoNegocio, ExcecaoConexaoCliente {
        manterUsuario = new ArrayList();
        manterUsuario.add("Usuario");
        manterUsuario.add("cadastrar");
        manterUsuario.add(usuario);
        
        //Indice de onde vai estar o long
        Long result = 0L;
        try {
            result = (Long) cliente.requisicao(manterUsuario).get(0);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ProxyManterUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public boolean alterar(Usuario usuario) throws ExcecaoPersistencia, ExcecaoNegocio,ExcecaoConexaoCliente {
        manterUsuario = new ArrayList();
        manterUsuario.add("Usuario");
        manterUsuario.add("alterar");
        manterUsuario.add(usuario);
        
        boolean result = false;
        try {
            result = (boolean) cliente.requisicao(manterUsuario).get(0);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ProxyManterUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public boolean excluir(Usuario usuario) throws ExcecaoPersistencia, ExcecaoNegocio,ExcecaoConexaoCliente {
        manterUsuario = new ArrayList();
        manterUsuario.add("Usuario");
        manterUsuario.add("excluir");
        manterUsuario.add(usuario);
        
        boolean result = false;
        try {
            result = (boolean) cliente.requisicao(manterUsuario).get(0);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ProxyManterUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public List<Usuario> pesquisarTodos() throws ExcecaoPersistencia,ExcecaoConexaoCliente {
        manterUsuario = new ArrayList();
        manterUsuario.add("Usuario");
        manterUsuario.add("pesquisarTodos");
        
        List<Usuario> result = null;
        try {
            result = (List<Usuario>) cliente.requisicao(manterUsuario).get(0);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ProxyManterUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public Usuario pesquisarPorId(Long id) throws ExcecaoPersistencia,ExcecaoConexaoCliente {
        manterUsuario = new ArrayList();
        manterUsuario.add("Usuario");
        manterUsuario.add("pesquisarPorId");
        manterUsuario.add(id);
        
        Usuario result = null;
        try {
            result = (Usuario) cliente.requisicao(manterUsuario).get(0);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ProxyManterUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public Usuario getUserLogin(String email, String senha) throws ExcecaoPersistencia, ExcecaoNegocio,ExcecaoConexaoCliente {
        manterUsuario = new ArrayList();
        manterUsuario.add("Usuario");
        manterUsuario.add("getUserLogin");
        manterUsuario.add(email);
        manterUsuario.add(senha);

        Usuario result = null;
        try {
            result = (Usuario) cliente.requisicao(manterUsuario).get(0);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ProxyManterUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public Usuario getUserEmail(String email) throws ExcecaoPersistencia, ExcecaoNegocio,ExcecaoConexaoCliente {
        manterUsuario = new ArrayList();
        manterUsuario.add("Usuario");
        manterUsuario.add("getUserEmail");
        manterUsuario.add(email);

        Usuario result = null;
        try {
            result = (Usuario) cliente.requisicao(manterUsuario).get(0);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ProxyManterUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

}

