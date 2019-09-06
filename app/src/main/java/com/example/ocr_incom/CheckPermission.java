package com.example.ocr_incom;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class CheckPermission {

    public static void checkIsOpenCamera(Activity activity) {
        int permissionStatus = ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA);

        if (permissionStatus == PackageManager.PERMISSION_GRANTED) {

        } else {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA},
                    MainActivity.REQUEST_CODE_CAMERA);
        }
    }
}
