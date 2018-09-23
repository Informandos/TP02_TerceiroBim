package controller;

import controller.interfacelogica.Logica;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.domain.Tag;
import model.domain.UsuarioTag;
import model.service.interfaces.InterfaceManterTag;
import proxy.ProxyManterUsuarioTag;

public class ListarTagUsuario implements Logica{

    @Override
    public String execute(HttpServletRequest req) throws Exception {
        String jsp = "";
        try {
            
            String codUsuarioStr = req.getSession().getAttribute("codUsuario").toString();
            Long codUsuario = Long.parseLong(codUsuarioStr);
            req.getSession().setAttribute("codUsuario", codUsuario);
            
            ProxyManterUsuarioTag manterUsuarioTag = new ProxyManterUsuarioTag();
            List<UsuarioTag> listaUsuarioTag =  manterUsuarioTag.pesquisarPorCodUsuario(codUsuario);
            
            List listaDescUsuarioTag = null;
            for(UsuarioTag usuarioTag: listaUsuarioTag){
                listaDescUsuarioTag.add( usuarioTag.getTag().getDescTag());
            }
            
            
            if (listaDescUsuarioTag!= null) {
                
                req.setAttribute("listaTagsUsuario", listaDescUsuarioTag);
                jsp = "/tagsUsuario.jsp";
            } else {
                String erro = "Nao existem tags cadastradas!";
                req.setAttribute("erro", erro);
                jsp = "/erro.jsp";
            }

        } catch (Exception e) {
            e.printStackTrace();
            jsp = "";
        }
        
        return jsp;
    }
    
}
