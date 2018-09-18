package model.service.implementacao;

import util.db.exception.ExcecaoNegocio;
import util.db.exception.ExcecaoPersistencia;
import java.util.List;
import model.dao.implementacao.TagDiarioDAO;
import model.dao.interfaces.InterfaceTagDiarioDAO;
import model.domain.TagDiario;
import model.service.interfaces.InterfaceManterTagDiario;

public class ManterTagDiario implements InterfaceManterTagDiario{
    
    private final InterfaceTagDiarioDAO tagdDAO;

    public ManterTagDiario() {
        tagdDAO = new TagDiarioDAO();
    }
    
    @Override
    public Long cadastrar(TagDiario tagDiario) throws ExcecaoPersistencia, ExcecaoNegocio {
        if(tagDiario.getSeqTagDiario() == null){
            throw new ExcecaoNegocio("Obrigatório informar o código da Tag do diário");
        }
        if(tagDiario.getDiario().getCodDiario() == null){
            throw new ExcecaoNegocio("Obrigatório informar o código do diário");
        }
        if(tagDiario.getTag().getCodTag() == null){
            throw new ExcecaoNegocio("Obrigatório informar o código da tag");
        }
        
        Long result = tagdDAO.inserir(tagDiario);
        return result;
    }

    @Override
    public boolean alterar(TagDiario tagDiario) throws ExcecaoPersistencia, ExcecaoNegocio {
        if(tagDiario.getSeqTagDiario() == null){
            throw new ExcecaoNegocio("Obrigatório informar o código da Tag do diário");
        }
        if(tagDiario.getDiario().getCodDiario() == null){
            throw new ExcecaoNegocio("Obrigatório informar o código do diário");
        }
        if(tagDiario.getTag().getCodTag() == null){
            throw new ExcecaoNegocio("Obrigatório informar o código da tag");
        }
        
        boolean result = tagdDAO.atualizar(tagDiario);
        return result;
    }

    @Override
    public boolean excluir(TagDiario tagDiario) throws ExcecaoPersistencia, ExcecaoNegocio {
        boolean result = tagdDAO.deletar(tagDiario);
        return result;
    }

    @Override
    public TagDiario pesquisarPorId(Long seqTagDiario) throws ExcecaoPersistencia {
        TagDiario result = tagdDAO.consultarPorId(seqTagDiario);
        return result;
    }

    @Override
    public List<TagDiario> pesquisarTodos() throws ExcecaoPersistencia {
        List<TagDiario> result = tagdDAO.listarTudo();
        return result;
    }

    @Override
    public List<TagDiario> pesquisarPorCodDiario(Long codDiario) throws ExcecaoPersistencia {
        List<TagDiario> result = tagdDAO.listarPorCodDiario(codDiario);
        return result;
    }

    @Override
    public List<TagDiario> pesquisarPorCodTag(Long codTag) throws ExcecaoPersistencia {
        List<TagDiario> result = tagdDAO.listarPorCodTag(codTag);
        return result;
    }
    
}
