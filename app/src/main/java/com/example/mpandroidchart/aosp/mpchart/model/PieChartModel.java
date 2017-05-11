package com.example.mpandroidchart.aosp.mpchart.model;


import com.example.mpandroidchart.aosp.mpchart.interfaces.IPieChartModel;
import com.example.mpandroidchart.aosp.mpchart.interfaces.IPieDrawModel;
import java.util.List;

/**
 * Created by Clair on 2017/3/17.
 */

public class PieChartModel implements IPieChartModel {
    private List<IPieDrawModel> drawModels;

    public PieChartModel(List<IPieDrawModel> drawModels) {
        this.drawModels = drawModels;
    }

    @Override
    public List<IPieDrawModel> getDrawList() {
        return drawModels;
    }
}
