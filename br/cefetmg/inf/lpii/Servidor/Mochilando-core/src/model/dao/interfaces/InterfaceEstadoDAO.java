/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.interfaces;

import java.util.List;
import model.domain.Estado;
import util.db.exception.ExcecaoPersistencia;

/**
 *
 * @author Juliana
 */
public interface InterfaceEstadoDAO {
    public Long inserir(Estado estado) throws ExcecaoPersistencia;
    public boolean atualizar(Estado estado) throws ExcecaoPersistencia;
    public boolean deletar(Estado estado) throws ExcecaoPersistencia;
    public Estado consultarPorId(Long codEstado) throws ExcecaoPersistencia;
    public Estado consultarPorSigla(String sigla) throws ExcecaoPersistencia;
    public List<Estado> listarTudo() throws ExcecaoPersistencia;
}
