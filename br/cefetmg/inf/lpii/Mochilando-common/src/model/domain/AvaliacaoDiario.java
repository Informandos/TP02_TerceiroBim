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
public class AvaliacaoDiario {

    private Long seqAvaliacao;
    private Diario diario;
    private Usuario usuario;
    private String avaliacao; //limitar em dois valores

    public AvaliacaoDiario() {
    }

    public AvaliacaoDiario(Long seqAvaliacao, Diario diario, Usuario usuario, String avaliacao) {
        this.seqAvaliacao = seqAvaliacao;
        this.diario = diario;
        this.usuario = usuario;
        this.avaliacao = avaliacao;
    }

    /**
     * @return the seqAvaliacao
     */
    public Long getSeqAvaliacao() {
        return seqAvaliacao;
    }

    /**
     * @param seqAvaliacao the seqAvaliacao to set
     */
    public void setSeqAvaliacao(Long seqAvaliacao) {
        this.seqAvaliacao = seqAvaliacao;
    }

    /**
     * @return the diario
     */
    public Diario getDiario() {
        return diario;
    }

    /**
     * @param diario the diario to set
     */
    public void setDiario(Diario diario) {
        this.diario = diario;
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
     * @return the avaliacao
     */
    public String getAvaliacao() {
        return avaliacao;
    }

    /**
     * @param avaliacao the avaliacao to set
     */
    public void setAvaliacao(String avaliacao) {
        this.avaliacao = avaliacao;
    }


}
