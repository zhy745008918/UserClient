package com.chinadream.www.userclient.ui;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;

import com.chinadream.www.userclient.R;
import com.chinadream.www.userclient.base.BaseActivity;
import com.chinadream.www.userclient.service.LoginService;
import com.chinadream.www.userclient.utils.JudgePohneUtil;
import com.chinadream.www.userclient.utils.LoginUtil;

import android.os.CountDownTimer;
import android.os.IBinder;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class LoginActivity extends BaseActivity implements View.OnClickListener, TextWatcher {

    EditText loginBySMS, verifyForSMS;
    Button btnSendSMS, btnLogin;
    TextView  tvWeiXin, tvQQ, tvWeiBo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setMyActionBar("",R.mipmap.ic_launcher,null,"登录","",NON_IMAGEVIEW,null);
        initView();
        initService();

    }

    /**
     * 初始化 登录服务
     */
    ConnectionService connectionService;
    LoginService loginService;
    private void initService() {
        connectionService=new ConnectionService();
        Intent intent=new Intent(this,LoginService.class);
        bindService(intent,connectionService, Context.BIND_AUTO_CREATE);


    }

    /**
     * 10.1.250.3：8091/ht.php/Tobemember/checkinfo
     * 登录接口
     */
    class ConnectionService implements ServiceConnection{

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            loginService=((LoginService.MyBinder)service).getLoginService();
            loginService.setListener(new LoginService.OnLoginListener() {


                @Override
                public void SMSSDKVerification(int code) {
                    switch (code){
                        case LoginService.SUBMIT_VERIFICATION_CODE_OK:
                            //处理短信验证成功后的事情
                            LoginUtil loginUtil=new LoginUtil();
                            loginUtil.SMSLogin("http://10.1.250.3:8091/khd.php/Tobemember/checkinfo","18730183299");
                            break;
                        case LoginService.SUBMIT_VERIFICATION_CODE_ERROR:
                            //处理短信验证失败后的事情

                            break;
                    }
                }
            });
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }


    /**
     * 初始化组件
     */
    private void initView() {

        loginBySMS =(EditText)findViewById(R.id.et_sms_login_in_login);
        verifyForSMS =(EditText)findViewById(R.id.et_sms_verify_in_login);
        btnSendSMS =(Button)findViewById(R.id.btn_send_sms_in_login);
        btnLogin =(Button)findViewById(R.id.btn_login_in_login);
        tvWeiXin =(TextView)findViewById(R.id.tv_weixin_login_in_login);
        tvQQ =(TextView)findViewById(R.id.tv_qq_login_in_login);
        tvWeiBo =(TextView)findViewById(R.id.tv_weibo_login_in_login);

        btnSendSMS.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        tvWeiXin.setOnClickListener(this);
        tvQQ.setOnClickListener(this);
        tvWeiBo.setOnClickListener(this);
        loginBySMS.addTextChangedListener(this);
        verifyForSMS.addTextChangedListener(this);
        btnLogin.setEnabled(false);
        btnSendSMS.setEnabled(false);

    }

    //初始化计时器
    private CountDownTimer timer = new CountDownTimer(60000, 1000) {

        @Override
        public void onTick(long millisUntilFinished) {

            btnSendSMS.setText((millisUntilFinished / 1000) + "秒后可重发");

        }


        @Override
        public void onFinish() {

            btnSendSMS.setEnabled(true);
            btnSendSMS.setText("获取验证码");

        }
    };



    String phoneNums;
    Thread thread;
    boolean isRun;
    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btn_send_sms_in_login:

                phoneNums=loginBySMS.getText().toString();
                if (!JudgePohneUtil.judgePhoneNums(phoneNums)) {
                    Toast.makeText(this, "手机号码输入有误！",Toast.LENGTH_SHORT).show();
                    return;
                }
                //获取验证码
                loginService.getVerificationCode(phoneNums);

                btnSendSMS.setEnabled(false);
                btnLogin.setEnabled(true);
                timer.start();

                break;


            case R.id.btn_login_in_login:

                //将收到的验证码和手机号提交再次核对
                loginService.submitVerificationCode(phoneNums, verifyForSMS.getText().toString());

                break;

            case R.id.tv_weixin_login_in_login:
                //微信登录
                loginService.getCode();

                break;

            case R.id.tv_qq_login_in_login:


                break;

            case R.id.tv_weibo_login_in_login:


                break;
        }
    }





    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {


    }



    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        if (s.length()==11){

            btnSendSMS.setEnabled(true);
        }else{

            btnSendSMS.setEnabled(false);
        }

    }


    @Override
    public void afterTextChanged(Editable s) {


    }


    @Override
    protected void onDestroy() {

        super.onDestroy();
        unbindService(connectionService);
        isRun=false;
        if (thread!=null)
        thread.interrupt();
        thread=null;
    }
}

