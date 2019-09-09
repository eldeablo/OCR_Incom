package com.example.ocr_incom.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ocr_incom.Data.DataTemplate;
import com.example.ocr_incom.R;
import com.example.ocr_incom.Utils.DataUtils;
import com.example.ocr_incom.Utils.TouchUtils;
import com.example.ocr_incom.Utils.Utils;

import java.util.List;

public class SearchMain extends AppCompatActivity {

    private LinearLayout listTemplateBase;
    private EditText findOneTemplate;

    private List<DataTemplate> dataTemplates;
    private DataUtils dataUtils = new DataUtils();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_template);

        listTemplateBase = findViewById(R.id.allTemplate);

        findOneTemplate = findViewById(R.id.findOneTemplate);
        findOneTemplate.setOnKeyListener(new TouchUtils(this));

        Intent getData = getIntent();

        dataTemplates = (List<DataTemplate>) getData.getSerializableExtra("dataTemp");

        addAllTemplateBase();
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }

    public void addOneTemplate() {
        listTemplateBase.removeAllViews();

        DataTemplate _oneTemplate = dataUtils.getOneTemplate(dataTemplates, findOneTemplate.getText().toString());

        Utils.addPlankTable(_oneTemplate.getTemplate(), _oneTemplate.getCount(), _oneTemplate.getPlank(), listTemplateBase, this);
    }

    public void addAllTemplateBase() {
        listTemplateBase.removeAllViews();

        for (DataTemplate template : dataTemplates) {
            Utils.addPlankTable(template.getTemplate(), template.getCount(), template.getPlank(), listTemplateBase, this);
        }
    }

    public EditText getFindOneTemplate() {
        return findOneTemplate;
    }
}
