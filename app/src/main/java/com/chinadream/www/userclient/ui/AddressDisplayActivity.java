package com.chinadream.www.userclient.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.chinadream.www.userclient.R;
import com.chinadream.www.userclient.adapter.AddressDisplayAdapter;
import com.chinadream.www.userclient.base.BaseActivity;
import com.chinadream.www.userclient.bean.AddressInfoBean;

import java.util.ArrayList;
import java.util.List;

public class AddressDisplayActivity extends BaseActivity implements View.OnClickListener,
        AdapterView.OnItemLongClickListener {


    TextView tvAddNewAddress, tvAddressExist;
    ListView lvAddressDisplay;
    List<AddressInfoBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_adress_display);

        initView();

        initData();
    }


    AddressDisplayAdapter addresAdapter;

    private void initData() {

        list=new ArrayList<>();
        addresAdapter=new AddressDisplayAdapter(this);
        list.add(new AddressInfoBean("Jack","150****3299","先生","石家庄长安区万达广场B座703"));
        list.add(new AddressInfoBean("Jvm","150****3299","先生","石家庄长安区万达广场B座703"));
        if(list.size()==0){

            lvAddressDisplay.setVisibility(View.GONE);
            tvAddressExist.setVisibility(View.VISIBLE);
        }else{

            lvAddressDisplay.setVisibility(View.VISIBLE);
            tvAddressExist.setVisibility(View.GONE);
            addresAdapter.addAllList(list);
            lvAddressDisplay.setAdapter(addresAdapter);
        }

        addresAdapter.setListener(listener);
    }


    View.OnClickListener listener=new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Intent intent;
            Bundle bundle;
            switch (v.getId()){
                case R.id.tv_add_new_address_in_address_display:
                    intent=new Intent(AddressDisplayActivity.this,AddressEditActivity.class);
                    bundle=new Bundle();
                    bundle.putInt("viewId",v.getId());
                    intent.putExtras(bundle);
                    startActivityForResult(intent,10);
                    break;
                case  R.id.iv_address_modification_in_address_item:
                    int posotion= (int) v.getTag();
                    intent=new Intent(AddressDisplayActivity.this,AddressEditActivity.class);
                    bundle=new Bundle();
                    AddressInfoBean bean=addresAdapter.getItem(posotion);
                    bundle.putInt("viewId",v.getId());
                    bundle.putString("linkman",bean.getLinkman());
                    bundle.putString("gender",bean.getGender());
                    bundle.putString("phoneNum",bean.getPhoneNum());
                    bundle.putString("address",bean.getAddress());
                    intent.putExtras(bundle);
                    startActivityForResult(intent,0);
                    break;
            }

        }
    };



    private void initView() {
        setMyActionBar("收货地址",R.mipmap.ic_launcher,null,"","",NON_IMAGEVIEW,null);
        tvAddNewAddress=(TextView)findViewById(R.id.tv_add_new_address_in_address_display);
        tvAddressExist =(TextView)findViewById(R.id.tv_whether_address_in_address_display);
        lvAddressDisplay =(ListView)findViewById(R.id.lv_address_in_address_display);
        tvAddNewAddress.setOnClickListener(listener);
        lvAddressDisplay.setOnItemLongClickListener(this);
    }



    @Override
    public void onClick(View v) {

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }


    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("删除地址").setMessage("确定删除该收货地址么？").setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                addresAdapter.removeSingleData(position);
                if (addresAdapter.getCount()==0){
                    lvAddressDisplay.setVisibility(View.GONE);
                    tvAddressExist.setVisibility(View.VISIBLE);
                }else{
                    lvAddressDisplay.setVisibility(View.VISIBLE);
                    tvAddressExist.setVisibility(View.GONE);
                    addresAdapter.notifyDataSetChanged();
                }
            }
        }).setNegativeButton("取消",null).show();
        return false;
    }
}
