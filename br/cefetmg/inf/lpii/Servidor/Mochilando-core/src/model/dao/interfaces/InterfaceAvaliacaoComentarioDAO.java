/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.interfaces;

import java.util.List;
import model.domain.AvaliacaoComentario;
import model.domain.Comentario;
import util.db.exception.ExcecaoPersistencia;


/**
 *
 * @author Juliana
 */
public interface InterfaceAvaliacaoComentarioDAO {
    public Long inserir(AvaliacaoComentario avaliacaoComenario) throws ExcecaoPersistencia;
    public boolean atualizar(AvaliacaoComentario avaliacaoComenario) throws ExcecaoPersistencia;
    public boolean deletar(AvaliacaoComentario avaliacaoComenario) throws ExcecaoPersistencia;
    public AvaliacaoComentario consultarPorId(Long seqAvaliacao) throws ExcecaoPersistencia;
    public int consultarNumAvPositivas(Long seqComentario) throws ExcecaoPersistencia;
    public int consultarNumAvNegativas(Long seqComentario) throws ExcecaoPersistencia;
    public List <AvaliacaoComentario> listarPorDiario(Long codDiario) throws ExcecaoPersistencia;
    public List <AvaliacaoComentario> listarTudo(Long seqAvaliacao) throws ExcecaoPersistencia;
}
