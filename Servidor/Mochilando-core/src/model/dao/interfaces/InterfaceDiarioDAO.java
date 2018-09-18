/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.interfaces;

import java.util.List;
import model.domain.Cidade;
import model.domain.Diario;
import model.domain.Estado;
import model.domain.Usuario;
import util.db.exception.ExcecaoPersistencia;

/**
 *
 * @author Juliana
 */
public interface InterfaceDiarioDAO {
    public Long inserir(Diario diario) throws ExcecaoPersistencia;
    public boolean atualizar(Diario diario) throws ExcecaoPersistencia;
    public boolean deletar(Diario diario) throws ExcecaoPersistencia;
    public Diario consultarPorId(Long codDiario) throws ExcecaoPersistencia;
    public List<Diario> listarTudo() throws ExcecaoPersistencia;
    public List<Diario> listarPorCodUsuario(Long codUsuario) throws ExcecaoPersistencia;
    public List<Diario> listarPorCodCidade(Long codCidade) throws ExcecaoPersistencia;
    public List<Diario> listarPorCodEstado(Long codEstado) throws ExcecaoPersistencia;
}
