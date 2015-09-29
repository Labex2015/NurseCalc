package com.nursecalc;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nursecalc.Domain.Analise;
import com.nursecalc.Domain.Escala;
import com.nursecalc.Domain.Opcao;
import com.nursecalc.Domain.Perguntas;
import com.nursecalc.Util.DialogMaker;
import com.nursecalc.Util.actions.DialogActions;

import java.util.ArrayList;
import java.util.List;


public class EscalaActivity extends Activity implements DialogActions {
    private List<RadioGroup> radioGroups = new ArrayList<RadioGroup>();
    int resultado = 0;
    Analise objectAnalise;
    String estadoPaciente;
    String tituloEscala;

    AlertDialog alertDialog;
    private int resultadoSoma;
    private DialogMaker dialogMaker;
    private String nameTextDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escala);

        getActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        if(intent != null){
            Gson g = new Gson();
            Escala e = g.fromJson(intent.getStringExtra("escala"), Escala.class);

            final List<Analise> listAnalise = e.getAnalise();

            LinearLayout l = (LinearLayout) findViewById(R.id.linearEscalaActivity);

            TextView labelEscala = new TextView(getBaseContext());
            this.tituloEscala = e.getTituloEscala();
            labelEscala.setText(e.getTituloEscala());
            labelEscala.setPadding(10, 10, 0, 0);
            labelEscala.setTextSize(20);
            labelEscala.setTextColor(Color.parseColor("#000000"));

            l.addView(labelEscala);


            List<Perguntas> listPerguntas = e.getPerguntasEscala();

            if(listPerguntas.size() > 0){
                for(int i=0; i<listPerguntas.size(); i++){

                    TextView descricaoPergunta = new TextView(getBaseContext());
                    descricaoPergunta.setPadding(10, 10, 0, 0);
                    descricaoPergunta.setTextColor(Color.parseColor("#222222"));
                    descricaoPergunta.setText(listPerguntas.get(i).getTituloPergunta());
                    descricaoPergunta.setTextSize(20);
                    descricaoPergunta.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
                    labelEscala.setTextColor(Color.parseColor("#000000"));
                    //List<Opcao> listaOpcoes = listPerguntas.get(i).getOpcoes();


                    LinearLayout linearDescricao = new LinearLayout(getBaseContext());
                    linearDescricao.setOrientation(LinearLayout.HORIZONTAL);


                    ImageView imgV =  new ImageView(getBaseContext());
                    imgV.setImageDrawable(getResources().getDrawable(R.drawable.one_finger));

                    final Perguntas descricaoicone = listPerguntas.get(i);
                    imgV.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View view) {
                            showTipDialog(descricaoicone.getDescricao(),"Descrição da medida").show();
                        }
                    });

                    linearDescricao.addView(imgV);
                    descricaoPergunta.setTag(listPerguntas.get(i));
                    linearDescricao.addView(descricaoPergunta);

                    l.addView(linearDescricao);

                    if(listPerguntas.get(i).getOpcoes().size() > 0){
                        List<Opcao> o = listPerguntas.get(i).getOpcoes();
                        RadioGroup rGroup = new RadioGroup(getBaseContext());
                        rGroup.setPadding(10, 10, 0, 0);

                        for(int j=0; j<o.size(); j++){
                            RadioButton opcao = new RadioButton(getBaseContext());
                            opcao.setText(o.get(j).getTituloOpcao());
                            opcao.setTag(listPerguntas.get(i));
                            opcao.setPadding(70, 10, 10, 10);

                            opcao.setTextColor(Color.parseColor("#333333"));

                            rGroup.addView(opcao);
                        }

                        radioGroups.add(rGroup);
                        rGroup.setContentDescription(listPerguntas.get(i).getDescricao());
                        descricaoPergunta.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Perguntas p = (Perguntas) view.getTag();
                                showTipDialog(p.getDescricao(),"Descrição da medida").show();
                            }
                        });

                        l.addView(rGroup);
                    }
                }

                Button calcular = new Button(getBaseContext());
                calcular.setTextColor(Color.WHITE);
                calcular.setPadding(10, 20, 0, 20);
                calcular.setBackgroundResource(R.drawable.sl_button);

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                params.setMargins(16, 20, 16, 16);

                calcular.setLayoutParams(params);
                calcular.setText("Calcular");
                l.addView(calcular);
                final Escala escalaAux = e;

                calcular.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int resultadoSoma = 0;
                        boolean verificaCampos = true;

                        for(int z =0; z < radioGroups.size(); z++){
                            RadioButton rB = (RadioButton) findViewById(radioGroups.get(z).getCheckedRadioButtonId());
                            if(rB == null) {
                                verificaCampos = false;
                           }else {
                                Perguntas p = (Perguntas) rB.getTag();
                                List<Opcao> ops = p.getOpcoes();
                                for (int l = 0; l < ops.size(); l++) {
                                    Opcao opcao = ops.get(l);
                                    if (opcao.getTituloOpcao().equals(rB.getText())) {
                                        resultadoSoma += opcao.getPesoOpcao();
                                    }
                                }
                            }
                        }
                        if(escalaAux.getTipo().equals(Escala.DIALOG)) {
                            estadoPaciente = "Total de pontos obtidos = " + (((float) resultadoSoma) / 10);
                        } else if(escalaAux.getTipo().equals(Escala.NORMAL)) {
                            for (int k = 0; k < listAnalise.size(); k++) {
                                if (resultadoSoma >= listAnalise.get(k).getValorMin() && resultadoSoma <= listAnalise.get(k).getValorMax()) {
                                    Log.d("Resultado", listAnalise.get(k).getAnalise());
                                    estadoPaciente = listAnalise.get(k).getAnalise();
                                    break;
                                } else
                                    continue;
                            }
                        }
                            if(verificaCampos)
                                 exibeDialog(estadoPaciente, tituloEscala, resultadoSoma);
                            else
                                Toast.makeText(getBaseContext(),"Responda todas perguntas",Toast.LENGTH_LONG).show();
                        }
                });

            }
        }

    }

    public AlertDialog showTipDialog(String descricao, String title){
        AlertDialog.Builder builder = new AlertDialog.Builder(EscalaActivity.this);
        builder.setCancelable(true);
        View dialog = EscalaActivity.this.getLayoutInflater().inflate(R.layout.dialog_tip, null);
        TextView descricaoTextView = (TextView) dialog.findViewById(R.id.edtDescricaoMedida);
        descricaoTextView.setText(descricao);

        TextView titleTextView = (TextView) dialog.findViewById(R.id.edtDescricaoTitle);
        titleTextView.setText(title);

        builder.setView(dialog);
        alertDialog = builder.create();

        Button btnOkTip = (Button) dialog.findViewById(R.id.buttonOkTip);
        btnOkTip.setOnClickListener(closeDialogEventClick());
        
        alertDialog.setCanceledOnTouchOutside(true);
        return alertDialog;
    }

    private View.OnClickListener closeDialogEventClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(alertDialog.isShowing()){
                    alertDialog.dismiss();
                }
            }
        };
    }


    public void exibeDialog(String estadoPaciente, String tituloEscala, int resultadoSoma){
        dialogMaker = new DialogMaker(this, this);
        this.resultadoSoma = resultadoSoma;
        dialogMaker.createDialogWithEditText(tituloEscala, estadoPaciente ,"Total de pontos obtidos: " + resultadoSoma,"Nome do paciente",
                "Enviar por e-mail", "Fechar").show();
    }

    public void enviarPorEmail(String resultado, String tituloEscala, int resultadoValor, String nomePaciente){
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{""});
        i.putExtra(Intent.EXTRA_SUBJECT, "Escala: "+tituloEscala);
        i.putExtra(Intent.EXTRA_TEXT   , "Paciente:"+nomePaciente+", Resultado:"+resultado+".\nValor: "+resultadoValor);
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(EscalaActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSave() {
        String nomePacienteFromEditText = String.valueOf(dialogMaker.getEditText().getText());
        if(nomePacienteFromEditText.length() < 1){
            dialogMaker.getEditText().setError("Defina um nome para o paciente");
        }else{
            enviarPorEmail(estadoPaciente, tituloEscala, resultadoSoma, nomePacienteFromEditText);
            dialogMaker.closeDialog();
        }
    }

    @Override
    public void onCancel() { dialogMaker.closeDialog();}

    public class Feedback extends DialogFragment {
        private String msg;

        public void setMsg(String msg){
            this.msg = msg;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the Builder class for convenient dialog construction
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage(msg)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // FIRE ZE MISSILES!
                        }
                    });
            // Create the AlertDialog object and return it
            return builder.create();
        }
    }
}
