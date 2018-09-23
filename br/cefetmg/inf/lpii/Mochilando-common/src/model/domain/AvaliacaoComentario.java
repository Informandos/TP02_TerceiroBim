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
public class AvaliacaoComentario {
    
    private Long seqAvaliacao;
    private Comentario comentario;
    private Usuario usuario;
    private String avaliacao; //limitar em dois valores

    public AvaliacaoComentario() {
    }

    public AvaliacaoComentario(Long seqAvaliacao, Comentario comentario, Usuario usuario, String avaliacao) {
        this.seqAvaliacao = seqAvaliacao;
        this.comentario = comentario;
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
     * @return the comentario
     */
    public Comentario getComentario() {
        return comentario;
    }

    /**
     * @param comentario the comentario to set
     */
    public void setComentario(Comentario comentario) {
        this.comentario = comentario;
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
