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
import model.domain.Comentario;
import model.service.interfaces.InterfaceManterComentario;
import util.db.exception.ExcecaoConexaoCliente;
import util.db.exception.ExcecaoNegocio;
import util.db.exception.ExcecaoPersistencia;

/**
 *
 * @author Aluno
 */
public class ProxyManterComentario implements InterfaceManterComentario {
    
    private ArrayList manterComentario = null;
    private Cliente cliente = null;
    /*
            ArrayList armazena:
            1) Tipo do Objeto (String)
            2) Operacao (cadastrar, alterar, excluir, pesquisar, etc)
            3) Parametro(s) da operacao (Objeto, String ou Long)
     */
   
    public ProxyManterComentario() throws SocketException, UnknownHostException{
         cliente = Cliente.getInstance();   
    }
         
    @Override
    public Long cadastrar(Comentario comentario) throws ExcecaoPersistencia, ExcecaoNegocio, ExcecaoConexaoCliente {
         manterComentario = new ArrayList();
         manterComentario.add("Comentario");
         manterComentario.add("cadastrar");
         manterComentario.add(comentario);
        
        //Indice de onde vai estar o long
        Long result = 0L;
        try {
            result = (Long) cliente.requisicao(manterComentario).get(0);
        } catch (IOException | ClassNotFoundException ex) {
             Logger.getLogger(ProxyManterComentario.class.getName()).log(Level.SEVERE, null, ex);
         }
        return result;
    }

    @Override
    public boolean alterar(Comentario comentario) throws ExcecaoPersistencia, ExcecaoNegocio, ExcecaoConexaoCliente {
        manterComentario = new ArrayList();
         manterComentario.add("Comentario");
         manterComentario.add("alterar");
         manterComentario.add(comentario);
        
        //Indice de onde vai estar o boolean
        boolean result = false;
        try {
            result = (boolean) cliente.requisicao(manterComentario).get(0);
        } catch (IOException | ClassNotFoundException ex) {
             Logger.getLogger(ProxyManterComentario.class.getName()).log(Level.SEVERE, null, ex);
         }
        return result;
    }

    @Override
    public boolean excluir(Comentario comentario) throws ExcecaoPersistencia, ExcecaoNegocio, ExcecaoConexaoCliente {
        manterComentario = new ArrayList();
         manterComentario.add("Comentario");
         manterComentario.add("excluir");
         manterComentario.add(comentario);
        
        //Indice de onde vai estar o boolean
        boolean result = false;
        try {
            result = (boolean) cliente.requisicao(manterComentario).get(0);
        } catch (IOException | ClassNotFoundException ex) {
             Logger.getLogger(ProxyManterComentario.class.getName()).log(Level.SEVERE, null, ex);
         }
        return result;
    }

    @Override
    public Comentario pesquisarPorId(Long seqComentario) throws ExcecaoPersistencia, ExcecaoConexaoCliente {
         manterComentario = new ArrayList();
         manterComentario.add("Comentario");
         manterComentario.add("pesquisarPorId");
         manterComentario.add(seqComentario);
        
        //Indice de onde vai estar o Comentario
        Comentario result = null;
        try {
            result = (Comentario) cliente.requisicao(manterComentario).get(0);
        } catch (IOException | ClassNotFoundException ex) {
             Logger.getLogger(ProxyManterComentario.class.getName()).log(Level.SEVERE, null, ex);
         }
        return result;
    }

    @Override
    public List<Comentario> pesquisarPorCodDiario(Long codDiario) throws ExcecaoPersistencia, ExcecaoConexaoCliente {
         manterComentario = new ArrayList();
         manterComentario.add("Comentario");
         manterComentario.add("pesquisarPorCodDiario");
         manterComentario.add(codDiario);
        
        //Indice de onde vai estar o List<Comentario>
        List<Comentario> result = null;
        try {
            result = (List<Comentario>) cliente.requisicao(manterComentario).get(0);
        } catch (IOException | ClassNotFoundException ex) {
             Logger.getLogger(ProxyManterComentario.class.getName()).log(Level.SEVERE, null, ex);
         }
        return result;
    }

    @Override
    public List<Comentario> pesquisarTodos() throws ExcecaoPersistencia, ExcecaoConexaoCliente {
         manterComentario = new ArrayList();
         manterComentario.add("Comentario");
         manterComentario.add("pesquisarTodos");
         
        
        //Indice de onde vai estar o List<Comentario>
        List<Comentario> result = null;
        try {
            result = (List<Comentario>) cliente.requisicao(manterComentario).get(0);
        } catch (IOException | ClassNotFoundException ex) {
             Logger.getLogger(ProxyManterComentario.class.getName()).log(Level.SEVERE, null, ex);
         }
        return result;
    }
    
 }

