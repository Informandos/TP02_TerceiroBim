/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import comunicacao.EstabeleceComunicacao;
import util.pacote.Pacote;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.conversao.Conversao;
import util.db.exception.ExcecaoConexaoCliente;
import util.pacote.DesmontagemPacote;
import util.pacote.MontagemPacote;

/**
 *
 * @author Aluno
 */
public class Cliente {

    public static Cliente cliente;

    private final DatagramSocket clienteDatagramaSocket;
    private final String nomServidorStr = "localhost";
    private final int PORTA = 2223;
    private final InetAddress enderecoIP;
    private final int TAMANHO_MAXIMO_DATAGRAMA_UDP = 1500;
    private final int TAMANHO_MAXIMO_CONTEUDO_BYTE = 1000;
    private Conversao conversor;
    private int numPacotesEnviar;
    private int numPacotesReceber;

    private byte[] vetorBytesSaida;
    private byte[] vetorBytesEntrada;

    //ArrayList a ser recebida do proxy
    private ArrayList arrayListVindoProxy = null;
    //ArrayList a ser devolvida para o proxy
    private ArrayList arrayListDestinadoProxy = null;
    
    private final MontagemPacote montaPacotes;
    private final DesmontagemPacote desmontaPacotes;
    private final EstabeleceComunicacao comunicacao;
    

    private Cliente() throws SocketException, UnknownHostException {
        clienteDatagramaSocket = new DatagramSocket();
        enderecoIP = InetAddress.getByName(nomServidorStr);
        montaPacotes = new MontagemPacote();
        desmontaPacotes = new DesmontagemPacote();
        comunicacao = new EstabeleceComunicacao(clienteDatagramaSocket, enderecoIP, PORTA);
    }

    public static Cliente getInstance() throws SocketException, UnknownHostException {
        if (cliente == null) {
            cliente = new Cliente();
        }
        return cliente;
    }

    /*
    Realiza toda comunicacao com o servidor
    Pega o array List, converte para array
    e divide em arrays de bytes (ate 1000Bytes)
    e coloca cada array de bytes em objetos pacotes 
    Depois se estabelece a primeira comunicacao com o servidor,
    quando se envia para o servidor o numero de pacotes a serem recebidos
    o serviodor entao manda uma resposta, o primeiro pacote de resposta
    e o numero de pacotes que virao
    depois o cliente recebe todos os pacotes do servidor e os converte
    para arraylist novamente, devolvendo resposta para o proxy
    
     */
    public ArrayList requisicao(ArrayList arrayListVindo) throws IOException, ClassNotFoundException, ExcecaoConexaoCliente {
        //Recebe arrayList do proxy
        this.arrayListVindoProxy = arrayListVindo;

        //1000 bytes para conteudo, 300 para identificacao e 200 extras para o objeto Pacote
        
        ArrayList<Pacote> arrayPacotes = montaPacotes.montaPacotes(arrayListVindoProxy);
        numPacotesEnviar = arrayPacotes.size();
        
        boolean comunicAutorizada = comunicacao.enviaSolicitacaoComunicacao( numPacotesEnviar);
        
        if (comunicAutorizada) {
            
            for (Pacote pacote : arrayPacotes) {
                comunicacao.enviaPacote(pacote);
            }
            //Depois de enviar os pacotes, pega a resposta
            //O servidor deve primeiro enviar uma permissao para o cliente para 
            //mandar a resposta, informando o numero de pacotes
            int numPacotesSolicitados = comunicacao.recebeSolicitacaoComunicacao();
            //Estou supondo que os pacotes venham ja ordenados
            ArrayList<Pacote> pacotes = comunicacao.obtemRespostaPacotes(numPacotesSolicitados);
            //Pega todos os pacotes enviados e monta array list
            this.arrayListDestinadoProxy = desmontaPacotes.desmontaPacotes(pacotes);
        }
        else{
            throw new ExcecaoConexaoCliente("A conexao não foi autorizada");
        }
        return arrayListDestinadoProxy;
    }
}
