package com.example.mpandroidchart.aosp.mpchart.utils;

import android.graphics.Color;
import com.brady.coreframe.utils.dataprocess.ListUtils;
import com.brady.coreframe.utils.dataprocess.MapUtils;
import com.example.mpandroidchart.CApplication;
import com.example.mpandroidchart.R;
import com.example.mpandroidchart.aosp.mpchart.formatter.RmbFormatter;
import com.example.mpandroidchart.aosp.mpchart.interfaces.IPieChartModel;
import com.example.mpandroidchart.aosp.mpchart.interfaces.IPieDrawModel;
import com.example.mpandroidchart.aosp.mpchart.interfaces.IPieLabelModel;
import com.example.mpandroidchart.aosp.mpchart.model.PieLabelModel;
import com.example.mpandroidchart.aosp.mpchart.piechart.ChartPayment;
import com.example.mpandroidchart.aosp.mpchart.piechart.ChartType;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Created by Clair on 2017/3/14.
 * 报表数据处理辅助类
 */
public class ChartHelper {

    /**支付报表支付方式生成顺序*/
    private static ArrayList<ChartType> paymentSortList;
    /**退款报表支付方式生成顺序*/
    private static ArrayList<ChartType> refundPaymentSortList;
    /**对应关系*/
    private static HashMap<ChartPayment,ChartType> matchMap;

    /**
     *
     * @param chart
     * @param listener
     */
    public static void initPieChart(PieChart chart, OnChartValueSelectedListener listener){
        //如果启用此选项，则图表中的值将以百分比形式绘制，而不是以原始值绘制。ValueFormatter为格式提供的值随后以百分比形式提供。
        chart.setUsePercentValues(false);

        chart.setNoDataText(ChartHelper.getString(R.string.no_data));

        chart.getDescription().setEnabled(false);//设置显示在图表右下角的描述文本是否开启
        chart.setExtraOffsets(40.f, 0.f, 40.f, 0.f);//设置margin

        chart.setDragDecelerationFrictionCoef(0.95f);

        chart.setDrawHoleEnabled(true);//是否设置圆心
        chart.setHoleColor(Color.WHITE);//设置圆心颜色
        chart.setHoleRadius(60f);//是否设置圆心半径

        chart.setDrawCenterText(false);//是否绘制圆心文字
        //chart.setCenterTextTypeface(mTfLight);//设置圆心显示文字内容自定义字体
        //chart.setCenterText(generateCenterSpannableText());//设置圆心显示文字内容

        //chart.setTransparentCircleColor(Color.WHITE);//设置透明圆的颜色
        //chart.setTransparentCircleAlpha(110);//设置透明圆应具有的透明度（0-255）
        //chart.setTransparentCircleRadius(61f);//设定透明圆心半径

        chart.setDrawEntryLabels(false);//饼状图内部文字Label
        // entry label styling
        chart.setEntryLabelColor(Color.WHITE);//饼状图内部自定义字体颜色
        //chart.setEntryLabelTypeface(mTfRegular);//饼状图内部自定义字体
        chart.setEntryLabelTextSize(14f);//饼状图内部字体大小

        // enable rotation of the chart by touch
        chart.setRotationEnabled(false);//设置是否可旋转
        chart.setRotationAngle(0);//设置默认旋转角度
        chart.setHighlightPerTapEnabled(false);//设置是否可点击放大

        // chart.setUnit(" €");
        // chart.setDrawUnitsInChart(true);

        // add a selection listener
        if(listener!=null){
            chart.setOnChartValueSelectedListener(listener);
        }

        //chart.animateY(1400, Easing.EasingOption.EaseInOutQuad);//开始动画
        //chart.spin(2000, 0, 360,Easing.EasingOption.EaseInOutQuad);//开始动画

        //设置图表下方的描述文本样式
        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDirection(Legend.LegendDirection.LEFT_TO_RIGHT);
        l.setEnabled(false);//设置显示在图表右下角的描述文本是否开启
    }


    /**
     * 重置设置数据并刷新
     * @param chart
     * @param date  生成数据
     */
    public static void setPieChartData(PieChart chart,IPieChartModel date) {
        if(chart==null||date==null){
            return ;
        }
        List<IPieDrawModel> list = date.getDrawList();
        if(list!=null&&list.size()>0){

            ArrayList<PieEntry> entries = new ArrayList<PieEntry>();//饼状图生成value
            ArrayList<Integer> colors = new ArrayList<Integer>();//饼状图生成value对应颜色

            for (IPieDrawModel item: list) {
                //如果要设置图标的话，传参3
                ChartType type = item.getType();
                entries.add(new PieEntry(item.getValue(),type.getName()));
                colors.add(getColor(type.getColor()));
            }

            PieDataSet dataSet = new PieDataSet(entries, "");

            dataSet.setDrawIcons(false);//是否绘制图标
            //dataSet.setIconsOffset(new MPPointF(0, 40));//绘制图标偏移量

            //dataSet.setSliceSpace(3f);//饼状图各块之间的分隔线
            //dataSet.setSelectionShift(5f);//选中的数据变大时的状态

            dataSet.setColors(colors); // add a lot of colors

            //添加对应的指示线
            dataSet.setDrawValues(true);//是否设置指示线
            //dataSet.setValueLineVariableLength(true);//设置指示线是否按比例计算
            //dataSet.setValueLineWidth(15f);设置线宽度
            dataSet.setValueLinePart1Length(0.4f);
            dataSet.setValueLinePart2Length(0.5f);
            //dataSet.setValueLinePart1OffsetPercentage(30.f);//设置线偏移量
            dataSet.setValueLineColor(0xFF667788);
            //dataSet.setYValuePosition(PieDataSet.ValuePosition.INSIDE_SLICE);//在内部显示文字
            dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);//在外部用指示线电显示文字

            //指示线上对应的显示文字
            PieData data = new PieData(dataSet);
            data.setValueFormatter(new RmbFormatter());
            data.setValueTextSize(14f);
            data.setValueTextColor(0xFF667788);//设置线上的字体颜色，指定单色
            //data.setValueTextColors(getColors());//设置线上的字体颜色,跟随块的色彩
            //data.setValueTypeface(tf);//设置指定字体

            chart.setData(data);//更新数据
            //undo all highlights
            chart.highlightValues(null);
            chart.invalidate();//刷新（重绘）
        }
    }

    /**
     * 底部Label展示列表
     * @return
     */
    public static List<IPieLabelModel> getPaymentLabels() {
        List<IPieLabelModel> labelList = new ArrayList<IPieLabelModel>();
        ChartType[] payments = ChartType.values();
        if(payments != null ){
            for(ChartType p:payments){
                labelList.add(new PieLabelModel(p.getName(),p.getLabelBlockColor()));
            }
        }
        return labelList;
    }

    /**获取支付方式顺序列表 */
    public static ArrayList<ChartType> getPaymentSortList() {
        if(paymentSortList==null){
            paymentSortList = getSortList(ChartType.ALIPAY,ChartType.BANK_CARD,
                    ChartType.FREE_TICKET,ChartType.PREPAID,ChartType.WEIXIN,
                    ChartType.VIPPOINT,ChartType.VIPPOINT_DEDUCTION,ChartType.CASH,
                    ChartType.PICKING_CARD);
        }
        return paymentSortList;
    }

    /**获取退款支付方式顺序列表 */
    public static ArrayList<ChartType> getRefundSortList() {
        if(refundPaymentSortList==null){
            refundPaymentSortList = getSortList(ChartType.ALIPAY,ChartType.BANK_CARD,
                    ChartType.FREE_TICKET, ChartType.RETURN_PREPAID,ChartType.WEIXIN,
                    ChartType.RETURN_VIPPOINT,ChartType.VIPPOINT_DEDUCTION,
                    ChartType.CASH,ChartType.PICKING_CARD);
        }
        return refundPaymentSortList;
    }


    public static ArrayList<ChartType> getSortList(ChartType... arraySort) {
        ArrayList<ChartType> list = new ArrayList<ChartType>();
        if(ListUtils.isNotEmptyArray(arraySort)){
            list =ListUtils.buildList(arraySort);
        }
        return list;
    }

    /**
     * 查找类型匹配
     * @param matchItem
     * @return
     */
    public static ChartType getMatchType(ChartPayment matchItem) {
        if(matchMap==null){
            matchMap = new HashMap<ChartPayment,ChartType>();

            matchMap.put(ChartPayment.ALIPAY,ChartType.ALIPAY);
            matchMap.put(ChartPayment.BANK_CARD,ChartType.BANK_CARD);
            matchMap.put(ChartPayment.CASH,ChartType.CASH);
            matchMap.put(ChartPayment.FREE_TICKET,ChartType.FREE_TICKET);
            matchMap.put(ChartPayment.PREPAID,ChartType.PREPAID);
            matchMap.put(ChartPayment.VIPPOINT,ChartType.VIPPOINT);
            matchMap.put(ChartPayment.VIPPOINT_DEDUCTION,ChartType.VIPPOINT_DEDUCTION);
            matchMap.put(ChartPayment.WEIXIN,ChartType.WEIXIN);
            matchMap.put(ChartPayment.PICKING_CARD,ChartType.PICKING_CARD);

            matchMap.put(ChartPayment.RETURN_PREPAID,ChartType.RETURN_PREPAID);
            matchMap.put(ChartPayment.RETURN_VIPPOINT,ChartType.RETURN_VIPPOINT);

        }
        return matchMap.get(matchItem);
    }

    /**
     * 查找类型匹配
     * @param matchItem
     * @return
     */
    public static ChartPayment getMatchType(ChartType matchItem) {
        if(MapUtils.isEmpty(matchMap)){
            getMatchType(ChartPayment.ALIPAY);
        }
        Object obj = MapUtils.getKeyByValue(matchMap,matchItem);
        if(obj!=Integer.valueOf(-1)){
            return (ChartPayment)obj;
        }
//        Set<Map.Entry<ChartPayment, ChartType>> entries = matchMap.entrySet();
//        for (Map.Entry<ChartPayment, ChartType> p : entries) {
//            if(p!=null&&p.getValue()==matchItem){
//                return p.getKey();
//            }
//        }
        return null;
    }



    public static int getColor(int colorResId){
        return CApplication.applicationContext.getResources().getColor(colorResId);
    }
    public static String getString(int resId){
        return CApplication.applicationContext.getResources().getString(resId);
    }
}