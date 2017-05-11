package com.example.mpandroidchart.aosp.mpchart.model;


import com.example.mpandroidchart.aosp.mpchart.interfaces.IPieSortModel;

/**
 * Created by Clair on 2017/3/17.
 */

public class PieSortModel  implements IPieSortModel {
    private String title;
    private String value;

    public PieSortModel(String title, String value) {
        this.title = title;
        this.value = value;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
