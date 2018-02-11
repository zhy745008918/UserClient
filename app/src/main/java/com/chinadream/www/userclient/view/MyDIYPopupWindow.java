package com.chinadream.www.userclient.view;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import com.chinadream.www.userclient.R;

public class MyDIYPopupWindow extends PopupWindow{
    Activity context;
    View view;
    View.OnClickListener listener;
    public MyDIYPopupWindow(Activity context,View view, View.OnClickListener listener) {
        this.context=context;
        this.view=view;
        this.listener=listener;
    }

    public void initBackLayout() {
        //设置SelectPicPopupWindow的View
        this.setContentView(view);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.FILL_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.FILL_PARENT);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        ColorDrawable dw=new ColorDrawable(0x99000000);
        this.setBackgroundDrawable(dw);
        //设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.AnimBottom);
        final LinearLayout ll=(LinearLayout)view.findViewById(R.id.ll_photo_change);
        Button takePhoto=(Button)view.findViewById(R.id.btn_camera_inchange);
        Button album=(Button)view.findViewById(R.id.btn_photo_inchange);
        Button cancel=(Button)view.findViewById(R.id.btn_cancel_inchange);
        takePhoto.setOnClickListener(listener);
        album.setOnClickListener(listener);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        //mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        view.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                int height = ll.getTop();
                int y=(int) event.getY();
                if(event.getAction()==MotionEvent.ACTION_UP){
                    if(y<height){
                        dismiss();
                    }
                }
                return true;
            }
        });
    }
}
