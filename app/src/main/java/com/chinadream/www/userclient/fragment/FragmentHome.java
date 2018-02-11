package com.chinadream.www.userclient.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.chinadream.www.userclient.R;
import com.chinadream.www.userclient.adapter.FragmentHomeAdapter;
import com.chinadream.www.userclient.ui.HomeActivity;
import java.util.ArrayList;
import java.util.List;

public class FragmentHome extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private String locateString;
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    if (recyclerViewDis.getScrollState()==RecyclerView.SCROLL_STATE_IDLE
                            &&recyclerViewDis.isComputingLayout()==false)
                        adapter.notifyDataSetChanged();
                    break;
                case 0:
                    swipRefresh.setRefreshing(false);
                    break;
            }
        }
    };

    public FragmentHome() {

    }

    /**
     * 在定位成功的时候更新数据
     * @param bundle
     */
    public void updateData(Bundle bundle){
        if (bundle!=null)
        locateString= bundle.getString("location_address");
        if (adapter!=null){
            mDatas.remove(0);
            mDatas.add(0,locateString);
            mHandler.sendEmptyMessage(1);
        }
    }

    Activity activity;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.activity= getActivity();
        locateString= getArguments().getString("location_address");
    }

    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e("TAG","onCreateView");
        view=inflater.inflate(R.layout.fragment_fragment_home, container, false);
        initView(view);
        initData();
        return view;
    }

    FragmentHomeAdapter adapter;
    List<String> mDatas;
    TextView tvSearchFragment;
    LinearLayoutManager manager;
    int lastItemPosition;
    private void initData() {
        mDatas = new ArrayList<>();
        mDatas.add("正在定位中");
        for (int i = 'A'; i < 'z'; i++) {
            mDatas.add("" + (char) i);
        }
        adapter=new FragmentHomeAdapter(activity,mDatas);
//        updateData(null);
        manager=new LinearLayoutManager(activity);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewDis.setLayoutManager(manager);
        recyclerViewDis.setAdapter(adapter);
        swipRefresh.setOnRefreshListener(this);
        // 这句话是为了，第一次进入页面的时候显示加载进度条
        swipRefresh.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));
        //设置item的监听
        adapter.setOnItemClickLitener(new FragmentHomeAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(activity,""+position,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(activity,""+position+"long",Toast.LENGTH_SHORT).show();
            }
        });
        //设置recyclerview的滚动监听
        recyclerViewDis.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastItemPosition + 1 == adapter.getItemCount()) {
                    swipRefresh.setRefreshing(false);
                    // 此处在现实项目中，请换成网络请求数据代码，sendRequest .....
                    mHandler.sendEmptyMessageDelayed(0, 3000);
                }

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastItemPosition =manager.findLastVisibleItemPosition();
                View view=manager.findViewByPosition(1);
                if (view!=null){
                    if (view.getTop()<=0){
                        rlSearchFragment.setVisibility(View.VISIBLE);
                    }else{
                        rlSearchFragment.setVisibility(View.INVISIBLE);
                    }
                }
            }
        });
    }

    /**
     * 初始化组件
     */
    RecyclerView recyclerViewDis;
    RelativeLayout rlSearchFragment;
    SwipeRefreshLayout swipRefresh;
    private void initView(View v) {
        swipRefresh =(SwipeRefreshLayout)v.findViewById(R.id.swip_refresh_in_fragment_home);
        recyclerViewDis =(RecyclerView)v.findViewById(R.id.recy_list_dis_in_fragment_home);
        tvSearchFragment =(TextView)v.findViewById(R.id.tv_search_in_fragment_home);
        rlSearchFragment =(RelativeLayout)v.findViewById(R.id.rl_search_in_fragment_home);
        rlSearchFragment.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
    }


    @Override
    public void onRefresh() {
        mHandler.sendEmptyMessageDelayed(0,3000);
    }

}
