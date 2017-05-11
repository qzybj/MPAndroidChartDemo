package com.example.mpandroidchart.utils;

import com.brady.coreframe.utils.dataprocess.StringUtils;
import java.math.BigDecimal;


/**
 * Created by Clair
 *
 * @date 2017/5/10
 * @description
 */
public class ParseUtils {
    /**
     * 将bigDecimal转成字符串
     * @param bigDecimal
     * @return
     */
    public static String parseDecimal(BigDecimal bigDecimal){
        if( bigDecimal == null ||
                NumberUtil.eq(bigDecimal,BigDecimal.ZERO)){
            return "0";
        }

        return bigDecimal.stripTrailingZeros().toPlainString();
    }

    /**
     * 将字符串专程BigDecimal并乘以-1，然后转成字符串
     * @param bigDecimal
     * @return
     */
    public static String parseNegateDecimal(String bigDecimal){
        BigDecimal value = parseDecimal(bigDecimal);
        return parseNegateDecimal(value);
    }

    /**
     * 将bigDecimal*-1，并转成字符串
     * @param bigDecimal
     * @return
     */
    public static String parseNegateDecimal(BigDecimal bigDecimal){
        if( bigDecimal == null ||
                NumberUtil.eq(bigDecimal,BigDecimal.ZERO)){
            return "0";
        }

        return parseDecimal(NumberUtil.negate(bigDecimal));
    }

    /**
     * 将bigDecimal转成字符串
     * @param bigDecimal
     * @return
     */
    public static String parseDecimal(BigDecimal bigDecimal, int maxdecimals){
        if( bigDecimal == null ||
                NumberUtil.eq(bigDecimal,BigDecimal.ZERO)){
            return "0";
        }
        if(bigDecimal.scale() > maxdecimals){
            BigDecimal newValue = bigDecimal.setScale(maxdecimals,BigDecimal.ROUND_DOWN);
            return newValue.stripTrailingZeros().toPlainString();
        }
        return bigDecimal.stripTrailingZeros().toPlainString();
    }

    /**
     * 将float中的小数点去掉
     * @param value
     * @return
     */
    public static String parseFloat(float value){
        BigDecimal newValue = new BigDecimal(value+"");
        return newValue + "";
    }

    /**
     * 将字符串转成bigDecimal
     * @param value
     * @return
     */
    public static BigDecimal parseDecimal(String value){
        try{
            return new BigDecimal(value);
        }catch (Exception e){

        }
        return null;
    }

    /**
     * 将字符串转成整型，转换失败返回-1
     * @param value
     * @return
     */
    public static boolean parseBoolean(String value){
        try{
            return Boolean.parseBoolean(StringUtils.format(value));
        }catch (Exception e){

        }
        return false;
    }

    /**
     * 将字符串转成整型，转换失败返回-1
     * @param value
     * @return
     */
    public static int parseInt(String value){
        try{
            return Integer.parseInt(StringUtils.format(value));
        }catch (Exception e){

        }
        return -1;
    }

    /**
     * 将字符串转成整型，转换失败返回-1
     * @param value
     * @return
     */
    public static float parseFloat(String value){
        try{
            return Float.parseFloat(StringUtils.format(value));
        }catch (Exception e){

        }
        return -1;
    }

    /**
     * 将字符串转成double
     * @param value
     * @return
     */
    public static double parseDouble(String value){
        try{
            return Double.parseDouble(StringUtils.format(value));
        }catch (Exception e){

        }
        return 0;
    }

    /**
     * 将字符串转成long
     * @param value
     * @return
     */
    public static long parseLong(String value){
        try{
            return Long.parseLong(StringUtils.format(value));
        }catch (Exception e){

        }
        return 0;
    }
}