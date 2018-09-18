/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.interfacelogica.Logica;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import model.domain.Diario;
import util.pesquisas.AtualizacaoDiarios;

/**
 *
 * @author Juliana
 */
public class PaginaInicial implements Logica {

    @Override
    public String execute(HttpServletRequest request) throws Exception {
        String paginaJsp = "";

        //Obtem dados do usuario para exibir seus diarios no jsp a ser enviado (pagina inicial)
        //Busca por todos os diarios que tem alguma tag nesse conjunto de tags do usuario
        List<Diario> listaDiario = null;
                
                AtualizacaoDiarios atualizacao = new AtualizacaoDiarios();
                //listaDiario = atualizacao.atualizar(usr.getCodUsuario());*/
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
