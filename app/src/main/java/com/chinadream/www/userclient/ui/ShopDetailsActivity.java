package com.chinadream.www.userclient.ui;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chinadream.www.userclient.R;
import com.chinadream.www.userclient.base.BaseActivity;
import com.chinadream.www.userclient.view.MyCustomDialog;

public class ShopDetailsActivity extends BaseActivity implements View.OnClickListener {
    /**商铺图片列表*/
    LinearLayout shopIvList, shopRecommend;
    TextView tv_telphone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_details);
        initView();
    }

    private void initView() {
        setMyActionBar("商家", R.mipmap.ic_launcher, null, "", "", R.mipmap.ic_launcher, null);
        shopIvList = (LinearLayout) findViewById(R.id.ll_shopiv_list);
        addShopIv(shopIvList);
        shopRecommend = (LinearLayout) findViewById(R.id.ll_shop_recommend);
        addShopRecommend(shopRecommend);
        tv_telphone = (TextView) findViewById(R.id.tv_telphone);
        tv_telphone.setOnClickListener(this);
    }

    /**
     * 添加商铺推荐商品的图片及信息
     * @param shopRecommend
     */
    private void addShopRecommend(LinearLayout shopRecommend) {
        for (int i = 0; i < 7; i++) {
            LinearLayout ll = new LinearLayout(this);
            ll.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            ImageView iv = new ImageView(this);
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.shopxxxx);
            iv.setImageBitmap(bitmap);
            ll.addView(iv, params);
            TextView tv = new TextView(this);
            tv.setText("杂粮煎饼");
            tv.setSingleLine();
            tv.setGravity(Gravity.CENTER);
            ll.addView(tv, params);
            shopRecommend.addView(ll);
        }
    }

    /**
     * 添加商铺图片（百度获取的图片）
     * @param shopIvList
     */
    private void addShopIv(LinearLayout shopIvList) {
        for (int i = 0; i < 7; i++) {
            ImageView iv = new ImageView(this);
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.shopxxxx);
            iv.setImageBitmap(bitmap);
            iv.setRight(5);
            shopIvList.addView(iv);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_telphone:
                //拨打商家电话对话框
                MyCustomDialog.Builder builder = new MyCustomDialog.Builder(this);
                builder.setTitle("18730183255").setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).setPositiveButton("呼叫", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //拨打电话
                        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "18730183255"));
                        if (ActivityCompat.checkSelfPermission(ShopDetailsActivity.this,
                                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            return;
                        }
                        ShopDetailsActivity.this.startActivity(intent);
                        dialog.dismiss();

                    }
                }).create().show();
                break;
        }
    }
}
