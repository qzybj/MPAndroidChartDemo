package com.example.mpandroidchart.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.example.mpandroidchart.R;
import com.example.mpandroidchart.aosp.mpchart.piechart.PieChartView;
import com.example.mpandroidchart.aosp.mpchart.utils.ReportTestDataUtil;
import com.example.mpandroidchart.aosp.mpchart.utils.CListUtil;
import com.example.mpandroidchart.aosp.mpchart.utils.ChartUtil;
import com.example.mpandroidchart.fragment.base.FrameBaseFragment;
import com.example.mpandroidchart.aosp.mpchart.model.PieChartViewModel;
import com.example.mpandroidchart.model.OrderPaymentDetailEntity;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Clair on 2017/3/16.
 * 今日汇总
 */
public class SummaryTodayFragment extends FrameBaseFragment {

    private LinearLayout sv_layout;
    private RelativeLayout layout_nodata;
    private PieChartView saleView;
    private PieChartView refundView;
    private ArrayList<OrderPaymentDetailEntity> paymentDetailEntities;

    private PieChartViewModel saleData;
    private PieChartViewModel refundData;


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
        refundView = new PieChartView(getBaseActivity(),PieChartView.TYPE_REFUND);
        refundView.setHeaderTitleVisiabel(true);
        sv_layout.addView(saleView.getView());
        sv_layout.addView(refundView.getView());
    }

    public void refresh() {
        refreshLocalTodayData();
        if(saleView!=null&& getTodaySaleData()!=null){
            saleView.setVisibility(true);
            saleView.refresh(getTodaySaleData());
        }else{
            saleView.setVisibility(false);
        }

        if(refundView!=null&&getTodayRefundData()!=null){
            refundView.setVisibility(true);
            refundView.refresh(getTodayRefundData());
        }else{
            refundView.setVisibility(false);
        }
    }

    /** 获取本地当天数据 - 销售*/
    private PieChartViewModel getTodaySaleData(){
        return saleData;
    }

    /** 获取本地当天数据 - 退款*/
    private PieChartViewModel getTodayRefundData(){
        return refundData;
    }

    /**查询数据库中的数据并生成展示用数据*/
    private void refreshLocalTodayData(){
        setTestData();

        if(!CListUtil.isEmpty(paymentDetailEntities)){
            layout_nodata.setVisibility(View.GONE);

            List<OrderPaymentDetailEntity> saleList = new ArrayList<OrderPaymentDetailEntity>();
            List<OrderPaymentDetailEntity> refundList = new ArrayList<OrderPaymentDetailEntity>();

            for (OrderPaymentDetailEntity item:paymentDetailEntities) {
                if(item!=null) {
                    if (item.isSale()) {//销售
                        saleList.add(item);
                    } else {//退货
                        refundList.add(item);
                    }
                }
            }
            saleData = ChartUtil.getPieChart2Today(true,saleList);
            refundData =  ChartUtil.getPieChart2Today(false,refundList);
        }else {
            //无数据展示
            layout_nodata.setVisibility(View.VISIBLE);
        }
    }

    /**设置测试信息入口*/
    private void setTestData() {
        paymentDetailEntities = ReportTestDataUtil.getOrderPaymentDetails(true);
    }
}