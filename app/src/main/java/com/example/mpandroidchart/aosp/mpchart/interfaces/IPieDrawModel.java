package com.example.mpandroidchart.aosp.mpchart.interfaces;

import com.example.mpandroidchart.aosp.mpchart.piechart.ChartType;

/**
 * Created by Clair on 2017/3/22.
 */
public interface IPieDrawModel {
     /**用来区分类型*/
     ChartType getType();
     /**用来计算百分比的Int类型值*/
     float getValue();
     /**用来展示的字符串*/
     String getLabel();
}
