/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.domain;

import java.sql.Blob;
import java.util.Date;

/**
 *
 * @author Juliana
 */
public class Usuario {
    private Long codUsuario;
    private String nomUsuario;
    private String sobrenomeUsuario;
    private String txtEmail;
    private String txtSenha;
    private Byte imgPerfil;
    private String sexo;
    private Date datNascimento;
    private Cidade cidade;

    public Usuario() {
    }

    public Usuario(Long codUsuario, String nomUsuario, String sobrenomeUsuario, String txtEmail, String txtSenha, Byte imgPerfil, String sexo, Date datNascimento, Cidade cidade) {
        this.codUsuario = codUsuario;
        this.nomUsuario = nomUsuario;
        this.sobrenomeUsuario = sobrenomeUsuario;
        this.txtEmail = txtEmail;
        this.txtSenha = txtSenha;
        this.imgPerfil = imgPerfil;
        this.sexo = sexo;
        this.datNascimento = datNascimento;
        this.cidade = cidade;
    }

    /**
     * @return the codUsuario
     */
    public Long getCodUsuario() {
        return codUsuario;
    }

    /**
     * @param codUsuario the codUsuario to set
     */
    public void setCodUsuario(Long codUsuario) {
        this.codUsuario = codUsuario;
    }

    /**
     * @return the nomUsuario
     */
    public String getNomUsuario() {
        return nomUsuario;
    }

    /**
     * @param nomUsuario the nomUsuario to set
     */
    public void setNomUsuario(String nomUsuario) {
        this.nomUsuario = nomUsuario;
    }

    /**
     * @return the sobrenomeUsuario
     */
    public String getSobrenomeUsuario() {
        return sobrenomeUsuario;
    }

    /**
     * @param sobrenomeUsuario the sobrenomeUsuario to set
     */
    public void setSobrenomeUsuario(String sobrenomeUsuario) {
        this.sobrenomeUsuario = sobrenomeUsuario;
    }

    /**
     * @return the txtEmail
     */
    public String getTxtEmail() {
        return txtEmail;
    }

    /**
     * @param txtEmail the txtEmail to set
     */
    public void setTxtEmail(String txtEmail) {
        this.txtEmail = txtEmail;
    }

    /**
     * @return the txtSenha
     */
    public String getTxtSenha() {
        return txtSenha;
    }

    /**
     * @param txtSenha the txtSenha to set
     */
    public void setTxtSenha(String txtSenha) {
        this.txtSenha = txtSenha;
    }

    /**
     * @return the imgPerfil
     */
    public Byte getImgPerfil() {
        return imgPerfil;
    }

    /**
     * @param imgPerfil the imgPerfil to set
     */
    public void setImgPerfil(Byte imgPerfil) {
        this.imgPerfil = imgPerfil;
    }

    /**
     * @return the sexo
     */
    public String getSexo() {
        return sexo;
    }

    /**
     * @param sexo the sexo to set
     */
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    /**
     * @return the datNascimento
     */
    public Date getDatNascimento() {
        return datNascimento;
    }

    /**
     * @param datNascimento the datNascimento to set
     */
    public void setDatNascimento(Date datNascimento) {
        this.datNascimento = datNascimento;
    }

    /**
     * @return the codCidade
     */
    public Cidade getCidade() {
        return cidade;
    }

    /**
     * @param cidade the Cidade to set
     */
    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }
    
}
