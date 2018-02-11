package com.chinadream.www.userclient.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chinadream.www.userclient.R;

import java.util.List;

public class FragmentOederAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    List<String> list;

    public FragmentOederAdapter(Activity activity, List<String> list) {
        context=activity;
        this.list=list;
    }



    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
        void onItemLongClick(View view , int position);
    }


    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new OrderViewHolder(LayoutInflater.from(context).            //
                inflate(R.layout.item_fragment_order_list,parent,false));
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final OrderViewHolder orderViewHolder=(OrderViewHolder)holder;
        orderViewHolder.tvAlsoReserve.setSelected(false);
        orderViewHolder.tvCancelOrder.setSelected(false);
        orderViewHolder.tvPayOrder.setSelected(true);
        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = orderViewHolder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(orderViewHolder.itemView, pos);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = orderViewHolder.getLayoutPosition();
                    mOnItemClickLitener.onItemLongClick(orderViewHolder.itemView, pos);
                    return true;
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    public class OrderViewHolder extends RecyclerView.ViewHolder{

        ImageView ivShop;
        TextView tvName, tvPayState, tvPayTime, tvCommodityNum, tvTotalSum, tvCancelOrder,
                tvAlsoReserve, tvPayOrder;
        public OrderViewHolder(View itemView) {
            super(itemView);
            ivShop =(ImageView)itemView.findViewById(R.id.iv_shop_in_item_fragment_order) ;
            tvName =(TextView)itemView.findViewById(R.id.tv_name_in_item_fragment_order);
            tvPayState =(TextView)itemView.findViewById(R.id.tv_pay_state_in_item_fragment_order);
            tvPayTime =(TextView)itemView.findViewById(R.id.tv_pay_time_in_item_fragment_order);
            tvCommodityNum =(TextView)itemView.findViewById(R.id.tv_commodity_num_in_item_fragment_order);
            tvTotalSum =(TextView)itemView.findViewById(R.id.tv_commodity_total_sum_in_item_fragment_order);
            tvCancelOrder =(TextView)itemView.findViewById(R.id.tv_cancel_order_in_item_fragment_order);
            tvAlsoReserve =(TextView)itemView.findViewById(R.id.tv_also_reserve_shop_in_item_fragment_order);
            tvPayOrder =(TextView)itemView.findViewById(R.id.tv_pay_order_in_item_fragment_order);
        }
    }

    public class FooterViewHolder extends RecyclerView.ViewHolder{

        public FooterViewHolder(View itemView) {
            super(itemView);
        }
    }
}
