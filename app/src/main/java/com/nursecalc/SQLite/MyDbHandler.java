package com.nursecalc.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;

/**
 * Created by 0118424 on 25/11/2014.
 */
public class MyDbHandler extends SQLiteOpenHelper {



    private static final int DATABASE_VERSION  = 1;
    private static final String DATABASE_NAME  = "produtos.db";
    private static final String TABLE_PRODUCTS = "produto";

    public static final String COLUMN_ID       = "id";
    public static final String COLUMN_NAME     = "name";
    public static final String COLUMN_QUANTITY = "quantity";

    public MyDbHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
       super(context, name, null, 1);

    }
    public void checkExistsDatabase(Context context){
        File database= context.getDatabasePath("produtos.db");
        if (!database.exists()) {
           Log.i("Database", "Not Found");
        } else {
            Log.i("Database", "Found");
        }
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_PRODUCTS_TABLE = "CREATE TABLE " +
                TABLE_PRODUCTS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_NAME
                + " TEXT," + COLUMN_QUANTITY + " INTEGER" + ")";

         db.execSQL(CREATE_PRODUCTS_TABLE);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i2) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        onCreate(db);
    }






}
