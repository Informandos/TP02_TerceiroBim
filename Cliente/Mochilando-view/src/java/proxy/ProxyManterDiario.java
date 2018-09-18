package proxy;

import cliente.Cliente;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.domain.Diario;
import model.service.interfaces.InterfaceManterDiario;
import util.db.exception.ExcecaoConexaoCliente;
import util.db.exception.ExcecaoNegocio;
import util.db.exception.ExcecaoPersistencia;

public class ProxyManterDiario implements InterfaceManterDiario{
    private ArrayList manterDiario = null;
    private Cliente cliente = null;

    public ProxyManterDiario() throws SocketException, UnknownHostException{
        cliente = Cliente.getInstance();
    }

    @Override
    public Long cadastrar(Diario diario) throws ExcecaoPersistencia, ExcecaoNegocio, ExcecaoConexaoCliente {
        manterDiario = new ArrayList();
        manterDiario.add("Diario");
        manterDiario.add("cadastrar");
        manterDiario.add(diario);
        
        Long result = 0L;
        try {
            result = (Long) cliente.requisicao(manterDiario).get(0);
        } catch (IOException | ClassNotFoundException ex) {
             Logger.getLogger(ProxyManterDiario.class.getName()).log(Level.SEVERE, null, ex);
         }
        return result;
    }

    @Override
    public boolean alterar(Diario diario) throws ExcecaoPersistencia, ExcecaoNegocio, ExcecaoConexaoCliente {
        manterDiario = new ArrayList();
        manterDiario.add("Diario");
        manterDiario.add("alterar");
        manterDiario.add(diario);
        
        boolean result = false;
        try {
            result = (boolean) cliente.requisicao(manterDiario).get(0);
        } catch (IOException | ClassNotFoundException ex) {
             Logger.getLogger(ProxyManterDiario.class.getName()).log(Level.SEVERE, null, ex);
         }
        return result;
    }

    @Override
    public boolean excluir(Diario diario) throws ExcecaoPersistencia, ExcecaoNegocio, ExcecaoConexaoCliente {
        manterDiario = new ArrayList();
        manterDiario.add("Diario");
        manterDiario.add("excluir");
        manterDiario.add(diario);
        
        boolean result = false;
        try {
            result = (boolean) cliente.requisicao(manterDiario).get(0);
        } catch (IOException | ClassNotFoundException ex) {
             Logger.getLogger(ProxyManterDiario.class.getName()).log(Level.SEVERE, null, ex);
         }
        return result;
    }

    @Override
    public Diario pesquisarPorId(Long codDiario) throws ExcecaoPersistencia, ExcecaoConexaoCliente {
        manterDiario = new ArrayList();
        manterDiario.add("Diario");
        manterDiario.add("pesquisarPorId");
        manterDiario.add(codDiario);
        
        Diario result = null;
        try {
            result = (Diario) cliente.requisicao(manterDiario).get(0);
        } catch (IOException | ClassNotFoundException ex) {
             Logger.getLogger(ProxyManterDiario.class.getName()).log(Level.SEVERE, null, ex);
         }
        return result;
    }

    @Override
    public List<Diario> pesquisarTodos() throws ExcecaoPersistencia, ExcecaoConexaoCliente {
        manterDiario = new ArrayList();
        manterDiario.add("Diario");
        manterDiario.add("pesquisarTodos");
        
        List<Diario> result = null;
        try {
            result = (List<Diario>) cliente.requisicao(manterDiario).get(0);
        } catch (IOException | ClassNotFoundException ex) {
             Logger.getLogger(ProxyManterDiario.class.getName()).log(Level.SEVERE, null, ex);
         }
        return result;
    }

    @Override
    public List<Diario> pesquisarPorCodUsuario(Long codUsuario) throws ExcecaoPersistencia, ExcecaoConexaoCliente {
        manterDiario = new ArrayList();
        manterDiario.add("Diario");
        manterDiario.add("pesquisarCodUsuario");
        manterDiario.add(codUsuario);
        
        List<Diario> result = null;
        try {
            result = (List<Diario>) cliente.requisicao(manterDiario).get(0);
        } catch (IOException | ClassNotFoundException ex) {
             Logger.getLogger(ProxyManterDiario.class.getName()).log(Level.SEVERE, null, ex);
         }
        return result;
    }

    @Override
    public List<Diario> pesquisarPorCodCidade(Long codCidade) throws ExcecaoPersistencia, ExcecaoConexaoCliente {
        manterDiario = new ArrayList();
        manterDiario.add("Diario");
        manterDiario.add("pesquisarCodUsuario");
        manterDiario.add(codCidade);
        
        List<Diario> result = null;
        try {
            result = (List<Diario>) cliente.requisicao(manterDiario).get(0);
        } catch (IOException | ClassNotFoundException ex) {
             Logger.getLogger(ProxyManterDiario.class.getName()).log(Level.SEVERE, null, ex);
         }
        return result;
    }

    @Override
    public List<Diario> pesquisarPorCodEstado(Long codEstado) throws ExcecaoPersistencia, ExcecaoConexaoCliente {
        manterDiario = new ArrayList();
        manterDiario.add("Diario");
        manterDiario.add("pesquisarCodUsuario");
        manterDiario.add(codEstado);
        
        List<Diario> result = null;
        try {
            result = (List<Diario>) cliente.requisicao(manterDiario).get(0);
        } catch (IOException | ClassNotFoundException ex) {
             Logger.getLogger(ProxyManterDiario.class.getName()).log(Level.SEVERE, null, ex);
         }
        return result;
    }
    
    
}
