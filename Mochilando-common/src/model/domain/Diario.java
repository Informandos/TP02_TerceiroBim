/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.domain;

import java.util.Date;

/**
 *
 * @author Juliana
 */
public class Diario {
    private Long codDiario;
    private Usuario usuario;
    private String nomDiario;
    private Date datPublicacao;
    private Date datInicioViagem;
    private Date datFimViagem;
    private String txtDiario;
    private String tipoDiario;

    public Diario() {
    }

    public Diario(Long codDiario, Usuario usuario, String nomDiario, Date datPublicacao, Date datInicioViagem, Date datFimViagem, String txtDiario, String tipoDiario) {
        this.codDiario = codDiario;
        this.usuario = usuario;
        this.nomDiario = nomDiario;
        this.datPublicacao = datPublicacao;
        this.datInicioViagem = datInicioViagem;
        this.datFimViagem = datFimViagem;
        this.txtDiario = txtDiario;
        this.tipoDiario = tipoDiario;
    }

    /**
     * @return the codDiario
     */
    public Long getCodDiario() {
        return codDiario;
    }

    /**
     * @param codDiario the codDiario to set
     */
    public void setCodDiario(Long codDiario) {
        this.codDiario = codDiario;
    }

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the nomDiario
     */
    public String getNomDiario() {
        return nomDiario;
    }

    /**
     * @param nomDiario the nomDiario to set
     */
    public void setNomDiario(String nomDiario) {
        this.nomDiario = nomDiario;
    }

    /**
     * @return the datPublicacao
     */
    public Date getDatPublicacao() {
        return datPublicacao;
    }

    /**
     * @param datPublicacao the datPublicacao to set
     */
    public void setDatPublicacao(Date datPublicacao) {
        this.datPublicacao = datPublicacao;
    }

    /**
     * @return the datInicioViagem
     */
    public Date getDatInicioViagem() {
        return datInicioViagem;
    }

    /**
     * @param datInicioViagem the datInicioViagem to set
     */
    public void setDatInicioViagem(Date datInicioViagem) {
        this.datInicioViagem = datInicioViagem;
    }

    /**
     * @return the datFimViagem
     */
    public Date getDatFimViagem() {
        return datFimViagem;
    }

    /**
     * @param datFimViagem the datFimViagem to set
     */
    public void setDatFimViagem(Date datFimViagem) {
        this.datFimViagem = datFimViagem;
    }

    /**
     * @return the txtDiario
     */
    public String getTxtDiario() {
        return txtDiario;
    }

    /**
     * @param txtDiario the txtDiario to set
     */
    public void setTxtDiario(String txtDiario) {
        this.txtDiario = txtDiario;
    }

    public String getTipoDiario() {
        return tipoDiario;
    }

    public void setTipoDiario(String tipoDiario) {
        this.tipoDiario = tipoDiario;
    }

    
    
}
