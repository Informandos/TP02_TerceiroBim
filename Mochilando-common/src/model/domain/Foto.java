/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.domain;

import java.awt.image.BufferedImage;
import java.sql.Blob;
import org.ietf.jgss.Oid;

/**
 *
 * @author Juliana
 */
public class Foto {

    private Long seqFoto;
    private Dia dia;
    private Byte byteFoto;

    public Foto() {
    }

    public Foto(Long seqFoto, Dia dia, Byte byteFoto) {
        this.seqFoto = seqFoto;
        this.dia = dia;
        this.byteFoto = byteFoto;
    }

    public Long getSeqFoto() {
        return seqFoto;
    }

    public void setSeqFoto(Long seqFoto) {
        this.seqFoto = seqFoto;
    }

    public Dia getDia() {
        return dia;
    }

    public void setDia(Dia dia) {
        this.dia = dia;
    }

    public Byte getByteFoto() {
        return byteFoto;
    }

    public void setByteFoto( Byte byteFoto) {
        this.byteFoto = byteFoto;
    }

}
