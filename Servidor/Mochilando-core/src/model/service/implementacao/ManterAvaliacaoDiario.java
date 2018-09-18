package model.service.implementacao;

import util.db.exception.ExcecaoNegocio;
import util.db.exception.ExcecaoPersistencia;
import java.util.List;
import model.dao.implementacao.AvaliacaoDiarioDAO;
import model.dao.interfaces.InterfaceAvaliacaoDiarioDAO;
import model.domain.AvaliacaoDiario;
import model.service.interfaces.InterfaceManterAvaliacaoDiario;

public class ManterAvaliacaoDiario implements InterfaceManterAvaliacaoDiario{

    private final InterfaceAvaliacaoDiarioDAO aDiarioDAO;

    public ManterAvaliacaoDiario() {
        aDiarioDAO = new AvaliacaoDiarioDAO();
    }
    
    @Override
    public Long cadastrar(AvaliacaoDiario avaliacaoDiario) throws ExcecaoPersistencia, ExcecaoNegocio {
        if (avaliacaoDiario.getSeqAvaliacao() == null) {
            throw new ExcecaoNegocio("Obrigatório informar o código da avaliação");
        }
        if (avaliacaoDiario.getDiario().getCodDiario() == null) {
            throw new ExcecaoNegocio("Obrigatório informar o código do diário");
        }
        if (avaliacaoDiario.getUsuario().getCodUsuario() == null) {
            throw new ExcecaoNegocio("Obrigatório informar o código do usuário");
        }
        if (avaliacaoDiario.getAvaliacao() == null) {
            throw new ExcecaoNegocio("Obrigatório informar a avaliação");
        }
        
        Long result = aDiarioDAO.inserir(avaliacaoDiario);
        return result;
    }

    @Override
    public boolean alterar(AvaliacaoDiario avaliacaoDiario) throws ExcecaoPersistencia, ExcecaoNegocio {
        if (avaliacaoDiario.getSeqAvaliacao() == null) {
            throw new ExcecaoNegocio("Obrigatório informar o código da avaliação");
        }
        if (avaliacaoDiario.getDiario().getCodDiario() == null) {
            throw new ExcecaoNegocio("Obrigatório informar o código do diário");
        }
        if (avaliacaoDiario.getUsuario().getCodUsuario() == null) {
            throw new ExcecaoNegocio("Obrigatório informar o código do usuário");
        }
        if (avaliacaoDiario.getAvaliacao() == null) {
            throw new ExcecaoNegocio("Obrigatório informar a avaliação");
        }
        
        boolean result = aDiarioDAO.atualizar(avaliacaoDiario);
        return result;
    }

    @Override
    public boolean excluir(AvaliacaoDiario avaliacaoDiario) throws ExcecaoPersistencia, ExcecaoNegocio {
        boolean result = aDiarioDAO.deletar(avaliacaoDiario);
        return result;
    }

    @Override
    public AvaliacaoDiario pesquisarPorId(Long seqAvaliacao) throws ExcecaoPersistencia {
        AvaliacaoDiario result = aDiarioDAO.consultarPorId(seqAvaliacao);
        return result;
    }

    @Override
    public int pesquisarNumAvPositivas(Long codDiario) throws ExcecaoPersistencia {
        int result = aDiarioDAO.consultarNumAvPositivas(codDiario);
        return result;
    }

    @Override
    public int pesquisarNumAvNegativas(Long codDiario) throws ExcecaoPersistencia {
        int result = aDiarioDAO.consultarNumAvNegativas(codDiario);
        return result;
    }

    @Override
    public List<AvaliacaoDiario> pesquisarPorDiario(Long codDiario) throws ExcecaoPersistencia {
        List<AvaliacaoDiario> result = aDiarioDAO.listarPorDiario(codDiario);
        return result;
    }

    @Override
    public List<AvaliacaoDiario> pesquisarTodos() throws ExcecaoPersistencia {
        List<AvaliacaoDiario> result = aDiarioDAO.listarTudo();
        return result;
    }
    
}
