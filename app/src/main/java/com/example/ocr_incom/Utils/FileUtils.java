package com.example.ocr_incom.Utils;

import android.content.Context;
import com.example.ocr_incom.Data.DataTemplate;
import com.example.ocr_incom.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.List;

public class FileUtils {

    private InputStreamReader streamReader;
    private File file;

    public FileUtils(Context context) {
        ReadFileTemplate(context);
    }


    /**
     * Save file
     *
     * @param dataList Save data
     */
    public void SaveFileTemplate(List<DataTemplate> dataList) {
        Gson gson = new Gson();

        String jsonString = gson.toJson(dataList);

        try {
            FileOutputStream outputStream = new FileOutputStream(file);

            outputStream.write(jsonString.getBytes());
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Read file
     */
    public void ReadFileTemplate(Context context) {
        streamReader = new InputStreamReader(context.getResources().openRawResource(R.raw.data));

    }


    /**
     * Get save json list Data
     */
    public List<DataTemplate> getDataReadFile( ) {


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

    public boolean isFile( ) {
        return file.isFile();
    }
}
