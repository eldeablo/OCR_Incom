package com.example.ocr_incom.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.ocr_incom.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Objects;

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


    public static Uri getUriSaveImage(File file) {
        return Uri.fromFile(file);
    }

    public static Bitmap getBitmap(Uri uriCropFile) {
        try {
            File crop = new File(Objects.requireNonNull(uriCropFile.getPath()));
            InputStream inputStreamCrop = new FileInputStream(crop);
            return BitmapFactory.decodeStream(inputStreamCrop);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }


    public static byte[] bitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, bos);
        return bos.toByteArray();
    }
}
