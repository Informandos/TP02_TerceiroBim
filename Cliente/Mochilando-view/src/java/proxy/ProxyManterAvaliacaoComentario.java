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
import model.domain.AvaliacaoComentario;
import model.domain.AvaliacaoDiario;
import model.service.interfaces.InterfaceManterAvaliacaoComentario;
import util.db.exception.ExcecaoConexaoCliente;
import util.db.exception.ExcecaoNegocio;
import util.db.exception.ExcecaoPersistencia;

/**
 *
 * @author Aluno
 */
public class ProxyManterAvaliacaoComentario implements InterfaceManterAvaliacaoComentario {
    private ArrayList manterAvaliacaoComentario = null;
    private Cliente cliente = null;
    /*
            ArrayList armazena:
            1) Tipo do Objeto (String)
            2) Operacao (cadastrar, alterar, excluir, pesquisar, etc)
            3) Parametro(s) da operacao (Objeto, String ou Long)
     */
    public ProxyManterAvaliacaoComentario() throws SocketException, UnknownHostException{
         cliente = Cliente.getInstance();
    }
    
    @Override
    public Long cadastrar(AvaliacaoComentario avaliacaoComentario) throws ExcecaoPersistencia, ExcecaoNegocio, ExcecaoConexaoCliente {
        manterAvaliacaoComentario = new ArrayList();
        manterAvaliacaoComentario.add("AvaliacaoComentario");
        manterAvaliacaoComentario.add("cadastrar");
        manterAvaliacaoComentario.add(avaliacaoComentario);
        
        //Indice de onde vai estar o long
        Long result = 0L;
        try {
            result = (Long) cliente.requisicao(manterAvaliacaoComentario).get(0);
        } catch (IOException | ClassNotFoundException ex) {
             Logger.getLogger(ProxyManterAvaliacaoComentario.class.getName()).log(Level.SEVERE, null, ex);
         }
        return result;
    }

    @Override
    public boolean alterar(AvaliacaoComentario avaliacaoComentario) throws ExcecaoPersistencia, ExcecaoNegocio, ExcecaoConexaoCliente {
        manterAvaliacaoComentario = new ArrayList();
        manterAvaliacaoComentario.add("AvaliacaoComentario");
        manterAvaliacaoComentario.add("alterar");
        manterAvaliacaoComentario.add(avaliacaoComentario);
        
        //Indice de onde vai estar o boolean
        boolean result = false;
        try {
            result = (Boolean) cliente.requisicao(manterAvaliacaoComentario).get(0);
        } catch (IOException | ClassNotFoundException ex) {
             Logger.getLogger(ProxyManterAvaliacaoComentario.class.getName()).log(Level.SEVERE, null, ex);
         }
        return result;
    }

    @Override
    public boolean excluir(AvaliacaoComentario avaliacaoComentario) throws ExcecaoPersistencia, ExcecaoNegocio, ExcecaoConexaoCliente {
        manterAvaliacaoComentario = new ArrayList();
        manterAvaliacaoComentario.add("AvaliacaoComentario");
        manterAvaliacaoComentario.add("excluir");
        manterAvaliacaoComentario.add(avaliacaoComentario);
        
        //Indice de onde vai estar o boolean
        boolean result = false;
        try {
            result = (Boolean) cliente.requisicao(manterAvaliacaoComentario).get(0);
        } catch (IOException | ClassNotFoundException ex) {
             Logger.getLogger(ProxyManterAvaliacaoComentario.class.getName()).log(Level.SEVERE, null, ex);
         }
        return result;
    
    }

    @Override
    public AvaliacaoComentario pesquisarPorId(Long seqAvaliacao) throws ExcecaoPersistencia, ExcecaoConexaoCliente {
        manterAvaliacaoComentario = new ArrayList();
        manterAvaliacaoComentario.add("AvaliacaoComentario");
        manterAvaliacaoComentario.add("pesquisarPorId");
        manterAvaliacaoComentario.add(seqAvaliacao);
       AvaliacaoComentario result = null;
        try {
            result = (AvaliacaoComentario) cliente.requisicao(manterAvaliacaoComentario).get(0);
        } catch (IOException | ClassNotFoundException ex) {
             Logger.getLogger(ProxyManterAvaliacaoComentario.class.getName()).log(Level.SEVERE, null, ex);
         }
        return result; 
        
    }

    @Override
    public int pesquisarNumAvPositivas(Long seqComentario) throws ExcecaoPersistencia, ExcecaoConexaoCliente {
        manterAvaliacaoComentario = new ArrayList();
        manterAvaliacaoComentario.add("AvaliacaoComentario");
        manterAvaliacaoComentario.add("pesquisarNumAvPositivas");
        manterAvaliacaoComentario.add(seqComentario);
       int result = 0;
        try {
            result = (int) cliente.requisicao(manterAvaliacaoComentario).get(0);
        } catch (IOException | ClassNotFoundException ex) {
             Logger.getLogger(ProxyManterAvaliacaoComentario.class.getName()).log(Level.SEVERE, null, ex);
         }
        return result; 
    }

    @Override
    public int pesquisarNumAvNegativas(Long seqComentario) throws ExcecaoPersistencia, ExcecaoConexaoCliente {
         manterAvaliacaoComentario = new ArrayList();
        manterAvaliacaoComentario.add("AvaliacaoComentario");
        manterAvaliacaoComentario.add("pesquisarNumAvNegativas");
        manterAvaliacaoComentario.add(seqComentario);
       int result = 0;
        try {
            result = (int) cliente.requisicao(manterAvaliacaoComentario).get(0);
        } catch (IOException | ClassNotFoundException ex) {
             Logger.getLogger(ProxyManterAvaliacaoComentario.class.getName()).log(Level.SEVERE, null, ex);
         }
        return result; 
    }

    @Override
    public List<AvaliacaoComentario> pesquisarPorDiario(Long codDiario) throws ExcecaoPersistencia, ExcecaoConexaoCliente {
        manterAvaliacaoComentario = new ArrayList();
        manterAvaliacaoComentario.add("AvaliacaoComentario");
        manterAvaliacaoComentario.add("pesquisarPorDiario");
        manterAvaliacaoComentario.add(codDiario);
       List<AvaliacaoComentario> result = null;
        try {
            result = ( List<AvaliacaoComentario>) cliente.requisicao(manterAvaliacaoComentario).get(0);
        } catch (IOException | ClassNotFoundException ex) {
             Logger.getLogger(ProxyManterAvaliacaoComentario.class.getName()).log(Level.SEVERE, null, ex);
         }
        return result; 
    }

    @Override
    public List<AvaliacaoComentario> pesquisarTodos(Long seqAvaliacao) throws ExcecaoPersistencia, ExcecaoConexaoCliente {
         manterAvaliacaoComentario = new ArrayList();
        manterAvaliacaoComentario.add("AvaliacaoComentario");
        manterAvaliacaoComentario.add("pesquisarTodos");
        manterAvaliacaoComentario.add(seqAvaliacao);
       List<AvaliacaoComentario> result = null;
        try {
            result = ( List<AvaliacaoComentario>) cliente.requisicao(manterAvaliacaoComentario).get(0);
        } catch (IOException | ClassNotFoundException ex) {
             Logger.getLogger(ProxyManterAvaliacaoComentario.class.getName()).log(Level.SEVERE, null, ex);
         }
        return result; 
    }
    
}
