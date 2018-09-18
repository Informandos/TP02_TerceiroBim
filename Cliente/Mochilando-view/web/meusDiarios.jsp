<%-- 
    Document   : meusdiarios
    Created on : 30/07/2018, 01:57:46
    Author     : Juliana
--%>

<%@page import="model.domain.Diario"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"	%>
        <c:import url="cabecalho.jsp"/>
        <h1>Meus diarios</h1>
        
        <%
            List<Diario> listaDiario = (List<Diario>) request.getAttribute("listaDiario");
            for (Diario diario : listaDiario) {
        %>
        <hr>
        <br>Titulo:  <%=diario.getNomDiario()%>
        <br>Autor: <%=diario.getUsuario().getNomUsuario() %>
        <br>Texto: <%=diario.getTxtDiario() %>
        <br><button>Ver diario completo </button>
        <hr>
        <%
            }
        %>
    </body>
</html>
