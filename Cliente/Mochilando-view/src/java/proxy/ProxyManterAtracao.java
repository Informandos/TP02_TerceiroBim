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
import model.domain.Atracao;
import model.service.interfaces.InterfaceManterAtracao;
import util.db.exception.ExcecaoConexaoCliente;
import util.db.exception.ExcecaoNegocio;
import util.db.exception.ExcecaoPersistencia;

/**
 *
 * @author Aluno
 */
public class ProxyManterAtracao implements InterfaceManterAtracao {
     private ArrayList manterAtracao = null;
    private Cliente cliente = null;
    /*
            ArrayList armazena:
            1) Tipo do Objeto (String)
            2) Operacao (cadastrar, alterar, excluir, pesquisar, etc)
            3) Parametro(s) da operacao (Objeto, String ou Long)
     */
    public ProxyManterAtracao() throws SocketException, UnknownHostException{
         cliente = Cliente.getInstance();
    }

    @Override
    public Long cadastrar(Atracao atracao) throws ExcecaoPersistencia, ExcecaoNegocio, ExcecaoConexaoCliente {
       manterAtracao = new ArrayList();
        manterAtracao.add("Atracao");
        manterAtracao.add("cadastrar");
        manterAtracao.add(atracao);
        
        //Indice de onde vai estar o long
        Long result = 0L;
        try {
            result = (Long) cliente.requisicao(manterAtracao).get(0);
        } catch (IOException | ClassNotFoundException ex) {
             Logger.getLogger(ProxyManterAtracao.class.getName()).log(Level.SEVERE, null, ex);
         }
        return result;
    }

    @Override
    public boolean alterar(Atracao atracao) throws ExcecaoPersistencia, ExcecaoNegocio, ExcecaoConexaoCliente {
         manterAtracao = new ArrayList();
        manterAtracao.add("Atracao");
        manterAtracao.add("alterar");
        manterAtracao.add(atracao);
        
        //Indice de onde vai estar o boolean
        boolean result = false;
         try {
             result = (boolean) cliente.requisicao(manterAtracao).get(0);
         } catch (IOException | ClassNotFoundException ex) {
             Logger.getLogger(ProxyManterAtracao.class.getName()).log(Level.SEVERE, null, ex);
         }        
        return result;
    }

    @Override
    public boolean excluir(Atracao atracao) throws ExcecaoPersistencia, ExcecaoNegocio, ExcecaoConexaoCliente {
         manterAtracao = new ArrayList();
        manterAtracao.add("Atracao");
        manterAtracao.add("excluir");
        manterAtracao.add(atracao);
        
        boolean result = false;
        try {
            result = (boolean) cliente.requisicao(manterAtracao).get(0);
        } catch (IOException | ClassNotFoundException ex) {
             Logger.getLogger(ProxyManterAtracao.class.getName()).log(Level.SEVERE, null, ex);
         }
        return result;
    }

    @Override
    public Atracao pesquisarPorId(Long id) throws ExcecaoPersistencia, ExcecaoConexaoCliente {
        manterAtracao = new ArrayList();
        manterAtracao.add("Atracao");
        manterAtracao.add("pesquisarPorId");
        manterAtracao.add(id);
        Atracao result = null;
        try {
            result = (Atracao) cliente.requisicao(manterAtracao).get(0);
        } catch (IOException | ClassNotFoundException ex) {
             Logger.getLogger(ProxyManterAtracao.class.getName()).log(Level.SEVERE, null, ex);
         }
        return result;
    }

    @Override
    public List<Atracao> pesquisarPorCodCidade(Long codCidade) throws ExcecaoPersistencia, ExcecaoConexaoCliente {
        manterAtracao = new ArrayList();
        manterAtracao.add("Atracao");
        manterAtracao.add("pesquisarPorCodCidade");
        manterAtracao.add(codCidade);
         List<Atracao> result = null;
        try {
            result = (List<Atracao>) cliente.requisicao(manterAtracao).get(0);
        } catch (IOException | ClassNotFoundException ex) {
             Logger.getLogger(ProxyManterAtracao.class.getName()).log(Level.SEVERE, null, ex);
         }
        return result;
    }

    @Override
    public List<Atracao> pesquisarPorCodEstado(Long codEstado) throws ExcecaoPersistencia, ExcecaoConexaoCliente {
        manterAtracao = new ArrayList();
        manterAtracao.add("Atracao");
        manterAtracao.add("pesquisarPorCodEstado");
        manterAtracao.add(codEstado);
         List<Atracao> result = null;
        try {
            result = (List<Atracao>) cliente.requisicao(manterAtracao).get(0);
        } catch (IOException | ClassNotFoundException ex) {
             Logger.getLogger(ProxyManterAtracao.class.getName()).log(Level.SEVERE, null, ex);
         }
        return result;
    }

    @Override
    public List<Atracao> pesquisarPorCodTipoAtracao(Long codTipoAtracao) throws ExcecaoPersistencia, ExcecaoConexaoCliente {
        manterAtracao = new ArrayList();
        manterAtracao.add("Atracao");
        manterAtracao.add("pesquisarPorCodTipoAtracao");
        manterAtracao.add(codTipoAtracao);
         List<Atracao> result = null;
        try {
            result = (List<Atracao>) cliente.requisicao(manterAtracao).get(0);
        } catch (IOException | ClassNotFoundException ex) {
             Logger.getLogger(ProxyManterAtracao.class.getName()).log(Level.SEVERE, null, ex);
         }
        return result;
    }

    @Override
    public List<Atracao> pesquisarTodos() throws ExcecaoPersistencia, ExcecaoConexaoCliente {
        manterAtracao = new ArrayList();
        manterAtracao.add("Atracao");
        manterAtracao.add("pesquisarPorCodTipoAtracao");
        
         List<Atracao> result = null;
        try {
            result = (List<Atracao>) cliente.requisicao(manterAtracao).get(0);
        } catch (IOException | ClassNotFoundException ex) {
             Logger.getLogger(ProxyManterAtracao.class.getName()).log(Level.SEVERE, null, ex);
         }
        return result; 
    }
    
}
