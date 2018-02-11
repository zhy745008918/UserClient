package com.chinadream.www.userclient.listener;

import java.util.HashMap;

/**
 * Created by QianHe on 2017/2/21.
 */

public interface OnGetPoiResultListener {
    void getPoiSearched(HashMap<String,Object> map);
    void getPoiFailured();
}
