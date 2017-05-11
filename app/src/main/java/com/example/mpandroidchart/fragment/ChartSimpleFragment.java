package com.example.mpandroidchart.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.mpandroidchart.R;
import com.example.mpandroidchart.aosp.mpchart.model.PieChartViewModel;
import com.example.mpandroidchart.aosp.mpchart.piechart.PieChartView;
import com.example.mpandroidchart.aosp.mpchart.utils.ReportTestDataUtil;
import com.example.mpandroidchart.fragment.base.FrameBaseFragment;


/**
 * Created by Clair on 2017/3/16.
 */
public class ChartSimpleFragment extends FrameBaseFragment {

    private LinearLayout sv_layout;
    private RelativeLayout layout_nodata;
    private PieChartView saleView;

    private PieChartViewModel saleData;


    @Override
    public void initConstant(Bundle savedInstanceState) {

    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_summarytoday;
    }

    @Override
    public void initContentView(View view) {
        sv_layout =  (LinearLayout)view.findViewById(R.id.layout_container);
        layout_nodata =  (RelativeLayout)view.findViewById(R.id.layout_nodata);
        initPieChartView();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        refresh();
    }

    private void initPieChartView() {
        saleView = new PieChartView(getBaseActivity(),PieChartView.TYPE_SALE);
        saleView.setHeaderTitleVisiabel(true);
        sv_layout.addView(saleView.getView());
    }

    public void refresh() {
        if(saleView!=null&&getChartData()!=null){
            layout_nodata.setVisibility(View.GONE);
            saleView.setVisibility(true);
            saleView.refresh(getChartData());
        }else{
            //无数据展示
            layout_nodata.setVisibility(View.VISIBLE);
            saleView.setVisibility(false);
        }
    }

    /** 获取本地当天数据 - 销售*/
    private PieChartViewModel getChartData(){
        saleData = ReportTestDataUtil.getTestPieData();
        return saleData;
    }

}