/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service.interfaces;

import java.util.List;
import model.domain.Dia;
import util.db.exception.ExcecaoConexaoCliente;
import util.db.exception.ExcecaoNegocio;
import util.db.exception.ExcecaoPersistencia;

/**
 *
 * @author Juliana
 */
public interface InterfaceManterDia {
    public Long cadastrar(Dia dia) throws ExcecaoPersistencia, ExcecaoNegocio,ExcecaoConexaoCliente;
    public boolean alterar(Dia dia) throws ExcecaoPersistencia, ExcecaoNegocio,ExcecaoConexaoCliente;
    public boolean excluir(Dia dia) throws ExcecaoPersistencia, ExcecaoNegocio,ExcecaoConexaoCliente;
    public Dia pesquisarPorId(Long seqDia) throws ExcecaoPersistencia,ExcecaoConexaoCliente;
    public List<Dia> pesquisarPorCodDiario(Long codDiario) throws ExcecaoPersistencia,ExcecaoConexaoCliente; 
    public List<Dia> pesquisarTodos() throws ExcecaoPersistencia,ExcecaoConexaoCliente;
}
