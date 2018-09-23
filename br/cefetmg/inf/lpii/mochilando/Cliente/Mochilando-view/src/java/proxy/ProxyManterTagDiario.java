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
import model.domain.TagDiario;
import model.service.interfaces.InterfaceManterTagDiario;
import util.db.exception.ExcecaoNegocio;
import util.db.exception.ExcecaoPersistencia;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.db.exception.ExcecaoConexaoCliente;

/**
 *
 * @author Carlos
 */
public class ProxyManterTagDiario implements InterfaceManterTagDiario {
    
    private ArrayList manterTagDiario = null;
    private Cliente cliente = null;
    /*
            ArrayList armazena:
            1) Tipo do Objeto (String)
            2) Operacao (cadastrar, alterar, excluir, pesquisar, etc)
            3) Parametro(s) da operacao (Objeto, String ou Long)
     */
    public ProxyManterTagDiario() throws SocketException, UnknownHostException{
         cliente = Cliente.getInstance();
    }
    
    @Override
    public Long cadastrar(TagDiario tagDiario) throws ExcecaoPersistencia, ExcecaoNegocio, ExcecaoConexaoCliente {
        manterTagDiario = new ArrayList();
        manterTagDiario.add("TagDiario");
        manterTagDiario.add("cadastrar");
        manterTagDiario.add(tagDiario);
        
        //Indice de onde vai estar o long
        Long result = 0L;
        try {
            result = (Long) cliente.requisicao(manterTagDiario).get(0);
        } catch (IOException | ClassNotFoundException ex) {
             Logger.getLogger(ProxyManterTagDiario.class.getName()).log(Level.SEVERE, null, ex);
         }
        return result;
    }

    @Override
    public boolean alterar(TagDiario tagDiario) throws ExcecaoPersistencia, ExcecaoNegocio, ExcecaoConexaoCliente {
        manterTagDiario = new ArrayList();
        manterTagDiario.add("TagDiario");
        manterTagDiario.add("alterar");
        manterTagDiario.add(tagDiario);
        
        boolean result = false;
        try {
            result = (boolean) cliente.requisicao(manterTagDiario).get(0);
        } catch (IOException | ClassNotFoundException ex) {
             Logger.getLogger(ProxyManterTagDiario.class.getName()).log(Level.SEVERE, null, ex);
         }
        return result;
    }

    @Override
    public boolean excluir(TagDiario tagDiario) throws ExcecaoPersistencia, ExcecaoNegocio, ExcecaoConexaoCliente {
        manterTagDiario = new ArrayList();
        manterTagDiario.add("TagDiario");
        manterTagDiario.add("excluir");
        manterTagDiario.add(tagDiario);
        
        boolean result = false;
        try {
            result = (boolean) cliente.requisicao(manterTagDiario).get(0);
        } catch (IOException | ClassNotFoundException ex) {
             Logger.getLogger(ProxyManterTagDiario.class.getName()).log(Level.SEVERE, null, ex);
         }
        return result;
    }

    @Override
    public TagDiario pesquisarPorId(Long seqTagDiario) throws ExcecaoPersistencia, ExcecaoConexaoCliente {
        manterTagDiario = new ArrayList();
        manterTagDiario.add("TagDiario");
        manterTagDiario.add("pesquisarPorId");
        manterTagDiario.add(seqTagDiario);
        
        TagDiario result = null;
        try {
            result = (TagDiario) cliente.requisicao(manterTagDiario).get(0);
        } catch (IOException | ClassNotFoundException ex) {
             Logger.getLogger(ProxyManterTagDiario.class.getName()).log(Level.SEVERE, null, ex);
         }
        return result;
    }

    @Override
    public List<TagDiario> pesquisarTodos() throws ExcecaoPersistencia, ExcecaoConexaoCliente {
        manterTagDiario = new ArrayList();
        manterTagDiario.add("TagDiario");
        manterTagDiario.add("pesquisarTodos");
        
        List<TagDiario> result = null;
        try {
            result = (List<TagDiario>) cliente.requisicao(manterTagDiario).get(0);
        } catch (IOException | ClassNotFoundException ex) {
             Logger.getLogger(ProxyManterTagDiario.class.getName()).log(Level.SEVERE, null, ex);
         }
        return result;
    }

    @Override
    public List<TagDiario> pesquisarPorCodDiario(Long codDiario) throws ExcecaoPersistencia, ExcecaoConexaoCliente {
        manterTagDiario = new ArrayList();
        manterTagDiario.add("TagDiario");
        manterTagDiario.add("pesquisarPorCodDiario");
        manterTagDiario.add(codDiario);
        
        List<TagDiario> result = null;
        try {
            result = (List<TagDiario>) cliente.requisicao(manterTagDiario).get(0);
        } catch (IOException | ClassNotFoundException ex) {
             Logger.getLogger(ProxyManterTagDiario.class.getName()).log(Level.SEVERE, null, ex);
         }
        return result;
    }

    @Override
    public List<TagDiario> pesquisarPorCodTag(Long codTag) throws ExcecaoPersistencia, ExcecaoConexaoCliente {
        manterTagDiario = new ArrayList();
        manterTagDiario.add("TagDiario");
        manterTagDiario.add("pesquisarPorCodTag");
        manterTagDiario.add(codTag);
        
        List<TagDiario> result = null;
        try {
            result = (List<TagDiario>) cliente.requisicao(manterTagDiario).get(0);
        } catch (IOException | ClassNotFoundException ex) {
             Logger.getLogger(ProxyManterTagDiario.class.getName()).log(Level.SEVERE, null, ex);
         }
        return result;
    }
    
}
