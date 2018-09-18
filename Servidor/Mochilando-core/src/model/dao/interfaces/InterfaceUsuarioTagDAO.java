/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.interfaces;

import java.util.List;
import model.domain.UsuarioTag;
import util.db.exception.ExcecaoPersistencia;

/**
 *
 * @author Juliana
 */
public interface InterfaceUsuarioTagDAO {
    public Long inserir(UsuarioTag usuarioTag) throws ExcecaoPersistencia;
    public boolean atualizar(UsuarioTag usuarioTag) throws ExcecaoPersistencia;
    public boolean deletar(UsuarioTag usuarioTag) throws ExcecaoPersistencia;
    public UsuarioTag consultarPorId(Long seqUsuarioTag) throws ExcecaoPersistencia;
    public List<UsuarioTag> listarTudo() throws ExcecaoPersistencia;
    public List<UsuarioTag> listarPorCodUsuario(Long codUsuario) throws ExcecaoPersistencia;
    public List<UsuarioTag> listarPorCodTag(Long codTag) throws ExcecaoPersistencia;
}
