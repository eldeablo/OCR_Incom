package com.example.ocr_incom.Utils;

import android.view.KeyEvent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ocr_incom.Activity.SearchMain;
import com.example.ocr_incom.ButtonCard;
import com.example.ocr_incom.MainActivity;
import com.example.ocr_incom.R;

public class TouchUtils implements View.OnClickListener, View.OnKeyListener {

    private AppCompatActivity appCompatActivity;

    public TouchUtils(AppCompatActivity appCompatActivity) {
        this.appCompatActivity = appCompatActivity;
    }

    /**
     * Touch click event
     */
    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.f_files) {
            ActionIntentUtils.performFileSearch(appCompatActivity);
        } else if (id == R.id.f_findTemrplate) {
            ActionIntentUtils.performSearchTemplate(MainActivity.getInstance(), MainActivity.getTemplate());
        } else if (id == R.id.f_camera) {
            ActionIntentUtils.performCamera(appCompatActivity);
        } else if (id == R.id.dataButton) {
            ButtonCard _card = (ButtonCard) view;
            ActionIntentUtils.performDataPlank(MainActivity.getInstance(), _card.getData(), MainActivity.getTemplate());
        }
    }

    /**
     * Key click event
     */
    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        int id = view.getId();

        // find one template
        if (id == R.id.findOneTemplate) {
            if (keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                SearchMain _searchMain = (SearchMain) appCompatActivity;
                if (!_searchMain.getFindOneTemplate().getText().toString().isEmpty()) {
                    _searchMain.addOneTemplate();
                } else {
                    _searchMain.addAllTemplateBase();
                }
            }

        }

        return false;
    }
}
