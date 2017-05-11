package com.example.mpandroidchart.aosp.mpchart.utils;

import com.brady.coreframe.utils.dataprocess.ListUtils;
import com.brady.coreframe.utils.dataprocess.RandomUtils;
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
import com.example.mpandroidchart.model.Payment;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Created by Clair on 2017/3/23.
 */
public class ReportTestDataUtil {


    /**获取测试数据 */
    public static PieChartViewModel getTestPieData(){
        List<IPieSortModel> sortList = new ArrayList<IPieSortModel>();
        sortList.add(new PieSortModel(ChartHelper.getString(R.string.discount),"￥"+"367"));
        sortList.add(new PieSortModel(ChartHelper.getString(R.string.cash_payment),"￥"+"336470"));

        List<IPieLabelModel> labelList = ChartHelper.getPaymentLabels();

        List<IPieDrawModel> drawModels = new ArrayList<IPieDrawModel>();
        ArrayList<ChartType> list = ListUtils.buildList(ChartType.values());

        for (ChartType item:list){
            drawModels.add(new PieDrawModel(item,item.getName(), RandomUtils.getRandom(10)*100.3F));
        }

        IPieChartModel pieChart = new PieChartModel(drawModels);
        PieChartViewModel data = new PieChartViewModel(sortList,labelList,pieChart);
        return data;
    }


    public static ArrayList<OrderPaymentDetailEntity> getOrderPaymentDetails(boolean isSale) {
        String traceNo ="33445566";
        ArrayList<OrderPaymentDetailEntity> paymentList=new ArrayList<>();
        //现金
        paymentList.add(getOrderPaymentDetailEntity(isSale,Payment.CASH,traceNo));
        //预付费卡
        paymentList.add(getOrderPaymentDetailEntity(isSale,Payment.PREPAID,traceNo));
        //银行卡
        paymentList.add(getOrderPaymentDetailEntity(isSale,Payment.BANK_CARD,traceNo));
        //微信支付
        paymentList.add(getOrderPaymentDetailEntity(isSale,Payment.WEIXIN,traceNo));
        //支付宝支付
        paymentList.add(getOrderPaymentDetailEntity(isSale,Payment.ALIPAY,traceNo));
        //积分支付
        paymentList.add(getOrderPaymentDetailEntity(isSale,Payment.VIPPOINT,traceNo));
        //积分现抵
        OrderPaymentDetailEntity item= getOrderPaymentDetailEntity(isSale,Payment.VIPPOINT,traceNo);
        item.setPaymentType(ChartPayment.VIPPOINT_DEDUCTION.getType());
        item.setPayCategory(ChartPayment.VIPPOINT_DEDUCTION.getCategory());
        item.setPaySubCategory(ChartPayment.VIPPOINT_DEDUCTION.getSubCategory());
        paymentList.add(item);
        //赠券
        paymentList.add(getOrderPaymentDetailEntity(isSale,Payment.FREE_TICKET,traceNo));
        //提货券
        paymentList.add(getOrderPaymentDetailEntity(isSale,Payment.PICKING_CARD,traceNo));
        //预付费卡退款
        paymentList.add(getOrderPaymentDetailEntity(isSale,Payment.RETURN_PREPAID,traceNo));
        //积分退款
        paymentList.add(getOrderPaymentDetailEntity(isSale,Payment.RETURN_VIPPOINT,traceNo));
        return paymentList;
    }


    /**
     *
     * @param isSale true 销售 false 退款
     * @param payType 支付类型 Payment.getType()
     * @param traceNo 交易凭证号
     * @return
     */
    private static OrderPaymentDetailEntity getOrderPaymentDetailEntity(boolean isSale, Payment payType, String traceNo){
        OrderPaymentDetailEntity entity=new OrderPaymentDetailEntity();
        entity.setUserID("user");
        entity.setTraceNo(traceNo);
        entity.setStoreNo("HZ01");
        entity.setShoppeNo("城西银泰001test");
        entity.setSaleType(isSale);
        entity.setOrderDate(DateUtil.getCurrentDate());
        entity.setOrderTime(DateUtil.getCurrentTime());
        entity.setPaymentType(payType.getType());
        entity.setPayCategory(payType.getCategory());
        entity.setPaySubCategory(payType.getSubCategory());
        entity.setCreateTime(System.currentTimeMillis());
        entity.setPaymentAmount(new BigDecimal(10* new Random().nextInt(10)));
        return entity;
    }
}