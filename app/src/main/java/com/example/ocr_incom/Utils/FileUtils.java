package com.example.ocr_incom.Utils;

import android.content.Context;

import com.example.ocr_incom.Data.DataTemplate;
import com.example.ocr_incom.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

public class FileUtils {

    private InputStreamReader streamReader;
    private File rootFile;
    private Context context;

    public FileUtils(Context context) {
        this.context = context;
    }



    /**
     * Read file base template
     *
     * **/
    public void readBaseTemplate(){
        streamReader = new InputStreamReader(context.getResources().openRawResource(R.raw.data));

    }

    /**
     * Loading file
     * */
    public List<DataTemplate> loadBaseTemplate(){

        Type _collectionType = new TypeToken<List<DataTemplate>>() {
        }.getType();

        Gson _json = new Gson();

        List<DataTemplate> _getData = _json.fromJson(streamReader, _collectionType);

        try {
            streamReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return _getData;
    }
}
