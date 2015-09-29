package com.nursecalc.Components;

import android.content.Context;
import android.widget.RadioButton;

/**
 * Created by 0118424 on 04/12/2014.
 */
public class EscalaRadio extends RadioButton {

    private int id;
    private String descricao;
    private int     peso;
    private int     idPergunta;

    public EscalaRadio(Context context) {
        super(context);
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }
}
