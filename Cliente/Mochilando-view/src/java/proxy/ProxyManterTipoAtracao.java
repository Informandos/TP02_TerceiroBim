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
import model.domain.TipoAtracao;
import model.service.interfaces.InterfaceManterTipoAtracao;
import util.db.exception.ExcecaoConexaoCliente;
import util.db.exception.ExcecaoNegocio;
import util.db.exception.ExcecaoPersistencia;


/**
 *
 * @author Carlos
 */
public class ProxyManterTipoAtracao implements InterfaceManterTipoAtracao {
    
    private ArrayList manterTipoAtracao = null;
    private Cliente cliente = null;
    /*
            ArrayList armazena:
            1) Tipo do Objeto (String)
            2) Operacao (cadastrar, alterar, excluir, pesquisar, etc)
            3) Parametro(s) da operacao (Objeto, String ou Long)
     */
    public ProxyManterTipoAtracao() throws SocketException, UnknownHostException{
         cliente = Cliente.getInstance();
    }
    
    @Override
    public Long cadastrar(TipoAtracao tipoAtracao) throws ExcecaoPersistencia, ExcecaoNegocio, ExcecaoConexaoCliente {
        manterTipoAtracao = new ArrayList();
        manterTipoAtracao.add("TipoAtracao");
        manterTipoAtracao.add("cadastrar");
        manterTipoAtracao.add(tipoAtracao);
        
        //Indice de onde vai estar o long
        Long result = 0L;
        try {
            result = (Long) cliente.requisicao(manterTipoAtracao).get(0);
        } catch (IOException | ClassNotFoundException ex) {
             Logger.getLogger(ProxyManterTipoAtracao.class.getName()).log(Level.SEVERE, null, ex);
         }
        return result;
    }

    @Override
    public boolean alterar(TipoAtracao tipoAtracao) throws ExcecaoPersistencia, ExcecaoNegocio, ExcecaoConexaoCliente {
        manterTipoAtracao = new ArrayList();
        manterTipoAtracao.add("TipoAtracao");
        manterTipoAtracao.add("alterar");
        manterTipoAtracao.add(tipoAtracao);
        
        boolean result = false;
        try {
            result = (boolean) cliente.requisicao(manterTipoAtracao).get(0);
        } catch (IOException | ClassNotFoundException ex) {
             Logger.getLogger(ProxyManterTipoAtracao.class.getName()).log(Level.SEVERE, null, ex);
         }
        return result;
    }

    @Override
    public boolean excluir(TipoAtracao tipoAtracao) throws ExcecaoPersistencia, ExcecaoNegocio, ExcecaoConexaoCliente {
        manterTipoAtracao = new ArrayList();
        manterTipoAtracao.add("TipoAtracao");
        manterTipoAtracao.add("excluir");
        manterTipoAtracao.add(tipoAtracao);
        
        boolean result = false;
        try {
            result = (boolean) cliente.requisicao(manterTipoAtracao).get(0);
        } catch (IOException | ClassNotFoundException ex) {
             Logger.getLogger(ProxyManterTipoAtracao.class.getName()).log(Level.SEVERE, null, ex);
         }
        return result;
    }

    @Override
    public TipoAtracao pesquisarPorId(Long codTipoAtracao) throws ExcecaoPersistencia, ExcecaoConexaoCliente {
        manterTipoAtracao = new ArrayList();
        manterTipoAtracao.add("TipoAtracao");
        manterTipoAtracao.add("pesquisarPorId");
        manterTipoAtracao.add(codTipoAtracao);
        
        TipoAtracao result = null;
        try {
            result = (TipoAtracao) cliente.requisicao(manterTipoAtracao).get(0);
        } catch (IOException | ClassNotFoundException ex) {
             Logger.getLogger(ProxyManterTipoAtracao.class.getName()).log(Level.SEVERE, null, ex);
         }
        return result;
    }

    @Override
    public TipoAtracao pesquisarPorNome(String descTipoAtracao) throws ExcecaoPersistencia, ExcecaoConexaoCliente {
        manterTipoAtracao = new ArrayList();
        manterTipoAtracao.add("TipoAtracao");
        manterTipoAtracao.add("pesquisarPorNome");
        manterTipoAtracao.add(descTipoAtracao);
        
        TipoAtracao result = null;
        try {
            result = (TipoAtracao) cliente.requisicao(manterTipoAtracao).get(0);
        } catch (IOException | ClassNotFoundException ex) {
             Logger.getLogger(ProxyManterTipoAtracao.class.getName()).log(Level.SEVERE, null, ex);
         }
        return result;
    }

    @Override
    public List<TipoAtracao> pesquisarTodos() throws ExcecaoPersistencia, ExcecaoConexaoCliente {
        manterTipoAtracao = new ArrayList();
        manterTipoAtracao.add("TipoAtracao");
        manterTipoAtracao.add("pesquisarPorNome");
        
        List<TipoAtracao> result = null;
        try {
            result = (List<TipoAtracao>) cliente.requisicao(manterTipoAtracao).get(0);
        } catch (IOException | ClassNotFoundException ex) {
             Logger.getLogger(ProxyManterTipoAtracao.class.getName()).log(Level.SEVERE, null, ex);
         }
        return result;
    }
    
}
