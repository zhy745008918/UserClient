package com.chinadream.www.userclient.utils;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;

import com.chinadream.www.userclient.R;

public class ActionBarBuilder {
    TextView tv_center,tv_left,tv_right;
    Activity context;

    public ActionBarBuilder(Activity context) {
        this.context=context;
        tv_center=(TextView)context.findViewById(R.id.tv_actionbar_mid_in_base);
        tv_left=(TextView)context.findViewById(R.id.tv_actionbar_left_in_base);
        tv_right=(TextView)context.findViewById(R.id.tv_actionbar_right_in_base);
    }
    public ActionBarBuilder setMidText(String str){
        tv_center.setText(str);
        return this;
    }
    public ActionBarBuilder setLeftText(String str){
        tv_left.setText(str);
        return this;
    }
    public ActionBarBuilder setRightText(String str){
        tv_right.setText(str);
        return this;
    }
    public ActionBarBuilder setMidGone(){
        tv_center.setVisibility(View.GONE);
        return this;
    }
    public ActionBarBuilder setLeftGone(){
        tv_left.setVisibility(View.GONE);
        return this;
    }
    public ActionBarBuilder setRightGone(){
        tv_right.setVisibility(View.GONE);
        return this;
    }
    public ActionBarBuilder setLeftImage(int resId){
        Drawable left=context.getResources().getDrawable(resId);
        left.setBounds(0, 0, left.getMinimumWidth(), left.getMinimumHeight());
        tv_left.setCompoundDrawables(left,null,null,null);
        return this;
    }
    public ActionBarBuilder setRightImage(int resId){
        Drawable right=context.getResources().getDrawable(resId);
        right.setBounds(0, 0, right.getMinimumWidth(), right.getMinimumHeight());
        tv_right.setCompoundDrawables(null,null,right,null);
        return this;
    }
    public ActionBarBuilder setLeftClickListener(View.OnClickListener listener){
        if (View.VISIBLE==tv_left.getVisibility()){
            tv_left.setOnClickListener(listener);
        }
        return this;
    }
    public ActionBarBuilder setRightClickListener(View.OnClickListener listener){
        if (View.VISIBLE==tv_right.getVisibility()){
            tv_right.setOnClickListener(listener);
        }
        return this;
    }

}

