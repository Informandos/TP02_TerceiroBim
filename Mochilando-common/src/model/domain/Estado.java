/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.domain;

/**
 *
 * @author Juliana
 */
public class Estado {
    
    private Long codEstado;
    private String sigla;
    private String nomEstado;

    public Estado() {
    }

    public Estado(Long codEstado, String sigla, String nomEstado) {
        this.codEstado = codEstado;
        this.sigla = sigla;
        this.nomEstado = nomEstado;
    }

    /**
     * @return the codEstado
     */
    public Long getCodEstado() {
        return codEstado;
    }

    /**
     * @param codEstado the codEstado to set
     */
    public void setCodEstado(Long codEstado) {
        this.codEstado = codEstado;
    }

    /**
     * @return the sigla
     */
    public String getSigla() {
        return sigla;
    }

    /**
     * @param sigla the sigla to set
     */
    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    /**
     * @return the nomEstado
     */
    public String getNomEstado() {
        return nomEstado;
    }

    /**
     * @param nomEstado the nomEstado to set
     */
    public void setNomEstado(String nomEstado) {
        this.nomEstado = nomEstado;
    }

    
}
