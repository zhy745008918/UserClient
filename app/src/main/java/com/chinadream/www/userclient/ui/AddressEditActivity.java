package com.chinadream.www.userclient.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.chinadream.www.userclient.R;
import com.chinadream.www.userclient.base.BaseActivity;


public class AddressEditActivity extends BaseActivity {

    EditText personName, houseNumber, phoneNumber, verifyCode;
    RadioGroup radioGroup;
    RadioButton radioBtnMan, radioBtnWeman;
    TextView tvLocation;
    Button btnRegisterVerify, btnSaveAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_edit);
        initView();
        initData();

    }

    private void initData() {
        Bundle bundle=getIntent().getExtras();
        switch (bundle.getInt("viewId")){
            case R.id.iv_address_modification_in_address_item:
                personName.setText(bundle.getString("linkman"));
                houseNumber.setText(bundle.getString("address"));
                phoneNumber.setText(bundle.getString("phoneNum"));
                if("先生".equals(bundle.getString("gender"))){
                    radioBtnMan.setChecked(true);
                    radioBtnWeman.setChecked(false);
                }else{
                    radioBtnMan.setChecked(false);
                    radioBtnWeman.setChecked(true);
                }
                break;
            case R.id.tv_add_new_address_in_address_display:

                break;

        }
    }

    private void initView() {
        setMyActionBar("返回",R.mipmap.back,null,"","保存",NON_IMAGEVIEW,null);
        personName =(EditText)findViewById(R.id.et_name_in_adress_edit);
        houseNumber =(EditText)findViewById(R.id.et_house_number_in_adress_edit);
        phoneNumber =(EditText)findViewById(R.id.et_phone_number_in_adress_edit) ;
        verifyCode =(EditText)findViewById(R.id.et_verify_code_in_adress_edit) ;
        radioGroup =(RadioGroup)findViewById(R.id.radio_group_in_adress_edit) ;
        radioBtnMan =(RadioButton)findViewById(R.id.radio_btn_man_in_adress_edit) ;
        radioBtnWeman =(RadioButton)findViewById(R.id.radio_btn_weman_in_adress_edit);
        tvLocation =(TextView)findViewById(R.id.tv_locate_in_adress_edit) ;
        btnRegisterVerify =(Button)findViewById(R.id.btn_register_in_adress_edit);
        btnSaveAddress =(Button)findViewById(R.id.btn_save_address_in_address_edit);
    }


}
