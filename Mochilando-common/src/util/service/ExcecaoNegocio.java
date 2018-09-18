/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.db.exception;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Juliana
 */
public class ExcecaoNegocio extends Exception {

    private List<String> msgs;

    public ExcecaoNegocio() {
        this.msgs = new ArrayList();
    }

    public ExcecaoNegocio(String msg, Exception exception) {
        super(msg, exception);
    }

    public ExcecaoNegocio(String msg) {
        super(msg);
    }

    public ExcecaoNegocio(List<String> msgs) {
        this.msgs = msgs;
    }

    public List<String> getMessages() {
        return this.msgs;
    }
}
