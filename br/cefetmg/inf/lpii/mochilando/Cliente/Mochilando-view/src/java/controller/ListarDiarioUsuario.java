/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.interfacelogica.Logica;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import model.domain.Diario;
import model.service.interfaces.InterfaceManterDiario;
import proxy.ProxyManterDiario;

/**
 *
 * @author Juliana
 */
public class ListarDiarioUsuario implements Logica {

    @Override
    public String execute(HttpServletRequest request) throws Exception {
        String paginaJsp = "";
        String stringCodUsuario = request.getSession().getAttribute("codUsuario").toString();
        Long codUsuario = Long.parseLong(stringCodUsuario);

        InterfaceManterDiario manterDiario = new ProxyManterDiario();
        List<Diario> listaDiario = manterDiario.pesquisarPorCodUsuario(codUsuario);

        if (listaDiario != null) {
            request.setAttribute("listaDiario", listaDiario);
            paginaJsp = "/meusdiarios.jsp";
        } else {
            
            String erro = "Usuario sem diario!";
            request.setAttribute("erro", erro);
            paginaJsp = "/erro.jsp";
        }
        return paginaJsp;
    }

}
