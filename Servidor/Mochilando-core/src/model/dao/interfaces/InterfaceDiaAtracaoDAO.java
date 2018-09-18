/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.interfaces;

import java.util.List;
import model.domain.DiaAtracao;
import util.db.exception.ExcecaoPersistencia;

/**
 *
 * @author Juliana
 */

public interface InterfaceDiaAtracaoDAO {
    public Long inserir(DiaAtracao diaAtracao) throws ExcecaoPersistencia;
    public boolean atualizar(DiaAtracao diaAtracao) throws ExcecaoPersistencia;
    public boolean deletar(DiaAtracao diaAtracao) throws ExcecaoPersistencia;
    public DiaAtracao consultarPorId(Long seqDiaAtracao) throws ExcecaoPersistencia;
    public List<DiaAtracao> listarPorSeqDia(Long seqDia) throws ExcecaoPersistencia;
    public List<DiaAtracao> listarTudo() throws ExcecaoPersistencia;
}
