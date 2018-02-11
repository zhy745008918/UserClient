package com.chinadream.www.userclient.view;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chinadream.www.userclient.R;

/**
 * 自定义的dialog类
 */
public class MyCustomDialog extends Dialog{

    public MyCustomDialog(Context context) {
        super(context);

    }

    public MyCustomDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public static class Builder{
        Context context;
        String message,title,positiveText,negativeText;
        OnClickListener positiveClickListener,negativeClickListener;
        View contentView;
        public Builder (Context context){
            this.context=context;
        }
        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }
        /**
         * Set the Dialog message from resource
         *
         * @param message
         * @return
         */
        public Builder setMessage(int message) {
            this.message = (String) context.getText(message);
            return this;
        }

        /**
         * Set the Dialog title from resource
         *
         * @param title
         * @return
         */
        public Builder setTitle(int title) {
            this.title = (String) context.getText(title);
            return this;
        }

        /**
         * Set the Dialog title from String
         *
         * @param title
         * @return
         */

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setContentView(View v) {
            this.contentView = v;
            return this;
        }

        /**
         * Set the positive button resource and it's listener
         *
         * @param positiveButtonText
         * @return
         */
        public Builder setPositiveButton(int positiveButtonText,
                                         DialogInterface.OnClickListener listener) {
            this.positiveText = (String) context
                    .getText(positiveButtonText);
            this.positiveClickListener = listener;
            return this;
        }

        public Builder setPositiveButton(String positiveButtonText,
                                         DialogInterface.OnClickListener listener) {
            this.positiveText = positiveButtonText;
            this.positiveClickListener = listener;
            return this;
        }

        public Builder setNegativeButton(int negativeButtonText,
                                         DialogInterface.OnClickListener listener) {
            this.negativeText = (String) context
                    .getText(negativeButtonText);
            this.negativeClickListener = listener;
            return this;
        }

        public Builder setNegativeButton(String negativeButtonText,
                                         DialogInterface.OnClickListener listener) {
            this.negativeText = negativeButtonText;
            this.negativeClickListener = listener;
            return this;
        }
        public MyCustomDialog create(){
            LayoutInflater inflater= (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final MyCustomDialog dialog=new MyCustomDialog(context, R.style.Dialog);
            View view=inflater.inflate(R.layout.dialog_consignee_info_layout,null);
            dialog.addContentView(view, new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            //设置标题
            TextView tvTitle=(TextView)view.findViewById(R.id.dialog_title);
            if (title==null||"".equals(title)){
                tvTitle.setVisibility(View.GONE);
            }else{
                tvTitle.setText(title);
            }
            //设置消息
            TextView tvMsg=(TextView)view.findViewById(R.id.dialog_msg);
            if (message==null||"".equals(message)){
                tvMsg.setVisibility(View.GONE);
            }else{
                tvMsg.setText(message);
            }
            //设置确认按钮
            Button positiveButon=(Button)view.findViewById(R.id.dialog_positive);
            View dialogView=view.findViewById(R.id.dialog_view);
            if (positiveText!=null||!("".equals(positiveText))){
                positiveButon.setText(positiveText);
                positiveButon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        positiveClickListener.onClick(dialog,DialogInterface.BUTTON_POSITIVE);
                    }
                });
            }else{
                positiveButon.setVisibility(View.GONE);
                dialogView.setVisibility(View.GONE);
            }
            //设置取消键
            Button negativeButton=(Button)view.findViewById(R.id.dialog_nagative);
            if (negativeText!=null||!("".equals(negativeButton))){
                negativeButton.setText(negativeText);
                negativeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        negativeClickListener.onClick(dialog,DialogInterface.BUTTON_NEGATIVE);
                    }
                });
            }else{
                negativeButton.setVisibility(View.GONE);
                dialogView.setVisibility(View.GONE);
            }
            if (contentView!=null){
                LinearLayout linearLayout=(LinearLayout)view.findViewById(R.id.dialog_linearlayout);
                linearLayout.removeAllViews();
                linearLayout.addView(contentView,new ViewGroup.LayoutParams
                        (ViewGroup.LayoutParams.FILL_PARENT,ViewGroup.LayoutParams.FILL_PARENT));
            }
            dialog.setContentView(view);
            return dialog;
        }

    }

}
