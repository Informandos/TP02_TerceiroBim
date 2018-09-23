/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.interfaces;

import java.util.List;
import model.domain.Cidade;
import util.db.exception.ExcecaoPersistencia;

/**
 *
 * @author Juliana
 */
public interface InterfaceCidadeDAO {
    public Long inserir(Cidade cidade) throws ExcecaoPersistencia;
    public boolean atualizar(Cidade cidade) throws ExcecaoPersistencia;
    public boolean deletar(Cidade cidade) throws ExcecaoPersistencia;
    public Cidade consultarPorId(Long codCidade) throws ExcecaoPersistencia;
    public List<Cidade> listarPorCodEstado(Long codEstado) throws ExcecaoPersistencia;
    public List<Cidade> listarTudo() throws ExcecaoPersistencia;
}
