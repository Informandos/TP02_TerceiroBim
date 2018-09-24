/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.conversao;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Juliana
 */
public class Conversao {

    public Conversao() {
    }

    public byte[] objetoParaByte(Object objeto) throws IOException {
        ByteArrayOutputStream baos;
        ObjectOutputStream writer;

        baos = new ByteArrayOutputStream();

        writer = new ObjectOutputStream(baos);
        writer.writeObject(objeto);
        writer.flush();

        writer.close();
        baos.close();

        return baos.toByteArray();
    }

    public Object byteParaObjeto(byte[] bytes) throws IOException, ClassNotFoundException {
        ObjectInputStream ois;
        Object arrayConvertido;
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        System.out.println("bytes obtidos"+Arrays.toString(bytes));
        ois = new ObjectInputStream(bais);
        arrayConvertido = (Object) ois.readObject();

        ois.close();
        bais.close();
        return arrayConvertido;
    }
}
