/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.interfaces;

import java.util.List;
import model.domain.Tag;
import util.db.exception.ExcecaoPersistencia;

/**
 *
 * @author Juliana
 */
public interface InterfaceTagDAO {
    public Long inserir(Tag tag) throws ExcecaoPersistencia;
    public boolean atualizar(Tag tag) throws ExcecaoPersistencia;
    public boolean deletar(Tag tag) throws ExcecaoPersistencia;
    public Tag consultarPorId(Long codTag) throws ExcecaoPersistencia;
    public Tag consultarPorNome(String descTag) throws ExcecaoPersistencia;
    public List<Tag> listarTudo() throws ExcecaoPersistencia;
}
