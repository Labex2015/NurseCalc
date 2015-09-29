package com.nursecalc.Domain;

import android.content.Context;
import android.util.Log;

import com.nursecalc.Domain.Unidades.Gota;
import com.nursecalc.Domain.Unidades.Hora;
import com.nursecalc.Domain.Unidades.Litro;
import com.nursecalc.Domain.Unidades.MicroGota;
import com.nursecalc.Domain.Unidades.Mililitro;
import com.nursecalc.Domain.Unidades.Minuto;
import com.nursecalc.Domain.Unidades.UnidadeMedida;
import com.nursecalc.Domain.Unidades.UnidadeMedidaTempoContract;
import com.nursecalc.Domain.Unidades.UnidadeMedidaVolumeContract;
import com.nursecalc.R;

import java.text.DecimalFormat;

/**
 * Created by 0118424 on 01/12/2014.
 */
public class Gotejamento{

    public static int MICROGOTEJAMENTO_TEMPO = 1;
    public static int TEMPO_MICROGOTEJAMENTO = 2;

    private double volume;
    private int gotas;
    private int    horas;
    private Context context;
    private int icon;
    private int unidadeMedidaVolume;
    private int unidadeMedidaTempo;

    public Gotejamento(Context context){
        this.context = context;
        this.icon = R.drawable.gotejamento;
    }
    public int getIcon() {
        return icon;
    }



    public Gotejamento(double v, int h){
        this.volume = v;
        this.horas  = h;
    }

    public Gotejamento(double v, int h, int gotas, int unidadeMedidaVolume, int unidadeMedidaTempo){
        this.volume = v;
        this.horas  = h;
        this.gotas = gotas;
        this.unidadeMedidaVolume = unidadeMedidaVolume;
        this.unidadeMedidaTempo = unidadeMedidaTempo;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public String getGotejamento(){

        UnidadeMedidaVolumeContract objectVolume = (UnidadeMedidaVolumeContract) this.getObjectVolumeByIndex(this.unidadeMedidaVolume);
        Double volumeConverted = objectVolume.getMililitro(this.volume);

        if(this.horas != 0) {
            UnidadeMedidaTempoContract objectTempo = (UnidadeMedidaTempoContract) this.getObjectTempoByIndex(this.unidadeMedidaTempo);
            Double tempoConverted = objectTempo.getHora(this.horas);
            return "Resultado: "+Math.round((volumeConverted / (tempoConverted * 3)))+" gts/min";
        }else{
            double hourAux = volumeConverted / (this.gotas* 3);
            return "Resultado: "+convertDecimalHoursToFormattedHour(hourAux);
        }
    }

    public String getMicroGotejamento(int tipo){

        UnidadeMedidaVolumeContract objectVolume = (UnidadeMedidaVolumeContract) this.getObjectVolumeByIndex(this.unidadeMedidaVolume);
        Double volumeConverted = objectVolume.getMililitro(this.volume);

        if(tipo == TEMPO_MICROGOTEJAMENTO) {
            UnidadeMedidaTempoContract objectTempo = (UnidadeMedidaTempoContract) this.getObjectTempoByIndex(this.unidadeMedidaTempo);
            Double tempoConverted = objectTempo.getHora(this.horas);
            return "Resultado: "+Math.round(volumeConverted/tempoConverted)+" microgotas/min";
        }else{
            double hourAux = volumeConverted / gotas;
            return "Resultado: "+convertDecimalHoursToFormattedHour(hourAux);
        }
    }

    public Object getObjectTempoByIndex(int i){
        Object objeto = new Minuto();
        switch(i){
            case UnidadeMedida.MINUTE:
                objeto = new Minuto();
                break;
            case UnidadeMedida.HOUR:
                objeto = new Hora();
                break;
        }
        return objeto;
    }

    public Object getObjectVolumeByIndex(int i){
        Object objeto = new Mililitro();
        switch(i){
            case UnidadeMedida.MILILITRO:
                objeto = new Mililitro();
                break;
            case UnidadeMedida.GOTA:
                objeto = new Gota();
                break;
            case UnidadeMedida.LITRO:
                objeto = new Litro();
                break;
            case UnidadeMedida.MICROGOTA:
                objeto = new MicroGota();
                break;
        }
        return objeto;
    }

    private String convertDecimalHoursToFormattedHour(double hourAux){
        int hour = 0, minutes = 0, seconds = 0;
        hour = (int)hourAux;
        minutes = (int)((hourAux - hour) * 60);
        seconds = (int)((((hourAux - hour) * 60) - minutes) * 60);
        return new StringBuilder().append(hour)
                .append(" hora").append(hour > 1 || hour == 0  ? "s, ": ", ")
                .append(minutes)
                .append(" minuto").append(minutes > 1 || minutes == 0  ? "s, ":", ")
                .append(seconds)
                .append(" segundo").append(seconds > 1 || seconds == 0 ? "s." : ".")
                .toString();
    }


}
