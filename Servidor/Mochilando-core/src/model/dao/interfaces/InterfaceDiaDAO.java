/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.interfaces;

import java.util.List;
import model.domain.Dia;
import util.db.exception.ExcecaoPersistencia;

/**
 *
 * @author Juliana
 */
public interface InterfaceDiaDAO {
    public Long inserir(Dia dia) throws ExcecaoPersistencia;
    public boolean atualizar(Dia dia) throws ExcecaoPersistencia;
    public boolean deletar(Dia dia) throws ExcecaoPersistencia;
    public Dia consultarPorId(Long seqDia) throws ExcecaoPersistencia;
    public List<Dia> listarPorCodDiario(Long codDiario) throws ExcecaoPersistencia; 
    public List<Dia> listarTudo() throws ExcecaoPersistencia;
}
