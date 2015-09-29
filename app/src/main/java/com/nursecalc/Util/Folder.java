package com.nursecalc.Util;

import android.os.Environment;
import android.util.Log;

import java.io.File;

/**
 * Created by 0118424 on 24/11/2014.
 */
public class Folder {
    final String PATH = Environment.getExternalStorageDirectory() + "/tchevi/";

    private static final String FILENAME = "data.txt";
    private static String DNAME = "myfiles";

    public Folder(String dir){
        DNAME = dir;
    }


    public void criarFolder(){
        if(!(new File(PATH)).exists()){
            Log.d("Arquivo não existe", Environment.getExternalStorageDirectory()+"");
            File dir =  new File(PATH);
            Boolean result = dir.mkdir();
            Log.d("Resultado Criação", result+"");
            if (!dir.canWrite()) {
               Log.d("Não foi possível escrever no arquivo", false+"");
            }
       }


        Log.d("Entrei aqui", Environment.getExternalStorageDirectory()+"");
    }

    public void criarFolderMethod(){
        File rootPath = new File(Environment.getExternalStorageDirectory(), DNAME);
        if(!rootPath.exists()) {
            Boolean statusCreatingFile = rootPath.mkdirs();
            Log.d("Creating file", statusCreatingFile+"");
        }
    }


}
