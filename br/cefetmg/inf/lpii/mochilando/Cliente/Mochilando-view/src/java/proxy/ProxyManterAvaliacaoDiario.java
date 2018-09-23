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
import model.domain.AvaliacaoDiario;
import model.service.interfaces.InterfaceManterAvaliacaoDiario;
import util.db.exception.ExcecaoConexaoCliente;
import util.db.exception.ExcecaoNegocio;
import util.db.exception.ExcecaoPersistencia;

/**
 *
 * @author Aluno
 */
public class ProxyManterAvaliacaoDiario implements InterfaceManterAvaliacaoDiario{
    private ArrayList manterAvaliacaoDiario = null;
    private Cliente cliente = null;
    /*
            ArrayList armazena:
            1) Tipo do Objeto (String)
            2) Operacao (cadastrar, alterar, excluir, pesquisar, etc)
            3) Parametro(s) da operacao (Objeto, String ou Long)
     */
    public ProxyManterAvaliacaoDiario() throws SocketException, UnknownHostException{
         cliente = Cliente.getInstance();
    }
    
    @Override
    public Long cadastrar(AvaliacaoDiario avaliacaoDiario) throws ExcecaoPersistencia, ExcecaoNegocio, ExcecaoConexaoCliente {
         manterAvaliacaoDiario = new ArrayList();
         manterAvaliacaoDiario.add("AvaliacaoDiario");
         manterAvaliacaoDiario.add("cadastrar");
         manterAvaliacaoDiario.add(avaliacaoDiario);
        
        //Indice de onde vai estar o long
        Long result = 0L;
        try {
            result = (Long) cliente.requisicao(manterAvaliacaoDiario).get(0);
        } catch (IOException | ClassNotFoundException ex) {
             Logger.getLogger(ProxyManterAvaliacaoDiario.class.getName()).log(Level.SEVERE, null, ex);
         }
        return result;
    }

    @Override
    public boolean alterar(AvaliacaoDiario avaliacaoDiario) throws ExcecaoPersistencia, ExcecaoNegocio, ExcecaoConexaoCliente { 
         manterAvaliacaoDiario = new ArrayList();
         manterAvaliacaoDiario.add("AvaliacaoDiario");
         manterAvaliacaoDiario.add("alterar");
         manterAvaliacaoDiario.add(avaliacaoDiario);
        
        //Indice de onde vai estar o boolean
        boolean result = false;
        try {
            result = (boolean) cliente.requisicao(manterAvaliacaoDiario).get(0);
        } catch (IOException | ClassNotFoundException ex) {
             Logger.getLogger(ProxyManterAvaliacaoDiario.class.getName()).log(Level.SEVERE, null, ex);
         }
        return result;
    }

    @Override
    public boolean excluir(AvaliacaoDiario avaliacaoDiario) throws ExcecaoPersistencia, ExcecaoNegocio, ExcecaoConexaoCliente {
        manterAvaliacaoDiario = new ArrayList();
         manterAvaliacaoDiario.add("AvaliacaoDiario");
         manterAvaliacaoDiario.add("excluir");
         manterAvaliacaoDiario.add(avaliacaoDiario);
        
        //Indice de onde vai estar o boolean
        boolean result = false;
        try {
            result = (boolean) cliente.requisicao(manterAvaliacaoDiario).get(0);
        } catch (IOException | ClassNotFoundException ex) {
             Logger.getLogger(ProxyManterAvaliacaoDiario.class.getName()).log(Level.SEVERE, null, ex);
         }
        return result;
    }

    @Override
    public AvaliacaoDiario pesquisarPorId(Long seqAvaliacao) throws ExcecaoPersistencia, ExcecaoConexaoCliente {
        manterAvaliacaoDiario = new ArrayList();
         manterAvaliacaoDiario.add("AvaliacaoDiario");
         manterAvaliacaoDiario.add("pesquisarPorId");
         manterAvaliacaoDiario.add(seqAvaliacao);
        
        //Indice de onde vai estar o AvaliacaoDiario
        AvaliacaoDiario result = null;
        try {
            result = (AvaliacaoDiario) cliente.requisicao(manterAvaliacaoDiario).get(0);
        } catch (IOException | ClassNotFoundException ex) {
             Logger.getLogger(ProxyManterAvaliacaoDiario.class.getName()).log(Level.SEVERE, null, ex);
         }
        return result;
    }

    @Override
    public int pesquisarNumAvPositivas(Long codDiario) throws ExcecaoPersistencia, ExcecaoConexaoCliente {
         manterAvaliacaoDiario = new ArrayList();
         manterAvaliacaoDiario.add("AvaliacaoDiario");
         manterAvaliacaoDiario.add("pesquisarNumAvPositivas");
         manterAvaliacaoDiario.add(codDiario);
        
        //Indice de onde vai estar o int
        int result = 0;
        try {
            result = (int) cliente.requisicao(manterAvaliacaoDiario).get(0);
        } catch (IOException | ClassNotFoundException ex) {
             Logger.getLogger(ProxyManterAvaliacaoDiario.class.getName()).log(Level.SEVERE, null, ex);
         }
        return result;
    }

    @Override
    public int pesquisarNumAvNegativas(Long codDiario) throws ExcecaoPersistencia, ExcecaoConexaoCliente {
        manterAvaliacaoDiario = new ArrayList();
         manterAvaliacaoDiario.add("AvaliacaoDiario");
         manterAvaliacaoDiario.add("pesquisarNumAvNegativas");
         manterAvaliacaoDiario.add(codDiario);
        
        //Indice de onde vai estar o int
        int result = 0;
        try {
            result = (int) cliente.requisicao(manterAvaliacaoDiario).get(0);
        } catch (IOException | ClassNotFoundException ex) {
             Logger.getLogger(ProxyManterAvaliacaoDiario.class.getName()).log(Level.SEVERE, null, ex);
         }
        return result;
    }

    @Override
    public List<AvaliacaoDiario> pesquisarPorDiario(Long codDiario) throws ExcecaoPersistencia, ExcecaoConexaoCliente {
         manterAvaliacaoDiario = new ArrayList();
         manterAvaliacaoDiario.add("AvaliacaoDiario");
         manterAvaliacaoDiario.add("pesquisarPorDiario");
         manterAvaliacaoDiario.add(codDiario);
        
        //Indice de onde vai estar o List<AvaliacaoDiario>
        List<AvaliacaoDiario> result = null;
        try {
            result = (List<AvaliacaoDiario>) cliente.requisicao(manterAvaliacaoDiario).get(0);
        } catch (IOException | ClassNotFoundException ex) {
             Logger.getLogger(ProxyManterAvaliacaoDiario.class.getName()).log(Level.SEVERE, null, ex);
         }
        return result;
    }

    @Override
    public List<AvaliacaoDiario> pesquisarTodos() throws ExcecaoPersistencia, ExcecaoConexaoCliente {
        manterAvaliacaoDiario = new ArrayList();
         manterAvaliacaoDiario.add("AvaliacaoDiario");
         manterAvaliacaoDiario.add("pesquisarTodos");
        
        
        //Indice de onde vai estar o List<AvaliacaoDiario>
        List<AvaliacaoDiario> result = null;
        try {
            result = (List<AvaliacaoDiario>) cliente.requisicao(manterAvaliacaoDiario).get(0);
        } catch (IOException | ClassNotFoundException ex) {
             Logger.getLogger(ProxyManterAvaliacaoDiario.class.getName()).log(Level.SEVERE, null, ex);
         }
        return result;
    }
    
}
