package com.example.mpandroidchart.aosp.mpchart.interfaces;

import java.util.List;

/**
 * Created by Clair on 2017/3/13.
 */
public interface IPieChartViewModel<T extends IPieSortModel, H extends IPieLabelModel> {

    /**项部展示大类统计*/
    List<T> getSortList();
    /**底部展示Label*/
    List<H> getLabelList();
    /**饼状图展示用数据*/
    IPieChartModel getPieChartData();

}
