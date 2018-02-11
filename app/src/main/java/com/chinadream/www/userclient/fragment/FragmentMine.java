package com.chinadream.www.userclient.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chinadream.www.userclient.R;
import com.chinadream.www.userclient.manger.DataCleanManager;
import com.chinadream.www.userclient.ui.AddressDisplayActivity;
import com.chinadream.www.userclient.ui.LoginActivity;
import com.chinadream.www.userclient.ui.PersonInfoActivity;
import com.chinadream.www.userclient.utils.MyImageUtils;

public class FragmentMine extends Fragment implements View.OnClickListener {

    public FragmentMine() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    ImageView ivMsgNew, ivSet,ivHeadPortrait;
    TextView tvUserName, tvUserPhone, tvAdressMine, tvClearCache;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_fragment_mine, container, false);
        initView(v);
        return v;
    }

    private void initView(View v) {
        ivHeadPortrait=(ImageView)v.findViewById(R.id.iv_head_portrait_inhome);
        ivMsgNew =(ImageView)v.findViewById(R.id.iv_msg_new_inmine);
        ivSet =(ImageView)v.findViewById(R.id.iv_set_inmine);
        tvUserName =(TextView) v.findViewById(R.id.tv_user_name_inmine);
        tvUserPhone =(TextView) v.findViewById(R.id.tv_user_phone_inmine);
        tvAdressMine =(TextView)v.findViewById(R.id.tv_adress_in_fragment_mine);
        tvClearCache =(TextView)v.findViewById(R.id.tv_clear_cache_in_fragment_mine);
        try {
            String cache=DataCleanManager.getTotalCacheSize(getActivity().getApplicationContext());
            tvClearCache.setText(cache);
        } catch (Exception e) {
            e.printStackTrace();
        }
        tvClearCache.setOnClickListener(this);
        ivHeadPortrait.setOnClickListener(this);
        tvUserName.setOnClickListener(this);
        tvUserPhone.setOnClickListener(this);
        tvAdressMine.setOnClickListener(this);
        //http://img1.3lian.com/2015/w7/98/d/22.jpg
        new MyImageUtils(getActivity()).loadImageView(ivHeadPortrait,
                "http://img1.3lian.com/2015/w7/98/d/22.jpg",66,66,33,R.mipmap.head_portrait_in_mine);
    }


    boolean isLogin;
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_head_portrait_inhome:
            case R.id.tv_user_name_inmine:
            case R.id.tv_user_phone_inmine:
                if (isLogin){
                    Intent intent=new Intent(getActivity(),PersonInfoActivity.class);
                    startActivityForResult(intent,1);
                }else{
                    Intent intent=new Intent(getActivity(),LoginActivity.class);
                    startActivityForResult(intent,1);
                }

                break;
            case R.id.tv_adress_in_fragment_mine:
                Intent in=new Intent(getActivity(), AddressDisplayActivity.class);
                startActivity(in);
                break;
            case R.id.tv_clear_cache_in_fragment_mine:
                DataCleanManager.cleanInternalCache(getActivity().getApplicationContext());
                DataCleanManager.cleanExternalCache(getActivity().getApplicationContext());
                try {
                    String cache=DataCleanManager.getTotalCacheSize(getActivity().getApplicationContext());
                    tvClearCache.setText(cache);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

}
