<!DOCTYPE html>
<html>
    <head>
        <title>Ajuda</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <style type="text/css">

            * {padding:0; margin:0;}
            a {text-decoration: none;}
            li {list-style: none;}

            /* Navigation Styling */
            .main-nav {width: 700px; background-color: gray;}
            .main-nav a {
                text-transform: uppercase;
                letter-spacing: .2em;
                color: #000000;
                display: block;
                padding: 10px 0 10px 20px;
                border-bottom: 1px dotted gray;
            }

            .main-nav a:hover {background: rgba(121,165,162,1.00);}
            .main-nav-ul ul {display: none;}
            .main-nav-ul li:hover ul {display: block;}

        </style>

    </head>
    <body>
        <%@	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"	%>
        <c:import	url="cabecalho.jsp"	/>

        <h1 align="center">Ajuda</h1>
        <div align="center">
            <nav class="main-nav">
                <ul class="main-nav-ul">
                    <li><a href="#">Artigos Mais Lidos</a>
                        <ul>
                            <li style="background: rgba(180,205,203,1.00)"><a href="#">Fazer Login</a></li>
                            <li style="background: rgba(180,205,203,1.00)"><a href="#">Criar Uma Conta</a></li>
                            <li style="background: rgba(180,205,203,1.00)"><a href="#">Esqueceu a Senha</a></li>
                            <li style="background: rgba(180,205,203,1.00)"><a href="#">Fale Consoco</a></li>
                        </ul>
                    </li>
                    <li><a href="#">Configurações de Conta</a>
                        <ul>
                            <li style="background: rgba(180,205,203,1.00)"><a href="#">trocar foto de prefil</a></li>
                            <li style="background: rgba(180,205,203,1.00)"><a href="#">apagar post</a></li>
                            <li style="background: rgba(180,205,203,1.00)"><a href="#">apagar comentário</a></li>
                        </ul>
                    </li>
                    <li><a href="#">Solucionar Problemas</a>
                        <ul>
                            <li style="background: rgba(180,205,203,1.00)"><a href="#">Item 1</a></li>
                            <li style="background: rgba(180,205,203,1.00)"><a href="#">Item 2</a></li>
                            <li style="background: rgba(180,205,203,1.00)"><a href="#">Item 3</a></li>
                            <li style="background: rgba(180,205,203,1.00)"><a href="#">Item 4</a></li>
                        </ul>
                    </li>
                </ul>
            </nav>
        </div>

    </body>
</html>