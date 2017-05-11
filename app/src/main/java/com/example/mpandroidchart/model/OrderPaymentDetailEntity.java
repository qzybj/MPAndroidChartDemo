package com.example.mpandroidchart.model;


import com.example.mpandroidchart.config.OrderConstant;
import java.math.BigDecimal;

/**
 *
 * @author Cuckoo
 * @date 2017-03-06
 * @description
 *      订单的支付相关信息
 */
public class OrderPaymentDetailEntity {
    private long id ;
    //PaymentType	int	N	支付类型	PaymentType的Type字段
    private int paymentType ;
    //receiptCode	String	Y	小票号
    private String receiptCode ;
    //traceNo	String	N	交易凭证号
    private String traceNo ;
    //PaymentAmount	decimal	N	支付金额
    private BigDecimal paymentAmount ;
    //MachineNo	varchar	N	收银机编号
    private String machineNo ;
    //ShoppeNo	varchar	N	专柜号
    private String shoppeNo ;
    //StoreNo	varchar	N	门店号
    private String storeNo ;
    //UserID	varchar	N	员工编号
    private String userID ;
    //OrderType	int	N	订单类型	"0：销售,1：退货"
    private int saleType ;
    //dealType	int	N	交易类型	"0：普通交易 1：纯收银"
    private int dealType = 0;
    //CreateTime	long	N	添加时间
    private long createTime ;
    //orderDate	String	N	交易日期
    private String orderDate;
    //orderTime	String	N	交易时间
    private String orderTime;
    //payCategory	String	N	支付大类
    private String payCategory;
    //paySubCategory	String	N	支付小类
    private String paySubCategory;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(int paymentType) {
        this.paymentType = paymentType;
    }

    public String getReceiptCode() {
        return receiptCode;
    }

    public void setReceiptCode(String receiptCode) {
        this.receiptCode = receiptCode;
    }

    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getMachineNo() {
        return machineNo;
    }

    public void setMachineNo(String machineNo) {
        this.machineNo = machineNo;
    }

    public String getShoppeNo() {
        return shoppeNo;
    }

    public void setShoppeNo(String shoppeNo) {
        this.shoppeNo = shoppeNo;
    }

    public String getStoreNo() {
        return storeNo;
    }

    public void setStoreNo(String storeNo) {
        this.storeNo = storeNo;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getTraceNo() {
        return traceNo;
    }

    public void setTraceNo(String traceNo) {
        this.traceNo = traceNo;
    }

    /**
     *  类型是否为销售。true：销售 false：退货
     * @return
     */
    public boolean isSale() {
        return saleType == OrderConstant.SALETYPE_SALE ;
    }

    /**
     * 设置订单类型
     * @param isSale
     *  true：销售 false：退货
     */
    public void setSaleType(boolean isSale) {
        this.saleType = isSale ?
                OrderConstant.SALETYPE_SALE :
                OrderConstant.SALETYPE_RETURN;
    }

    /**
     * 设置订单类型
     * @param isJustCash
     *  true：纯收银 false：普通收银
     */
    public void setJustCash(boolean isJustCash) {
        this.dealType = isJustCash ? 1 :0;
    }


    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getPayCategory() {
        return payCategory;
    }

    public void setPayCategory(String payCategory) {
        this.payCategory = payCategory;
    }

    public String getPaySubCategory() {
        return paySubCategory;
    }

    public void setPaySubCategory(String paySubCategory) {
        this.paySubCategory = paySubCategory;
    }
}
