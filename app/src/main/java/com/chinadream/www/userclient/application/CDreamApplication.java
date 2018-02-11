package com.chinadream.www.userclient.application;

import android.app.Application;
import android.widget.Toast;

import com.amap.api.location.AMapLocationClient;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.xutils.BuildConfig;
import org.xutils.x;



public class CDreamApplication extends Application{
    private String locateStr;
    public static IWXAPI api;
    public AMapLocationClient client;
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);
        registerToWx();
        client=new AMapLocationClient(getApplicationContext());
    }

    /**
     * 初始化微信api
     */
    private void registerToWx() {
        if (api == null) {
            api = WXAPIFactory.createWXAPI(this, "wx52d1780164fdd888", false);
        }
        if (!api.isWXAppInstalled()) {
            //提醒用户没有按照微信
            Toast.makeText(this,"请安装微信", Toast.LENGTH_SHORT).show();
            return;
        }
        api.registerApp("wx52d1780164fdd888");
    }

    public String getLocateStr() {
        return locateStr;
    }

    public void setLocateStr(String locateStr) {
        this.locateStr = locateStr;
    }
}
