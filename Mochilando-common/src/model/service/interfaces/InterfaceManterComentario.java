/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service.interfaces;

import java.util.List;
import model.domain.Comentario;
import util.db.exception.ExcecaoConexaoCliente;
import util.db.exception.ExcecaoNegocio;
import util.db.exception.ExcecaoPersistencia;

/**
 *
 * @author Juliana
 */
public interface InterfaceManterComentario {
    public Long cadastrar(Comentario comentario) throws ExcecaoPersistencia, ExcecaoNegocio,ExcecaoConexaoCliente;
    public boolean alterar(Comentario comentario) throws ExcecaoPersistencia, ExcecaoNegocio,ExcecaoConexaoCliente;
    public boolean excluir(Comentario comentario) throws ExcecaoPersistencia, ExcecaoNegocio,ExcecaoConexaoCliente;
    public Comentario pesquisarPorId(Long seqComentario) throws ExcecaoPersistencia,ExcecaoConexaoCliente;
    public List<Comentario> pesquisarPorCodDiario(Long codDiario) throws ExcecaoPersistencia,ExcecaoConexaoCliente;
    public List<Comentario> pesquisarTodos() throws ExcecaoPersistencia,ExcecaoConexaoCliente;
}
