package com.chinadream.www.userclient.utils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;


public class XutilsRequestHttpUtils {

    public XutilsRequestHttpUtils() {

    }
    public void requestCommDetails(String url){
        RequestParams params=new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                requestBackListener.requestOnSuccess(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                requestBackListener.requestOnFailure(ex+"\n"+isOnCallback);
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    public void setRequestBackListener(OnRequestBackListener requestBackListener) {
        this.requestBackListener = requestBackListener;
    }

    OnRequestBackListener requestBackListener;
    public interface OnRequestBackListener{
        void requestOnSuccess(String request);
        void requestOnFailure(String error);
    }
}

