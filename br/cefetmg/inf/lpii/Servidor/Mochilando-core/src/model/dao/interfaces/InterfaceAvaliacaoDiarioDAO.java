/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.interfaces;

import java.util.List;
import model.domain.AvaliacaoDiario;
import util.db.exception.ExcecaoPersistencia;

/**
 *
 * @author Juliana
 */
public interface InterfaceAvaliacaoDiarioDAO {
    public Long inserir(AvaliacaoDiario avaliacaoDiario) throws ExcecaoPersistencia;
    public boolean atualizar(AvaliacaoDiario avaliacaoDiario) throws ExcecaoPersistencia;
    public boolean deletar(AvaliacaoDiario avaliacaoDiario) throws ExcecaoPersistencia;
    public AvaliacaoDiario consultarPorId(Long seqAvaliacao) throws ExcecaoPersistencia;
    public int consultarNumAvPositivas(Long codDiario) throws ExcecaoPersistencia;
    public int consultarNumAvNegativas(Long codDiario) throws ExcecaoPersistencia;
    public List<AvaliacaoDiario> listarPorDiario(Long codDiario) throws ExcecaoPersistencia;
    public List<AvaliacaoDiario> listarTudo()throws ExcecaoPersistencia;
}
