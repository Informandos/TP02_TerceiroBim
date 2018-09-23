/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import static servidor.Servidor.requisicao;
import util.conversao.Conversao;
import util.db.exception.ExcecaoConexaoCliente;
import util.db.exception.ExcecaoNegocio;
import util.db.exception.ExcecaoPersistencia;

/**
 *
 * @author Juliana
 */
public class TesteServidor {
    private static DatagramSocket servidorDatagramaSocket = null;
    private static final int PORTASERVIDOR = 2223;
    private static final int TAMANHO_MAXIMO_DATAGRAMA_UDP = 1000;
   

    public static void omain(String args[]) throws IOException, ClassNotFoundException, ExcecaoPersistencia, ExcecaoNegocio, ExcecaoConexaoCliente {

        servidorDatagramaSocket = new DatagramSocket(PORTASERVIDOR);
        //Servidor eternamente ligado (enquanto a main estiver rodando)
        while (true) {
            requisicao();
        }
    }
    public static void requisicao() throws IOException, ClassNotFoundException, ExcecaoPersistencia, ExcecaoConexaoCliente, ExcecaoNegocio {

        /*Preparando recebimento do pacote (datagrama)*/
        //Vetor de bytes a ser recebido do cliente
        byte[] vetorBytesVindoCliente = new byte[TAMANHO_MAXIMO_DATAGRAMA_UDP];
        
        System.out.println("Esperando conexao do cliente");
        //Recebe requisicao do cliente
        DatagramPacket datagramaReceber = new DatagramPacket(vetorBytesVindoCliente, vetorBytesVindoCliente.length);
        servidorDatagramaSocket.receive(datagramaReceber);
        
        InetAddress enderecoIPCliente = datagramaReceber.getAddress();
        int portaCliente = datagramaReceber.getPort();
        
        boolean resposta = true;
        Conversao conversor;
        conversor = new Conversao();
        byte[] respostaBytes = conversor.objetoParaByte(resposta);
        //enviando resposta de que pode mandar para o servidor
        DatagramPacket respostaSolicitacao;
        respostaSolicitacao = new DatagramPacket(respostaBytes, respostaBytes.length, enderecoIPCliente, portaCliente);
        servidorDatagramaSocket.send(respostaSolicitacao);
        
    }
    
        
}
