package com.example.mpandroidchart.aosp.mpchart.utils;


import com.example.mpandroidchart.R;
import com.example.mpandroidchart.aosp.mpchart.interfaces.IPieChartModel;
import com.example.mpandroidchart.aosp.mpchart.interfaces.IPieDrawModel;
import com.example.mpandroidchart.aosp.mpchart.interfaces.IPieLabelModel;
import com.example.mpandroidchart.aosp.mpchart.interfaces.IPieSortModel;
import com.example.mpandroidchart.aosp.mpchart.model.PieChartModel;
import com.example.mpandroidchart.aosp.mpchart.model.PieChartViewModel;
import com.example.mpandroidchart.aosp.mpchart.model.PieDrawModel;
import com.example.mpandroidchart.aosp.mpchart.model.PieSortModel;
import com.example.mpandroidchart.aosp.mpchart.piechart.ChartPayment;
import com.example.mpandroidchart.aosp.mpchart.piechart.ChartType;
import com.example.mpandroidchart.model.OrderPaymentDetailEntity;
import com.example.mpandroidchart.utils.ParseUtils;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Created by Clair on 2017/3/22.
 * 报表数据生成类
 */
public class ChartUtil {

    /**
     *
     * 根据信息生成图表展示数据(今日汇总)
     * @param isSale true 销售  false 退款
     * @param saleList
     * @return
     */
    public static PieChartViewModel getPieChart2Today(boolean isSale, List<OrderPaymentDetailEntity> saleList){
        if(!CListUtil.isEmpty(saleList)){
            return getPieChartModel(isSale,getPayInfoMap(saleList));
        }
        return null;
    }

    /**
     * 根据数据库中数据，归类成支付方式区分的map
     * @param saleList
     * @return
     */
    private static HashMap<ChartPayment,BigDecimal> getPayInfoMap(List<OrderPaymentDetailEntity> saleList){
        if(!CListUtil.isEmpty(saleList)){
            HashMap<ChartPayment,BigDecimal> payInfoMap = new HashMap<ChartPayment,BigDecimal>();
            for (OrderPaymentDetailEntity item:saleList) {
                ChartPayment payment = ChartPayment.getPayment(item.getPayCategory(),item.getPaySubCategory());
                if (payment != null&&item.getPaymentAmount()!=null) {
                    if(payInfoMap.containsKey(payment)){
                        BigDecimal money= payInfoMap.get(payment);
                        payInfoMap.put(payment,NumberUtil.add(money,item.getPaymentAmount()));
                    }else{
                        payInfoMap.put(payment,item.getPaymentAmount());
                    }
                }
            }
            return payInfoMap;
        }
        return null;
    }

    /**
     *
     * 根据信息生成图表展示数据
     * @param isSale true 销售  false 退款
     * @param payInfoMap
     * @return
     */
    public static PieChartViewModel getPieChartModel(boolean isSale,HashMap<ChartPayment,BigDecimal> payInfoMap){
        if(!CListUtil.isEmpty(payInfoMap)){
            //获取支付顺序排序依据
            ArrayList<ChartType> showSortList = isSale ?
                    ChartHelper.getPaymentSortList() : ChartHelper.getRefundSortList();
            //项部展示数据
            List<IPieSortModel> topLabelList = new ArrayList<IPieSortModel>();
            //生成底部颜色示意列表
            List<IPieLabelModel> bottomLabelList = ChartHelper.getPaymentLabels();

            //以下为生成饼状图数据
            List<IPieDrawModel> drawModels = new ArrayList<IPieDrawModel>();
            //根据支付信息列表，生成展示数据
            if(!CListUtil.isEmpty(payInfoMap)){
                BigDecimal money;
                BigDecimal cashCategory = BigDecimal.ZERO;
                BigDecimal discountCategory = BigDecimal.ZERO;
                for (ChartType payment : showSortList) {
                    if (payment != null) {
                        ChartPayment type = ChartHelper.getMatchType(payment);
                        if (type != null && payInfoMap.containsKey(type)) {
                            money = payInfoMap.get(type);
                            if (type.isCashCategory()) {
                                cashCategory = NumberUtil.add(cashCategory, money);
                            } else {
                                discountCategory = NumberUtil.add(discountCategory, money);
                            }
                            drawModels.add(new PieDrawModel(payment, payment.getName(), ParseUtils.parseFloat(money + "")));

                        }
                    }
                }

                //设置展示分类总额
                if(NumberUtil.over(discountCategory, BigDecimal.ZERO)){
                    topLabelList.add(new PieSortModel(ChartHelper.getString(R.string.discount),
                            "￥" + StringUtil.parseDecimal(discountCategory)));
                }
                if(NumberUtil.over(cashCategory, BigDecimal.ZERO)){
                    topLabelList.add(new PieSortModel(ChartHelper.getString(R.string.cash_payment),
                            "￥" + StringUtil.parseDecimal(cashCategory)));
                }

                IPieChartModel pieChart = new PieChartModel(drawModels);
                PieChartViewModel data = new PieChartViewModel(topLabelList, bottomLabelList, pieChart);
                return data;
            }

        }
        return null;
    }
}