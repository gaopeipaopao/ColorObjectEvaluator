package com.example.gaope.colorobjectevaluator;

import android.animation.TypeEvaluator;
import android.support.annotation.IntegerRes;
import android.util.Log;

/**
 * 将开始的颜色和结束的颜色分解为3部分，开始，中间和结束；
 * 3部分都转化为int类型，范围为0到255；
 * 通过fraction得出计算的结果的3部分也是在0到255之间
 * Created by gaope on 2018/5/8.
 */

public class ColorEvaluator implements TypeEvaluator {

    private static final String TAG = "ColorEvaluator";
    private int mStart;
    private int mCenter;
    private int mEnd;


    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {
        String startColor = (String) startValue;
        String endColor = (String) endValue;
        Log.d(TAG,"aaaaa");

        int sStart = Integer.parseInt(startColor.substring(1,2),16);
        int sCenter = Integer.parseInt(startColor.substring(3,4),16);
        int sEnd = Integer.parseInt(startColor.substring(5,6),16);

        int eStart = Integer.parseInt(endColor.substring(1,2),16);
        int eCenter = Integer.parseInt(endColor.substring(3,4),16);
        int eEnd = Integer.parseInt(endColor.substring(5,6),16);

        mStart = sStart;
        mCenter = sCenter;
        mEnd = sEnd;

//        int redDiff = Math.abs(sStart - eStart);
//        int greenDiff = Math.abs(sCenter - eCenter);
//        int blueDiff = Math.abs(sEnd - eEnd);
//        int colorDiff = redDiff + greenDiff + blueDiff;



        if(sStart != eStart){
            mStart = getColor(fraction,sStart,eStart);
        }
        if (sCenter != eCenter){
            mCenter = getColor(fraction,sCenter,eCenter);
        }
        if (sEnd != eEnd){
            mEnd = getColor(fraction,sEnd,eEnd);
        }

        String color ="#" + transTenSix(mStart) + transTenSix(mCenter) + transTenSix(mEnd);
        Log.d(TAG,color);
        return color;
    }


    private int getColor(float fraction,int start,int end) {

        /**
         * start可能比end大，也可能比它小
         * start > end时最小的值不能比end小
         * start < end时最大的值不能比end大
         */
        int c = 0;
        if (start > end){
            c = start - (int) (fraction * (start - end));
            if (c < end){
                c = end;
            }
        }else {
            c = start + (int) (fraction * (end - start));
            if (c > end){
                c = end;
            }
        }

        return c;

    }

    /**
     * 转化为16进制数
     * @param mm
     * @return
     */
    private String transTenSix(int mm) {
        String hexString = Integer.toHexString(mm).toUpperCase();
        if (hexString.length() == 1){
            hexString = "E" + hexString;
        }
        return hexString;
    }

}
