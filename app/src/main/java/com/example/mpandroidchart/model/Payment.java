package com.example.mpandroidchart.model;


import com.brady.coreframe.utils.dataprocess.StringUtils;

/**
 * 支付方式类型
 */

public enum Payment {
    CASH(1, "现金", "现金","A0",""),
    PREPAID(2, "预付费卡", "预付费卡","D0",""),
    BANK_CARD(3, "银行卡", "银行卡","C0","2"),
    WEIXIN(4, "微信支付","微信","D1","44"),
    ALIPAY(5, "支付宝支付", "支付宝","D1","36"),
    VIPPOINT(6, "积分支付", "积分","E0","13"),
    FREE_TICKET(9, "赠券", "赠券","E0",""),  //赠券和提货券的大小支付方式由券核销接口决定
    PICKING_CARD(10, "提货券", "提货券","D1",""),
    //退款相关
    RETURN_PREPAID(7, "预付费卡退款", PREPAID.getAliasName(),PREPAID.getCategory(),PREPAID.getSubCategory()),
    RETURN_VIPPOINT(8, "积分退款",VIPPOINT.getAliasName(), VIPPOINT.getCategory(),VIPPOINT.getSubCategory());  //E0-15指积分现抵，E0-13指积分支付


    private final int type;
    //支付方式名称
    private final String name;
    //支付方式别名
    private final String aliasName ;
    //大支付方式
    private final String category;
    //小支付方式
    private final String subCategory;


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
    Payment(int type, String name, String aliasName, String category, String subCategory) {
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
    public static Payment getPaymentByType(int type){
        Payment[] payments = values();
        if(payments != null ){
            for(Payment p:payments){
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
    public static Payment getPaymentByType(String strType){
        int type = StringUtils.parseInt(strType);
        if(type < 0 ){
            return null ;
        }
        Payment[] payments = values();
        if(payments != null ){
            for(Payment p:payments){
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
    public static Payment getPayment(boolean isSale, String category, String subCategory){
        Payment payment = getPayment(category,subCategory);
        if( payment != null ){
            if(isSale){
                //销售
                if( payment == Payment.RETURN_PREPAID){
                    payment = Payment.PREPAID;
                }else if(payment == Payment.RETURN_VIPPOINT){
                    payment = Payment.VIPPOINT;
                }
            }else {
                //退货
                if( payment == Payment.PREPAID){
                    payment = Payment.RETURN_PREPAID;
                }else if(payment == Payment.VIPPOINT){
                    payment = Payment.RETURN_VIPPOINT;
                }
            }

        }
        return payment;
    }

    /**
     * 根据大小支付方式获取{@link Payment}对象
     * @param category
     * @param subCategory
     * @return
     */
    public static Payment getPayment(String category, String subCategory){
        Payment[] payments = Payment.values();
        for(Payment p: payments){
            if( p.getCategory().equals(category) &&
                    p.getSubCategory().equals(subCategory)){
                return p;
            }
        }
        if(Payment.VIPPOINT.getCategory().equals(category) &&
                "15".equals(subCategory)){
            //积分现抵
            return Payment.VIPPOINT;
        }
        //当匹配不上，则是券
        if(Payment.FREE_TICKET.getCategory().equals(category)){
            return Payment.FREE_TICKET;
        }else if(Payment.PICKING_CARD.getCategory().equals(category)){
            return Payment.PICKING_CARD;
        }
        return null ;
    }

    /**
     * 是否为历史积分支付 E0-15指积分现抵，E0-13指历史积分支付
     * @param category
     * @param subCategory
     * @return
     */
    public static boolean isHistoryVipPoint(String category, String subCategory ){
        if("E0".equalsIgnoreCase(category)){
            if("13".equalsIgnoreCase(subCategory)){
                return true ;
            }
        }
        return false ;
    }
}