package com.example.ocr_incom.Utils;

import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ocr_incom.ButtonCard;
import com.example.ocr_incom.MainActivity;
import com.example.ocr_incom.R;

public class TouchUtils implements View.OnClickListener {

    private AppCompatActivity appCompatActivity;

    public TouchUtils(AppCompatActivity appCompatActivity) {
        this.appCompatActivity = appCompatActivity;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.f_files: {
                if (!MainActivity.isSwitch()) {
                    MainActivity.setSwitch(true);
                    ActionIntentUtils.performFileSearch(appCompatActivity);
                    break;
                }
            }
            case R.id.dataButton: {
                if (!MainActivity.isSwitch()) {
                    MainActivity.setSwitch(true);
                    ButtonCard card = (ButtonCard) view;
                    ActionIntentUtils.performDataPlank(MainActivity.getInstance(), card.getData(), MainActivity.getTemplate());
                    break;
                }
            }
            case R.id.f_findTemrplate: {
                if (!MainActivity.isSwitch()) {
                    MainActivity.setSwitch(true);
                    System.out.println("Enable search");
                    ActionIntentUtils.performSearchTemplate(MainActivity.getInstance(), MainActivity.getTemplate());
                }
            }
            case R.id.f_camera: {
                if (!MainActivity.isSwitch()) {
                    MainActivity.setSwitch(true);
                    System.out.println("Enable camera");
                    ActionIntentUtils.performCamera(MainActivity.getInstance());
                }
            }
        }
    }
}
