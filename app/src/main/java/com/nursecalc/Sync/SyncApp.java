package com.nursecalc.Sync;

import android.content.Context;

import com.nursecalc.SQLite.MyDbHandler;
import com.nursecalc.Util.Folder;


/*
 * Created by Lucas on 24/11/2014.
 *
 * Classe criada para gerenciar / sincronizar atualizações
 * no contexto de aplicação
 *
 */
public class SyncApp {

    private Context context;
    private MyDbHandler dbHandler;

    public SyncApp(Context context){
        this.context = context;
    }

    public void seeState()
    {
        this.firstUpdate();
     }

    private void firstUpdate(){
        Folder folder = new Folder("nursecalc");
        folder.criarFolderMethod();
    }



}
