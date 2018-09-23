/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.pacote;

import java.io.IOException;
import java.util.ArrayList;
import util.conversao.Conversao;

/**
 *
 * @author Juliana
 */
public class DesmontagemPacote {
    
    private final int TAMANHO_MAXIMO_CONTEUDO_BYTE = 1000;
    private final Conversao conversor;
    public DesmontagemPacote(){
        conversor = new Conversao();
        
    }
    public ArrayList desmontaPacotes(ArrayList<Pacote> arrayPacotes) throws IOException, ClassNotFoundException {
        ArrayList respostaArray;
        int numbytes = 0;

        for (Pacote pacote : arrayPacotes) {
            numbytes+= pacote.getConteudo().length;
            
        }
        //Quantos pacotes ha no arrayPacotes
        int numPacotes = arrayPacotes.size();

        byte[] bytesRespostaRecebida = new byte[numbytes];

        byte[] vetBytesConteudoPacote = new byte[TAMANHO_MAXIMO_CONTEUDO_BYTE];

        int restoUltimo = numbytes % this.TAMANHO_MAXIMO_CONTEUDO_BYTE;

        for (int i = 0; i < numPacotes; i++) {
            Pacote p = arrayPacotes.get(i);

            //Se e o ultimo pacote e tem tamanho numbytes % this.TAMANHO_MAXIMO_CONTEUDO_BYTE !=0 
            if ((i == (numPacotes - 1)) && restoUltimo != 0) {


                vetBytesConteudoPacote = p.getConteudo();

                //Copia para o vetor bytesRespostaRecebida ate o resto (tamanho maximo) 100 - 107 -> 8 = 108 % 25
                for (int j = 0; j < restoUltimo; j++) {
                    //Inicio
                    int inicio = i * this.TAMANHO_MAXIMO_CONTEUDO_BYTE;
                    bytesRespostaRecebida[inicio + j] = vetBytesConteudoPacote[j];
                }

            } else {

                vetBytesConteudoPacote = p.getConteudo();
                //Copia para o vetor bytesRespostaRecebida (tamanho normal pacote: 50 - 74 -> 25 = tamanho maximo
                int inicio = i * this.TAMANHO_MAXIMO_CONTEUDO_BYTE;
                
                for(int j=0; j< TAMANHO_MAXIMO_CONTEUDO_BYTE; j++){
                    bytesRespostaRecebida[inicio+j] = vetBytesConteudoPacote[j];
                }
            }

        }

        respostaArray = (ArrayList) conversor.byteParaObjeto(bytesRespostaRecebida);

        return respostaArray;
    }

}
