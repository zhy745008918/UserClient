package com.chinadream.www.userclient.utils;


import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.chinadream.www.userclient.listener.OnLoginSuccessListener;
import com.chinadream.www.userclient.manger.AppManager;
import com.chinadream.www.userclient.ui.LoginActivity;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.security.NoSuchAlgorithmException;

public class LoginUtil {

    public LoginUtil() {

    }


    public void WeiChatRequestInfo(String url, final OnLoginSuccessListener loginSuccessListener){
        RequestParams params=new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                loginSuccessListener.onSuccess(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                loginSuccessListener.onError(ex,isOnCallback);
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    public void SMSLogin(String url,String phoneNum){
        RequestParams params=new RequestParams(url);
        try {
            params.addParameter("key",Encrypt.md5("A_fei&Hui_yan_zhao"));
            Log.e("TAG",Encrypt.md5("A_fei&Hui_yan_zhao"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        params.addParameter("url",url);
        params.addParameter("tel",phoneNum);
        params.addParameter("method","1");
        x.http().post(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                Log.e("TAG",result);
                AppManager.getAppManager().finishActivity(LoginActivity.class);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("TAG","Throwable ex, boolean isOnCallback");

            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.e("TAG","CancelledException cex");

            }

            @Override
            public void onFinished() {
                Log.e("TAG","onFinished()");

            }
        });

    }



}
