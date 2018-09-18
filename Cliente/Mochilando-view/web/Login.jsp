<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">


<html>
    <head>
        <title>Login</title>
               <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
        
         <script type="text/javascript" language="JavaScript" src="js/webvalida.js"></script>
    </head>
    <body>
        <form name="frmLogin"  method="post">
        <h1>Login</h1>
        <a> E-Mail</a><br>
        <input type="text" id="email" name="email" ><br>
        <a> Senha </a> <br>
        <input type="password" id="senha" name="senha"><br>
         <button type="submit" onclick="validarCamposLogin()">Acessar</button>
        </form>
        <div>
            <a href="RecuperacaoDeSenha.jsp">Esqueci a senha</a><br>
            <a href="Cadastro.jsp">Cadastro</a>
        </div>
        <a href="Desenvolvedores.jsp">Desenvolvedores</a>
    </body>
</html>