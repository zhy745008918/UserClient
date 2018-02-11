package com.chinadream.www.userclient.utils;

import android.content.Context;

/**
 * Created by QianHe on 2017/3/16.
 */

public class DisplayDeminsUtil {

    /**
     * 将px值转换成dp值，保证尺寸大小不变
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2dp(Context context,float pxValue){
        final float scale=context.getResources().getDisplayMetrics().density;
        return (int)(pxValue/scale+0.5f);
    }


    /**
     * 将dp值转换成px值，保证尺寸大小不变
     * @param context
     * @param dpValue
     * @return
     */
    public static int dp2px(Context context,float dpValue){
        final float scale=context.getResources().getDisplayMetrics().density;
        return (int)(dpValue*scale+0.5f);
    }

    /**
     * 将px值转换成sp值，保证字体大小不变
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2sp(Context context,float pxValue){
        final float fontScale=context.getResources().getDisplayMetrics().scaledDensity;
        return (int)(pxValue/fontScale+0.5f);
    }

    /**
     * 将sp值转换成px值，保证字体大小不变
     * @param context
     * @param spValue
     * @return
     */
    public static int sp2px(Context context,float spValue){
        final float fontScale=context.getResources().getDisplayMetrics().scaledDensity;
        return (int)(fontScale*spValue+0.5f);

    }

    /**
     * 获取屏幕宽度
     * @param context
     * @return
     */
    public static int getScreenWith(Context context){

        return 0;
    }

    public static int getScreenHeight(Context context){

        return 0;
    }
}
