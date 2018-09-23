/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.interfaces;

import java.util.List;
import model.domain.TipoAtracao;
import util.db.exception.ExcecaoPersistencia;

/**
 *
 * @author Juliana
 */
public interface InterfaceTipoAtracaoDAO {
    public Long inserir(TipoAtracao tipoAtracao) throws ExcecaoPersistencia;
    public boolean atualizar(TipoAtracao tipoAtracao) throws ExcecaoPersistencia;
    public boolean deletar(TipoAtracao tipoAtracao) throws ExcecaoPersistencia;
    public TipoAtracao consultarPorId(Long codTipoAtracao) throws ExcecaoPersistencia;
    public TipoAtracao consultarPorNome(String descTipoAtracao) throws ExcecaoPersistencia;
    public List<TipoAtracao> listarTudo() throws ExcecaoPersistencia;
}
