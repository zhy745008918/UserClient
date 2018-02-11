package com.chinadream.www.userclient.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chinadream.www.userclient.R;
import com.chinadream.www.userclient.listener.TrolleyNumberCallback;
import com.chinadream.www.userclient.ui.CommodityDetailsActivity;
import com.chinadream.www.userclient.utils.MyImageUtils;

public class FragmentCommDetailsAdapter extends RecyclerView.Adapter<FragmentCommDetailsAdapter.CommodityViewHolder> {
    Context context;

    public FragmentCommDetailsAdapter(Context context) {
        this.context = context;
    }


    public interface OnItemClickLitener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }


    @Override
    public CommodityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new CommodityViewHolder(LayoutInflater.from(context).   //
                inflate(R.layout.item_fragment_commodity_details, parent, false));
    }


    @Override
    public void onBindViewHolder(final CommodityViewHolder holder, final int position) {
//        holder.ivCommPhoto;
        new MyImageUtils(context).loadImageView(holder.ivCommPhoto, "", 80, 80, 5, R.mipmap.shopxxx);
        if ((Integer.parseInt(holder.tvCommSelectorNum.getText().toString())) > 0) {
            holder.ivSubtraction.setSelected(true);
        } else {
            holder.ivSubtraction.setSelected(false);
        }
        holder.ivPlus.setSelected(true);
        holder.tvCommName.setText("康师傅");
        holder.tvCommSales.setText("月销量60笔");
        holder.tvCommPrice.setText("0.89");
        holder.tvCommSelectorNum.setTag(holder.getLayoutPosition());
        holder.ivPlus.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                onAddition(holder,holder.getLayoutPosition());
                int[] startLocation = new int[2];//一个整型数组，用来存储按钮的在屏幕的X、Y坐标
                v.getLocationInWindow(startLocation);//这是获取购买按钮的在屏幕的X、Y坐标（这也是动画开始的坐标）
                ImageView imageView=new ImageView(context);
                imageView.setImageResource(R.mipmap.trolley_add_anim);
                ((CommodityDetailsActivity)context).setAnim(imageView,startLocation);
            }

        });
        holder.ivSubtraction.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                onSubtraction(holder,holder.getLayoutPosition());
            }

        });

        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {


                @Override
                public boolean onLongClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemLongClick(holder.itemView, pos);
                    return true;
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        return 10;
    }


    /** * 变成灰色，表明形成不可点击状态 */
    public void noClickbg(View view) {
        view.setSelected(false);
    }


    /** * 变成黄色，表明形成可点击状态 */
    public void onClickbg(View view) {
        view.setSelected(true);
    }


    /**
     * String 转换int
     */
    public int toInt(String tostring) {
        return Integer.parseInt(tostring);
    }


    public double toDouble(String tostring){
        return Double.parseDouble(tostring);
    }


    private synchronized void onAddition(CommodityViewHolder holder,int position) {
        //得到当前购物车数量，然后加1
        int i = toInt(holder.tvCommSelectorNum.getText().toString());
        if ((int)holder.tvCommSelectorNum.getTag()==position)
            holder.tvCommSelectorNum.setText("" + (i + 1));
        onClickbg(holder.ivPlus);
        onClickbg(holder.ivSubtraction);
        trolleyNumberCallback.numberaddLoad(toInt(holder.tvCommSelectorNum.getText().toString()) - i,
                toDouble(holder.tvCommPrice.getText().toString()));
    }


    private synchronized void onSubtraction(CommodityViewHolder holder,int position) {
        //判断当前的数量，如果是不大于0则不做任何处理
        if (toInt(holder.tvCommSelectorNum.getText().toString()) > 0) {
            int i = toInt(holder.tvCommSelectorNum.getText().toString());
            if ((int)holder.tvCommSelectorNum.getTag()==position)
                holder.tvCommSelectorNum.setText("" + (i - 1));
            if ((i - 1) == 0) {
                noClickbg(holder.ivSubtraction);
            }else{
                onClickbg(holder.ivSubtraction);
            }
            if (trolleyNumberCallback != null) {
                trolleyNumberCallback.numbersubLoad(i - toInt(holder.tvCommSelectorNum.getText().toString()),
                        toDouble(holder.tvCommPrice.getText().toString()));
            }
        }

    }


    /**
     * 购物车右上角数量回调
     */
    TrolleyNumberCallback trolleyNumberCallback;

    public void setTrolleyNumberCallback(TrolleyNumberCallback trolleyNumberCallback) {
        this.trolleyNumberCallback = trolleyNumberCallback;
    }


    /**
     * ViewHolder
     */
    public class CommodityViewHolder extends RecyclerView.ViewHolder{
        public ImageView ivCommPhoto, ivSubtraction, ivPlus;
        public TextView tvCommName, tvCommSales, tvCommPrice, tvCommSelectorNum;
        public CommodityViewHolder(View convertView) {
            super(convertView);
            ivCommPhoto =(ImageView)convertView.findViewById(R.id.iv_commodity_in_item_commodity_details);
            ivSubtraction =(ImageView)convertView.findViewById(R.id.iv_subtraction_in_item_commodity_details);
            ivPlus =(ImageView)convertView.findViewById(R.id.iv_plus_in_item_commodity_details);
            tvCommName =(TextView)convertView.findViewById(R.id.tv_commodity_name_in_item_commodity_details);
            tvCommSales =(TextView)convertView.findViewById(R.id.tv_commodity_sales_in_item_commodity_details);
            tvCommPrice =(TextView)convertView.findViewById(R.id.tv_commodity_price_in_item_commodity_details);
            tvCommSelectorNum =(TextView)convertView.findViewById(R.id.tv_commodity_num_in_commodity_details);
        }
    }
}
