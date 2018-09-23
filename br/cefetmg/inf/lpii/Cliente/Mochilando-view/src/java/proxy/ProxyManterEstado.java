package proxy;

import cliente.Cliente;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.domain.Estado;
import model.service.interfaces.InterfaceManterEstado;
import util.db.exception.ExcecaoConexaoCliente;
import util.db.exception.ExcecaoNegocio;
import util.db.exception.ExcecaoPersistencia;

public class ProxyManterEstado implements InterfaceManterEstado{
   private ArrayList manterEstado = null;
   private Cliente cliente = null;

   public ProxyManterEstado() throws SocketException, UnknownHostException{
        cliente = Cliente.getInstance();
   }

    @Override
    public Long cadastrar(Estado estado) throws ExcecaoPersistencia, ExcecaoNegocio, ExcecaoConexaoCliente {
        manterEstado = new ArrayList();
        manterEstado.add("Estado");
        manterEstado.add("cadastrar");
        manterEstado.add(estado);
        
        Long result = 0L;
        try {
            result = (Long) cliente.requisicao(manterEstado).get(0);
        } catch (IOException | ClassNotFoundException ex) {
             Logger.getLogger(ProxyManterEstado.class.getName()).log(Level.SEVERE, null, ex);
         }
        return result;
    }

    @Override
    public boolean alterar(Estado estado) throws ExcecaoPersistencia, ExcecaoNegocio, ExcecaoConexaoCliente {
        manterEstado = new ArrayList();
        manterEstado.add("Estado");
        manterEstado.add("alterar");
        manterEstado.add(estado);
        
        boolean result = false;
        try {
            result = (boolean) cliente.requisicao(manterEstado).get(0);
        } catch (IOException | ClassNotFoundException ex) {
             Logger.getLogger(ProxyManterEstado.class.getName()).log(Level.SEVERE, null, ex);
         }
        return result;
    }

    @Override
    public boolean excluir(Estado estado) throws ExcecaoPersistencia, ExcecaoNegocio, ExcecaoConexaoCliente {
        manterEstado = new ArrayList();
        manterEstado.add("Estado");
        manterEstado.add("excluir");
        manterEstado.add(estado);
        
        boolean result = false;
        try {
            result = (boolean) cliente.requisicao(manterEstado).get(0);
        } catch (IOException | ClassNotFoundException ex) {
             Logger.getLogger(ProxyManterEstado.class.getName()).log(Level.SEVERE, null, ex);
         }
        return result;
    }

    @Override
    public Estado pesquisarPorId(Long codEstado) throws ExcecaoPersistencia, ExcecaoConexaoCliente {
        manterEstado = new ArrayList();
        manterEstado.add("Estado");
        manterEstado.add("pesquisarPorId");
        manterEstado.add(codEstado);
        
        Estado result = null;
        try {
            result = (Estado) cliente.requisicao(manterEstado).get(0);
        } catch (IOException | ClassNotFoundException ex) {
             Logger.getLogger(ProxyManterEstado.class.getName()).log(Level.SEVERE, null, ex);
         }
        return result;
    }

    @Override
    public Estado pesquisarPorSigla(String sigla) throws ExcecaoPersistencia, ExcecaoConexaoCliente {
        manterEstado = new ArrayList();
        manterEstado.add("Estado");
        manterEstado.add("pesquisarPorSigla");
        manterEstado.add(sigla);
        
        Estado result = null;
        try {
            result = (Estado) cliente.requisicao(manterEstado).get(0);
        } catch (IOException | ClassNotFoundException ex) {
             Logger.getLogger(ProxyManterEstado.class.getName()).log(Level.SEVERE, null, ex);
         }
        return result;
    }

    @Override
    public List<Estado> pesquisarTodos() throws ExcecaoPersistencia, ExcecaoConexaoCliente {
        manterEstado = new ArrayList();
        manterEstado.add("Estado");
        manterEstado.add("pesquisarTodos");
        
        List<Estado> result = null;
        try {
            result = (List<Estado>) cliente.requisicao(manterEstado).get(0);
        } catch (IOException | ClassNotFoundException ex) {
             Logger.getLogger(ProxyManterEstado.class.getName()).log(Level.SEVERE, null, ex);
         }
        return result;
    }
   
   
   
}
