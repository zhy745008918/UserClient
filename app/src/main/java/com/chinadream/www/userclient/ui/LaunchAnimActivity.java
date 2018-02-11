package com.chinadream.www.userclient.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.chinadream.www.userclient.R;

public class LaunchAnimActivity extends AppCompatActivity {
    ImageView ivAnimation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_anim);
        initView();
        initAnimation();
        initLocation();
    }

    private void initView() {
        ivAnimation =(ImageView)findViewById(R.id.iv_animation_in_home);
    }

    private void initLocation() {

    }
    /*** 初始化动画
     */
    private void initAnimation() {
        AlphaAnimation animation=new AlphaAnimation(1f,1f);
        animation.setDuration(3000);
        ivAnimation.setAnimation(animation);
        animation.start();
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }
            @Override
            public void onAnimationEnd(Animation animation) {
                Intent intent=new Intent(LaunchAnimActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();
            }
            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
