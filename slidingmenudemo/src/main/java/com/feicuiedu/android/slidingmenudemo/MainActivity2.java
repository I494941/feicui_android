package com.feicuiedu.android.slidingmenudemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

/**
 * Created by chenyan on 2016/11/23.
 */

public class MainActivity2 extends AppCompatActivity {

    private SlidingMenu mLeftMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slidingmen);

        mLeftMenu = (SlidingMenu) findViewById(R.id.sm_1);
        // configure the SlidingMenu
        // SlidingMenu menu = new SlidingMenu(this);
        mLeftMenu.setMode(SlidingMenu.LEFT);
        // 设置触摸屏幕的模式
        mLeftMenu.setShadowWidthRes(R.dimen.shadow_width);
        mLeftMenu.setShadowDrawable(R.mipmap.ic_launcher);

        mLeftMenu.setMenu(R.layout.activity_left);

        mLeftMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mLeftMenu.isMenuShowing())
                    mLeftMenu.toggle();
            }
        });
        // 设置滑动菜单视图的宽度
        // 设置渐入渐出效果的值
        /**
         * SLIDING_WINDOW will include the Title/ActionBar in the content
         * section of the SlidingMenu, while SLIDING_CONTENT does not.
         */

    }
}
