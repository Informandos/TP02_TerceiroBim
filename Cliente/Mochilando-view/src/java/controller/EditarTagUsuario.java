package controller;

import controller.interfacelogica.Logica;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditarTagUsuario implements Logica {

    @Override
    public String execute(HttpServletRequest req) throws Exception {
        String descTag;
        descTag = req.getParameter("tag");
        return null;
    }
    
}
