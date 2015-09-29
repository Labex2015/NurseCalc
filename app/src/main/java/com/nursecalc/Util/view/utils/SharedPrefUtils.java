package com.nursecalc.Util.view.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by 0126128 on 29/04/2015.
 */
public class SharedPrefUtils {

    public static final String MY_SHARED_PREF = "NurseShared";

    public static final String TOUR_TAG = "TOUR_TAG";


    public SharedPreferences returnMySharedPref(Context context) {
        return context.getSharedPreferences(MY_SHARED_PREF, Context.MODE_PRIVATE);
    }

    public void clear(Context context){
        SharedPreferences.Editor editor = returnMySharedPref(context).edit();
        editor.clear();
        editor.commit();
    }

    public Boolean alredyPlayedTour(Context context){
        SharedPreferences preferences = returnMySharedPref(context);
        return preferences.getBoolean(TOUR_TAG, false);
    }

    public void saveTourExecuted(Boolean status, Context context){
        SharedPreferences.Editor editor = returnMySharedPref(context).edit();
        editor.putBoolean(TOUR_TAG, status);
        editor.commit();
    }
}
