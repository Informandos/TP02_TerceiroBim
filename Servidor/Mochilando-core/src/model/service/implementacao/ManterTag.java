/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service.implementacao;

import java.util.List;
import model.dao.implementacao.TagDAO;
import model.dao.interfaces.InterfaceTagDAO;
import model.domain.Tag;
import model.service.interfaces.InterfaceManterTag;
import util.db.exception.ExcecaoNegocio;
import util.db.exception.ExcecaoPersistencia;

/**
 *
 * @author User
 */
public class ManterTag implements InterfaceManterTag{
    private InterfaceTagDAO tagDAO;
    public ManterTag() {
        tagDAO = new TagDAO();
    }
    
    @Override
    public Long cadastrar(Tag tag) throws ExcecaoPersistencia, ExcecaoNegocio {
          
        if((tag.getCodTag() == null))
            throw new ExcecaoNegocio("Obrigatório informar o cod teg.");
         if((tag.getDescTag()== null) || (tag.getDescTag().isEmpty()))
            throw new ExcecaoNegocio("Obrigatório informar o desc tag.");
        Long result = tagDAO.inserir(tag);
        return result;
    }

    @Override
    public boolean alterar(Tag tag) throws ExcecaoPersistencia, ExcecaoNegocio {
        if((tag.getCodTag() == null))
            throw new ExcecaoNegocio("Obrigatório informar o cod teg.");
         if((tag.getDescTag()== null) || (tag.getDescTag().isEmpty()))
            throw new ExcecaoNegocio("Obrigatório informar o desc tag.");
       
        boolean result = tagDAO.atualizar(tag);
        return result;
    }

    @Override
    public boolean excluir(Tag tag) throws ExcecaoPersistencia, ExcecaoNegocio {
         
        boolean result = tagDAO.deletar(tag);
        return result;     
    }

    @Override
    public Tag pesquisarPorId(Long codTag) throws ExcecaoPersistencia {
         Tag result = tagDAO.consultarPorId(codTag);
        return result;  
    }

    @Override
    public Tag pesquisarPorNome(String descTag) throws ExcecaoPersistencia {
        Tag result = tagDAO.consultarPorNome(descTag);
        return result;  
    }

    @Override
    public List<Tag> pesquisarTodos() throws ExcecaoPersistencia {
        List<Tag> result = tagDAO.listarTudo();
        return result;
    }
    
}
