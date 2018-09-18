<%@page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Editar Tags</title>
        <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

        <!-- CSS -->
        <style type="text/css">
            .check {
                opacity:0.5;
                color:#996;    
            }
        </style>

        <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

        <!-- Latest compiled JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

        <!-- JavaScript -->
        <script>
            $(document).ready(function (e) {
                $(".img-check").click(function () {
                    $(this).toggleClass("check");
                });
            });
        </script>
        <script type="text/javascript" language="JavaScript" src="js/webvalida.js"></script>
    </head>
    <body>
        <div class="container">
            <form action="ServletWeb?logica=EditarTagUsuario" method="POST" name="frmEditarTag">
                <h1 align="center">Selecione suas 4 Tags</h1>
                <div class="form-group" align="center">
                    <div class="row">
                        <div class="col-md-3">
                            <label class="btn btn-primary">
                                <img src="../web/img/Montanha.jpg" alt="..." class="img-thumbnail img-check">
                                <input type="checkbox" name="tag" id="item1" value="Montanha" class="hidden" autocomplete="off">
                            </label>
                            <p>Montanha</p>
                        </div>
                        <div class="col-md-3">
                            <label class="btn btn-primary">
                                <img src="../web/img/Praia.jpg" alt="..." class="img-thumbnail img-check">
                                <input type="checkbox" name="tag" id="item2" value="Praia" class="hidden" autocomplete="off">
                            </label>
                            <p>Praia</p>
                        </div>
                        <div class="col-md-3">
                            <label class="btn btn-primary">
                                <img src="../web/img/Interior.jpg" alt="..." class="img-thumbnail img-check">
                                <input type="checkbox" name="tag" id="item3" value="Interior" class="hidden" autocomplete="off">
                            </label>
                            <p>Interior</p>
                        </div>
                        <div class="col-md-3">
                            <label class="btn btn-primary">
                                <img src="../web/img/praca.jpg" alt="..." class="img-thumbnail img-check">
                                <input type="checkbox" name="tag" id="item4" value="cidade" class="hidden" autocomplete="off">
                            </label>
                            <p>Praça</p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-3">
                            <label class="btn btn-primary">
                                <img src="../web/img/Parque.jpg" alt="..." class="img-thumbnail img-check">
                                <input type="checkbox" name="tag" id="item5" value="Parque" class="hidden" autocomplete="off">
                            </label>
                            <p>Parque</p>
                        </div>
                        <div class="col-md-3">
                            <label class="btn btn-primary">
                                <img src="../web/img/7 Maravilhas.jpg" alt="..." class="img-thumbnail img-check">
                                <input type="checkbox" name="tag" id="item6" value="7 Maravilhas" class="hidden" autocomplete="off">
                            </label>
                            <p>7 Maravilhas</p>
                        </div>
                        <div class="col-md-3">
                            <label class="btn btn-primary">
                                <img src="../web/img/Museu.jpg" alt="..." class="img-thumbnail img-check">
                                <input type="checkbox" name="tag" id="item7" value="Museu" class="hidden" autocomplete="off">
                            </label>
                            <p>Museu</p>
                        </div>
                        <div class="col-md-3">
                            <label class="btn btn-primary">
                                <img src="../web/img/Cidade.jpg" alt="..." class="img-thumbnail img-check">
                                <input type="checkbox" name="tag" id="item8" value="Cidade" class="hidden" autocomplete="off">
                            </label>
                            <p>Cidade</p>
                        </div>
                    </div>
                    <div class="row">
                        <div align="center">
                            <input align="center" type="submit" name="Salvar" value="Salvar" class="btn btn-default" onclick="validarCamposEditarTag()"> <input align="center" type="button" name="Cancelar" value="Cancelar" class="btn btn-default">
                        </div>
                    </div>    
                </div>
            </form>
        </div>
    </body>
</html>