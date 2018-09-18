/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.interfacelogica.Logica;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import model.busca.implementacao.BuscarAtracao;
import model.busca.implementacao.BuscarDiario;
import model.busca.implementacao.BuscarUsuario;
import model.domain.Atracao;
import model.domain.Diario;
import model.domain.Usuario;
import util.db.exception.ExcecaoNegocio;
import util.db.exception.ExcecaoPersistencia;


/**
 *
 * @author Juliana
 */
public class RealizarBusca implements Logica{

    @Override
    public String execute(HttpServletRequest request) throws Exception {
        String jsp = "";

        try {
           
           String textoBusca = request.getParameter("TextoBusca");
            
           BuscarAtracao buscarAtracao;
           buscarAtracao = new BuscarAtracao();
           List<Atracao> listaAtracao = buscarAtracao.buscaGeral(textoBusca);
           
           BuscarDiario buscarDiario;
           buscarDiario = new BuscarDiario();
           List<Diario> listaDiario = buscarDiario.buscaGeral(textoBusca);
           
           BuscarUsuario buscarUsuario;
           buscarUsuario = new BuscarUsuario();
           List<Usuario> listaUsuario = buscarUsuario.buscaGeral(textoBusca);
           
           

            if (listaAtracao == null && listaDiario == null && listaUsuario == null) {
               request.setAttribute("resultados", "nenhum");
            } else {
                
                
                
                request.setAttribute("listaAtracao", listaAtracao);
                request.setAttribute("listaDiario", listaDiario);
                request.setAttribute("listaUsuario", listaUsuario);
                
                
                jsp = "/ResultadosDaBusca.jsp";
            }

        } catch (ExcecaoPersistencia e) {
            request.setAttribute("excecao", e.getMessage());
            jsp = "/erro.jsp";
        }
        return jsp;
    }
    
}
