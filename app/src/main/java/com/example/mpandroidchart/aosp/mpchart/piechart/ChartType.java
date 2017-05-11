package com.example.mpandroidchart.aosp.mpchart.piechart;

import com.brady.coreframe.utils.dataprocess.StringUtils;
import com.example.mpandroidchart.R;
import com.example.mpandroidchart.aosp.mpchart.utils.ChartHelper;


/**
 * 用于报表展示的展示
 */
public enum ChartType {

    CASH(1, ChartHelper.getString(R.string.cash), R.color.color_FEB801,R.drawable.shape_rounded_square_feb801),
    PREPAID(2, ChartHelper.getString(R.string.prepaid),R.color.color_F3705E,R.drawable.shape_rounded_square_f3705e),
    BANK_CARD(3, ChartHelper.getString(R.string.bank_card),R.color.color_3D79E7,R.drawable.shape_rounded_square_3d79e7),
    WEIXIN(4, ChartHelper.getString(R.string.weixin),R.color.color_54D363,R.drawable.shape_rounded_square_54d363),
    ALIPAY(5, ChartHelper.getString(R.string.alipay),R.color.color_009FE8,R.drawable.shape_rounded_square_009fe8),
    VIPPOINT(6,  ChartHelper.getString(R.string.history_vippoint),R.color.color_1EBD9D,R.drawable.shape_rounded_square_1ebd9d),
    FREE_TICKET(9,  ChartHelper.getString(R.string.free_ticket),R.color.color_8165F6,R.drawable.shape_rounded_square_8165f6),
    PICKING_CARD(10,ChartHelper.getString(R.string.take_product_ticket),R.color.color_E96D89,R.drawable.shape_rounded_square_e96d89),
    VIPPOINT_DEDUCTION(11, ChartHelper.getString(R.string.now_vippoint),R.color.color_0BC3E7,R.drawable.shape_rounded_square_0bc3e7),
    RETURN_PREPAID(12,ChartHelper.getString(R.string.prepaid_card_refund),R.color.color_F3705E,R.drawable.shape_rounded_square_f3705e),
    RETURN_VIPPOINT(13,ChartHelper.getString(R.string.vippoint_refund),R.color.color_1EBD9D,R.drawable.shape_rounded_square_1ebd9d);


    /**类型 - code*/
    private final int type;
    /**名称*/
    private final String name;
    /**饼状图中的颜色值*/
    private final int color;
    /**底部Label的文字对应的色块颜色值*/
    private final int labelBlockColor;

    /**
     * @param type
     *  类型
     * @param name
     *  类型的具体名称
     * @param color
     *  颜色值
     */
    ChartType(int type, String name ,int color,int labelBlockColor) {
        this.type = type;
        this.name = name;
        this.color = color;
        this.labelBlockColor = labelBlockColor;
    }

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getColor() {
        return color;
    }

    public int getLabelBlockColor() {
        return labelBlockColor;
    }

    /**
     * 根据类型找出相对应的枚举
     * @param type
     * @return
     */
    public static ChartType getPaymentByType(int type){
        ChartType[] payments = values();
        if(payments != null ){
            for(ChartType p:payments){
                if(type == p.getType()){
                    return p;
                }
            }
        }
        return null ;
    }

    /**
     * 根据类型找出相对应的枚举
     * @param strType
     * @return
     */
    public static ChartType getPaymentByType(String strType){
        int type = StringUtils.parseInt(strType);
        if(type < 0 ){
            return null ;
        }
        return getPaymentByType(type);
    }
}