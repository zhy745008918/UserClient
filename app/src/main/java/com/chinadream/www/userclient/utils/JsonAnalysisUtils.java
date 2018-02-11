package com.chinadream.www.userclient.utils;

import com.chinadream.www.userclient.bean.CommodityDetailsBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class JsonAnalysisUtils {
    public JsonAnalysisUtils() {
    }
    public List<CommodityDetailsBean> getCommDetailsList(String json){
        List<CommodityDetailsBean> list=new ArrayList<>();
        try {
            JSONObject jsonObject=new JSONObject(json);
            JSONArray right=jsonObject.getJSONArray("right");
            for (int i = 0; i < right.length(); i++) {
                JSONObject js= (JSONObject) right.get(i);
                /**
                 * "gid":"3","gname":"\u5eb7\u5e08\u5085\u77ff\u6cc9\u6c34","gprice":"3",
                 * "gstck":"70","gstd":"1000ml","gslg":"http:\/\/10.1.250.3:8091\/Uploads\/2017-01-19\/1.jpg"}
                 */
                String commId,commName,commPrice,commStck,commStd,commSurl;
                commId=js.getString("gid");
                commName=js.getString("gname");
                commPrice=js.getString("gprice");
                commStck=js.getString("gstck");
                commStd=js.getString("gstd");
                commSurl=js.getString("gslg");
                list.add(new CommodityDetailsBean(commId,commName,commPrice,commStck,commStd,commSurl));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

}
