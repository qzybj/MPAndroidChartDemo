package com.example.mpandroidchart.aosp.mpchart.model;


import com.example.mpandroidchart.aosp.mpchart.interfaces.IPieDrawModel;
import com.example.mpandroidchart.aosp.mpchart.piechart.ChartType;

/**
 * Created by Clair on 2017/3/17.
 */
public class PieDrawModel implements IPieDrawModel {
    private ChartType type;
    private String label;
    private float value;

    public PieDrawModel(ChartType type, String label, float value) {
        this.type = type;
        this.label = label;
        this.value = value;
    }

    public ChartType getType() {
        return type;
    }

    public void setType(ChartType type) {
        this.type = type;
    }


    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    @Override
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
