/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service.interfaces;

import java.util.List;
import model.domain.UsuarioTag;
import util.db.exception.ExcecaoConexaoCliente;
import util.db.exception.ExcecaoNegocio;
import util.db.exception.ExcecaoPersistencia;

/**
 *
 * @author Juliana
 */
public interface InterfaceManterUsuarioTag {
    public Long cadastrar(UsuarioTag usuarioTag) throws ExcecaoPersistencia, ExcecaoNegocio,ExcecaoConexaoCliente;
    public boolean alterar(UsuarioTag usuarioTag) throws ExcecaoPersistencia, ExcecaoNegocio,ExcecaoConexaoCliente;
    public boolean excluir(UsuarioTag usuarioTag) throws ExcecaoPersistencia, ExcecaoNegocio,ExcecaoConexaoCliente;
    public UsuarioTag pesquisarPorId(Long seqUsuarioTag) throws ExcecaoPersistencia,ExcecaoConexaoCliente;
    public List<UsuarioTag> pesquisarTodos() throws ExcecaoPersistencia,ExcecaoConexaoCliente;
    public List<UsuarioTag> pesquisarPorCodUsuario(Long codUsuario) throws ExcecaoPersistencia,ExcecaoConexaoCliente;
    public List<UsuarioTag> pesquisarPorCodTag(Long codTag) throws ExcecaoPersistencia,ExcecaoConexaoCliente;
}
