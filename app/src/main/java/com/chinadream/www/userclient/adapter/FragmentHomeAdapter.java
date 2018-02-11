package com.chinadream.www.userclient.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.chinadream.www.userclient.R;
import java.util.List;


public class FragmentHomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    List<String> mDatas;
    public static final int ITEM_LOCATE=0;
    public static final int ITEM_SEARCH=1;
    public static final int ITEM_NORMAL=2;

    public List<String> getmDatas() {
        return mDatas;
    }


    public void setmDatas(List<String> mDatas) {
        this.mDatas = mDatas;
    }


    public FragmentHomeAdapter(Context context, List<String> mDatas) {
        this.context=context;
        this.mDatas=mDatas;
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
        if (viewType==ITEM_LOCATE){
            return new HomeLocateViewHolder(LayoutInflater.from(
                    context).inflate(R.layout.item_fragment_home_locate, parent,
                    false));
        }else if(viewType==ITEM_SEARCH){
            return new HomeSearchViewHolder(LayoutInflater.from(
                    context).inflate(R.layout.item_fragment_home_search, parent,
                    false));
        }else{
            return new HomeMainViewHolder(LayoutInflater.from(
                    context).inflate(R.layout.item_fragment_home_main, parent,
                    false));
        }
    }


    @Override
    public int getItemViewType(int position) {
        if(position==0){
            return ITEM_LOCATE;
        }else if (position==1){
            return ITEM_SEARCH;
        }else{
            return ITEM_NORMAL;
        }
    }


    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HomeLocateViewHolder){
            HomeLocateViewHolder locateViewHolder= (HomeLocateViewHolder) holder;
            locateViewHolder.tvLocateItem.setText(mDatas.get(position));
            locateViewHolder.tvLocateItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, mDatas.get(0),Toast.LENGTH_SHORT).show();
                }
            });

        }else if(holder instanceof HomeSearchViewHolder){
            HomeSearchViewHolder searchViewHolder=(HomeSearchViewHolder) holder;
        }else if (holder instanceof HomeMainViewHolder){
            HomeMainViewHolder mainViewHolder= (HomeMainViewHolder) holder;
            (mainViewHolder.tvTest).setText(mDatas.get(position));
            // 如果设置了回调，则设置点击事件
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
    }


    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    /**
     *  ViewHolder
     */
    public class HomeSearchViewHolder extends RecyclerView.ViewHolder{
        public TextView tvSearchItem;
        public HomeSearchViewHolder(View itemView) {
            super(itemView);
            tvSearchItem =(TextView)itemView.findViewById(R.id.tv_search_in_item_home_search);
        }
    }
    public class HomeMainViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTest;
        public HomeMainViewHolder(View view) {
            super(view);
            tvTest =(TextView)view.findViewById(R.id.tv_data_in_item_home_main);
        }
    }
    public class HomeLocateViewHolder extends RecyclerView.ViewHolder{
        public TextView tvLocateItem;
        public HomeLocateViewHolder(View itemView) {
            super(itemView);
            tvLocateItem =(TextView)itemView.findViewById(R.id.tv_locate_in_item_home_locate);

        }
    }




}


