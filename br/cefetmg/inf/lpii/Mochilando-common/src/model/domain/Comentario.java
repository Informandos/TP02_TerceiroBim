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
public class Comentario {
    
    private Long seqComentario;
    private Diario diario;
    private Usuario usuario;
    private Date datPublicacao;
    private String txtComentario;

    public Comentario() {
    }

    public Comentario(Long seqComentario, Diario diario, Usuario usuario, Date datPublicacao, String txtComentario) {
        this.seqComentario = seqComentario;
        this.diario = diario;
        this.usuario = usuario;
        this.datPublicacao = datPublicacao;
        this.txtComentario = txtComentario;
    }

    public Long getSeqComentario() {
        return seqComentario;
    }

    public void setSeqComentario(Long seqComentario) {
        this.seqComentario = seqComentario;
    }

    public Diario getDiario() {
        return diario;
    }

    public void setDiario(Diario diario) {
        this.diario = diario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getDatPublicacao() {
        return datPublicacao;
    }

    public void setDatPublicacao(Date datPublicacao) {
        this.datPublicacao = datPublicacao;
    }

    public String getTxtComentario() {
        return txtComentario;
    }

    public void setTxtComentario(String txtComentario) {
        this.txtComentario = txtComentario;
    }


}
