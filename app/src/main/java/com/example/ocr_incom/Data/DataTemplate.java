package com.example.ocr_incom.Data;

import java.io.Serializable;

public class DataTemplate implements Serializable {

    private String plank;
    private String template;
    private String count;

    public DataTemplate( ) {

    }

    public String getPlank( ) {
        return plank;
    }

    public void setPlank(String plank) {
        this.plank = plank;
    }

    public String getTemplate( ) {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getCount( ) {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
