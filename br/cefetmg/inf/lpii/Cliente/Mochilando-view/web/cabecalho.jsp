<%-- 
    Document   : cabecalho
    Created on : 27/07/2018, 20:47:40
    Author     : Juliana
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cabecalho</title>
    </head>
    <body>

        <div style=" top:0px; vertical-align:top; left:0px;">
            <a href="#">
                <form action="ServletWeb?logica=PaginaInicial" method="post" >
                    <input type="submit" name="paginainicial" value="Pagina Inicial">
                </form><t></t>
                <form action="buscar.jsp" method="post" >
                    <input type="text" name="busca" value="busca" onfocus="this.value = '';" />
                    <input type="submit" name="Buscar" value="Buscar">

                </form>
                <form action="ServletWeb?logica=Perfil" method='post'>

                    <input type="submit" size="10" name="perfil" value="Perfil" ></input>
                </form>

                <form action="ServletWeb?logica=CriarDiario" method='post'>
                    <input type="submit" size="10" name="novoDiario" value="Criar Novo Diario" ></input>

                </form>

                <form action="ajuda.jsp">

                    <input type="submit" size="10" name="novoDiario" value="Ajuda" ></input>
                </form>

            </a>
            <hr	/>
        </div>
    </body>
</html>
