package com.chinadream.www.userclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chinadream.www.userclient.R;
import com.chinadream.www.userclient.base.adapter.BaseAdapter2;
import com.chinadream.www.userclient.bean.AddressInfoBean;

public class AddressDisplayAdapter extends BaseAdapter2<AddressInfoBean> {
    Context context;
    public AddressDisplayAdapter(Context context) {
        super(context);
        this.context=context;
    }
    View.OnClickListener listener;

    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.item_address_display,null);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        AddressInfoBean bean=getItem(position);
        holder.tvAddressItem.setText(bean.getAddress());
        holder.tvUserItem.setText(bean.getLinkman()+" "+bean.getGender()+"    "+bean.getPhoneNum());
        holder.ivAddressModification.setTag(position);
        holder.ivAddressModification.setOnClickListener(listener);
        return convertView;
    }
    public class ViewHolder {
        ImageView ivAddressModification;
        TextView tvUserItem, tvAddressItem;
        public ViewHolder(View convertView) {
            ivAddressModification =(ImageView)convertView.           //
                    findViewById(R.id.iv_address_modification_in_address_item);
            tvAddressItem =(TextView)convertView.findViewById(R.id.tv_address_info_in_address_item);
            tvUserItem =(TextView)convertView.findViewById(R.id.tv_user_info_in_address_item);
        }



    }
}
