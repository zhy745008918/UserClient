package com.chinadream.www.userclient.ui;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.chinadream.www.userclient.R;
import com.chinadream.www.userclient.base.BaseActivity;
import com.chinadream.www.userclient.fragment.FragmentCommodityDetails;
import com.chinadream.www.userclient.utils.DoubleOperationUtils;

/**
 * 商品详情Activity
 * */
public class CommodityDetailsActivity extends BaseActivity implements View.OnClickListener {

    /**店铺图片，购物城图片*/
    ImageView ivShop,ivTrolley;

    /**商铺名称，送达时间，营业状态，商家活动，
     * 活动个数，商品tv，跳转商铺详情，商品总金额，配送费，起送价位*/
    TextView tvShopname,tvMaxtime,tvOpenstate, tvFavorable,tvFavnum,tvCommodity,tvToShop,
            tvCommoditySum,tvSendFee, tvInitDelivery, tvTotalCommodity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commodity_details);
        initView();
        initData();
        initFragment();
    }

    double num;
    int totalCommodity;
    private void initFragment() {


        FragmentCommodityDetails fragment=new FragmentCommodityDetails();
        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.replace(R.id.ll_replace_in_commoidty_details,fragment);
        transaction.commit();

        fragment.setFragNumCallback(new FragmentCommodityDetails.FragmentNumberCallback() {
            @Override
            public void numberAddChange(int number, double price) {
                for (int i=0;i<number;i++){
                    num= DoubleOperationUtils.sum(num,price);
                }
                tvCommoditySum.setText(num+"");
                totalCommodity=totalCommodity+number;
                if (totalCommodity>0){
                    tvTotalCommodity.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void numberSubChange(int number, double price) {
                for (int i=number;i>0;i--){
                    num=DoubleOperationUtils.sub(num,price);
                }
                tvCommoditySum.setText(num+"");
                totalCommodity=totalCommodity-number;
                if (totalCommodity==0)
                    tvTotalCommodity.setVisibility(View.INVISIBLE);
                tvTotalCommodity.setText(totalCommodity+"");
            }
        });

    }


    private void initData() {
        for (int i=0;i<addSort.getChildCount();i++){

            addSort.getChildAt(i).setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    for (int j=0;j<addSort.getChildCount();j++){
                        TextView textView= (TextView) addSort.getChildAt(j);
                        if (v==addSort.getChildAt(j)){
                            textView.setSelected(true);
                            textView.setTextColor(Color.parseColor("#f09020"));
                        }else{
                            textView.setSelected(false);
                            textView.setTextColor(Color.parseColor("#404040"));
                        }
                    }
                }
            });
        }

    }



    String[] sort={"热门","牛奶乳品","饮料酒水","休闲零食","热门推荐","牛奶乳品",
            "饮料酒水","休闲零食","热门推荐、热门推荐、热门推荐","牛奶乳品",
            "饮料酒水","休闲零食","热门推荐","牛奶乳品","饮料酒水","休闲零食",};

    LinearLayout addSort;

    private void initView() {
        setMyActionBar("返回",R.mipmap.ic_launcher,null,"店铺商品详情","",R.mipmap.ic_launcher,null);
        addSort=(LinearLayout)findViewById(R.id.ll_addsort);
        addTextView(addSort);
        ivShop=(ImageView)findViewById(R.id.iv_shop_in_commodity_details);
        ivTrolley=(ImageView)findViewById(R.id.iv_trolley_in_commodity_details);
        tvShopname=(TextView)findViewById(R.id.tv_shopname_in_commodity_details);
        tvMaxtime=(TextView)findViewById(R.id.tv_maxtime_in_commodity_details);
        tvOpenstate=(TextView)findViewById(R.id.tv_openstate_in_commodity_details);
        tvFavorable=(TextView)findViewById(R.id.tv_favorable_in_commodity_details);
        tvFavnum=(TextView)findViewById(R.id.tv_favnum_in_commodity_details);
        tvCommodity=(TextView)findViewById(R.id.tv_commodity_in_commodity_details);
        tvToShop=(TextView)findViewById(R.id.tv_to_shop_in_commodity_details);
        tvCommoditySum=(TextView)findViewById(R.id.tv_commodity_sum_in_commodity_details);
        tvSendFee=(TextView)findViewById(R.id.tv_send_fee_in_commodity_details);
        tvInitDelivery =(TextView)findViewById(R.id.tv_init_delivery_in_commodity_details);
        tvTotalCommodity =(TextView)findViewById(R.id.tv_total_commodity_in_commodity_details);
        ivShop.setOnClickListener(this);
        ivTrolley.setOnClickListener(this);
        tvInitDelivery.setOnClickListener(this);
        if (totalCommodity>0){
            tvTotalCommodity.setVisibility(View.VISIBLE);
        }else{
            tvTotalCommodity.setVisibility(View.INVISIBLE);
        }
        tvTotalCommodity.setText(totalCommodity+"");

    }



    public void addTextView(final LinearLayout addSort){
        for (int i=0;i<sort.length;i++){
            final TextView tv=new TextView(this);
            tv.setBackgroundResource(R.drawable.bg_for_shopdetails_text);
            if (i==0){
                tv.setSelected(true);
                Drawable dr=this.getResources().getDrawable(R.mipmap.shopx);
                dr.setBounds(0,0,dr.getMinimumWidth(),dr.getMinimumHeight());
                tv.setCompoundDrawables(dr,null,null,null);
                tv.setTextColor(Color.parseColor("#f09020"));
            }else{
                tv.setTextColor(Color.parseColor("#404040"));
            }
            tv.setText(sort[i]);
            tv.setTextSize(18);
            tv.setPadding(30,30,30,30);
            tv.setGravity(Gravity.CENTER);
            addSort.addView(tv);
        }



    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_shop_in_commodity_details:
                Intent intent=new Intent(this,ShopQRCodeActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_trolley_in_commodity_details:
                PopupWindow popupWindow=new PopupWindow(this);
                popupWindow.setContentView(new View(this));

                break;
            case R.id.tv_init_delivery_in_commodity_details:
                Intent toSubmitActivity=new Intent(this,SubmitOrderActivity.class);
                startActivity(toSubmitActivity);
                break;
        }
    }


    /**
     * @Description: 创建动画层
     * @param
     * @return void
     * @throws
     */
    ViewGroup anim_mask_layout;
    private ViewGroup createAnimLayout() {
        ViewGroup rootView = (ViewGroup) this.getWindow().getDecorView();
        LinearLayout animLayout = new LinearLayout(this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        animLayout.setLayoutParams(lp);
        animLayout.setBackgroundResource(android.R.color.transparent);
        rootView.addView(animLayout);
        return animLayout;
    }


    private View addViewToAnimLayout(final ViewGroup parent, final View view,
                                     int[] location) {
        int x = location[0];
        int y = location[1];
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.leftMargin = x;
        lp.topMargin = y;
        view.setLayoutParams(lp);
        return view;
    }


    public void setAnim(final View v, int[] startLocation) {
        anim_mask_layout = null;
        anim_mask_layout = createAnimLayout();
        anim_mask_layout.addView(v);//把动画小球添加到动画层
        final View view = addViewToAnimLayout(anim_mask_layout, v,
                startLocation);
        int[] endLocation = new int[2];//存储动画结束位置的X、Y坐标
        ivTrolley.getLocationInWindow(endLocation);// shopCart是那个购物车

        // 计算位移
        int endX = 0 - startLocation[0] + 40;// 动画位移的X坐标
        int endY = endLocation[1] - startLocation[1];// 动画位移的y坐标
        TranslateAnimation translateAnimationX = new TranslateAnimation(0,
                endX, 0, 0);
        translateAnimationX.setInterpolator(new LinearInterpolator());
        translateAnimationX.setRepeatCount(0);// 动画重复执行的次数
        translateAnimationX.setFillAfter(true);

        TranslateAnimation translateAnimationY = new TranslateAnimation(0, 0,
                0, endY);
        translateAnimationY.setInterpolator(new AccelerateInterpolator());
        translateAnimationY.setRepeatCount(0);// 动画重复执行的次数
        translateAnimationX.setFillAfter(true);

        AnimationSet set = new AnimationSet(false);
        set.setFillAfter(false);
        set.addAnimation(translateAnimationY);
        set.addAnimation(translateAnimationX);
        set.setDuration(600);// 动画的执行时间
        view.startAnimation(set);
        // 动画监听事件
        set.setAnimationListener(new Animation.AnimationListener() {
            // 动画的开始
            @Override
            public void onAnimationStart(Animation animation) {

                v.setVisibility(View.VISIBLE);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            // 动画的结束
            @Override
            public void onAnimationEnd(Animation animation) {
                v.setVisibility(View.GONE);
                tvTotalCommodity.setText(totalCommodity+"");
            }
        });

    }

}
