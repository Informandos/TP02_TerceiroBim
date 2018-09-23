package util.pacote;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;

/**
 *
 * @author Juliana
 */
public class Pacote {
    
    private int ordem;
    private byte[] conteudo;

    
    
    public Pacote( int ordem, byte[] conteudo) {
      
        this.ordem = ordem;
        this.conteudo = conteudo;
    }
    
    public byte[] getConteudo(){
        return this.conteudo;
    }
    
    public int getOrdem(){
        return this.ordem;
    }
    
    public void setOrdem(int ordem){
        this.ordem = ordem;
    }

    public void setConteudo(byte[] conteudo) {
        this.conteudo = conteudo;
    }
    
}
