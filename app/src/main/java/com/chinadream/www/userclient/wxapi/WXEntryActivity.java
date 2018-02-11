package com.chinadream.www.userclient.wxapi;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.chinadream.www.userclient.R;
import com.chinadream.www.userclient.application.CDreamApplication;
import com.chinadream.www.userclient.listener.OnLoginSuccessListener;
import com.chinadream.www.userclient.manger.AppManager;
import com.chinadream.www.userclient.ui.LoginActivity;
import com.chinadream.www.userclient.utils.LoginUtil;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import org.json.JSONException;
import org.json.JSONObject;

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CDreamApplication.api.handleIntent(getIntent(), this);

    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        CDreamApplication. api.handleIntent(intent, WXEntryActivity.this);//必须调用此句话
    }


    //微信发送消息给app，app接受并处理的回调函数
    @Override
    public void onReq(BaseReq baseReq) {

    }


    ProgressDialog dialog;
    public Bundle bundle;


    @Override
    public void onResp(BaseResp arg0) {
        bundle=getIntent().getExtras();
        SendAuth.Resp resp = new SendAuth.Resp( bundle);
        //获取到code之后，需要调用接口获取到access_token
        if (resp. errCode == BaseResp.ErrCode. ERR_OK) {
            String code = resp.code;
            getToken(code);
            dialog=ProgressDialog.show(this,"","登陆中");
        } else{
            WXEntryActivity. this.finish();
        }
    }



    //这个方法会取得accesstoken  和openID
    private void getToken(String code){
        LoginUtil loginUtil=new LoginUtil();
        String url="https://api.weixin.qq.com/sns/oauth2/access_token?" +
                "appid=wx52d1780164fdd888&" +
                "secret=824ca39281b4fe06647d2a4b92dd41ff&code="+ code+
                "&grant_type=authorization_code";
        loginUtil.WeiChatRequestInfo(url, new OnLoginSuccessListener() {
            @Override
            public void onSuccess(String result) {
                Toast.makeText(WXEntryActivity.this,result,Toast.LENGTH_LONG).show();
                try {
                    JSONObject jsonObject=new JSONObject(result);
                    String token=jsonObject.optString("access_token");
                    if (token==null||"".equals(token)){
                        token=jsonObject.getString("refresh_token");
                    }
                    String openId=jsonObject.getString("openid");
                    getUserInfo(token,openId);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }
        });
    }



    //获取到token和openID之后，调用此接口得到身份信息
    private void getUserInfo(String token,String openID){
        String url="https://api.weixin.qq.com/sns/userinfo?access_token="+token+"&openid="+openID;
        new LoginUtil().WeiChatRequestInfo(url, new OnLoginSuccessListener() {
            @Override
            public void onSuccess(String result) {
                Toast.makeText(WXEntryActivity.this,result,Toast.LENGTH_LONG).show();
                WXEntryActivity.this.finish();
                AppManager.getAppManager().finishActivity(LoginActivity.class);
                dialog.dismiss();
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        dialog=null;
    }
}
