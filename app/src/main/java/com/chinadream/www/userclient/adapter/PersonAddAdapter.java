package com.chinadream.www.userclient.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.chinadream.www.userclient.base.adapter.BaseAdapter2;
import com.chinadream.www.userclient.bean.CommodityDetailsBean;

public class PersonAddAdapter extends BaseAdapter2<CommodityDetailsBean>{
    Context context;
    public PersonAddAdapter(Context context) {
        super(context);
        this.context=context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        return null;
    }
}
