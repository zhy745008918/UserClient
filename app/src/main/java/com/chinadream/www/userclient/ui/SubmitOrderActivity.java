package com.chinadream.www.userclient.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chinadream.www.userclient.R;
import com.chinadream.www.userclient.adapter.SubmitOrderAdapter;
import com.chinadream.www.userclient.base.BaseActivity;

public class SubmitOrderActivity extends BaseActivity implements View.OnClickListener {
    /**
     * 等待买家付款，订单状态，待支付，待接单，
     * 待送达，倒计时，收货人，收货人电话，
     * 收货人地址，送达时间，商店名称，商品总价，
     * 优惠金额，配送费，实际付款，商家电话，
     * 取消订单，提交订单
     */
    TextView tvWaitingPay, tvStatusAlert, tvNoPayment, tvWaitingList,
            tvToBeServed, tvCountDown, tvConsignee, tvConsigneeTel,
            tvConsigneeAddress, tvTransportTime, tvShopName, tvCommoditySum,
            tvFavorable, tvDeliveryFee, tvActualPayment, tvShopTel,
            tvCancelOrder, tvPayOrder;
    TextView[] textViews={tvWaitingPay, tvStatusAlert, tvNoPayment, tvWaitingList,
            tvToBeServed, tvCountDown, tvConsignee, tvConsigneeTel,
            tvConsigneeAddress, tvTransportTime, tvShopName, tvCommoditySum,
            tvFavorable, tvDeliveryFee, tvActualPayment, tvShopTel,
            tvCancelOrder, tvPayOrder};
    int[] textViewId={R.id.tv_waiting_pay_in_submit_order,R.id.tv_status_alert_in_submit_order,
            R.id.tv_no_payment_in_submit_order,R.id.tv_waiting_list_in_submit_order,
            R.id.tv_to_be_served_in_submit_order,R.id.tv_count_down_in_submit_order,
            R.id.tv_consignee_in_submit_order,R.id.tv_consignee_tel_in_submit_order,
            R.id.tv_consignee_address_in_submit_order, R.id.tv_transport_time_in_submit_order,
            R.id.tv_shopname_in_submit_order, R.id.tv_commodity_sum_in_submit_order,
            R.id.tv_favorable_in_submit_order, R.id.tv_delivery_fee_in_submit_order,
            R.id.tv_actual_payment_in_submit_order, R.id.tv_shop_tel_in_submit_order,
            R.id.tv_cancel_order_in_submit_order,
            R.id.tv_pay_order_in_submit_order};
    RecyclerView recyCommodity;
    ImageView ivShop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_order);
        initView();
        initData();
    }

    SubmitOrderAdapter submitAdapter;
    private void initData() {
        submitAdapter=new SubmitOrderAdapter(this);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyCommodity.setLayoutManager(manager);
        recyCommodity.setAdapter(submitAdapter);
    }

    private void initView() {
        setMyActionBar("",R.mipmap.ic_launcher,null,"确认订单","",NON_IMAGEVIEW,null);
        for (int i = 0; i < textViewId.length; i++) {

            textViews[i]=(TextView)findViewById(textViewId[i]);

        }
        textViews[16].setOnClickListener(this);
        textViews[17].setSelected(true);
        textViews[17].setOnClickListener(this);
        textViews[2].setSelected(true);
        textViews[6].setOnClickListener(this);
        textViews[7].setOnClickListener(this);
        textViews[8].setOnClickListener(this);
        textViews[6].setText("收货人：" +
                "刘辉");
        textViews[7].setText("电话：" +
                "18730183255");
        textViews[8].setText("收货地址：" +
                "石家庄裕华区万达广场万达写字楼B座706" +
                "石家庄裕华区万达广场万达写字楼B座706" +
                "石家庄裕华区万达广场万达写字楼B座706" +
                "石家庄裕华区万达广场万达写字楼B座706");
        recyCommodity =(RecyclerView)findViewById(R.id.recy_commodity_in_submit_order);
        ivShop =(ImageView)findViewById(R.id.iv_shop_in_submit_order);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //点击更换收货地址
            case R.id.tv_consignee_in_submit_order:
            case R.id.tv_consignee_tel_in_submit_order:
            case R.id.tv_consignee_address_in_submit_order:

                break;
        }

    }
}
