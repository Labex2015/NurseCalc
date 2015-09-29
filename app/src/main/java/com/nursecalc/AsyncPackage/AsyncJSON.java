package com.nursecalc.AsyncPackage;


import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.nursecalc.Domain.OnTaskFinished;
import com.nursecalc.Util.FileUtils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class AsyncJSON extends AsyncTask<String, Integer, Long> {

    private OnTaskFinished listener;
    private JSONArray jArray;
    private Activity activity;


    public AsyncJSON(OnTaskFinished task, Activity activity){
        listener = task;
        this.activity = activity;
    }

    protected Long doInBackground(String... urls) {

        JSONParser jsonParser = new JSONParser();
        try {
            JSONArray jsonObject = (JSONArray) jsonParser.parse(FileUtils.readFromAssets(urls[0], activity));
            jArray = jsonObject;
            JSONObject object = (JSONObject) jsonObject.get(0);
            Log.d("JsonObject", object.get("perguntas") + "");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0L;
    }

    protected void onProgressUpdate(Integer... progress) {
        //
    }

    protected void onPostExecute(Long result) {
        listener.onTaskCompleted(jArray);
    }



}