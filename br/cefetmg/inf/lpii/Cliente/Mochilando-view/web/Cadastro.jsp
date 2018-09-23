<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <title>Cadastro</title>
        
         <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    </head>
    <body>
        <form action="ServletWeb?logica=CadastrarUsuario" method="post">
        <h3>cadastro</h3>
        
        <a>Nome</a><br>
         <input type="text" name="nome" Value="nome"><br>
         <a>Sobrenome</a><br>
         <input type="text" name="sobrenome" Value="sobrename"><br>
         <a>Data de nascimento</a><br>
         <input type="date" name="data" id="data"><br>
         <a>Estado </a><br>
         <input type="text" name="estado"><br>
         <a>Estado(Sigla)</a><br>
         <input type="text" name="estados"><br>
         <a>Cidade</a><br>
        <input type="text" name="cidade"><br>
        <a> E-Mail</a><br>
        <input type="text" name="email" Value="E-mail"><br>
        <a> sexo</a><br>
        <input type="text" name="sexo" ><br>
        
        <a>Senha</a><br>
        <input type="password" name="senha"><br>
        <a>confirme a senha</a><br>
        <input type="password" name="confirmacao"><br>
        <input type="radio" name="termos"><a>Concordo com os</a>
        <a href="Termosdeuso.jsp">Termos e Politica de privacidade</a><br>
        
        <input type="submit" value="Cadastrar">
      
        </form>
        <div>
            <a href="recuperacaoDeSenha.jsp">Esqueci a senha</a><br>
            <a href="cadastro.jsp">Cadastro</a>
        </div>
        <a href="Desenvolvedores.jsp">Desenvolvedores</a>
    </body>
</html>