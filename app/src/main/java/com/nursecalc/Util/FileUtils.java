package com.nursecalc.Util;

import android.app.Activity;
import android.content.res.AssetManager;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/**
 * Created by 0126128 on 18/02/2015.
 */
public class FileUtils {


    public static final String JSON_PATH = "escala.json";

    public static String readFromAssets(String path, Activity activity){
        InputStream inputStream = null;
        AssetManager manager = activity.getAssets();
        try {
            inputStream = manager.open(path);
            return loadTextFile(inputStream);
        } catch (IOException e) {
            return "";
        } finally {
            if (inputStream != null)
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    private static String loadTextFile(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        byte[] bytes = new byte[4096];
        int len = 0;
        while ((len = inputStream.read(bytes)) > 0)
            byteStream.write(bytes, 0, len);
        return new String(byteStream.toByteArray(), "UTF8");
    }

}
