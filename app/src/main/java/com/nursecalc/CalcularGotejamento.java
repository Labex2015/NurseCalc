package com.nursecalc;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.nursecalc.Domain.FormulasContract;
import com.nursecalc.Domain.Gotejamento;
import com.nursecalc.Domain.Unidades.Gota;
import com.nursecalc.Domain.Unidades.Hora;
import com.nursecalc.Domain.Unidades.Litro;
import com.nursecalc.Domain.Unidades.MicroGota;
import com.nursecalc.Domain.Unidades.Mililitro;
import com.nursecalc.Domain.Unidades.Minuto;
import com.nursecalc.Domain.Unidades.UnidadeMedida;
import com.nursecalc.Util.view.TourItem;
import com.nursecalc.Util.view.utils.SharedPrefUtils;

import java.text.DecimalFormat;


public class CalcularGotejamento extends Activity implements FormulasContract {


    private double vDouble;
    private double tDouble;
    private Gotejamento g;
    private Dialog dialog;

    private RadioButton gotejamentoRadioButton, horasRadioButton;
    private EditText volume, tempo;
    private TextView label, resultado, labelTempoGotejamento;
    private Button buttonGotejamentoTempo, buttonMicroGota;

    public int unidadeMedidaVolume = UnidadeMedida.MILILITRO;
    public int unidadeMedidaTempo = UnidadeMedida.HOUR;
    public String labelUnidadeTempo = "min";
    public String labelUnidadeVolume = "ml";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcular_gotejamento);

        getActionBar().setDisplayHomeAsUpEnabled(true);

        this.volume                 = (EditText) findViewById(R.id.volume);
        this.tempo                  = (EditText) findViewById(R.id.tempo);
        this.label                  = (TextView) findViewById(R.id.label);
        this.resultado              = (TextView) findViewById(R.id.resultado);
        this.labelTempoGotejamento  = (TextView) findViewById(R.id.labelUnidadeTempoLayout);
        this.buttonGotejamentoTempo = (Button) findViewById(R.id.buttonCalcularGotejamentoTempo);
        this.buttonMicroGota        = (Button) findViewById(R.id.buttonMicroGota);
        this.gotejamentoRadioButton = (RadioButton) findViewById(R.id.gotejamentoRadioButton);
        this.horasRadioButton       = (RadioButton) findViewById(R.id.horasRadioButton);

        this.gotejamentoRadioButton.setOnClickListener(changeLayoutEventClick());
        this.horasRadioButton.setOnClickListener(changeLayoutEventClick());
        /*SharedPrefUtils prefUtils = new SharedPrefUtils();
        if (!prefUtils.alredyPlayedTour(this)) {
            TourItem.createTour(TourItem.loadItensGotejamento(), this, findViewById(R.id.view_fragment_calcular_gotejamento));
            prefUtils.saveTourExecuted(true, this);
        }*///TODO: Criar novo style para esta tela.


    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        return super.onCreateView(parent, name, context, attrs);
    }

    private View.OnClickListener changeLayoutEventClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(gotejamentoRadioButton.isChecked())
                    changeLabelsAndTitles(getResources().getString(R.string.button_calcular_gotejamento),
                            getResources().getString(R.string.horas_text_view));
                else
                    changeLabelsAndTitles(getResources().getString(R.string.button_calcular_tempo),
                            getResources().getString(R.string.gotejamento_m_text_view));

                tempo.setText("");
                resultado.setText("Resultado:");
            }
        };
    }

    private void changeLabelsAndTitles(String textButton, String textTextView){
        this.buttonGotejamentoTempo.setText(textButton);
        this.labelTempoGotejamento.setText(textTextView);
    }

    public void calcular(View view){
        if( volume.getText().toString().length() < 1 || tempo.getText().toString().length() < 1){

            label.setTextColor(getResources().getColor(R.color.red));
            label.setText("Informe os valores abaixo");
        }else{
            vDouble = Double.parseDouble(volume.getText().toString());
            tDouble = Double.parseDouble(tempo.getText().toString());

            if(gotejamentoRadioButton.isChecked())
                g = new Gotejamento(vDouble, (int) tDouble,  0, unidadeMedidaVolume, unidadeMedidaTempo);
            else
                g = new Gotejamento(vDouble, 0,(int) tDouble,  unidadeMedidaVolume, UnidadeMedida.HOUR);

            resultado.setText(g.getGotejamento());
        }


    }
    @Override
    public void verFormula(View v) {
            Intent intent = new Intent(this, Formula.class);
            Gotejamento gotejamento = new Gotejamento(getBaseContext());
            intent.putExtra(Formula.EXTRA_TYPE, Formula.TYPE_DRIP);
            startActivity(intent);
     }



    public void calcularMicroGota(View v){
        double vol;
        if( volume.getText().toString().length() < 1 || tempo.getText().toString().length() < 1){
            label.setTextColor(getResources().getColor(R.color.red));
            label.setText(getResources().getString(R.string.required_fields));
        }else{
            vol = Double.parseDouble(volume.getText().toString());
            tDouble = Double.parseDouble(tempo.getText().toString());

            if(gotejamentoRadioButton.isChecked())
                g = new Gotejamento(vol, (int) tDouble,  0, unidadeMedidaVolume, unidadeMedidaTempo);
            else
                g = new Gotejamento(vol, 0,(int) tDouble,  unidadeMedidaVolume, UnidadeMedida.HOUR);
            resultado.setText(g.getMicroGotejamento(gotejamentoRadioButton.isChecked() ? Gotejamento.TEMPO_MICROGOTEJAMENTO : Gotejamento.MICROGOTEJAMENTO_TEMPO));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.calcular_gotejamento, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_definir_medida) {
           exibeDialog();
        }
        return super.onOptionsItemSelected(item);


    }

    public void exibeDialog(){
        UnidadeMedida unidadeMedida = new UnidadeMedida();
        dialog = new Dialog(this);

        LinearLayout v = new LinearLayout(getBaseContext());
        v.setOrientation(LinearLayout.VERTICAL);
        v.setBackgroundColor(Color.parseColor("#c1c1c1"));
        v.setPadding(10, 10, 10, 10);

        TextView labelVolumetrico = (TextView) new TextView(getBaseContext());
        final TextView labelTemporal = (TextView) new TextView(getBaseContext());

        Button button = new Button(getBaseContext());
        button.setText("Usar");
        button.setBackgroundResource(R.drawable.sl_button);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 20, 0, 0);
        button.setLayoutParams(params);

        labelVolumetrico.setText("Medidas Volumétricas");
        labelTemporal.setText("Medidas de Tempo");

        final Spinner sVolumetrico = new Spinner(getBaseContext());
        final Spinner sTemporal = new Spinner(getBaseContext());

        sTemporal.setTag(new UnidadeMedida());

        sVolumetrico.setBackgroundColor(Color.parseColor("#c1c1c1"));
        sTemporal.setBackgroundColor(Color.parseColor("#c1c1c1"));

        sVolumetrico.setLayoutParams(new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        sTemporal.setLayoutParams(new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item, unidadeMedida.getUnidadesDeMedidaVolumetricas());

        dataAdapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);


        ArrayAdapter<String> temporalAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item, unidadeMedida.getUnidadesDeMedidaTemporal());

        temporalAdapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String unidadeMedidaTemporal = sTemporal.getSelectedItem().toString();
                String unidadeVolumetrica = sVolumetrico.getSelectedItem().toString();

                if (unidadeMedidaTemporal == Hora.LABEL) {
                    unidadeMedidaTempo = UnidadeMedida.HOUR;
                    labelUnidadeTempo = Hora.LABELABREVIACAO;

                } else if (unidadeMedidaTemporal == Minuto.LABEL) {
                    unidadeMedidaTempo = UnidadeMedida.MINUTE;
                    labelUnidadeTempo = Minuto.LABELABREVIACAO;
                }
                if (unidadeVolumetrica == Mililitro.LABEL) {
                    unidadeMedidaVolume = UnidadeMedida.MILILITRO;
                    labelUnidadeVolume = Mililitro.LABELABREVIACAO;

                } else if (unidadeVolumetrica == MicroGota.LABEL) {
                    unidadeMedidaVolume = UnidadeMedida.MICROGOTA;
                    labelUnidadeVolume = MicroGota.LABEL;
                } else if (unidadeVolumetrica == Litro.LABEL) {
                    unidadeMedidaVolume = UnidadeMedida.LITRO;
                    labelUnidadeVolume = Litro.LABELABREVIACAO;
                } else if (unidadeVolumetrica == Gota.LABEL) {
                    unidadeMedidaVolume = UnidadeMedida.GOTA;
                    labelUnidadeVolume = Gota.LABELABREVIACAO;
                }

                TextView tTempo = (TextView) findViewById(R.id.labelUnidadeTempoLayout);
                TextView tVolume = (TextView) findViewById(R.id.labelUnidadeVolumeLayout);

                gotejamentoRadioButton.setChecked(true);
                gotejamentoRadioButton.performClick();

                tTempo.setText("Unidade de tempo (" + labelUnidadeTempo + ")");
                tVolume.setText("Unidade de volume (" + labelUnidadeVolume + ")");

                dialog.dismiss();
            }
        });

        sVolumetrico.setAdapter(dataAdapter);
        sTemporal.setAdapter(temporalAdapter);

        v.addView(labelVolumetrico);
        v.addView(sVolumetrico);
        v.addView(labelTemporal);
        v.addView(sTemporal);
        v.addView(button);

        dialog.setContentView(v);
        dialog.setTitle("Defina escala de medida:");
        dialog.show();
    }






}
