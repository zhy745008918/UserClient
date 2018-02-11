package com.chinadream.www.userclient.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.chinadream.www.userclient.adapter.FragmentCommDetailsAdapter;
import com.chinadream.www.userclient.adapter.FragmentCommDetailsAdapter.OnItemClickLitener;
import com.chinadream.www.userclient.R;
import com.chinadream.www.userclient.bean.CommodityDetailsBean;
import com.chinadream.www.userclient.listener.TrolleyNumberCallback;
import com.chinadream.www.userclient.ui.CommodityDisplayActivity;

import java.util.List;

public class FragmentCommodityDetails extends Fragment {

    public FragmentCommodityDetails() {

    }

    Activity activity;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity=getActivity();
    }
    RecyclerView recyclerInCommFrag;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_commodity_details, container, false);
        recyclerInCommFrag =(RecyclerView)view.findViewById(R.id.recycler_in_fragment_commodity_details);
        initData();
        return view;
    }

    double num;
    FragmentCommDetailsAdapter adapter;
    List<CommodityDetailsBean> list;
    String url="http://10.1.250.3:8091/index.php?m=Customer&c=Eshop&a=index";
    private void initData() {
        LinearLayoutManager manager=new LinearLayoutManager(activity);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerInCommFrag.setLayoutManager(manager);
        adapter=new FragmentCommDetailsAdapter(activity);
        recyclerInCommFrag.setAdapter(adapter);
        adapter.setTrolleyNumberCallback(new TrolleyNumberCallback() {
            @Override
            public void numberaddLoad(int number, double price) {
                num=num+(number*price);
                fragNumCallback.numberAddChange(number,price);
            }

            @Override
            public void numbersubLoad(int number, double price) {
                fragNumCallback.numberSubChange(number,price);
            }
        });
        adapter.setOnItemClickLitener(new OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent=new Intent(activity, CommodityDisplayActivity.class);
                activity.startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
    }

    public void setFragNumCallback(FragmentNumberCallback fragNumCallback) {
        this.fragNumCallback = fragNumCallback;
    }

    FragmentNumberCallback fragNumCallback;
    public interface FragmentNumberCallback{
        void numberAddChange(int number, double price);
        void numberSubChange(int number, double price);
    }




}
