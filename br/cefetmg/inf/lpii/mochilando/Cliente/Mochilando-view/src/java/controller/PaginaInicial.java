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
import model.domain.Usuario;
import model.service.interfaces.InterfaceManterDiario;
import proxy.ProxyManterDiario;

/**
 *
 * @author Juliana
 * A pagina inicial recebe todos os diarios com tags que o usuario segue
 */
public class PaginaInicial implements Logica {

    @Override
    public String execute(HttpServletRequest request) throws Exception {
        String paginaJsp;

        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        

        InterfaceManterDiario manterDiario;
        manterDiario = new ProxyManterDiario();
        //Se a lista de diarios for null, continua mostrando null
        ArrayList<Diario> diarios = (ArrayList<Diario>) manterDiario.atualizarPagInicial(usuario.getCodUsuario());
        request.setAttribute("DiariosPagInicial", diarios);
        
        /*Diario diarioSegundo = new Diario();
        diarioSegundo.setCodDiario(1L);
        diarioSegundo.setNomDiario("nomdiario2");

        Diario diarioSegundot = new Diario();
        diarioSegundot.setCodDiario(2L);
        diarioSegundot.setNomDiario("nomdiario3");

        List<Diario> teste = new ArrayList();
        teste.add(diarioSegundo);
        teste.add(diarioSegundot);

        request.setAttribute("listadiario", teste);*/
        paginaJsp = "/paginaInicial.jsp";
        return paginaJsp;
    }

}
