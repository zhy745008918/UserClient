package com.chinadream.www.userclient.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.amap.api.services.poisearch.Photo;
import com.chinadream.www.userclient.R;
import com.chinadream.www.userclient.bean.ShopBDPoiBean;
import com.chinadream.www.userclient.utils.MyImageUtils;

import java.util.List;

public class FragmentNearAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    Context context;
    List<ShopBDPoiBean> mDatas;
    List<String> footerData;
    public static final int ITEM_FOOTER=0;
    public static final int ITEM_NORMAL=1;
    public FragmentNearAdapter(Context context, List<ShopBDPoiBean> mDatas, List<String> footerData) {
        this.context=context;
        this.mDatas=mDatas;
        this.footerData=footerData;
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
    public int getItemViewType(int position) {
        if (position+1==getItemCount()){
            return ITEM_FOOTER;
        }else {
            return ITEM_NORMAL;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType==ITEM_FOOTER){
            return new NearFooterViewHolder(LayoutInflater.from(context).     //
                    inflate(R.layout.item_fragment_near_footer,parent,false));
        }else{
            return new NearShopViewHolder(LayoutInflater.from(context).       //
                    inflate(R.layout.item_shop_info_by_search,parent,false));
        }
    }


    NearShopViewHolder nearShopViewHolder;
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof NearShopViewHolder){
            nearShopViewHolder= (NearShopViewHolder) holder;
            if (mDatas==null||mDatas.size()==0) return;
            ShopBDPoiBean bean=mDatas.get(position);
            nearShopViewHolder.ivEvaluate.setImageResource(R.mipmap.starxxxx);
            List<Photo> photoList=bean.getPhotoList();
            String url="";
            if (photoList.size()>0){
                url=bean.getPhotoList().get(0).getUrl();
            }
            new MyImageUtils(context).loadImageView(nearShopViewHolder.ivShopPic,
                    url,80,80,5,R.mipmap.shopxxxx);

            nearShopViewHolder.tvEvaluateScore.setText(bean.getEvaluate()+"");
            nearShopViewHolder.tvLocation.setText("距您："+bean.getDistance()+" m");
            nearShopViewHolder.tvShopName.setText(bean.getShopTitle());
            nearShopViewHolder.tvShopType.setText(bean.getTypeDes());
            if (mOnItemClickLitener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = nearShopViewHolder.getLayoutPosition();
                        mOnItemClickLitener.onItemClick(nearShopViewHolder.itemView, pos);
                    }
                });
                holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        int pos = nearShopViewHolder.getLayoutPosition();
                        mOnItemClickLitener.onItemLongClick(nearShopViewHolder.itemView, pos);
                        return true;
                    }
                });
            }
        }else{
            NearFooterViewHolder footerViewHolder= (NearFooterViewHolder) holder;
            if (footerData.get(0)!=null&&footerData.get(0).equals("没有更多信息了")){
                footerViewHolder.pbLoadMoreInNear.setVisibility(View.INVISIBLE);
            }else {
                footerViewHolder.pbLoadMoreInNear.setVisibility(View.VISIBLE);
            }
            footerViewHolder.tvLoadMoreInNear.setText(footerData.get(0));
        }
    }


    @Override
    public int getItemCount() {

        return mDatas.size()+footerData.size();
    }


    /**
     * ViewHolder
     */
    public class NearFooterViewHolder extends RecyclerView.ViewHolder {
        public TextView tvLoadMoreInNear;
        public ProgressBar pbLoadMoreInNear;
        public NearFooterViewHolder(View itemView) {
            super(itemView);
            tvLoadMoreInNear=(TextView)itemView.findViewById(R.id.tv_for_loadmore_in_item_near_footer);
            pbLoadMoreInNear=(ProgressBar)itemView.findViewById(R.id.pb_for_loadmore_in_item_near_footer);
        }
    }

    public class NearShopViewHolder extends RecyclerView.ViewHolder {
        public TextView tvShopName, tvShopType, tvLocation, tvEvaluateScore;
        public ImageView ivShopPic, ivEvaluate;
        public NearShopViewHolder(View itemView) {
            super(itemView);
            tvShopName =(TextView)itemView.findViewById(R.id.tv_shop_name_in_item_shop);
            tvShopType =(TextView)itemView.findViewById(R.id.tv_shop_type_in_item_shop);
            tvLocation =(TextView)itemView.findViewById(R.id.tv_location_in_item_shop);
            tvEvaluateScore =(TextView)itemView.findViewById(R.id.tv_evaluate_score_in_item_shop);
            ivShopPic =(ImageView) itemView.findViewById(R.id.iv_shop_in_item_shop);
            ivEvaluate =(ImageView) itemView.findViewById(R.id.iv_evaluate_in_item_shop);
        }
    }
}
