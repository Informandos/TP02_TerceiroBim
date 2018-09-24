/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import adapter.Adapter;
import comunicacao.EstabeleceComunicacao;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
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
        byte[] bytesNumPacotes = new byte[TAMANHO_MAXIMO_DATAGRAMA_UDP];

        System.out.println("Esperando conexao do cliente");
        //Recebe requisicao do cliente
        DatagramPacket datagramaReceber = new DatagramPacket(bytesNumPacotes, bytesNumPacotes.length);
        servidorDatagramaSocket.receive(datagramaReceber);

        InetAddress enderecoIPCliente = datagramaReceber.getAddress();
        int portaCliente = datagramaReceber.getPort();

        boolean resposta = true;
        Conversao conversor;
        conversor = new Conversao();
        ArrayList respostArr = new ArrayList();
        respostArr.add(resposta);
        byte[] respostaBytes = conversor.objetoParaByte(respostArr);
        //enviando resposta de que pode mandar para o servidor
        DatagramPacket respostaSolicitacao;
        respostaSolicitacao = new DatagramPacket(respostaBytes, respostaBytes.length, enderecoIPCliente, portaCliente);
        servidorDatagramaSocket.send(respostaSolicitacao);

        System.out.println("A comunicacao esta autorizada");
        ArrayList arrNumPcts = (ArrayList) conversor.byteParaObjeto(bytesNumPacotes);
        int numPacotesReceber = (int) arrNumPcts.get(0);
        System.out.println("ArrayNumPactes"+Arrays.toString(bytesNumPacotes));
        System.out.println("O numero de pacotes a serem recebidos eh:" + numPacotesReceber);

        //EstabeleceComunicacao comunicacao;
        //comunicacao = new EstabeleceComunicacao(servidorDatagramaSocket, enderecoIPCliente, portaCliente );
        //Obtem todos os pacotes em um arraylist
        byte[] bytesPacotesVindosCliente = new byte[TAMANHO_MAXIMO_DATAGRAMA_UDP];
        ArrayList<Pacote> arrayPacotesRecebido = new ArrayList();
        //System.out.println("\nvetor de bytes: "+Arrays.toString(pacotesVindosCliente));

        for (int aux = 0; aux < numPacotesReceber; aux++) {

            bytesPacotesVindosCliente = new byte[TAMANHO_MAXIMO_DATAGRAMA_UDP];
            DatagramPacket datagramaReceberResposta = new DatagramPacket(bytesPacotesVindosCliente, bytesPacotesVindosCliente.length);
            servidorDatagramaSocket.receive(datagramaReceberResposta);
            System.out.print("Datagrama recebido");
            System.out.println("\nvetor de bytes apos receber datagrama:\n tamanho: " + bytesPacotesVindosCliente.length);
            System.out.println("Compare "+Arrays.toString(bytesPacotesVindosCliente));
            boolean compara = Arrays.equals(bytesPacotesVindosCliente, bytesNumPacotes);
            System.out.println("Eh igual?"+compara);
            ObjectInputStream ois;
            Object arrayConvertido;
            ByteArrayInputStream bais = new ByteArrayInputStream(bytesPacotesVindosCliente);
            System.out.println("bytes obtidos" + Arrays.toString(bytesPacotesVindosCliente));
            ois = new ObjectInputStream(bais);
            arrayConvertido = (Object) ois.readObject();

            ois.close();
            bais.close();
            
            Pacote pacoteRecebido = (Pacote) arrayConvertido;
            arrayPacotesRecebido.add(pacoteRecebido);
        }

        if (bytesPacotesVindosCliente == null) {
            System.out.println("erro, 0 pacotes vindos do cliente");
        }
        System.out.println("Pacotes vindos cliente" + bytesPacotesVindosCliente.toString());

        DesmontagemPacote desmontaPacotes = new DesmontagemPacote();

        ArrayList arrayListDestinadoAdapter = desmontaPacotes.desmontaPacotes(arrayPacotesRecebido);
        System.out.println("Imprimindo arraylist recebido");
        String a = (String) arrayListDestinadoAdapter.get(0);
        System.out.println("Primeiro" + a);
        a = (String) arrayListDestinadoAdapter.get(1);
        System.out.println("Segundo" + a);
        a = (String) arrayListDestinadoAdapter.get(2);
        System.out.println("terco" + a);
        a = (String) arrayListDestinadoAdapter.get(3);
        System.out.println("quart" + a);

        //Passa o array para o adapter tratar
        //Adapter deve tratar o pacote e enviar para o cliente
        Adapter adapter = new Adapter(arrayListDestinadoAdapter, enderecoIPCliente, portaCliente);
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
