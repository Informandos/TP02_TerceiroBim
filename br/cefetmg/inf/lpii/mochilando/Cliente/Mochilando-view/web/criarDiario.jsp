<%-- 
    Document   : criardiario
    Created on : 27/07/2018, 20:50:28
    Author     : Juliana
--%>
<%@page import="model.service.implementacao.ManterCidade"%>
<%@page import="model.service.interfaces.InterfaceManterCidade"%>
<%@page import="model.service.interfaces.InterfaceManterEstado"%>
<%@page import="model.service.implementacao.ManterEstado"%>
<%@page import="model.domain.Estado"%>
<%@page import="java.util.List"%>
<%@page import="model.domain.Tag"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.service.implementacao.ManterTag"%>       
            
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Novo diario</title>
    </head>
    <body>
        <%@	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"	%>
        <c:import url="cabecalho.jsp"/>
        <h1>Novo diario</h1>
        <form name="frmCriarDiario" method='post'>
            <label>Titulo *:
                <input type="text" name="nomDiario" id="nomeDiario" maxlength="50" size="50">    
            </label><br />
            
            
            
            
        </form>

    </body>
</html>
