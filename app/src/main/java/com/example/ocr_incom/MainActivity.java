package com.example.ocr_incom;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ocr_incom.Data.DataTemplate;
import com.example.ocr_incom.Utils.FileUtils;
import com.example.ocr_incom.Utils.TouchUtils;
import com.example.ocr_incom.Utils.Utils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_CAMERA = 21;
    public static final int READ_REQUEST_CODE = 42;

    private static boolean isSwitch = false;

    @SuppressLint("StaticFieldLeak")
    private static MainActivity instance;
    private static List<DataTemplate> readData = new ArrayList<>();

    private LinearLayout listDataPlank;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        CheckPermission.checkIsOpenCamera(this);

        listDataPlank = findViewById(R.id.dataList);

        FileUtils fileUtils = new FileUtils(this);
        readData = fileUtils.getDataReadFile();

        FloatingActionButton cameraF = findViewById(R.id.f_camera);
        cameraF.setOnClickListener(new TouchUtils(this));

        FloatingActionButton browserFileF = findViewById(R.id.f_files);
        browserFileF.setOnClickListener(new TouchUtils(this));

        FloatingActionButton searchF = findViewById(R.id.f_findTemrplate);
        searchF.setOnClickListener(new TouchUtils(this));

        instance = this;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {

        if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Uri uri;
            if (resultData != null) {
                uri = resultData.getData();

                ButtonCard card = new ButtonCard(this, readData);
                card.getData().getDataCard().setImageSource(Objects.requireNonNull(uri).toString());

                listDataPlank.addView(card, 0);

            }
        }
    }

    public static MainActivity getInstance( ) {
        return instance;
    }

    public static List<DataTemplate> getTemplate(){
        return readData;
    }

    public static boolean isSwitch( ) {
        return isSwitch;
    }

    public static void setSwitch(boolean aSwitch) {
        isSwitch = aSwitch;
    }
}
