package controller;

import controller.interfacelogica.Logica;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

public class ServletWeb extends HttpServlet {

    private String paginaJsp = "";

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String parametro = request.getParameter("logica");
        
        String nomeDaClasse = "controller." + parametro;
        
        //Logar nao implementa a interface logica
        if (parametro.equals("Logar")) {
            try {
                paginaJsp = Logar.execute(request);
            } catch (Exception ex) {
                Logger.getLogger(ServletWeb.class.getName()).log(Level.SEVERE, null, ex);
            }
            RequestDispatcher rd = request.getRequestDispatcher(paginaJsp);
            rd.forward(request, response);
            
        } else {
            try {
                Class<?> classe;
                classe = Class.forName(nomeDaClasse);
                Logica logica = (Logica) classe.newInstance();
                //Recebe a String após a execução da logica

                paginaJsp = logica.execute(request);
                
                //Faz o forward para a página JSP
                //Redirecionando pagina
                RequestDispatcher rd = request.getRequestDispatcher(paginaJsp);
                rd.forward(request, response);
            } catch (Exception e) {
                throw new ServletException("Erro ao direcionar", e);
            }
        }

    }
}
