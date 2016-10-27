package com.feicuiedu.android.sqlitedemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

/**
 * Created by chenyan on 2016/10/27.
 */

public class LeadAcitivity extends AppCompatActivity {

    private static final String TAG = "LeadAcitivity";
    private ImageView iv_lead;
    private Animation.AnimationListener listener = new Animation.AnimationListener() {

        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {

            Intent intent = new Intent();
            intent.setClass(LeadAcitivity.this, MainActivity.class);
            startActivity(intent);

        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lead_layout);

        iv_lead = (ImageView) findViewById(R.id.iv_lead);

        // 创建一个补间动画
        Animation animation = new RotateAnimation(0f, 720f, Animation.ABSOLUTE, 0.5f, Animation.ABSOLUTE, 0.5f);
        animation.setDuration(5000);
        // 动画是和控件相关的,控件上执行动画
        iv_lead.startAnimation(animation);

        animation.setAnimationListener(listener);
    }


}
