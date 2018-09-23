/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service.interfaces;

import java.util.List;
import model.domain.Tag;
import util.db.exception.ExcecaoConexaoCliente;
import util.db.exception.ExcecaoNegocio;
import util.db.exception.ExcecaoPersistencia;

/**
 *
 * @author Juliana
 */
public interface InterfaceManterTag {
    public Long cadastrar(Tag tag) throws ExcecaoPersistencia, ExcecaoNegocio,ExcecaoConexaoCliente;
    public boolean alterar(Tag tag) throws ExcecaoPersistencia, ExcecaoNegocio,ExcecaoConexaoCliente;
    public boolean excluir(Tag tag) throws ExcecaoPersistencia, ExcecaoNegocio,ExcecaoConexaoCliente;
    public Tag pesquisarPorId(Long codTag) throws ExcecaoPersistencia,ExcecaoConexaoCliente;
    public Tag pesquisarPorNome(String descTag) throws ExcecaoPersistencia,ExcecaoConexaoCliente;
    public List<Tag> pesquisarTodos() throws ExcecaoPersistencia,ExcecaoConexaoCliente;
}
