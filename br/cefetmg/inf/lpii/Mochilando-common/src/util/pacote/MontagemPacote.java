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
public class MontagemPacote {
    private final int TAMANHO_MAXIMO_CONTEUDO_BYTE = 1000;
    private final Conversao conversor ;
    
    public MontagemPacote(){
       conversor  = new Conversao();
    }
    
    public ArrayList<Pacote> montaPacotes(ArrayList array) throws IOException {

        ArrayList<Pacote> arrayPacotes = new ArrayList();

        //calcula o tamanho em bytes do dado
        // vetorBytesEntrada = objetoParaByte(array);
        byte[] vetorBytesEntrada = conversor.objetoParaByte(array);

        //Primeiro copia os pacotes inteiros
        //tamanho do vetor de bytes
        int tamVetorBytes = vetorBytesEntrada.length;
        int resto = tamVetorBytes % TAMANHO_MAXIMO_CONTEUDO_BYTE;
        int maxIterador;
        if (resto == 0) {
            maxIterador = tamVetorBytes;
        } else {
            maxIterador = tamVetorBytes - resto;
        }
        byte[] aux = new byte[TAMANHO_MAXIMO_CONTEUDO_BYTE];
        Pacote pac;
        int ordem;
        for (int i = 0; i < maxIterador; i++) {
            //Resto que vai de 0 a TAMANHO_MAXIMO_CONTEUDO_BYTE -1
            int indiceAux = i % TAMANHO_MAXIMO_CONTEUDO_BYTE;
            aux[indiceAux] = vetorBytesEntrada[i];
            //quando o i for multiplo de TAMANHO_MAXIMO_CONTEUDO_BYTE -1:
            if (indiceAux == TAMANHO_MAXIMO_CONTEUDO_BYTE - 1) {

                //Copia o vetor aux para o conteudo do pacote
                //Ordem e (i+1)/TAMANHO_MAXIMO_CONTEUDO_BYTE
                ordem = (i + 1) / TAMANHO_MAXIMO_CONTEUDO_BYTE;
                //Copia o conteudo
                arrayPacotes.add(new Pacote(ordem, aux));

                //Apaga tudo de aux
                aux = new byte[TAMANHO_MAXIMO_CONTEUDO_BYTE];

            }
        }
        //Depois copia o que falta, se o resto for !=0
        if (resto != 0) {
            byte[] ultimoAux = new byte[resto];
            for (int indice = 0; indice < resto; indice++) {
                ultimoAux[indice] = vetorBytesEntrada[maxIterador + indice];
            }

            ordem = tamVetorBytes / TAMANHO_MAXIMO_CONTEUDO_BYTE;
            ordem++;
           
            arrayPacotes.add(new Pacote(ordem, ultimoAux));
        }
        //this.numeroPacotesEnviar = arrayPacotes.size();

        
        return arrayPacotes;
    }

}
