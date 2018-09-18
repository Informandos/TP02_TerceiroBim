/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service.interfaces;

import java.util.List;
import model.domain.Diario;
import util.db.exception.ExcecaoConexaoCliente;
import util.db.exception.ExcecaoNegocio;
import util.db.exception.ExcecaoPersistencia;

/**
 *
 * @author Juliana
 */
public interface InterfaceManterDiario {
    public Long cadastrar(Diario diario) throws ExcecaoPersistencia, ExcecaoNegocio,ExcecaoConexaoCliente;
    public boolean alterar(Diario diario) throws ExcecaoPersistencia, ExcecaoNegocio,ExcecaoConexaoCliente;
    public boolean excluir(Diario diario) throws ExcecaoPersistencia, ExcecaoNegocio,ExcecaoConexaoCliente;
    public Diario pesquisarPorId(Long codDiario) throws ExcecaoPersistencia,ExcecaoConexaoCliente;
    public List<Diario> pesquisarTodos() throws ExcecaoPersistencia,ExcecaoConexaoCliente;
    public List<Diario> pesquisarPorCodUsuario(Long codUsuario) throws ExcecaoPersistencia,ExcecaoConexaoCliente;
    public List<Diario> pesquisarPorCodCidade(Long codCidade) throws ExcecaoPersistencia,ExcecaoConexaoCliente;
    public List<Diario> pesquisarPorCodEstado(Long codEstado) throws ExcecaoPersistencia,ExcecaoConexaoCliente;
}
