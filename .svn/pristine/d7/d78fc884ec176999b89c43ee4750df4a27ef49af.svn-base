package com.nursecalc.Components;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.nursecalc.Domain.Escala;
import com.nursecalc.R;

import java.util.List;

/**
 * Created by 0118424 on 03/12/2014.
 */
public class EscalaAdapter  extends ArrayAdapter<List<Escala>> {
    private final Context context;
    private List<Escala> escalas;

    public EscalaAdapter(Context context, List<Escala> escalas) {
        super(context, R.layout.linha_escala_list);
        this.context = context;
        this.escalas = escalas;
    }

    @Override
    public int getCount() {
        return this.escalas.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.linha_escala_list, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.escalaLabel);
        textView.setText(escalas.get(position).getTituloEscala());
        return rowView;
    }
}
