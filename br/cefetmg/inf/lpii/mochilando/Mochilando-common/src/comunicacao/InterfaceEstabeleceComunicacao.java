/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comunicacao;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author Juliana
 */
public interface InterfaceEstabeleceComunicacao {
    
    public boolean enviaSolicitacaoComunicacao( int numPacotesEnviar) 
            throws IOException, ClassNotFoundException;
    
    public int recebeSolicitacaoComunicacao()
            throws IOException, ClassNotFoundException;
    
    
}
