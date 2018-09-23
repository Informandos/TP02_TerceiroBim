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
public class Tag {
    private Long codTag;
    private String descTag;

    public Tag() {
    }

    public Tag(Long codTag, String descTag) {
        this.codTag = codTag;
        this.descTag = descTag;
    }

    /**
     * @return the codTag
     */
    public Long getCodTag() {
        return codTag;
    }

    /**
     * @param codTag the codTag to set
     */
    public void setCodTag(Long codTag) {
        this.codTag = codTag;
    }

    /**
     * @return the descTag
     */
    public String getDescTag() {
        return descTag;
    }

    /**
     * @param descTag the descTag to set
     */
    public void setDescTag(String descTag) {
        this.descTag = descTag;
    }
    
}
