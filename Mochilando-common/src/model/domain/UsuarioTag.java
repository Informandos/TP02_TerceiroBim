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
public class UsuarioTag {
    private Long seqUsuarioTag;
    private Usuario usuario;
    private Tag tag;

    public UsuarioTag() {
    }

    public UsuarioTag(Long seqUsuarioTag, Usuario usuario, Tag preferencia) {
        this.seqUsuarioTag = seqUsuarioTag;
        this.usuario = usuario;
        this.tag = tag;
    }

    public Long getSeqUsuarioTag() {
        return seqUsuarioTag;
    }

    public void setSeqUsuarioTag(Long seqUsuarioTag) {
        this.seqUsuarioTag = seqUsuarioTag;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    
}
