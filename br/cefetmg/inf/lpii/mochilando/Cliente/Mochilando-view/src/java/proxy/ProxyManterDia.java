package proxy;

import cliente.Cliente;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.domain.Dia;
import model.service.interfaces.InterfaceManterDia;
import util.db.exception.ExcecaoConexaoCliente;
import util.db.exception.ExcecaoNegocio;
import util.db.exception.ExcecaoPersistencia;

public class ProxyManterDia implements InterfaceManterDia{

    private ArrayList manterDia = null;
    private Cliente cliente = null;
    
    public ProxyManterDia() throws SocketException, UnknownHostException{
         cliente = Cliente.getInstance();
    }
    
    @Override
    public Long cadastrar(Dia dia) throws ExcecaoPersistencia, ExcecaoNegocio, ExcecaoConexaoCliente {
        manterDia = new ArrayList();
        manterDia.add("Dia");
        manterDia.add("cadastrar");
        manterDia.add(dia);
        
        Long result = 0L;
        try {
            result = (Long) cliente.requisicao(manterDia).get(0);
        } catch (IOException | ClassNotFoundException ex) {
             Logger.getLogger(ProxyManterDia.class.getName()).log(Level.SEVERE, null, ex);
         }
        return result;
    }

    @Override
    public boolean alterar(Dia dia) throws ExcecaoPersistencia, ExcecaoNegocio, ExcecaoConexaoCliente {
        manterDia = new ArrayList();
        manterDia.add("Dia");
        manterDia.add("alterar");
        manterDia.add(dia);
        
        boolean result = false;
        try {
            result = (boolean) cliente.requisicao(manterDia).get(0);
        } catch (IOException | ClassNotFoundException ex) {
             Logger.getLogger(ProxyManterDia.class.getName()).log(Level.SEVERE, null, ex);
         }
        return result;
    }

    @Override
    public boolean excluir(Dia dia) throws ExcecaoPersistencia, ExcecaoNegocio, ExcecaoConexaoCliente {
        manterDia = new ArrayList();
        manterDia.add("Dia");
        manterDia.add("excluir");
        manterDia.add(dia);
        
        boolean result = false;
        try {
            result = (boolean) cliente.requisicao(manterDia).get(0);
        } catch (IOException | ClassNotFoundException ex) {
             Logger.getLogger(ProxyManterDia.class.getName()).log(Level.SEVERE, null, ex);
         }
        return result;
    }

    @Override
    public Dia pesquisarPorId(Long seqDia) throws ExcecaoPersistencia, ExcecaoConexaoCliente {
        manterDia = new ArrayList();
        manterDia.add("Dia");
        manterDia.add("pesquisarPorId");
        manterDia.add(seqDia);
        
        Dia result = null;
        try {
            result = (Dia) cliente.requisicao(manterDia).get(0);
        } catch (IOException | ClassNotFoundException ex) {
             Logger.getLogger(ProxyManterDia.class.getName()).log(Level.SEVERE, null, ex);
         }
        return result;
    }

    @Override
    public List<Dia> pesquisarPorCodDiario(Long codDiario) throws ExcecaoPersistencia, ExcecaoConexaoCliente {
        manterDia = new ArrayList();
        manterDia.add("Dia");
        manterDia.add("pesquisarCodDiario");
        manterDia.add(codDiario);
        
        List<Dia> result = null;
        try {
            result = (List<Dia>) cliente.requisicao(manterDia).get(0);
        } catch (IOException | ClassNotFoundException ex) {
             Logger.getLogger(ProxyManterDia.class.getName()).log(Level.SEVERE, null, ex);
         }
        return result;
    }

    @Override
    public List<Dia> pesquisarTodos() throws ExcecaoPersistencia, ExcecaoConexaoCliente {
        manterDia = new ArrayList();
        manterDia.add("Dia");
        manterDia.add("pesquisarTodos");
        
        List<Dia> result = null;
        try {
            result = (List<Dia>) cliente.requisicao(manterDia).get(0);
        } catch (IOException | ClassNotFoundException ex) {
             Logger.getLogger(ProxyManterDia.class.getName()).log(Level.SEVERE, null, ex);
         }
        return result;
    }
    
}
