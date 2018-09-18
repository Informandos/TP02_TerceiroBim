<%@page import="model.domain.UsuarioTag"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.service.interfaces.InterfaceManterTag"%>
<%@page import="model.service.implementacao.ManterTag"%>
<%@page import="java.util.List"%>
<%@page import="model.domain.Tag"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<% 
    List listaTagsUsuario = (List) request.getAttribute("listaTagsUsuario");
    for(Object descricao: listaTagsUsuario){
        String descricaoStr = descricao.toString();
        %>
        <h1>Minhas tags</h1>
        <hr>${descricaoStr}<hr>   
        
<%
    }
 %>
 
<% 
/*<!DOCTYPE html>
<html>
<head>
	<title>Tags</title>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

	<!-- jQuery library -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

	<!-- Latest compiled JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<form action="Servlet" method="POST">
		<h1 align="center">Tags</h1>
		<div class="form-group" align="center">
                    <div class="form-group" align="center">
                    <div class="row">
                        <div class="col-md-3">
                            <img name="tag1" src="../web/img/<%System.out.print(desc[0]);.jpg">
                            <p name="tag1"></p>
                        </div>
                        <div class="col-md-3">
                            <img name="tag2" src="../web/img/<%System.out.print(desc[1]);.jpg">
                            <p name="tag2"></p>
                        </div>
                        <div class="col-md-3">
                            <img name="tag3" src="../web/img/<%System.out.print(desc[2]);.jpg">
                            <p name="tag3"></p>
                        </div>
                        <div class="col-md-3">
                            <img name="tag4" src="../web/img/<%System.out.print(desc[3]);.jpg">
                            <p name="tag4"></p>
                        </div>
                    </div>
                    <input align="center" type="submit" name="Editar" value="Editar" class="btn btn-default" onclick="editar_tags.jsp">
		</div>
	</form>
</body>
</html>*/%>