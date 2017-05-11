package com.example.mpandroidchart.aosp.mpchart.piechart;


import com.brady.coreframe.utils.dataprocess.StringUtils;
import com.example.mpandroidchart.model.Payment;

/**
 * 用于报表展示的支付方式类型
 */
public enum ChartPayment {
    CASH(Payment.CASH),
    PREPAID(Payment.PREPAID),
    BANK_CARD(Payment.BANK_CARD),
    WEIXIN(Payment.WEIXIN),
    ALIPAY(Payment.ALIPAY),
    VIPPOINT(Payment.VIPPOINT),
    FREE_TICKET(Payment.FREE_TICKET),  //赠券和提货券的大小支付方式由券核销接口决定
    PICKING_CARD(Payment.PICKING_CARD),
    VIPPOINT_DEDUCTION(11, "积分现抵", "积分现抵","E0","15"),
    //退款相关
    RETURN_PREPAID(Payment.RETURN_PREPAID),
    RETURN_VIPPOINT(Payment.RETURN_VIPPOINT);  //E0-15指积分现抵，E0-13指积分支付


    private final int type;
    //支付方式名称
    private final String name;
    //支付方式别名
    private final String aliasName ;
    //大支付方式
    private final String category;
    //小支付方式
    private final String subCategory;

    ChartPayment(Payment payment) {
        this.type = payment.getType();
        this.name = payment.getName();
        this.aliasName = payment.getAliasName();
        this.category = payment.getCategory();
        this.subCategory = payment.getSubCategory();
    }

    /**
     *
     * @param type
     *  支付类型
     * @param name
     *  支付类型的具体名称
     * @param aliasName
     *  支付类型别名， 主要是把退款相关的支付方式中的退款两字去掉
     * @param category
     *  大支付方式
     * @param subCategory
     *  小支付方式
     */
    ChartPayment(int type, String name, String aliasName, String category, String subCategory) {
        this.type = type;
        this.name = name;
        this.aliasName = aliasName;
        this.category = category;
        this.subCategory = subCategory;
    }

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    /**
     * 大支付方式
     * @return
     */
    public String getCategory() {
        return category;
    }

    /**
     * 小支付方式
     * @return
     */
    public String getSubCategory() {
        return subCategory;
    }

    public String getAliasName() {
        return aliasName;
    }

    /**
     * 根据支付类型找出相对应的枚举
     * @param type
     * @return
     */
    public static ChartPayment getPaymentByType(int type){
        ChartPayment[] payments = values();
        if(payments != null ){
            for(ChartPayment p:payments){
                if(type == p.getType()){
                    return p;
                }
            }
        }
        return null ;
    }

    /**
     * 根据支付类型找出相对应的枚举
     * @param strType
     * @return
     */
    public static ChartPayment getPaymentByType(String strType){
        int type = StringUtils.parseInt(strType);
        if(type < 0 ){
            return null ;
        }
        ChartPayment[] payments = values();
        if(payments != null ){
            for(ChartPayment p:payments){
                if(type == p.getType()){
                    return p;
                }
            }
        }
        return null ;
    }

    /**
     * 根据销售类型获取支付信息
     * @param isSale
     * @param category
     * @param subCategory
     * @return
     */
    public static ChartPayment getPayment(boolean isSale, String category, String subCategory){
        ChartPayment payment = getPayment(category,subCategory);
        if( payment != null ){
            if(isSale){
                //销售
                if( payment == ChartPayment.RETURN_PREPAID){
                    payment = ChartPayment.PREPAID;
                }else if(payment == ChartPayment.RETURN_VIPPOINT){
                    payment = ChartPayment.VIPPOINT;
                }
            }else {
                //退货
                if( payment == ChartPayment.PREPAID){
                    payment = ChartPayment.RETURN_PREPAID;
                }else if(payment == ChartPayment.VIPPOINT){
                    payment = ChartPayment.RETURN_VIPPOINT;
                }
            }

        }
        return payment;
    }

    /**
     * 根据大小支付方式获取{@link ChartPayment}对象
     * @param category
     * @param subCategory
     * @return
     */
    public static ChartPayment getPayment(String category, String subCategory){
        ChartPayment[] payments = ChartPayment.values();
        for(ChartPayment p: payments){
            if( p.getCategory().equals(category) &&
                    p.getSubCategory().equals(subCategory)){
                return p;
            }
        }
        //当匹配不上，则是券
        if(ChartPayment.FREE_TICKET.getCategory().equals(category)){
            return ChartPayment.FREE_TICKET;
        }else if(ChartPayment.PICKING_CARD.getCategory().equals(category)){
            return ChartPayment.PICKING_CARD;
        }
        return null ;
    }

    public static ChartPayment getPayment(Payment payment){
        if(payment!=null){
            return getPayment(payment.getCategory(),payment.getSubCategory());
        }
        return null;
    }

    /**
     * 当前支付方式是否为现金支付分类
     * @return
     */
    public boolean isCashCategory() {
        if (CASH == this || PREPAID == this || BANK_CARD == this ||
                WEIXIN == this || ALIPAY == this || RETURN_PREPAID == this) {
            return true;
        }
        return false;
    }
}