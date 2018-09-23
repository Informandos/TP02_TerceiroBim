/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.db.exception;

/**
 *
 * @author Aluno
 */
public class ExcecaoConexaoCliente extends Exception {
     public ExcecaoConexaoCliente(String msg, Exception exception) {
        super(msg, exception);
    }

    public ExcecaoConexaoCliente(String msg) {
        super(msg);
    }

    public ExcecaoConexaoCliente() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    }

   

