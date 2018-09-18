/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.db.exception;

/**
 *
 * @author Juliana
 */
public class ExcecaoPersistencia extends Exception {

    public ExcecaoPersistencia(String msg, Exception exception) {
        super(msg, exception);
    }

    public ExcecaoPersistencia(String msg) {
        super(msg);
    }

}

