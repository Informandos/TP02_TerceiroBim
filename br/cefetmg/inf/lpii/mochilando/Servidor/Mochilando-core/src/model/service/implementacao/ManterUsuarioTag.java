package model.service.implementacao;

import util.db.exception.ExcecaoNegocio;
import util.db.exception.ExcecaoPersistencia;
import java.util.List;
import model.dao.implementacao.UsuarioTagDAO;
import model.dao.interfaces.InterfaceUsuarioTagDAO;
import model.domain.UsuarioTag;
import model.service.interfaces.InterfaceManterUsuarioTag;

public class ManterUsuarioTag implements InterfaceManterUsuarioTag {

    private final InterfaceUsuarioTagDAO taguDAO;
    
    public ManterUsuarioTag() {
        taguDAO = new UsuarioTagDAO();
    }
    
    @Override
    public Long cadastrar(UsuarioTag usuarioTag) throws ExcecaoPersistencia, ExcecaoNegocio {
        if(usuarioTag.getSeqUsuarioTag() == null){
            throw new ExcecaoNegocio("Obrigatório informar o código da tag do usuario");
        }
        if(usuarioTag.getTag().getCodTag() == null){
            throw new ExcecaoNegocio("Obrigatório informar o código da tag");
        }
        if(usuarioTag.getUsuario().getCodUsuario() == null){
            throw new ExcecaoNegocio("Obrigatório informar o código do usuário");
        }
        
        Long result = taguDAO.inserir(usuarioTag);
        return result;
    }

    @Override
    public boolean alterar(UsuarioTag usuarioTag) throws ExcecaoPersistencia, ExcecaoNegocio {
        if(usuarioTag.getSeqUsuarioTag() == null){
            throw new ExcecaoNegocio("Obrigatório informar o código da tag do usuario");
        }
        if(usuarioTag.getTag().getCodTag() == null){
            throw new ExcecaoNegocio("Obrigatório informar o código da tag");
        }
        if(usuarioTag.getUsuario().getCodUsuario() == null){
            throw new ExcecaoNegocio("Obrigatório informar o código do usuário");
        }
        
        boolean result = taguDAO.atualizar(usuarioTag);
        return result;
    }

    @Override
    public boolean excluir(UsuarioTag usuarioTag) throws ExcecaoPersistencia, ExcecaoNegocio {
        boolean result = taguDAO.deletar(usuarioTag);
        return result;
    }

    @Override
    public UsuarioTag pesquisarPorId(Long seqUsuarioTag) throws ExcecaoPersistencia {
        UsuarioTag result = taguDAO.consultarPorId(seqUsuarioTag);
        return result;
    }

    @Override
    public List<UsuarioTag> pesquisarTodos() throws ExcecaoPersistencia {
        List<UsuarioTag> result = taguDAO.listarTudo();
        return result;
    }

    @Override
    public List<UsuarioTag> pesquisarPorCodUsuario(Long codUsuario) throws ExcecaoPersistencia {
        List<UsuarioTag> result = taguDAO.listarPorCodUsuario(codUsuario);
        return result;
    }

    @Override
    public List<UsuarioTag> pesquisarPorCodTag(Long codTag) throws ExcecaoPersistencia {
        List<UsuarioTag> result = taguDAO.listarPorCodUsuario(codTag);
        return result;
    }
    
}
