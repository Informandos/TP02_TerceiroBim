/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comunicacao;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import util.conversao.Conversao;
import util.pacote.Pacote;

/**
 *
 * @author Juliana
 */
public class EstabeleceComunicacao implements InterfaceEstabeleceComunicacao {
    
    private final Conversao conversor;
    private final int TAMANHO_MAXIMO_DATAGRAMA_UDP = 1500;
    private final DatagramSocket datagramaSocket;
    private final InetAddress enderecoIP;
    private final int PORTA;
    
    public EstabeleceComunicacao(DatagramSocket datagramaSocket,InetAddress enderecoIP, int PORTA ){
        conversor = new Conversao();
        this.datagramaSocket = datagramaSocket;
        this.enderecoIP = enderecoIP;
        this.PORTA = PORTA;
        
    }

    @Override
    public boolean enviaSolicitacaoComunicacao( int numPacotesEnviar) 
            throws IOException, ClassNotFoundException {
        boolean resposta;

        //Vetor que armazenara a resposta do servidor
        byte[] vetorBytesEntrada = new byte[1500];
        //Passa o numero de pacotes para o servidor, para ele saber quantos serao recebidos
        byte[] cabecalhoBytes = conversor.objetoParaByte(numPacotesEnviar);
        DatagramPacket primeiroPacote;
        primeiroPacote = new DatagramPacket(cabecalhoBytes, cabecalhoBytes.length, enderecoIP, PORTA);
        datagramaSocket.send(primeiroPacote);

        //Recebendo resposta (boolean) do servidor 
        DatagramPacket datagramaReceber = new DatagramPacket(vetorBytesEntrada, vetorBytesEntrada.length);
        datagramaSocket.receive(datagramaReceber);

        //Transformando byte para object(String)
        resposta = (boolean) conversor.byteParaObjeto(vetorBytesEntrada);

        return resposta;
    }

    //Aceita a solicitacao de comunicacao e obtem o numero de pacotes a serem recebidos
    @Override
    public int recebeSolicitacaoComunicacao() 
            throws IOException, ClassNotFoundException {
        byte[] conteudoServidorSolicitacao = null;
        //Recebendo numero de pacotes do servidor que quer falar quantos pacotes foram enviados
        DatagramPacket datagramaReceber = new DatagramPacket(conteudoServidorSolicitacao, conteudoServidorSolicitacao.length);
        datagramaSocket.receive(datagramaReceber);

        
        boolean resposta = true;
        byte[] respostaBytes = conversor.objetoParaByte(resposta);
        //enviando resposta de que pode mandar para o servidor
        DatagramPacket respostaSolicitacao;
        respostaSolicitacao = new DatagramPacket(respostaBytes, respostaBytes.length, enderecoIP, PORTA);
        datagramaSocket.send(respostaSolicitacao);
        
        //Transformando byte para object(String)
        int numPacotesReceber = (int) conversor.byteParaObjeto(conteudoServidorSolicitacao);

        
        return numPacotesReceber;
    }
    
     public void enviaPacote(Pacote pacote) throws IOException {
        //Converte pacote em byte
        byte[] bytesPacote;
        bytesPacote = conversor.objetoParaByte(pacote);

        //envia pacote
        DatagramPacket datagramaEnviar;
        datagramaEnviar = new DatagramPacket(bytesPacote, bytesPacote.length, enderecoIP, PORTA);
        datagramaSocket.send(datagramaEnviar);
    }

   
    //Depois de saber quantos pacotes virao, é hora de pega-los
    public ArrayList<Pacote> obtemRespostaPacotes(int numPacotesReceber) throws IOException, ClassNotFoundException {

        //Obtem todos os pacotes em um arraylist
        byte[] bytesPacoteResposta = new byte[TAMANHO_MAXIMO_DATAGRAMA_UDP];
        ArrayList<Pacote> arrayPacotesRecebido = null;

        for (int aux = 0; aux < numPacotesReceber; aux++) {

            DatagramPacket datagramaReceberResposta = new DatagramPacket(bytesPacoteResposta, bytesPacoteResposta.length);
            datagramaSocket.receive(datagramaReceberResposta);
            Pacote pacoteRecebido = (Pacote) conversor.byteParaObjeto(bytesPacoteResposta);
            arrayPacotesRecebido.add(pacoteRecebido);
        }
        return arrayPacotesRecebido;
    }
    
}
