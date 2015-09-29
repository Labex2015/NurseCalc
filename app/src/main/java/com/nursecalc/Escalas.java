package com.nursecalc;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Escalas extends Fragment {

    public static Escalas newInstance(int index) {
        Escalas escalas = new Escalas();
        Bundle args = new Bundle();
        args.putInt("index", index);
        escalas.setArguments(args);

        return escalas;
    }

    public int getShownIndex() {
        return getArguments().getInt("index", 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (container == null) {
            return null;
        }
        return inflater.inflate(R.layout.calculogotejamento, container, false);
    }
}
