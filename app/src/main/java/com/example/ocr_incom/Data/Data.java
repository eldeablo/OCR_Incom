package com.example.ocr_incom.Data;

import java.io.Serializable;

public class Data implements Serializable {
    private String time;
    private DataCardPlank dataCard = new DataCardPlank();

    public Data() {

    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public DataCardPlank getDataCard() {
        return dataCard;
    }

    public void setDataCard(DataCardPlank dataCard) {
        this.dataCard = dataCard;
    }

}
