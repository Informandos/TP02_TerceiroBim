package proxy;

import cliente.Cliente;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.domain.Foto;
import model.service.interfaces.InterfaceManterFoto;
import util.db.exception.ExcecaoConexaoCliente;
import util.db.exception.ExcecaoNegocio;
import util.db.exception.ExcecaoPersistencia;

public class ProxyManterFoto implements InterfaceManterFoto{
    private ArrayList manterFoto = null;
    private Cliente cliente = null;

    public ProxyManterFoto() throws SocketException, UnknownHostException{
        cliente = Cliente.getInstance();
    }

    @Override
    public Long cadastrar(Foto foto) throws ExcecaoPersistencia, ExcecaoNegocio, ExcecaoConexaoCliente {
        manterFoto = new ArrayList();
        manterFoto.add("Foto");
        manterFoto.add("cadastrar");
        manterFoto.add(foto);
        
        Long result = 0L;
        try {
            result = (Long) cliente.requisicao(manterFoto).get(0);
        } catch (IOException | ClassNotFoundException ex) {
             Logger.getLogger(ProxyManterFoto.class.getName()).log(Level.SEVERE, null, ex);
         }
        return result;
    }

    @Override
    public boolean alterar(Foto foto) throws ExcecaoPersistencia, ExcecaoNegocio, ExcecaoConexaoCliente {
        manterFoto = new ArrayList();
        manterFoto.add("Foto");
        manterFoto.add("alterar");
        manterFoto.add(foto);
        
        boolean result = false;
        try {
            result = (boolean) cliente.requisicao(manterFoto).get(0);
        } catch (IOException | ClassNotFoundException ex) {
             Logger.getLogger(ProxyManterFoto.class.getName()).log(Level.SEVERE, null, ex);
         }
        return result;
    }

    @Override
    public boolean excluir(Foto foto) throws ExcecaoPersistencia, ExcecaoNegocio, ExcecaoConexaoCliente {
        manterFoto = new ArrayList();
        manterFoto.add("Foto");
        manterFoto.add("excluir");
        manterFoto.add(foto);
        
        boolean result = false;
        try {
            result = (boolean) cliente.requisicao(manterFoto).get(0);
        } catch (IOException | ClassNotFoundException ex) {
             Logger.getLogger(ProxyManterFoto.class.getName()).log(Level.SEVERE, null, ex);
         }
        return result;
    }

    @Override
    public Foto pesquisarPorId(Long seqFoto) throws ExcecaoPersistencia, ExcecaoConexaoCliente {
        manterFoto = new ArrayList();
        manterFoto.add("Foto");
        manterFoto.add("pesquisarPorId");
        manterFoto.add(seqFoto);
        
        Foto result = null;
        try {
            result = (Foto) cliente.requisicao(manterFoto).get(0);
        } catch (IOException | ClassNotFoundException ex) {
             Logger.getLogger(ProxyManterFoto.class.getName()).log(Level.SEVERE, null, ex);
         }
        return result;
    }

    @Override
    public List<Foto> pesquisarTodos() throws ExcecaoPersistencia, ExcecaoConexaoCliente {
        manterFoto = new ArrayList();
        manterFoto.add("Foto");
        manterFoto.add("pesquisarTodos");
        
        List<Foto> result = null;
        try {
            result = (List<Foto>) cliente.requisicao(manterFoto).get(0);
        } catch (IOException | ClassNotFoundException ex) {
             Logger.getLogger(ProxyManterFoto.class.getName()).log(Level.SEVERE, null, ex);
         }
        return result;
    }

    @Override
    public List<Foto> pesquisarPorDia(Long seqDia) throws ExcecaoPersistencia, ExcecaoConexaoCliente {
        manterFoto = new ArrayList();
        manterFoto.add("Foto");
        manterFoto.add("pesquisarTodos");
        manterFoto.add(seqDia);
        
        List<Foto> result = null;
        try {
            result = (List<Foto>) cliente.requisicao(manterFoto).get(0);
        } catch (IOException | ClassNotFoundException ex) {
             Logger.getLogger(ProxyManterFoto.class.getName()).log(Level.SEVERE, null, ex);
         }
        return result;
    }
    
}
