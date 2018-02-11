package com.chinadream.www.userclient.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.Photo;
import com.amap.api.services.poisearch.PoiSearch;
import com.chinadream.www.userclient.R;
import com.chinadream.www.userclient.adapter.FragmentNearAdapter;
import com.chinadream.www.userclient.bean.ShopBDPoiBean;
import com.chinadream.www.userclient.listener.AFExchangeDatasListener;
import com.chinadream.www.userclient.listener.MyPoiSearchListener;
import com.chinadream.www.userclient.listener.OnGetPoiResultListener;
import com.chinadream.www.userclient.ui.CommodityDetailsActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FragmentNear extends Fragment implements View.OnClickListener{

    Activity activity;

    public FragmentNear() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity=getActivity();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_fragment_near, container, false);
        initView(view);
        initData();
        return view;
    }




    /**
     * 初始化组件
     */
    private TextView tvAllShop, tvCooperate, tvAllClassify, tvAllCity, tvLocateInfo;
    private ImageView ivSearch, ivRefresh;
    private RecyclerView recyclerShopDis;
    private void initView(View view) {
        tvAllShop =(TextView)view.findViewById(R.id.tv_allshop_in_fragment_near);
        tvCooperate =(TextView)view.findViewById(R.id.tv_cooperate_in_fragment_near);
        tvAllClassify =(TextView)view.findViewById(R.id.tv_all_classify_in_fragment_near);
        tvAllCity =(TextView)view.findViewById(R.id.tv_allcity_in_fragment_near);
        tvLocateInfo =(TextView)view.findViewById(R.id.tv_locate_info_in_fragment_near);
        ivSearch =(ImageView)view.findViewById(R.id.iv_search_in_fragment_near);
        ivRefresh =(ImageView)view.findViewById(R.id.iv_refresh_in_fragment_near);
        recyclerShopDis =(RecyclerView)view.findViewById(R.id.recy_shop_dis_in_fragment_near);
        tvAllShop.setSelected(true);
        tvCooperate.setSelected(false);
        tvAllShop.setOnClickListener(this);
        tvCooperate.setOnClickListener(this);
        ivRefresh.setOnClickListener(this);
    }



    private FragmentNearAdapter adapter;
    private LinearLayoutManager manager;
    private void initData() {
        footerData=new ArrayList<>();
        mDatas=new ArrayList<>();
        footerData.add("加载中...");
        adapter=new FragmentNearAdapter(activity,mDatas,footerData);
        manager=new LinearLayoutManager(activity);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerShopDis.setLayoutManager(manager);
        recyclerShopDis.setAdapter(adapter);
        recyclerShopDis.setOnScrollListener(new RecyclerView.OnScrollListener() {


            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }


            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastItemVisiblePosition=manager.findLastVisibleItemPosition();
                if (lastItemVisiblePosition+1==adapter.getItemCount()){
                    if (!isLoading){
                        isLoading=true;
                        initNearByShop();
                    }
                }

            }
        });



        adapter.setOnItemClickLitener(new FragmentNearAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent=new Intent(activity, CommodityDetailsActivity.class);
                activity.startActivity(intent);
            }
            @Override
            public void onItemLongClick(View view, int position) {

            }
        });

    }



    private boolean isLoading=true;
    private String locateStr;
    private int searchPoiPage;
    double latitude;
    double longitude;
    String cityCode;
    public void updateData(Bundle bundle){
        locateStr= bundle.getString("location_address");
        latitude= bundle.getDouble("location_latitude");
        longitude= bundle.getDouble("location_longitude");
        cityCode=bundle.getString("location_citycode");

    }

    @Override
    public void onResume() {
        super.onResume();
        //第一次加载数据
        if (mDatas!=null&&mDatas.size()>0) return;
            initNearByShop();
    }

    /**
     * 获取附近O店信息
     */
    private List<ShopBDPoiBean> mDatas;
    private List<String> footerData;
    int totalPage;
    public void initNearByShop() {
        tvLocateInfo.setText("当前位置："+locateStr);
        PoiSearch.Query query=new PoiSearch.Query("便利店","",cityCode);
        query.setPageNum(searchPoiPage);
        query.setPageSize(10);
        MyPoiSearchListener myListener=new MyPoiSearchListener();
        PoiSearch poiSearch=new PoiSearch(activity,query);
        PoiSearch.SearchBound bound=new PoiSearch.SearchBound(new LatLonPoint(latitude,longitude),2000);
        poiSearch.setBound(bound);
        poiSearch.setOnPoiSearchListener(myListener);
        myListener.setListener(resultListener);
        poiSearch.searchPOIAsyn();
    }

    /**
     * 获取poi信息的监听
     */
    OnGetPoiResultListener resultListener=new OnGetPoiResultListener() {

        @Override
        public void getPoiSearched(HashMap<String, Object> map) {
            if (map==null) {
                Toast.makeText(activity,"未搜索到结果",Toast.LENGTH_SHORT).show();
                return;
            }
            List<PoiItem> list=(List<PoiItem>)map.get("poi_pois");
            totalPage=(int)map.get("poi_page_count");
            searchPoiPage++;

            for (int i = 0; i < list.size(); i++) {
                PoiItem item=list.get(i);
                int distance=item.getDistance();
                List<Photo> photoList=item.getPhotos();
                String poiId=item.getPoiId();
                String telNum=item.getTel();
                String shopTitle=item.getTitle();
                String typeCode=item.getTypeCode();
                LatLonPoint latLonPoi=item.getLatLonPoint();
                String address=item.getSnippet();
                String typeDes=item.getTypeDes();
                String shopUrl=item.getWebsite();
                String evaluate=item.getPoiExtension().getmRating();
                String openTime=item.getPoiExtension().getOpentime();
                String cityName=item.getCityName();
                String email=item.getEmail();
                mDatas.add(new ShopBDPoiBean(distance,photoList,poiId,telNum,shopTitle,
                        typeCode,latLonPoi,address,typeDes,shopUrl,evaluate,openTime,cityName,email));
            }
            mHandler.sendEmptyMessage(1);
        }

        @Override
        public void getPoiFailured() {
            Toast.makeText(activity,"未搜索到结果",Toast.LENGTH_SHORT).show();
        }


    };


    Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    isLoading=false;
                    if (searchPoiPage==totalPage){
                        footerData.set(0,"没有更多信息了");
                    }
                    adapter.notifyDataSetChanged();
                    break;
            }
        }
    };




    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_allshop_in_fragment_near:
                tvAllShop.setSelected(true);
                tvCooperate.setSelected(false);
                break;
            case R.id.tv_cooperate_in_fragment_near:
                tvAllShop.setSelected(false);
                tvCooperate.setSelected(true);
                break;
            case R.id.iv_refresh_in_fragment_near:
                //刷新时初始化数据
                nearListener.intentData(R.id.iv_refresh_in_fragment_near,this);
                tvLocateInfo.setText("当前位置：");
                isLoading=true;
                searchPoiPage=0;
                footerData.set(0,"加载中...");
                mDatas.clear();
                adapter.notifyDataSetChanged();
                break;
        }
    }




    @Override
    public void onDestroy() {
        super.onDestroy();

    }


    /**
     * fragment和activity传递数据的方法
     */
    AFExchangeDatasListener nearListener;

    public void setNearListener(AFExchangeDatasListener nearListener) {
        this.nearListener = nearListener;
    }
}
