/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.interfacelogica.Logica;
import javax.servlet.http.HttpServletRequest;
import model.domain.Diario;
import model.service.implementacao.ManterDiario;
import model.service.interfaces.InterfaceManterDiario;
import proxy.ProxyManterDiario;

/**
 *
 * @author Juliana
 */
public class MostrarDiario implements Logica {

    String paginaJsp = "";

    @Override
    public String execute(HttpServletRequest request) throws Exception {
        Long codDiarioSelecionado = null;
        codDiarioSelecionado = (Long) request.getAttribute("codDiarioSelecionado");
        InterfaceManterDiario manterDiario = new ProxyManterDiario();
        Diario diario = manterDiario.pesquisarPorId(codDiarioSelecionado);
        request.setAttribute("diarioSelecionado", diario);
        
        return paginaJsp;
    }

}
