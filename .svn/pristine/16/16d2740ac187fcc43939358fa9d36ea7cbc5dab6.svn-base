package com.nursecalc.Domain.Unidades;

/**
 * Created by 0118424 on 11/12/2014.
 */
public class UnidadeMedida implements  Unidade{

    public static final int MINUTE = 0;
    public static final int HOUR   = 1;
    public static final int MILILITRO = 2;
    public static final int MICROGOTA = 3;
    public static final int LITRO = 4;
    public static final int GOTA = 5;

    private String[] medidasVolumetricas = {
                                                Mililitro.LABEL,
                                                Litro.LABEL
                                            };
    private String[] medidasTemporal = {
            Hora.LABEL,
            Minuto.LABEL,

    };

    Hora h;
    Minuto m ;

    @Override
    public double converte(int from, int to, double value) {
        double valor = 0;
        h = new Hora();
        m = new Minuto();

        if(from == MINUTE  && to == HOUR){
            valor =  m.getHora(value);
        }else if(from == HOUR && to == MINUTE){
            valor = h.getMinuto(value);
        }
        return valor;

    }
    public String[] getUnidadesDeMedidaVolumetricas(){
        return this.medidasVolumetricas;
    }
    public String[] getUnidadesDeMedidaTemporal(){
        return this.medidasTemporal;
    }





}
