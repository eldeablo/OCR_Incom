package com.example.ocr_incom.Utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ocr_incom.Activity.CameraMain;
import com.example.ocr_incom.Activity.CardPlankMain;
import com.example.ocr_incom.Activity.SearchMain;
import com.example.ocr_incom.Data.Data;
import com.example.ocr_incom.Data.DataTemplate;
import com.example.ocr_incom.MainActivity;

import java.io.Serializable;
import java.util.List;

public class ActionIntentUtils {

    /**
     * Intent file Browser
     *
     * @param frame app class open browser file
     */
    public static void performFileSearch(AppCompatActivity frame) {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");

        frame.startActivityForResult(intent, MainActivity.READ_REQUEST_CODE);

    }

    public static void performFileCamera(AppCompatActivity frame, Uri uriImage) {
        Intent intent = new Intent(frame, MainActivity.class);
        intent.putExtra("image", uriImage);
        frame.setResult(Activity.RESULT_OK, intent);
        frame.finish();
    }

    /**
     * Intent open class plank
     *
     * @param mainActivity main class
     * @param data         data class
     */
    public static void performDataPlank(MainActivity mainActivity, Data data, List<DataTemplate> dataTemplate) {
        Intent intent = new Intent(mainActivity, CardPlankMain.class);
        intent.putExtra("data", data);
        intent.putExtra("dataTemp", (Serializable) dataTemplate);

        mainActivity.startActivity(intent);
    }

    /**
     * Intent open class search template
     *
     * @param mainActivity main class
     * @param dataTemplate get data list template
     **/
    public static void performSearchTemplate(MainActivity mainActivity, List<DataTemplate> dataTemplate) {
        Intent intent = new Intent(mainActivity, SearchMain.class);
        intent.putExtra("dataTemp", (Serializable) dataTemplate);

        mainActivity.startActivity(intent);
    }

    /**
     * Intent open class search template
     *
     * @param mainActivity main class
     * @param dataTemplate get data list template
     **/
    public static void performCamera(AppCompatActivity frame) {
        Intent intent = new Intent(frame, CameraMain.class);

        frame.startActivityForResult(intent,MainActivity.READ_REQUEST_CAMERA_CODE);
    }
}
