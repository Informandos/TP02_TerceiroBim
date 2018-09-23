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
import model.domain.UsuarioTag;
import model.service.interfaces.InterfaceManterUsuarioTag;
import util.db.exception.ExcecaoConexaoCliente;
import util.db.exception.ExcecaoNegocio;
import util.db.exception.ExcecaoPersistencia;

/**
 *
 * @author Carlos
 */
public class ProxyManterUsuarioTag implements InterfaceManterUsuarioTag{
    private ArrayList manterUsuarioTag = null;
    private Cliente cliente = null;
    /*
            ArrayList armazena:
            1) Tipo do Objeto (String)
            2) Operacao (cadastrar, alterar, excluir, pesquisar, etc)
            3) Parametro(s) da operacao (Objeto, String ou Long)
     */
    public ProxyManterUsuarioTag() throws SocketException, UnknownHostException{
         cliente = Cliente.getInstance();
    }

    @Override
    public Long cadastrar(UsuarioTag usuarioTag) throws ExcecaoPersistencia, ExcecaoNegocio, ExcecaoConexaoCliente {
        manterUsuarioTag = new ArrayList();
        manterUsuarioTag.add("UsuarioTag");
        manterUsuarioTag.add("cadastrar");
        manterUsuarioTag.add(usuarioTag);
        
        //Indice de onde vai estar o long
        Long result = 0L;
        try {
            result = (Long) cliente.requisicao(manterUsuarioTag).get(0);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ProxyManterUsuarioTag.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public boolean alterar(UsuarioTag usuarioTag) throws ExcecaoPersistencia, ExcecaoNegocio, ExcecaoConexaoCliente {
        manterUsuarioTag = new ArrayList();
        manterUsuarioTag.add("UsuarioTag");
        manterUsuarioTag.add("alterar");
        manterUsuarioTag.add(usuarioTag);
        
        boolean result = false;
        try {
            result = (boolean) cliente.requisicao(manterUsuarioTag).get(0);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ProxyManterUsuarioTag.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public boolean excluir(UsuarioTag usuarioTag) throws ExcecaoPersistencia, ExcecaoNegocio, ExcecaoConexaoCliente {
        manterUsuarioTag = new ArrayList();
        manterUsuarioTag.add("UsuarioTag");
        manterUsuarioTag.add("excluir");
        manterUsuarioTag.add(usuarioTag);
        
        boolean result = false;
        try {
            result = (boolean) cliente.requisicao(manterUsuarioTag).get(0);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ProxyManterUsuarioTag.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public UsuarioTag pesquisarPorId(Long seqUsuarioTag) throws ExcecaoPersistencia, ExcecaoConexaoCliente {
        manterUsuarioTag = new ArrayList();
        manterUsuarioTag.add("UsuarioTag");
        manterUsuarioTag.add("pesquisarPorId");
        manterUsuarioTag.add(seqUsuarioTag);
        
        UsuarioTag result = null;
        try {
            result = (UsuarioTag) cliente.requisicao(manterUsuarioTag).get(0);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ProxyManterUsuarioTag.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public List<UsuarioTag> pesquisarTodos() throws ExcecaoPersistencia, ExcecaoConexaoCliente {
        manterUsuarioTag = new ArrayList();
        manterUsuarioTag.add("UsuarioTag");
        manterUsuarioTag.add("pesquisarTodos");
        
        
        List<UsuarioTag> result = null;
        try {
            result = (List<UsuarioTag>) cliente.requisicao(manterUsuarioTag).get(0);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ProxyManterUsuarioTag.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public List<UsuarioTag> pesquisarPorCodUsuario(Long codUsuario) throws ExcecaoPersistencia, ExcecaoConexaoCliente {
        manterUsuarioTag = new ArrayList();
        manterUsuarioTag.add("UsuarioTag");
        manterUsuarioTag.add("pesquisarPorCodUsuario");
        manterUsuarioTag.add(codUsuario);
        
        List<UsuarioTag> result = null;
        try {
            result = (List<UsuarioTag>) cliente.requisicao(manterUsuarioTag).get(0);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ProxyManterUsuarioTag.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public List<UsuarioTag> pesquisarPorCodTag(Long codTag) throws ExcecaoPersistencia, ExcecaoConexaoCliente {
        manterUsuarioTag = new ArrayList();
        manterUsuarioTag.add("UsuarioTag");
        manterUsuarioTag.add("pesquisarPorCodTag");
        manterUsuarioTag.add(codTag);
        
        List<UsuarioTag> result = null;
        try {
            result = (List<UsuarioTag>) cliente.requisicao(manterUsuarioTag).get(0);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ProxyManterUsuarioTag.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    
}
