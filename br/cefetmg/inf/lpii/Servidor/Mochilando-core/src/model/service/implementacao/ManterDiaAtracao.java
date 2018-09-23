package model.service.implementacao;

import java.util.List;
import model.dao.implementacao.DiaAtracaoDAO;
import model.dao.interfaces.InterfaceDiaAtracaoDAO;
import model.domain.DiaAtracao;
import model.service.interfaces.InterfaceManterDiaAtracao;
import util.db.exception.ExcecaoNegocio;
import util.db.exception.ExcecaoPersistencia;

public class ManterDiaAtracao implements InterfaceManterDiaAtracao{
    
    private final InterfaceDiaAtracaoDAO diaADAO;
    
    public ManterDiaAtracao() {
        diaADAO = new DiaAtracaoDAO();
    }
    
    @Override
    public Long cadastrar(DiaAtracao diaAtracao) throws ExcecaoPersistencia, ExcecaoNegocio {
        if(diaAtracao.getSeqDiaAtracao() == null){
            throw new ExcecaoNegocio("Obrigatório informar o código do diaAtracao");
        }
        if(diaAtracao.getAtracao().getSeqAtracao() == null){
            throw new ExcecaoNegocio("Obrigatório informar o código da atração");
        }
        if(diaAtracao.getDia().getDataDia() == null){
            throw new ExcecaoNegocio("Obrigatório informar a data da atração");
        }
        
        Long result = diaADAO.inserir(diaAtracao);
        return result;
    }

    @Override
    public boolean alterar(DiaAtracao diaAtracao) throws ExcecaoPersistencia, ExcecaoNegocio {
        if(diaAtracao.getSeqDiaAtracao() == null){
            throw new ExcecaoNegocio("Obrigatório informar o código do diaAtracao");
        }
        if(diaAtracao.getAtracao().getSeqAtracao() == null){
            throw new ExcecaoNegocio("Obrigatório informar o código da atração");
        }
        if(diaAtracao.getDia().getDataDia() == null){
            throw new ExcecaoNegocio("Obrigatório informar a data da atração");
        }
        
        boolean result = diaADAO.atualizar(diaAtracao);
        return result;
    }

    @Override
    public boolean excluir(DiaAtracao diaAtracao) throws ExcecaoPersistencia, ExcecaoNegocio {
        boolean result = diaADAO.deletar(diaAtracao);
        return result;
    }

    @Override
    public DiaAtracao pesquisarPorId(Long seqDiaAtracao) throws ExcecaoPersistencia {
        DiaAtracao result = diaADAO.consultarPorId(seqDiaAtracao);
        return result;
    }

    @Override
    public List<DiaAtracao> pesquisarPorSeqDia(Long seqDia) throws ExcecaoPersistencia {
        List<DiaAtracao> result = diaADAO.listarPorSeqDia(seqDia);
        return result;
    }

    @Override
    public List<DiaAtracao> pesquisarTodos() throws ExcecaoPersistencia {
        List<DiaAtracao> result = diaADAO.listarTudo();
        return result;
    }
    
}
