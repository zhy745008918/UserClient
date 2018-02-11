package com.chinadream.www.userclient.base;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Toast;

import com.chinadream.www.userclient.R;
import com.chinadream.www.userclient.manger.AppManager;
import com.chinadream.www.userclient.utils.ActionBarBuilder;

import java.util.ArrayList;


public class BaseActivity extends FragmentActivity {


    public static final int NON_IMAGEVIEW=-1;
    private final int SDK_PERMISSION_REQUEST = 127;
    private String permissionInfo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        AppManager.getAppManager().addActivity(this);
        getPersimmions();
    }



    private void getPersimmions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ArrayList<String> permissions = new ArrayList<String>();
            /***
             * 定位权限为必须权限，用户如果禁止，则每次进入都会申请
             */
            // 定位精确位置
            if(checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
            }
            if(checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);
            }
			/*
			 * 读写权限和电话状态权限非必要权限(建议授予)只会申请一次，用户同意或者禁止，只会弹一次
			 */
            // 读写权限
            if (addPermission(permissions, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                permissionInfo += "Manifest.permission.WRITE_EXTERNAL_STORAGE Deny \n";
            }
            // 读取电话状态权限
            if (addPermission(permissions, Manifest.permission.READ_PHONE_STATE)) {
                permissionInfo += "Manifest.permission.READ_PHONE_STATE Deny \n";
            }

            if (permissions.size() > 0) {
                requestPermissions(permissions.toArray(new String[permissions.size()]), SDK_PERMISSION_REQUEST);
            }
        }
    }



    @TargetApi(23)
    private boolean addPermission(ArrayList<String> permissionsList, String permission) {
        if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) { // 如果应用没有获得对应权限,则添加到列表中,准备批量申请
            if (shouldShowRequestPermissionRationale(permission)){
                return true;
            }else{
                permissionsList.add(permission);
                return false;
            }

        }else{
            return true;
        }
    }



    @TargetApi(23)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        // TODO Auto-generated method stub
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }



    /**
     * 设置标题栏
     * @param leftStr 左侧文字
     * @param leftImg 左侧图片 如果没有请设置 NON_IMAGEVIEW
     * @param leftListener 左侧监听
     * @param midStr 中间文字
     * @param rightStr 右侧文字
     * @param rightImg 右侧图片 如果没有请设置 NON_IMAGEVIEW
     * @param rightListener 右侧监听
     */
    public void setMyActionBar(String leftStr, int leftImg, View.OnClickListener leftListener,
                               String midStr,
                               String rightStr, int rightImg, View.OnClickListener rightListener){

        ActionBarBuilder builder=new ActionBarBuilder(this);

        if (("".equals(leftStr)||leftStr==null)&&leftImg==NON_IMAGEVIEW&&leftListener==null){
            builder.setLeftGone();
        }else if(leftImg==NON_IMAGEVIEW){
            builder.setLeftText(leftStr).setLeftClickListener(leftListener);
        }else {
            builder.setLeftText(leftStr).setLeftImage(leftImg)
                    .setLeftClickListener(leftListener);
        }

        if (("".equals(rightStr)||rightStr==null)&&rightImg==NON_IMAGEVIEW&&rightListener==null){
            builder.setRightGone();
        }else if(rightImg==NON_IMAGEVIEW){
            builder.setRightText(rightStr).setRightClickListener(rightListener);
        } else {
            builder.setRightText(rightStr).setRightImage(rightImg).
                    setRightClickListener(rightListener);
        }

        if ("".equals(midStr)||midStr==null){
            builder.setMidGone();
        }else{
            builder.setMidText(midStr);
        }
    }




}

