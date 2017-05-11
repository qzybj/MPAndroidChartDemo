package com.example.mpandroidchart.aosp.mpchart.model;


import com.example.mpandroidchart.aosp.mpchart.interfaces.IPieLabelModel;

/**
 * Created by Clair on 2017/3/17.
 */
public class PieLabelModel implements IPieLabelModel {
    private int colorResId;
    private String title;

    public PieLabelModel(String title, int colorResId) {
        this.colorResId = colorResId;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getColorResId() {
        return colorResId;
    }

    public void setColorResId(int colorResId) {
        this.colorResId = colorResId;
    }
}
