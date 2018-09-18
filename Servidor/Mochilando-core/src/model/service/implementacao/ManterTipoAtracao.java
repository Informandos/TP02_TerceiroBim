/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service.implementacao;

import java.util.List;
import model.dao.implementacao.TipoAtracaoDAO;
import model.dao.interfaces.InterfaceTipoAtracaoDAO;
import model.domain.TipoAtracao;
import model.service.interfaces.InterfaceManterTipoAtracao;
import util.db.exception.ExcecaoNegocio;
import util.db.exception.ExcecaoPersistencia;

/**
 *
 * @author Juliana
 */
public class ManterTipoAtracao implements InterfaceManterTipoAtracao {

    private InterfaceTipoAtracaoDAO tipoAtracaoDAO;

    public ManterTipoAtracao() {
        tipoAtracaoDAO = new TipoAtracaoDAO();
    }

    @Override
    public Long cadastrar(TipoAtracao tipoAtracao) throws ExcecaoPersistencia, ExcecaoNegocio {
        if ((tipoAtracao.getDescTipoAtracao() == null) || (tipoAtracao.getDescTipoAtracao().isEmpty())) {
            throw new ExcecaoNegocio("Obrigatório informar a descricao do tipo Atracao.");
        }
        Long result = tipoAtracaoDAO.inserir(tipoAtracao);
        return result;
    }

    @Override
    public boolean alterar(TipoAtracao tipoAtracao) throws ExcecaoPersistencia, ExcecaoNegocio {
       if ((tipoAtracao.getDescTipoAtracao() == null) || (tipoAtracao.getDescTipoAtracao().isEmpty())) {
            throw new ExcecaoNegocio("Obrigatório informar a descricao do tipo Atracao.");
        }
        boolean result = tipoAtracaoDAO.atualizar(tipoAtracao);
        return result;
    }

    @Override
    public boolean excluir(TipoAtracao tipoAtracao) throws ExcecaoPersistencia, ExcecaoNegocio {
        boolean result = tipoAtracaoDAO.deletar(tipoAtracao);
        return result;
    }

    @Override
    public TipoAtracao pesquisarPorId(Long codTipoAtracao) throws ExcecaoPersistencia {
        TipoAtracao result = tipoAtracaoDAO.consultarPorId(codTipoAtracao);
        return result;
    }

    @Override
    public TipoAtracao pesquisarPorNome(String descTipoAtracao) throws ExcecaoPersistencia {
        TipoAtracao result = tipoAtracaoDAO.consultarPorNome(descTipoAtracao);
        return result;
    }

    @Override
    public List<TipoAtracao> pesquisarTodos() throws ExcecaoPersistencia {
       List<TipoAtracao> result = tipoAtracaoDAO.listarTudo();
        return result;
    }

}
