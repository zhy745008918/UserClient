package com.chinadream.www.userclient.service;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.chinadream.www.userclient.application.CDreamApplication;
import com.tencent.mm.opensdk.modelmsg.SendAuth;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class LoginService extends Service {

    public LoginService() {

    }




    @Override
    public IBinder onBind(Intent intent) {
        initSMS();
        return new MyBinder();

    }




    public class MyBinder extends Binder{
        public LoginService getLoginService(){
            return LoginService.this;
        }
    }

    OnLoginListener listener;

    public void setListener(OnLoginListener listener) {
        this.listener = listener;
    }

    public interface OnLoginListener{
        void SMSSDKVerification(int code);
    }

    /**
     * 微信登录
     */

    public void getCode(){
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "carjob_wx_login";
        CDreamApplication.api.sendReq(req);
    }

    /**
     * 短信验证登录
     */
    public static final String SMS_APP_KEY="1ae17b4bf51ee";
    public static final String SMS_APP_SECRET="e7acc629fc73349cc5b8f8040fbee486";
    private void initSMS() {
        SMSSDK.initSDK(this, SMS_APP_KEY, SMS_APP_SECRET);

        final EventHandler ev=new EventHandler(){

            @Override
            public void afterEvent(int event, int result, Object data) {
                super.afterEvent(event, result, data);
                Message msg = new Message();
                msg.arg1 = event;
                msg.arg2 = result;
                msg.obj = data;
                handler.sendMessage(msg);
                Log.e("Tag",event+""+result+""+data);

            }
        };

        SMSSDK.registerEventHandler(ev);
    }

    /**
     * 获取验证码
     * @param phoneNums
     */
    public void getVerificationCode(String phoneNums) {
        SMSSDK.getVerificationCode("86", phoneNums);
    }

    /**
     * 将收到的验证码和手机号提交再次核对
     * @param phoneNums
     * @param s
     */
    public void submitVerificationCode(String phoneNums, String s) {
        SMSSDK.submitVerificationCode("86", phoneNums,s);
    }

    /**
     * 提交短信验证后成功失败识别码
     */
    public static final int SUBMIT_VERIFICATION_CODE_OK=0;
    public static final int SUBMIT_VERIFICATION_CODE_ERROR=1;

    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            int event = msg.arg1;
            int result = msg.arg2;
            Object data = msg.obj;

            if (result == SMSSDK.RESULT_COMPLETE) {

                // 短信注册成功后，返回MainActivity,然后提示
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {

                    // 提交验证码成功
                    Toast.makeText(getApplicationContext(), "提交验证码成功",
                            Toast.LENGTH_SHORT).show();

                    listener.SMSSDKVerification(SUBMIT_VERIFICATION_CODE_OK);
//                    Log.e("Tag","提交验证码成功");

                } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {//获取验证码

                    if(result == SMSSDK.RESULT_COMPLETE) {
                        boolean smart = (Boolean)data;
                        if(smart) {

                            //通过智能验证
                            Toast.makeText(getApplicationContext(), "智能验证",
                                    Toast.LENGTH_SHORT).show();
//                            Log.e("Tag","智能验证");

                        } else {

                            //依然走短信验证
                            Toast.makeText(getApplicationContext(), "正在获取验证码",
                                    Toast.LENGTH_SHORT).show();
//                            Log.e("Tag","正在获取验证码");

                        }
                    }
                }else {

                    ((Throwable) data).printStackTrace();

                    //验证失败
                    Toast.makeText(getApplicationContext(), "验证失败",
                            Toast.LENGTH_SHORT).show();

                    listener.SMSSDKVerification(SUBMIT_VERIFICATION_CODE_ERROR);

                }
            }
        }

    };

    @Override
    public void unbindService(ServiceConnection conn) {
        super.unbindService(conn);
        SMSSDK.unregisterAllEventHandler();
    }
}
