package com.example.ocr_incom.Activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ocr_incom.MainActivity;
import com.example.ocr_incom.R;
import com.example.ocr_incom.Utils.ActionIntentUtils;
import com.example.ocr_incom.Utils.FileUtils;
import com.example.ocr_incom.Utils.Utils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import static android.hardware.Camera.AutoFocusCallback;
import static android.hardware.Camera.PictureCallback;
import static android.hardware.Camera.PreviewCallback;
import static android.hardware.Camera.open;
import static android.view.SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS;

public class CameraMain extends AppCompatActivity implements SurfaceHolder.Callback, View.OnClickListener, PictureCallback, PreviewCallback, AutoFocusCallback {

    private SurfaceView preview;
    private Camera camera;

    FileUtils fileUtils = new FileUtils(this);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera_get_image);

        ImageView view = findViewById(R.id.imageView2);

        CropImageView f = findViewById(R.id.cropImageView);

        FloatingActionButton cameraPicture = findViewById(R.id.cameraPicture);
        cameraPicture.setOnClickListener(this);
        preview = findViewById(R.id.imageView);
        preview.setOnClickListener(this);

        SurfaceHolder surfaceHolder = preview.getHolder();
        surfaceHolder.addCallback(this);
        surfaceHolder.setType(SURFACE_TYPE_PUSH_BUFFERS);

        camera = open();
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onAutoFocus(boolean b, Camera camera) {
        Camera.Parameters params = camera.getParameters();
        params.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
        camera.setParameters(params);
    }

    @Override
    public void onPictureTaken(byte[] bytes, Camera camera) {
        fileUtils.saveImageFile(String.valueOf(System.currentTimeMillis()), bytes, this);

        CropImage.activity(Utils.getUriSaveImage(new File(fileUtils.getFullNameFile())))
                .start(this);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult _result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                fileUtils.saveImageFile(fileUtils.getNameFile(), Utils.bitmapToByteArray(Objects.requireNonNull(Utils.getBitmap(_result.getUri()))), this);
                ActionIntentUtils.performFileCamera(this, _result.getUri());
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = _result.getError();
            }
        }
    }

    @Override
    public void onPreviewFrame(byte[] bytes, Camera camera) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        try {
            camera.setPreviewDisplay(surfaceHolder);
            camera.setPreviewCallback(this);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Camera.Size previewSize = camera.getParameters().getPreviewSize();
        float aspect = (float) previewSize.width / previewSize.height;

        int previewSurfaceWidth = preview.getWidth();
        int previewSurfaceHeight = preview.getHeight();

        ViewGroup.LayoutParams lp = preview.getLayoutParams();


        if (this.getResources().getConfiguration().orientation != Configuration.ORIENTATION_LANDSCAPE) {
            camera.setDisplayOrientation(90);
            lp.height = previewSurfaceHeight;
            lp.width = (int) (previewSurfaceHeight / aspect);
        } else {

            camera.setDisplayOrientation(0);
            lp.width = previewSurfaceWidth;
            lp.height = (int) (previewSurfaceWidth / aspect);
        }

        preview.setLayoutParams(lp);
        camera.startPreview();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.cameraPicture) {
            camera.takePicture(null, null, this);
        } else if (id == R.id.imageView) {
            camera.autoFocus(this);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == MainActivity.REQUEST_CODE_CAMERA) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                camera = open();
            }
        }
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}
