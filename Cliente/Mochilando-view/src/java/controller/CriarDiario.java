/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.interfacelogica.Logica;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.domain.Estado;
import model.domain.Tag;
import model.domain.Usuario;
import model.service.interfaces.InterfaceManterEstado;
import model.service.interfaces.InterfaceManterTag;
import model.service.interfaces.InterfaceManterTipoAtracao;
import model.service.interfaces.InterfaceManterUsuario;
import proxy.ProxyManterEstado;
import proxy.ProxyManterTag;
import proxy.ProxyManterTipoAtracao;

/**
 *
 * @author Juliana
 */
public class CriarDiario implements Logica {

    @Override
    public String execute(HttpServletRequest request) throws Exception {

        InterfaceManterTag manterTag =  new ProxyManterTag();
        List<Tag> listaTag = manterTag.pesquisarTodos();
        request.setAttribute("listaTag", listaTag);
        //Pegando todos os estados
        InterfaceManterEstado manterEstado = new ProxyManterEstado();
        List<Estado> listaEstado = manterEstado.pesquisarTodos();
        request.setAttribute("listaEstado", listaEstado);
        
        InterfaceManterTipoAtracao manterTipoAtraco = new ProxyManterTipoAtracao();

        String jsp = "/criarDiario.jsp";

        return jsp;
    }

}
