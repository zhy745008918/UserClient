package com.chinadream.www.userclient.listener;

import android.content.Context;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;

import java.util.HashMap;

/**
 * 用于返回百度定位信息
 * Created by QianHe on 2017/2/13.
 */
public class MyAMapLocationListener implements AMapLocationListener {
    Context context;
    public MyAMapLocationListener(Context context) {
        this.context=context;
    }
    LocationSuccessListener successListener;

    public void setSuccessListener(LocationSuccessListener successListener) {
        this.successListener = successListener;
    }


    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation.getErrorCode()==0){
            HashMap<String,Object> map=new HashMap<>();
            map.put("location_type",aMapLocation.getLocationType());//定位类型
            map.put("location_longitude",aMapLocation.getLongitude());//经度
            map.put("location_latitude",aMapLocation.getLatitude());//纬度
            map.put("location_accuracy",aMapLocation.getAccuracy());//精度
            map.put("location_provider",aMapLocation.getProvider());//提供者
            map.put("location_speed",aMapLocation.getSpeed());//速度
            map.put("location_bearing",aMapLocation.getBearing());//角度
            map.put("location_country",aMapLocation.getCountry());//国家
            map.put("location_province",aMapLocation.getProvince());//省份
            map.put("location_city",aMapLocation.getCity());//城市
            map.put("location_citycode",aMapLocation.getCityCode());//城市编码
            map.put("location_district",aMapLocation.getDistrict());//区
            map.put("location_Ad_code",aMapLocation.getAdCode());//区域码
            map.put("location_address",aMapLocation.getAddress());//地址
            map.put("location_poiname",aMapLocation.getPoiName());//兴趣点
            map.put("location_time",aMapLocation.getTime());//定位时间
            successListener.success(map);
        }else {
            StringBuffer sb = new StringBuffer();
            //定位失败
            sb.append("定位失败" + "\n");
            sb.append("错误码:" + aMapLocation.getErrorCode() + "\n");
            sb.append("错误信息:" + aMapLocation.getErrorInfo() + "\n");
            sb.append("错误描述:" + aMapLocation.getLocationDetail() + "\n");
            successListener.failure(sb.toString());

        }
    }

}

