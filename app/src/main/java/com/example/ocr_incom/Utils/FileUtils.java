package com.example.ocr_incom.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import com.example.ocr_incom.CheckPermission;
import com.example.ocr_incom.Data.DataTemplate;
import com.example.ocr_incom.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

public class FileUtils {

    private InputStreamReader streamReader;
    private String nameFile;
    private Context context;

    public FileUtils(Context context) {
        this.context = context;
    }


    /**
     * Save image file is camera
     *
     * @param name      name file save
     * @param imageByte image byte
     */
    public void saveImageFile(String name, byte[] imageByte) {
        nameFile = name;
        File _imageFile = new File(Environment.getExternalStorageDirectory().getAbsoluteFile(), "/INCOM/Pictures");

        if (CheckPermission.isExternalStorageWritable()) {
            if (!_imageFile.exists()) {
                _imageFile.mkdirs();
            }

            try {
                FileOutputStream _outputFile = new FileOutputStream(_imageFile + "/" + name + ".jpg");
                _outputFile.write(imageByte);
                _outputFile.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Bitmap readImageFile() {
        File _imageFileRead = new File(Environment.getExternalStorageDirectory().getAbsoluteFile(), "/INCOM/Pictures" + "/" + nameFile + ".jpg");

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;

        return BitmapFactory.decodeFile(_imageFileRead.toString());

    }


    /**
     * Read file base template
     **/
    public void readBaseTemplate() {
        streamReader = new InputStreamReader(context.getResources().openRawResource(R.raw.data));
    }

    /**
     * Loading file base template
     */
    public List<DataTemplate> loadBaseTemplate() {

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

    public String getNameFile(){
        return nameFile;
    }
}
