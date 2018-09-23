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
import model.domain.Atracao;
import model.domain.Diario;
import model.domain.Usuario;
import model.service.interfaces.InterfaceManterDiario;
import proxy.ProxyManterDiario;
import util.db.exception.ExcecaoNegocio;
import util.db.exception.ExcecaoPersistencia;

/**
 *
 * @author Juliana
 */
public class RealizarBusca implements Logica {

    @Override
    public String execute(HttpServletRequest request) throws Exception {
        String jsp;

        try {
            String textoBusca = request.getParameter("TextoBusca");

            InterfaceManterDiario manterDiario;
            manterDiario = new ProxyManterDiario();

            ArrayList<Diario> resultadoDiarios = (ArrayList<Diario>) manterDiario.pesquisarDiario(textoBusca);
            request.setAttribute("DiariosResultPesquisa", resultadoDiarios);

            //Saberemos se houve ou nao resultados encontrados na jsp
            jsp = "/ResultadosDaBusca.jsp";

        } catch (ExcecaoPersistencia e) {
            request.setAttribute("excecao", e.getMessage());
            jsp = "/erro.jsp";
        }
        return jsp;
    }

}
