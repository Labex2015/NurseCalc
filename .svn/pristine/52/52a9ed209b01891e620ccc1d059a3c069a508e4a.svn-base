package com.nursecalc;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nursecalc.AsyncPackage.AsyncJSON;
import com.nursecalc.Components.EscalaAdapter;
import com.nursecalc.Domain.Analise;
import com.nursecalc.Domain.Escala;
import com.nursecalc.Domain.OnTaskFinished;
import com.nursecalc.Domain.Opcao;
import com.nursecalc.Domain.Perguntas;
import com.nursecalc.Util.FileUtils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class LeXml extends Activity implements OnTaskFinished{

    private Escala escala = new Escala();
    List<Escala> escalaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_le_xml);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        new AsyncJSON(this, this).execute(FileUtils.JSON_PATH);

    }




    @Override
    public void onTaskCompleted(JSONArray data) {


        escalaList = new ArrayList<Escala>();


        if(data != null) {
            if (data.size() > 0) {
                for (int i = 0; i < data.size(); i++) {

                    escala = new Escala();
                    JSONObject itemObject = (JSONObject)data.get(i);
                    JSONArray arrayAnalise = (JSONArray) itemObject.get("analise");
                    List<Analise> listAnalise = new ArrayList<Analise>();
                    JSONObject objetoAnalise = (JSONObject) arrayAnalise.get(0);
                    JSONArray arrayPerguntas = (JSONArray) itemObject.get("perguntas");

                    List<Perguntas> perguntasList = new ArrayList<Perguntas>();
                    for (int j = 0; j < arrayPerguntas.size(); j++) {

                        Perguntas p = new Perguntas();
                        JSONObject objectPergunta = (JSONObject) arrayPerguntas.get(j);
                        p.setTituloPergunta((String) objectPergunta.get("pergunta"));
                        p.setDescricao((String) objectPergunta.get("descricao"));
                        List<Opcao> opcaoList = new ArrayList<Opcao>();
                        JSONArray opcoesPergunta = (JSONArray) objectPergunta.get("opcoes");
                        for (int k = 0; k < opcoesPergunta.size(); k++) {
                            JSONObject opcaoObjeto = (JSONObject) opcoesPergunta.get(k);
                            Opcao opcao = new Opcao();

                            opcao.setTituloOpcao((String) opcaoObjeto.get("titulo"));

                            Long peso = (Long) opcaoObjeto.get("peso");
                         try {
                             opcao.setPesoOpcao(peso.intValue());
                             opcaoList.add(opcao);

                             p.setOpcoes(opcaoList);
                         }catch (NullPointerException e){
                             Log.v("testes","teste");
                         }
                        }

                        perguntasList.add(p);
                        escala.setPerguntasEscala(perguntasList);
                    }

                    for (int j = 0; j < arrayAnalise.size(); j++) {
                        Analise analise = new Analise();
                        JSONObject objectAnalise = (JSONObject) arrayAnalise.get(j);
                        Long idAnalise = (Long) objectAnalise.get("id");
                        analise.setId(idAnalise.intValue());
                        analise.setAnalise((String) objectAnalise.get("analise"));

                        Long valorMin = (Long) objectAnalise.get("valorMin");
                        analise.setValorMin(valorMin.intValue());
                        Long valorMax = (Long) objectAnalise.get("valorMax");
                        analise.setValorMax(valorMax.intValue());

                        listAnalise.add(analise);
                    }
                    escala.setAnalise(listAnalise);
                    escala.setDescricaoEscala(itemObject.get("descricao").toString());
                    escala.setTituloEscala(itemObject.get("nome").toString());
                    escala.setTipo(itemObject.get("tipo").toString().charAt(0));
                    this.escalaList.add(escala);

                }
                this.inflaList();
            }

        }

    }
    public void inflaList(){
        if(this.escalaList != null){
            ListView lv = (ListView) findViewById(R.id.listViewEscalas);
            EscalaAdapter ea = new EscalaAdapter(getBaseContext(), this.escalaList);
            lv.setAdapter(ea);

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    Escala e = escalaList.get(i);
                    Gson gson = new Gson();
                    String objeto = gson.toJson(e);

                    if(e.getTipo() == Escala.INFORMATIVO){
                        Intent intent = new Intent(getBaseContext(), Formula.class);
                        intent.putExtra("tipo", e.getTituloEscala());
                        startActivity(intent);
                    }else {
                        Intent intentTo = new Intent(view.getContext(), EscalaActivity.class);
                        intentTo.putExtra("escala", objeto);
                        startActivityForResult(intentTo, 1);
                    }
                }
            });

        }else{
            Log.d("Escalas", this.escalaList.size()+"");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
