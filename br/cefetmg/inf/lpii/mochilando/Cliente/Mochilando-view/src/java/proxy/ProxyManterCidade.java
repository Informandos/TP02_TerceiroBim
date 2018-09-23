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
import model.domain.Cidade;
import model.service.interfaces.InterfaceManterCidade;
import util.db.exception.ExcecaoConexaoCliente;
import util.db.exception.ExcecaoNegocio;
import util.db.exception.ExcecaoPersistencia;

/**
 *
 * @author Aluno
 */
public class ProxyManterCidade implements InterfaceManterCidade {
    private ArrayList manterCidade = null;
    private Cliente cliente = null;
    /*
            ArrayList armazena:
            1) Tipo do Objeto (String)
            2) Operacao (cadastrar, alterar, excluir, pesquisar, etc)
            3) Parametro(s) da operacao (Objeto, String ou Long)
     */
    public ProxyManterCidade() throws SocketException, UnknownHostException{
         cliente = Cliente.getInstance();   
    }

    @Override
    public Long cadastrar(Cidade cidade) throws ExcecaoPersistencia, ExcecaoNegocio, ExcecaoConexaoCliente {
         
         manterCidade = new ArrayList();
         manterCidade.add("Cidade");
         manterCidade.add("cadastrar");
         manterCidade.add(cidade);
        
        //Indice de onde vai estar o long
        Long result = 0L;
        try {
            result = (Long) cliente.requisicao(manterCidade).get(0);
        } catch (IOException | ClassNotFoundException ex) {
             Logger.getLogger(ProxyManterCidade.class.getName()).log(Level.SEVERE, null, ex);
         }
        return result;
    }

    @Override
    public boolean alterar(Cidade cidade) throws ExcecaoPersistencia, ExcecaoNegocio, ExcecaoConexaoCliente {
        manterCidade = new ArrayList();
         manterCidade.add("Cidade");
         manterCidade.add("alterar");
         manterCidade.add(cidade);
        
        //Indice de onde vai estar o boolean
        boolean result = false;
        try {
            result = (boolean) cliente.requisicao(manterCidade).get(0);
        } catch (IOException | ClassNotFoundException ex) {
             Logger.getLogger(ProxyManterCidade.class.getName()).log(Level.SEVERE, null, ex);
         }
        return result;
    }

    @Override
    public boolean excluir(Cidade cidade) throws ExcecaoPersistencia, ExcecaoNegocio, ExcecaoConexaoCliente {
        manterCidade = new ArrayList();
         manterCidade.add("Cidade");
         manterCidade.add("excluir");
         manterCidade.add(cidade);
        
        //Indice de onde vai estar o boolean
        boolean result = false;
        try {
            result = (boolean) cliente.requisicao(manterCidade).get(0);
        } catch (IOException | ClassNotFoundException ex) {
             Logger.getLogger(ProxyManterCidade.class.getName()).log(Level.SEVERE, null, ex);
         }
        return result;
    }

    @Override
    public Cidade pesquisarPorId(Long codCidade) throws ExcecaoPersistencia, ExcecaoConexaoCliente {
        manterCidade = new ArrayList();
         manterCidade.add("Cidade");
         manterCidade.add("pesquisarPorId");
         manterCidade.add(codCidade);
        
        //Indice de onde vai estar o Cidade
        Cidade result = null;
        try {
            result = (Cidade) cliente.requisicao(manterCidade).get(0);
        } catch (IOException | ClassNotFoundException ex) {
             Logger.getLogger(ProxyManterCidade.class.getName()).log(Level.SEVERE, null, ex);
         }
        return result;
    }

    @Override
    public List<Cidade> pesquisarPorCodEstado(Long codEstado) throws ExcecaoPersistencia, ExcecaoConexaoCliente {
         manterCidade = new ArrayList();
         manterCidade.add("Cidade");
         manterCidade.add("pesquisarPorCodEstado");
         manterCidade.add(codEstado);
        
        //Indice de onde vai estar o List<Cidade> 
        List<Cidade> result = null;
        try {
            result = (List<Cidade>) cliente.requisicao(manterCidade).get(0);
        } catch (IOException | ClassNotFoundException ex) {
             Logger.getLogger(ProxyManterCidade.class.getName()).log(Level.SEVERE, null, ex);
         }
        return result;
    }

    @Override
    public List<Cidade> pesquisarTodos() throws ExcecaoPersistencia, ExcecaoConexaoCliente {
         manterCidade = new ArrayList();
         manterCidade.add("Cidade");
         manterCidade.add("pesquisarTodos");
         
        
        //Indice de onde vai estar o List<Cidade> 
        List<Cidade> result = null;
        try {
            result = (List<Cidade>) cliente.requisicao(manterCidade).get(0);
        } catch (IOException | ClassNotFoundException ex) {
             Logger.getLogger(ProxyManterCidade.class.getName()).log(Level.SEVERE, null, ex);
         }
        return result;
    }
}
