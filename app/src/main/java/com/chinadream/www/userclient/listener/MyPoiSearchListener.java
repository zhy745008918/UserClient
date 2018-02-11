package com.chinadream.www.userclient.listener;


import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;

import java.util.HashMap;

public class MyPoiSearchListener implements PoiSearch.OnPoiSearchListener {


    OnGetPoiResultListener listener;

    public void setListener(OnGetPoiResultListener listener) {
        this.listener = listener;
    }

    @Override
    public void onPoiSearched(PoiResult poiResult, int i) {
        if (i==1000){
            HashMap<String,Object> map=new HashMap<>();
            map.put("poi_page_count",poiResult.getPageCount());
            map.put("poi_pois",poiResult.getPois());
            listener.getPoiSearched(map);
        }else {
            listener.getPoiFailured();
        }
    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {

    }
}
