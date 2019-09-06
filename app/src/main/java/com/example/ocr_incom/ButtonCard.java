package com.example.ocr_incom;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ocr_incom.Data.Data;
import com.example.ocr_incom.Data.DataTemplate;
import com.example.ocr_incom.Utils.TouchUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class ButtonCard extends LinearLayout {

    //Children view
    private TextView textView;
    private View v;

    //Data
    private Data data;
    private List<DataTemplate> dataTemplate;


    public ButtonCard(Context context, List<DataTemplate> dataTemplate) {
        super(context);

        this.dataTemplate = dataTemplate;

        data = new Data();


        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        Calendar cal = Calendar.getInstance();

        v = View.inflate(context, R.layout.card_button_data_plank, null);
        v.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        v.setPadding(0, 40, 0, 0);

        setId(R.id.dataButton);

        textView = v.findViewById(R.id.textView);
        textView.setText(dateFormat.format(cal.getTime()));

        setOnClickListener(new TouchUtils(null));

        data.setTime(textView.getText().toString());

        addView(v);
    }

    public Data getData( ) {
        return data;
    }

    public List<DataTemplate> getDataTemplate( ) {
        return dataTemplate;
    }

}
