/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import adapter.Adapter;
import comunicacao.EstabeleceComunicacao;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.conversao.Conversao;
import util.db.exception.ExcecaoConexaoCliente;
import util.db.exception.ExcecaoNegocio;
import util.db.exception.ExcecaoPersistencia;
import util.pacote.DesmontagemPacote;
import util.pacote.Pacote;

/**
 *
 * @author Juliana Carvalho de Souza
 */
/*
    Esta classe é responsável por tratar os pacotes (datagramas) vindos do cliente
    Recebe os pacotes e manda os dados para o adapter que é quem vai decidir o que criar
    de acordo com os parametros do pacote

 */
public class Servidor {

    private static DatagramSocket servidorDatagramaSocket = null;
    private static final int PORTASERVIDOR = 2223;
    private static final int TAMANHO_MAXIMO_DATAGRAMA_UDP = 1000;
   

    public static void main(String args[]) throws IOException, ClassNotFoundException, ExcecaoPersistencia, ExcecaoNegocio, ExcecaoConexaoCliente {

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
        
        EstabeleceComunicacao comunicacao;
        comunicacao = new EstabeleceComunicacao(servidorDatagramaSocket, enderecoIPCliente, portaCliente );
        
        int numPacotesReceber = (int) conversor.byteParaObjeto(vetorBytesVindoCliente);
        ArrayList<Pacote> pacotesVindosCliente = comunicacao.obtemRespostaPacotes(numPacotesReceber);
        
        DesmontagemPacote desmontaPacotes = new DesmontagemPacote();
        
        ArrayList arrayListDestinadoAdapter = desmontaPacotes.desmontaPacotes(pacotesVindosCliente);
        
        
        //Passa o array para o adapter tratar
        //Adapter deve tratar o pacote e enviar para o cliente
        Adapter adapter = new Adapter(arrayListDestinadoAdapter,  enderecoIPCliente, portaCliente);
        try {
            adapter.tratarRequisicao();
        } catch (ExcecaoNegocio | ExcecaoConexaoCliente ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        Thread adapterThread = new Thread(adapter);
        //Adapter deve enviar para o cliente no metodo run
        adapterThread.start();
    }
}
