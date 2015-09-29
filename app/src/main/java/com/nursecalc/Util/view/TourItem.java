package com.nursecalc.Util.view;

import android.app.Activity;
import android.graphics.Point;
import android.os.Build;
import android.view.View;

import com.github.amlcurran.showcaseview.OnShowcaseEventListener;
import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.Target;
import com.github.amlcurran.showcaseview.targets.ViewTarget;
import com.nursecalc.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 0126128 on 10/03/2015.
 */
public class TourItem {

    private String title;
    private String text;
    private int component;
    private TourItem nextItem;

    public TourItem() {}

    public TourItem(String title, String text, int component) {
        this.title = title;
        this.text = text;
        this.component = component;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getComponent() {
        return component;
    }

    public void setComponent(int component) {
        this.component = component;
    }

    public TourItem getNextItem() {
        return nextItem;
    }

    public void setNextItem(TourItem nextItem) {
        this.nextItem = nextItem;
    }

    public static TourItem loadItens(){

        List<TourItem> items = new ArrayList<TourItem>();
        items.add(new TourItem("Escalas","Exibe cada escala em forma de um questionário e " +
                "retornam o resultado após todas as questões serem respondidas.", R.id.buttonEscalas));
        items.add(new TourItem("Dosagem","Auxilia no cálculo de dosagens.", R.id.fakeDosagemButton));
        items.add(new TourItem("Gotejamento","Tela para auxílio no cálculo de " +
                "gotejamento e também o tempo para uma detarminada taxa.", R.id.buttonCalcularGotejamento));
        for(int i = items.size() - 1; i > 0; ){
            if(i-1 >= 0)
                items.get(i).setNextItem(items.get(--i));
            else
                i--;
        }
        return items.get(items.size()-1);
    }

    public static TourItem loadItensGotejamento(){

        List<TourItem> items = new ArrayList<TourItem>();
        items.add(new TourItem("","", R.id.volume));
        items.add(new TourItem("","", R.id.tempo));
        items.add(new TourItem("","", R.id.buttonCalcularGotejamentoTempo));
        items.add(new TourItem("","", R.id.buttonMicroGota));
        items.add(new TourItem("","", R.id.gotejamentoRadioButton));
        for(int i = items.size() - 1; i > 0; ){
            if(i-1 >= 0)
                items.get(i).setNextItem(items.get(--i));
            else
                i--;
        }
        return items.get(items.size()-1);
    }

    public static void createTour(final TourItem tourItem, final Activity activity, final View view) {
        ShowcaseView showcaseView = new ShowcaseView.Builder(activity, true)
                .setTarget(new Target() {
                    @Override
                    public Point getPoint() {
                        View item = view.findViewById(tourItem.getComponent());
                        return item != null ? new ViewTarget(item).getPoint() : null;
                    }
                })
                .setContentTitle(tourItem.getTitle())
                .setContentText(tourItem.getText())
                .setStyle(R.style.CustomShowcaseTheme2)
                .build();
        showcaseView.setOnShowcaseEventListener(new OnShowcaseEventListener() {
            @Override
            public void onShowcaseViewHide(ShowcaseView showcaseView) {}

            @Override
            public void onShowcaseViewDidHide(ShowcaseView showcaseView) {
                if (tourItem.getNextItem() != null) {
                    int currentapiVersion = android.os.Build.VERSION.SDK_INT;
                    if (currentapiVersion > Build.VERSION_CODES.HONEYCOMB_MR1) {
                        createTour(tourItem.getNextItem(), activity, view);
                    }
                }
                showcaseView = null;
            }

            @Override
            public void onShowcaseViewShow(ShowcaseView showcaseView) {}
        });
    }
}
