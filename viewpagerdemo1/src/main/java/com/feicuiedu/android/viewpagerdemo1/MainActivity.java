package com.feicuiedu.android.viewpagerdemo1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenyan on 2016/6/12.
 */
public class MainActivity extends AppCompatActivity {
    private Bitmap cursor;

    private int offSet;

    private int currentItem;

    private Matrix matrix = new Matrix();

    private int bmWidth;

    private ImageView imageView;

    private LinearLayout ll_textview;

    private TextView pageview_txtview_1;

    private TextView pageview_txtview_2;

    private TextView pageview_txtview_3;

    private Animation animation;

    private List<View> lists;

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ll_textview = (LinearLayout) findViewById(R.id.ll_textview);

        pageview_txtview_1 = (TextView) ll_textview.findViewById(R.id.pageview_txtview_1);
        pageview_txtview_2 = (TextView) ll_textview.findViewById(R.id.pageview_txtview_2);
        pageview_txtview_3 = (TextView) ll_textview.findViewById(R.id.pageview_txtview_3);

        imageView = (ImageView)findViewById(R.id.cursor);

        lists = getData();
        initeCursor();
        viewPager = (ViewPager)findViewById(R.id.viewpager1);

        viewPager.setAdapter(pagerAdapter);

        viewPager.addOnPageChangeListener(ocl);

        pageview_txtview_1.setOnClickListener(txtViewListener);
        pageview_txtview_2.setOnClickListener(txtViewListener);
        pageview_txtview_3.setOnClickListener(txtViewListener);
    }

    public List<View> getData() {

        List<View> lists = new ArrayList<View>();
        lists.add(getLayoutInflater().inflate(R.layout.layout_red, null));
        lists.add(getLayoutInflater().inflate(R.layout.layout_yellow, null));
        lists.add(getLayoutInflater().inflate(R.layout.layout_blue, null));

        return lists;
    }

    private View.OnClickListener txtViewListener = new View.OnClickListener() {


        @Override
        public void onClick(View v) {

            switch(v.getId()){
                case R.id.pageview_txtview_1:
                    viewPager.setCurrentItem(0);
                    break;
                case R.id.pageview_txtview_2:
                    viewPager.setCurrentItem(1);
                    break;
                case R.id.pageview_txtview_3:
                    viewPager.setCurrentItem(2);
                    break;
                default:
                    viewPager.setCurrentItem(0);
                    break;
            }
        }
    };

    private ViewPager.OnPageChangeListener ocl = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int order) {

            /**
             移动动画：
             TranslateAnimation(float fromXDelta, float toXDelta, float fromYDelta, float toYDelta)
             参数的含义：
             float fromXDelta 动画开始的点离当前View X坐标上的差值
             float toXDelta 动画结束的点离当前View X坐标上的差值
             float fromYDelta 动画开始的点离当前View Y坐标上的差值
             float toYDelta 动画开始的点离当前View Y坐标上的差值
             */

            switch (order) {
                case 0:
                    if (currentItem == 1) {
                        animation = new TranslateAnimation(offSet * 2 + bmWidth, 0, 0, 0);
                    }
                    else
                    if (currentItem == 2) {
                        animation = new TranslateAnimation(offSet * 4 + 2 * bmWidth, 0, 0, 0);
                    }
                    break;
                case 1:
                    if (currentItem == 0) {
                        animation = new TranslateAnimation(0, offSet * 2 + bmWidth, 0, 0);
                    }
                    else
                    if (currentItem == 2) {
                        animation = new TranslateAnimation(4 * offSet + 2 * bmWidth, offSet * 2 + bmWidth, 0, 0);
                    }
                    break;
                case 2:
                    if (currentItem == 0) {
                        animation = new TranslateAnimation(0, 4 * offSet + 2 * bmWidth, 0, 0);
                    }
                    else
                    if (currentItem == 1) {
                        animation = new TranslateAnimation(offSet * 2 + bmWidth, 4 * offSet + 2 * bmWidth, 0, 0);
                    }
            }
            currentItem = order;

            animation.setDuration(500);
            animation.setFillAfter(true);
            imageView.startAnimation(animation);

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };

    private PagerAdapter pagerAdapter = new PagerAdapter() {

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {

            return arg0 == arg1;
        }

        @Override
        public int getCount() {

            return lists.size();
        }

        @Override
        public void destroyItem(View view, int position, Object object) {

            ((ViewPager) view).removeView(lists.get(position));
        };

        @Override
        public Object instantiateItem(View view, int position) {

            ((ViewPager) view).addView(lists.get(position), 0);
            return lists.get(position);
        };
    };

    private void initeCursor() {

        cursor = BitmapFactory.decodeResource(getResources(), R.drawable.textback);
        bmWidth = cursor.getWidth();

        DisplayMetrics dm;
        dm = getResources().getDisplayMetrics();

        offSet = (dm.widthPixels - 3 * bmWidth) / 6;
        matrix.setTranslate(offSet, 0);
        imageView.setImageMatrix(matrix); //需要iamgeView的scaleType为matrix
        currentItem = 0;
    }
}
