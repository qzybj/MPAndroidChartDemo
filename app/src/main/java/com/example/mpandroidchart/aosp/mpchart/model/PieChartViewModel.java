package com.example.mpandroidchart.aosp.mpchart.model;

import com.example.mpandroidchart.aosp.mpchart.interfaces.IPieChartModel;
import com.example.mpandroidchart.aosp.mpchart.interfaces.IPieChartViewModel;
import com.example.mpandroidchart.aosp.mpchart.interfaces.IPieLabelModel;
import com.example.mpandroidchart.aosp.mpchart.interfaces.IPieSortModel;

import java.util.List;

/**
 * Created by Clair on 2017/3/17.
 */

public class PieChartViewModel implements IPieChartViewModel {
    /**项部的支付方式列表*/
    private List<IPieSortModel> sortList;
    /**底部支付方式Icon列表*/
    private List<IPieLabelModel> labelList;
    /**用于生成饼状图的数据*/
    private IPieChartModel pieChart;

    /**
     *
     * @param sortList  项部的支付方式列表
     * @param labelList 底部支付方式Icon列表
     * @param pieChart 用于生成饼状图的数据
     */
    public PieChartViewModel(List<IPieSortModel> sortList, List<IPieLabelModel> labelList, IPieChartModel pieChart) {
        this.sortList = sortList;
        this.labelList = labelList;
        this.pieChart = pieChart;
    }

    @Override
    public List<IPieSortModel> getSortList() {
        return sortList;
    }

    @Override
    public List<IPieLabelModel> getLabelList() {
        return labelList;
    }

    @Override
    public IPieChartModel getPieChartData() {
        return pieChart;
    }
}
