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

        if (id == R.id.f_files) {
            ActionIntentUtils.performFileSearch(appCompatActivity);
        } else if (id == R.id.f_findTemrplate) {
            ActionIntentUtils.performSearchTemplate(MainActivity.getInstance(), MainActivity.getTemplate());
        } else if (id == R.id.f_camera) {
            ActionIntentUtils.performCamera(MainActivity.getInstance());
        } else if (id == R.id.dataButton) {
            ButtonCard card = (ButtonCard) view;
            ActionIntentUtils.performDataPlank(MainActivity.getInstance(), card.getData(), MainActivity.getTemplate());
        }
    }
}
