package com.chinadream.www.userclient.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chinadream.www.userclient.R;

/**
 * Created by QianHe on 2017/3/16.
 */

public class SubmitOrderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_NORMAL=0;
    Context context;
    public SubmitOrderAdapter(Context context) {
        this.context=context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new SubmitOrderViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.item_submit_commodity_info,parent,false));
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return 3;
    }

    @Override
    public int getItemViewType(int position) {
        return TYPE_NORMAL;
    }

    public class SubmitOrderViewHolder extends RecyclerView.ViewHolder{

        public SubmitOrderViewHolder(View itemView) {
            super(itemView);
        }
    }
}
