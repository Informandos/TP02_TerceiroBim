<%-- 
    Document   : Desenvolvedores
    Created on : 19/07/2018, 11:45:41
    Author     : User
--%>

<%@page import="model.domain.Diario"%>
<%@page import="model.service.interfaces.InterfaceManterAtracao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Desenvolvedores</h1>
        Carlos Brasileiro<br>
        Eduardo Epiphanio<br>
        Felipe Linhares<br>
        Juliana Carvalho<br>
        Lucca Bessa
        <% Diario diario = new Diario(); 
        diario.setNomDiario("nome do diario");
        %>
        
        <br>Nome do diario: <% diario.getNomDiario(); %>
    </body>
</html>
