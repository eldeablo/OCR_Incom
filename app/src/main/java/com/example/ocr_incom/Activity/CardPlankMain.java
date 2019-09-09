package com.example.ocr_incom.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.SparseArray;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ocr_incom.Data.Data;
import com.example.ocr_incom.Data.DataTemplate;
import com.example.ocr_incom.R;
import com.example.ocr_incom.Utils.DataUtils;
import com.example.ocr_incom.Utils.Utils;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

import java.util.List;
import java.util.Objects;

public class CardPlankMain extends AppCompatActivity {

    private ImageView view;
    private LinearLayout table;

    //Data
    private DataUtils dataUtils = new DataUtils();
    private List<DataTemplate> dataTemplates;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plank);

        Intent getData = getIntent();

        Data data = (Data) getData.getSerializableExtra("data");
        dataTemplates = (List<DataTemplate>) getData.getSerializableExtra("dataTemp");

        view = findViewById(R.id.image);

        table = findViewById(R.id.table);

        getDataPlankInImage(Uri.parse(Objects.requireNonNull(data).getDataCard().getImageSource()));
    }


    /**
     * Get image Text
     *
     * @param imageUri get URI image is file
     */
    private void getDataPlankInImage(Uri imageUri) {
        view.setImageURI(imageUri);

        BitmapDrawable drawable = (BitmapDrawable) view.getDrawable();
        Bitmap map = drawable.getBitmap();

        TextRecognizer textRecognizer = new TextRecognizer.Builder(getApplicationContext()).build();
        if (!textRecognizer.isOperational()) {
            System.out.println("Error");
        } else {
            Frame frame = new Frame.Builder().setBitmap(map).build();
            SparseArray<TextBlock> text = textRecognizer.detect(frame);

            for (int i = 0; i < text.size(); i++) {
                TextBlock _block = text.valueAt(i);
                dataUtils.breakingImageText(_block.getValue());
            }

            dataUtils.searchAllTemplateInScheme(dataTemplates);
            add();
        }
    }

    @Override
    public void onBackPressed() {
        finish();

        super.onBackPressed();
    }

    private void add() {
        for (DataTemplate template : dataUtils.getTemplate()) {
            Utils.addPlankTable(template.getTemplate(), template.getCount(), template.getPlank(), table, this);
        }
        for (String s : dataUtils.getScheme()) {
            Utils.addPlankTable("Схема", "", s, table, this);
        }
    }
}
