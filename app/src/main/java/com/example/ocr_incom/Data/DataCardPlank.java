package com.example.ocr_incom.Data;

import java.io.Serializable;
import java.util.ArrayList;

public class DataCardPlank implements Serializable {
    private String imageSource;
    private ArrayList<String> arrayPlank = new ArrayList<>();

    public DataCardPlank( ) {

    }

    public String getImageSource( ) {
        return imageSource;
    }

    public void setImageSource(String imageSource) {
        this.imageSource = imageSource;
    }

    public ArrayList<String> getArrayPlank( ) {
        return arrayPlank;
    }

    public void setArrayPlank(ArrayList<String> arrayPlank) {
        this.arrayPlank = arrayPlank;
    }

}
