package com.chinadream.www.userclient.listener;

import java.util.HashMap;

/**
 * Created by QianHe on 2017/2/16.
 */

public interface LocationSuccessListener {
    void success(HashMap<String,Object> map);
    void failure(String failureStr);
}
