package com.nursecalc.Domain.Unidades;

/**
 * Created by 0118424 on 11/12/2014.
 */
public class Minuto implements UnidadeMedidaTempoContract {

    static double MICROSEGUNDO = 3600;
    static double      SEGUNDO = 60;
    static double      MINUTO  = 1;
    static double      HORA    = 60;
    public static final String LABEL = "Minuto";
    public static final String LABELABREVIACAO = "min";

    public double getMicroSegundo(double minute){
        return minute * MICROSEGUNDO;
    }
    public double getSegundo(double minute){
        return minute * SEGUNDO;
    }
    public double getMinuto(double minute){
         return minute * MINUTO;
    }
    public double getHora(double minute){
        return minute / HORA;
    }




}
