package com.chinadream.www.userclient.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.chinadream.www.userclient.R;
import com.chinadream.www.userclient.base.BaseActivity;
import com.chinadream.www.userclient.utils.MyImageUtils;
import com.chinadream.www.userclient.view.MyDIYPopupWindow;


public class PersonInfoActivity extends BaseActivity implements View.OnClickListener {

    /**用户头像用户头像设置*/
    ImageView ivUserHeadInfo;
    TextView tvChangeName, tvBindingPhone, tvBindingWeichat, tvBindingQQ, tvBindingWeibo,
            tvSetLoginPass, tvSetPayPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_info);
        initView();
    }


    private void initView() {
        setMyActionBar("返回",R.mipmap.ic_launcher,null,"个人信息","",NON_IMAGEVIEW,null);
        ivUserHeadInfo =(ImageView) findViewById(R.id.iv_user_portrait_in_person_info);//头像
        tvChangeName =(TextView)findViewById(R.id.tv_change_name_in_person_info) ;//用户名
        tvBindingPhone =(TextView)findViewById(R.id.tv_binding_phone_in_person_info) ;//绑定手机
        tvBindingWeichat =(TextView)findViewById(R.id.tv_binding_weichat_in_person_info);//绑定微信
        tvBindingQQ =(TextView)findViewById(R.id.tv_binding_QQ_in_person_info) ;//绑定qq
        tvBindingWeibo =(TextView)findViewById(R.id.tv_binding_weibo_in_person_info) ;//绑定微博
        tvSetLoginPass =(TextView)findViewById(R.id.tv_set_login_pass_in_person_info) ;//设置登录密码
        tvSetPayPass =(TextView)findViewById(R.id.tv_set_pay_pass_in_person_info) ;//设置支付密码
        tvChangeName.setSelected(true);
        tvBindingPhone.setSelected(true);
        tvBindingWeichat.setSelected(true);
        tvBindingQQ.setSelected(true);
        tvBindingWeibo.setSelected(true);
        tvSetLoginPass.setSelected(true);
        tvSetPayPass.setSelected(true);
        tvChangeName.setOnClickListener(this);
        tvBindingPhone.setOnClickListener(this);
        tvBindingWeichat.setOnClickListener(this);
        tvBindingQQ.setOnClickListener(this);
        tvBindingWeibo.setOnClickListener(this);
        tvSetLoginPass.setOnClickListener(this);
        tvSetPayPass.setOnClickListener(this);
        ivUserHeadInfo.setOnClickListener(this);
        new MyImageUtils(this).loadImageView(ivUserHeadInfo,"",60,60,30,R.mipmap.shopxxx);
    }

    MyDIYPopupWindow popWindow;
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //头像行
            case R.id.iv_user_portrait_in_person_info:
                popWindow=new MyDIYPopupWindow(this,LayoutInflater.from(this).
                        inflate(R.layout.popup_change_portrait,null),listener);
                popWindow.initBackLayout();
                popWindow.showAtLocation(findViewById(R.id.activity_person_info),
                    Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
                break;

        }
    }


    /**
     * popupwindow的按键监听
     */
    View.OnClickListener listener=new View.OnClickListener() {

        @Override
        public void onClick(View v) {
                switch (v.getId()){
                case R.id.btn_camera_inchange:
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(new String[]{Manifest.permission.CAMERA,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                    } else {
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent, 1);
                    }
                    popWindow.dismiss();
                    popWindow=null;
                    break;
                case R.id.btn_photo_inchange:
                    Intent getAlbum = null;
                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
                        getAlbum = new Intent(Intent.ACTION_GET_CONTENT);
                        getAlbum.setType("image/*");
                    } else {
                        getAlbum = new Intent(Intent.ACTION_PICK,
                                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    }
                    startActivityForResult(getAlbum, 2);
                    popWindow.dismiss();
                    popWindow=null;
                    break;
            }
        }
    };


    /**
     * 相机的动态权限设置权限*/
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 1);
            } else {
                Toast.makeText(this, "请在应用管理中打开“相机”访问权限！", Toast.LENGTH_LONG).show();
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data==null||resultCode==0){
            ivUserHeadInfo.setImageResource(R.mipmap.shopxxx);
            return;
        }
        if (requestCode==3){

            return;
        }
        MyImageUtils image=new MyImageUtils(this);
        String filePath="";
        if (requestCode==1){
            filePath=image.initCameraData(data);
        }else if (requestCode==2){
            filePath=image.initAlbumData(data);
        }
        image.loadImageView(ivUserHeadInfo,filePath,60,60,30,R.mipmap.shopxxx);
    }


}
