package com.chinadream.www.userclient.ui;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocationClient;
import com.chinadream.www.userclient.R;
import com.chinadream.www.userclient.application.CDreamApplication;
import com.chinadream.www.userclient.base.BaseActivity;
import com.chinadream.www.userclient.fragment.FragmentHome;
import com.chinadream.www.userclient.fragment.FragmentMine;
import com.chinadream.www.userclient.fragment.FragmentNear;
import com.chinadream.www.userclient.fragment.FragmentOrder;
import com.chinadream.www.userclient.listener.AFExchangeDatasListener;
import com.chinadream.www.userclient.listener.LocationSuccessListener;
import com.chinadream.www.userclient.listener.MyAMapLocationListener;
import com.chinadream.www.userclient.utils.AMLocationUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HomeActivity extends BaseActivity implements View.OnClickListener,
        AFExchangeDatasListener {



    TextView tvMsgMine, tvBottomMine, tvBottomOrder,
            tvBottomNear, tvMsgHome, tvBottomHome;
    TextView[] tvArray= new TextView[]{tvBottomHome, tvBottomNear, tvBottomOrder, tvBottomMine};
    int[] idArray=new int[]{R.id.tv_bottom_home_inhome,R.id.tv_bottom_near_inhome,
            R.id.tv_bottom_order_inhome,R.id.tv_bottom_mine_inhome};
    LinearLayout llBottomHome, llReplaceHome;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        setDefaultFragment(savedInstanceState);
        initData();
    }




    AMapLocationClient client;
    AMLocationUtils locationUtils;
    @Override
    protected void onStart() {
        super.onStart();
        initLocation();
    }

    /**
     * 初始化定位
     */
    private void initLocation(){
        locationUtils=new AMLocationUtils();
        client=((CDreamApplication)getApplication()).client;
        client.setLocationOption(locationUtils.getDeraultLocationOption());
        client.setLocationListener(myAMListener);
        client.startLocation();
    }


    @Override
    protected void onStop() {
        super.onStop();
        client.stopLocation();
        client.unRegisterLocationListener(myAMListener);
    }




    private Fragment currentFragment = new Fragment();
    private List<Fragment> fragments = new ArrayList<>();
    private FragmentManager fragmentManager;
    private int currentIndex = 0;
    //当前显示的fragment
    private static final String CURRENT_FRAGMENT = "STATE_FRAGMENT_SHOW";
    /**
     * 设置默认显示fragment
     */
    private void setDefaultFragment(Bundle savedInstanceState) {
        fragmentManager = getFragmentManager();
        if (savedInstanceState != null) { // “内存重启”时调用

            //获取“内存重启”时保存的索引下标
            currentIndex = savedInstanceState.getInt(CURRENT_FRAGMENT,0);
            //注意，添加顺序要跟下面添加的顺序一样！！！！
            fragments.removeAll(fragments);
            fragments.add(fragmentManager.findFragmentByTag(0+""));
            fragments.add(fragmentManager.findFragmentByTag(1+""));
            fragments.add(fragmentManager.findFragmentByTag(2+""));
            fragments.add(fragmentManager.findFragmentByTag(3+""));
            //恢复fragment页面
            restoreFragment();
        }else{
            //正常启动时调用

            fragments.add(new FragmentHome());
            fragments.add(new FragmentNear());
            fragments.add(new FragmentOrder());
            fragments.add(new FragmentMine());
            showFragment();
            Bundle bundle=new Bundle();
            bundle.putString("location_address",str);
            fragments.get(0).setArguments(bundle);
            ((FragmentNear)fragments.get(1)).setNearListener(this);
        }
    }


    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        //“内存重启”时保存当前的fragment名字
        outState.putInt(CURRENT_FRAGMENT,currentIndex);
        super.onSaveInstanceState(outState, outPersistentState);
    }


    /**
     * 使用show() hide()切换页面
     * 显示fragment
     */
    private void showFragment(){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        //如果之前没有添加过
        if(!fragments.get(currentIndex).isAdded()){
            transaction.hide(currentFragment)
                    .add(R.id.ll_replace_in_home,fragments.get(currentIndex),""+currentIndex);
                                                    //第三个参数为添加当前的fragment时绑定一个tag
        }else{
            transaction.hide(currentFragment).show(fragments.get(currentIndex));
        }
        currentFragment = fragments.get(currentIndex);
        transaction.commit();
    }


    /**
     * 恢复fragment
     */
    private void restoreFragment(){
        FragmentTransaction mBeginTreansaction = fragmentManager.beginTransaction();
        for (int i = 0; i < fragments.size(); i++) {
            if(i == currentIndex){
                mBeginTreansaction.show(fragments.get(i));
            }else{
                mBeginTreansaction.hide(fragments.get(i));
            }
        }
        mBeginTreansaction.commit();
        //把当前显示的fragment记录下来
        currentFragment = fragments.get(currentIndex);
    }



    MyAMapLocationListener myAMListener =new MyAMapLocationListener(this);
    String str="正在定位中";
    Bundle bundle;
    /**
     * 初始化数据
     */
    private void initData() {

        myAMListener.setSuccessListener(new LocationSuccessListener() {
            @Override
            public void success(HashMap<String,Object> map) {
                str= (String) map.get("location_address");
                double latitude= (double) map.get("location_latitude");
                double longitude= (double) map.get("location_longitude");
                String cityCode=(String)map.get("location_citycode");
                bundle=new Bundle();
                bundle.putString("location_address",str);
                bundle.putDouble("location_latitude",latitude);
                bundle.putDouble("location_longitude",longitude);
                bundle.putString("location_citycode",cityCode);
                ((FragmentHome)fragments.get(0)).updateData(bundle);
                if (currentIndex==1){
                    ((FragmentNear)fragments.get(1)).updateData(bundle);
                    ((FragmentNear)fragments.get(1)).initNearByShop();
                }
            }
            @Override
            public void failure(String failureStr) {

            }
        });

    }



    /**
     * 初始化组件
     */
    private void initView() {
        llReplaceHome =(LinearLayout)findViewById(R.id.ll_replace_in_home);
        llBottomHome =(LinearLayout)findViewById(R.id.ll_bottom_in_home);
        tvMsgHome =(TextView)findViewById(R.id.tv_msg_home_inhome);
        tvMsgMine =(TextView)findViewById(R.id.tv_msg_mine_inhome);
        tvMsgMine.setVisibility(View.VISIBLE);
        for (int i=0;i<tvArray.length;i++){
            tvArray[i]=(TextView)findViewById(idArray[i]);
            tvArray[i].setOnClickListener(this);
            if (i==0) tvArray[i].setSelected(true);
        }
    }





    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_bottom_home_inhome:
                currentIndex=0;
                break;
            case R.id.tv_bottom_near_inhome:
                ((FragmentNear)fragments.get(1)).updateData(bundle);
                currentIndex=1;
                break;
            case R.id.tv_bottom_order_inhome:
                currentIndex=2;
                break;
            case R.id.tv_bottom_mine_inhome:
                currentIndex=3;
                break;
        }
        setTvSeclector(currentIndex);
        showFragment();
    }




    /**
     * 设置底部导航栏的选中状态
     * @param index
     */
    private void setTvSeclector(int index){
        for (int i=0;i<idArray.length;i++){
            if(index==i){
                tvArray[i].setSelected(true);
            }else{
                tvArray[i].setSelected(false);
            }
        }
    }


    private long mPressedTime = 0;
    @Override
    public void onBackPressed() {
        long mNowTime = System.currentTimeMillis();//获取第一次按键时间
        if((mNowTime - mPressedTime) > 2000){//比较两次按键时间差
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            mPressedTime = mNowTime;
        } else{//退出程序
            finish();
        }
    }


    /**
     * fragment向activity中传递数据
     * @param viewId
     * @param fragment
     */
    @Override
    public void intentData(int viewId, Fragment fragment) {
        if (fragment==fragments.get(1)){
            switch (viewId){
                case R.id.iv_refresh_in_fragment_near:
                    initLocation();
                    break;
            }
        }
    }
}
