package com.example.mpandroidchart.aosp.mpchart.piechart;


import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.brady.coreframe.utils.LogUtils;
import com.example.mpandroidchart.R;
import com.example.mpandroidchart.adapter.PieViewLabelAdapter;
import com.example.mpandroidchart.adapter.PieViewSortAdapter;
import com.example.mpandroidchart.aosp.mpchart.interfaces.IPieChartViewModel;
import com.example.mpandroidchart.aosp.mpchart.interfaces.IPieLabelModel;
import com.example.mpandroidchart.aosp.mpchart.interfaces.IPieSortModel;
import com.example.mpandroidchart.aosp.mpchart.utils.ChartHelper;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import java.util.List;


/**
 * Created by Clair on 2017/3/16.
 */
public class PieChartView implements OnChartValueSelectedListener {
    public static final int TYPE_NONE=0;
    public static final int TYPE_SALE=1;
    public static final int TYPE_REFUND=2;

    public static final String PREFIX_SALE="+";
    public static final String PREFIX_REFUND="-";


    private Context mContext;
    private View rootView;

    private LinearLayout layout_header;
    private TextView tv_header;

    private RecyclerView rv_sort;
    private RecyclerView rv_label;

    private PieViewSortAdapter sortAdapter;
    private PieViewLabelAdapter labelAdapter;

    private PieChart mChart;

    private int type=TYPE_NONE;
    private String curPrefix=null;

    public PieChartView(Context con) {
        this.mContext = con;
        initView();
    }

    public PieChartView(Context con, int type) {
        this.mContext = con;
        this.type = type;
        initView();
    }

    private void initView() {
        rootView =  LayoutInflater.from(mContext).inflate(R.layout.view_chart_pie,null);

        layout_header = (LinearLayout) rootView.findViewById(R.id.layout_header);
        tv_header = (TextView) rootView.findViewById(R.id.tv_header);

        rv_sort = (RecyclerView) rootView.findViewById(R.id.rv_sort);
        rv_label = (RecyclerView) rootView.findViewById(R.id.rv_label);

        mChart = (PieChart) rootView.findViewById(R.id.chart_pie);

        ChartHelper.initPieChart(mChart, null);
        switchType(type);

    }
    public View getView(){
        return rootView;
    }


    public void switchType(int type){
        switch (type){
            case TYPE_SALE:
                setHeaderTitle(ChartHelper.getString(R.string.sale));
                curPrefix = PREFIX_SALE;
                break;
            case TYPE_REFUND:
                setHeaderTitle(ChartHelper.getString(R.string.refund));
                curPrefix = PREFIX_REFUND;
                break;
            case TYPE_NONE:
            default:
                break;

        }
    }

    /**
     * 刷新数据
     * @param iData
     */
    public void refresh(IPieChartViewModel iData){
        if(iData!=null){
            setSortAdapter(iData.getSortList());
            setLabelAdapter(iData.getLabelList());
            ChartHelper.setPieChartData(mChart,iData.getPieChartData());
        }
    }

    /**
     * 项部支付方式及金额信息列表
     * @param sortList
     */
    private void setSortAdapter(List<IPieSortModel> sortList) {
        if(rv_sort!=null&&sortList!=null&&sortList.size()>0){
            rv_sort.setLayoutManager(new LinearLayoutManager(mContext));
            sortAdapter = new PieViewSortAdapter(mContext, R.layout.adapter_item_piesort,sortList,curPrefix);
            rv_sort.setAdapter(sortAdapter);
        }
    }

    /**
     * 底部支付方式Icon列表
     * @param labelList
     */
    private void setLabelAdapter(List<IPieLabelModel> labelList) {
        if(rv_label!=null&&labelList!=null&&labelList.size()>0){
            rv_label.setLayoutManager(new GridLayoutManager(mContext,4));
            //rv_label.setLayoutManager(new LinearLayoutManager(mContext));
            labelAdapter = new PieViewLabelAdapter(mContext,R.layout.adapter_item_pielabel,labelList);
            rv_label.setAdapter(labelAdapter);
        }
    }

    /**
     * 设置头部标题
     * @param title
     */
    private void setHeaderTitle(String title){
        if(layout_header!=null){
            tv_header.setText(title);
        }
    }
    /**
     * 设置头部标题是否显示
     * @param isVisible
     */
    public void setHeaderTitleVisiabel(boolean isVisible){
        if(layout_header!=null){
            layout_header.setVisibility(isVisible? View.VISIBLE: View.GONE);
        }
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {
        if (e != null) {
            LogUtils.i("VAL SELECTED", "Value: " + e.getY() + ", xIndex: " + e.getX()
                    + ", DataSet index: " + h.getDataSetIndex());
        }
    }

    @Override
    public void onNothingSelected() {
        Log.i("PieChart", "nothing selected");
    }

    public void setVisibility(boolean visibility) {
        if(rootView!=null){
            rootView.setVisibility(visibility? View.VISIBLE: View.GONE);
        }
    }
}