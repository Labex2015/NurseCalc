package com.nursecalc.Domain.Unidades;

/**
 * Created by 0118424 on 11/12/2014.
 */
public class MicroGota implements  UnidadeMedidaVolumeContract {

    public static final String LABEL = "MicroGota";
    public static final String LABELABREVIACAO = "MicroGota";
    public static final int GOTAS = 1;
    static double      LITRO =  0.0000166666;
    public static final double MILILITRO = 0.016666 ;

    public double getQuantidade(double volume, int tempo){
         double resultado;
         resultado  = volume * 20 / tempo;
         return resultado;
    }

    @Override
    public double getMililitro(double value) {
        return value * MILILITRO;
    }

    @Override
    public double getLitro(double value) {
       return value * LITRO;
    }
}
