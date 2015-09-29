package com.nursecalc.Util;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by 0118424 on 24/11/2014.
 */
public class LogTcheVi {

    private String folder = "tchevi";
    private String fileTxt = "log.txt";

    public LogTcheVi(){
        Folder folder = new Folder(this.folder);
        folder.criarFolder();
        this.criaLog();
    }
    public void criaLog(){
        File file = new File(Environment.getExternalStorageDirectory()+"/"+this.folder+"/", fileTxt);
        try {
            FileOutputStream f = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            Log.d("Caiu no catch", e.getMessage() + "");
            e.printStackTrace();
        }
    }
    public void escreverLog(){

    }
    public void lerLog(){

    }


}
