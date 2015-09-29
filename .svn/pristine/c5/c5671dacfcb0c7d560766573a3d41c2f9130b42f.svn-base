package com.nursecalc.Domain.Unidades;

import android.util.Log;

/**
 * Created by 0118424 on 11/12/2014.
 */
public class Gota implements  UnidadeMedidaVolumeContract {

    public static final int MICRO_GOTA = 3;
    public static final String LABEL = "Gota";
    public static final String LABELABREVIACAO = "gts";

    static double      LITRO =  0.00005;
    public static final double MILILITRO = 0.05 ;

    @Override
    public double getMililitro(double value) {
        Log.d("Mililitro", value+"");
        return value *  MILILITRO;
    }

    @Override
    public double getLitro(double value) {
        return value * LITRO;
    }
}
