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
public class DiaAtracao {
    private Long seqDiaAtracao;
    private Atracao atracao;
    private Dia dia;

    public DiaAtracao() {
    }

    public DiaAtracao(Long seqDiaAtracao, Atracao atracao, Dia dia) {
        this.seqDiaAtracao = seqDiaAtracao;
        this.atracao = atracao;
        this.dia = dia;
    }

    /**
     * @return the seqDiaAtracao
     */
    public Long getSeqDiaAtracao() {
        return seqDiaAtracao;
    }

    /**
     * @param seqDiaAtracao the seqDiaAtracao to set
     */
    public void setSeqDiaAtracao(Long seqDiaAtracao) {
        this.seqDiaAtracao = seqDiaAtracao;
    }

    /**
     * @return the atracao
     */
    public Atracao getAtracao() {
        return atracao;
    }

    /**
     * @param atracao the atracao to set
     */
    public void setAtracao(Atracao atracao) {
        this.atracao = atracao;
    }

    /**
     * @return the dia
     */
    public Dia getDia() {
        return dia;
    }

    /**
     * @param dia the dia to set
     */
    public void setDia(Dia dia) {
        this.dia = dia;
    }

    
}
