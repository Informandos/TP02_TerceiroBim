package model.service.implementacao;

import java.util.List;
import model.dao.implementacao.CidadeDAO;
import model.dao.interfaces.InterfaceCidadeDAO;
import model.domain.Cidade;
import model.service.interfaces.InterfaceManterCidade;
import util.db.exception.ExcecaoNegocio;
import util.db.exception.ExcecaoPersistencia;

public class ManterCidade implements InterfaceManterCidade {
    
    private final InterfaceCidadeDAO cidadeDAO;

    public ManterCidade() {
        cidadeDAO = new CidadeDAO();
    }
    
    @Override
    public Long cadastrar(Cidade cidade) throws ExcecaoPersistencia, ExcecaoNegocio {

        if(cidade.getEstado().getCodEstado() == null){
            throw new ExcecaoNegocio("Obrigatório informar o código do estado");
        }
        if(cidade.getNomCidade() == null){
            throw new ExcecaoNegocio("Obrigatório informar o nome da cidade");
        }
        
        Long result = cidadeDAO.inserir(cidade);
        return result;
    }

    @Override
    public boolean alterar(Cidade cidade) throws ExcecaoPersistencia, ExcecaoNegocio {
        if(cidade.getCodCidade() == null){
            throw new ExcecaoNegocio("Obrigatório informar o código da cidade");
        }
        if(cidade.getEstado().getCodEstado() == null){
            throw new ExcecaoNegocio("Obrigatório informar o código do estado");
        }
        if(cidade.getNomCidade() == null){
            throw new ExcecaoNegocio("Obrigatório informar o nome da cidade");
        }
        
        boolean result = cidadeDAO.atualizar(cidade);
        return result;
    }

    @Override
    public boolean excluir(Cidade cidade) throws ExcecaoPersistencia, ExcecaoNegocio {
        boolean result = cidadeDAO.deletar(cidade);
        return result;
    }

    @Override
    public Cidade pesquisarPorId(Long codCidade) throws ExcecaoPersistencia {
        Cidade result = cidadeDAO.consultarPorId(codCidade);
        return result;
    }

    @Override
    public List<Cidade> pesquisarPorCodEstado(Long codEstado) throws ExcecaoPersistencia {
        List<Cidade> result = cidadeDAO.listarPorCodEstado(codEstado);
        return result;
    }

    @Override
    public List<Cidade> pesquisarTodos() throws ExcecaoPersistencia {
        List<Cidade> result = cidadeDAO.listarTudo();
        return result;
    }

    
}
