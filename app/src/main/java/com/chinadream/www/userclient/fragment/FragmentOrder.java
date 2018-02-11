package com.chinadream.www.userclient.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chinadream.www.userclient.R;
import com.chinadream.www.userclient.adapter.FragmentOederAdapter;

import java.util.ArrayList;
import java.util.List;

public class FragmentOrder extends Fragment {

    public FragmentOrder() {

    }

    Activity activity;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity=getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_fragment_order, container, false);
        initView(view);
        initData();
        return view;
    }


    FragmentOederAdapter adapter;
    List<String> list;
    LinearLayoutManager manger;
    /**
     * 初始化数据
     */
    private void initData() {

        list=new ArrayList<>();
        for (int i = 0; i <10; i++) {
            list.add("a");
        }
        adapter=new FragmentOederAdapter(activity,list);
        manger=new LinearLayoutManager(activity);
        manger.setOrientation(LinearLayoutManager.VERTICAL);
        recyOederList.setLayoutManager(manger);
        recyOederList.setAdapter(adapter);
        adapter.setOnItemClickLitener(new FragmentOederAdapter.OnItemClickLitener() {

            @Override
            public void onItemClick(View view, int position) {

            }


            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
    }


    RecyclerView recyOederList;
    /**
     * 初始化布局
     * @param view
     */
    private void initView(View view) {
        recyOederList =(RecyclerView)view.findViewById(R.id.recy_order_list_in_fragment_order);
    }


}
