package com.chinadream.www.userclient.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chinadream.www.userclient.R;
import com.chinadream.www.userclient.base.BaseActivity;
import com.chinadream.www.userclient.view.MyCustomDialog;

public class CommodityDisplayActivity extends BaseActivity {
    /**商品图片（256以上），商品规格选择图片，购物车*/
    ImageView ivCommodityIndis,ivNormsIndis,ivTrolleyIndis;
    /** 商品名称，月销量，单品价格，商店名称，商品规格，商品详情，总计金额，配送费，起送价格*/
    TextView tvCommodityName,tvSaleCount,tvSingleComm,
            tvShopName,tvBrand,tvCommodityDetails,
            tvCommoditySum,tvSendFee,tvInitSum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commodity_display);
        initView();
    }

    private void initView() {

        setMyActionBar("返回",R.mipmap.ic_launcher,null,"","",R.mipmap.ic_launcher,null);
        ivCommodityIndis=(ImageView)findViewById(R.id.iv_commodity_indis);
        ivNormsIndis=(ImageView)findViewById(R.id.iv_norms_indis);
        ivTrolleyIndis=(ImageView)findViewById(R.id.iv_trolley_indis);
        tvCommodityName=(TextView)findViewById(R.id.tv_commodity_name_indis);
        tvSaleCount=(TextView)findViewById(R.id.tv_salecount_indis);
        tvSingleComm=(TextView)findViewById(R.id.tv_singlecomm_indis);
        tvShopName=(TextView)findViewById(R.id.tv_shopname_indis);
        tvBrand=(TextView)findViewById(R.id.tv_brand_indis);
        tvCommodityDetails=(TextView)findViewById(R.id.tv_commodity_details_indis);
        tvCommoditySum=(TextView)findViewById(R.id.tv_commodity_sum_indis);
        tvSendFee=(TextView)findViewById(R.id.tv_send_fee_indis);
        tvInitSum=(TextView)findViewById(R.id.tv_init_sum_indis);

        ivCommodityIndis.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                        new MyCustomDialog.Builder(CommodityDisplayActivity.this)
                                .setTitle("请确认收货信息")
                                .setMessage("刘辉\n" +
                                        "18731083255\n" +
                                        "石家庄万达广场B座")
                                .setPositiveButton("是", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                }).setNegativeButton("否", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).create().show();
                    }
                });

    }


}
