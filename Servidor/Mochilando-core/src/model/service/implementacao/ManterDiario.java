/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service.implementacao;

import java.util.List;
import model.dao.implementacao.DiarioDAO;
import model.dao.interfaces.InterfaceDiarioDAO;
import model.domain.Diario;
import model.service.interfaces.InterfaceManterDiario;
import util.db.exception.ExcecaoNegocio;
import util.db.exception.ExcecaoPersistencia;

/**
 *
 * @author Juliana
 */
public class ManterDiario implements InterfaceManterDiario {
    
    private InterfaceDiarioDAO diarioDAO;
    
    public ManterDiario(){
        diarioDAO = new DiarioDAO();
    }

    @Override
    public Long cadastrar(Diario diario) throws ExcecaoPersistencia, ExcecaoNegocio {
        if(diario.getUsuario().getCodUsuario() == null ){
            throw new ExcecaoNegocio("Obrigatório informar o usuario proprietario do diario");
        }
        if(diario.getNomDiario() == null || diario.getNomDiario().isEmpty()){
            throw new ExcecaoNegocio("Obrigatório informar o nome (titulo) do diario");
        }
        if(diario.getDatPublicacao() == null){
            throw new ExcecaoNegocio("Obrigatório informar data de publicacao");
        }
        if(diario.getDatInicioViagem() == null){
            throw new ExcecaoNegocio("Obrigatório informar data de inicio da viagem");
        }
        if(diario.getDatFimViagem() == null){
            throw new ExcecaoNegocio("Obrigatório informar data de fim da viagem");
        }
        if(diario.getTxtDiario() == null || diario.getTxtDiario().isEmpty()){
            throw new ExcecaoNegocio("Obrigatório informar texto do diario");
        }
        if(diario.getTipoDiario() == null || diario.getTipoDiario().isEmpty()){
            throw new ExcecaoNegocio("Obrigatório informar tipo do diario");
        }
        Long result = diarioDAO.inserir(diario);
        return result;
    }

    @Override
    public boolean alterar(Diario diario) throws ExcecaoPersistencia, ExcecaoNegocio {
        if(diario.getUsuario().getCodUsuario() == null ){
            throw new ExcecaoNegocio("Obrigatório informar o usuario proprietario do diario");
        }
        if(diario.getNomDiario() == null || diario.getNomDiario().isEmpty()){
            throw new ExcecaoNegocio("Obrigatório informar o nome (titulo) do diario");
        }
        if(diario.getDatPublicacao() == null){
            throw new ExcecaoNegocio("Obrigatório informar data de publicacao");
        }
        if(diario.getDatInicioViagem() == null){
            throw new ExcecaoNegocio("Obrigatório informar data de inicio da viagem");
        }
        if(diario.getDatFimViagem() == null){
            throw new ExcecaoNegocio("Obrigatório informar data de fim da viagem");
        }
        if(diario.getTxtDiario() == null || diario.getTxtDiario().isEmpty()){
            throw new ExcecaoNegocio("Obrigatório informar texto do diario");
        }
        boolean result = diarioDAO.atualizar(diario);
        return result;
    }

    @Override
    public boolean excluir(Diario diario) throws ExcecaoPersistencia, ExcecaoNegocio {
        boolean result = diarioDAO.deletar(diario);
        return result; 
    }

    @Override
    public Diario pesquisarPorId(Long codDiario) throws ExcecaoPersistencia {
        Diario result = diarioDAO.consultarPorId(codDiario);
        return result;
    }

    @Override
    public List<Diario> pesquisarTodos() throws ExcecaoPersistencia {
        List<Diario> result = diarioDAO.listarTudo();
        return result;
    }

    @Override
    public List<Diario> pesquisarPorCodUsuario(Long codUsuario) throws ExcecaoPersistencia {
        List<Diario> result = diarioDAO.listarPorCodUsuario(codUsuario);
        return result;
    }

    @Override
    public List<Diario> pesquisarPorCodCidade(Long codCidade) throws ExcecaoPersistencia {
        List<Diario> result = diarioDAO.listarPorCodCidade(codCidade);
        return result;
    }

    @Override
    public List<Diario> pesquisarPorCodEstado(Long codEstado) throws ExcecaoPersistencia {
        List<Diario> result = diarioDAO.listarPorCodCidade(codEstado);
        return result;
    }
    
}
