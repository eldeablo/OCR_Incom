package com.example.ocr_incom.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.ocr_incom.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

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

    public static Uri getBitmapUri(Bitmap bitmap, String nameFile) {
        File _imageFileRead = new File(Environment.getExternalStorageDirectory().getAbsoluteFile(), "/INCOM/Pictures" + "/" + nameFile + ".jpg");
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(_imageFileRead);
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Uri.fromFile(_imageFileRead);
    }
}
