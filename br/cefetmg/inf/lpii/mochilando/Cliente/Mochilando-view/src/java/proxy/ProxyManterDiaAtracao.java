package proxy;

import cliente.Cliente;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.domain.DiaAtracao;
import model.service.interfaces.InterfaceManterDiaAtracao;
import util.db.exception.ExcecaoConexaoCliente;
import util.db.exception.ExcecaoNegocio;
import util.db.exception.ExcecaoPersistencia;

public class ProxyManterDiaAtracao implements InterfaceManterDiaAtracao {
   private ArrayList manterDiaAtracao = null;
   private Cliente cliente = null;

    public ProxyManterDiaAtracao() throws SocketException, UnknownHostException {
        cliente = Cliente.getInstance();
    }

    @Override
    public Long cadastrar(DiaAtracao diaAtracao) throws ExcecaoPersistencia, ExcecaoNegocio, ExcecaoConexaoCliente  {
        manterDiaAtracao = new ArrayList();
        manterDiaAtracao.add("DiaAtracao");
        manterDiaAtracao.add("cadastrar");
        manterDiaAtracao.add(diaAtracao);
        
        Long result = 0L;
        try {
            result = (Long) cliente.requisicao(manterDiaAtracao).get(0);
        } catch (IOException | ClassNotFoundException ex) {
             Logger.getLogger(ProxyManterDiaAtracao.class.getName()).log(Level.SEVERE, null, ex);
         }
        return result;
    }

    @Override
    public boolean alterar(DiaAtracao diaAtracao) throws ExcecaoPersistencia, ExcecaoNegocio, ExcecaoConexaoCliente {
        manterDiaAtracao = new ArrayList();
        manterDiaAtracao.add("DiaAtracao");
        manterDiaAtracao.add("alterar");
        manterDiaAtracao.add(diaAtracao);
        
        boolean result = false;
        try {
            result = (boolean) cliente.requisicao(manterDiaAtracao).get(0);
        } catch (IOException | ClassNotFoundException ex) {
             Logger.getLogger(ProxyManterDiaAtracao.class.getName()).log(Level.SEVERE, null, ex);
         }
        return result;
    }

    @Override
    public boolean excluir(DiaAtracao diaAtracao) throws ExcecaoPersistencia, ExcecaoNegocio, ExcecaoConexaoCliente {
        manterDiaAtracao = new ArrayList();
        manterDiaAtracao.add("DiaAtracao");
        manterDiaAtracao.add("excluir");
        manterDiaAtracao.add(diaAtracao);
        
        boolean result = false;
        try {
            result = (boolean) cliente.requisicao(manterDiaAtracao).get(0);
        } catch (IOException | ClassNotFoundException ex) {
             Logger.getLogger(ProxyManterDiaAtracao.class.getName()).log(Level.SEVERE, null, ex);
         }
        return result;
    }

    @Override
    public DiaAtracao pesquisarPorId(Long seqDiaAtracao) throws ExcecaoPersistencia, ExcecaoConexaoCliente {
        manterDiaAtracao = new ArrayList();
        manterDiaAtracao.add("DiaAtracao");
        manterDiaAtracao.add("pesquisarPorId");
        manterDiaAtracao.add(seqDiaAtracao);
        
        DiaAtracao result = null;
        try {
            result = (DiaAtracao) cliente.requisicao(manterDiaAtracao).get(0);
        } catch (IOException | ClassNotFoundException ex) {
             Logger.getLogger(ProxyManterDiaAtracao.class.getName()).log(Level.SEVERE, null, ex);
         }
        return result;
    }

    @Override
    public List<DiaAtracao> pesquisarPorSeqDia(Long seqDia) throws ExcecaoPersistencia, ExcecaoConexaoCliente {
        manterDiaAtracao = new ArrayList();
        manterDiaAtracao.add("DiaAtracao");
        manterDiaAtracao.add("pesquisarSeqDia");
        manterDiaAtracao.add(seqDia);
        
        List<DiaAtracao> result = null;
        try {
            result = (List<DiaAtracao>) cliente.requisicao(manterDiaAtracao).get(0);
        } catch (IOException | ClassNotFoundException ex) {
             Logger.getLogger(ProxyManterDiaAtracao.class.getName()).log(Level.SEVERE, null, ex);
         }
        return result;
    }

    @Override
    public List<DiaAtracao> pesquisarTodos() throws ExcecaoPersistencia, ExcecaoConexaoCliente {
        manterDiaAtracao = new ArrayList();
        manterDiaAtracao.add("DiaAtracao");
        manterDiaAtracao.add("pesquisarTodos");
        
        List<DiaAtracao> result = null;
        try {
            result = (List<DiaAtracao>) cliente.requisicao(manterDiaAtracao).get(0);
        } catch (IOException | ClassNotFoundException ex) {
             Logger.getLogger(ProxyManterDiaAtracao.class.getName()).log(Level.SEVERE, null, ex);
         }
        return result;
    }
    
    
    
}
