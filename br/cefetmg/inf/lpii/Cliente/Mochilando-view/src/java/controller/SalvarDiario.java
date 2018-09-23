/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.interfacelogica.Logica;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import model.domain.Dia;
import model.domain.Diario;
import model.domain.Usuario;
import model.service.interfaces.InterfaceManterDiario;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import model.domain.Atracao;
import model.domain.Comentario;
import model.domain.DiaAtracao;
import model.domain.Foto;
import model.service.interfaces.InterfaceManterDiaAtracao;
import proxy.ProxyManterDiaAtracao;
import proxy.ProxyManterDiario;

/**
 *
 * @author Juliana
 */
public class SalvarDiario implements Logica {
    
    String paginaJsp = "";
    @Override
    public String execute(HttpServletRequest request) throws Exception {
        
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        String nomDiario = request.getParameter("nomDiario");
        String tipoDiario = request.getParameter("tipoDiario");//Pega a opcao selecionada
        String txtDiario = request.getParameter("txtDiario");
        
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String datInicioStr = request.getParameter("datInicio");
        Date dataInicioFormatada = formato.parse(datInicioStr);  
        String datFimStr = request.getParameter("datFim");
        Date dataFimFormatada = formato.parse(datFimStr);
        
        
        long numDias = ChronoUnit.DAYS.between((Temporal) dataInicioFormatada, (Temporal) dataFimFormatada);

        
        //Diario
        Diario diario = new Diario();
        diario.setUsuario(usuario);
        diario.setNomDiario(nomDiario);
        diario.setTipoDiario(tipoDiario);
        diario.setDatInicioViagem(dataInicioFormatada);
        diario.setDatFimViagem(dataFimFormatada);
        diario.setTxtDiario(txtDiario);
        
        InterfaceManterDiario manterDiario = new ProxyManterDiario();
        Long codDiario = manterDiario.cadastrar(diario);
        
        //Dia
        
        
        //Sera passado um parametro hidden com o numero de dias inseridos na pagina
        //Pode ser a diferenca em dias entre a data de inicio e a data de fim
        Locale dataLocaleInicio = new Locale(datInicioStr);
        Calendar calendario = new GregorianCalendar(dataLocaleInicio); 
        for(int i=1; i<=numDias; i++){
            String txtDia = request.getParameter("txtDia "+i);
            Dia dia = new Dia();
            dia.setDiario(diario);
            dia.setTxtDia(txtDia);
            dia.setOrdemDia(i);
            calendario.add(Calendar.DAY_OF_MONTH, i);
            Date dataDia = formato.parse(formato.format(calendario.getTime()));
            dia.setDataDia(dataDia);
            
            //Obtendo atracoes do dia
            List<Atracao> listaAtracao = (List<Atracao>) request.getAttribute("listaAtracoesDia"+i);
            for(Atracao atracao: listaAtracao){
                DiaAtracao diaAtracao = new DiaAtracao();
                diaAtracao.setAtracao(atracao);
                diaAtracao.setDia(dia);
                InterfaceManterDiaAtracao manterDiaAtracao = new ProxyManterDiaAtracao();
                manterDiaAtracao.cadastrar(diaAtracao);
            }
            
            //Obtendo fotos do dia
            
            
        }
        
        
        
        return paginaJsp;
    }
    
}
