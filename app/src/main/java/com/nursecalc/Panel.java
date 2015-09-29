package com.nursecalc;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;


public class Panel extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel);

        FragmentManager fragmentManager = getFragmentManager();
        Fragment fragment =  new Principal();
        fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.panel, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_coments:
                Intent it = new Intent(this, Creditos.class);
                startActivity(it);

                return true;
        }

        return false;
    }

    public void calcularGotejamento(View v){
        startActivity(new Intent(this, CalcularGotejamento.class));
    }

    public void calcularDosagem(View v){
        startActivity(new Intent(this, CalcularDosagem.class));
    }

    public void showEscalas(View v){
        startActivity(new Intent(this, LeXml.class));
    }

}
