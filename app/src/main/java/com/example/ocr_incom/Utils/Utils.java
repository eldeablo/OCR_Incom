package com.example.ocr_incom.Utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.ocr_incom.MainActivity;
import com.example.ocr_incom.R;

public class Utils {

    public static void addPlankTable(String template, String count, String plank, ViewGroup v, Context context) {
        FrameLayout _row = (FrameLayout) View.inflate(context, R.layout.template_scheme, null);

        TextView _template = _row.findViewById(R.id.template);
        _template.setText(template);

        TextView _count = _row.findViewById(R.id.count);
        _count.setText(count);


        TextView _plank = _row.findViewById(R.id.plank);
        _plank.setText(plank);

        v.addView(_row);

    }
}
