/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.interfaces;

import java.util.List;
import model.domain.Foto;
import util.db.exception.ExcecaoPersistencia;

/**
 *
 * @author Juliana
 */
public interface InterfaceFotoDAO {
    public Long inserir(Foto foto) throws ExcecaoPersistencia;
    public boolean atualizar(Foto foto) throws ExcecaoPersistencia;
    public boolean deletar(Foto foto) throws ExcecaoPersistencia;
    public Foto consultarPorId(Long seqFoto) throws ExcecaoPersistencia;
    public List<Foto> listarTudo() throws ExcecaoPersistencia;
    public List<Foto> listarPorDia(Long seqDia) throws ExcecaoPersistencia;
}
