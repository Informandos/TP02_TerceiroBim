/* 
 Todo que o programa fazer na parte nos html deve passa aqui para depois ir para o controller
 */

function validarCamposLogin() {
    var frm = document.frmLogin;
    var email = frm.email.value;
    var senha = frm.senha.value;

    if (email === "") {
        alert("Favor, preencha o campo email!");
        frm.email.focus();
        return false;
    } else if (senha === "") {
        alert("Favor, preencha o campo senha!");
        frm.senha.focus();
        return false;
    } else {
        caminhourl = "/Mochilando/ServletWeb?logica=Logar";
        document.forms[0].action = caminhourl;
        window.document.forms[0].submit();
        return true;
    }
}
/*
 separe cada fução 
 */
/*---------------------------------------------------------------------------------------------------*/
function Checkfiles(){
    var fup = document.getElementById('selecionararquivo');
    var fileName = fup.value;
    var ext = fileName.substring(fileName.lastIndexOf('.') + 1);

    if(ext =="jpeg" || ext=="png"){
        return true;
    }
    else{
        return false;
    }
}
/*
 separe cada fução 
 */
/*---------------------------------------------------------------------------------------------------*/
function validarCamposDiario() {
    var frm = document.frmCriarDiario;
    var nomdiario = frm.nomdiario.value;
    var descDiario = frm.descDiario.value;

    if (nomdiario == "") {
        alert("Favor, preencha o campo titulo!");
        frm.nomdiaro.focus();
        return false;
    } else if (descDiario == "") {
        alert("Favor, preencha o campo descDiario!");
        frm.descDiario.focus();
        return false;
    } else {
        caminhourl = "/Mochilando/ServletWeb?logica=CriarDiario";
        document.forms[0].action = caminhourl;
        window.document.forms[0].submit();
        return true;
    }
}
/*
 separe cada fução 
 */
/*---------------------------------------------------------------------------------------------------*/
function validarCamposEditarTag(){
    var frm = document.frmEditarTag;
    var tag = frm.tag.values;
    
    if(tag.length === 4){
        caminhoURL = "/Mochilando/ServelWeb?logica=EditarTagUsuario";
        document.forms[0].action = caminhoURL;
        window.document.forms[0].submit();
        return true;
    }else{
        alert("Favor selecionar 4 tags");
        return false;
    }
}
/*
 separe cada fução 
 */
/*---------------------------------------------------------------------------------------------------*/
function validarTags(){
    var frm = document.frmTags;
    
}
/* 
 Todo que o programa fazer na parte nos html deve passa aqui para depois ir para o controller
 */