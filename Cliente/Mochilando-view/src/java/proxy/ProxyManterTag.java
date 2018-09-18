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
import model.domain.Tag;
import model.service.interfaces.InterfaceManterTag;
import util.db.exception.ExcecaoConexaoCliente;
import util.db.exception.ExcecaoNegocio;
import util.db.exception.ExcecaoPersistencia;

/**
 *
 * @author Aluno
 */
public class ProxyManterTag implements InterfaceManterTag {
    
    private ArrayList manterTag = null;
    private Cliente cliente = null;
    /*
            ArrayList armazena:
            1) Tipo do Objeto (String)
            2) Operacao (cadastrar, alterar, excluir, pesquisar, etc)
            3) Parametro(s) da operacao (Objeto, String ou Long)
     */
    public ProxyManterTag() throws SocketException, UnknownHostException{
         cliente = Cliente.getInstance();
    }
    
    @Override
    public Long cadastrar(Tag tag) throws ExcecaoPersistencia, ExcecaoNegocio, ExcecaoConexaoCliente {
        manterTag = new ArrayList();
        manterTag.add("Tag");
        manterTag.add("cadastrar");
        manterTag.add(tag);
        
        //Indice de onde vai estar o long
        Long result = 0L;
        try {
            result = (Long) cliente.requisicao(manterTag).get(0);
        } catch (IOException | ClassNotFoundException ex) {
             Logger.getLogger(ProxyManterTag.class.getName()).log(Level.SEVERE, null, ex);
         }
        return result;
    }

    @Override
    public boolean alterar(Tag tag) throws ExcecaoPersistencia, ExcecaoNegocio, ExcecaoConexaoCliente {
        manterTag = new ArrayList();
        manterTag.add("Tag");
        manterTag.add("alterar");
        manterTag.add(tag);
        
        boolean result = false;
        try {
            result = (boolean) cliente.requisicao(manterTag).get(0);
        } catch (IOException | ClassNotFoundException ex) {
             Logger.getLogger(ProxyManterTag.class.getName()).log(Level.SEVERE, null, ex);
         }
        return result;
    }

    @Override
    public boolean excluir(Tag tag) throws ExcecaoPersistencia, ExcecaoNegocio, ExcecaoConexaoCliente {
        manterTag = new ArrayList();
        manterTag.add("Tag");
        manterTag.add("excluir");
        manterTag.add(tag);
        
        boolean result = false;
        try {
            result = (boolean) cliente.requisicao(manterTag).get(0);
        } catch (IOException | ClassNotFoundException ex) {
             Logger.getLogger(ProxyManterTag.class.getName()).log(Level.SEVERE, null, ex);
         }
        return result;
    }

    @Override
    public Tag pesquisarPorId(Long codTag) throws ExcecaoPersistencia, ExcecaoConexaoCliente {
        manterTag = new ArrayList();
        manterTag.add("Tag");
        manterTag.add("pesquisarPorId");
        manterTag.add(codTag);
        
        Tag result = null;
        try {
            result = (Tag) cliente.requisicao(manterTag).get(0);
        } catch (IOException | ClassNotFoundException ex) {
             Logger.getLogger(ProxyManterTag.class.getName()).log(Level.SEVERE, null, ex);
         }
        return result;
    }

    @Override
    public Tag pesquisarPorNome(String descTag) throws ExcecaoPersistencia, ExcecaoConexaoCliente {
        manterTag = new ArrayList();
        manterTag.add("Tag");
        manterTag.add("pesquisarPorNome");
        manterTag.add(descTag);
        
        Tag result = null;
        try {
            result = (Tag) cliente.requisicao(manterTag).get(0);
        } catch (IOException | ClassNotFoundException ex) {
             Logger.getLogger(ProxyManterTag.class.getName()).log(Level.SEVERE, null, ex);
         }
        return result;    
    }

    @Override
    public List<Tag> pesquisarTodos() throws ExcecaoPersistencia, ExcecaoConexaoCliente {
        manterTag = new ArrayList();
        manterTag.add("Tag");
        manterTag.add("pesquisarTodos");
        
        List<Tag> result = null;
        try {
            result = (List<Tag>) cliente.requisicao(manterTag).get(0);
        } catch (IOException | ClassNotFoundException ex) {
             Logger.getLogger(ProxyManterTag.class.getName()).log(Level.SEVERE, null, ex);
         }
        return result; 
    }
    
}
