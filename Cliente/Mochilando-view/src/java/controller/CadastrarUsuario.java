/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.interfacelogica.Logica;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import model.domain.Usuario;
import model.service.interfaces.InterfaceManterUsuario;
import proxy.ProxyManterUsuario;
import util.db.exception.ExcecaoConexaoCliente;
import util.db.exception.ExcecaoNegocio;
import util.db.exception.ExcecaoPersistencia;

/**
 *
 * @author User
 */
@WebServlet(name = "CadastrarUsuario", urlPatterns = {"/CadastrarUsuario"})
public class CadastrarUsuario extends HttpServlet implements Logica{
   @Override
    public String execute(HttpServletRequest request) throws Exception {
       
    try {
            String jsp = "";
            String nome = request.getParameter("nome");
            String sobrenome = request.getParameter("sobrenome");
            String data = request.getParameter("data");
            String senha = request.getParameter("senha");
            String sexo = request.getParameter("sexo");
            String email = request.getParameter("email");
            String cidade = request.getParameter("cidade");
            String estado = request.getParameter("estado");
            String estados = request.getParameter("estados");
            /*
            Estado est = new Estado();
            est.setNomEstado(estado);
            est.setSigla(estados);
            ManterEstado mest = new ManterEstado();
            mest.cadastrar(est);
            Cidade cid = new Cidade();
            cid.setNomCidade(cidade);
            cid.setEstado(est);
            ManterCidade mcid = new ManterCidade();
            mcid.cadastrar(cid);
            */
            Usuario usuario = new Usuario();
            usuario.setNomUsuario(nome);
            usuario.setSobrenomeUsuario(sobrenome);
            usuario.setDatNascimento(new SimpleDateFormat("yyyy-MM-dd").parse(data));
            usuario.setTxtSenha(senha);
            usuario.setSexo(sexo);
            usuario.setTxtEmail(email);
            usuario.setCidade(null);
            usuario.setImgPerfil(null);
            usuario.setCodUsuario(null);
            InterfaceManterUsuario usrProxy = new ProxyManterUsuario();
            Long cadastrar = usrProxy.cadastrar(usuario);
        } catch (ExcecaoPersistencia | ExcecaoNegocio ex) {
            Logger.getLogger(CadastrarUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }catch (ExcecaoConexaoCliente ex) {
            Logger.getLogger(CadastrarUsuario.class.getName()).log(Level.SEVERE, null, ex);
            return "falhaConexao.jsp";
        }
       return "index.jsp";
    }
     
        
    
}

