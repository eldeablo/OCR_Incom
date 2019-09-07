package com.example.ocr_incom.Activity;


import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ocr_incom.Data.Data;
import com.example.ocr_incom.Data.DataTemplate;
import com.example.ocr_incom.MainActivity;
import com.example.ocr_incom.R;

import java.util.List;

public class SearchMain extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_template);

        Intent getData = getIntent();

        Data data = (Data) getData.getSerializableExtra("data");

        List<DataTemplate>  dataTemplates = (List<DataTemplate>) getData.getSerializableExtra("dataTemp");

    }

    @Override
    public void onBackPressed( ) {
        finish();
        super.onBackPressed();
    }
}
