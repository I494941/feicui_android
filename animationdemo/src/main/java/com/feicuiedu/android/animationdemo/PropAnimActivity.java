package com.feicuiedu.android.animationdemo;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


/**
 * Created by chenyan on 2016/10/27.
 */

public class PropAnimActivity extends AppCompatActivity {

    private static final String TAG = "PropAnimActivity";
    /*private Button btnTest;*/

    private int[] imgIds = new int[]{R.id.iv_0,R.id.iv_1,R.id.iv_2,R.id.iv_3,R.id.iv_4};
    private boolean clickTag = true;
    private View.OnClickListener ocl = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(PropAnimActivity.this,"我点击了",Toast.LENGTH_SHORT).show();

            switch (v.getId()) {
                case R.id.iv_0:

                    if (clickTag) {
                        for (int i = 1; i <imgIds.length ; i++) {
                            Log.d(TAG, "onCreate: 角度="+(Math.PI/2/(imgIds.length-1))*i);

                            float x = (float) (Math.sin((Math.PI/2/(imgIds.length-1))*i)*700);
                            float y = (float) (Math.cos((Math.PI/2/(imgIds.length-1))*i)*700);

                            Log.d(TAG, "onCreate: x="+x+",y="+y);

                            ObjectAnimator tranlateX = ObjectAnimator.ofFloat(imageViews[i], "translationX", 0, x);
                            ObjectAnimator tranlateY = ObjectAnimator.ofFloat(imageViews[i], "translationY", 0, y);

                            AnimatorSet animatorSet = new AnimatorSet();
                            animatorSet.play(tranlateX).with(tranlateY);
                            animatorSet.setDuration(500);
                            animatorSet.start();
                        }

                        clickTag = false;
                    }
                    else {
                        for (int i = 1; i <imgIds.length ; i++) {
                            Log.d(TAG, "onCreate: 角度="+(Math.PI/2/(imgIds.length-1))*i);

                            float x = (float) (Math.sin((Math.PI/2/(imgIds.length-1))*i)*700);
                            float y = (float) (Math.cos((Math.PI/2/(imgIds.length-1))*i)*700);

                            Log.d(TAG, "onCreate: x="+x+",y="+y);

                            ObjectAnimator tranlateX = ObjectAnimator.ofFloat(imageViews[i], "translationX", x, 0);
                            ObjectAnimator tranlateY = ObjectAnimator.ofFloat(imageViews[i], "translationY", y, 0);

                            AnimatorSet animatorSet = new AnimatorSet();
                            animatorSet.play(tranlateX).with(tranlateY);
                            animatorSet.setDuration(2000);
                            animatorSet.start();
                        }

                        clickTag = true;
                    }

                    break;
                default:
                    break;
            }
        }
    };



    private ImageView[] imageViews;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prop_layout);

        imageViews = new ImageView[imgIds.length];

        for (int i = 0; i < imageViews.length ; i++) {
            imageViews[i] = (ImageView) findViewById(imgIds[i]);

            imageViews[i].setOnClickListener(ocl);

        }


        //btnTest = (Button) findViewById(R.id.btn_test);

        /*TranslateAnimation translateAnimation = new TranslateAnimation(0f,350f,0f,800f);
        translateAnimation.setDuration(5000);
        //animation.setRepeatCount(5);

        ScaleAnimation scaleAnimation = new ScaleAnimation(0.0f, 1.4f, 0.0f, 1.4f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(5000);

        AnimationSet animationSet = new AnimationSet(this,null);
        animationSet.addAnimation(translateAnimation);
        animationSet.addAnimation(scaleAnimation);
        animationSet.setFillAfter(true);

        btnTest.startAnimation(animationSet);

        btnTest.setOnClickListener(ocl);*/

        /*AnimatorSet animatorSet = new AnimatorSet();//组合动画

        ObjectAnimator animatorX = ObjectAnimator.ofFloat(btnTest, "scaleX", 0, 3,1);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(btnTest, "scaleY", 0, 3,1);

        ObjectAnimator tranlateX = ObjectAnimator.ofFloat(btnTest, "translationX", 0, 700);
        ObjectAnimator tranlateY = ObjectAnimator.ofFloat(btnTest, "translationY", 0, 800);


        animatorSet.play(animatorX).with(animatorY).with(tranlateX).with(tranlateY);
        animatorSet.setDuration(10000);
        animatorSet.start();*/

        //btnTest.setOnClickListener(ocl);


    }
}
