/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service.implementacao;

import java.util.List;
import model.dao.implementacao.EstadoDAO;
import model.dao.interfaces.InterfaceEstadoDAO;
import model.domain.Estado;
import model.service.interfaces.InterfaceManterEstado;
import util.db.exception.ExcecaoNegocio;
import util.db.exception.ExcecaoPersistencia;

/**
 *
 * @author Juliana
 */
public class ManterEstado implements InterfaceManterEstado {
    
    private InterfaceEstadoDAO estadoDAO;
    
    public ManterEstado() {
        estadoDAO = new EstadoDAO();
    }

    @Override
    public Long cadastrar(Estado estado) throws ExcecaoPersistencia, ExcecaoNegocio {
        if((estado.getNomEstado() == null) || (estado.getNomEstado().isEmpty()))
            throw new ExcecaoNegocio("Obrigatório informar o nome do estado.");
        
        if((estado.getSigla() == null) || (estado.getSigla().isEmpty()))
            throw new ExcecaoNegocio("Obrigatório informar a sigla.");
                  
        Long result = estadoDAO.inserir(estado);
        return result;
    }

    @Override
    public boolean alterar(Estado estado) throws ExcecaoPersistencia, ExcecaoNegocio {
        if((estado.getNomEstado() == null) || (estado.getNomEstado().isEmpty()))
            throw new ExcecaoNegocio("Obrigatório informar o nome do estado.");
        
        if((estado.getSigla() == null) || (estado.getSigla().isEmpty()))
            throw new ExcecaoNegocio("Obrigatório informar a sigla.");
                  
        boolean result = estadoDAO.atualizar(estado);
        return result;
    }

    @Override
    public boolean excluir(Estado estado) throws ExcecaoPersistencia, ExcecaoNegocio {
        boolean result = estadoDAO.deletar(estado);
        return result;   
    }

    @Override
    public Estado pesquisarPorId(Long codEstado) throws ExcecaoPersistencia {
        Estado result = estadoDAO.consultarPorId(codEstado);
        return result;
    }

    @Override
    public Estado pesquisarPorSigla(String sigla) throws ExcecaoPersistencia {
       Estado result = estadoDAO.consultarPorSigla(sigla);
       return result; 
    }

    @Override
    public List<Estado> pesquisarTodos() throws ExcecaoPersistencia {
        List<Estado> result = estadoDAO.listarTudo();
        return result;
    }
    
}
