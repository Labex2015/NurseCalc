package com.nursecalc;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class Formula extends Activity {

    public static String EXTRA_TYPE = "tipo";
    public static String TYPE_DRIP = "Fórmula do gotejamento";
    public static String TYPE_RASS = "Richmond Agitation Sedation Scale RASS";
    public static String TYPE_DOSAGE = "Fórmula da dosagem";
    public static String TYPE_RAMSAY = "Escala de Ramsay";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.formula);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();

        String image = intent.getStringExtra(EXTRA_TYPE);

        WebView webImage = (WebView) findViewById(R.id.webImage);

        String img = null;
        if(image.equals(TYPE_DOSAGE)){
            img = "exemplos_dosagem_x.png";
            getActionBar().setTitle("Fórmula da dosagem");
        }else if(image.equals(TYPE_DRIP)){
            img = "gotejamento_x.png";
            getActionBar().setTitle("Fórmula do gotejamento");
        }else if(image.equals(TYPE_RASS)){
            img = "rass.png";
            getActionBar().setTitle("Escala de RASS");
        }else if(image.equals(TYPE_RAMSAY)){
            img = "ramsay.png";
            getActionBar().setTitle("Escala de RAMSAY");
        }

        webImage.loadUrl("file:///android_asset/" + img);
        webImage.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);
        webImage.getSettings().setBuiltInZoomControls(true);
        webImage.setInitialScale(100);
        webImage.getSettings().setUseWideViewPort(true);
    }
}
