package model.service.implementacao;

import util.db.exception.ExcecaoNegocio;
import util.db.exception.ExcecaoPersistencia;
import java.util.List;
import model.dao.implementacao.ComentarioDAO;
import model.dao.interfaces.InterfaceComentarioDAO;
import model.domain.Comentario;
import model.service.interfaces.InterfaceManterComentario;

public class ManterComentario implements InterfaceManterComentario{
    
    private final InterfaceComentarioDAO comentarioDAO;

    public ManterComentario() {
        comentarioDAO = new ComentarioDAO();
    }
    
    @Override
    public Long cadastrar(Comentario comentario) throws ExcecaoPersistencia, ExcecaoNegocio {
        if(comentario.getSeqComentario() == null){
            throw new ExcecaoNegocio("Obrigatório informar o código do comentário");
        }
        if(comentario.getUsuario().getCodUsuario() == null){
            throw new ExcecaoNegocio("Obrigatório informar o código do usuário");
        }
        if(comentario.getDiario().getCodDiario() == null){
            throw new ExcecaoNegocio("Obrigatório informar o código do diário");
        }
        if(comentario.getDatPublicacao() == null){
            throw new ExcecaoNegocio("Obrigatório informar a data do comentario");
        }
        if(comentario.getTxtComentario() == null){
            throw new ExcecaoNegocio("Obrigatório informar o comentário");
        }
        
        Long result = comentarioDAO.inserir(comentario);
        return result;
    }

    @Override
    public boolean alterar(Comentario comentario) throws ExcecaoPersistencia, ExcecaoNegocio {
        if(comentario.getSeqComentario() == null){
            throw new ExcecaoNegocio("Obrigatório informar o código do comentário");
        }
        if(comentario.getUsuario().getCodUsuario() == null){
            throw new ExcecaoNegocio("Obrigatório informar o código do usuário");
        }
        if(comentario.getDiario().getCodDiario() == null){
            throw new ExcecaoNegocio("Obrigatório informar o código do diário");
        }
        if(comentario.getDatPublicacao() == null){
            throw new ExcecaoNegocio("Obrigatório informar a data do comentario");
        }
        if(comentario.getTxtComentario() == null){
            throw new ExcecaoNegocio("Obrigatório informar o comentário");
        }
        
        boolean result = comentarioDAO.atualizar(comentario);
        return result;
    }

    @Override
    public boolean excluir(Comentario comentario) throws ExcecaoPersistencia, ExcecaoNegocio {
        boolean result = comentarioDAO.deletar(comentario);
        return result;
    }

    @Override
    public Comentario pesquisarPorId(Long seqComentario) throws ExcecaoPersistencia {
        Comentario result = comentarioDAO.consultarPorId(seqComentario);
        return result;
    }

    @Override
    public List<Comentario> pesquisarPorCodDiario(Long codDiario) throws ExcecaoPersistencia {
        List<Comentario> result = comentarioDAO.listarPorCodDiario(codDiario);
        return result;
    }

    @Override
    public List<Comentario> pesquisarTodos() throws ExcecaoPersistencia {
        List<Comentario> result = comentarioDAO.listarTudo();
        return result;
    }
    
}
