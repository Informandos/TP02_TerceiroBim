<%-- 
    Document   : Perfil
    Created on : 23/07/2018, 18:22:10
    Author     : Juliana
--%>

<%@page import="model.domain.Usuario"%>
<%@page import="model.service.interfaces.InterfaceManterUsuario"%>
<%@page import="model.service.implementacao.ManterUsuario"%>
<%@page import="controller.Logar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<% //Logar.validarSessao(request, response); %>

<html>
    <head>
        <title>Tela perfil</title>

        <script language="javascript">
            function Mudarestado(el) {
                var display = document.getElementById(el).style.display;
                if (display === "none")
                    document.getElementById(el).style.display = 'block';
                else
                    document.getElementById(el).style.display = 'none';
            }
        </script>
    </head>
    <body>
        <%@	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <c:import	url="cabecalho.jsp"	/>


        <div>
            <H1>Perfil </H1>
            <img src="" width="100" height="100"><br>
            <button type="button" onclick="Mudarestado('minhaDiv')">Sobre</button>
            <br>
            
            <form action="ServletWeb?logica=ListarTagUsuario" method='post'>
                <input type="submit" size="10" name="listarTag" value="Minhas tags" ></input>
            </form>
            
            <form action="ServletWeb?logica=ListarDiarioUsuario" method='post'>
                <input type="submit" size="10" name="listarTag" value="Meus diarios" ></input>
            </form>

            <div id="minhaDiv" style="display:none">
                <br><label> <b>Nome: </b>  ${requestScope.nome}</label>
                <br><label> <b>Data de nascimento: </b> ${requestScope.datanasc}</label>
                <br><label> <b>E-mail: </b>  ${requestScope.email}</label>
                <br><label> <b>Sexo: </b>  ${requestScope.sexo}</label>
                <br><label> <b>Cidade: </b> ${requestScope.cidade} </label>

            </div>

        </div>
    </body>
</html>
