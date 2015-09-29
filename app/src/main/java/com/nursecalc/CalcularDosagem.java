package com.nursecalc;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.nursecalc.Domain.Dosagem;
import com.nursecalc.Domain.FormulasContract;



public class CalcularDosagem extends Activity implements FormulasContract {

    EditText amountMedicineField;
    EditText amountSolventField;
    EditText amountToCalc;
    Button btnCalc;
    TextView txtResultDinamic;
    TextView txtFeedback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcular_dosagem);

        getActionBar().setDisplayHomeAsUpEnabled(true);


        this.amountMedicineField= (EditText) findViewById(R.id.edtQuantidadeRemedio);
        this.amountSolventField = (EditText) findViewById(R.id.edtQuantidadeSolvente);
        this.amountToCalc       = (EditText) findViewById(R.id.edtQuantidadeASerCalculada);
        this.txtResultDinamic   = (TextView) findViewById(R.id.txtResultadoDinamic);
        this.txtFeedback        = (TextView) findViewById(R.id.txtFeedback);

        btnCalc                 = (Button) findViewById(R.id.calcularDosagem);
    }

    public void calcularDosagem(View v){

        if(this.amountMedicineField.getText().length() < 1 || this.amountSolventField.getText().length() < 1
                || this.amountToCalc.getText().length() < 1){
                this.txtFeedback.setTextColor(Color.parseColor("#ff0000"));
                this.txtFeedback.setText(getResources().getString(R.string.required_fields));
        }else{
            double medicineValue = Double.parseDouble(String.valueOf(this.amountMedicineField.getText()));
            double solventValue =   Double.parseDouble(String.valueOf(this.amountSolventField.getText()));
            double amountValue = Double.parseDouble(String.valueOf(this.amountToCalc.getText()));
            Dosagem dosage = new Dosagem(medicineValue, solventValue, amountValue);
            double result =  (double)Math.round(dosage.resultado() * 100000) / 100000;
            String stringDouble= Double.toString(result);
            this.txtFeedback.setText("");
            this.txtResultDinamic.setText(stringDouble + "ml");
        }
    }
     @Override
    public void verFormula(View v) {
        Intent intent = new Intent(this, Formula.class);
        intent.putExtra(Formula.EXTRA_TYPE, Formula.TYPE_DOSAGE);
        startActivity(intent);
    }
}
